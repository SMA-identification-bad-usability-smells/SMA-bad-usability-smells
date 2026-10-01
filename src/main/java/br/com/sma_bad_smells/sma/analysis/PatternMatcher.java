package br.com.sma_bad_smells.sma.analysis;

import br.com.sma_bad_smells.sma.analysis.model.GesturePattern;
import br.com.sma_bad_smells.sma.analysis.model.PatternTerm;
import br.com.sma_bad_smells.sma.analysis.model.SmellOccurrence;
import br.com.sma_bad_smells.sma.domain.models.NormalizedLogs;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PatternMatcher {
    public List<SmellOccurrence> findOccurrences(GesturePattern pattern, List<NormalizedLogs> bloco) {
        List<SmellOccurrence> ocorrencias = new ArrayList<>();
        List<PatternTerm> terms = pattern.getTerms();
        int n = terms.size();

        int i = 0;
        while (i <= bloco.size() - n) {          // só vale tentar se cabem n termos a partir de i
            if (matchesAt(terms, bloco, i)) {
                // ocorrência vai de i até i+n-1
                List<Long> ids = bloco.subList(i, i + n).stream()
                        .map(NormalizedLogs::getId)
                        .collect(Collectors.toList());
                ocorrencias.add(new SmellOccurrence(pattern.getType(), ids));
                i += n;                           // pula a ocorrência inteira (sem sobreposição)
            } else {
                i++;                              // não casou aqui, anda um
            }
        }
        return ocorrencias;
    }

    // todos os termos casam, em sequência estrita, a partir da posição 'start'?
    private boolean matchesAt(List<PatternTerm> terms, List<NormalizedLogs> bloco, int start) {
        for (int k = 0; k < terms.size(); k++) {
            if (!terms.get(k).matches(bloco.get(start + k))) {
                return false;
            }
        }
        return true;
    }
}
