package Util;

import Extra.Position;
import jade.core.Runtime;
import Extra.WorldMap;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;

public class MainContainer {

    private int totalAgents = 0;
    private int totalManagers = 0;
    private int totalInterfaces = 0;
    private int totalUsers = 0;
    private int totalStations = 0;
    private Runtime rt;
    private ContainerController container;

    public MainContainer() {

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        this.initMainContainerInPlatform("localhost", "9888", "MainContainer");

    }

    public ContainerController initContainerInPlatform(String host, String port, String containerName) {

        // Get the JADE runtime interface (singleton)
        this.rt = Runtime.instance();

        // Create a Profile, where the launch arguments are stored
        Profile profile = new ProfileImpl();
        profile.setParameter(Profile.CONTAINER_NAME, containerName);
        profile.setParameter(Profile.MAIN_HOST, host);
        profile.setParameter(Profile.MAIN_PORT, port);
        // create a non-main agent container
        ContainerController container = rt.createAgentContainer(profile);

        return container;

    }

    public void initMainContainerInPlatform(String host, String port, String containerName) {

        // Get the JADE runtime interface (singleton)
        this.rt = Runtime.instance();

        // Create a Profile, where the launch arguments are stored
        Profile prof = new ProfileImpl();
        prof.setParameter(Profile.CONTAINER_NAME, containerName);
        prof.setParameter(Profile.MAIN_HOST, host);
        prof.setParameter(Profile.MAIN_PORT, port);
        prof.setParameter(Profile.MAIN, "true");
        prof.setParameter(Profile.GUI, "false");

        // create a main agent container
        this.container = rt.createMainContainer(prof);
        rt.setCloseVM(true);

    }

    public void startAgentInPlatform(String name, String classpath, Object[] args) {

        try {
            AgentController ac = container.createNewAgent(name, classpath, args);
            ac.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void startManager(){
        this.startAgentInPlatform("Manager " + totalManagers++, "Agents.Manager", new Object[] {});
    }

    public void startUser(Integer financialStatus, Integer stubborness, Integer velocity, Position actualPosition, Position setDestination){
        this.startAgentInPlatform("User "+ totalUsers++, "Agents.User", new Object[] {financialStatus, stubborness, velocity, actualPosition, setDestination});
    }

    public void startStation(Integer stationAPE, Integer stationBaseRate, Integer numBikes, Position position) {
        this.startAgentInPlatform("Station " + totalStations++, "Agents.Station", new Object[] {stationAPE, stationBaseRate, numBikes, position});
    }

    public void startInterface(WorldMap map) {
        this.startAgentInPlatform("Interface", "Agents.Interface", new Object[] {map});
    }
}