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
 *      - Deliver Bike Informs
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

            //Station recebe mensagem do Manager com AID do User que entrou no seu APE
            if (agentName.contains("Manager") && message.getPerformative() == ACLMessage.INFORM) {



                //Station recebe pedido do User para requisitar uma Bike
            } else if(agentName.contains("User") && message.getPerformative() == ACLMessage.REQUEST) {



                //Station recebe mensagem do User a aceitar proposta
            } else if(agentName.contains("User") && message.getPerformative() == ACLMessage.ACCEPT_PROPOSAL) {



                //Station recebe mensagem do User a rejeitar proposta
            } else if(agentName.contains("User") && message.getPerformative() == ACLMessage.REJECT_PROPOSAL) {



                //Station recebe mensagem do User de que entregou a sua Bike
            } else if(agentName.contains("User") && message.getPerformative() == ACLMessage.INFORM) {



            } else {

                block();

            }

        }

    }

}
