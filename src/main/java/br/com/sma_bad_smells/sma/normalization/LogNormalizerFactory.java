package br.com.sma_bad_smells.sma.normalization;

import br.com.sma_bad_smells.sma.normalization.rules.GroupingRuleRegistry;
import br.com.sma_bad_smells.sma.normalization.rules.gestures.DragGroupingRule;
import br.com.sma_bad_smells.sma.normalization.rules.gestures.PressGroupingRule;

import java.util.List;

public class LogNormalizerFactory {

    private LogNormalizerFactory(){}

    public static LogNormalizer create(){
        GroupingRuleRegistry registry = new GroupingRuleRegistry(List.of(
                new PressGroupingRule(),
                new DragGroupingRule()
        ));

        return new LogNormalizer(registry);
    }
}
