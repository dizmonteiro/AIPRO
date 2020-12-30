package Behaviors.User;

import Agents.User;
import Extra.InfoPackageFromUserToManager;
import Util.DFFunctions;
import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import java.io.IOException;

/**
 * BEHAVIOR STATUS: NOT DONE
 */

public class UpdatePosition extends OneShotBehaviour {

    /**
     * Variáveis
     */

    private User agentUser;
    private AID agentManager;

    /**
     * Construtores
     */

    public UpdatePosition(User agentUser) {

        this.agentUser = agentUser;
        this.agentManager = DFFunctions.findSpecificAgent(this.agentUser,"Agent Manager");

    }

    /**
     * Action
     */

    public void action() {

        while(this.agentUser.isMoving() && !this.agentUser.getActualPosition().equals(this.agentUser.getActualTPackage().getDestination())){

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
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            ACLMessage message = new ACLMessage(ACLMessage.INFORM);
            message.addReceiver(this.agentManager);

            InfoPackageFromUserToManager newPackage = new InfoPackageFromUserToManager(this.agentUser.isTraveling(), this.agentUser.getActualPosition(), this.agentUser.getActualTPackage());

            try {

                message.setContentObject(newPackage);

            } catch (IOException e) {

                e.printStackTrace();

            }

            this.agentUser.send(message);

        }

    }

}