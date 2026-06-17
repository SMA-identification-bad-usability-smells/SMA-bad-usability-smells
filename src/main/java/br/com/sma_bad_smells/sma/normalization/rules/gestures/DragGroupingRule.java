package br.com.sma_bad_smells.sma.normalization.rules.gestures;

import br.com.sma_bad_smells.sma.domain.enums.InteractionType;
import br.com.sma_bad_smells.sma.domain.models.Logs;
import br.com.sma_bad_smells.sma.normalization.rules.GestureGroupingRule;

public class DragGroupingRule implements GestureGroupingRule {
    @Override
    public InteractionType appliesTo() {
        return InteractionType.DRAG;
    }

    @Override
    public boolean sameGesture(Logs previous, Logs current) {
        return previous.getGestureDirection() == current.getGestureDirection();
    }
}
