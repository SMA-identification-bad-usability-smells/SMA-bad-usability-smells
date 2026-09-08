package br.com.sma_bad_smells.sma.behaviours.persistenceAgent;

import br.com.sma_bad_smells.sma.agents.DataAgent;
import br.com.sma_bad_smells.sma.agents.PersistenceAgent;
import br.com.sma_bad_smells.sma.domain.dto.LogsIdsDTO;
import br.com.sma_bad_smells.sma.domain.dto.NormalizedLogsDTO;
import br.com.sma_bad_smells.sma.domain.models.NormalizedLogs;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;

import java.util.List;
import java.util.stream.Collectors;

public class ReceiveInformBehaviour extends CyclicBehaviour {
    private final MessageTemplate messageTemplate = MessageTemplate.MatchPerformative(ACLMessage.INFORM);
    private final PersistenceAgent agent;

    public ReceiveInformBehaviour(PersistenceAgent agent){
        super(agent);
        this.agent = agent;
    }

    @Override
    public void action() {
        ACLMessage message = agent.receive(messageTemplate);

        if(message != null){
            String conversationId = message.getConversationId();

            // Não utilizado mais: SendNormalizedLogsBehaviour (behaviours.dataAgent) passou a ser
            // usada pelo DataAgent, chamada a partir do FetchNormalizedLogsInitiator, e não recebe
            // mais um PersistenceAgent no construtor — o envio agora é pull, não por INFORM aqui.
            //if(conversationId.equals("mormalized-logs")){
            //    sendNormalizedLogsToAPI(message);
            //}
            if(conversationId.equals("logs-response")){
                sendLogsIDSDTOtoAPI(message);
            }
        }
        else {
            block();
        }
    }

    // Não utilizado mais: ver nota em action(). SendNormalizedLogsBehaviour agora é construída
    // com um DataAgent, então esta chamada (com um PersistenceAgent) não se aplica mais.
    //private void sendNormalizedLogsToAPI(ACLMessage message){
    //    try {
    //        List<NormalizedLogs> normalizedLogs = this.getNormalizedLogsByMessageContent(message);
    //        agent.addBehaviour(new SendNormalizedLogsBehaviour(agent, normalizedLogs));
    //    } catch (Exception e) {
    //        throw new RuntimeException(e);
    //    }
    //}

    private List<NormalizedLogs> getNormalizedLogsByMessageContent(ACLMessage message)
            throws UnreadableException {
        @SuppressWarnings("unchecked")
                List<NormalizedLogs> normalizedLogs = (List<NormalizedLogs>) message.getContentObject();

        return normalizedLogs;
    }

    private void sendLogsIDSDTOtoAPI(ACLMessage message){
        //            INFORMAR PARA A API QUAIS LOGS FORAM RECEBIDOS COM SUCESSO PELO TRADUTOR
        try {
            LogsIdsDTO logsIdsRequest = this.getLogsIdsDTOByMessageContent(message);

            agent.addBehaviour(new SendLogsIDsBehaviour(agent, logsIdsRequest));
        } catch (UnreadableException e) {
            e.printStackTrace();
        }
    }

    private LogsIdsDTO getLogsIdsDTOByMessageContent(ACLMessage message)
            throws UnreadableException {
        @SuppressWarnings("unchecked")
            List<Long> ids = (List<Long>) message.getContentObject();

        String idsSting = ids.stream().map(String::valueOf).collect(Collectors.joining(","));

        return new LogsIdsDTO(idsSting);
    }
}
