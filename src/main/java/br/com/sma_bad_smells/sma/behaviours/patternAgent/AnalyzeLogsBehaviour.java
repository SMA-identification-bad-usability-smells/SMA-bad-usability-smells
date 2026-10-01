package br.com.sma_bad_smells.sma.behaviours.patternAgent;

import br.com.sma_bad_smells.sma.agents.PatternAgent;
import br.com.sma_bad_smells.sma.analysis.PatternCatalog;
import br.com.sma_bad_smells.sma.analysis.PatternMatcher;
import br.com.sma_bad_smells.sma.analysis.model.GesturePattern;
import br.com.sma_bad_smells.sma.analysis.model.SmellOccurrence;
import br.com.sma_bad_smells.sma.domain.models.NormalizedLogs;
import jade.core.behaviours.OneShotBehaviour;

import java.util.ArrayList;
import java.util.List;

public class AnalyzeLogsBehaviour extends OneShotBehaviour {
    private final PatternAgent agent;
    private final PatternMatcher matcher = new PatternMatcher();
    private final List<NormalizedLogs> bloco; // o lote recém-recebido para analisar

    public AnalyzeLogsBehaviour(PatternAgent agent, List<NormalizedLogs> bloco) {
        super(agent);
        this.agent = agent;
        this.bloco = bloco;
    }

    @Override
    public void action() {
        if (bloco == null || bloco.isEmpty()) {
            System.out.println(agent.getLocalName() + "Tentei começar a análise mas o bloco de logs está null ou vazio.");
            return; // nada a analisar
        }

        // roda TODOS os patterns do catálogo sobre o mesmo bloco e acumula as ocorrências
        List<SmellOccurrence> ocorrencias = new ArrayList<>();
        for (GesturePattern pattern : PatternCatalog.all()) {
            ocorrencias.addAll(matcher.findOccurrences(pattern, bloco));
        }

        // por enquanto: só mostra o que achou (validação visual antes de persistir)
        if (ocorrencias.isEmpty()) {
            System.out.println(agent.getLocalName() + ": nenhum smell neste bloco.");
        } else {
            System.out.println(agent.getLocalName() + ": " + ocorrencias.size() + " smell(s) detectado(s):");
            ocorrencias.forEach(o ->
                    System.out.println("  - " + o.getType() + " nos logs " + o.getNormalizedLogIds()));
        }

        // PRÓXIMO PASSO (ainda não): montar AnalysisResult e enviar ao PersistenceAgent
    }

}
