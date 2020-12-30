package Behaviors.User;

import Agents.Station;
import Agents.User;
import Extra.InfoPackageFromUserToManager;
import Util.DFFunctions;
import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;

import java.io.IOException;

public class InformCreationUser extends OneShotBehaviour {

    /**
     * Variáveis
     */

    private User agentUser;
    private AID agentManager;

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

        this.agentManager = DFFunctions.findSpecificAgent(this.agentUser,"Agent Manager");

        InfoPackageFromUserToManager newPackage = new InfoPackageFromUserToManager(this.agentUser.isTraveling(), this.agentUser.getActualTPackage(), this.agentUser.getActualPosition());

        ACLMessage message = new ACLMessage(ACLMessage.INFORM);
        message.addReceiver(this.agentManager);

        try {

            message.setContentObject(newPackage.clone());

        } catch (IOException e) {

            e.printStackTrace();

        }

        this.agentUser.send(message);

    }
}

