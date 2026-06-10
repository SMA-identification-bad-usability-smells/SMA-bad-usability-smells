package br.com.sma_bad_smells.sma.behaviours.dataAgent;

import br.com.sma_bad_smells.sma.agents.DataAgent;
import br.com.sma_bad_smells.sma.utils.Config;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.TickerBehaviour;

public class GetDataOneShotBehavior extends OneShotBehaviour {

    private static final String API_URL = Config.API_URL;

    private final DataAgent agent;

    public GetDataOneShotBehavior(DataAgent agent){
        super(agent);
        this.agent = agent;
    }

    @Override
    public void action() {
        try{
            String data = agent.getApiService().getData(API_URL);
            agent.setMostRecentData(data);
            System.out.println(agent.getLocalName() + ": dados atualizados.");
            System.out.println(agent.getMostRecentData());
        } catch (Exception e) {
            System.out.println(agent.getLocalName() + ": falha ao buscar dados | " + e.getMessage());
        }
    }
}
