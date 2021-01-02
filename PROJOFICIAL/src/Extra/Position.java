package Extra;

import java.io.Serializable;
import java.util.Random;

public class Position implements Serializable {

    /**
     * Variáveis
     */

    private int x;
    private int y;

    /**
     * Construtores
     */

    public Position() {

        this.setX(0);
        this.setY(0);

    }

    public Position(int x, int y) {

        this.setX(x);
        this.setY(y);

    }

    public Position(Position pos) {

        this.setX(pos.getX());
        this.setY(pos.getY());

    }

    /**
     * Getters
     */

    public int getX() {

        return x;

    }

    public int getY() {

        return y;

    }

    /**
     * Setters
     */

    public void setX(int x) {

        this.x = x;

    }

    public void setY(int y) {

        this.y = y;

    }

    /**
     * Clone
     */

    public Position clone() {

        return new Position(this.x, this.y);

    }

    /**
     * Outros Métodos
     */

    public String toString() {
        return "Position x: " + this.x + " y: " + this.y;
    }

    public void move(int x, int y) {

        setX(x);
        setY(y);

    }

    public boolean equalsPos(Position newp) {

        if(this.x == newp.getX() && this.y == newp.getY()) {

            return true;

        }

        return false;
    }

    public void xChange(int x) {

        this.setX(this.x + x);

    }

    public void yChange(int y) {

        this.setX(this.y + y);

    }


}