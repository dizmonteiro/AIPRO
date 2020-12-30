package Behaviors.Manager;

import Agents.Manager;
import Agents.Station;
import Extra.InfoPackageFromUserToManager;
import Extra.Position;
import Extra.TravelPackage;
import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import java.util.ArrayList;
import java.util.List;

/**
 * Behavior ReceiveUserPosition
 * 1. Recebe a posição do User
 * 2. Atualiza a posição do User
 * 3. Verifica se a nova posição é dentro de uma ou mais APEs
 * 4. Caso a nova posição seja dentro de uma ou mais APEs, inicia Behavior AnswerUser
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

            //O Agente Manager recolhe os dados da mensagem
            AID agent = message.getSender();
            String agentName = agent.getLocalName();

            if (agentName.contains("User") && message.getPerformative() == ACLMessage.INFORM) {

                try {

                    InfoPackageFromUserToManager newPackage = (InfoPackageFromUserToManager) message.getContentObject();
                    Boolean isTraveling = newPackage.isTraveling();
                    Position newUserPos = newPackage.getActualPos();
                    TravelPackage tp = newPackage.getTp();

                    //O Agente Manager verifica se o Agente User está a viajar
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

            } else if (agentName.contains("Station") && message.getPerformative() == ACLMessage.INFORM) {

                try {

                    //Caso seja uma estação que acabou de ser criada, adicionamos ao Map

                    Station newStation = (Station) message.getContentObject();

                    this.agentManager.addStation(agent, newStation);

                } catch (Exception e) {

                    e.printStackTrace();

                }

            } else {

                block();

            }

        }

    }

}