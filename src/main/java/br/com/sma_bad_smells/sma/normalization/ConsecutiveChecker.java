package br.com.sma_bad_smells.sma.normalization;

import br.com.sma_bad_smells.sma.domain.models.Logs;

import java.time.Duration;

public final class ConsecutiveChecker {
    private static final long MAX_SECONDS = 5;

    private ConsecutiveChecker(){}

    public static boolean isConsecutive(Logs previous, Logs current){
        boolean idsConsecutive = current.getId() - previous.getId() == 1;
        long seconds = Math.abs(
                Duration.between(previous.getTimestamp(), current.getTimestamp()).getSeconds()
        );
        return idsConsecutive || seconds <= MAX_SECONDS;
    }
}
