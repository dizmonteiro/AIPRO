package Behaviors.Manager;

import Agents.Manager;
import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import java.io.IOException;

public class SendTurnOffNotice extends OneShotBehaviour {

    /**
     * Variáveis
     */

    private Manager agentManager;
    private AID turnOffUser;

    /**
     * Construtor
     */

    public SendTurnOffNotice(Manager agentManager, AID user) {

        this.setAgentManager(agentManager);
        this.setUserAID(user);

    }

    /**
     * Setter
     */

    public void setAgentManager(Manager agentManager) {

        this.agentManager = agentManager;

    }

    public void setUserAID(AID user) {

        this.turnOffUser = user;

    }

    /**
     * Active
     */

    public void action() {

        //1. Criamos a mensagem com performative INFORM
        ACLMessage message = new ACLMessage(ACLMessage.CANCEL);

        //2. Introduzimos o manager como receptor da mensagem
        message.addReceiver(this.agentManager.getAgentInterface());

        try {

            //3. Colocamos o pacote StationInfo na mensagem
            message.setContentObject(this.turnOffUser);

        } catch (IOException e) {

            e.printStackTrace();

        }

        //Mensagem
        System.out.println("> Manager AID: " + this.agentManager.getAID() + " has sent TURN OFF NOTICE to INTERFACE");

        //5. Enviamos a mensagem para o Manager
        this.agentManager.send(message);

    }
}
