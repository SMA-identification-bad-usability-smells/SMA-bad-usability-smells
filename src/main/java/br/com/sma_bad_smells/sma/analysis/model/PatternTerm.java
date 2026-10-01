package br.com.sma_bad_smells.sma.analysis.model;

import br.com.sma_bad_smells.sma.domain.enums.GestureDirection;
import br.com.sma_bad_smells.sma.domain.enums.InteractionType;
import br.com.sma_bad_smells.sma.domain.models.NormalizedLogs;

public class PatternTerm {

    private final long minFrequency;          // pelo menos N
    private final InteractionType type;       // ex.: DRAG (o "Pan")
    private final GestureDirection direction; // ex.: DOWN; null = "qualquer direção"

    public PatternTerm(long minFrequency, InteractionType type, GestureDirection direction) {
        this.minFrequency = minFrequency;
        this.type = type;
        this.direction = direction;
    }

    // Verifica se o log bate com o termo do pattern (ex: [5]Drag(down))
    public boolean matches(NormalizedLogs log) {
        boolean tipoBate = log.getInteractionType() == type;
        boolean direcaoBate = (direction == null)              // null = aceita qualquer direção
                || log.getGestureDirection() == direction;
        boolean frequenciaBate = log.getFrequency() >= minFrequency; // a regra do "pelo menos"
        return tipoBate && direcaoBate && frequenciaBate;
    }
}
