package SMA;

import com.supmeca.colMEA.business.StatisticsServiceLocal;

import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.util.ExtendedProperties;
import jade.util.leap.Properties;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.ControllerException;
public class MainContainer {
	public static void main(String[] args) {

		try {
			Runtime rt = Runtime.instance();
			// Creation des proprietes 
			Properties p = new ExtendedProperties();
			//pour afficher l'interface graphique
			p.setProperty("gui", "true");
			
			ProfileImpl pc = new ProfileImpl(p);
			
			AgentContainer container = rt.createMainContainer(pc);
			
			AgentController agentController = container.createNewAgent("CoordinatorAgent", "Agent.CoordinatorAgent", new Object[]{});
			agentController.start();
			
			//Get number of Partition	
			StatisticsServiceLocal StatisticEjb =null;

		    int nbrPartition;
		    nbrPartition = StatisticEjb.NumberPartitions();
		  
		    // Send Target
		    for (int i = 0; i < nbrPartition; i++) {
		    	AgentController agentControllern = container.createNewAgent("DesignAgent"+i, "Agent.DesignAgent", new Object[]{"Target"});
				agentControllern.start();
		    	}
			
		} catch (ControllerException e) {
			e.printStackTrace();
		}
	}
	
	public void Container(int i) {
		try {
			Runtime rt = Runtime.instance();
			ProfileImpl pc = new ProfileImpl(false); // false pour dire que ce n'est pas MainContainer
			pc.setParameter(ProfileImpl.MAIN_HOST, "localhost");
			AgentContainer ac = rt.createAgentContainer(pc);
							
			//Deployer un agent
			AgentController agentController = ac.createNewAgent("DesignAgent", "Agent.DesignAgent", new Object[]{"XML"});
			agentController.start();
										
		} catch (ControllerException e) {
			e.printStackTrace();
		}
		
}
}
