package Behaviors.Manager;

import Agents.Manager;
import Extra.InfoPackageFromUser;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import java.io.IOException;

/**
 * BEHAVIOR STATUS: DONE
 */

public class SendUserPackage extends OneShotBehaviour {

    /**
     * Variáveis
     */

    private Manager agentManager;
    private InfoPackageFromUser userPackage;

    /**
     * Construtor
     */

    public SendUserPackage(Manager agentManager, InfoPackageFromUser newUserPos) {

        this.setAgentManager(agentManager);
        this.setUserPackage(newUserPos);

    }

    /**
     * Setter
     */

    public void setAgentManager(Manager agentManager) {

        this.agentManager = agentManager;

    }

    public void setUserPackage(InfoPackageFromUser newUserPos) {

        this.userPackage = newUserPos.clone();

    }

    /**
     * Active
     */

    public void action() {

        //1. Criamos a mensagem com performative INFORM_IF
        ACLMessage message = new ACLMessage(ACLMessage.INFORM_REF);

        //2. Introduzimos o manager como receptor da mensagem
        message.addReceiver(this.agentManager.getAgentInterface());

        try {

            //3. Colocamos a Position na mensagem
            message.setContentObject(this.userPackage.clone());

        } catch (IOException e) {

            e.printStackTrace();

        }

        //Mensagem
        System.out.println("> Manager AID: " + this.agentManager.getAID() + " has sent new User Position to INTERFACE");

        //5. Enviamos a mensagem para o Manager
        this.agentManager.send(message);

    }
}
