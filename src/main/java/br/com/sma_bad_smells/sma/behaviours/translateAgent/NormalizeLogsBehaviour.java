package br.com.sma_bad_smells.sma.behaviours.translateAgent;

import br.com.sma_bad_smells.sma.agents.TranslateAgent;
import jade.core.behaviours.CyclicBehaviour;

import java.util.ArrayList;

public class NormalizeLogsBehaviour extends CyclicBehaviour {
    private final TranslateAgent agent;

    public NormalizeLogsBehaviour(TranslateAgent agent){
        super(agent);
        this.agent = agent;
    }

    @Override
    public void action() {
        if(!agent.getLogsApi().isEmpty()){
            System.out.println("Preparando para normalizar os logs.");

            System.out.println("[LOGSAPI] LogsApi = " + agent.getLogsApi());

            agent.setLogsApi(new ArrayList<String>());
        } else {
            block();
        }
    }
}
