package Extra;

import jade.core.AID;

import java.io.Serializable;

public class StationInfo implements Serializable {

    /**
     * Variáveis
     */

    private AID agentStation;
    private int stationAPE;
    private Position stationPos;
    private int numBikes;

    /**
     * Construtores
     */

    public StationInfo(AID agentStation, int stationAPE, Position stationPos, int numBikes) {

        this.agentStation = agentStation;
        this.stationAPE = stationAPE;
        this.stationPos = stationPos.clone();
        this.numBikes = numBikes;

    }

    /**
     * Getters
     */

    public int getStationAPE() {

        return this.stationAPE;

    }

    public Position getStationPos() {

        return this.stationPos;

    }

    public AID getAgentStation() {

        return this.agentStation;

    }

    public int getNumBikes() {

        return this.numBikes;

    }

    /**
     * Clone
     */

    public StationInfo clone() {

        return new StationInfo(this.agentStation, this.stationAPE, this.stationPos, this.numBikes);

    }
}
