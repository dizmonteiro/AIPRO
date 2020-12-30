import Extra.Position;
import Extra.WorldMap;
import Util.MainContainer;

import java.util.List;

public class Main {

    private static WorldMap map;
    private static MainContainer mc;

    public static void main(String[] args){

        mc = new MainContainer();
        //mc.startInterface(map);


        Position stationP = new Position(10,10);

        Position userP = new Position(4,4);

        map = null;

        try {

            Thread.sleep(1000);
            mc.startManager(map);

            Thread.sleep(1000);
            mc.startUser(map,2,2,userP,stationP);

            Thread.sleep(1000);
            mc.startStation(map,6,2,10,stationP);


        } catch (Exception e) {

            e.printStackTrace();

        }


    }

}