package Extra;

import Agents.User;
import jade.core.AID;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WorldMap {

    /**
     * Variáveis
     */

    private int mapSize;
    private Map<AID, Position> userPositions;
    private Map<AID, StationInfo> stationPositions;

    /**
     * Construtores
     */

    public WorldMap() {

        this.setMapSize(0);
        this.setUserPositions(new HashMap<>());
        this.setStationPositions(new HashMap<>());

    }

    public WorldMap(int size, Map<AID, Position> users, Map<AID, StationInfo> stations) {

        this.setMapSize(size);
        this.setUserPositions(users);
        this.setStationPositions(stations);

    }

    public WorldMap(int size) {

        this.setMapSize(size);
        this.setUserPositions(new HashMap<>());
        this.setStationPositions(new HashMap<>());

    }

    /**
     * Getters
     */

    public int getMapSize() {

        return this.mapSize;

    }

    public Map<AID, Position> getUserPositions() {

        Map<AID, Position> res = new HashMap<>(this.userPositions);

        return res;

    }

    public Map<AID, StationInfo> getStationPositions() {

        Map<AID, StationInfo> res = new HashMap<>(this.stationPositions);

        return res;

    }

    /**
     * Setters
     */

    public void setMapSize(int mapSize) {

        this.mapSize = mapSize;

    }

    public void setUserPositions(Map<AID, Position> userPositions) {

        this.userPositions =  new HashMap<>(userPositions);

    }

    public void setStationPositions(Map<AID, StationInfo> stationPositions) {

        this.stationPositions =  new HashMap<>(stationPositions);

    }

    /**
     * Clone
     */

    public WorldMap clone() {

        return new WorldMap(this.mapSize, this.userPositions, this.stationPositions);

    }

    /**
     * Outros Métodos
     */

    public WorldMap randomMapGenerator(int size) {

        return null;

    }

}
