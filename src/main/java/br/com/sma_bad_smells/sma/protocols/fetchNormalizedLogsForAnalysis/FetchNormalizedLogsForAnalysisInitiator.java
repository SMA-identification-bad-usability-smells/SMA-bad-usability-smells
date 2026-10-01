package br.com.sma_bad_smells.sma.protocols.fetchNormalizedLogsForAnalysis;

import br.com.sma_bad_smells.sma.agents.PatternAgent;
import br.com.sma_bad_smells.sma.behaviours.patternAgent.AnalyzeLogsBehaviour;
import br.com.sma_bad_smells.sma.domain.models.NormalizedLogs;
import jade.core.AID;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;
import jade.proto.AchieveREInitiator;

import java.util.List;

public class FetchNormalizedLogsForAnalysisInitiator extends AchieveREInitiator {
    private final PatternAgent agent;

    public FetchNormalizedLogsForAnalysisInitiator(PatternAgent agent) {
        super(agent, createOrder());
        this.agent = agent;
    }

    private static ACLMessage createOrder(){
        ACLMessage request = new ACLMessage(ACLMessage.REQUEST);
        request.addReceiver(new AID(FetchNormalizedLogsForAnalysisVocabulary.RESPONDER_NAME, AID.ISLOCALNAME));
        request.setProtocol(FetchNormalizedLogsForAnalysisVocabulary.PROTOCOL);
        request.setContent("Tem logs normalizados para analisar?");
        request.setConversationId("get-normalized-logs-for-analysis");
        return request;
    }

    @Override
    protected void handleInform(ACLMessage inform){
        try {
            @SuppressWarnings("unchecked")
            List<NormalizedLogs> normalizedLogs = (List<NormalizedLogs>) inform.getContentObject();
            System.out.println(agent.getLocalName() + ": recebi " + normalizedLogs.size() + " logs normalizados para analisar.");
            agent.addBehaviour(new AnalyzeLogsBehaviour(agent, normalizedLogs));
        } catch (UnreadableException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void handleRefuse(ACLMessage refuse) {
        System.out.println(agent.getLocalName() + ": pedido recusado.");
    }

    @Override
    protected void handleFailure(ACLMessage failure){
        System.out.println(agent.getLocalName() + ": o TranslateAgent falhou -> " + failure.getContent());
    }
}
