package br.com.sma_bad_smells.sma.behaviours.dataAgent;

import br.com.sma_bad_smells.sma.agents.DataAgent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class ReceiveRequestBehaviour extends CyclicBehaviour {
    private final MessageTemplate messageTemplate = MessageTemplate.MatchPerformative(ACLMessage.REQUEST);
    private final DataAgent agent;

    public ReceiveRequestBehaviour(DataAgent agent){
        super(agent);
        this.agent = agent;
    }

    @Override
    public void action() {
        ACLMessage message = agent.receive(messageTemplate); //procura na fila uma msg com o templete REQUEST

        if(message != null){
            this.sendData(message);
        }
        else {
            block(); //trava o behavior por um tempo
        }
    }

    private void sendData(ACLMessage message){
        ACLMessage response = message.createReply();
        response.setPerformative(ACLMessage.INFORM);
        response.setContent("Aqui estão os dados");
        agent.send(response);
    }
}
