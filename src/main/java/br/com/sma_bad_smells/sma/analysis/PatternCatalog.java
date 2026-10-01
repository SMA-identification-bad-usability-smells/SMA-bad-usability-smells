package br.com.sma_bad_smells.sma.analysis;

import br.com.sma_bad_smells.sma.analysis.model.GesturePattern;
import br.com.sma_bad_smells.sma.analysis.model.PatternTerm;
import br.com.sma_bad_smells.sma.analysis.model.SmellType;
import br.com.sma_bad_smells.sma.domain.enums.GestureDirection;
import br.com.sma_bad_smells.sma.domain.enums.InteractionType;

import java.util.List;

public class PatternCatalog {
    public static List<GesturePattern> all() {
        return List.of(
                new GesturePattern(SmellType
                        .DISTANT_CONTENT, List.of(
                        new PatternTerm(5, InteractionType.DRAG, GestureDirection.DOWN)
                ))
        );
    }
}
