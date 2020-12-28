package Behaviors.Manager;

import Agents.Manager;
import Extra.Position;
import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

/**
 * Behavior ReceivePosition
 * 1. Recebe a posição do User
 * 2. Atualiza a posição do User
 * 3. Verifica se a nova posição é dentro de uma ou mais APEs
 * 4. Caso a nova posição seja dentro de uma ou mais APEs, inicia Behavior AnswerUser
 */

public class ReceivePosition extends CyclicBehaviour {

    /**
     * Variáveis
     */

    private Manager agentManager;

    /**
     * Construtores
     */

    public ReceivePosition(Manager agentManager) {

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

            AID agent = message.getSender();
            String agentName = agent.getLocalName();

            //Caso seja um Agent User a mandar a posição
            if (agentName.contains("User") && message.getPerformative() == ACLMessage.INFORM) {

                try {

                    Position newUserPos = (Position) message.getContentObject();
                    this.agentManager.updateUserPos(agent, newUserPos);

                    //Caso o User esteja dentro de uma APE vamos responder
                    if(this.agentManager.isNearStation(agent)) {

                        this.agentManager.addBehaviour(new AnswerUser(this.agentManager,null, null));

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