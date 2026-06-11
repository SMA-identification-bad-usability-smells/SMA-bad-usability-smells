package br.com.sma_bad_smells.sma.domain.enums;

public enum InteractionType {
    PRESS("press"),
    DRAG("drag");

    private String type;

    InteractionType(String type){
        this.type = type;
    }

    public String getType(){return this.type;}
}
