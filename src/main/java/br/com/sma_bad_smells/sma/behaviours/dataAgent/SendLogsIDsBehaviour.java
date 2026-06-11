package br.com.sma_bad_smells.sma.behaviours.dataAgent;

import br.com.sma_bad_smells.sma.agents.DataAgent;
import br.com.sma_bad_smells.sma.domain.dto.LogsIdsDTO;
import br.com.sma_bad_smells.sma.utils.Config;
import jade.core.behaviours.OneShotBehaviour;

public class SendLogsIDsBehaviour extends OneShotBehaviour {
    private static final String API_URL = Config.API_URL;
    private final DataAgent agent;
    private final LogsIdsDTO ids;

    public SendLogsIDsBehaviour(DataAgent agent, LogsIdsDTO ids){
        super(agent);
        this.agent = agent;
        this.ids = ids;
    }

    @Override
    public void action() {
        try {
            this.sendIdsListToAPI();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sendIdsListToAPI() throws Exception {
        String response = agent.getApiService().markLogsReceived(
                API_URL + "/ids/all",
                ids
        );
    }
}
