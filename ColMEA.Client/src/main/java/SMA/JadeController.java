package SMA;

import Agent.*;

public class JadeController {

	private DesignAgent DesignAgent ; 
	
	private CoordinatorAgent CoordinatorAgent ;

	
	
	public JadeController() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DesignAgent getDesignAgent() {
		return DesignAgent;
	}

	public void setDesignAgent(DesignAgent designAgent) {
		DesignAgent = designAgent;
	}

	public CoordinatorAgent getCoordinatorAgent() {
		return CoordinatorAgent;
	}

	public void setCoordinatorAgent(CoordinatorAgent coordinatorAgent) {
		CoordinatorAgent = coordinatorAgent;
	}

	public JadeController(DesignAgent designAgent,
			CoordinatorAgent coordinatorAgent) {
		super();
		DesignAgent = designAgent;
		CoordinatorAgent = coordinatorAgent;
	}
	
	
}
