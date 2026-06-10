package br.com.sma_bad_smells.sma.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDateTime;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LogDTO {
    private Long id;
    private float coordinatesX;
    private float coordinatesY;
    private GestureDirection direction;
    private InteractionType type;
    private String targetElementId;
    private LocalDateTime timestamp;

    public LogDTO(){}

    public LogDTO(
            Long id,
            float coordinatesX,
            float coordinatesY,
            GestureDirection direction,
            InteractionType type,
            String targetElementId,
            LocalDateTime timestamp) {
        this.id = id;
        this.coordinatesX = coordinatesX;
        this.coordinatesY = coordinatesY;
        this.direction = direction;
        this.type = type;
        this.targetElementId = targetElementId;
        this.timestamp = timestamp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public GestureDirection getDirection() {
        return direction;
    }

    public void setDirection(GestureDirection direction) {
        this.direction = direction;
    }

    public InteractionType getType() {
        return type;
    }

    public void setType(InteractionType type) {
        this.type = type;
    }

    public String getTargetElementId() {
        return targetElementId;
    }

    public void setTargetElementId(String targetElementId) {
        this.targetElementId = targetElementId;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        LogDTO logDTO = (LogDTO) o;
        return Float.compare(coordinatesX, logDTO.coordinatesX) == 0
                && Float.compare(coordinatesY, logDTO.coordinatesY) == 0
                && Objects.equals(id, logDTO.id)
                && direction == logDTO.direction
                && type == logDTO.type
                && Objects.equals(targetElementId, logDTO.targetElementId)
                && Objects.equals(timestamp, logDTO.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, coordinatesX, coordinatesY, direction, type, targetElementId, timestamp);
    }

    @Override
    public String toString() {
        return "LogDTO{" +
                "id=" + id +
                ", coordinatesX=" + coordinatesX +
                ", coordinatesY=" + coordinatesY +
                ", direction=" + direction +
                ", type=" + type +
                ", targetElementId='" + targetElementId + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
