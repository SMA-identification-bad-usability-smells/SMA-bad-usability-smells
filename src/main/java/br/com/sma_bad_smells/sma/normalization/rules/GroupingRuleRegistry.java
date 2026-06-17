package br.com.sma_bad_smells.sma.normalization.rules;

import br.com.sma_bad_smells.sma.domain.enums.InteractionType;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class GroupingRuleRegistry {
    private final Map<InteractionType, GestureGroupingRule> rules =
            new EnumMap<>(InteractionType.class);

    public GroupingRuleRegistry(List<GestureGroupingRule> list) {
        list.forEach(rule -> rules.put(rule.appliesTo(), rule));
    }

    public Optional<GestureGroupingRule> forType(InteractionType type) {
        return Optional.ofNullable(rules.get(type));
    }
}
