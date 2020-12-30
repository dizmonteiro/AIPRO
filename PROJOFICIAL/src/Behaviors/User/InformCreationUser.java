package Behaviors.User;

import Agents.User;
import Extra.InfoPackageFromUserToManager;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import java.io.IOException;

/**
 * BEHAVIOR STATUS: NOT DONE
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

        this.agentUser = agentUser;

    }

    /**
     * Active
     */

    public void action() {

        InfoPackageFromUserToManager newPackage = new InfoPackageFromUserToManager(this.agentUser.isTraveling(), this.agentUser.getActualPosition(), this.agentUser.getActualTPackage());

        ACLMessage message = new ACLMessage(ACLMessage.INFORM);
        message.addReceiver(this.agentUser.getAgentManager());

        try {

            message.setContentObject(newPackage.clone());

        } catch (IOException e) {

            e.printStackTrace();

        }

        this.agentUser.send(message);

    }
}

