package br.com.sma_bad_smells.sma.analysis;

import br.com.sma_bad_smells.sma.analysis.model.GesturePattern;
import br.com.sma_bad_smells.sma.analysis.model.PatternTerm;
import br.com.sma_bad_smells.sma.analysis.model.SmellType;
import br.com.sma_bad_smells.sma.domain.enums.GestureDirection;
import br.com.sma_bad_smells.sma.domain.enums.InteractionType;
import br.com.sma_bad_smells.sma.domain.models.NormalizedLogs;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.List;

class PatternMatcherTest {

    private final PatternMatcher matcher = new PatternMatcher();
    private final GesturePattern distantContent = new GesturePattern(
            SmellType.DISTANT_CONTENT,
            List.of(new PatternTerm(5, InteractionType.DRAG, GestureDirection.DOWN))
    );

    // helper para encurtar
    private NormalizedLogs drag(long id, long freq, GestureDirection dir) {
        return new NormalizedLogs(id, InteractionType.DRAG, freq, dir, LocalDateTime.now());
    }

    @Test
    void naoSomaFrequenciaEntreVizinhos() {
        // [1]drag(down), [10]drag(down) -> só o segundo casa -> 1 ocorrência
        var bloco = List.of(drag(1, 1, GestureDirection.DOWN), drag(2, 10, GestureDirection.DOWN));
        var r = matcher.findOccurrences(distantContent, bloco);
        assertEquals(1, r.size());
        assertEquals(List.of(2L), r.get(0).getNormalizedLogIds()); // é o log id 2
    }

    @Test
    void doisTrechosSeparadosPorRuidoSaoDuasOcorrencias() {
        // [6]drag(down), [3]drag(UP) (ruído), [5]drag(down) -> 2 ocorrências
        var bloco = List.of(drag(1, 6, GestureDirection.DOWN), drag(2, 3, GestureDirection.UP), drag(3, 5, GestureDirection.DOWN));
        var r = matcher.findOccurrences(distantContent, bloco);
        assertEquals(2, r.size());
    }

    @Test
    void blocoSemNenhumMatchRetornaVazio() {
        var bloco = List.of(drag(1, 2, GestureDirection.DOWN), drag(2, 4, GestureDirection.DOWN)); // ambos < 5
        assertTrue(matcher.findOccurrences(distantContent, bloco).isEmpty());
    }
}