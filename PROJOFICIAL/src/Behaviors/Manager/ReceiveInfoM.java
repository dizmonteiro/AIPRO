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

            //Caso seja um Agent User a mandar a posição
            if (agentName.contains("User") && message.getPerformative() == ACLMessage.INFORM) {

                try {

                    Position newUserPos = (Position) message.getContentObject();
                    this.agentManager.updateUserPos(agentUser, newUserPos);

                    //Caso o User esteja dentro de uma APE vamos responder
                    if(this.agentManager.isNearStation(agentUser)) {

                        //Vai buscar todas as estações no range do User
                        List<AID> nearStations = new ArrayList<>(this.agentManager.getNearStations(agentUser));

                        //Envia a lista das estações para o User, envia o User para todas as Estações
                        for(AID agentStation : nearStations) {
                            this.agentManager.addBehaviour(new SendNearbyStation(this.agentManager, agentUser, agentStation));
                            this.agentManager.addBehaviour(new SendNearbyUser(this.agentManager, agentStation, agentUser));
                        }
                    }

                } catch (Exception e) {

                    e.printStackTrace();

                }

                //Caso seja um Agent Interface a pedir informação
            } else {

                block();

            }

        }

    }

}