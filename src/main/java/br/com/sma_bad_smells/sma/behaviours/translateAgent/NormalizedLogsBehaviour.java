package br.com.sma_bad_smells.sma.behaviours.translateAgent;

import br.com.sma_bad_smells.sma.agents.TranslateAgent;
import jade.core.behaviours.CyclicBehaviour;

public class NormalizedLogsBehaviour extends CyclicBehaviour {
    private final TranslateAgent agent;

    public NormalizedLogsBehaviour(TranslateAgent agent){
        super(agent);
        this.agent = agent;
    }

    @Override
    public void action() {
        if (!agent.getLogs().isEmpty()) {

        } else {
            block();
        }
    }
}
