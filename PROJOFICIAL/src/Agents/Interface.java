package Agents;

import Extra.WorldMap;
import Util.DFFunctions;
import jade.core.Agent;
import java.util.ArrayList;
import java.util.List;

public class Interface extends Agent {

    /**
     * Variáveis
     */

    private WorldMap map;
    private List<User> users;
    private List<Station> stations;

    /**
     * Setup
     */

    public void setup() {

        Object[] args = this.getArguments();

        this.setMap((WorldMap) args[0]);

        DFFunctions.registerAgent(this, "Agent Interface");

        this.users = new ArrayList<>();
        this.stations = new ArrayList<>();

    }

    /**
     * Construtores
     */

    public Interface(WorldMap map, List<User> users, List<Station> stations) {

        this.setMap(map);
        this.setUsers(users);
        this.setStations(stations);

    }

    /**
     * Getters
     */

    public WorldMap getMap() {

        return this.map.clone();

    }

    public List<Station> getStations() {

        List<Station> res = new ArrayList<>();

        for(Station s : this.stations) {
            res.add(s.clone());
        }

        return res;

    }

    public List<User> getUsers() {

        List<User> res = new ArrayList<>();

        for(User u : this.users) {
            res.add(u.clone());
        }

        return res;

    }

    /**
     * Setters
     */

    public void setMap(WorldMap map) {

        this.map = map.clone();

    }

    public void setStations(List<Station> stations) {

        this.stations = new ArrayList<>();

        for(Station s : stations) {
            this.stations.add(s.clone());
        }

    }

    public void setUsers(List<User> users) {

        this.users = new ArrayList<>();

        for(User u : users) {
            this.users.add(u.clone());
        }

    }

    /**
     * Clone
     */

    public Interface clone() {

        return new Interface(this.map, this.users, this.stations);

    }


    /**
     * Outros Métodos
     */




}
