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

        Position stationM = new Position(10,10);

        Position stationD = new Position(15,10);

        map = new WorldMap(100);

        try {

            Thread.sleep(1000);
            mc.startManager();

            Thread.sleep(1000);
            mc.startStation(2,2,2, stationO);

            //Thread.sleep(1000);
            ///mc.startStation(3,2,2, stationM);

            Thread.sleep(1000);
            mc.startStation(3,2,2, stationD);

            Thread.sleep(1000);
            mc.startUser(5,5, 10, stationO, stationD);


        } catch (Exception e) {

            e.printStackTrace();

        }


    }

}