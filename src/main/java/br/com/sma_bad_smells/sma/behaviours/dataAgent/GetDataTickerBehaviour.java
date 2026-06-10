package br.com.sma_bad_smells.sma.behaviours.dataAgent;

import br.com.sma_bad_smells.sma.agents.DataAgent;
import br.com.sma_bad_smells.sma.utils.Config;
import jade.core.behaviours.TickerBehaviour;

public class GetDataTickerBehaviour extends TickerBehaviour {

    private static final String API_URL = Config.API_URL;

    private final DataAgent agent;

    public GetDataTickerBehaviour(DataAgent agent, long periodMs){
        super(agent, periodMs);
        this.agent = agent;
    }

    @Override
    public void onTick() {
        try{
            String data = agent.getApiService().getData(API_URL);
            agent.setMostRecentData(data);
            System.out.println(agent.getLocalName() + ": dados atualizados.");
            System.out.println(agent.getMostRecentData());

            agent.addBehaviour(new SendLogsBehaviour(agent, "translateAgent"));
        } catch (Exception e) {
            System.out.println(agent.getLocalName() + ": falha ao buscar dados | " + e.getMessage());
        }
    }
}
