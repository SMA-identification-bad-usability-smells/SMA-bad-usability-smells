package br.com.sma_bad_smells.sma.agents;

import br.com.sma_bad_smells.sma.behaviours.dataAgent.GetDataBehavior;
import br.com.sma_bad_smells.sma.behaviours.dataAgent.ReceiveRequestBehavior;
import br.com.sma_bad_smells.sma.service.ApiService;
import br.com.sma_bad_smells.sma.utils.Config;
import jade.core.Agent;

public class DataAgent extends Agent {
    private final ApiService apiService = new ApiService();
    private String mostRecentData;

    @Override
    protected void setup(){
        System.out.println("Agente " + getLocalName() + " foi iniciado com sucesso.");

        addBehaviour(new GetDataBehavior(this, Config.INTERVAL_MS));
        addBehaviour(new ReceiveRequestBehavior(this));
    }

    @Override
    protected void takeDown(){
        System.out.println("Agente " + getLocalName() + " está sendo finalizado.");
    }

    public ApiService getApiService() {
        return apiService;
    }

    public String getMostRecentData() {
        return mostRecentData;
    }

    public void setMostRecentData(String dados) {
        this.mostRecentData = dados;
    }
}
