package br.com.sma_bad_smells.sma.models;

import java.util.Objects;

public class NormalizedLogs {
    private Long id;
    private InteractionType interactionType;
    private Long frequency;
    private GestureDirection gestureDirection;

    public NormalizedLogs(
            Long id,
            InteractionType interactionType,
            Long frequency,
            GestureDirection gestureDirection) {
        this.id = id;
        this.interactionType = interactionType;
        this.frequency = frequency;
        this.gestureDirection = gestureDirection;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public InteractionType getInteractionType() {
        return interactionType;
    }

    public void setInteractionType(InteractionType interactionType) {
        this.interactionType = interactionType;
    }

    public Long getFrequency() {
        return frequency;
    }

    public void setFrequency(Long frequency) {
        this.frequency = frequency;
    }

    public GestureDirection getGestureDirection() {
        return gestureDirection;
    }

    public void setGestureDirection(GestureDirection gestureDirection) {
        this.gestureDirection = gestureDirection;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        NormalizedLogs that = (NormalizedLogs) o;
        return Objects.equals(id, that.id)
                && interactionType == that.interactionType
                && Objects.equals(frequency, that.frequency)
                && gestureDirection == that.gestureDirection;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, interactionType, frequency, gestureDirection);
    }

    @Override
    public String toString() {
        return "NormalizedLogs{" +
                "id=" + id +
                ", interactionType=" + interactionType +
                ", frequency=" + frequency +
                ", gestureDirection=" + gestureDirection +
                '}';
    }
}
