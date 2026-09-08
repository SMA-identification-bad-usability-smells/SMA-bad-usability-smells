package br.com.sma_bad_smells.sma.behaviours.persistenceAgent;

import br.com.sma_bad_smells.sma.agents.PersistenceAgent;
import br.com.sma_bad_smells.sma.domain.models.NormalizedLogs;
import br.com.sma_bad_smells.sma.utils.Config;
import jade.core.behaviours.OneShotBehaviour;

import java.util.List;

public class SendNormalizedLogsBehaviour extends OneShotBehaviour {
    private static final String API_URL = Config.API_URL;
    private final PersistenceAgent agent;
    private final List<NormalizedLogs> normalizedLogsList;

    public SendNormalizedLogsBehaviour(PersistenceAgent agent, List<NormalizedLogs> normalizedLogsList){
        super(agent);
        this.agent = agent;
        this.normalizedLogsList = normalizedLogsList;
    }

    @Override
    public void action() {
        try {
            System.out.println("[ENVIANDO NORMALIZEDLOGS...]");
            this.sendNormalizedLogsListToAPI();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sendNormalizedLogsListToAPI() throws Exception {
        try {
            String response = agent.getApiService().sendNormalizedLogsList(
                    API_URL + "/normalizedlogs/all",
                    normalizedLogsList);
        } catch (Exception e) {
            System.err.println(agent.getLocalName() + ": falha ao enviar normalized logs - " + e.getMessage());
            e.printStackTrace();
        }
    }
}
