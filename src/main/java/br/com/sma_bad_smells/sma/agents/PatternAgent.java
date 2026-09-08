package br.com.sma_bad_smells.sma.agents;

import br.com.sma_bad_smells.sma.behaviours.patternAgent.FetchNormalizedLogsForAnalysisScheduler;
import br.com.sma_bad_smells.sma.domain.models.NormalizedLogs;
import jade.core.Agent;

import java.util.ArrayList;
import java.util.List;

public class PatternAgent extends Agent {
    private List<NormalizedLogs> normalizedLogs = new ArrayList<>();

    @Override
    protected void setup(){
        System.out.println("PatternAgent " + getLocalName() + " iniciado.");
        addBehaviour(new FetchNormalizedLogsForAnalysisScheduler(this, 10_000));
    }

    @Override
    protected void takeDown(){
        System.out.println("Agente " + getLocalName() + " finalizado.");
    }

    public List<NormalizedLogs> getNormalizedLogs() {
        return normalizedLogs;
    }

    public void setNormalizedLogs(List<NormalizedLogs> normalizedLogs) {
        this.normalizedLogs = normalizedLogs;
    }
}
