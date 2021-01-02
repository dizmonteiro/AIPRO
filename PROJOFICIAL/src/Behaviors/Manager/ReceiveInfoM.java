package Behaviors.Manager;

import Agents.Manager;
import Extra.InfoPackageFromUser;
import Extra.Position;
import Extra.StationInfo;
import Extra.TravelPackage;
import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import java.util.ArrayList;
import java.util.List;

/**
 * BEHAVIOR STATUS: NOT DONE, FALTA TESTAR SE A LISTA DAS NEARBYSTATIONS CONTEM A STATION DESTINO, CASO TENHA TEMOS QUE DAR O AID DESSA
 */

public class ReceiveInfoM extends CyclicBehaviour {

    /**
     * Variáveis
     */

    private Manager agentManager;

    /**
     * Construtores
     */

    public ReceiveInfoM(Manager agentManager) {

        this.setAgentManager(agentManager);

    }

    /**
     * Setters
     */

    // Não pode ser clone porque queremos alterar o Manager e não ter acesso a uma cópia dos dados
    public void setAgentManager(Manager agentManager) {

        this.agentManager = agentManager;

    }

    /**
     * Action
     */

    public void action() {

        ACLMessage message = this.agentManager.receive();

        if(message != null) {

            //1. Recolhemos os dados da mensagem
            AID agent = message.getSender();
            String agentName = agent.getLocalName();

            //2. Verificamos qual o Agente e qual o Performative da Mensagem

            //2.1. Caso seja um User a mandar um INFORM
            if (agentName.contains("User") && message.getPerformative() == ACLMessage.INFORM) {

                System.out.println("> Manager AID: " + this.agentManager.getAID() + " has received new INFORM message from User " + agent);

                try {

                    //2.1.1. Recolhemos o pacote InfoPackageFromUserToManager enviado pelo User
                    InfoPackageFromUser newPackage = (InfoPackageFromUser) message.getContentObject();

                    //2.1.2. Recolhemos os dados contidos no pacote
                    Boolean isTraveling = newPackage.isTraveling();
                    Position newUserPos = newPackage.getActualPos();
                    TravelPackage newTravelPackage = newPackage.getTravelPackage();

                    //Mensagem
                    System.out.println("> Manager AID: " + this.agentManager.getAID() + " has collected new InfoPackageFromUserToManager");

                    //2.1.3. Verificamos se o User que enviou o pacote está a viajar

                    //2.1.4. Caso esteja a viajar (tem bike)
                    if(isTraveling) {

                        System.out.println("> Manager AID: " + this.agentManager.getAID() + " User is Traveling");

                        //2.1.4.1. Verificamos se a nova posição do User está dentro do APE de alguma Station

                        //2.1.4.2. Caso esteja dentro do alcance de uma Station
                        if (this.agentManager.isNearStation(newUserPos)) {

                            //Mensagem
                            System.out.println("> Manager AID: " + this.agentManager.getAID() + " User IS inside Station APE");

                            //2.1.4.2.1. Vamos buscar todos as Stations cujo User está ao alcance
                            List<AID> nearStations = new ArrayList<>(this.agentManager.getNearStations(newUserPos));

                            //Mensagem
                            System.out.println("> Manager AID: " + this.agentManager.getAID() + " Checking if Station is User Destination");

                            if(this.agentManager.checkIfHasDestination(nearStations, newUserPos)) {

                                //Mensagem
                                System.out.println("> Manager AID: " + this.agentManager.getAID() + " Station IS User Destination");

                                AID destination = this.agentManager.getDestinationFromList(nearStations, newUserPos);

                                this.agentManager.addBehaviour(new SendNearbyUserToStation(this.agentManager, destination, newTravelPackage.clone()));

                            } else {

                                //Mensagem
                                System.out.println("> Manager AID: " + this.agentManager.getAID() + " Station is NOT User Destination");

                                for (AID agentStation : nearStations) {

                                    this.agentManager.addBehaviour(new SendNearbyUserToStation(this.agentManager, agentStation, newTravelPackage.clone()));

                                }

                            }

                            //2.1.4.2.2. Vamos enviar o travelPackage do User para todas as Stations cujo APE alcance o User
                            /*
                            for (AID agentStation : nearStations) {

                                this.agentManager.addBehaviour(new SendNearbyUserToStation(this.agentManager, agentStation, newTravelPackage.clone()));

                            }*/

                            //2.1.4.2.2. Primeiramente vamos enviar só para a primeira Station
                            //this.agentManager.addBehaviour(new SendNearbyUserToStation(this.agentManager, nearStations.get(0), newTravelPackage.clone()));

                        } else {

                            //2.1.4.3. Caso não esteja dentro do alcance de uma Station

                            //Mensagem
                            System.out.println("> Manager AID: " + this.agentManager.getAID() + " User is NOT inside Station APE");

                        }

                    //2.1.5. Caso não esteja a viajar (não tem bike)
                    // Nesta implementação inicial, isto quer dizer que o User acabou de ser criado
                    } else {

                        //Mensagem
                        System.out.println("> Manager AID: " + this.agentManager.getAID() + " User is NOT Traveling");

                        //2.1.5.1. Vamos buscar o AID da Station em que o User foi criado
                        //AID agentStation = this.agentManager.getStationWithPosition(newUserPos);

                        List<AID> nearStations = new ArrayList<>(this.agentManager.getNearStations(newUserPos));
                        this.agentManager.addBehaviour(new SendNearbyStationToUser(this.agentManager, agent, nearStations.get(0)));

                        //2.1.5.2. Vamos enviar o AID da Station em que foi gerado para o User
                        //this.agentManager.addBehaviour(new SendNearbyStationToUser(this.agentManager, agent, agentStation));

                    }

                    //2.2. Enviamos o package do User para a Interface
                    this.agentManager.addBehaviour(new SendUserPackage(this.agentManager, newPackage.clone()));

                } catch (Exception e) {

                    e.printStackTrace();

                }

            //2.2. Caso seja uma Station a mandar um INFORM
            //Vamos receber este tipo de mensagem quando uma nova estação for criada ou quando for atualizada
            } else if (agentName.contains("Station") && message.getPerformative() == ACLMessage.INFORM) {

                //Mensagem
                System.out.println("> Manager AID: " + this.agentManager.getAID() + " has received new INFORM message from Station " + agent);

                try {

                    //2.2.1. Recolhemos o pacote StationInfo enviado pela Station
                    StationInfo newStationInfo = (StationInfo) message.getContentObject();

                    //Mensagem
                    System.out.println("> Manager AID: " + this.agentManager.getAID() + " has collected new StationInfo");

                    //2.2.2. Adicionamos ao pacote à lista que temos na classe Manager
                    this.agentManager.addStationInfo(agent, newStationInfo.clone());

                    //Mensagem
                    System.out.println("> Manager AID: " + this.agentManager.getAID() + " has saved new StationInfo");

                    //2.2.3. Enviamos a StationInfo para a interface
                    this.agentManager.addBehaviour(new SendStationInfo(this.agentManager, newStationInfo.clone()));

                } catch (Exception e) {

                    e.printStackTrace();

                }

            } else if (agentName.contains("User") && message.getPerformative() == ACLMessage.CANCEL) {

                //Mensagem
                System.out.println("> Manager AID: " + this.agentManager.getAID() + " has received new CANCEL message from User " + agent);

                try {

                    //2.2.1. Recolhemos o pacote StationInfo enviado pela Station
                    AID turnOffUser = (AID) message.getContentObject();

                    //Mensagem
                    System.out.println("> Manager AID: " + this.agentManager.getAID() + " has collected AID of User who turned off");

                    //2.2.3. Enviamos a StationInfo para a interface
                    this.agentManager.addBehaviour(new SendTurnOffNotice(this.agentManager, turnOffUser));

                } catch (Exception e) {

                    e.printStackTrace();

                }


            } else {

                block();

            }

        }

    }

}