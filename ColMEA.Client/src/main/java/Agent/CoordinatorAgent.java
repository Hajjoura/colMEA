package Agent;


import java.util.Hashtable;
import java.util.Map;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.supmeca.colMEA.business.PartitionServiceLocal;
import com.supmeca.colMEA.business.SetServiceLocal;
import com.supmeca.colMEA.business.StatisticsServiceLocal;
import com.supmeca.colMEA.business.StatisticsServiceRemote;
import com.supmeca.colMEA.business.VariablesServiceLocal;
import com.supmeca.colMEA.domain.Variable;

import SMA.JadeController;
import jade.core.AID;
import jade.core.Agent;
import jade.core.Location;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.ParallelBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.gui.GuiAgent;
import jade.gui.GuiEvent;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class CoordinatorAgent extends GuiAgent{

	@PersistenceContext
	private EntityManager em;

	Variable Variable;

	private int cmpt ;
	// The catalogue of Targets for share 
    private Hashtable catalogue;
	
	private Variable shared;
	
	
    public Variable getSeller() {
        return shared;
    }

    public  void setController(JadeController jadeController) {
        this.guijade = jadeController;
    }

    public JadeController getController() {
        return guijade;
    }
	
    private JadeController guijade ;


	// Setup contient les comportements de l'agent
	@Override
	protected void setup() {
		
		guijade = new JadeController() ;
		guijade.setCoordinatorAgent(this);
	
		System.out.println("---------------------------------------");
		System.out.println("----------------Agent CA----------------");
		System.out.println("---------------------------------------");
		

		
		VariablesServiceLocal VariableEjb;
		SetServiceLocal SetEjb;
		StatisticsServiceLocal StatisticEjb = null ;
		PartitionServiceLocal PartitionEjb;
			
	

			ACLMessage msg=new ACLMessage(ACLMessage.PROPOSE);
			
			//Get number of Partition	    
		  
		    int nbrPartition;
		    nbrPartition = StatisticEjb.NumberPartitions();
		    // Send Target
		    for (int i = 0; i < nbrPartition; i++) {
		    	msg.addReceiver(new AID("DA"+i,AID.ISLOCALNAME));
		    	msg.setContent("I send you the Target");
		    	System.out.println(msg);
		    	send(msg); 
		    	}
  
			System.out.println("Target Sent ... "+this.getAID().getName());
		  
	
		ParallelBehaviour parallelBehaviour = new ParallelBehaviour();
		addBehaviour(parallelBehaviour);
		parallelBehaviour.addSubBehaviour(new CyclicBehaviour() {
			private AID requester ; 
			private String model ; 
			private double result ; 
			
			@Override
			public void action() {
				try{
					MessageTemplate template = 
							MessageTemplate.or(
									MessageTemplate.MatchPerformative(ACLMessage.PROPOSE), 
									MessageTemplate.or(
											MessageTemplate.MatchPerformative(ACLMessage.CONFIRM),
											MessageTemplate.MatchPerformative(ACLMessage.REQUEST)));
				
					ACLMessage aclMessage = receive(template);
										
					if (aclMessage!=null){
						switch (aclMessage.getPerformative()){
						
						case ACLMessage.REQUEST :
							++cmpt ;
						
							System.out.println("#################");
							System.out.println("Send Response... ");
							System.out.println("From... "+aclMessage.getSender().getName());
							
							model = aclMessage.getContent(); 
							requester = aclMessage.getSender();
							
							System.out.println(".......................");
							System.out.println("Send request...");
						
							ACLMessage msg1 = new ACLMessage(ACLMessage.INFORM);
							msg1.setContent(model);
							msg1.setConversationId(model+"--"+cmpt);
							msg1.addUserDefinedParameter("cmpt", String.valueOf(cmpt));
							msg1.addReceiver(new AID("DA",AID.ISLOCALNAME));
							
							System.out.println("...Loading...");
						
							Thread.sleep(5000);
							send(msg1);
						break;
						
						case ACLMessage.PROPOSE:
							System.out.println(".......................");
							System.out.println("Conversation ID : " +aclMessage.getConversationId());
							System.out.println("Reception : ");
							System.out.println("From... "+aclMessage.getSender().getName());
							
							System.out.println(".......................");
							System.out.println("Closing of the transaction...");
							
							// Ing a accepter Result
							
							ACLMessage msg2 = aclMessage.createReply();
							msg2.setPerformative(ACLMessage.ACCEPT_PROPOSAL);
							
							System.out.println("...Loading...");

							Thread.sleep(5000);
							send(msg2);
							
								
						break;
						
						case ACLMessage.CONFIRM : 
							System.out.println(".......................");
							
							System.out.println("received the Confirmation...");
							
							System.out.println("Conversation ID : " +aclMessage.getConversationId());
							
							ACLMessage msg3 = new ACLMessage(ACLMessage.INFORM);
							msg3.addReceiver(requester);
							msg3.setConversationId(aclMessage.getConversationId());
							msg3.setContent("Confirmation of results...");
							
							send(msg3);
						break;
						
						}
					}
					else {
						block();
					}
				}catch (Exception e){
				e.printStackTrace();
				}
			} 	});
		
		 this.Variable = new Variable();
	        catalogue = new Hashtable();
	        /*
	        myGui = new DAGui();
	        myGui.setCA(this);
	        
	        new Thread() {
	            @Override
	            public void run() {
	                myGui.launchThis();
	            }
	        }.start(); */ 
	}
	//Appeler avant la destruction de l'agent 
	@Override
	protected void takeDown() {
		System.out.println("Destruction de l'agent");
		
	}
	
	@Override
	public void doMove(Location loc) {
		System.out.println(" Migration vers "+ loc.getName());
	}

	@Override
	protected void onGuiEvent(GuiEvent ev) {
		switch (ev.getType()) {
		case 1:
			// Read Parameters
			Map<String, Object> params=(Map<String, Object>)ev.getParameter(0);
			String valueresponse = (String) params.get("value");
			String namevariable = (String) params.get("name");
	
			ACLMessage aclMessage = new ACLMessage(ACLMessage.INFORM);
			aclMessage.addReceiver(new AID("DA",AID.ISLOCALNAME));
			
			aclMessage.setContent(valueresponse);
			send(aclMessage);
			
			break;

		default:
			break;
		}
	}

}
