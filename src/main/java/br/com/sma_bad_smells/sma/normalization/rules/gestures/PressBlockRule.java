package br.com.sma_bad_smells.sma.normalization.rules.gestures;

import br.com.sma_bad_smells.sma.domain.enums.InteractionType;
import br.com.sma_bad_smells.sma.domain.models.Logs;
import br.com.sma_bad_smells.sma.domain.models.NormalizedLogs;
import br.com.sma_bad_smells.sma.normalization.ConsecutiveChecker;
import br.com.sma_bad_smells.sma.normalization.rules.GestureBlockRule;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class PressBlockRule implements GestureBlockRule {
    private static final float EPSILON = 0.0001f;

    @Override
    public Optional<List<Logs>> tryMatch(List<Logs> logs, int begin) {
        if (logs.get(begin).getInteractionType() != InteractionType.PRESS) {
            return Optional.empty();
        }

        List<Logs> bloco = new ArrayList<>();
        bloco.add(logs.get(begin));

        int j = begin + 1;
        while (j < logs.size()
                && logs.get(j).getInteractionType() == InteractionType.PRESS
                && ConsecutiveChecker.isConsecutive(logs.get(j - 1), logs.get(j))
                && (sameElement(logs.get(j - 1), logs.get(j))
                || sameCoordinates(logs.get(j - 1), logs.get(j)))) {
            bloco.add(logs.get(j));
            j++;
        }
        return Optional.of(bloco);
    }

    @Override
    public NormalizedLogs reduce(List<Logs> block) {
        Logs anchor = block.get(0);
        return new NormalizedLogs(
                anchor.getId(),
                InteractionType.PRESS,
                (long) block.size(),
                anchor.getGestureDirection(),
                anchor.getTimestamp()
        );
    }

    private boolean sameElement(Logs a, Logs b) {
        return Objects.equals(a.getTargetElementId(), b.getTargetElementId());
    }

    private boolean sameCoordinates(Logs a, Logs b) {
        return Math.abs(a.getCoordinatesX() - b.getCoordinatesX()) < EPSILON
                && Math.abs(a.getCoordinatesY() - b.getCoordinatesY()) < EPSILON;
    }
}