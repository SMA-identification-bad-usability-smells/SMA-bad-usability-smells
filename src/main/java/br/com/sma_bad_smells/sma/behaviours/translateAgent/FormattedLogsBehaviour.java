package br.com.sma_bad_smells.sma.behaviours.translateAgent;

import br.com.sma_bad_smells.sma.agents.TranslateAgent;
import br.com.sma_bad_smells.sma.domain.models.Logs;
import br.com.sma_bad_smells.sma.utils.LogParser;
import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FormattedLogsBehaviour extends CyclicBehaviour {
    private final TranslateAgent agent;
    private final LogParser logParser = new LogParser();

    public FormattedLogsBehaviour(TranslateAgent agent){
        super(agent);
        this.agent = agent;
    }

    @Override
    public void action() {
        if(!agent.getLogsApi().isEmpty()){
            this.getLogsByLogsAPI();

            agent.setLogsApi(new ArrayList<>());

            this.sendIdsListMsg();
        } else {
            block();
        }
    }

    private void getLogsByLogsAPI(){
        agent.getLogsApi().forEach( logApi -> {
            try {
                List<Logs> logsList = logParser.parse(logApi);
                logsList.forEach(agent::addLogs);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void sendIdsListMsg(){
        List<Long> ids = agent.getLogs().stream()
                .map(Logs::getId)
                .collect(Collectors.toList());

        try {
            ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
            msg.addReceiver(new AID("dataAgent", AID.ISLOCALNAME));
            msg.setConversationId("logs-response");
            msg.setContentObject(new ArrayList<>(ids));
            agent.send(msg);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
