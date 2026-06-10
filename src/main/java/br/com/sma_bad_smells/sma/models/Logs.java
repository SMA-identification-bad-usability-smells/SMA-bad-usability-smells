package br.com.sma_bad_smells.sma.models;

import java.time.LocalDateTime;
import java.util.Objects;

public class Logs {
    private Long id;
    private InteractionType interactionType;
    private LocalDateTime timestamp;
    private float coordinatesX;
    private float coordinatesY;
    private GestureDirection gestureDirection;
    private String targetElementId;

    public Logs(
            Long id,
            InteractionType interactionType,
            LocalDateTime timestamp,
            float coordinatesX,
            float coordinatesY,
            GestureDirection gestureDirection,
            String targetElementId) {
        this.id = id;
        this.interactionType = interactionType;
        this.timestamp = timestamp;
        this.coordinatesX = coordinatesX;
        this.coordinatesY = coordinatesY;
        this.gestureDirection = gestureDirection;
        this.targetElementId = targetElementId;
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

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public float getCoordinatesX() {
        return coordinatesX;
    }

    public void setCoordinatesX(float coordinatesX) {
        this.coordinatesX = coordinatesX;
    }

    public float getCoordinatesY() {
        return coordinatesY;
    }

    public void setCoordinatesY(float coordinatesY) {
        this.coordinatesY = coordinatesY;
    }

    public GestureDirection getGestureDirection() {
        return gestureDirection;
    }

    public void setGestureDirection(GestureDirection gestureDirection) {
        this.gestureDirection = gestureDirection;
    }

    public String getTargetElementId() {
        return targetElementId;
    }

    public void setTargetElementId(String targetElementId) {
        this.targetElementId = targetElementId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Logs logs = (Logs) o;
        return Float.compare(coordinatesX, logs.coordinatesX) == 0
                && Float.compare(coordinatesY, logs.coordinatesY) == 0
                && Objects.equals(id, logs.id)
                && interactionType == logs.interactionType
                && Objects.equals(timestamp, logs.timestamp)
                && gestureDirection == logs.gestureDirection
                && Objects.equals(targetElementId, logs.targetElementId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                id,
                interactionType,
                timestamp,
                coordinatesX,
                coordinatesY,
                gestureDirection,
                targetElementId);
    }

    @Override
    public String toString() {
        return "Logs{" +
                "id=" + id +
                ", interactionType=" + interactionType +
                ", timestamp=" + timestamp +
                ", coordinatesX=" + coordinatesX +
                ", coordinatesY=" + coordinatesY +
                ", gestureDirection=" + gestureDirection +
                ", targetElementId='" + targetElementId + '\'' +
                '}';
    }
}
