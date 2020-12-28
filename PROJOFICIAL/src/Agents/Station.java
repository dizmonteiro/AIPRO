package Agents;

import Extra.Position;
import Extra.WorldMap;
import Util.DFFunctions;
import jade.core.AID;
import jade.core.Agent;
import java.util.HashMap;
import java.util.Map;

public class Station extends Agent {

    /**
     * Variáveis
     */

    private WorldMap map;
    private double ape;
    private int numBikes;
    private Position position;
    private Map<AID, User> requestQueue;
    private Map<AID, User> localUsers;

    /**
     * Setup
     */

    public void setup() {

        Object[] args = this.getArguments();

        this.setMap((WorldMap) args[0]);
        this.setApe((Double) args[1]);
        this.setNumBikes((Integer) args[2]);
        this.setPosition((Position) args[3]);

        DFFunctions.registerAgent(this, "Agent Station");

        this.requestQueue = new HashMap<>();
        this.localUsers = new HashMap<>();

    }

    /**
     * Construtores
     */

    public Station(WorldMap map, double ape, int numBikes, Position position, Map<AID, User> requestQueue, Map<AID, User> localUsers) {

        this.setMap(map);
        this.setApe(ape);
        this.setNumBikes(numBikes);
        this.setPosition(position);
        this.setRequestQueue(requestQueue);
        this.setLocalUsers(localUsers);

    }

    /**
     * Getters
     */

    public int getNumBikes() {

        return this.numBikes;

    }

    public double getApe() {

        return this.ape;

    }

    public Map<AID, User> getLocalUsers() {

        Map<AID, User> res = new HashMap<AID, User>(this.localUsers);

        return res;

    }

    public Map<AID, User> getRequestQueue() {

        Map<AID, User> res = new HashMap<AID, User>(this.requestQueue);

        return res;

    }

    public Position getPosition() {

        return this.position.clone();

    }

    public WorldMap getMap() {

        return this.map.clone();

    }

    /**
     * Setters
     */

    public void setNumBikes(int numBikes) {

        this.numBikes = numBikes;

    }

    public void setApe(double ape) {

        this.ape = ape;

    }

    public void setLocalUsers(Map<AID, User> localUsers) {

        this.localUsers = new HashMap<AID, User>(localUsers);

    }

    public void setRequestQueue(Map<AID, User> requestQueue) {

        this.requestQueue = new HashMap<AID, User>(requestQueue);

    }

    public void setPosition(Position position) {

        this.position = position.clone();

    }

    public void setMap(WorldMap map) {

        this.map = map.clone();

    }

    /**
     * Clone
     */

    public Station clone() {

        return new Station(this.map, this.ape, this.numBikes, this.position, this.requestQueue, this.localUsers);

    }

    /**
     * Outros Métodos
     */

}
