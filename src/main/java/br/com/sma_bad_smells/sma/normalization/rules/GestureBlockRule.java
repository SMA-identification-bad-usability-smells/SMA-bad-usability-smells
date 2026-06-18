package br.com.sma_bad_smells.sma.normalization.rules;

import br.com.sma_bad_smells.sma.domain.models.Logs;
import br.com.sma_bad_smells.sma.domain.models.NormalizedLogs;
import java.util.List;
import java.util.Optional;


public interface GestureBlockRule {
    Optional<List<Logs>> tryMatch(List<Logs> logs, int begin);
    NormalizedLogs reduce(List<Logs> block);
}