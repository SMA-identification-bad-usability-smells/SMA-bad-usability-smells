package br.com.sma_bad_smells.sma.protocols.fetchNormalizedLogsForAnalysis;

import br.com.sma_bad_smells.sma.agents.TranslateAgent;
import br.com.sma_bad_smells.sma.domain.models.NormalizedBatch;
import br.com.sma_bad_smells.sma.domain.models.NormalizedLogs;
import jade.domain.FIPAAgentManagement.NotUnderstoodException;
import jade.domain.FIPAAgentManagement.RefuseException;
import jade.lang.acl.ACLMessage;
import jade.proto.AchieveREResponder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FetchNormalizedLogsForAnalysisResponder extends AchieveREResponder {
    private final TranslateAgent agent;

    public FetchNormalizedLogsForAnalysisResponder(TranslateAgent agent) {
        super(agent, createMessageTemplate(FetchNormalizedLogsForAnalysisVocabulary.PROTOCOL));
        this.agent = agent;
    }

    @Override
    protected ACLMessage prepareResponse(ACLMessage request)
            throws NotUnderstoodException, RefuseException {
        System.out.println(agent.getLocalName() + ": recebi um pedido de logs normalizados para análise de "
                + request.getSender().getLocalName());
        return null;
    }


    @Override
    protected ACLMessage prepareResultNotification(ACLMessage request, ACLMessage response){
        ACLMessage reply = request.createReply();

        List<NormalizedBatch> normalizedBatches = agent.getUnanalyzedNormalizedBatches();

        if(normalizedBatches == null || normalizedBatches.isEmpty()){
            reply.setPerformative(ACLMessage.FAILURE);
            reply.setContent("Sem logs normalizados disponíveis no momento");
        } else {
            List<NormalizedLogs> normalizedLogs = new ArrayList<>();
            normalizedBatches.forEach(normalizedBatch1 -> {
                normalizedLogs.addAll(normalizedBatch1.getLogs());
            });

            try {
                reply.setPerformative(ACLMessage.INFORM);
                reply.setContentObject(new ArrayList<>(normalizedLogs));
            } catch (IOException e) {
                reply.setPerformative(ACLMessage.FAILURE);
                reply.setContent("Falha ao serializar logs normalizados");
            }
        }

        return reply;
    }

}
