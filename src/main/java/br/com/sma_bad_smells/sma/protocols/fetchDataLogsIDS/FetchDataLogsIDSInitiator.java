package br.com.sma_bad_smells.sma.protocols.fetchDataLogsIDS;

import br.com.sma_bad_smells.sma.agents.TranslateAgent;
import br.com.sma_bad_smells.sma.domain.models.Logs;
import br.com.sma_bad_smells.sma.protocols.fetchdata.FetchDataVocabulary;
import jade.core.AID;
import jade.lang.acl.ACLMessage;
import jade.proto.AchieveREInitiator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FetchDataLogsIDSInitiator extends AchieveREInitiator {
    private final TranslateAgent agent;

    public FetchDataLogsIDSInitiator(TranslateAgent agent) throws IOException {
        super(agent, createOrder(agent));
        this.agent = agent;
    }

    private static ACLMessage createOrder(TranslateAgent agent) throws IOException {
        List<Long> ids = getIdsList(agent.getLogs());

        System.out.println(agent.getLocalName() + ": Lista de ids sendo enviados = " + ids);

        ACLMessage request = new ACLMessage(ACLMessage.REQUEST);
        request.addReceiver(new AID(FetchDataLogsIDSVocabulary.RESPONDER_NAME, AID.ISLOCALNAME));
        request.setProtocol(FetchDataVocabulary.PROTOCOL);
        request.setContent("Marque esses logs como normalizados");
        request.setContentObject(new ArrayList<>(ids));
        request.setConversationId("send-api-logs-ids");
        return request;
    }

    private static List<Long> getIdsList(List<Logs> logs){
        return logs.stream()
                .map(Logs::getId)
                .collect(Collectors.toList());
    }

    @Override
    protected void handleInform(ACLMessage inform){
        System.out.println(
                agent.getLocalName() + ": recebi confirmação de envio -> " + inform.getContent()
        );
    }

    @Override
    protected void handleRefuse(ACLMessage refuse) {
        System.out.println(agent.getLocalName() + ": pedido recusado.");
    }

    @Override
    protected void handleFailure(ACLMessage failure){
        System.out.println(agent.getLocalName() + ": o PersistenceAgent falhou -> " + failure.getContent());
    }
}
