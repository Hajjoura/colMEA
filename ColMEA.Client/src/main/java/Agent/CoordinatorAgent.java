package Agent;


import java.util.Hashtable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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
		
			
			// R�cuperer la valeur du TARGET de la BD 
			String url ="jdbc:mysql://localhost/quartz";
		    PreparedStatement st = null;
		    ResultSet rs = null; 
		    try {
			//MYSQL 
				Class.forName("com.mysql.jdbc.Driver");
				Connection cx = DriverManager.getConnection(url, "root", "root");
		
				//Declarer Tableau de Target
		    	Vector  TargetTab = new Vector();
		    
				st = cx.prepareStatement("SELECT DISTINCT v.Target "
						+ " FROM quartz.variable v, quartz.typeVariable tv, quartz.study s, quartz.projectpartition pp"
						+ " WHERE tv.Id_Study = s.Id_Study"
						+ " AND s.Type = '"+CoordinatorChooseProjectPartitionController.ns+"' ");
		    	rs = st.executeQuery();
		    	System.out.println("Connect to database.");
		    	while (rs.next()){
		    		float trg = rs.getFloat(1);
		    		TargetTab.add(trg);
		    		System.out.println(TargetTab);
		    	}
		    } catch (Exception e) {
		           throw new RuntimeException(e); 
		           } 

			ACLMessage msg=new ACLMessage(ACLMessage.PROPOSE);
			
			//Get number of Partition	    
		    ProjectPartitionDAO PPDAO = new ProjectPartitionDAOImp(); 
		    int nbrPartition = PPDAO.NbrPartition(CoordinatorChooseProjectPartitionController.ns);
		    // Send Target
		    for (int i = 0; i < nbrPartition; i++) {
		    	msg.addReceiver(new AID("DA"+i,AID.ISLOCALNAME));
		    	msg.setContent("I send you the Target");
		    	System.out.println(msg);
		    	send(msg); 
		    	}
  
			System.out.println("Target Sent ... "+this.getAID().getName());
		  	}else {
				System.out.println("Target doesn't exist in DataBase");
		  	}
	
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
							if(CoordinatorPartitionsCoordinatingController.AcceptRep == true){
							ACLMessage msg2 = aclMessage.createReply();
							msg2.setPerformative(ACLMessage.ACCEPT_PROPOSAL);
							
							System.out.println("...Loading...");

							Thread.sleep(5000);
							send(msg2);}
							else {
								ACLMessage msg3=aclMessage.createReply();
								msg3.setPerformative(ACLMessage.PROPOSE);
							    msg3.setContent("I send you the New Target");
							    System.out.println(msg3);
							    send(msg3); 
							}
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
		
		 this.shared = new Shared();
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