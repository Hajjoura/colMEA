package Services;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.Status;

import com.supmeca.colMEA.business.PartitionServiceLocal;
import com.supmeca.colMEA.business.SetServiceLocal;
import com.supmeca.colMEA.business.VariablesServiceLocal;
import com.supmeca.colMEA.domain.Interval;
import com.supmeca.colMEA.domain.Partition;
import com.supmeca.colMEA.domain.Set;
import com.supmeca.colMEA.domain.User;
import com.supmeca.colMEA.domain.Variable;
import com.supmeca.colMEA.domain.Variables_Partitions;
import com.supmeca.colMEA.domain.Variables_PartitionsFK;

@Path("/Variables")
public class VariableRessource {

	@Inject
	VariablesServiceLocal VariableEjb;

	@Inject
	SetServiceLocal SetEjb;

	@Inject
	PartitionServiceLocal PartitionEjb;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response findAllVariables(){

		List<Variable> Variables = null;
		Variables = VariableEjb.findAllVariables();
		if (Variables==null)
			return Response.status(Status.NOT_FOUND).build();
		else
			return Response.ok(Variables).build();

	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)

	public Response addVariable (Variable Variable, @Context UriInfo uriInfo) {
		VariableEjb.CreateVariable(Variable);
		UriBuilder builder = uriInfo.getAbsolutePathBuilder();
		return Response.created(builder.build()).entity(Variable).build();
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateVariable(Variable Variable){
		VariableEjb.EditVariable(Variable);
		if (Variable != null)
			return Response.status(Status.ACCEPTED).entity("Variable successfully Updated").build();
		else
			return Response.status(Status.NOT_FOUND).entity("Variable Not found").build();
	}
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteVariable(Variable Variable){
		VariableEjb.removeVariable(Variable);
		if (Variable == null)
			return Response.status(Status.ACCEPTED).entity("User successfully Deleted").build();
		else
			return Response.status(Status.NOT_FOUND).entity("User Not found").build();
	}
	@GET
	@Path("findVariable/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findVariableById(@PathParam("id") Integer id){
		Variable En = VariableEjb.findVariableById(id);
		if (En==null)
			return Response.status(Status.NOT_FOUND).entity("User Not Found").build();
		else
			return Response.ok(En).build();

	}
	@GET
	@Path("findLastRow")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findLastRow(){
		Variable En = VariableEjb.getLastRow();
		if (En==null)
			return Response.status(Status.NOT_FOUND).entity("User Not Found").build();
		else
			return Response.ok(En).build();

	}
	@DELETE
	@Path("DeleteVariable/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteVariable(@PathParam("id") Integer id){
		VariableEjb.DeleteVariable(id);
		if (id != null)
			return Response.status(Status.ACCEPTED).entity("Variable successfully Deleted").build();
		else
			return Response.status(Status.NOT_FOUND).entity("Variable Not found").build();
	}


	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
	//  Add Patition to Variable service
	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 

	@POST
	@Path("addPartition/{id_partition}/{id_variable}/{id_set}")
	@Produces("application/json")
	@Consumes("application/json")

	public Response reserveSeat(@PathParam("id_partition")Integer id_partition,@PathParam("id_variable")Integer id_variable,@PathParam("id_set")Integer id_set)
	{



		/*Date dateReservation=null;
				SimpleDateFormat simple_date= new 
						SimpleDateFormat("dd/MM/yyyy");
				try {
					 dateReservation =  (Date) simple_date.parse(date_s);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/

		Date date = new Date();
		System.out.println(date);
		Variables_Partitions varpart = new Variables_Partitions();

		Variables_PartitionsFK varpartFk = new Variables_PartitionsFK();
		varpartFk.setId_variable(id_variable);
		System.out.println(id_variable);
		varpartFk.setId_partition(id_partition);
		System.out.println(id_partition);
		varpartFk.setId_set(id_set);

		varpart.setDate(date);
		Partition partition = PartitionEjb.findPartitionById(id_partition);
		Variable variable = VariableEjb.findVariableById(id_variable);
		Set set = SetEjb.findSetById(id_set);




		if ((partition!=null)&&(variable!=null))
		{
			if (PartitionEjb.addVariableToPartition(partition, variable,set, varpart.getDate(),varpart.getMinRes(), varpart.getMinRes()))
			{
				return Response.status(Status.ACCEPTED).entity("Success variable was added").build();
			}else
			{
				return Response.status(Status.NOT_FOUND).build();

			}


		}
		else
		{
			return Response.status(Status.NOT_FOUND).entity(" Partition/varaible Not Found").build();

		}
	}
	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
	//  find Variable by Name
	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
	@GET
	@Path("findVariableByName/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findVariableByName(@PathParam("name") String name){
		Variable Variable = VariableEjb.findVariableByName(name);
		if (Variable==null)
			return Response.status(Status.NOT_FOUND).entity("Variable Not Found").build();
		else
			return Response.ok(Variable).build();

	}
	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
	//  find Variable by Min Max
	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 

	@GET
	@Path("findByMinMax/{min}/{max}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findByMinMax(@PathParam("min") float min, @PathParam("max") float max) 
	{
		Variable variable =  VariableEjb.findVariableByMinMax(min, min);

		if (variable ==null)
			return Response.status(Status.NOT_FOUND).entity("Variable Not Found").build();

		else
			return Response.ok(variable).build();
	}

	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
	//  find Variable by Min Max Res
	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 

	@GET
	@Path("findByMinMaxRes/{min}/{max}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findByMinMaxRes(@PathParam("min") float min, @PathParam("max") float max) 
	{
		Variable variable =  VariableEjb.findVariableByMinMaxRes(min, min);

		if (variable ==null)
			return Response.status(Status.NOT_FOUND).entity("Variable Not Found").build();

		else
			return Response.ok(variable).build();
	}

	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
	//  find Variables by Min Max
	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 

	@GET
	@Path("findVariablesByMinMax/{min}/{max}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findVariablesByMinMax(@PathParam("min") float min, @PathParam("max") float max) 
	{
		List<Variable> variables =  VariableEjb.findVariablesByMinMax(min, min);

		if (variables ==null)
			return Response.status(Status.NOT_FOUND).entity("Variable Not Found").build();

		else
			return Response.ok(variables).build();
	}

	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
	//  find Variables by Min Max Res
	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 

	@GET
	@Path("findByVariablesMinMaxRes/{min}/{max}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findVariablesByMinMaxRes(@PathParam("min") float min, @PathParam("max") float max) 
	{
		List<Variable> variables =  VariableEjb.findVariablesByMinMaxRes(min, min);

		if (variables ==null)
			return Response.status(Status.NOT_FOUND).entity("Variable Not Found").build();

		else
			return Response.ok(variables).build();
	}

	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
	//  find Variable by Partition
	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
	@GET
	@Path("findVariableByPartition/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findVariableByPartition(@PathParam("id") Integer id){
		Variable Variable = VariableEjb.findVariableByPartition(id);
		if (Variable==null)
			return Response.status(Status.NOT_FOUND).entity("variable Not Found").build();
		else
			return Response.ok(Variable).build();

	}


	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
	//  find Variable by Partition
	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
	@GET
	@Path("findVariablesByPartition/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findVariablesByPartition(@PathParam("id") Integer id){
		List<Variable> Variables = VariableEjb.findVariablesByPartition(id);
		if (Variables==null)
			return Response.status(Status.NOT_FOUND).entity("variable Not Found").build();
		else
			return Response.ok(Variables).build();

	}

	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
	//  find Variable by Study
	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
	@GET
	@Path("findVariableByStudy/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findVariableByStudy(@PathParam("id") Integer id){
		Variable Variable = VariableEjb.findVariableByStudy(id);
		if (Variable==null)
			return Response.status(Status.NOT_FOUND).entity("Variable Not Found").build();
		else
			return Response.ok(Variable).build();

	}


	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
	//  find Variables by Study
	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
	@GET
	@Path("findVariablesByStudy/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findVariablesByStudy(@PathParam("id") Integer id){
		List<Variable> Variables = VariableEjb.findVariablesByStudy(id);
		if (Variables==null)
			return Response.status(Status.NOT_FOUND).entity("Variable Not Found").build();
		else
			return Response.ok(Variables).build();

	}

	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
	//  find Variable by Project
	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
	@GET
	@Path("findVariableByProject/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findVariableByProject(@PathParam("id") Integer id){
		Variable Variable = VariableEjb.findVariableByProject(id);
		if (Variable==null)
			return Response.status(Status.NOT_FOUND).entity("Variable Not Found").build();
		else
			return Response.ok(Variable).build();

	}


	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
	//  find Variables by Project
	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
	@GET
	@Path("findVariablesByProject/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findVariablesByProject(@PathParam("id") Integer id){
		List<Variable> Variables = VariableEjb.findVariablesByProject(id);
		if (Variables==null)
			return Response.status(Status.NOT_FOUND).entity("Variable Not Found").build();
		else
			return Response.ok(Variables).build();

	}


	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
	//  find Variable by Team
	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
	@GET
	@Path("findVariableByTeam/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findVariableByTeam(@PathParam("id") Integer id){
		Variable Variable = VariableEjb.findVariableByTeam(id);
		if (Variable==null)
			return Response.status(Status.NOT_FOUND).entity("Variable Not Found").build();
		else
			return Response.ok(Variable).build();

	}

	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
	//  find Variables by Team
	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
	@GET
	@Path("findVariablesByTeam/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findVariablesByTeam(@PathParam("id") Integer id){
		List<Variable> Variables = VariableEjb.findVariablesByTeam(id);
		if (Variables==null)
			return Response.status(Status.NOT_FOUND).entity("Variable Not Found").build();
		else
			return Response.ok(Variables).build();

	}

	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
	//  find Variable by Engineer
	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
	@GET
	@Path("findVariableByEngineer/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findVariableByEngineer(@PathParam("id") Integer id){
		Variable Variable = VariableEjb.findVariableByEngineer(id);
		if (Variable==null)
			return Response.status(Status.NOT_FOUND).entity("Variable Not Found").build();
		else
			return Response.ok(Variable).build();

	}

	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
	//  find Variables by Engineer
	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
	@GET
	@Path("findVariablesByEngineer/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findVariablesByEngineer(@PathParam("id") Integer id){
		List<Variable> Variables = VariableEjb.findVariablesByEngineer(id);
		if (Variables==null)
			return Response.status(Status.NOT_FOUND).entity("Variable Not Found").build();
		else
			return Response.ok(Variables).build();

	}

	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
	//  find Variable by Coordinator
	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
	@GET
	@Path("findVariableByCoordinator/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findVariableByCoordinator(@PathParam("id") Integer id){
		Variable Variable = VariableEjb.findVariableByCoordinator(id);
		if (Variable==null)
			return Response.status(Status.NOT_FOUND).entity("Variable Not Found").build();
		else
			return Response.ok(Variable).build();

	}

	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
	//  find Variables by Coordinator
	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
	@GET
	@Path("findVariablesByCoordinator/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findVariablesByCoordinator(@PathParam("id") Integer id){
		List<Variable> Variables = VariableEjb.findVariablesByCoordinator(id);
		if (Variables==null)
			return Response.status(Status.NOT_FOUND).entity("Variable Not Found").build();
		else
			return Response.ok(Variables).build();

	}

	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
	//  find Variable by Manager
	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
	@GET
	@Path("findVariableByManager/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findVariableByManager(@PathParam("id") Integer id){
		Variable Variable = VariableEjb.findVariableByManager(id);
		if (Variable==null)
			return Response.status(Status.NOT_FOUND).entity("Variable Not Found").build();
		else
			return Response.ok(Variable).build();

	}

	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
	//  find Variables by Manager
	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
	@GET
	@Path("findVariablesByManager/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findVariablesByManager(@PathParam("id") Integer id){
		List<Variable> Variables = VariableEjb.findVariablesByManager(id);
		if (Variables==null)
			return Response.status(Status.NOT_FOUND).entity("Variable Not Found").build();
		else
			return Response.ok(Variables).build();

	}

	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
	//  find Variable by Visibility
	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
	@GET
	@Path("findVariableByVisibility/{Visibility}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findVariableByVisibility(@PathParam("Visibility") Boolean Visibility){
		List<Variable> Variables = VariableEjb.findVariableByVisibility(Visibility);
		if (Variables==null)
			return Response.status(Status.NOT_FOUND).entity("Variable Not Found").build();
		else
			return Response.ok(Variables).build();

	}

	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
	//  find Shared Variable by Manager
	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
	@GET
	@Path("findSharedVariableByManager/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findSharedVariableByManager(@PathParam("id") Integer id){
		List<Variable> Variables = VariableEjb.findSharedVariablesByManager(id);
		if (Variables==null)
			return Response.status(Status.NOT_FOUND).entity("Variable Not Found").build();
		else
			return Response.ok(Variables).build();

	}
	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
	//  find Local Variable by Manager
	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
	@GET
	@Path("findLocalVariableByManager/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findLocalVariableByManager(@PathParam("id") Integer id){
		List<Variable> Variables = VariableEjb.findLocalVariablesByManager(id);
		if (Variables==null)
			return Response.status(Status.NOT_FOUND).entity("Variable Not Found").build();
		else
			return Response.ok(Variables).build();

	}

	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
	//  find Shared Variable by Coodinator
	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
	@GET
	@Path("findSharedVariableByCoodinator/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findSharedVariableByCoodinator(@PathParam("id") Integer id){
		List<Variable> Variables = VariableEjb.findLocalVariablesByCoordinator(id);
		if (Variables==null)
			return Response.status(Status.NOT_FOUND).entity("Variable Not Found").build();
		else
			return Response.ok(Variables).build();

	}
	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
	//  find Shared Variables
	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
	@GET
	@Path("findSharedVariables")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findSharedVariables(){
		List<Variable> Variables = VariableEjb.findSharedVariables();
		if (Variables==null)
			return Response.status(Status.NOT_FOUND).entity("Variable Not Found").build();
		else
			return Response.ok(Variables).build();

	}
	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
	//  find Shared Variables
	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
	@GET
	@Path("findLocalVariables")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findLocalVariables(){
		List<Variable> Variables = VariableEjb.findLocalVariables();
		if (Variables==null)
			return Response.status(Status.NOT_FOUND).entity("Variable Not Found").build();
		else
			return Response.ok(Variables).build();

	}
	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
	//  find Local Variable by Engineer
	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
	@GET
	@Path("findLocalVariableByEngineer/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findLocalVariableByEngineer(@PathParam("id") Integer id){
		List<Variable> Variables = VariableEjb.findLocalVariablesByEngineer(id);
		if (Variables==null)
			return Response.status(Status.NOT_FOUND).entity("Variable Not Found").build();
		else
			return Response.ok(Variables).build();

	}
	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
	//  find Variable with set
	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
	@GET
	@Path("findVariableWithSet/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findVariableWitSet(@PathParam("id") Integer id){
		List<Set> Variables = VariableEjb.findVariablewithSet(id);
		if (Variables==null)
			return Response.status(Status.NOT_FOUND).entity("variable Not Found").build();
		else
			return Response.ok(Variables).build();

	}
	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
	//  find Variable with sets
	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
	@GET
	@Path("findVariableWithSets/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findVariableWitSets(@PathParam("id") Integer id){
		HashMap<String,List<Number>> Variables = VariableEjb.findVariablewithSets(id);
		if (Variables==null)
			return Response.status(Status.NOT_FOUND).entity("variable Not Found").build();
		else
			return Response.ok(Variables).build();

	}
	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
	//  display Variable with sets
	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
	@GET
	@Path("displayVariableWithSets/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response displayVariableWitSets(@PathParam("id") Integer id){
		List<Set> Variables = VariableEjb.findVariablewithSet(id);
		if (Variables==null)
			return Response.status(Status.NOT_FOUND).entity("variable Not Found").build();
		else
			return Response.ok(Variables).build();

	}
	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
	//  find Variable with sets by partition
	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
	@GET
	@Path("findVariableWithSetsByPartition/{id_var}/{id_part}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findVariableWitSetsByPartition(@PathParam("id_var") Integer id_var,@PathParam("id_part") Integer id_part){
		HashMap<String,List<Number>> Variables = VariableEjb.findVariablewithSetsbyPartition(id_var, id_part);
		if (Variables==null)
			return Response.status(Status.NOT_FOUND).entity("variable Not Found").build();
		else
			return Response.ok(Variables).build();

	}

	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
	//  find Variable with Intervals
	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
	@GET
	@Path("findVariableWithIntervals/{id_var}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findVariableWitSetsByPartition(@PathParam("id_var") Integer id_var){
		HashMap<String,List<Interval>> Variables = VariableEjb.findVariablewithIntervals(id_var);
		if (Variables==null)
			return Response.status(Status.NOT_FOUND).entity("variable Not Found").build();
		else
			return Response.ok(Variables).build();

	}
	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
	//  find Intervals with Variable and partitions 
	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
	@GET
	@Path("findIntervalsByVableandpart/{id_var}/{id_part}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findIntervalsByVableandpart(@PathParam("id_var") Integer id_var,@PathParam("id_part") Integer id_part){
		HashMap<String,List<Interval>> Variables = VariableEjb.findIntervalsByVableandpart(id_var, id_part);
		if (Variables==null)
			return Response.status(Status.NOT_FOUND).entity("variable Not Found").build();
		else
			return Response.ok(Variables).build();

	}
	
	
	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
	//  find Variable with sets and intervals
	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
	@GET
	@Path("findVariableWithSetsandIntervals/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findVariableWitSetsandIntervals(@PathParam("id") Integer id){
		HashMap<String,List<Number>> Variables = VariableEjb.findVariablewithSets(id);
		if (Variables==null)
			return Response.status(Status.NOT_FOUND).entity("variable Not Found").build();
		else
			return Response.ok(Variables).build();

	}
	
	
	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
	//  find find SetsBy Variable
	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
	@GET
	@Path("findSetsByVariable/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findSetsByVariable(@PathParam("id") Integer id){
		List<Number> Variables = VariableEjb.findSetsByVariable(id);
		if (Variables==null)
			return Response.status(Status.NOT_FOUND).entity("variable Not Found").build();
		else
			return Response.ok(Variables).build();

	}


}



