package Behaviors.Manager;

import Agents.Manager;
import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import java.io.IOException;

/**
 * BEHAVIOR STATUS: DONE
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

        //1. Criamos mensagem com Performative INFORM
        ACLMessage message = new ACLMessage(ACLMessage.INFORM);

        //2. Pomos o User como destinatario da mensagem
        message.addReceiver(this.agentUser);

        try {

            //3. Pomos o AID da Station em que o User foi gerado na mensagem
            message.setContentObject(this.agentStation);

        } catch (IOException e) {

            e.printStackTrace();

        }

        //Mensagem
        System.out.println("> Manager AID: " + this.agentManager.getAID() + " has sent TravelPackage from Nearby User to Station");

        //4. Enviamos a mensagem para o User
        this.agentManager.send(message);

    }

}