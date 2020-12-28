import Extra.Position;
import Extra.WorldMap;
import Util.MainContainer;

import java.util.List;

public class Main {

    private WorldMap map;
    private MainContainer mc;

    public void main(String[] args){

        this.mc = new MainContainer();
        this.mc.startInterface(map);

    }

    public void run() {

        List<Position> agentPositions;
        int id = 0;

        this.mc.startManager(map);

    }
}