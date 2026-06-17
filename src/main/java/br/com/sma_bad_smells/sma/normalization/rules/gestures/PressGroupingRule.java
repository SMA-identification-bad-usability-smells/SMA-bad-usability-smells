package br.com.sma_bad_smells.sma.normalization.rules.gestures;

import br.com.sma_bad_smells.sma.domain.enums.InteractionType;
import br.com.sma_bad_smells.sma.domain.models.Logs;
import br.com.sma_bad_smells.sma.normalization.rules.GestureGroupingRule;

import java.util.Objects;

public class PressGroupingRule implements GestureGroupingRule {
    private static final float EPSILON = 0.0001f;

    @Override
    public InteractionType appliesTo() {
        return InteractionType.PRESS;
    }

    @Override
    public boolean sameGesture(Logs previous, Logs current) {
        return sameElement(previous, current) || sameCoordinates(previous, current);
    }

    private boolean sameElement(Logs a, Logs b){
        return Objects.equals(a.getTargetElementId(), b.getTargetElementId());
    }

    private boolean sameCoordinates(Logs a, Logs b) {
        return Math.abs(a.getCoordinatesX() - b.getCoordinatesX()) < EPSILON
                && Math.abs(a.getCoordinatesY() - b.getCoordinatesY()) < EPSILON;
    }
}
