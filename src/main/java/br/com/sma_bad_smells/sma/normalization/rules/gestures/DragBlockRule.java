package br.com.sma_bad_smells.sma.normalization.rules.gestures;

import br.com.sma_bad_smells.sma.domain.enums.GestureDirection;
import br.com.sma_bad_smells.sma.domain.enums.InteractionType;
import br.com.sma_bad_smells.sma.domain.models.Logs;
import br.com.sma_bad_smells.sma.domain.models.NormalizedLogs;
import br.com.sma_bad_smells.sma.normalization.ConsecutiveChecker;
import br.com.sma_bad_smells.sma.normalization.rules.GestureBlockRule;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DragBlockRule implements GestureBlockRule {

    @Override
    public Optional<List<Logs>> tryMatch(List<Logs> logs, int begin) {
        GestureDirection direction = initialDragDirection(logs, begin);
        if (direction == null) {
            return Optional.empty();
        }

        List<Logs> block = new ArrayList<>();
        block.add(logs.get(begin));

        int j = begin + 1;
        while (j < logs.size()
                && ConsecutiveChecker.isConsecutive(logs.get(j - 1), logs.get(j))
                && toNextDrag(logs, j, direction)) {
            block.add(logs.get(j));
            j++;
        }
        return Optional.of(block);
    }

    private GestureDirection initialDragDirection(List<Logs> logs, int begin) {
        Logs atual = logs.get(begin);
        if (atual.getInteractionType() == InteractionType.DRAG) {
            return atual.getGestureDirection();
        }
        if (atual.getInteractionType() == InteractionType.PRESS
                && begin + 1 < logs.size()
                && logs.get(begin + 1).getInteractionType() == InteractionType.DRAG
                && ConsecutiveChecker.isConsecutive(atual, logs.get(begin + 1))) {
            return logs.get(begin + 1).getGestureDirection();
        }
        return null;
    }

    private boolean toNextDrag(List<Logs> logs, int j, GestureDirection direction) {
        Logs log = logs.get(j);

        if (log.getInteractionType() == InteractionType.DRAG) {
            return log.getGestureDirection() == direction;
        }

        return log.getInteractionType() == InteractionType.PRESS
                && j + 1 < logs.size()
                && logs.get(j + 1).getInteractionType() == InteractionType.DRAG
                && ConsecutiveChecker.isConsecutive(log, logs.get(j + 1))
                && logs.get(j + 1).getGestureDirection() == direction;
    }

    @Override
    public NormalizedLogs reduce(List<Logs> block) {
        long frequency = calculateFrequency(block);
        Logs ref = block.get(0);
        GestureDirection gestureDirection = getDirection(block, ref);
        return new NormalizedLogs(
                ref.getId(),
                InteractionType.DRAG,
                frequency,
                gestureDirection,
                ref.getTimestamp()
        );
    }

    private long calculateFrequency(List<Logs> block){
        return block.stream()
                .filter(l -> l.getInteractionType() == InteractionType.DRAG)
                .count();
    }

    private GestureDirection getDirection(List<Logs> block, Logs ref){
        Logs primeiroDrag = block.stream()
                .filter(l -> l.getInteractionType() == InteractionType.DRAG)
                .findFirst().orElse(ref);
        return  primeiroDrag.getGestureDirection();
    }
}