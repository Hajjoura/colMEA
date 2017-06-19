package SMA;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.ControllerException;

public class JadeContainer {
	public static void main(String[] args) {
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
