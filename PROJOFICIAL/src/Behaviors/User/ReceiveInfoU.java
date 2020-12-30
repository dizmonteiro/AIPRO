package Behaviors.User;

import Agents.User;
import Extra.TravelPackage;
import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

/**
 * BEHAVIOR STATUS: NOT DONE
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

                    AID agentStation = (AID) message.getContentObject();

                    this.agentUser.addBehaviour(new MakeBikeRequest(this.agentUser, agentStation));

                } catch (Exception e) {

                    e.printStackTrace();

                }

                //User recebe mensagem da Station com desconto para deixar a sua bike
            } else if (agentName.contains("Station") && message.getPerformative() == ACLMessage.PROPOSE) {

                try {

                    TravelPackage newPackage = (TravelPackage) message.getContentObject();

                    this.agentUser.setMoving(false);

                    this.agentUser.addBehaviour(new AnswerProposal(this.agentUser,agent,newPackage));

                } catch (Exception e) {

                    e.printStackTrace();

                }

                //User recebe resposta da Station que aceita o seu Bike Request
            } else if (agentName.contains("Station") && message.getPerformative() == ACLMessage.ACCEPT_PROPOSAL) {

                try {

                    TravelPackage newPackage = (TravelPackage) message.getContentObject();

                    this.agentUser.setMoving(true);
                    this.agentUser.setActualTPackage(newPackage);

                    this.agentUser.addBehaviour(new UpdatePosition(this.agentUser));

                } catch (Exception e) {

                    e.printStackTrace();

                }

                //User recebe resposta da Station que rejeita o seu Bike Request
            } else if (agentName.contains("Station") && message.getPerformative() == ACLMessage.REJECT_PROPOSAL) {

                try {



                } catch (Exception e) {

                    e.printStackTrace();

                }

            } else {

                block();

            }

        }

    }

}
