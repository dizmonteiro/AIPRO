package Behaviors.User;

import Agents.User;
import Extra.TravelPackage;
import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import java.io.IOException;

public class AnswerProposal extends OneShotBehaviour {

    /**
     * Variáveis
     */

    private User agentUser;
    private AID agentStation;
    private TravelPackage newPackage;

    /**
     * Construtores
     */

    public AnswerProposal(User agentUser, AID agentStation, TravelPackage newPackage) {

        this.setAgentUser(agentUser);
        this.setAgentStation(agentStation);
        this.setNewPackage(newPackage);

    }

    /**
     * Setters
     */

    public void setAgentUser(User agentUser) {

        this.agentUser = agentUser;

    }

    public void setAgentStation(AID agentStation) {

        this.agentStation = agentStation;

    }

    public void setNewPackage(TravelPackage newPackage) {

        this.newPackage = newPackage.clone();

    }

    /**
     * Action
     */

    public void action() {

        //1. Verificamos se o TravelPackage proposto tem como destino o mesmo que o User

        //2. Caso seja, quer dizer que chegamos à Station destino e vamos aceitar
        if(this.agentUser.getActualTPackage().getDestination().equalsPos(this.newPackage.getDestination())) {

            //Mensagem
            System.out.println("> User AID: " + this.agentUser.getAID() + " proposal is from DESTINATION");

            //2.1. Criamos a mensagem com Performative ACCEPT_PROPOSAL
            ACLMessage message = new ACLMessage(ACLMessage.ACCEPT_PROPOSAL);

            //2.2. Adicionamos a Station como recetor da mensagem
            message.addReceiver(this.agentStation);

            try {

                //2.3. Introduzimos o package atual do User, que é igual ao que recebemos, na mensagem
                message.setContentObject(this.agentUser.getActualTPackage().clone());

            } catch (IOException e) {

                e.printStackTrace();

            }

            //Mensagem
            System.out.println("> User AID: " + this.agentUser.getAID() + " has sent ACCEPT_PROPOSAL to Station");

            //2.4. Enviamos a mensagem para a Station
            this.agentUser.send(message);

            this.agentUser.addBehaviour(new InformTurnOff(this.agentUser));

            //3. Caso não seja, vamos ver se vale a pena aceitar a proposta

            //3.1. Caso valha a pena aceitar a proposta
        } else if(this.agentUser.evaluateProposal(this.agentUser.getActualTPackage().getTotalCost(), this.newPackage.getTotalCost())) {

            //Mensagem
            System.out.println("> User AID: " + this.agentUser.getAID() + " PROPOSAL is WORTH IT");

            this.agentUser.setActualTPackage(newPackage.clone());

            //2.1. Criamos a mensagem com Performative ACCEPT_PROPOSAL
            ACLMessage message = new ACLMessage(ACLMessage.ACCEPT_PROPOSAL);

            //2.2. Adicionamos a Station como recetor da mensagem
            message.addReceiver(this.agentStation);

            try {

                //2.3. Introduzimos o package atual do User, que é igual ao que recebemos, na mensagem
                message.setContentObject(this.agentUser.getActualTPackage().clone());

            } catch (IOException e) {

                e.printStackTrace();

            }

            //Mensagem
            System.out.println("> User AID: " + this.agentUser.getAID() + " has sent ACCEPT_PROPOSAL to Station");

            //2.4. Enviamos a mensagem para a Station
            this.agentUser.send(message);

            this.agentUser.addBehaviour(new InformTurnOff(this.agentUser));

        //3.2. Caso não valha a pena aceitar a proposta

        } else {

            //Mensagem
            System.out.println("> User AID: " + this.agentUser.getAID() + " PROPOSAL is NOT WORTH IT");

            //3.2.1. Criamos a mensagem com Performative REJECT_PROPSAL
            ACLMessage message = new ACLMessage(ACLMessage.REJECT_PROPOSAL);

            //3.2.2. Adicionamos a Station como recetor da mensagem
            message.addReceiver(this.agentStation);

            try {

                //3.2.3. Adicionamos o travelPackage atual do User, não importa o que vai aqui dentro
                message.setContentObject(this.agentUser.getActualTPackage().clone());

            } catch (IOException e) {

                e.printStackTrace();

            }

            //Mensagem
            System.out.println("> User AID: " + this.agentUser.getAID() + " has sent REJECT_PROPOSAL to Station");

            //3.2.3. Enviamos a mensagem para a Station
            this.agentUser.send(message);

            //Mensagem
            System.out.println("> User AID: " + this.agentUser.getAID() + " has started moving again");

            //3.2.4. O Agente User começa a andar outra vez
            this.agentUser.setMoving(true);

        }

    }

}