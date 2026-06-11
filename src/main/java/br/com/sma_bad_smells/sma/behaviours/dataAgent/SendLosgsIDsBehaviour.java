package br.com.sma_bad_smells.sma.behaviours.dataAgent;

import br.com.sma_bad_smells.sma.agents.DataAgent;
import br.com.sma_bad_smells.sma.models.LogsIdsRequest;
import br.com.sma_bad_smells.sma.utils.Config;
import jade.core.behaviours.OneShotBehaviour;

import java.util.List;

public class SendLosgsIDsBehaviour extends OneShotBehaviour {
    private static final String API_URL = Config.API_URL;
    private final DataAgent agent;
    private final LogsIdsRequest ids;

    public SendLosgsIDsBehaviour(DataAgent agent, LogsIdsRequest ids){
        super(agent);
        this.agent = agent;
        this.ids = ids;
    }

    @Override
    public void action() {
        try {
            String response = agent.getApiService().markLogsReceived(
                    API_URL + "/ids/all",
                    ids
            );
            System.out.println(response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
