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

public class MakeProposal extends OneShotBehaviour {

    /**
     * Variáveis
     */

    private Station agentStation;
    private TravelPackage actualTravelPackage;

    /**
     * Construtores
     */

    public MakeProposal(Station agentStation, TravelPackage actualTravelPackage) {

        this.setAgentStation(agentStation);
        this.setTravelPackage(actualTravelPackage);

    }

    /**
     * Setters
     */

    public void setAgentStation(Station agentStation) {

        this.agentStation = agentStation;

    }

    public void setTravelPackage(TravelPackage tp) {

        this.actualTravelPackage = tp.clone();

    }

    /**
     * Action
     */

    public void action() {

        //1. Criamos mensagem com performative PROPOSE
        ACLMessage message = new ACLMessage(ACLMessage.PROPOSE);

        //2. Adicionamos o User contido no TravelPackage como o recetor da mensagem
        message.addReceiver(this.actualTravelPackage.getAgentUser());

        //3. Verificamos se o travelPackage tem como destino a Station

        //3.1. Caso a Station seja não seja o destino, vamos aplicar um desconto ao totalCost do travelPackage
        //     Caso seja o destino, mandamos o mesmo travelPackage que recebemos (não fazemos alterações)

        if(!this.actualTravelPackage.getDestination().equalsPos(this.agentStation.getPosition())) {

            //Mensagem
            System.out.println("> Station AID: " + this.agentStation.getAID() + " is NOT User destination");

            //Mensagem
            System.out.println("> Station AID: " + this.agentStation.getAID() + " is updating travelPackage destination");

            //3.1.1. Atualizamos destino do travelpackage para a posição desta station
            this.actualTravelPackage.setDestination(this.agentStation.getPosition());

            //Mensagem
            System.out.println("> Station AID: " + this.agentStation.getAID() + " is processing discount");

            //3.1.2. Calculamos desconto
            double discountPrice = this.agentStation.calculateDiscount(this.actualTravelPackage.getTotalCost());

            //Mensagem
            System.out.println("> Station AID: " + this.agentStation.getAID() + " is updating travelPackage totalCost");

            //3.1.3. Atualizamos o totalCost do travelPackage
            this.actualTravelPackage.setTotalCost(discountPrice);

        } else {

            //Mensagem
            System.out.println("> Station AID: " + this.agentStation.getAID() + " is User destination");

        }

        try {

            message.setContentObject(this.actualTravelPackage);

        } catch (IOException e) {

            e.printStackTrace();

        }

        //Mensagem
        System.out.println("> Station AID: " + this.agentStation.getAID() + " has sent proposal to leave bike to User");

        this.agentStation.send(message);

    }

}

