package Agents;

import Extra.Position;
import Extra.WorldMap;
import Util.DFFunctions;
import jade.core.Agent;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.*;

public class Interface extends Agent {

    /**
     * Variáveis
     */

    private WorldMap map;
    private List<User> users;
    private List<Station> stations;
    private PainelInicial pi;
    /**
     * Setup
     */

    public void setup() {

        Object[] args = this.getArguments();

        this.setMap((WorldMap) args[0]);

        DFFunctions.registerAgent(this, "Agent Interface");

        this.users = new ArrayList<>();
        this.stations = new ArrayList<>();

        pi = new PainelInicial();
        JScrollPane scroll = new JScrollPane(pi);
        pi.setPreferredSize(new Dimension(500*10,500*10));
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
     * Métodos Auxiliares
     */

    /**
     * Behaviors
     */
    public class PainelInicial extends JPanel {
        public void paintComponent(Graphics g){
            int x, y;
            Position s_pos;
            Position u_pos;
            super.paintComponent(g);
            for (int row = 0; row < map.getMapSize(); row++) {
                for (int col = 0; col < map.getMapSize(); col++) {
                    x = row * 10;
                    y = col * 10;
                    g.drawRect(x, y, 10, 10);
                }
            }

            for(User u : users) {
                u_pos = u.getActualPosition();
                g.setColor(Color.ORANGE);
                g.fillRect(u_pos.getX() * 10, u_pos.getY() * 10, 10, 10);
            }

            for(Station s : stations) {
                s_pos = s.getPosition();
                g.setColor(Color.ORANGE);
                g.fillRect(s_pos.getX() * 10, s_pos.getY() * 10, 10, 10);
            }

        }
    }




}
