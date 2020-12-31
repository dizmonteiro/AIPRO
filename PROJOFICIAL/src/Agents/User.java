package Agents;

import Behaviors.User.InformCreationUser;
import Behaviors.User.ReceiveInfoU;
import Extra.Position;
import Extra.TravelPackage;
import Extra.WorldMap;
import Util.DFFunctions;
import jade.core.AID;
import jade.core.Agent;
import java.util.ArrayList;
import java.util.List;

public class User extends Agent {

    /**
     * Variáveis
     */

    //Mapa global com agentes
    //private WorldMap map;

    //Variáveis aleatórias que servirão para influenciar as decisões do Agente User
    private int financialStatus; //grau de riqueza, 1-10, quanto mais rico menor será a probabilidade de aceitar os descontos.
    private int stubborness; //grau de teimosia de 1-10, quanto mais teimoso mmenor será a probabilidade de aceitar os descontos.

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
        //this.setMap((WorldMap) args[0]);
        this.setFinancialStatus((Integer) args[0]);
        this.setStubborness((Integer) args[1]);
        this.setActualPosition((Position) args[2]);

        Position destination = new Position((Position) args[3]);

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

}
