package Behaviors.User;

import Agents.User;
import Extra.InfoPackageFromUser;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import java.io.IOException;

/**
 * BEHAVIOR STATUS: DONE
 */

public class InformTurnOff extends OneShotBehaviour {

    /**
     * Variáveis
     */

    private User agentUser;

    /**
     * Construtor
     */

    public InformTurnOff(User agentUser) {

        this.setAgentUser(agentUser);

    }

    /**
     * Setter
     */

    public void setAgentUser(User agentUser) {

        this.agentUser = agentUser;

    }

    /**
     * Active
     */

    public void action() {

        //2. Criamos a mensagem com performative INFORM
        ACLMessage message = new ACLMessage(ACLMessage.CANCEL);

        //3. Introduzimos o manager como receptor da mensagem
        message.addReceiver(this.agentUser.getAgentManager());

        try {

            //4. Colocamos o pacote InfoPackageFromUserToManager na mensagem
            message.setContentObject(this.agentUser.getAID());

        } catch (IOException e) {

            e.printStackTrace();

        }

        //Mensagem
        System.out.println("> User AID: " + this.agentUser.getAID() + " has sent Turn Off Notice to Manager");

        //5. Enviamos a mensagem para o Manager
        this.agentUser.send(message);

        //Mensagem
        System.out.println("> User AID: " + this.agentUser.getAID() + " has stopped receiving Messages");

        this.agentUser.blockingReceive();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Mensagem
        System.out.println("> User AID: " + this.agentUser.getAID() + " has turned OFF");

        this.agentUser.turnOff();

    }
}

