package Behaviors.Interface;

import Agents.Interface;
import Extra.InfoPackageFromUserToManager;
import Extra.Position;
import Extra.StationInfo;
import Extra.TravelPackage;
import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import java.util.ArrayList;
import java.util.List;

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

            //Verificar agente e performative da message
            if(agentName.contains("User") && message.getPerformative() == ACLMessage.INFORM){

                //System.out.println();

                try {
                    //Reaproveitar
                    InfoPackageFromUserToManager newPackage = (InfoPackageFromUserToManager) message.getContentObject();

                    Boolean isTraveling = newPackage.isTraveling();
                    Position newUserPos = newPackage.getActualPos();

                    if(!isTraveling){
                        AID agentStation = this.agentInterface.getStationWithPosition(newUserPos);
                    }

                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
}