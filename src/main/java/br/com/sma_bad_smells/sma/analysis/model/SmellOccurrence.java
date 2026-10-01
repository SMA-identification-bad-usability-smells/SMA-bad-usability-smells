package br.com.sma_bad_smells.sma.analysis.model;

import java.util.List;

// Representa o resultado da análise do blco de logs
// Resultado da função 'findOccurrences'
public class SmellOccurrence {
    private final SmellType type;
    private final List<Long> normalizedLogIds; // ids dos logs que formaram a ocorrência

    public SmellOccurrence(SmellType type, List<Long> normalizedLogIds) {
        this.type = type;
        this.normalizedLogIds = normalizedLogIds;
    }

    public SmellType getType() { return type; }
    public List<Long> getNormalizedLogIds() { return normalizedLogIds; }
}
