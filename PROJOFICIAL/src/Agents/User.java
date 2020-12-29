package Agents;

import Extra.Position;
import Extra.WorldMap;
import Util.DFFunctions;
import jade.core.AID;
import jade.core.Agent;
import java.util.ArrayList;
import java.util.List;

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
    private int teimosia; //grau de teimosia de 1-10, variável que será util na decisão de aceitar ou rejeitar propostas das Stations
    private List<AID> localStations;

    /**
     * Setup
     */

    public void setup() {

        Object[] args = this.getArguments();

        this.setMap((WorldMap) args[0]);
        this.setActualPosition((Position) args[1]);
        this.setBalance((Double) args[2]);
        this.setTeimosia((Integer) args[3]);

        DFFunctions.registerAgent(this, "Agent User");

        this.setOrigin(null);
        this.setDestination(null);
        this.setTraveling(false);
        this.localStations = new ArrayList<>();

    }

    /**
     * Construtores
     */

    public User(WorldMap map, Position origin, Position actualPosition, Position destination, double balance, boolean isTraveling, List<AID> localStations, int teimosia) {

        this.setMap(map);
        this.setOrigin(origin);
        this.setActualPosition(actualPosition);
        this.setDestination(destination);
        this.setBalance(balance);
        this.setTraveling(isTraveling);
        this.setLocalStations(localStations);
        this.setTeimosia(teimosia);

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

    public List getLocalStations() {

        List<AID> res = new ArrayList<>(this.localStations);

        return res;

    }

    public boolean isTraveling() {

        return this.isTraveling;

    }

    public WorldMap getMap() {

        return this.map.clone();

    }

    public int getTeimosia() {
        return this.teimosia;
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

    public void setLocalStations(List<AID> localStations) {

        this.localStations = new ArrayList<>(localStations);

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

    public void setTeimosia(int teimosia) {
        this.teimosia = teimosia;
    }

    /**
     * Clone
     */

    public User clone() {

        return new User(this.map, this.origin, this.actualPosition, this.destination, this.balance, this.isTraveling, this.localStations, this.teimosia);

    }

    /**
     * Métodos Auxiliares
     */


}
