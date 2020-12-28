package Agents;

import Behaviors.AnswerUser;
import Behaviors.ReceivePosition;
import Extra.Position;
import Extra.WorldMap;
import Util.DFFunctions;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

import java.util.HashMap;
import java.util.Map;

public class Manager extends Agent {

    /**
     * Variáveis
     */

    private WorldMap map;
    private Map<AID, User> globalUsers;
    private Map<AID, Station> globalStations;

    /**
     * Setup
     */

    public void setup() {

        Object[] args = this.getArguments();

        this.setMap((WorldMap) args[0]);

        DFFunctions.registerAgent(this, "Agent Manager");

        this.globalUsers = new HashMap<>();
        this.globalStations = new HashMap<>();

        addBehaviour(new ReceivePosition());

    }

    /**
     * Construtores
     */

    public Manager(WorldMap map, Map<AID, User> globalUsers, Map<AID, Station> globalStations) {

        this.setMap(map);
        this.setGlobalUsers(globalUsers);
        this.setGlobalStations(globalStations);

    }

    /**
     * Getters
     */

    public WorldMap getMap() {

        return this.map.clone();

    }

    public Map<AID, Station> getGlobalStations() {

        Map<AID, Station> res = new HashMap<AID, Station>(this.globalStations);

        return res;

    }

    public Map<AID, User> getGlobalUsers() {

        Map<AID, User> res = new HashMap<AID, User>(this.globalUsers);

        return res;

    }

    /**
     * Setters
     */

    public void setMap(WorldMap map) {

        this.map = map.clone();

    }

    public void setGlobalStations(Map<AID, Station> globalStations) {

        this.globalStations = new HashMap<AID, Station>(globalStations);

    }

    public void setGlobalUsers(Map<AID, User> globalUsers) {

        this.globalUsers = new HashMap<AID, User>(globalUsers);

    }

    /**
     * Clone
     */

    public Manager clone() {

        return new Manager(this.map, this.globalUsers, this.globalStations);

    }

    /**
     * Métodos
     */

    public void updateUserPos(AID agent, Position newPos) {

        this.globalUsers.get(agent).setActualPosition(newPos);

    }

    public boolean isNearStation(AID agent) {

        Position agentPosition = this.globalUsers.get(agent).getActualPosition().clone();

        return false;

    }

    /**
     * Behaviors Receive & Answer
     */

    public class ReceivePosition extends CyclicBehaviour {

        public void action() {

            ACLMessage message = receive();

            if(message != null) {

                AID agent = message.getSender();
                String agentName = agent.getLocalName();

                //Caso seja um Agent User a mandar a posição
                if (agentName.contains("User") && message.getPerformative() == ACLMessage.INFORM) {

                    try {

                        Position newUserPos = (Position) message.getContentObject();
                        updateUserPos(agent, newUserPos);

                        //Caso o User esteja dentro de uma APE vamos responder
                        if(isNearStation(agent)) {

                            addBehaviour(new AnswerUser());

                        }

                    } catch (Exception e) {

                        e.printStackTrace();

                    }

                    //Caso seja um Agent Interface a pedir informação
                } else {

                    block();

                }

            }

        }

    }

    public class AnswerUser extends CyclicBehaviour {

        public void action() {

        }

    }

}
