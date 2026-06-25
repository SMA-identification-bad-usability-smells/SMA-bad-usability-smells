package br.com.sma_bad_smells.sma.agents;

import br.com.sma_bad_smells.sma.behaviours.dataAgent.GetDataTickerBehaviour;
import br.com.sma_bad_smells.sma.behaviours.dataAgent.ReceiveInformBehaviour;
import br.com.sma_bad_smells.sma.behaviours.dataAgent.ReceiveRequestBehaviour;
import br.com.sma_bad_smells.sma.service.ApiService;
import br.com.sma_bad_smells.sma.utils.Config;
import jade.core.Agent;

public class DataAgent extends Agent {
    private final ApiService apiService = new ApiService();
    private String mostRecentData;

    @Override
    protected void setup(){
        addBehaviour(new GetDataTickerBehaviour(this, Config.INTERVAL_MS));
        addBehaviour(new ReceiveRequestBehaviour(this));
        addBehaviour(new ReceiveInformBehaviour(this));

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
