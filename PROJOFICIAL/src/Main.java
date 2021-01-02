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


            /*
            Random r = new Random();

            generatePositions(5000,r);

            generateStations(20,r);

            generateUsers(30,r);*/



            Position station1 = new Position(5,5);
            Position station1b = new Position(6,5);
            Position station2 = new Position(20,20);
            Position station3 = new Position(40,40);

            Thread.sleep(100);
            mc.startStation(2,5,7,station1);

            Thread.sleep(100);
            mc.startStation(4,5,10,station2);

            Thread.sleep(100);
            mc.startStation(3,5,0,station3);

            Thread.sleep(100);
            mc.startUser(10,10,3,station1b,station3);

            Thread.sleep(8000);
            mc.startUser(10,1,3,station1b,station3);

            Thread.sleep(8000);
            mc.startUser(1,10,3,station1b,station3);

            Thread.sleep(8000);
            mc.startUser(1,1,3,station1b,station3);

            Thread.sleep(8000);
            mc.startUser(7,7,3,station1b,station3);

            Thread.sleep(8000);
            mc.startUser(5,5,3,station1b,station3);

            Thread.sleep(8000);
            mc.startUser(3,3,3,station1b,station3);



        } catch (InterruptedException e) {

            e.printStackTrace();

        }

    }

    public static void generatePositions(int maxPositions, Random r) {

        for(int i = 0; i< maxPositions; i++) {

            int resultx = r.nextInt(map.getMapSize());
            int resulty = r.nextInt(map.getMapSize());

            stationPositions.add(new Position(resultx,resulty));

        }


    }

    public static void generateStations(int maxStations, Random r) throws InterruptedException {


        for(int i = 0; i < maxStations; i++) {

            int ape = r.nextInt(4) + 1;
            int baseRate = r.nextInt(3) + 1;
            int numBikes = r.nextInt(20) + 5;
            int randomPos = r.nextInt(5000);

            Thread.sleep(100);


            mc.startStation(ape,baseRate,numBikes,stationPositions.get(randomPos).clone());

            Position user = stationPositions.get(randomPos).clone();
            user.xChange(1);

            userPositions.add(user.clone());
            auxStationPositions.add(stationPositions.get(randomPos).clone());

        }

    }

    public static void generateUsers(int maxUsers, Random r) throws InterruptedException {


        for(int i = 0; i < maxUsers; i++) {

            int financialStatus = r.nextInt(10) + 1;
            int stubborness = r.nextInt(10) + 1;
            int velocity = r.nextInt(6) + 2;
            int randomPos1 = r.nextInt(20);
            int randomPos2 = r.nextInt(20);

            Thread.sleep(700);

            mc.startUser(financialStatus,stubborness,velocity,userPositions.get(randomPos1), auxStationPositions.get(randomPos2));

        }

    }

}