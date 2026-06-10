package br.com.sma_bad_smells.sma.agents;

import br.com.sma_bad_smells.sma.behaviours.translateAgent.GetLogsBehaviour;
import br.com.sma_bad_smells.sma.utils.Config;
import jade.core.Agent;

import java.util.ArrayList;
import java.util.List;

public class TranslateAgent extends Agent {
    private List<String> logs;

    @Override
    protected void setup(){
        System.out.println("Olá, sou o agente tradutor!" + getLocalName());

        this.logs = new ArrayList<>();

        addBehaviour(new GetLogsBehaviour(this));
    }

    @Override
    protected void takeDown(){
        System.out.println("Agente " + getLocalName() + " sendo finalizado...");
    }

    public List<String> getLogs() {
        return logs;
    }

    public void setLogs(List<String> logs) {
        this.logs = logs;
    }

    public void addLog(String log){
        this.logs.add(log);
    }
}
