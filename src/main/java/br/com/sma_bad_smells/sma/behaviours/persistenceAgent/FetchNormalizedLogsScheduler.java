package br.com.sma_bad_smells.sma.behaviours.persistenceAgent;

import br.com.sma_bad_smells.sma.agents.PersistenceAgent;
import br.com.sma_bad_smells.sma.protocols.fetchNormalizedLogs.FetchNormalizedLogsInitiator;
import jade.core.behaviours.TickerBehaviour;

public class FetchNormalizedLogsScheduler extends TickerBehaviour {
    private final PersistenceAgent agent;

    public FetchNormalizedLogsScheduler(PersistenceAgent agent, long periodMs){
        super(agent, periodMs);
        this.agent = agent;
    }

    @Override
    protected void onTick() {
        System.out.println(agent.getLocalName() + ": solicitando logs normalizados (tick " + getTickCount() + ")");
        agent.addBehaviour(new FetchNormalizedLogsInitiator(agent));
    }
}
