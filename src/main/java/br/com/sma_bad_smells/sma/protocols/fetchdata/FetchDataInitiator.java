package br.com.sma_bad_smells.sma.protocols.fetchdata;

import br.com.sma_bad_smells.sma.agents.TranslateAgent;
import br.com.sma_bad_smells.sma.behaviours.translateAgent.FormattedLogsBehaviour;
import jade.core.AID;
import jade.core.Agent;
import jade.domain.FIPANames;
import jade.lang.acl.ACLMessage;
import jade.proto.AchieveREInitiator;

public class FetchDataInitiator extends AchieveREInitiator {
    private final TranslateAgent agent;

    public FetchDataInitiator(TranslateAgent agent) {
        super(agent, createOrder());
        this.agent = agent;
    }

    private static ACLMessage createOrder(){
        ACLMessage request = new ACLMessage(ACLMessage.REQUEST);
        request.addReceiver(new AID(FetchDataVocabulary.RESPONDER_NAME, AID.ISLOCALNAME));
        request.setProtocol(FetchDataVocabulary.PROTOCOL);
        request.setContent("Me envie os dados mais recentes");
        request.setConversationId("get-logs");
        return request;
    }

    @Override
    protected void handleInform(ACLMessage inform){
        System.out.println(
                agent.getLocalName() + ": recebi os dados."
        );
        agent.addLogsApi(inform.getContent());
        agent.addBehaviour(new FormattedLogsBehaviour(agent));
    }

    @Override
    protected void handleRefuse(ACLMessage refuse) {
        System.out.println(agent.getLocalName() + ": pedido recusado.");
    }

    @Override
    protected void handleFailure(ACLMessage failure){
        System.out.println(agent.getLocalName() + ": o DataAgent falhou -> " + failure.getContent());
    }
}
