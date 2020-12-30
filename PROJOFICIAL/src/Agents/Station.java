package Agents;

import Extra.Position;
import Extra.TravelPackage;
import Extra.WorldMap;
import Util.DFFunctions;
import jade.core.AID;
import jade.core.Agent;
import java.util.ArrayList;
import java.util.List;

public class Station extends Agent {

    /**
     * Variáveis
     */

    private WorldMap map;

    private int ape;
    private double baseRate;
    private int numBikes;
    private Position position;

    private List<TravelPackage> rentHistory;

    /**
     * Setup
     */

    public void setup() {

        Object[] args = this.getArguments();

        //Variáveis Pré-definidas
        this.setMap((WorldMap) args[0]);
        this.setApe((Integer) args[1]);
        this.setBaseRate((Double) args[2]);
        this.setNumBikes((Integer) args[3]);
        this.setPosition((Position) args[4]);

        //Registar o Agente Station
        DFFunctions.registerAgent(this, "Agent Station");

        //Inicialmente o Agente Station não tem historico
        this.rentHistory = new ArrayList<>();

        //Iniciar Behaviors

    }

    /**
     * Construtores
     */

    public Station(WorldMap map, int ape, double baseRate, int numBikes, Position position, List<TravelPackage> rentHistory) {

        this.setMap(map);
        this.setApe(ape);
        this.setBaseRate(baseRate);
        this.setNumBikes(numBikes);
        this.setPosition(position);
        this.setRentHistory(rentHistory);

    }

    /**
     * Getters
     */

    public WorldMap getMap() {

        return this.map.clone();

    }

    public int getApe() {

        return this.ape;

    }

    public double getBaseRate() {

        return this.baseRate;

    }

    public int getNumBikes() {

        return this.numBikes;

    }

    public Position getPosition() {

        return this.position.clone();

    }

    public List<TravelPackage> getRentHistory() {

        List<TravelPackage> res = new ArrayList<>();

        for(TravelPackage tp : this.rentHistory) {

            res.add(tp.clone());

        }

        return res;

    }

    /**
     * Setters
     */

    public void setMap(WorldMap map) {

        this.map = map.clone();

    }

    public void setApe(int ape) {

        this.ape = ape;

    }

    public void setBaseRate(double baseRate) {

        this.baseRate = baseRate;

    }

    public void setNumBikes(int numBikes) {

        this.numBikes = numBikes;

    }

    public void setPosition(Position position) {

        this.position = position.clone();

    }

    public void setRentHistory(List<TravelPackage> rentHistory) {

        this.rentHistory = new ArrayList<>();

        for(TravelPackage tp : rentHistory) {

            this.rentHistory.add(tp.clone());

        }

    }

    /**
     * Clone
     */

    public Station clone() {

        return new Station(this.map, this.ape, this.baseRate, this.numBikes, this.position, this.rentHistory);

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
