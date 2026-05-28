package br.com.sma_bad_smells.sma;

import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;


public class MainApplication {

    public static void main(String[] args) {
        Runtime runtime = Runtime.instance();

        Profile profile = new ProfileImpl();
        
        profile.setParameter(Profile.MAIN_HOST, "localhost");
        profile.setParameter(Profile.MAIN_PORT, "1099");
        
        profile.setParameter(Profile.GUI, "true");

        try {
            System.out.println("=== Inicializando a Plataforma Multiagente JADE ===");
            
            AgentContainer mainContainer = runtime.createMainContainer(profile);
            System.out.println("Main Container criado com sucesso.");

            // 4. Exemplo de inicialização dinâmica de agentes (Opcional)
            String nomeAgente = "TranslateAgent";
            String caminhoClasse = "br.com.sma_bad_smells.sma.agents.TranslateAgent";
            Object[] argumentosDoAgente = new Object[] {};

            System.out.println("Criando o agente: " + nomeAgente);
            AgentController agentController = mainContainer.createNewAgent(
                nomeAgente, 
                caminhoClasse, 
                argumentosDoAgente
            );
            
            // Inicia o ciclo de vida do agente (dispara o método setup() interno)
            agentController.start();

            System.out.println("=== Sistema pronto e monitorado via GUI ===");

        } catch (RuntimeException e) {
            System.err.println("Falha crítica ao inicializar o container ou agente do JADE.");
            e.printStackTrace();
        } catch (StaleProxyException e) {
            throw new RuntimeException(e);
        }
    }
}