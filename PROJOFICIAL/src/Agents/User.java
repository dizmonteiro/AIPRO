package Agents;

import Extra.Position;
import Extra.WorldMap;
import Util.DFFunctions;
import jade.core.AID;
import jade.core.Agent;
import java.util.HashMap;
import java.util.Map;

public class User extends Agent {

    /**
     * Variáveis
     */

    private WorldMap map;
    private Position origin;
    private Position actualPosition;
    private Position destination;
    private double balance;
    private boolean isTraveling;
    private Map<AID, Station> localStations;

    /**
     * Setup
     */

    public void setup() {

        Object[] args = this.getArguments();

        this.setMap((WorldMap) args[0]);
        this.setActualPosition((Position) args[1]);
        this.setBalance((Double) args[2]);

        DFFunctions.registerAgent(this, "Agent User");

        this.setOrigin(null);
        this.setDestination(null);
        this.setTraveling(false);
        this.localStations = new HashMap<>();

    }

    /**
     * Construtores
     */

    public User(WorldMap map, Position origin, Position actualPosition, Position destination, double balance, boolean isTraveling, Map<AID, Station> localStations) {

        this.setMap(map);
        this.setOrigin(origin);
        this.setActualPosition(actualPosition);
        this.setDestination(destination);
        this.setBalance(balance);
        this.setTraveling(isTraveling);
        this.setLocalStations(localStations);

    }

    /**
     * Getters
     */

    public double getBalance() {

        return this.balance;

    }

    public Position getActualPosition() {

        return this.actualPosition.clone();

    }

    public Position getDestination() {

        return this.destination.clone();

    }

    public Position getOrigin() {

        return this.origin.clone();

    }

    public Map<AID, Station> getLocalStations() {

        Map<AID, Station> res = new HashMap<AID, Station>(this.localStations);

        return res;

    }

    public boolean isTraveling() {

        return this.isTraveling;

    }

    public WorldMap getMap() {

        return this.map.clone();

    }

    /**
     * Setters
     */

    public void setActualPosition(Position actualPosition) {

        this.actualPosition = actualPosition.clone();

    }

    public void setBalance(double balance) {

        this.balance = balance;

    }

    public void setDestination(Position destination) {

        this.destination = destination.clone();

    }

    public void setLocalStations(Map<AID, Station> localStations) {

        this.localStations = new HashMap<AID, Station>(localStations);

    }

    public void setOrigin(Position origin) {

        this.origin = origin.clone();

    }

    public void setTraveling(boolean traveling) {

        isTraveling = traveling;

    }

    public void setMap(WorldMap map) {

        this.map = map.clone();

    }

    /**
     * Clone
     */

    public User clone() {

        return new User(this.map, this.origin, this.actualPosition, this.destination, this.balance, this.isTraveling, this.localStations);

    }

    /**
     * Outros Métodos
     */

}
