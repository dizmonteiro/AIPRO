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


        Position stationO = new Position(4,4);

        Position stationD = new Position(10,10);

        Position userP = new Position(4,4);

        map = new WorldMap();

        try {

            Thread.sleep(1000);
            mc.startManager(map);

            Thread.sleep(1000);
            mc.startStation(map,10,2,2, stationO);

            Thread.sleep(1000);
            mc.startStation(map,10,2,2, stationD);

            Thread.sleep(1000);
            mc.startUser(map,5,5, userP, stationD);


        } catch (Exception e) {

            e.printStackTrace();

        }


    }

}