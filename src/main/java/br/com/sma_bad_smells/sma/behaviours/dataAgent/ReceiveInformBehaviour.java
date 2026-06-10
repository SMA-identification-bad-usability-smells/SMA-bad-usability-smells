package br.com.sma_bad_smells.sma.behaviours.dataAgent;

import br.com.sma_bad_smells.sma.agents.DataAgent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class ReceiveInformBehaviour extends CyclicBehaviour {
    private final MessageTemplate messageTemplate = MessageTemplate.MatchPerformative(ACLMessage.INFORM);
    private final DataAgent agent;

    public ReceiveInformBehaviour(DataAgent agent){
        super(agent);
        this.agent = agent;
    }

    @Override
    public void action() {
        ACLMessage message = agent.receive(messageTemplate); //procura na fila uma msg com o templete INFORM

        if(message != null){
            ACLMessage response = message.createReply();
            response.setPerformative(ACLMessage.INFORM);
            response.setContent("Aqui estão os dados");
            agent.send(response);
        }
        else {
            block(); //trava o behavior por um tempo
        }
    }
}
