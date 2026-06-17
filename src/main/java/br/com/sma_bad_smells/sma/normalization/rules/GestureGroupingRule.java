package br.com.sma_bad_smells.sma.normalization.rules;

import br.com.sma_bad_smells.sma.domain.enums.InteractionType;
import br.com.sma_bad_smells.sma.domain.models.Logs;

public interface GestureGroupingRule {
    InteractionType appliesTo();

    boolean sameGesture(Logs previous, Logs current);
}
