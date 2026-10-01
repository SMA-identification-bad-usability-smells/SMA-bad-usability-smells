package br.com.sma_bad_smells.sma;

import br.com.sma_bad_smells.sma.analysis.model.PatternTerm;
import br.com.sma_bad_smells.sma.domain.enums.GestureDirection;
import br.com.sma_bad_smells.sma.domain.enums.InteractionType;
import br.com.sma_bad_smells.sma.domain.models.NormalizedLogs;

import java.time.LocalDateTime;

public class Testes {
    public static void main(String[] args) {
        PatternTerm termo = new PatternTerm(5, InteractionType.DRAG, GestureDirection.DOWN);

// casa: DRAG, DOWN, freq 5 (exatamente no limite do "pelo menos")
        NormalizedLogs a = new NormalizedLogs(1L, InteractionType.DRAG, 5L, GestureDirection.DOWN, LocalDateTime.now());
// casa: freq acima do mínimo
        NormalizedLogs b = new NormalizedLogs(2L, InteractionType.DRAG, 10L, GestureDirection.DOWN, LocalDateTime.now());
// NÃO casa: freq abaixo
        NormalizedLogs c = new NormalizedLogs(3L, InteractionType.DRAG, 4L, GestureDirection.DOWN, LocalDateTime.now());
// NÃO casa: direção errada
        NormalizedLogs d = new NormalizedLogs(4L, InteractionType.DRAG, 9L, GestureDirection.UP, LocalDateTime.now());
// NÃO casa: tipo errado
        NormalizedLogs e = new NormalizedLogs(5L, InteractionType.PRESS, 9L, GestureDirection.DOWN, LocalDateTime.now());

        System.out.println(termo.matches(a)); // true
        System.out.println(termo.matches(b)); // true
        System.out.println(termo.matches(c)); // false
        System.out.println(termo.matches(d)); // false
        System.out.println(termo.matches(e)); // false
    }
}
