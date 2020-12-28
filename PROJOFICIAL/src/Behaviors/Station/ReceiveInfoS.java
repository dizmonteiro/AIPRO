package Behaviors.Station;

import Agents.Station;
import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

/**
 * Behavior ReceiveInfoS
 * Pode receber:
 *      - Nearby Users
 *      - Bike Requests
 *      - Proposal Answers
 */

public class ReceiveInfoS extends CyclicBehaviour {

    /**
     * Variáveis
     */

    private Station agentStation;

    /**
     * Construtores
     */

    public ReceiveInfoS(Station agentStation) {

        this.setAgentStation(agentStation);

    }

    /**
     * Setters
     */

    public void setAgentStation(Station agentStation) {

        this.agentStation = agentStation;

    }

    /**
     * Action
     */

    public void action() {

        ACLMessage message = this.agentStation.receive();

        if(message != null) {

            AID agent = message.getSender();
            String agentName = agent.getLocalName();

            //Manager envia AID de Nearby User
            if (agentName.contains("Manager") && message.getPerformative() == ACLMessage.INFORM) {

                try {



                } catch (Exception e) {

                    e.printStackTrace();

                }

            //User envia um pedido de requisitar bike
            } else if(agentName.contains("User") && message.getPerformative() == ACLMessage.REQUEST) {


            //User responder à proposta
            } else if(agentName.contains("User") && message.getPerformative() == ACLMessage.ACCEPT_PROPOSAL) {



            } else {

                block();

            }

        }

    }

}
