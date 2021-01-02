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

    /**
     * Construtores
     */

    public StationInfo(AID agentStation, int stationAPE, Position stationPos) {

        this.agentStation = agentStation;
        this.stationAPE = stationAPE;
        this.stationPos = stationPos.clone();

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

    /**
     * Clone
     */

    public StationInfo clone() {

        return new StationInfo(this.agentStation, this.stationAPE, this.stationPos);

    }
}
