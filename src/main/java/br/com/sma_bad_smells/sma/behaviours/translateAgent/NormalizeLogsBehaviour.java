package br.com.sma_bad_smells.sma.behaviours.translateAgent;

import br.com.sma_bad_smells.sma.agents.TranslateAgent;
import br.com.sma_bad_smells.sma.domain.models.NormalizedLogs;
import br.com.sma_bad_smells.sma.normalization.LogNormalizer;
import br.com.sma_bad_smells.sma.normalization.LogNormalizerFactory;
import jade.core.behaviours.CyclicBehaviour;

import java.util.List;

public class NormalizeLogsBehaviour extends CyclicBehaviour {
    private TranslateAgent agent;
    private final LogNormalizer normalizer = LogNormalizerFactory.create();

    public NormalizeLogsBehaviour(TranslateAgent agent){
        super(agent);
        this.agent = agent;
    }

    @Override
    public void action() {
        if(!agent.getNormalizedLogs().isEmpty()){
            List<NormalizedLogs> normalizedLogs = normalizer.normalize(agent.getLogs());
            System.out.println(normalizedLogs);
        }
        else {
            block();
        }
    }
}
