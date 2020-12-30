package Agents;

import Behaviors.Station.InformCreationStation;
import Behaviors.Station.ReceiveInfoS;
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

    //Mapa global com agentes
    private WorldMap map;

    //Variável ape, raio do range da estação
    private int ape;

    //Variável baseRate unidade de valor a pagar pela distancia
    private int baseRate;

    //numBikes disponiveis
    private int numBikes;

    //Posição da estação
    private Position position;

    //Registo do TravelPackage de todas as bicicletas que forem deixadas na estação
    private List<TravelPackage> rentHistory;

    //AID do agente Manager
    private AID agentManager;

    /**
     * Setup
     */

    protected void setup() {

        Object[] args = this.getArguments();

        //RECOLHER VARIÁVEIS PRÉ-DEFINIDAS
        this.setMap((WorldMap) args[0]);
        this.setApe((Integer) args[1]);
        this.setBaseRate((Integer) args[2]);
        this.setNumBikes((Integer) args[3]);
        this.setPosition((Position) args[4]);

        //REGISTAR O AGENTE STATION
        DFFunctions.registerAgent(this, "Station");
        this.agentManager = DFFunctions.findSpecificAgent(this,"Manager");

        //INICIAR RENT HISTORY
        this.rentHistory = new ArrayList<>();

        System.out.println("> Station AID: " + this.getAID() + " is ON");

        //INICIAR BEHAVIORS
        addBehaviour(new InformCreationStation(this));
        //addBehaviour(new ReceiveInfoS(this));

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

    public int getBaseRate() {

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

    public AID getAgentManager() {

        return this.agentManager;

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

    public void setBaseRate(int baseRate) {

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
     * Métodos Auxiliares
     */

    public void removeBike() {

        this.numBikes--;

    }

    public void addBike() {

        this.numBikes++;

    }

    public void addTravelPackage(TravelPackage newTP) {

        this.rentHistory.add(newTP.clone());

    }

    public double calculateDistance(Position origin, Position destination) {

        double distance = 0;

        distance = Math.sqrt((destination.getX()-origin.getX())^2 + (destination.getY()-origin.getY())^2);

        return distance;

    }

    /**
     * FALTA FAZER
     */

    public double calculateTotalCost(double distance) {

        return distance * this.baseRate;

    }

    /**
     * FALTA FAZER
     */

    public double calculateDiscount(double distance, double precoantigo) {

        return precoantigo - (1/numBikes);

    }

}
