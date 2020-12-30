package Behaviors.Manager;

import Agents.Manager;
import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import java.io.IOException;

/**
 * Behavior SendNearbyStations
 * 1. Recebe o AID do User
 * 2. Recebe o AID da Station
 * 3. Envia o AID do User para a Station
 */

public class SendNearbyUser extends OneShotBehaviour {

    /**
     * Variáveis
     */

    private Manager agentManager;
    private AID agentUser;
    private AID agentStation;
    private Boolean isUserTraveling;

    /**
     * Construtores
     */

    public SendNearbyUser(Manager agentManager, AID agentStation, AID agentUser, Boolean isUserTraveling) {

        this.setAgentManager(agentManager);
        this.setAgentStation(agentStation);
        this.setAgentUser(agentUser);
        this.setUserTraveling(isUserTraveling);

    }

    /**
     * Setters
     */

    public void setAgentManager(Manager agentManager) {

        this.agentManager = agentManager;

    }

    public void setAgentStation(AID agentStation) {

        this.agentStation = agentStation;

    }

    public void setAgentUser(AID agentUser) {

        this.agentUser = agentUser;

    }

    public void setUserTraveling(Boolean userTraveling) {
        this.isUserTraveling = userTraveling;
    }

    /**
     * Action
     */

    public void action() {

        ACLMessage message = new ACLMessage(ACLMessage.INFORM);
        message.addReceiver(this.agentStation);

        InfoPackage newPackage = new InfoPackage(this.isUserTraveling, this.agentUser);

        try {

            message.setContentObject(newPackage);

        } catch (IOException e) {

            e.printStackTrace();

        }

        this.agentManager.send(message);

    }

}