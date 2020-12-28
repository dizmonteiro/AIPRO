package Agents;

import Behaviors.Manager.ReceivePosition;
import Extra.Position;
import Extra.WorldMap;
import Util.DFFunctions;
import jade.core.AID;
import jade.core.Agent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Manager extends Agent {

    /**
     * Variáveis
     */

    private WorldMap map;
    private Map<AID, User> globalUsers;
    private Map<AID, Station> globalStations;

    /**
     * Setup
     */

    public void setup() {

        Object[] args = this.getArguments();

        this.setMap((WorldMap) args[0]);

        DFFunctions.registerAgent(this, "Agent Manager");

        this.globalUsers = new HashMap<>();
        this.globalStations = new HashMap<>();

        addBehaviour(new ReceivePosition(this));

    }

    /**
     * Construtores
     */

    public Manager(WorldMap map, Map<AID, User> globalUsers, Map<AID, Station> globalStations) {

        this.setMap(map);
        this.setGlobalUsers(globalUsers);
        this.setGlobalStations(globalStations);

    }

    /**
     * Getters
     */

    public WorldMap getMap() {

        return this.map.clone();

    }

    public Map<AID, Station> getGlobalStations() {

        Map<AID, Station> res = new HashMap<AID, Station>(this.globalStations);

        return res;

    }

    public Map<AID, User> getGlobalUsers() {

        Map<AID, User> res = new HashMap<AID, User>(this.globalUsers);

        return res;

    }

    /**
     * Setters
     */

    public void setMap(WorldMap map) {

        this.map = map.clone();

    }

    public void setGlobalStations(Map<AID, Station> globalStations) {

        this.globalStations = new HashMap<AID, Station>(globalStations);

    }

    public void setGlobalUsers(Map<AID, User> globalUsers) {

        this.globalUsers = new HashMap<AID, User>(globalUsers);

    }

    /**
     * Clone
     */

    public Manager clone() {

        return new Manager(this.map, this.globalUsers, this.globalStations);

    }

    /**
     * Métodos Auxiliares
     */

    public void updateUserPos(AID agent, Position newPos) {

        this.globalUsers.get(agent).setActualPosition(newPos);

    }

    public boolean isNearStation(AID agent) {

        Position agentPosition = this.globalUsers.get(agent).getActualPosition().clone();



        return false;

    }

    public List getNeatStations(AID agent) {
        return null;
    }

}
