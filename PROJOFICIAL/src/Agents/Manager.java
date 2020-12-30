package Agents;

import Behaviors.Manager.ReceiveInfoM;
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
    private Map<AID, Station> globalStations;

    /**
     * Setup
     */

    protected void setup() {

        Object[] args = this.getArguments();

        //Variáveis Pré-Definidas
        //this.setMap((WorldMap) args[0]);

        //WorldMap news = (WorldMap) args[0];

        //Registar Agente Manager
        DFFunctions.registerAgent(this, "Manager");

        //Inicialmente o Agente Manager não vai saber que Agentes estão ativos
        this.globalStations = new HashMap<>();

        //Iniciar Behaviors
        addBehaviour(new ReceiveInfoM(this));

    }

    /**
     * Getters
     */

    public WorldMap getMap() {

        return this.map.clone();

    }

    public Map<AID, Station> getGlobalStations() {

        Map<AID, Station> res = new HashMap<>(this.globalStations);

        return res;

    }

    /**
     * Setters
     */

    public void setMap(WorldMap map) {

        this.map = map.clone();

    }

    public void setGlobalStations(Map<AID, Station> globalStations) {

        this.globalStations = new HashMap<>(globalStations);

    }

    /**
     * Métodos Auxiliares
     */

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
    public boolean isNearStation(Position userPosition) {

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
    public List getNearStations(Position userPosition) {

        List<AID> nearStations = new ArrayList<>();

        this.globalStations.forEach((k,v) -> {

            if(isInRange(userPosition, v.getPosition(), v.getApe())) {

                nearStations.add(k);

            }

        });

        return nearStations;

    }

    public void addStation(AID agentStation, Station station) {

        this.globalStations.put(agentStation, station.clone());

    }
}
