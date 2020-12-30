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
    private Map<String, List<Position>> agentPositions;

    /**
     * Construtores
     */

    public WorldMap() {

        this.setMapSize(0);
        this.setAgentPositions(new HashMap<>());

    }

    public WorldMap(int mapSize, Map<String, List<Position>> agentPositions) {

        this.setMapSize(mapSize);
        this.setAgentPositions(agentPositions);

    }

    /**
     * Getters
     */

    public int getMapSize() {

        return this.mapSize;

    }

    public Map<String, List<Position>> getAgentPositions() {

        Map<String, List<Position>> res = new HashMap<>(this.agentPositions);

        return res;

    }

    /**
     * Setters
     */

    public void setMapSize(int mapSize) {

        this.mapSize = mapSize;

    }

    public void setAgentPositions(Map<String, List<Position>> agentPositions) {

        this.agentPositions =  new HashMap<>(agentPositions);

    }

    /**
     * Clone
     */

    public WorldMap clone() {

        return new WorldMap(this.mapSize, this.agentPositions);

    }

    /**
     * Outros Métodos
     */

    public WorldMap randomMapGenerator(int size) {

        return null;

    }

}
