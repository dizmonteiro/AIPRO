package Behaviors.Interface;

import Agents.Interface;
import Extra.InfoPackageFromUser;
import Extra.Position;
import Extra.StationInfo;
import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class ReceiveInfoI extends CyclicBehaviour{
    //vars

    private Interface agentInterface;


    //Construtores
    public ReceiveInfoI(Interface agentInterface){
        this.setAgentInterface(agentInterface);
    }

    //Sets
    public void setAgentInterface(Interface agentInterface){
        this.agentInterface = agentInterface;
    }


    //Métodos
    public void action(){

        ACLMessage message = this.agentInterface.receive();

        if(message != null){

            //Conteúdo da mensagem
            AID agent = message.getSender();
            String agentName = agent.getLocalName();

            //Info da Station
            if(agentName.contains("Manager") && message.getPerformative() == ACLMessage.INFORM) {

                //Mensagem
                System.out.println("> Interface AID: " + this.agentInterface.getAID() + " has received new StationInfo Package message from Manager ");

                try {

                    //Reaproveitar
                    StationInfo newStationPackage = (StationInfo) message.getContentObject();

                    this.agentInterface.addStation(newStationPackage.clone());

                } catch (Exception e) {
                    e.printStackTrace();
                }

            //Info da Pos do User
            } else if(agentName.contains("Manager") && message.getPerformative() == ACLMessage.INFORM_REF) {

                //Mensagem
                System.out.println("> Interface AID: " + this.agentInterface.getAID() + " has received new User Package message from Manager ");

                try {

                    //Reaproveitar
                    InfoPackageFromUser newUserPackage = (InfoPackageFromUser) message.getContentObject();

                    this.agentInterface.addUser(newUserPackage.clone());

                } catch (Exception e) {
                    e.printStackTrace();
                }

            } else if(agentName.contains("Manager") && message.getPerformative() == ACLMessage.CANCEL) {

                //Mensagem
                System.out.println("> Interface AID: " + this.agentInterface.getAID() + " has received new TURN OFF NOTICE from Manager ");

                try {

                    //Reaproveitar
                    AID userturnoff = (AID) message.getContentObject();

                    this.agentInterface.removeUser(userturnoff);

                } catch (Exception e) {
                    e.printStackTrace();
                }


            } else {

                block();

            }
        }
    }
}