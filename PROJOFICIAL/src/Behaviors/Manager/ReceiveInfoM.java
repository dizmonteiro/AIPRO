package Behaviors.Manager;

import Agents.Manager;
import Extra.Position;
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

            AID agentUser = message.getSender();
            String agentName = agentUser.getLocalName();

            //Manager recebe a nova posição de um User
            if (agentName.contains("User") && message.getPerformative() == ACLMessage.INFORM) {

                try {

                    InfoPackage newPackage = (InfoPackage) message.getContentObject();
                    Position newUserPos = newPackage.getActualPos();
                    Boolean isTraveling = newPackage.isTraveling();
                    this.agentManager.updateUserPos(agentUser, newUserPos);

                    //Manager verifica se a nova posição do User está dentro de uma APE
                    if(this.agentManager.isNearStation(agentUser)) {

                        //Manager vai buscar todas as Stations que estão no range do User
                        List<AID> nearStations = new ArrayList<>(this.agentManager.getNearStations(agentUser));

                        //Manager envia a lista das Stations para o User
                        //Manager envia o User para todas as Stations
                        for(AID agentStation : nearStations) {

                            this.agentManager.addBehaviour(new SendNearbyStation(this.agentManager, agentUser, agentStation));
                            this.agentManager.addBehaviour(new SendNearbyUser(this.agentManager, agentStation, agentUser, isTraveling));

                        }
                    }

                } catch (Exception e) {

                    e.printStackTrace();

                }

            } else {

                block();

            }

        }

    }

}