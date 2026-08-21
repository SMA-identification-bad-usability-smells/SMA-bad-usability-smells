package br.com.sma_bad_smells.sma.behaviours.translateAgent;

import br.com.sma_bad_smells.sma.agents.TranslateAgent;
import br.com.sma_bad_smells.sma.protocols.fetchdata.FetchDataInitiator;
import jade.core.behaviours.TickerBehaviour;

public class FetchDataScheduler extends TickerBehaviour {
    private final TranslateAgent agent;

    public FetchDataScheduler(TranslateAgent agent, long periodMs){
        super(agent, periodMs);
        this.agent = agent;
    }

    @Override
    protected void onTick() {
        System.out.println(agent.getLocalName() + ": solicitando dados (tick " + getTickCount() + ")");
        agent.addBehaviour(new FetchDataInitiator(agent));
    }
}
