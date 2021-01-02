package Behaviors.Station;

import Agents.Station;
import Extra.StationInfo;
import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import java.io.IOException;

/**
 * BEHAVIOR STATUS: DONE
 */

public class InformUpdateStation extends OneShotBehaviour {

    /**
     * Variáveis
     */

    private Station agentStation;

    /**
     * Construtor
     */

    public InformUpdateStation(Station agentStation) {

        this.setAgentStation(agentStation);

    }

    /**
     * Setters
     */

    public void setAgentStation(Station agentStation) {

        this.agentStation = agentStation;

    }

    /**
     * Active
     */

    public void action() {

        //1. Criamos o pacote stationInfo com o APE e a Posição do AgentStation
        StationInfo stationInfo = new StationInfo(this.agentStation.getAID(),this.agentStation.getApe(), this.agentStation.getPosition(), this.agentStation.getNumBikes());

        //2. Criamos a mensagem com performative INFORM
        ACLMessage message = new ACLMessage(ACLMessage.INFORM);

        //3. Introduzimos o Manager como destinatario da mensagem
        message.addReceiver(this.agentStation.getAgentManager());

        try {

            //4. Colocamos o pacote StationInfo na mensagem
            message.setContentObject(stationInfo.clone());

        } catch (IOException e) {

            e.printStackTrace();

        }

        //Mensagem
        System.out.println("> Station AID: " + this.agentStation.getAID() + " has sent StationInfo to Manager");

        //5. Enviamos a mensagem
        this.agentStation.send(message);

    }
}

