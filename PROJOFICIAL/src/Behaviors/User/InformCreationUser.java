package Behaviors.User;

import Agents.User;
import Extra.InfoPackageFromUserToManager;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import java.io.IOException;

/**
 * BEHAVIOR STATUS: DONE
 */

public class InformCreationUser extends OneShotBehaviour {

    /**
     * Variáveis
     */

    private User agentUser;

    /**
     * Construtor
     */

    public InformCreationUser(User agentUser) {

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

        //1. Criamos o pacote InfoPackageFromUserToManager que vai conter a informação se o User está a viajar, a posição atual do User e o travelPackage atual do User
        InfoPackageFromUserToManager newPackage = new InfoPackageFromUserToManager(this.agentUser.isTraveling(), this.agentUser.getActualPosition(), this.agentUser.getActualTPackage().clone());

        //2. Criamos a mensagem com performative INFORM
        ACLMessage message = new ACLMessage(ACLMessage.INFORM);

        //3. Introduzimos o manager como receptor da mensagem
        message.addReceiver(this.agentUser.getAgentManager());

        try {

            //4. Colocamos o pacote InfoPackageFromUserToManager na mensagem
            message.setContentObject(newPackage.clone());

        } catch (IOException e) {

            e.printStackTrace();

        }

        //Mensagem
        System.out.println("> User AID: " + this.agentUser.getAID() + " has sent InfoPackageFromUserToManager to Manager");

        //5. Enviamos a mensagem para o Manager
        this.agentUser.send(message);

    }
}

