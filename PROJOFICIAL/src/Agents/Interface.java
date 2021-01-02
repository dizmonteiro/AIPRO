package Agents;

import Behaviors.Manager.ReceiveInfoM;
import Extra.InfoPackageFromUser;
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
    private PainelInicial pi;
    private JFrame f;

    /**
     * Setup
     */

    public void setup() {

        Object[] args = this.getArguments();

        this.setMap((WorldMap) args[0]);

        DFFunctions.registerAgent(this, "Interface");

        pi = new PainelInicial();
        JScrollPane scroll = new JScrollPane(pi);
        JFrame frame = new JFrame(getClass().getSimpleName());
        pi.setPreferredSize(new Dimension(this.map.getMapSize()*10,this.map.getMapSize()*10));
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
                    revalidate();
                    repaint();
                }
            }

            for (Map.Entry<AID, InfoPackageFromUser> entry : map.getUserPositions().entrySet()) {
                AID k = entry.getKey();
                InfoPackageFromUser v = entry.getValue();
                u_pos = v.getActualPos();
                boolean istrav = v.isTraveling();
                if(istrav) {
                    g.setColor(Color.ORANGE);
                } else {
                    g.setColor(Color.MAGENTA);
                }
                g.fillRect(u_pos.getX() * 10, u_pos.getY() * 10, 10, 10);
                revalidate();
                repaint();
            }

            for (Map.Entry<AID, StationInfo> entry : map.getStationPositions().entrySet()) {
                AID k = entry.getKey();
                StationInfo v = entry.getValue();
                s_pos = v.getStationPos();
                g.setColor(Color.GREEN);
                g.fillRect(s_pos.getX() * 10, s_pos.getY() * 10, 10, 10);
                revalidate();
                repaint();
            }
        }
    }

    public void addStation(StationInfo stationPackage) {

        this.map.addStationInfo(stationPackage.clone());

    }

    public void addUser(InfoPackageFromUser userPackage) {

        this.map.addUserInfo(userPackage.clone());

    }

    public void removeUser(AID user) {

        this.map.removeUserFromMAP(user);

    }

}
