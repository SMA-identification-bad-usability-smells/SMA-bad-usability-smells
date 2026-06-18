package br.com.sma_bad_smells.sma.normalization;

import br.com.sma_bad_smells.sma.domain.models.Logs;
import br.com.sma_bad_smells.sma.domain.models.NormalizedLogs;
import br.com.sma_bad_smells.sma.normalization.rules.GestureBlockRule;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class LogNormalizer {
    private final List<GestureBlockRule> rules;

    public LogNormalizer(List<GestureBlockRule> rules) {
        this.rules = rules;
    }

    public List<NormalizedLogs> normalize(List<Logs> logs){
        List<NormalizedLogs> result = new ArrayList<>();
        if (logs == null || logs.isEmpty()) return result;

        List<Logs> sorted = new ArrayList<>(logs);
        sorted.sort(Comparator.comparing(Logs::getId));

        int i = 0;
        while (i < sorted.size()) {
            int jump = 1;
            for (GestureBlockRule rule : rules) {
                Optional<List<Logs>> bloco = rule.tryMatch(sorted, i);
                if (bloco.isPresent()) {
                    result.add(rule.reduce(bloco.get()));
                    jump = bloco.get().size();
                    break;
                }
            }
            i += jump;
        }
        return result;
    }
}