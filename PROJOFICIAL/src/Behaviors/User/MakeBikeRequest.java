package Behaviors.User;

import Agents.User;
import Extra.TravelPackage;
import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import java.io.IOException;

/**
 * BEHAVIOR STATUS: DONE
 */

public class MakeBikeRequest extends OneShotBehaviour {

    /**
     * Variáveis
     */

    private User agentUser;
    private AID agentStation;

    /**
     * Construtores
     */

    public MakeBikeRequest(User agentUser, AID agentStation) {

        this.setAgentUser(agentUser);
        this.setAgentStation(agentStation);

    }

    /**
     * Setters
     */

    public void setAgentUser(User agentUser) {

        this.agentUser = agentUser;

    }

    public void setAgentStation(AID agentStation) {

        this.agentStation = agentStation;

    }

    /**
     * Action
     */

    public void action() {

        //1. Criamos a mensagem com performative PROPOSE
        ACLMessage message = new ACLMessage(ACLMessage.PROPOSE);

        //2. Introduzimos a Station como receptor da mensagem
        message.addReceiver(this.agentStation);

        try {

            //3. Introduzimos uma cópia do travelPackage atual na mensagem
            message.setContentObject(this.agentUser.getActualTPackage().clone());

        } catch (IOException e) {

            e.printStackTrace();

        }

        //Mensagem
        System.out.println("> User AID: " + this.agentUser.getAID() + " has sent TravelPackage to Station");

        //4. Enviamos a mensagem para a Station
        this.agentUser.send(message);

    }

}
