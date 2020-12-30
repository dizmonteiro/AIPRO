package Behaviors.Station;

import Agents.Station;
import Extra.InfoPackageFromUserToManager;
import Util.DFFunctions;
import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;

import java.io.IOException;

public class InformCreationStation extends OneShotBehaviour {

    /**
     * Variáveis
     */

    private Station agentStation;
    private AID agentManager;

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

        this.agentManager = DFFunctions.findSpecificAgent(this.agentStation,"Agent Manager");

        ACLMessage message = new ACLMessage(ACLMessage.INFORM);
        message.addReceiver(this.agentManager);

        try {

            message.setContentObject(this.agentStation.clone());

        } catch (IOException e) {

            e.printStackTrace();

        }

        this.agentStation.send(message);

    }
}

