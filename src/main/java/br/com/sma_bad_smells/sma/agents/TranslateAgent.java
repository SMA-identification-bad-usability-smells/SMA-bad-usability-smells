package br.com.sma_bad_smells.sma.agents;

import br.com.sma_bad_smells.sma.behaviours.translateAgent.GetLogsBehaviour;
import br.com.sma_bad_smells.sma.behaviours.translateAgent.NormalizeLogsBehaviour;
import br.com.sma_bad_smells.sma.models.Logs;
import br.com.sma_bad_smells.sma.models.NormalizedLogs;
import jade.core.Agent;

import java.util.ArrayList;
import java.util.List;

public class TranslateAgent extends Agent {
    private List<String> logsApi = new ArrayList<>();
    private final List<Logs> logs = new ArrayList<>();
    private final List<NormalizedLogs> normalizedLogs = new ArrayList<>();

    @Override
    protected void setup(){
        System.out.println("Olá, sou o agente tradutor " + getLocalName());

        addBehaviour(new GetLogsBehaviour(this));
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
}
