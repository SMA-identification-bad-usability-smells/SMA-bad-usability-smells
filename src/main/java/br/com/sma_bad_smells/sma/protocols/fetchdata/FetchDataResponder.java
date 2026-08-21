package br.com.sma_bad_smells.sma.protocols.fetchdata;

import br.com.sma_bad_smells.sma.agents.DataAgent;
import jade.domain.FIPAAgentManagement.NotUnderstoodException;
import jade.domain.FIPAAgentManagement.RefuseException;
import jade.lang.acl.ACLMessage;
import jade.proto.AchieveREResponder;

public class FetchDataResponder extends AchieveREResponder {
    private final DataAgent agent;

    public FetchDataResponder(DataAgent agent) {
        super(agent, createMessageTemplate(FetchDataVocabulary.PROTOCOL));
        this.agent = agent;
    }

    @Override
    protected ACLMessage prepareResponse(ACLMessage request)
            throws NotUnderstoodException, RefuseException {
        System.out.println(agent.getLocalName() + ": recebi um pedido de dados de "
                + request.getSender().getLocalName());
        return null;
    }


    @Override
    protected ACLMessage prepareResultNotification(ACLMessage request, ACLMessage response){
        ACLMessage reply = request.createReply();

        String data = agent.getMostRecentData();

        if(data == null || data.isBlank()){
            reply.setPerformative(ACLMessage.FAILURE);
            reply.setContent("Sem dados disponíveis no momento");
        } else {
            reply.setPerformative(ACLMessage.INFORM);
            reply.setContent(data);
        }

        return reply;
    }

}
