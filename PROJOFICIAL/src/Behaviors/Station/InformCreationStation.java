package Behaviors.Station;

import Agents.Station;
import Extra.StationInfo;
import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;

import java.io.IOException;

public class InformCreationStation extends OneShotBehaviour {

    /**
     * Variáveis
     */

    private Station agentStation;

    /**
     * Construtor
     */

    public InformCreationStation(Station agentStation) {

        this.agentStation = agentStation;

    }

    /**
     * Active
     */

    public void action() {

        //1. Vamos buscar o AID do agentManager que está armazenado no agentStation
        AID agentManager = this.agentStation.getAgentManager();

        //2. Criamos o pacote stationInfo com o APE e a Posição do AgentStation
        StationInfo stationInfo = new StationInfo(this.agentStation.getApe(), this.agentStation.getPosition());

        //3. Criamos a mensagem com performative INFORM
        ACLMessage message = new ACLMessage(ACLMessage.INFORM);

        //4. Introduzimos o Manager como destinatario da mensagem
        message.addReceiver(agentManager);

        try {

            //5. Colocamos o pacote StationInfo na mensagem
            message.setContentObject(stationInfo.clone());

        } catch (IOException e) {

            e.printStackTrace();

        }

        System.out.println("> Station AID: " + this.agentStation.getAID() + " has sent StationInfo to Manager");

        //6. Enviamos a mensagem
        this.agentStation.send(message);

    }
}

