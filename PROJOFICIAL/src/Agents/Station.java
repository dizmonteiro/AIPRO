package Agents;

import Extra.Position;
import Extra.WorldMap;
import Util.DFFunctions;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Station extends Agent {

    /**
     * Variáveis
     */

    private WorldMap map;
    private int ape;
    private int numBikes;
    private Position position;
    private List<AID> requestQueue;
    private List<AID> localUsers;

    /**
     * Setup
     */

    public void setup() {

        Object[] args = this.getArguments();

        this.setMap((WorldMap) args[0]);
        this.setApe((Integer) args[1]);
        this.setNumBikes((Integer) args[2]);
        this.setPosition((Position) args[3]);

        DFFunctions.registerAgent(this, "Agent Station");

        this.requestQueue = new ArrayList<>();
        this.localUsers = new ArrayList<>();

    }

    /**
     * Construtores
     */

    public Station(WorldMap map, int ape, int numBikes, Position position, List<AID> requestQueue, List<AID> localUsers) {

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

    public int getApe() {

        return this.ape;

    }

    public List<AID> getLocalUsers() {

        List<AID> res = new ArrayList<AID>(this.localUsers);

        return res;

    }

    public List<AID> getRequestQueue() {

        List<AID> res = new ArrayList<AID>(this.requestQueue);

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

    public void setApe(int ape) {

        this.ape = ape;

    }

    public void setLocalUsers(List<AID> localUsers) {

        this.localUsers = new ArrayList<AID>(localUsers);

    }

    public void setRequestQueue(List<AID> requestQueue) {

        this.requestQueue = new ArrayList<AID>(requestQueue);

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
     * Métodos Auxiliares
     */

    public void removeBike() {

        this.numBikes--;

    }

    public void addBike() {

        this.numBikes++;

    }


}
