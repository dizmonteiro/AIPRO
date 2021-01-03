package Behaviors.Manager;

import Agents.Manager;
import Extra.StationInfo;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import java.io.IOException;

public class SendStationInfo extends OneShotBehaviour {

    /**
     * Variáveis
     */

    private Manager agentManager;
    private StationInfo stationInfo;

    /**
     * Construtor
     */

    public SendStationInfo(Manager agentManager, StationInfo newStation) {

        this.setAgentManager(agentManager);
        this.setStationInfo(newStation);

    }

    /**
     * Setter
     */

    public void setAgentManager(Manager agentManager) {

        this.agentManager = agentManager;

    }

    public void setStationInfo(StationInfo stationInfo) {

        this.stationInfo = stationInfo.clone();

    }

    /**
     * Active
     */

    public void action() {

        //1. Criamos a mensagem com performative INFORM
        ACLMessage message = new ACLMessage(ACLMessage.INFORM);

        //2. Introduzimos o manager como receptor da mensagem
        message.addReceiver(this.agentManager.getAgentInterface());

        try {

            //3. Colocamos o pacote StationInfo na mensagem
            message.setContentObject(this.stationInfo.clone());

        } catch (IOException e) {

            e.printStackTrace();

        }

        //Mensagem
        System.out.println("> Manager AID: " + this.agentManager.getAID() + " has sent StationInfo to INTERFACE");

        //5. Enviamos a mensagem para o Manager
        this.agentManager.send(message);

    }
}
