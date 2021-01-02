package Agents;

import Behaviors.Manager.ReceiveInfoM;
import Extra.Position;
import Extra.StationInfo;
import Extra.WorldMap;
import Util.DFFunctions;
import jade.core.AID;
import jade.core.Agent;
import Behaviors.Interface.ReceiveInfoI;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.*;

public class Interface extends Agent {

    /**
     * Variáveis
     */

    private WorldMap map;
    private Map<AID, Position> userPositions;
    private PainelInicial pi;
    private JFrame f;
    //Info das APE de todas as Stations
    private Map<AID, StationInfo> globalStations;
    /**
     * Setup
     */

    public void setup() {

        Object[] args = this.getArguments();

        this.setMap((WorldMap) args[0]);

        DFFunctions.registerAgent(this, "Interface");

        this.userPositions = new HashMap<>();
        //INICIALMENTE A LISTA DE AGENTES STATION VAI ESTAR VAZIA
        this.globalStations = new HashMap<>();

        pi = new PainelInicial();
        JScrollPane scroll = new JScrollPane(pi);
        JFrame frame = new JFrame(getClass().getSimpleName());
        pi.setPreferredSize(new Dimension(500*10,500*10));
        frame.add(scroll);
        frame.pack();
        frame.setVisible(true);
        f = new JFrame("Interface");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //INICIAR BEHAVIORS
        addBehaviour(new ReceiveInfoI(this));
    }

    /**
     * Getters
     */

    public WorldMap getMap() {

        return this.map.clone();

    }

    /**
     * Setters
     */

    public void setMap(WorldMap map) {

        this.map = map.clone();

    }

    /**
     * Métodos Auxiliares
     */
    //Vai buscar o AID da Station com determinada Position
    public AID getStationWithPosition(Position stationPos) {

        List<AID> agentStation = new ArrayList<>();

        this.globalStations.forEach((k,v) -> {

            if(v.getStationPos().equalsPos(stationPos)) {

                agentStation.add(k);

            }

        });

        return agentStation.get(0);
    }


    /**
     * Behaviors
     */
    public class PainelInicial extends JPanel {
        public void paintComponent(Graphics g){
            int x, y;
            Position s_pos;
            Position u_pos;
            super.paintComponent(g);
            for (int row = 0; row < map.getMapSize(); row++) {
                for (int col = 0; col < map.getMapSize(); col++) {
                    x = row * 10;
                    y = col * 10;
                    g.drawRect(x, y, 10, 10);
                }
            }

            for (Map.Entry<AID, Position> entry : userPositions.entrySet()) {
                AID k = entry.getKey();
                Position v = entry.getValue();
                g.setColor(Color.ORANGE);
                g.fillRect(v.getX() * 10, v.getY() * 10, 10, 10);
            }

            for (Map.Entry<AID, StationInfo> entry : globalStations.entrySet()) {
                AID k = entry.getKey();
                StationInfo v = entry.getValue();
                s_pos = v.getStationPos();
                g.setColor(Color.GREEN);
                g.fillRect(s_pos.getX() * 10, s_pos.getY() * 10, 10, 10);
            }
        }
    }




}
