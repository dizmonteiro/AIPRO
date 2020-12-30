package Behaviors.User;

import Agents.User;
import Extra.InfoPackageFromUserToManager;
import Util.DFFunctions;
import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import java.io.IOException;

/**
 * Behavior InformCreationUser
 * Este método será utilizado pelo Agente User quando for criado
 * 1. Cria um InfoPackageFromUserToManager
 * 2. Envia esse InfoPackage para o Manager
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

        InfoPackageFromUserToManager newPackage = new InfoPackageFromUserToManager(this.agentUser.isTraveling(), this.agentUser.getActualTPackage(), this.agentUser.getActualPosition());

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

