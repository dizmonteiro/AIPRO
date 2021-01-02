import Extra.Position;
import Extra.WorldMap;
import Util.MainContainer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

    private static WorldMap map;
    private static MainContainer mc;
    private static List<Position> stationPositions;
    private static List<Position> auxStationPositions;
    private static List<Position> userPositions;

    public static void main(String[] args){

        map = new WorldMap(50);

        try {

            stationPositions = new ArrayList<>();
            auxStationPositions = new ArrayList<>();
            userPositions = new ArrayList<>();

            mc = new MainContainer();

            Thread.sleep(100);
            mc.startInterface(map);

            Thread.sleep(100);
            mc.startManager();

            generatePositions(10000);

            generateStations(25);

            generateUsers(60);

            //generateUsers(50);

            /*
        Position stationa = new Position(10,10);
        Position stationa2 = new Position(11,10);

        Position stationb = new Position(20,20);
        Position stationb2 = new Position(21,20);

        Position stationc = new Position(40,40);
        Position stationc2 = new Position(41,40);

        Position stationd = new Position(13,28);
        Position stationd2 = new Position(14,28);

        Position statione = new Position(32,30);
        Position statione2 = new Position(33,30);

        Position stationf = new Position(40,40);
        Position stationf2 = new Position(41,40);

        Position stationg = new Position(10,12);
        Position stationg2 = new Position(11,12);

        Position stationh = new Position(40,12);
        Position stationh2 = new Position(41,12);

        Position stationi = new Position(25,15);
        Position stationi2 = new Position(26,15);

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

         */



        } catch (InterruptedException e) {

            e.printStackTrace();

        }

    }

    public static void generatePositions(int maxPositions) {

        Random r = new Random();

        for(int i = 0; i< maxPositions; i++) {

            int resultx = r.nextInt(map.getMapSize());
            int resulty = r.nextInt(map.getMapSize());

            stationPositions.add(new Position(resultx,resulty));

        }


    }

    public static void generateStations(int maxStations) throws InterruptedException {

        Random r = new Random();

        for(int i = 0; i < maxStations; i++) {

            int ape = r.nextInt(9);
            int baseRate = r.nextInt(3);
            int numBikes = r.nextInt(25);
            int randomPos = r.nextInt(10000);

            Thread.sleep(100);


            mc.startStation(ape,baseRate,numBikes,stationPositions.get(randomPos).clone());

            Position user = stationPositions.get(randomPos).clone();
            user.xChange(1);

            userPositions.add(user.clone());
            auxStationPositions.add(stationPositions.get(randomPos).clone());

        }

    }

    public static void generateUsers(int maxUsers) throws InterruptedException {

        Random r = new Random();

        for(int i = 0; i < maxUsers; i++) {

            int financialStatus = r.nextInt(10);
            int stubborness = r.nextInt(10);
            int velocity = r.nextInt(10);
            int randomPos1 = r.nextInt(10);
            int randomPos2 = r.nextInt(10);

            Thread.sleep(500);

            mc.startUser(financialStatus,stubborness,velocity,userPositions.get(randomPos1), auxStationPositions.get(randomPos2));

        }

    }

}