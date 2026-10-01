package br.com.sma_bad_smells.sma.analysis.model;

import java.util.List;

public class GesturePattern {
    private final SmellType type;
    private final List<PatternTerm> terms;

    public GesturePattern(SmellType type, List<PatternTerm> terms) {
        this.type = type;
        this.terms = terms;
    }

    public SmellType getType() { return type; }
    public List<PatternTerm> getTerms() { return terms; }
}
