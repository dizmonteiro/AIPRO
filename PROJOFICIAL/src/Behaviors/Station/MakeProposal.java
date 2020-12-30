package Behaviors.Station;

import Agents.Station;
import Extra.TravelPackage;
import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;

import java.io.IOException;

public class MakeProposal extends OneShotBehaviour {

    /**
     * Variáveis
     */

    private Station agentStation;
    private TravelPackage tp;
    private AID agentUser;

    /**
     * Construtores
     */

    public MakeProposal(Station agentStation, AID agentUser, TravelPackage tp) {

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

        ACLMessage message = new ACLMessage(ACLMessage.PROPOSE);
        message.addReceiver(this.agentUser);

        if(tp.getDestination().equals(this.agentStation.getPosition())) {

            try {

                message.setContentObject(this.tp);

            } catch (IOException e) {

                e.printStackTrace();

            }

            this.agentStation.send(message);

        } else {

            this.tp.setDestination(this.agentStation.getPosition());

            double precoantigo = this.tp.getTotalCost();

            //Calcular Distancia

            double distancia = this.agentStation.calculateDistance(tp.getOrigin(),tp.getDestination());

            //Calcular Promocao

            double novopreco = this.agentStation.calculateDiscount(distancia,precoantigo);

            this.tp.setTotalCost(novopreco);

            try {

                message.setContentObject(this.tp);

            } catch (IOException e) {

                e.printStackTrace();

            }

            this.agentStation.send(message);

        }

    }

}

