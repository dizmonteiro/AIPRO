package Behaviors.Station;

import Agents.Station;
import Extra.TravelPackage;
import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import java.io.IOException;

/**
 * BEHAVIOR STATUS: NOT DONE
 */

public class AnswerBikeRequest extends OneShotBehaviour {

    /**
     * Variáveis
     */

    private Station agentStation;
    private TravelPackage tp;
    private AID agentUser;

    /**
     * Construtores
     */

    public AnswerBikeRequest(Station agentStation, AID agentUser, TravelPackage tp) {

        this.setAgentStation(agentStation);
        this.setAgentUser(agentUser);
        this.setTravelPackage(tp);

    }

    /**
     * Setters
     */

    public void setAgentStation(Station agentStation) {

        this.agentStation = agentStation;

    }

    public void setAgentUser(AID agentUser) {

        this.agentUser = agentUser;

    }

    public void setTravelPackage(TravelPackage tp) {

        this.tp = tp.clone();

    }

    /**
     * Action
     */

    public void action() {

        this.tp.setAgentUser(this.agentUser);

        if(this.agentStation.getNumBikes() > 0) {

            ACLMessage message = new ACLMessage(ACLMessage.ACCEPT_PROPOSAL);
            message.addReceiver(this.agentUser);

            //Por a origem como a posição da estação
            this.tp.setOrigin(this.agentStation.getPosition());

            //Calcular Distancia
            double distance = this.agentStation.calculateDistance(tp.getOrigin(),tp.getDestination());

            //Calcular Preço
            double price = this.agentStation.calculateTotalCost(distance);

            //Atualizar o preço total
            this.tp.setTotalCost(price);

            this.agentStation.removeBike();

            try {

                message.setContentObject(this.tp);

            } catch (IOException e) {

                e.printStackTrace();

            }

            this.agentStation.send(message);


        } else {

            ACLMessage message = new ACLMessage(ACLMessage.REJECT_PROPOSAL);
            message.addReceiver(this.agentUser);

            try {

                message.setContentObject(this.tp);

            } catch (IOException e) {

                e.printStackTrace();

            }

            this.agentStation.send(message);

        }

    }

}

