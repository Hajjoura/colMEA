package Agent;

import java.util.Hashtable;
import java.util.Map;

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

public class DesignAgent extends GuiAgent {

private String Target ;
	
	// The catalogue of Targets for share 
    private Hashtable catalogue;
	
	private JadeController controller;
	
	private Variable shared;
	
	
    public Variable getSeller() {
        return shared;
    }

    public  void setController(JadeController jadeController) {
        this.controller = jadeController;
    }

    public JadeController getController() {
        return controller;
    }
	
    
    private JadeController guijade ;

    // Setup contient les comportements de l'agent
	@Override
	protected void setup() {
		
		controller = new JadeController();
		controller.setDesignAgent(this);

		System.out.println("---------------------------------------");
		System.out.println("----------------Agent DA----------------");
		System.out.println("---------------------------------------");
		
		System.out.println("Wainting for the Target...");
		
		addBehaviour(new CyclicBehaviour() {
			@Override
			public void action() {
				try{
					MessageTemplate messageTemplate = 
							MessageTemplate.or(
									MessageTemplate.MatchPerformative(ACLMessage.PROPOSE), 
									MessageTemplate.or(
											MessageTemplate.MatchPerformative(ACLMessage.INFORM),
											MessageTemplate.MatchPerformative(ACLMessage.ACCEPT_PROPOSAL)));
					
					ACLMessage aclMessage = receive(messageTemplate);
					if(aclMessage!=null){
						
						switch (aclMessage.getPerformative()){
						
						case ACLMessage.PROPOSE:
							System.out.println(".......................");
							System.out.println("Conversation ID : " +aclMessage.getConversationId());
							
							String content = aclMessage.getContent();
							
							System.out.println("sender : "+aclMessage.getSender().getName());
							System.out.println("Content of Message : "+content);
							System.out.println(".......................");
							
							ACLMessage reply = aclMessage.createReply();
							reply.setPerformative(ACLMessage.INFORM);
							reply.addReceiver(aclMessage.getSender());
							
							Object[] args=getArguments();
							if (args.length==1) {
								Target=(String)args[0];
								reply.setContent("Target Received");
								System.out.println(reply);
							}
							else {
								reply.setContent("Target didn't Received");
								System.out.println(reply);
							}
							Thread.sleep(5000);
							send(reply);
						break;
						
						case ACLMessage.INFORM:
							System.out.println(".......................");
							System.out.println("Conversation ID : " +aclMessage.getConversationId());
							
							String modele = aclMessage.getContent();
							String compteur = aclMessage.getUserDefinedParameter("compteur");
							
							System.out.println("Réception du message"+compteur);
							System.out.println("Sender : "+aclMessage.getSender().getName());
							System.out.println("Message : "+modele);
							System.out.println(".......................");
							
							ACLMessage replyi = aclMessage.createReply();
							replyi.setPerformative(ACLMessage.PROPOSE);
							
							// if Eng a ajouter les Rep boolean == true 
							// alors send Propose to CA
							//reply.setContent(*****);
							
						
							
							System.out.println("Voici les résultats de la simulation...");
							
							Thread.sleep(5000);
							send(replyi);
						break;
						
						case ACLMessage.ACCEPT_PROPOSAL :
							System.out.println(".......................");
							
							System.out.println("Conversation ID : " +aclMessage.getConversationId());
							System.out.println("Validation of the result...");
							
							ACLMessage reply2 = aclMessage.createReply(); 
							reply2.setPerformative(ACLMessage.CONFIRM);
							
							System.out.println("Confirmation...");
							
							Thread.sleep(5000);
							send(reply2);
						break;
							
						}
					}
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
        this.shared = new Variable();
        catalogue = new Hashtable();
	}
	
	//Appeler avant la destruction de l'agent 
	@Override
	protected void takeDown() {
		System.out.println("Destruction de l'agent");
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
			aclMessage.addReceiver(new AID("CA",AID.ISLOCALNAME));
			
			aclMessage.setContent(valueresponse);
			send(aclMessage);
			
			break;

		default:
			break;
		}
	}
}
