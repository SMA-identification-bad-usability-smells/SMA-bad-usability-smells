package br.com.sma_bad_smells.sma.behaviours.translateAgent;

import br.com.sma_bad_smells.sma.agents.TranslateAgent;
import br.com.sma_bad_smells.sma.models.Logs;
import br.com.sma_bad_smells.sma.utils.LogParser;
import jade.core.behaviours.CyclicBehaviour;

import java.util.ArrayList;
import java.util.List;

public class NormalizeLogsBehaviour extends CyclicBehaviour {
    private final TranslateAgent agent;
    private final LogParser logParser = new LogParser();

    public NormalizeLogsBehaviour(TranslateAgent agent){
        super(agent);
        this.agent = agent;
    }

    @Override
    public void action() {
        if(!agent.getLogsApi().isEmpty()){
            agent.getLogsApi().forEach( logApi -> {
                try {
                    List<Logs> logsList = logParser.parse(logApi);
                    logsList.forEach(agent::addLogs);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });

            agent.setLogsApi(new ArrayList<>());
        } else {
            block();
        }
    }
}
