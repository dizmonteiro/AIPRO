package Behaviors.Station;

import Agents.Station;
import Extra.TravelPackage;
import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import java.io.IOException;

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

        //1. Verificamos se a Station tem bikes disponiveis

        //1.1. Caso tenha:
        if(this.agentStation.getNumBikes() > 0) {

            //Mensagem
            System.out.println("> Station AID: " + this.agentStation.getAID() + " has available bikes");

            //1.1.1. Criamos uma mensagem com o performative ACCEPT_PROPOSAL
            ACLMessage message = new ACLMessage(ACLMessage.ACCEPT_PROPOSAL);

            //1.1.2. Introduzimos o User como recetor da mensagem
            message.addReceiver(this.agentUser);

            //Mensagem
            System.out.println("> Station AID: " + this.agentStation.getAID() + " is calculating distance to destination");

            //1.1.3. Calculamos a distancia ao destino
            double distance = this.agentStation.calculateDistance(tp.getOrigin(),tp.getDestination());

            //Mensagem
            System.out.println("> Station AID: " + this.agentStation.getAID() + " is calculating totalCost of trip");

            //1.1.4. Calculamos o preco total da viagem
            double price = this.agentStation.calculateTotalCost(distance);

            //Mensagem
            System.out.println("> Station AID: " + this.agentStation.getAID() + " is updating totalCost of travelPackage");

            //1.1.5. Atualizamos o totalCost do travelPackage
            this.tp.setTotalCost(price);

            //Mensagem
            System.out.println("> Station AID: " + this.agentStation.getAID() + " has removed 1 bike from available");

            //1.1.6. Removemos uma bikes da station
            this.agentStation.removeBike();

            try {

                //1.1.7. Introduzimos o travelPackage atualizado na mensagem
                message.setContentObject(this.tp.clone());

            } catch (IOException e) {

                e.printStackTrace();

            }

            //Mensagem
            System.out.println("> Station AID: " + this.agentStation.getAID() + " has sent ACCEPT_PROPOSAL to User");

            //1.1.8. Enviamos a mensagem para o User
            this.agentStation.send(message);

            //Mensagem
            System.out.println("> Station AID: " + this.agentStation.getAID() + " has sent updated StationInfo to Manager");

            //1.1.9. Atualizamos a info do numBikes que o Manager tem
            this.agentStation.addBehaviour(new InformUpdateStation(this.agentStation));


        //1.2. Caso não tenha:
        } else {

            //Mensagem
            System.out.println("> Station AID: " + this.agentStation.getAID() + " does NOT have available bikes");

            //1.2.1. Criamos uma mensagem
            ACLMessage message = new ACLMessage(ACLMessage.REJECT_PROPOSAL);

            //1.2.2. Introduzimos o User como recetor da mensagem
            message.addReceiver(this.agentUser);

            try {

                //1.2.3. Introduzimos o travelPackage que recebemos, não importa
                message.setContentObject(this.tp.clone());

            } catch (IOException e) {

                e.printStackTrace();

            }

            //Mensagem
            System.out.println("> Station AID: " + this.agentStation.getAID() + " has sent REJECT_PROPOSAL to User");

            //1.2.4. Enviamos a mensagem para o User
            this.agentStation.send(message);

        }

    }

}

