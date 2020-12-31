package Behaviors.Manager;

import Agents.Manager;
import Extra.TravelPackage;
import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import java.io.IOException;

/**
 * BEHAVIOR STATUS: DONE
 */

public class SendNearbyUserToStation extends OneShotBehaviour {

    /**
     * Variáveis
     */

    private Manager agentManager;
    private AID agentStation;
    private TravelPackage newTravelPackage;

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

    public void setTravelPackage(TravelPackage nTP) {

        this.newTravelPackage = nTP.clone();

    }

    /**
     * Action
     */

    public void action() {

        //1. Criamos a mensagem com Performative INFORM
        ACLMessage message = new ACLMessage(ACLMessage.INFORM);

        //2. Colocamos o AID da Station como recetor
        message.addReceiver(this.agentStation);

        try {

            //3. Introduzimos o TravelPackage na mensagem
            message.setContentObject(this.newTravelPackage);

        } catch (IOException e) {

            e.printStackTrace();

        }

        //Mensagem
        System.out.println("> Manager AID: " + this.agentManager.getAID() + " has sent AID from Nearby Station to User");

        //4. Enviamos a mensagem para a Station
        this.agentManager.send(message);

    }

}