package Behaviors.Manager;

import Agents.Manager;
import Extra.InfoPackageFromUserToManager;
import Extra.Position;
import Extra.StationInfo;
import Extra.TravelPackage;
import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import java.util.ArrayList;
import java.util.List;

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

                System.out.println("> Manager AID: " + this.agentManager.getAID() + " has received new message from User " + agent);

                try {

                    //2.1.1. Recolhemos o pacote InfoPackageFromUserToManager enviado pelo User
                    InfoPackageFromUserToManager newPackage = (InfoPackageFromUserToManager) message.getContentObject();

                    //2.1.2. Recolhemos os dados contidos no pacote
                    Boolean isTraveling = newPackage.isTraveling();
                    Position newUserPos = newPackage.getActualPos();
                    TravelPackage tp = newPackage.getTravelPackage();

                    //Mensagem
                    System.out.println("> Manager AID: " + this.agentManager.getAID() + " has received new InfoPackageFromUserToManager");

                    //2.1.3. Verificamos se o User que enviou o pacote está a viajar

                    //2.1.4. Caso esteja a viajar (tem bike)
                    if(isTraveling) {

                        //O Agente Manager verifica se a nova posição do Agente User está dentro do APE de algum Agente Station
                        if (this.agentManager.isNearStation(newUserPos)) {

                            //O Agente Manager vai buscar todas os Agentes Station cujo range contem a nova posição do Agente User
                            List<AID> nearStations = new ArrayList<>(this.agentManager.getNearStations(newUserPos));

                            //O Agente Manager envia o AID do Agente User para todos os Agentes Stations
                            for (AID agentStation : nearStations) {

                                this.agentManager.addBehaviour(new SendNearbyUserToStation(this.agentManager, agentStation, tp));

                            }
                        }

                    //2.1.5. Caso não esteja a viajar (não tem bike)
                    } else {

                        //O Agente Manager vai buscar todas os Agentes Station cujo range contem a nova posição do Agente User
                        List<AID> nearStations = new ArrayList<>(this.agentManager.getNearStations(newUserPos));

                        //O Agente Manager envia o AID do Agente User para todos os Agentes Stations
                        for (AID agentStation : nearStations) {

                            this.agentManager.addBehaviour(new SendNearbyStationToUser(this.agentManager, agent, agentStation));

                        }

                    }

                } catch (Exception e) {

                    e.printStackTrace();

                }

            //2.2. Caso seja uma Station a mandar um INFORM
            //Vamos receber este tipo de mensagem quando uma nova estação for criada
            } else if (agentName.contains("Station") && message.getPerformative() == ACLMessage.INFORM) {

                //Mensagem
                System.out.println("> Manager AID: " + this.agentManager.getAID() + " has received new message from Station " + agent);

                try {

                    //2.2.1. Recolhemos o pacote StationInfo enviado pela Station
                    StationInfo newStationInfo = (StationInfo) message.getContentObject();

                    //Mensagem
                    System.out.println("> Manager AID: " + this.agentManager.getAID() + " has received new StationInfo");

                    //2.2.2. Adicionamos ao pacote à lista que temos na classe Manager
                    this.agentManager.addStationInfo(agent, newStationInfo.clone());

                    //Mensagem
                    System.out.println("> Manager AID: " + this.agentManager.getAID() + " has saved new StationInfo");

                } catch (Exception e) {

                    e.printStackTrace();

                }

            } else {

                block();

            }

        }

    }

}