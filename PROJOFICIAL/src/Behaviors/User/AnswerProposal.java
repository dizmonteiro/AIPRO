package Behaviors.User;

import Agents.User;
import Extra.TravelPackage;
import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;

import java.io.IOException;

public class AnswerProposal extends OneShotBehaviour {

    /**
     * Variáveis
     */

    private User agentUser;
    private AID agentStation;
    private TravelPackage newPackage;

    /**
     * Construtores
     */

    public AnswerProposal(User agentUser, AID agentStation, TravelPackage newPackage) {

        this.agentUser = agentUser;
        this.agentStation = agentStation;
        this.newPackage = newPackage.clone();

    }

    /**
     * Action
     */

    public void action() {

        if(this.newPackage.equals(this.agentUser.getActualTPackage())) {

            ACLMessage message = new ACLMessage(ACLMessage.ACCEPT_PROPOSAL);
            message.addReceiver(this.agentStation);

            try {

                message.setContentObject(this.newPackage);

            } catch (IOException e) {

                e.printStackTrace();

            }

            this.agentUser.send(message);

        } else {

            /**
             * É para mudar
             */

            ACLMessage message = new ACLMessage(ACLMessage.ACCEPT_PROPOSAL);
            message.addReceiver(this.agentStation);

            try {

                message.setContentObject(this.newPackage);

            } catch (IOException e) {

                e.printStackTrace();

            }

            this.agentUser.send(message);

        }

    }

}