package Extra;

import jade.core.AID;

import java.io.Serializable;

public class TravelPackage implements Serializable {

    /**
     * Variáveis
     */

    private AID agentUser;
    private Position origin;
    private Position destination;
    private double totalCost;

    /**
     * Construtores
     */

    public TravelPackage() {

        this.setAgentUser(null);
        this.setOrigin(null);
        this.setDestination(null);
        this.setTotalCost(0);

    }

    public TravelPackage(AID agentUser, Position origin, Position destination, double totalCost) {

        this.setAgentUser(agentUser);
        this.setOrigin(origin);
        this.setDestination(destination);
        this.setTotalCost(totalCost);

    }

    /**
     * Getters
     */

    public AID getAgentUser() {

        return this.agentUser;

    }

    public Position getOrigin() {

        return this.origin.clone();

    }

    public Position getDestination() {

        return this.destination.clone();

    }

    public double getTotalCost() {

        return this.totalCost;

    }

    /**
     * Setters
     */

    public void setAgentUser(AID agentUser) {

        this.agentUser = agentUser;

    }

    public void setOrigin(Position origin) {

        this.origin = origin.clone();

    }

    public void setDestination(Position destination) {

        this.destination = destination.clone();

    }

    public void setTotalCost(double totalCost) {

        this.totalCost = totalCost;

    }

    /**
     * Clone
     */

    public TravelPackage clone() {

        return new TravelPackage(this.agentUser, this.origin, this.destination, this.totalCost);

    }

}
