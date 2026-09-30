package br.com.sma_bad_smells.sma.agents;

import br.com.sma_bad_smells.sma.behaviours.translateAgent.FetchDataScheduler;
import br.com.sma_bad_smells.sma.behaviours.translateAgent.GetLogsBehaviour;
import br.com.sma_bad_smells.sma.behaviours.translateAgent.FormattedLogsBehaviour;
import br.com.sma_bad_smells.sma.behaviours.translateAgent.NormalizeLogsBehaviour;
import br.com.sma_bad_smells.sma.domain.models.Logs;
import br.com.sma_bad_smells.sma.domain.models.NormalizedBatch;
import br.com.sma_bad_smells.sma.domain.models.NormalizedLogs;
import br.com.sma_bad_smells.sma.protocols.fetchdata.FetchDataInitiator;
import br.com.sma_bad_smells.sma.protocols.fetchNormalizedLogs.FetchNormalizedLogsResponder;
import br.com.sma_bad_smells.sma.protocols.fetchNormalizedLogsForAnalysis.FetchNormalizedLogsForAnalysisResponder;
import jade.core.Agent;

import java.util.ArrayList;
import java.util.List;

public class TranslateAgent extends Agent {
    private List<String> logsApi = new ArrayList<>();
    private List<Logs> logs = new ArrayList<>();
    private List<NormalizedBatch> normalizedBatches = new ArrayList<>();
    private long nextBatchId = 1;

    @Override
    protected void setup(){
        addBehaviour(new FetchDataScheduler(this, 10_000));
        addBehaviour(new FetchNormalizedLogsResponder(this));
        addBehaviour(new FetchNormalizedLogsForAnalysisResponder(this));
    }

    @Override
    protected void takeDown(){
        System.out.println("Agente " + getLocalName() + " finalizado.");
    }

    public void addBache(List<NormalizedLogs> normalizedLogs){
        if (normalizedLogs != null && !normalizedLogs.isEmpty()) {
            normalizedBatches.add(new NormalizedBatch(nextBatchId++, normalizedLogs));
        }
    }

    public void removeFullyConsumedBatches(){
        this.normalizedBatches.removeIf(NormalizedBatch::isFullyConsumed);
    }

    public void setLogsAsStored(){
        this.normalizedBatches.forEach(NormalizedBatch::markStored);
    }

    public void setLogsAsAnalyzed(){
        this.normalizedBatches.forEach(NormalizedBatch::markAnalyzed);
    }

    public List<NormalizedBatch> getNormalizedBatches() {
        return normalizedBatches;
    }

    public List<NormalizedBatch> getUnstoredNormalizedBatches() {
        return normalizedBatches.stream().filter(
                normalizedBatch -> !normalizedBatch.isStored()).toList();
    }

    public List<NormalizedBatch> getUnanalyzedNormalizedBatches() {
        return normalizedBatches.stream().filter(
                normalizedBatch -> !normalizedBatch.isAnalyzed()).toList();
    }

    public void setNormalizedBatches(List<NormalizedBatch> normalizedBatches) {
        this.normalizedBatches = normalizedBatches;
    }

    public List<String> getLogsApi() {
        return logsApi;
    }

    public List<Logs> getLogs() {
        return logs;
    }

    public void addLogsApi(String logApi){
        this.logsApi.add(logApi);
    }

    public void addLogs(Logs newLogs){
        this.logs.add(newLogs);
    }

    public void setLogsApi(List<String> logsApi) {
        this.logsApi = logsApi;
    }

    public void setLogs(List<Logs> logs) {
        this.logs = logs;
    }
}
