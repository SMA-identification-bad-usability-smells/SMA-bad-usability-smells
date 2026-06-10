package br.com.sma_bad_smells.sma.behaviours.translateAgent;

import br.com.sma_bad_smells.sma.agents.TranslateAgent;
import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;

public class SendConfirmationBehaviour extends OneShotBehaviour {
    private final TranslateAgent agent;
    private final String recipient;

    public SendConfirmationBehaviour(TranslateAgent agent, String recipient) {
        super(agent);
        this.agent = agent;
        this.recipient = recipient;
    }

    @Override
    public void action() {
        ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
        msg.addReceiver(new AID(recipient, AID.ISLOCALNAME));
        msg.setContent("Logs recebidos com sucesso.");
        msg.setConversationId("api-logs");

        System.out.println("Logs recebidos.");

        agent.send(msg);
    }
}
