package Behaviors.User;

import Agents.User;
import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

/**
 * Behavior ReceiveInfoU
 * Pode receber:
 *      - Nearby Stations
 *      - Bike Request Answers
 *      - Proposals
 */

public class ReceiveInfoU extends CyclicBehaviour {

    /**
     * Variáveis
     */

    private User agentUser;

    /**
     * Construtores
     */

    public ReceiveInfoU(User agentUser) {

        this.setAgentUser(agentUser);

    }

    /**
     * Setters
     */

    public void setAgentUser(User agentUser) {

        this.agentUser = agentUser;

    }

    /**
     * Action
     */

    public void action() {

        ACLMessage message = this.agentUser.receive();

        if (message != null) {

            AID agent = message.getSender();
            String agentName = agent.getLocalName();

            //Manager envia AID de Nearby Station
            if (agentName.contains("Manager") && message.getPerformative() == ACLMessage.INFORM) {

                try {


                } catch (Exception e) {

                    e.printStackTrace();

                }

                //Station envia uma proposta para deixar a bike
            } else if (agentName.contains("Station") && message.getPerformative() == ACLMessage.REQUEST) {


                //Station responde ao Bike Request
            } else if (agentName.contains("Station") && message.getPerformative() == ACLMessage.ACCEPT_PROPOSAL) {


            } else {

                block();

            }

        }

    }

}
