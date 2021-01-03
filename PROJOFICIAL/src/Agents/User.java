package Agents;

import Behaviors.User.InformCreationUser;
import Behaviors.User.ReceiveInfoU;
import Behaviors.User.UpdatePosition;
import Extra.Position;
import Extra.TravelPackage;
import Util.DFFunctions;
import jade.core.AID;
import jade.core.Agent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class User extends Agent {

    /**
     * Variáveis
     */

    //Variáveis aleatórias que servirão para influenciar as decisões do Agente User
    private int financialStatus; //grau de riqueza, 1-10, quanto mais rico menor será a probabilidade de aceitar os descontos.
    private int stubborness; //grau de teimosia de 1-10, quanto mais teimoso mmenor será a probabilidade de aceitar os descontos.
    private int velocity;

    //Posição atual do Agente User
    private Position actualPosition;

    //Travel Package atual do Agente User
    //Info: - AID agenteUser;
    //      - Position origin;
    //      - Position destination;
    //      - double totalCost;
    //Variável será inicializada com apenas o AID, a Origin e a Destination.
    private TravelPackage actualTPackage;

    //Inicialmente isTraveling = false
    private boolean isTraveling;

    //Inicialmente isMoving = false
    private boolean isMoving;

    //Inicialmente travelHistory estará vazio
    private List<TravelPackage> travelHistory;

    //AID do agent Manager com que o agent User vai comunicar
    private AID agentManager;


    /**
     * Setup
     */

    protected void setup() {

        Object[] args = this.getArguments();

        //Variáveis pré-definidas
        this.setFinancialStatus((Integer) args[0]);
        this.setStubborness((Integer) args[1]);
        this.setVelocity((Integer) args[2]);
        this.setActualPosition((Position) args[3]);


        Position destination = new Position((Position) args[4]);

        this.actualTPackage = new TravelPackage(this.getAID(), this.actualPosition, destination);

        //Inicialmente o Agente User não está a viajar
        this.setTraveling(false);
        this.setMoving(false);

        //Registar o Agente User
        DFFunctions.registerAgent(this, "User");
        this.agentManager = DFFunctions.findSpecificAgent(this,"Manager");

        //Inicialmente ainda não fez nenhuma viajem
        this.travelHistory = new ArrayList<>();

        System.out.println("> User AID: " + this.getAID() + " is ON");

        addBehaviour(new UpdatePosition(this,this.velocity));
        addBehaviour(new ReceiveInfoU(this));
        addBehaviour(new InformCreationUser(this));

    }

    /**
     * Getters
     */

    public int getFinancialStatus() {

        return this.financialStatus;

    }

    public int getStubborness() {

        return this.stubborness;

    }

    public Position getActualPosition() {

        return this.actualPosition.clone();

    }

    public TravelPackage getActualTPackage() {

        return this.actualTPackage.clone();

    }

    public boolean isTraveling() {

        return this.isTraveling;

    }

    public boolean isMoving() {

        return this.isMoving;

    }

    public List<TravelPackage> getTravelHistory() {

        List<TravelPackage> res = new ArrayList<>();

        for(TravelPackage tp : this.travelHistory) {

            res.add(tp.clone());

        }

        return res;

    }

    public AID getAgentManager() {

        return this.agentManager;

    }

    public int getVelocity() {

        return this.velocity;

    }

    /**
     * Setters
     */

    public void setFinancialStatus(int financialStatus) {

        this.financialStatus = financialStatus;

    }

    public void setStubborness(int stubborness) {

        this.stubborness = stubborness;

    }

    public void setActualTPackage(TravelPackage actualTPackage) {

        this.actualTPackage = actualTPackage.clone();

    }

    public void setActualPosition(Position actualPosition) {

        this.actualPosition = actualPosition.clone();

    }

    public void setTraveling(boolean traveling) {

        this.isTraveling = traveling;

    }

    public void setTravelHistory(List<TravelPackage> travelHistory) {

        this.travelHistory = new ArrayList<>();

        for(TravelPackage tp : travelHistory) {

            this.travelHistory.add(tp.clone());

        }

    }

    public void setMoving(boolean moving) {

        isMoving = moving;

    }

    public void setVelocity(int velocity) {

        this.velocity = velocity;

    }

    public void movePlusY() {

        int x = this.actualPosition.getX();
        int y = this.actualPosition.getY();

        y++;

        this.actualPosition.move(x,y);

    }

    public void moveMinusY() {

        int x = this.actualPosition.getX();
        int y = this.actualPosition.getY();

        y--;

        this.actualPosition.move(x,y);

    }

    public void movePlusX() {

        int x = this.actualPosition.getX();
        int y = this.actualPosition.getY();

        x++;

        this.actualPosition.move(x,y);

    }

    public void moveMinusX() {

        int x = this.actualPosition.getX();
        int y = this.actualPosition.getY();

        x--;

        this.actualPosition.move(x,y);

    }

    /**
     * Métodos Auxiliares
     */

    public void turnOff() {

        DFFunctions.removeAgent(this);

    }

    public boolean evaluateProposal(double oldPrice, double newPrice) {

        if(newPrice == 0) {

            return true;

        }

        double discount = Math.abs((newPrice*100)/oldPrice);

        double auxdiscount1 = discount/100;

        int auxdiscount2 = (int) Math.floor(auxdiscount1);

        if(auxdiscount2 >= this.stubborness + this.financialStatus) {

            return true;

        }

        Random r = new Random();

        int odd = this.financialStatus + this.stubborness - auxdiscount2;

        int pick = r.nextInt(odd) + 1;

        if (pick == 1) {

            return true;

        }

        return false;

    }

}
