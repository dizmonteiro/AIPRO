package Agents;

import Behaviors.Manager.ReceivePosition;
import Extra.Position;
import Extra.WorldMap;
import Util.DFFunctions;
import jade.core.AID;
import jade.core.Agent;

import java.util.ArrayList;
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

    //Verifica se a posição do User está dentro da APE de uma estação especifica
    public boolean isInRange(Position userPos, Position stationPos, int ape) {

        double calc1 = ((userPos.getX() - stationPos.getX())^2) + ((userPos.getY() - stationPos.getY())^2);
        double calc2 = ape^2;

        if(calc1 < calc2) {

            return true;

        }

        return false;

    }

    //Verifica se o User está dentro da APE de qualquer estação
    public boolean isNearStation(AID agent) {

        Position userPosition = this.globalUsers.get(agent).getActualPosition();
        final boolean[] x = new boolean[1];
        x[0] = false;

        this.globalStations.forEach((k,v) -> {

            if(isInRange(userPosition, v.getPosition(), v.getApe())) {

                x[0] = true;

            }

        });

        return x[0];

    }

    //Devolve uma lista com todas as estações em que o User tem no seu range
    public List getNearStations(AID agent) {

        List<AID> nearStations = new ArrayList<>();
        Position userPosition = this.globalUsers.get(agent).getActualPosition();

        this.globalStations.forEach((k,v) -> {

            if(isInRange(userPosition, v.getPosition(), v.getApe())) {

                nearStations.add(k);

            }

        });

        return nearStations;

    }

}
