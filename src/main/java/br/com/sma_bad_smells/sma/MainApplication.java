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
        profile.setParameter(Profile.GUI, "true"); //abre a interface gráfica

        AgentContainer mainContainer = runtime.createMainContainer(profile);

        try {
            System.out.println("Main Container criado com sucesso.");

            System.out.println("Criando agente...");
            AgentController dataAgentController = mainContainer.createNewAgent(
                "dataAgent",
                "br.com.sma_bad_smells.sma.agents.DataAgent",
                new Object[]{}
            );
            dataAgentController.start();

            AgentController translateAgentController = mainContainer.createNewAgent(
                    "translateAgent",
                    "br.com.sma_bad_smells.sma.agents.TranslateAgent",
                    new Object[]{}
            );
            translateAgentController.start();

        } catch (RuntimeException e) {
            System.err.println("Falha ao inicializar.");
            e.printStackTrace();
        } catch (StaleProxyException e) {
            throw new RuntimeException(e);
        }
    }
}