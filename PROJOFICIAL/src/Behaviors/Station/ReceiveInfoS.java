package Behaviors.Station;

import Agents.Station;
import Extra.TravelPackage;
import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

/**
 * BEHAVIOR STATUS: NOT DONE
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

            //1. Recolhemos os dados da mensagem
            AID agent = message.getSender();
            String agentName = agent.getLocalName();

            //2. Verificamos qual o Agente e qual o Performative da Mensagem

            //2.1. Caso seja um Manager a mandar um INFORM
            //Station recebe mensagem do Manager com TravelPackage do User que entrou no seu APE
            if (agentName.contains("Manager") && message.getPerformative() == ACLMessage.INFORM) {

                //Mensagem
                System.out.println("> Station AID: " + this.agentStation.getAID() + " has received new INFORM message from Manager " + agent);

                try {

                    //2.1.1. Recolhemos o pacote TravelPackage enviado pelo Manager
                    TravelPackage newTP = (TravelPackage) message.getContentObject();

                    //Mensagem
                    System.out.println("> Station AID: " + this.agentStation.getAID() + " is checking if User has rejected proposal");

                    if(!this.agentStation.getRejectHistory().contains(newTP.getAgentUser())) {

                        //Mensagem
                        System.out.println("> Station AID: " + this.agentStation.getAID() + " has NOT rejected proposal");

                        //2.2.2. Criamos uma proposta para o Utilizador deixar a sua bike
                        this.agentStation.addBehaviour(new MakeProposal(this.agentStation, newTP.clone()));

                    } else {

                        //Mensagem
                        System.out.println("> Station AID: " + this.agentStation.getAID() + " HAS rejected proposal");

                    }

                } catch (Exception e) {

                    e.printStackTrace();

                }

            //2.2. Caso seja um User a mandar um PROPOSE
            } else if(agentName.contains("User") && message.getPerformative() == ACLMessage.PROPOSE) {

                //Mensagem
                System.out.println("> Station AID: " + this.agentStation.getAID() + " has received new PROPOSE message from User " + agent);

                try {

                    //2.2.1. Recolhemos o pacote TravelPackage enviado pelo User
                    TravelPackage newTP = (TravelPackage) message.getContentObject();

                    System.out.println("> Station AID: " + this.agentStation.getAID() + " is processing request...");
                    Thread.sleep(1000);

                    //2.2.2. Criamos uma resposta para a proposta do User
                    this.agentStation.addBehaviour(new AnswerBikeRequest(this.agentStation, agent, newTP.clone()));

                } catch (Exception e) {

                    e.printStackTrace();

                }

            //2.3. Caso seja um User a mandar um ACCEPT_PROPOSAL
            //Um user aceitou deixar a bike na Station
            } else if(agentName.contains("User") && message.getPerformative() == ACLMessage.ACCEPT_PROPOSAL) {

                //Mensagem
                System.out.println("> Station AID: " + this.agentStation.getAID() + " has received new ACCEPT_PROPOSAL message from User " + agent);

                try {

                    //2.3.1. Recolhemos o TravelPackage da mensagem enviada pelo User
                    TravelPackage newTP = (TravelPackage) message.getContentObject();

                    //Mensagem
                    System.out.println("> Station AID: " + this.agentStation.getAID() + " has added 1 bike to available");

                    //2.3.3. Adicionamos 1 bike às available
                    this.agentStation.addBike();

                    //Mensagem
                    System.out.println("> Station AID: " + this.agentStation.getAID() + " has added TravelPackage to rentHistory");

                    //2.3.4. Adicionamos o TravelPackage ao historico
                    this.agentStation.addTravelPackage(newTP.clone());

                    //2.3.5. Atualizamos a info do numBikes que o Manager tem
                    this.agentStation.addBehaviour(new InformUpdateStation(this.agentStation));

                } catch (Exception e) {

                    e.printStackTrace();

                }

            //2.4. Caso seja um User a mandar um REJECT_PROPOSAL
            // Station recebe mensagem do User a rejeitar proposta para deixar a bike
            } else if(agentName.contains("User") && message.getPerformative() == ACLMessage.REJECT_PROPOSAL) {

                //Mensagem
                System.out.println("> Station AID: " + this.agentStation.getAID() + " has received new REJECT_PROPOSAL message from User " + agent);

                try {

                    //Mensagem
                    System.out.println("> Station AID: " + this.agentStation.getAID() + " has updated REJECT_LIST");

                    //2.4.1. Adicionamos o agente User aos reject history para não mandarmos mais propostas
                    this.agentStation.addRejectHistory(agent);

                } catch (Exception e) {

                    e.printStackTrace();

                }

            } else {

                block();

            }

        }

    }

}
