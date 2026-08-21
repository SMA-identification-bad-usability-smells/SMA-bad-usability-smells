package br.com.sma_bad_smells.sma.protocols.fetchDataLogsIDS;

import br.com.sma_bad_smells.sma.agents.DataAgent;
import br.com.sma_bad_smells.sma.agents.PersistenceAgent;
import br.com.sma_bad_smells.sma.behaviours.dataAgent.SendLogsIDsBehaviour;
import br.com.sma_bad_smells.sma.domain.dto.LogsIdsDTO;
import br.com.sma_bad_smells.sma.protocols.fetchdata.FetchDataVocabulary;
import jade.domain.FIPAAgentManagement.NotUnderstoodException;
import jade.domain.FIPAAgentManagement.RefuseException;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;
import jade.proto.AchieveREResponder;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FetchDataLogsIDSResponder extends AchieveREResponder {
    private final PersistenceAgent agent;

    public FetchDataLogsIDSResponder(PersistenceAgent agent) {
        super(agent, createMessageTemplate(FetchDataLogsIDSVocabulary.PROTOCOL));
        this.agent = agent;
    }

    @Override
    protected ACLMessage prepareResponse(ACLMessage request)
            throws NotUnderstoodException, RefuseException {
        System.out.println(agent.getLocalName() + ": recebi um pedido de envio de dados de "
                + request.getSender().getLocalName());
        return null; // sem AGREE explícito por enquanto
    }


    @Override
    protected ACLMessage prepareResultNotification(ACLMessage request, ACLMessage response){
        ACLMessage reply = request.createReply();

        ArrayList<Long> data = null;
        try {
            data = (ArrayList<Long>) request.getContentObject();
        } catch (UnreadableException e) {
            throw new RuntimeException(e);
        }

        if(data == null || data.isEmpty()){
            reply.setPerformative(ACLMessage.FAILURE);
            reply.setContent("Lista de IDS não foram recebidos.");
        } else {
            reply.setPerformative(ACLMessage.INFORM);
            reply.setContent("Lista de IDS recebida com sucesso.");
            System.out.println(agent.getLocalName() + ": Tentando enviar os IDS...");
            sendLogsIDSDTOtoAPI(data);
        }

        return reply;
    }

    private void sendLogsIDSDTOtoAPI(List<Long>  data){
        //            INFORMAR PARA A API QUAIS LOGS FORAM RECEBIDOS COM SUCESSO PELO TRADUTOR
        try {
            LogsIdsDTO logsIdsRequest = this.getLogsIdsDTOByMessageContent(data);

            agent.addBehaviour(new SendLogsIDsBehaviour(agent, logsIdsRequest));
        } catch (UnreadableException e) {
            e.printStackTrace();
        }
    }

    private LogsIdsDTO getLogsIdsDTOByMessageContent(List<Long> ids)
            throws UnreadableException {
        String idsSting = ids.stream().map(String::valueOf).collect(Collectors.joining(","));

        return new LogsIdsDTO(idsSting);
    }

}
