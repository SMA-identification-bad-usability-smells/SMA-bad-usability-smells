package br.com.sma_bad_smells.sma.protocols.fetchNormalizedLogs;

import br.com.sma_bad_smells.sma.agents.PersistenceAgent;
import br.com.sma_bad_smells.sma.behaviours.persistenceAgent.SendNormalizedLogsBehaviour;
import br.com.sma_bad_smells.sma.domain.models.NormalizedLogs;
import jade.core.AID;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;
import jade.proto.AchieveREInitiator;

import java.util.List;

public class FetchNormalizedLogsInitiator extends AchieveREInitiator {
    private final PersistenceAgent agent;

    public FetchNormalizedLogsInitiator(PersistenceAgent agent) {
        super(agent, createOrder());
        this.agent = agent;
    }

    private static ACLMessage createOrder(){
        ACLMessage request = new ACLMessage(ACLMessage.REQUEST);
        request.addReceiver(new AID(FetchNormalizedLogsVocabulary.RESPONDER_NAME, AID.ISLOCALNAME));
        request.setProtocol(FetchNormalizedLogsVocabulary.PROTOCOL);
        request.setContent("Tem logs normalizados para enviar?");
        request.setConversationId("get-normalized-logs");
        return request;
    }

    @Override
    protected void handleInform(ACLMessage inform){
        try {
            @SuppressWarnings("unchecked")
            List<NormalizedLogs> normalizedLogs = (List<NormalizedLogs>) inform.getContentObject();
            System.out.println(agent.getLocalName() + ": recebi " + normalizedLogs.size() + " logs normalizados.");
            agent.addBehaviour(new SendNormalizedLogsBehaviour(agent, normalizedLogs));
        } catch (UnreadableException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void handleRefuse(ACLMessage refuse) {
        System.out.println(agent.getLocalName() + ": pedido recusado.");
    }

    @Override
    protected void handleFailure(ACLMessage failure){
        System.out.println(agent.getLocalName() + ": o TranslateAgent falhou -> " + failure.getContent());
    }
}
