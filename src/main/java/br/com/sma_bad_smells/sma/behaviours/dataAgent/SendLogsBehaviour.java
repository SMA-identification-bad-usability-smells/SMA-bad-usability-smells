package br.com.sma_bad_smells.sma.behaviours.dataAgent;

import br.com.sma_bad_smells.sma.agents.DataAgent;
import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;

public class SendLogsBehaviour extends OneShotBehaviour {
    private final DataAgent agent;
    private final String recipient;

    public SendLogsBehaviour(DataAgent agent, String recipient) {
        super(agent);
        this.agent = agent;
        this.recipient = recipient;
    }

    @Override
    public void action() {
        String data = agent.getMostRecentData();

        if (data == null || data.isBlank()) return;

        ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
        msg.addReceiver(new AID(recipient, AID.ISLOCALNAME));
        msg.setContent(data);
        msg.setConversationId("api-logs");

        agent.send(msg);

        System.out.println(agent.getLocalName() + " enviou logs para " + recipient);
    }
}
