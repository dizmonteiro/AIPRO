package Extra;

import jade.core.AID;

import java.io.Serializable;

public class InfoPackage implements Serializable {

    /**
     * Variáveis
     */

    private AID agent;
    private Position actualPos;
    private Position origin;
    private Position destination;
    private double balance;
    private boolean isTraveling;

    /**
     * Construtores
     */

    public InfoPackage(AID agent, Position actualPos, Position origin, Position destination, double balance, boolean isTraveling) {

        this.setAgent(agent);
        this.setDestination(destination);
        this.setBalance(balance);
        this.setActualPos(actualPos);
        this.setOrigin(origin);
        this.setTraveling(isTraveling);

    }

    //SendNearbyUserPackage
    public InfoPackage(boolean isTraveling, AID agent) {

        //importante
        this.setAgent(agent);
        this.setTraveling(isTraveling);

        //lixo
        this.setOrigin(null);
        this.setDestination(null);
        this.setActualPos(null);
        this.setBalance(0);

    }

    //UpdatePositionPackage
    public InfoPackage(boolean isTraveling, Position actualPos) {

        //importante
        this.setActualPos(actualPos);
        this.setTraveling(isTraveling);

        //lixo
        this.setAgent(null);
        this.setBalance(0);
        this.setDestination(null);
        this.setOrigin(null);

    }

    /**
     * Getters
     */

    public Position getActualPos() {

        return this.actualPos.clone();

    }

    public boolean isTraveling() {

        return this.isTraveling;

    }

    public Position getOrigin() {

        return this.origin.clone();

    }

    public double getBalance() {

        return this.balance;

    }

    public AID getAgent() {

        return this.agent;

    }

    public Position getDestination() {

        return this.destination.clone();

    }

    /**
     * Setters
     */

    public void setTraveling(boolean traveling) {

        isTraveling = traveling;

    }

    public void setActualPos(Position actualPos) {

        this.actualPos = actualPos.clone();

    }

    public void setOrigin(Position origin) {

        this.origin = origin.clone();

    }

    public void setBalance(double balance) {

        this.balance = balance;

    }

    public void setAgent(AID agent) {

        this.agent = agent;

    }

    public void setDestination(Position destination) {

        this.destination = destination.clone();

    }

    /**
     * Clone
     */

    public InfoPackage clone() {

        return new InfoPackage(this.agent, this.actualPos, this.origin, this.destination, this.balance, this.isTraveling);

    }
}
