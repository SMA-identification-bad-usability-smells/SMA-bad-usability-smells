package br.com.sma_bad_smells.sma.agents;

import br.com.sma_bad_smells.sma.behaviours.dataAgent.SendLogsIDsBehaviour;
import br.com.sma_bad_smells.sma.protocols.fetchDataLogsIDS.FetchDataLogsIDSResponder;
import br.com.sma_bad_smells.sma.service.ApiService;
import jade.core.Agent;

public class PersistenceAgent extends Agent {
    private final ApiService apiService = new ApiService();

    @Override
    protected void setup(){
        System.out.println("PersistenceAgent " + getLocalName() + " iniciado.");
        addBehaviour(new FetchDataLogsIDSResponder(this));
    }

    public ApiService getApiService(){
        return apiService;
    }
}
