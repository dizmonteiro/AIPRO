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

        AID agentManager = this.agentStation.getAgentManager();

        ACLMessage message = new ACLMessage(ACLMessage.INFORM);
        message.addReceiver(agentManager);

        StationInfo stationInfo = new StationInfo(this.agentStation.getApe(), this.agentStation.getPosition());

        try {

            message.setContentObject(stationInfo.clone());

        } catch (IOException e) {

            e.printStackTrace();

        }

        this.agentStation.send(message);

    }
}

