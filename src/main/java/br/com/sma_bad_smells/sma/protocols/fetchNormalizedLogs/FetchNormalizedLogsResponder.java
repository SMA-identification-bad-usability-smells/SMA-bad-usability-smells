package br.com.sma_bad_smells.sma.protocols.fetchNormalizedLogs;

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

public class FetchNormalizedLogsResponder extends AchieveREResponder {
    private final TranslateAgent agent;

    public FetchNormalizedLogsResponder(TranslateAgent agent) {
        super(agent, createMessageTemplate(FetchNormalizedLogsVocabulary.PROTOCOL));
        this.agent = agent;
    }

    @Override
    protected ACLMessage prepareResponse(ACLMessage request)
            throws NotUnderstoodException, RefuseException {
        System.out.println(agent.getLocalName() + ": recebi um pedido de logs normalizados de "
                + request.getSender().getLocalName());
        return null;
    }


    @Override
    protected ACLMessage prepareResultNotification(ACLMessage request, ACLMessage response){
        ACLMessage reply = request.createReply();

        List<NormalizedBatch> normalizedBatch = agent.getUnstoredNormalizedBatches();

        if(normalizedBatch == null || normalizedBatch.isEmpty()){
            reply.setPerformative(ACLMessage.FAILURE);
            reply.setContent("Sem logs normalizados disponíveis no momento");
        } else {
            List<NormalizedLogs> normalizedLogs = new ArrayList<>();
            normalizedBatch.forEach(normalizedBatch1 -> {
                normalizedLogs.addAll(normalizedBatch1.getLogs());
            });

            try {
                reply.setPerformative(ACLMessage.INFORM);
                reply.setContentObject(new ArrayList<>(normalizedLogs));
                normalizedBatch.forEach(NormalizedBatch::markStored);
                agent.removeFullyConsumedBatches();
            } catch (IOException e) {
                reply.setPerformative(ACLMessage.FAILURE);
                reply.setContent("Falha ao serializar logs normalizados");
            }
        }

        return reply;
    }

}
