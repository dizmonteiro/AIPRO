package Behaviors.User;

import Agents.User;
import Extra.TravelPackage;
import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import java.io.IOException;

/**
 * BEHAVIOR STATUS: NOT DONE
 */

public class MakeBikeRequest extends OneShotBehaviour {

    /**
     * Variáveis
     */

    private User agentUser;
    private AID agentStation;
    private TravelPackage tp;

    /**
     * Construtores
     */

    public MakeBikeRequest(User agentUser, AID agentStation) {

        this.agentUser = agentUser;
        this.agentStation = agentStation;

    }

    /**
     * Action
     */

    public void action() {

        ACLMessage message = new ACLMessage(ACLMessage.REQUEST);
        message.addReceiver(this.agentStation);

        TravelPackage newTravel = this.agentUser.getActualTPackage();

        try {

            message.setContentObject(newTravel);

        } catch (IOException e) {

            e.printStackTrace();

        }

        this.agentUser.send(message);

    }

}
