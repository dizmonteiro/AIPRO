package Extra;

import java.io.Serializable;

public class StationInfo implements Serializable {

    /**
     * Variáveis
     */

    private int stationAPE;
    private Position stationPos;

    /**
     * Construtores
     */

    public StationInfo(int stationAPE, Position stationPos) {

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

    /**
     * Clone
     */

    public StationInfo clone() {

        return new StationInfo(this.stationAPE, this.stationPos);

    }
}
