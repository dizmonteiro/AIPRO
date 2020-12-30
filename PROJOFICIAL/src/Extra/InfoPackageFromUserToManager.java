package Extra;

import java.io.Serializable;

public class InfoPackageFromUserToManager implements Serializable {

    /**
     * Variáveis
     */

    private boolean isTraveling;
    private Position actualPos;

    private TravelPackage actualTravelPackage;

    /**
     * Construtores
     */

    public InfoPackageFromUserToManager(boolean isTraveling, Position actualPos, TravelPackage newTP) {

        this.setTraveling(isTraveling);
        this.setActualPos(actualPos);
        this.setTravelPackage(newTP);

    }

    /**
     * Getters
     */

    public TravelPackage getTravelPackage() {

        return this.actualTravelPackage.clone();

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

    public void setTravelPackage(TravelPackage newTP) {

        this.actualTravelPackage = newTP.clone();

    }

    public void setActualPos(Position actualPos) {

        this.actualPos = actualPos.clone();

    }


    /**
     * Clone
     */

    public InfoPackageFromUserToManager clone() {

        return new InfoPackageFromUserToManager(this.isTraveling, this.actualPos, this.actualTravelPackage);

    }
}
