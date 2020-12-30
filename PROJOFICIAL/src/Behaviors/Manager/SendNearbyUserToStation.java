package Behaviors.Manager;

import Agents.Manager;
import Extra.TravelPackage;
import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import java.io.IOException;
import java.io.Serializable;

/**
 * Behavior SendNearbyStations
 * 1. Recebe o AID do User
 * 2. Recebe o AID da Station
 * 3. Envia o AID do User para a Station
 */

public class SendNearbyUserToStation extends OneShotBehaviour {

    /**
     * Variáveis
     */

    private Manager agentManager;
    private TravelPackage tp;
    private AID agentStation;

    /**
     * Construtores
     */

    public SendNearbyUserToStation(Manager agentManager, AID agentStation, TravelPackage tp) {

        this.setAgentManager(agentManager);
        this.setAgentStation(agentStation);
        this.setTravelPackage(tp);

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

    public void setTravelPackage(TravelPackage tp) {

        this.tp = tp.clone();

    }

    /**
     * Action
     */

    public void action() {

        ACLMessage message = new ACLMessage(ACLMessage.INFORM);
        message.addReceiver(this.agentStation);



        try {

            message.setContentObject(this.tp);

        } catch (IOException e) {

            e.printStackTrace();

        }

        this.agentManager.send(message);

    }

}