package br.com.sma_bad_smells.sma.behaviours.patternAgent;

import br.com.sma_bad_smells.sma.agents.PatternAgent;
import br.com.sma_bad_smells.sma.protocols.fetchNormalizedLogsForAnalysis.FetchNormalizedLogsForAnalysisInitiator;
import jade.core.behaviours.TickerBehaviour;

public class FetchNormalizedLogsForAnalysisScheduler extends TickerBehaviour {
    private final PatternAgent agent;

    public FetchNormalizedLogsForAnalysisScheduler(PatternAgent agent, long periodMs){
        super(agent, periodMs);
        this.agent = agent;
    }

    @Override
    protected void onTick() {
        System.out.println(agent.getLocalName() + ": solicitando logs normalizados para análise (tick " + getTickCount() + ")");
        agent.addBehaviour(new FetchNormalizedLogsForAnalysisInitiator(agent));
    }
}
