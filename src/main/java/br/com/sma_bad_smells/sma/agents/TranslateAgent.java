package br.com.sma_bad_smells.sma.agents;

import jade.core.Agent;

public class TranslateAgent extends Agent {
    @Override
    protected void setup(){
        System.out.println("Olá, sou o agente tradutor!" + getLocalName());

        doDelete();
    }

    @Override
    protected void takeDown(){
        System.out.println("Agente " + getLocalName() + " sendo finalizado...");
    }
}
