package br.com.sma_bad_smells.sma.behaviours.translateAgent;

import br.com.sma_bad_smells.sma.agents.TranslateAgent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class GetLogsBehaviour extends CyclicBehaviour {
    private final MessageTemplate messageTemplate = MessageTemplate.and(
            MessageTemplate.MatchPerformative(ACLMessage.INFORM),
            MessageTemplate.MatchConversationId("api-logs")
    );

    private final TranslateAgent agent;

    public GetLogsBehaviour(TranslateAgent agent){
        super(agent);
        this.agent = agent;
    }

    @Override
    public void action() {
        ACLMessage msg = agent.receive(messageTemplate);

        if(msg != null){
            System.out.println(agent.getLocalName() + " recebeu dados de " + msg.getSender().getLocalName());
            System.out.println("[MESSAGE] " + msg.getContent());

            agent.addLogsApi(msg.getContent());

            agent.addBehaviour(new SendConfirmationBehaviour(agent, "dataAgent"));
        }
        else block();


    }
}
