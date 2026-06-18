package br.com.sma_bad_smells.sma.agents;

import br.com.sma_bad_smells.sma.behaviours.translateAgent.GetLogsBehaviour;
import br.com.sma_bad_smells.sma.behaviours.translateAgent.FormattedLogsBehaviour;
import br.com.sma_bad_smells.sma.behaviours.translateAgent.NormalizeLogsBehaviour;
import br.com.sma_bad_smells.sma.domain.models.Logs;
import br.com.sma_bad_smells.sma.domain.models.NormalizedLogs;
import jade.core.Agent;

import java.util.ArrayList;
import java.util.List;

public class TranslateAgent extends Agent {
    private List<String> logsApi = new ArrayList<>();
    private List<Logs> logs = new ArrayList<>();
    private List<NormalizedLogs> normalizedLogs = new ArrayList<>();

    @Override
    protected void setup(){
        addBehaviour(new GetLogsBehaviour(this));
        addBehaviour(new FormattedLogsBehaviour(this));
        addBehaviour(new NormalizeLogsBehaviour(this));
    }

    @Override
    protected void takeDown(){
        System.out.println("Agente " + getLocalName() + " finalizado.");
    }

    public List<String> getLogsApi() {
        return logsApi;
    }

    public List<Logs> getLogs() {
        return logs;
    }

    public List<NormalizedLogs> getNormalizedLogs() {
        return normalizedLogs;
    }

    public void addLogsApi(String logApi){
        this.logsApi.add(logApi);
    }

    public void addLogs(Logs newLogs){
        this.logs.add(newLogs);
    }

    public void addNormalizedLogs(NormalizedLogs newNormalizedLogs){
        this.normalizedLogs.add(newNormalizedLogs);
    }

    public void setLogsApi(List<String> logsApi) {
        this.logsApi = logsApi;
    }

    public void setLogs(List<Logs> logs) {
        this.logs = logs;
    }

    public void setNormalizedLogs(List<NormalizedLogs> normalizedLogs) {
        this.normalizedLogs = normalizedLogs;
    }
}
