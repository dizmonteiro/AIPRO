package Agents;

import Behaviors.Manager.ReceiveInfoM;
import Extra.Position;
import Extra.StationInfo;
import Extra.WorldMap;
import Util.DFFunctions;
import jade.core.AID;
import jade.core.Agent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

public class Manager extends Agent {

    /**
     * Variáveis
     */

    //Mapa Global com Agents
    private WorldMap map;

    //Info das APE de todas as Stations
    private Map<AID, StationInfo> globalStations;

    /**
     * Setup
     */

    protected void setup() {

        Object[] args = this.getArguments();

        //RECOLHER VARIÁVEIS PRÉ-DEFINIDAS
        this.setMap((WorldMap) args[0]);

        //REGISTAR AGENTE MANAGER
        DFFunctions.registerAgent(this, "Manager");

        //INICIALMENTE A LISTA DE AGENTES STATION VAI ESTAR VAZIA
        this.globalStations = new HashMap<>();

        System.out.println("> Manager AID: " + this.getAID() + " is ON");

        //INICIAR BEHAVIORS
        addBehaviour(new ReceiveInfoM(this));

    }

    /**
     * Getters
     */

    public WorldMap getMap() {

        return this.map.clone();

    }

    public Map<AID, StationInfo> getGlobalStationsInfo() {

        Map<AID, StationInfo> res = new HashMap<>(this.globalStations);

        return res;

    }

    /**
     * Setters
     */

    public void setMap(WorldMap map) {

        this.map = map.clone();

    }

    public void setGlobalStationsInfo(Map<AID, StationInfo> globalStationsInfo) {

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

            if(isInRange(userPosition, v.getStationPos(), v.getStationAPE())) {

                x[0] = true;

            }

        });

        return x[0];

    }

    //Devolve uma lista com todas as estações em que o User tem no seu range
    public List getNearStations(Position userPosition) {

        List<AID> nearStations = new ArrayList<>();

        this.globalStations.forEach((k,v) -> {

            if(isInRange(userPosition, v.getStationPos(), v.getStationAPE())) {

                nearStations.add(k);

            }

        });

        return nearStations;

    }

    //Adiciona um agente estação recém criado ao map de globalStations
    public void addStationInfo(AID agentStation, StationInfo stationInfo) {

        //1. Adicionamos um clone do pacote que recebemos
        this.globalStations.put(agentStation, stationInfo.clone());

    }

    //Vai buscar o AID da Station com determinada Position
    public AID getStationWithPosition(Position stationPos) {

        List<AID> agentStation = new ArrayList<>();

        this.globalStations.forEach((k,v) -> {

            if(v.getStationPos().equalsPos(stationPos)) {

                agentStation.add(k);

            }

        });

        return agentStation.get(0);
    }
}
