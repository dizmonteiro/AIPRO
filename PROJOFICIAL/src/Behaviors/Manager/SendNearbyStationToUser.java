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
 * 3. Envia o AID da Station para o User
 */

public class SendNearbyStationToUser extends OneShotBehaviour {

    /**
     * Variáveis
     */

    private Manager agentManager;
    private AID agentUser;
    private AID agentStation;

    /**
     * Construtores
     */

    public SendNearbyStationToUser(Manager agentManager, AID agentUser, AID agentStation) {

        this.setAgentManager(agentManager);
        this.setAgentStation(agentStation);
        this.setAgentUser(agentUser);

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

    /**
     * Action
     */

    public void action() {

        ACLMessage message = new ACLMessage(ACLMessage.INFORM);
        message.addReceiver(this.agentUser);

        try {

            message.setContentObject(this.agentStation);

        } catch (IOException e) {

            e.printStackTrace();

        }

        this.agentManager.send(message);

    }

}