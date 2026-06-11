package br.com.sma_bad_smells.sma.domain.enums;

public enum GestureDirection {
    UP("up"),
    DOWN("down");

    private String direction;

    GestureDirection(String direction){
        this.direction = direction;
    }

    public String getDirection(){return this.direction;}
}
