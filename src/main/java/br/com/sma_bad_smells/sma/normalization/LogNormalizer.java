package br.com.sma_bad_smells.sma.normalization;

import br.com.sma_bad_smells.sma.domain.models.Logs;
import br.com.sma_bad_smells.sma.domain.models.NormalizedLogs;
import br.com.sma_bad_smells.sma.normalization.rules.GroupingRuleRegistry;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class LogNormalizer {
    private final GroupingRuleRegistry registry;

    public LogNormalizer(GroupingRuleRegistry registry) {
        this.registry = registry;
    }

    public List<NormalizedLogs> normalize(List<Logs> logs){
        List<NormalizedLogs> result = new ArrayList<>();

        if(logs == null || logs.isEmpty()) return result;

        List<Logs> sorted = new ArrayList<>(logs);
        sorted.sort(Comparator.comparing(Logs::getId));

        var i = 0;
        while(i < sorted.size()){
            Logs anchor = sorted.get(i);
            long frequency = 1;

            var j = i+1;
            while(
                    j < sorted.size() && sameGroup(sorted.get(j-1), sorted.get(j))
            ){
                frequency++;
                j++;
            }

            result.add(toNormalized(anchor, frequency));
        }
        return result;
    }

    private boolean sameGroup(Logs previous, Logs current){
        if(previous.getInteractionType() != current.getInteractionType()) return false;
        if(!ConsecutiveChecker.isConsecutive(previous, current)) return false;

        return registry.forType(current.getInteractionType())
                .map(rule -> rule.sameGesture(previous, current))
                .orElse(false);
    }

    private NormalizedLogs toNormalized(Logs anchor, long frequency){
        return new NormalizedLogs(
                anchor.getId(),
                anchor.getInteractionType(),
                frequency,
                anchor.getGestureDirection(),
                anchor.getTimestamp()
        );
    }
}
