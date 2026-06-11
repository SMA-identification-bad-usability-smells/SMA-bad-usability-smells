package br.com.sma_bad_smells.sma.behaviours.dataAgent;

import br.com.sma_bad_smells.sma.agents.DataAgent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;

import java.util.List;

public class ReceiveInformBehaviour extends CyclicBehaviour {
    private final MessageTemplate messageTemplate = MessageTemplate.and(
            MessageTemplate.MatchPerformative(ACLMessage.INFORM),
            MessageTemplate.MatchConversationId("logs-response")
    );

    private final DataAgent agent;

    public ReceiveInformBehaviour(DataAgent agent){
        super(agent);
        this.agent = agent;
    }

    @Override
    public void action() {
        ACLMessage message = agent.receive(messageTemplate);

        if(message != null){
//            INFORMAR PARA A API QUAIS LOGS FORAM RECEBIDOS COM SUCESSO PELO TRADUTOR
            try {
                @SuppressWarnings("unchecked")
                List<Long> ids = (List<Long>) message.getContentObject();

                System.out.println("[IDS COLETADOS COM SUCESSO] " + ids);
            } catch (UnreadableException e) {
                e.printStackTrace();
            }
        }
        else {
            block(); //trava o behavior por um tempo
        }
    }
}
