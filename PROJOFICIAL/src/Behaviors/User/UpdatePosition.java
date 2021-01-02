package Behaviors.User;

import Agents.User;
import Extra.InfoPackageFromUser;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;
import java.io.IOException;

/**
 * BEHAVIOR STATUS: DONE
 */

public class UpdatePosition extends TickerBehaviour {

    /**
     * Variáveis
     */

    private User agentUser;
    private int velocity;

    /**
     * Construtores
     */

    public UpdatePosition(User agentUser, int velocity) {

        super(agentUser,velocity*10);
        this.setAgentUser(agentUser);
        this.velocity = velocity;


    }

    /**
     * Setters
     */


    public void setAgentUser(User agentUser) {

        this.agentUser = agentUser;

    }

    /**
     * Action
     */

    public void onTick() {

        if(this.agentUser.isMoving() && !this.agentUser.getActualPosition().equalsPos(this.agentUser.getActualTPackage().getDestination())){

            int actualX = this.agentUser.getActualPosition().getX();
            int actualY = this.agentUser.getActualPosition().getY();
            int finalX = this.agentUser.getActualTPackage().getDestination().getX();
            int finalY = this.agentUser.getActualTPackage().getDestination().getY();

            if(actualX == finalX && actualY > finalY) {

                this.agentUser.moveMinusY();

            }
            else if(actualX == finalX && actualY < finalY) {

                this.agentUser.movePlusY();

            }
            else if(actualX > finalX && actualY == finalY) {

                this.agentUser.moveMinusX();

            }
            else if(actualX < finalX && actualY == finalY) {

                this.agentUser.movePlusX();

            }
            else if(actualX > finalX && actualY > finalY) {

                this.agentUser.moveMinusX();
                this.agentUser.moveMinusY();

            }
            else if(actualX < finalX && actualY < finalY) {

                this.agentUser.movePlusX();
                this.agentUser.movePlusY();

            }
            else if(actualX > finalX && actualY < finalY) {

                this.agentUser.moveMinusX();
                this.agentUser.movePlusY();

            }
            else if(actualX < finalX && actualY > finalY) {

                this.agentUser.movePlusX();
                this.agentUser.moveMinusY();

            }

            try {
                Thread.sleep(this.agentUser.getVelocity()*100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //Mensagem
            System.out.println("> User AID: " + this.agentUser.getAID() + " has moved to new position: " + this.agentUser.getActualPosition().toString());

            //1. Criamos mensagem com performative INFORM
            ACLMessage message = new ACLMessage(ACLMessage.INFORM);

            //2. Introduzimos o Manager como recetor da mensagem
            message.addReceiver(this.agentUser.getAgentManager());

            //3. Criamos InfoPackageFromUserToManager com posição atualizada
            InfoPackageFromUser newPackage = new InfoPackageFromUser(this.agentUser.isTraveling(), this.agentUser.getActualPosition(), this.agentUser.getActualTPackage().clone());

            try {

                //4. Introduzimos copia do package na mensagem
                message.setContentObject(newPackage.clone());

            } catch (IOException e) {

                e.printStackTrace();

            }

            //Mensagem
            System.out.println("> User AID: " + this.agentUser.getAID() + " has sent update position to Manager");

            //5. Enviamos mensagem para o Manager
            this.agentUser.send(message);

        }

    }

}