package Behaviors.User;

import Agents.User;
import Extra.InfoPackageFromUserToManager;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import java.io.IOException;

/**
 * BEHAVIOR STATUS: DONE
 */

public class UpdatePosition extends OneShotBehaviour {

    /**
     * Variáveis
     */

    private User agentUser;

    /**
     * Construtores
     */

    public UpdatePosition(User agentUser) {

        this.agentUser = agentUser;

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

    public void action() {

        while(this.agentUser.isMoving() && !this.agentUser.getActualPosition().equalsPos(this.agentUser.getActualTPackage().getDestination())){

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

            //Mensagem
            System.out.println("> User AID: " + this.agentUser.getAID() + " has moved to new position: " + this.agentUser.getActualPosition().toString());

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //1. Criamos mensagem com performative INFORM
            ACLMessage message = new ACLMessage(ACLMessage.INFORM);

            //2. Introduzimos o Manager como recetor da mensagem
            message.addReceiver(this.agentUser.getAgentManager());

            //3. Criamos InfoPackageFromUserToManager com posição atualizada
            InfoPackageFromUserToManager newPackage = new InfoPackageFromUserToManager(this.agentUser.isTraveling(), this.agentUser.getActualPosition(), this.agentUser.getActualTPackage().clone());

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