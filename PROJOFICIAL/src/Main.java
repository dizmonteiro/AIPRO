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


        Position stationa = new Position(10,10);
        Position stationa2 = new Position(11,10);

        Position stationb = new Position(20,20);
        Position stationb2 = new Position(21,20);

        Position stationc = new Position(40,50);
        Position stationc2 = new Position(41,50);

        Position stationd = new Position(13,28);
        Position stationd2 = new Position(14,28);

        Position statione = new Position(32,30);
        Position statione2 = new Position(33,30);

        Position stationf = new Position(40,40);
        Position stationf2 = new Position(41,40);

        Position stationg = new Position(10,12);
        Position stationg2 = new Position(11,12);

        Position stationh = new Position(50,12);
        Position stationh2 = new Position(51,12);

        Position stationi = new Position(25,15);
        Position stationi2 = new Position(26,15);

        map = new WorldMap(100);

        try {

            Thread.sleep(000);
            mc.startInterface(map);

            Thread.sleep(1000);
            mc.startManager();

            Thread.sleep(100);
            mc.startStation(2,2,2, stationa);

            Thread.sleep(100);
            mc.startStation(3,2,2, stationb);

            Thread.sleep(100);
            mc.startStation(3,2,2, stationc);

            Thread.sleep(100);
            mc.startStation(2,2,2, stationd);

            Thread.sleep(100);
            mc.startStation(3,2,2, statione);

            Thread.sleep(100);
            mc.startStation(3,2,2, stationf);

            Thread.sleep(100);
            mc.startStation(2,2,2, stationg);

            Thread.sleep(100);
            mc.startStation(3,2,2, stationh);

            Thread.sleep(100);
            mc.startStation(3,2,2, stationi);

            Thread.sleep(100);
            mc.startUser(5,5, 10, stationa2, stationf);

            Thread.sleep(100);
            mc.startUser(5,5, 10, stationf2, statione);

            Thread.sleep(100);
            mc.startUser(5,5, 10, stationc2, stationa);

            Thread.sleep(100);
            mc.startUser(5,5, 10, stationb2, stationd);

            Thread.sleep(100);
            mc.startUser(5,5, 10, stationg2, stationd);

            Thread.sleep(100);
            mc.startUser(5,5, 10, stationh2, stationd);

            Thread.sleep(100);
            mc.startUser(5,5, 10, stationi2, stationd);

            Thread.sleep(100);
            mc.startUser(5,5, 10, statione2, stationi);

            Thread.sleep(100);
            mc.startUser(5,5, 10, stationd2, stationh);


        } catch (Exception e) {

            e.printStackTrace();

        }


    }

}