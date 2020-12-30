package Agents;

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

    private WorldMap map;

    private int financialStatus; //grau de riqueza, 1-10, quanto mais rico menor será a probabilidade de aceitar os descontos.
    private int stubborness; //grau de teimosia de 1-10, quanto mais teimoso mmenor será a probabilidade de aceitar os descontos.

    private Position actualPosition;

    private TravelPackage actualTPackage;
    private boolean isTraveling;
    private List<TravelPackage> travelHistory;

    /**
     * Setup
     */

    public void setup() {

        Object[] args = this.getArguments();

        //Variáveis pré-definidas
        this.setMap((WorldMap) args[0]);
        this.setFinancialStatus((Integer) args[1]);
        this.setStubborness((Integer) args[2]);
        this.setActualPosition((Position) args[3]);

        //Registar o Agente User
        DFFunctions.registerAgent(this, "Agent User");

        //Inicialmente o Agente User não está a viajar e ainda não fez nenhuma viajem
        this.actualTPackage = new TravelPackage();
        this.setTraveling(false);
        this.travelHistory = new ArrayList<>();

        //Iniciar Behaviors
        //É gerado numa estação aleatória
        //Informa Manager que foi criado
        //Recebe o AID das estações do Manager
        //Manda um pedido de aluguer da bike à Estação. O pedido vai criar o TravelPackage que vai ter a origem, o destino aleatório e mais dados
        //Começa o movimento e o behaviour UpdatePosition

    }

    /**
     * Construtores
     */

    public User(WorldMap map, int financialStatus, int stubborness, Position actualPosition, TravelPackage actualTPackage, boolean isTraveling, List<TravelPackage> travelHistory) {

        this.setMap(map);
        this.setFinancialStatus(financialStatus);
        this.setStubborness(stubborness);
        this.setActualPosition(actualPosition);
        this.setActualTPackage(actualTPackage);
        this.setTraveling(isTraveling);
        this.setTravelHistory(travelHistory);

    }

    /**
     * Getters
     */

    public WorldMap getMap() {

        return this.map.clone();

    }

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

    public List<TravelPackage> getTravelHistory() {

        List<TravelPackage> res = new ArrayList<>();

        for(TravelPackage tp : this.travelHistory) {

            res.add(tp.clone());

        }

        return res;

    }

    /**
     * Setters
     */

    public void setMap(WorldMap map) {

        this.map = map.clone();

    }

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

    /**
     * Clone
     */

    public User clone() {

        return new User(this.map, this.financialStatus, this.stubborness, this.actualPosition, this.actualTPackage, this.isTraveling, this.travelHistory);

    }

    /**
     * Métodos Auxiliares
     */


}
