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

            //User recebe mensagem do Manager com AID da Station que entrou no seu range
            if (agentName.contains("Manager") && message.getPerformative() == ACLMessage.INFORM) {

                try {


                } catch (Exception e) {

                    e.printStackTrace();

                }

                //User recebe mensagem da Station com desconto para deixar a sua bike
            } else if (agentName.contains("Station") && message.getPerformative() == ACLMessage.REQUEST) {



                //User recebe resposta da Station que aceita o seu Bike Request
            } else if (agentName.contains("Station") && message.getPerformative() == ACLMessage.ACCEPT_PROPOSAL) {



                //User recebe resposta da Station que rejeita o seu Bike Request
            } else if (agentName.contains("Station") && message.getPerformative() == ACLMessage.REJECT_PROPOSAL) {


            } else {

                block();

            }

        }

    }

}
