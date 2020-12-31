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

            //1. Recolhemos os dados da mensagem
            AID agent = message.getSender();
            String agentName = agent.getLocalName();

            //2. Verificamos qual o Agente e qual o Performative da Mensagem

            //2.1. Caso seja um Manager a mandar um INFORM
            //User acabou de ser criado e Manager manda AID da Station para ele fazer um BikeRequest
            if (agentName.contains("Manager") && message.getPerformative() == ACLMessage.INFORM) {

                //Mensagem
                System.out.println("> User AID: " + this.agentUser.getAID() + " has received new INFORM message from Manager " + agent);

                try {

                    //2.1.1. Recolhemos o AID da Station da mensagem
                    AID agentStation = (AID) message.getContentObject();

                    //Mensagem
                    System.out.println("> User AID: " + this.agentUser.getAID() + " has collected Station AID from message");

                    //2.1.2. Enviamos o BikeRequest para a Station
                    //this.agentUser.addBehaviour(new MakeBikeRequest(this.agentUser, agentStation));

                } catch (Exception e) {

                    e.printStackTrace();

                }

            //2.2. Caso seja uma Station a mandar um PROPOSE
            //User está em viagem e recebe uma mensagem da Station para deixar a sua bike
            } else if (agentName.contains("Station") && message.getPerformative() == ACLMessage.PROPOSE) {

                try {

                    TravelPackage newPackage = (TravelPackage) message.getContentObject();

                    this.agentUser.setMoving(false);

                    //this.agentUser.addBehaviour(new AnswerProposal(this.agentUser,agent,newPackage));

                } catch (Exception e) {

                    e.printStackTrace();

                }

            //2.3. Caso seja uma Station a mandar um ACCEPT_PROPOSAL
            //User recebe resposta da Station que aceita o seu Bike Request
            } else if (agentName.contains("Station") && message.getPerformative() == ACLMessage.ACCEPT_PROPOSAL) {

                try {

                    TravelPackage newPackage = (TravelPackage) message.getContentObject();

                    this.agentUser.setMoving(true);
                    this.agentUser.setActualTPackage(newPackage);

                    //this.agentUser.addBehaviour(new UpdatePosition(this.agentUser));

                } catch (Exception e) {

                    e.printStackTrace();

                }

            //2.4. Caso seja uma Station a mandar um REJECT_PROPOSAL
            //User recebe resposta da Station que rejeita o seu Bike Request
            //Como o REQUEST foi rejeitado, desligamos o User
            } else if (agentName.contains("Station") && message.getPerformative() == ACLMessage.REJECT_PROPOSAL) {

                //Mensagem
                System.out.println("> User AID: " + this.agentUser.getAID() + " has received new REJECT_PROPOSAL message from Manager " + agent);

                try {

                    //Mensagem
                    System.out.println("> User AID: " + this.agentUser.getAID() + " is turning OFF ");

                    //2.4.1. Desligamos o agente
                    this.agentUser.turnOff();

                } catch (Exception e) {

                    e.printStackTrace();

                }

            } else {

                block();

            }

        }

    }

}
