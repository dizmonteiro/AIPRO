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

    public Position(int x, int y) {
        super();
        this.x = x;
        this.y = y;
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

    public void move(int x, int y) {
        setX(x);
        setY(y);
    }

    public Position randomPos(int max) {

        Random r = new Random();

        int resultx = r.nextInt(max);
        int resulty = r.nextInt(max);

        return new Position(resultx, resulty);
    }


}