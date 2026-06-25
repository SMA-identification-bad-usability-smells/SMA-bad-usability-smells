package br.com.sma_bad_smells.sma.behaviours.dataAgent;

import br.com.sma_bad_smells.sma.agents.DataAgent;
import br.com.sma_bad_smells.sma.domain.dto.LogsIdsDTO;
import br.com.sma_bad_smells.sma.domain.models.NormalizedLogs;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;

import java.util.List;
import java.util.stream.Collectors;

public class ReceiveInformBehaviour extends CyclicBehaviour {
    private final MessageTemplate messageTemplate = MessageTemplate.MatchPerformative(ACLMessage.INFORM);

    private final DataAgent agent;

    public ReceiveInformBehaviour(DataAgent agent){
        super(agent);
        this.agent = agent;
    }

    @Override
    public void action() {
        ACLMessage message = agent.receive(messageTemplate);

        if(message != null){
            String conversationId = message.getConversationId();

            if(conversationId.equals("mormalized-logs")){
                // envia para a api
            }
            else if(conversationId.equals("logs-response")){
                sendLogsIDSDTOtoAPI(message);
            }
        }
        else {
            block();
        }
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

    private LogsIdsDTO getLogsIdsDTOByMessageContent(ACLMessage message) throws UnreadableException {
        @SuppressWarnings("unchecked")
        List<Long> ids = (List<Long>) message.getContentObject();

        String idsSting = ids.stream().map(String::valueOf).collect(Collectors.joining(","));

        return new LogsIdsDTO(idsSting);
    }
}
