package Extra;

import java.io.Serializable;

public class InfoPackageFromUserToManager implements Serializable {

    /**
     * Variáveis
     */

    private boolean isTraveling;
    private TravelPackage tp;
    private Position actualPos;

    /**
     * Construtores
     */

    public InfoPackageFromUserToManager(boolean isTraveling, TravelPackage tp, Position actualPos) {

        this.setTraveling(isTraveling);
        this.setTp(tp);
        this.setActualPos(actualPos);

    }

    /**
     * Getters
     */

    public TravelPackage getTp() {

        return this.tp.clone();

    }

    public boolean isTraveling() {

        return this.isTraveling;

    }

    public Position getActualPos() {

        return this.actualPos.clone();

    }

    /**
     * Setters
     */

    public void setTraveling(boolean traveling) {

        this.isTraveling = traveling;

    }

    public void setTp(TravelPackage tp) {

        this.tp = tp.clone();

    }

    public void setActualPos(Position actualPos) {

        this.actualPos = actualPos.clone();

    }


    /**
     * Clone
     */

    public InfoPackageFromUserToManager clone() {

        return new InfoPackageFromUserToManager(this.isTraveling, this.tp, this.actualPos);

    }
}
