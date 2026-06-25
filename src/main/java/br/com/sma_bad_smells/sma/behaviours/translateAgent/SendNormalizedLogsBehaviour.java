package br.com.sma_bad_smells.sma.behaviours.translateAgent;

import br.com.sma_bad_smells.sma.agents.TranslateAgent;
import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;

import java.io.IOException;
import java.util.ArrayList;

public class SendNormalizedLogsBehaviour extends OneShotBehaviour {
    private TranslateAgent agent;
    private String recipient;

    public SendNormalizedLogsBehaviour(TranslateAgent agent, String recipient){
        super(agent);
        this.agent = agent;
        this.recipient = recipient;
    }

    @Override
    public void action() {
        try {
            ACLMessage msg = createMessage();
            agent.send(msg);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    private ACLMessage createMessage(){
        ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
        msg.addReceiver(new AID(recipient, AID.ISLOCALNAME));
        msg.setConversationId("mormalized-logs");

        try {
            msg.setContentObject(new ArrayList<>(agent.getNormalizedLogs()));
            return msg;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
