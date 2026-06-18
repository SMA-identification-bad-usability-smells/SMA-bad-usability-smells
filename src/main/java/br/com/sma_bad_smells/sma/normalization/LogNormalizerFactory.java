package br.com.sma_bad_smells.sma.normalization;

import br.com.sma_bad_smells.sma.normalization.rules.GestureBlockRule;
import br.com.sma_bad_smells.sma.normalization.rules.gestures.DragBlockRule;
import br.com.sma_bad_smells.sma.normalization.rules.gestures.PressBlockRule;

import java.util.List;

public class LogNormalizerFactory {

    private LogNormalizerFactory(){}

    public static LogNormalizer create(){
        List<GestureBlockRule> rules = List.of(
                new DragBlockRule(),   // prioridade maior
                new PressBlockRule()
        );
        return new LogNormalizer(rules);
    }
}