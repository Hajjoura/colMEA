package Services;

import java.util.Date;
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
import com.supmeca.colMEA.domain.Partition;
import com.supmeca.colMEA.domain.Project;
import com.supmeca.colMEA.domain.Set;
import com.supmeca.colMEA.domain.Teams_Engineers;
import com.supmeca.colMEA.domain.Variable;
import com.supmeca.colMEA.domain.Variables_Partitions;
import com.supmeca.colMEA.domain.Variables_PartitionsFK;

@Path("/Partitions")
public class PartitionRessource {

	@Inject
	PartitionServiceLocal PartitionEjb;
	@Inject
	VariablesServiceLocal VariableEjb;
	@Inject
	SetServiceLocal SetEjb;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response findAllPartitions(){

		List<Partition> Partitions = null;
		Partitions = PartitionEjb.findAllPartitions();
		if (Partitions==null)
			return Response.status(Status.NOT_FOUND).build();
		else
			return Response.ok(Partitions).build();

	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)

	public Response addPartition (Partition Partition, @Context UriInfo uriInfo) {
		PartitionEjb.CreatePartition(Partition);
		UriBuilder builder = uriInfo.getAbsolutePathBuilder();
		return Response.created(builder.build()).entity(Partition).build();
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updatePartition(Partition Partition){
		PartitionEjb.EditPartition(Partition);
		if (Partition != null)
			return Response.status(Status.ACCEPTED).entity("User successfully Updated").build();
		else
			return Response.status(Status.NOT_FOUND).entity("User Not found").build();
	}
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deletePartition(Partition Partition){
		PartitionEjb.removePartition(Partition);
		if (Partition != null)
			return Response.status(Status.ACCEPTED).entity("User successfully Deleted").build();
		else
			return Response.status(Status.NOT_FOUND).entity("User Not found").build();
	}
	@GET
	@Path("findPartition/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findPartitionById(@PathParam("id") Integer id){
		Partition En = PartitionEjb.findPartitionById(id);
		if (En==null)
			return Response.status(Status.NOT_FOUND).entity("User Not Found").build();
		else
			return Response.ok(En).build();

	}
	@DELETE
	@Path("DeletePartition/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deletePartition(@PathParam("id") Integer id){
		PartitionEjb.DeletePartition(id);
		if (id == null)
			return Response.status(Status.ACCEPTED).entity("User successfully Deleted").build();
		else
			return Response.status(Status.NOT_FOUND).entity("User Not found").build();
	}


	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
	//  Add variable to partition service
	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 

	@POST
	@Path("addvariable/{id_partition}/{id_variable}/{id_set}")
	@Produces("application/json")
	@Consumes("application/json")

	public Response addvariable(@PathParam("id_partition")Integer id_partition,@PathParam("id_variable")Integer id_variable,@PathParam("id_set") Integer id_set)
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
		Float max = null;
		Float min= null;
		System.out.println(date);
		Variables_Partitions varpart = new Variables_Partitions();
		Variables_PartitionsFK varpartFk = new Variables_PartitionsFK();
		varpartFk.setId_variable(id_variable);
		varpartFk.setId_partition(id_partition);
		varpartFk.setId_set(id_set);
		varpart.setDate(date);
		varpart.setMaxRes(max);
		varpart.setMinRes(min);
		Partition partition = PartitionEjb.findPartitionById(id_partition);
		Variable variable = VariableEjb.findVariableById(id_variable);
		Set set = SetEjb.findSetById(id_set);

		if ((partition!=null)&&(variable!=null))
		{
			if (PartitionEjb.addVariableToPartition(partition, variable,set, varpart.getDate(),varpart.getMinRes(),varpart.getMaxRes()))
			{
				return Response.status(Status.ACCEPTED).entity("Success variable was added").build();
			}else
			{
				return Response.status(Status.NOT_FOUND).build();
			}

		}else
		{
			return Response.status(Status.NOT_FOUND).entity(" Partition/varaible Not Found").build();

		}
	}

	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
	//  Display partitions and variables  service
	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 

	@GET
	@Path("findVariablesPartitions")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findVariablesPartitions(){

		List<Variables_Partitions> Teams = null;
		Teams = PartitionEjb.findVariablesPartitions();
		if (Teams==null)
			return Response.status(Status.NOT_FOUND).build();
		else
			return Response.ok(Teams).build();

	}

	@GET
	@Path("findVariablesPartitionsByIdVable/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findVariablesPartitionsByIdVable(@PathParam("id")Integer id_variable){

		List<Variables_Partitions> Teams = null;
		Teams = PartitionEjb.findVariablePartitionByIdVable(id_variable);
		if (Teams==null)
			return Response.status(Status.NOT_FOUND).build();
		else
			return Response.ok(Teams).build();

	}
	@GET
	@Path("findVariablesPartitionsByIdPart/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findVariablesPartitionsById(@PathParam("id")Integer id_variable){

		List<Variables_Partitions> Teams = null;
		Teams = PartitionEjb.findVariablePartitionByIdPart(id_variable);
		if (Teams==null)
			return Response.status(Status.NOT_FOUND).build();
		else
			return Response.ok(Teams).build();

	}
	@GET
	@Path("findVariablesPartitionsByIdSet/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findVariablesPartitionsByIdSet(@PathParam("id")Integer id_variable){

		List<Variables_Partitions> Teams = null;
		Teams = PartitionEjb.findVariablePartitionByIdSet(id_variable);
		if (Teams==null)
			return Response.status(Status.NOT_FOUND).build();
		else
			return Response.ok(Teams).build();

	}


	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
	//  update variable to partition service
	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 

	@PUT
	@Path("updateVariable/{id_partition}/{id_variable}/{id_set}/{min}/{max}")
	@Produces("application/json")
	@Consumes("application/json")

	public Response updatevariablePartition(@PathParam("id_partition")Integer id_partition,
			@PathParam("id_variable")Integer id_variable,@PathParam("id_set") Integer id_set,
			@PathParam("min")Float minRes,@PathParam("max")Float maxRes)
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

		Variables_Partitions varpar = new Variables_Partitions();
		Variables_Partitions varpart = new Variables_Partitions();

		Variables_PartitionsFK varpartFk = new Variables_PartitionsFK();
		varpartFk.setId_variable(id_variable);
		varpartFk.setId_partition(id_partition);
		varpartFk.setId_set(id_set);
		varpar.setMaxRes(maxRes);
		varpar.setMinRes(minRes);
		Partition partition = PartitionEjb.findPartitionById(id_partition);
		Variable variable = VariableEjb.findVariableById(id_variable);
		Set set = SetEjb.findSetById(id_set);
		varpart = PartitionEjb.findVariableById(varpartFk);
	
		if ((varpart!=null))
		{
			if (PartitionEjb.updateVariableToPartition(partition, variable,set, varpar.getDate(), varpar.getMinRes(),varpar.getMaxRes()))
			{
				return Response.status(Status.ACCEPTED).entity("Success variable was updated").build();
			}else
			{
				return Response.status(Status.NOT_FOUND).build();
			}
		}else
		{
			return Response.status(Status.NOT_FOUND).entity(" Partition/varaible Not Found").build();
		}
	}
	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
	//  find Partition by Name
	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
	@GET
	@Path("findPartitionByName/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findPartitionByName(@PathParam("name") String name){
		Partition partition = PartitionEjb.findPartitionByName(name);
		if (partition==null)
			return Response.status(Status.NOT_FOUND).entity("User Not Found").build();
		else
			return Response.ok(partition).build();

	}
	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
	//  find Partition by Team
	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
	@GET
	@Path("findPartitionByTeam/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findPartitionByTeam(@PathParam("id") Integer id){
		Partition Partition = PartitionEjb.findPartitionByTeam(id);
		if (Partition==null)
			return Response.status(Status.NOT_FOUND).entity("User Not Found").build();
		else
			return Response.ok(Partition).build();
	}

	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
	// find projects by Team
	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
	@GET
	@Path("findPartitionsByTeam/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findPartitionsByTeam(@PathParam("id") Integer id){
		List<Partition> Partitions = PartitionEjb.findPartitionsByTeam(id);
		if (Partitions==null)
			return Response.status(Status.NOT_FOUND).entity("User Not Found").build();
		else
			return Response.ok(Partitions).build();

	}

	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
	//  find Partition by project
	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
	@GET
	@Path("findPartitionByProject/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findPartitionByProject(@PathParam("id") Integer id){
		Partition Partition = PartitionEjb.findPartitionByProject(id);
		if (Partition==null)
			return Response.status(Status.NOT_FOUND).entity("User Not Found").build();
		else
			return Response.ok(Partition).build();
	}

	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
	// find Partitions by Project
	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
	@GET
	@Path("findPartitionsByProject/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findPartitionsByProject(@PathParam("id") Integer id){
		List<Partition> Partitions = PartitionEjb.findPartitionsByProject(id);
		if (Partitions==null)
			return Response.status(Status.NOT_FOUND).entity("User Not Found").build();
		else
			return Response.ok(Partitions).build();

	}
	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
	//  find Partition by Study
	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
	@GET
	@Path("findPartitionByStudy/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findPartitionByStudy(@PathParam("id") Integer id){
		Partition Partition = PartitionEjb.findPartitionByStudy(id);
		if (Partition==null)
			return Response.status(Status.NOT_FOUND).entity("User Not Found").build();
		else
			return Response.ok(Partition).build();
	}

	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
	// find Partitions by Project
	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
	@GET
	@Path("findPartitionsByStudy/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findPartitionsByStudy(@PathParam("id") Integer id){
		List<Partition> Partitions = PartitionEjb.findPartitionsByStudy(id);
		if (Partitions==null)
			return Response.status(Status.NOT_FOUND).entity("User Not Found").build();
		else
			return Response.ok(Partitions).build();

	}

	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
	//  find Partition by Engineer
	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
	@GET
	@Path("findPartitionByEngineer/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findPartitionByEngineer(@PathParam("id") Integer id){
		Partition Partition = PartitionEjb.findPartitionByEngineer(id);
		if (Partition==null)
			return Response.status(Status.NOT_FOUND).entity("User Not Found").build();
		else
			return Response.ok(Partition).build();
	}

	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
	//  find Partition by Coordinator
	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
	@GET
	@Path("findPartitionByCoordinator/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findPartitionByCoordinator(@PathParam("id") Integer id){
		Partition Partition = PartitionEjb.findPartitionByCoordinator(id);
		if (Partition==null)
			return Response.status(Status.NOT_FOUND).entity("User Not Found").build();
		else
			return Response.ok(Partition).build();
	}

	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
	//  find Partitions by Coordinator
	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
	@GET
	@Path("findPartitionsByCoordinator/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findPartitionsByCoordinator(@PathParam("id") Integer id){
		List<Partition> Partitions = PartitionEjb.findPartitionsByCoordinator(id);
		if (Partitions==null)
			return Response.status(Status.NOT_FOUND).entity("User Not Found").build();
		else
			return Response.ok(Partitions).build();
	}


	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
	//  find Partition by Manager
	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
	@GET
	@Path("findPartitionByManager/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findPartitionByManager(@PathParam("id") Integer id){
		Partition Partition = PartitionEjb.findPartitionByManager(id);
		if (Partition==null)
			return Response.status(Status.NOT_FOUND).entity("User Not Found").build();
		else
			return Response.ok(Partition).build();
	}

	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
	//  find Partitions by Managerr
	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
	@GET
	@Path("findPartitionsByManager/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findPartitionsByManager(@PathParam("id") Integer id){
		List<Partition> Partitions = PartitionEjb.findPartitionsByManager(id);
		if (Partitions==null)
			return Response.status(Status.NOT_FOUND).entity("User Not Found").build();
		else
			return Response.ok(Partitions).build();
	}


	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
	//  find Partition by Variable
	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
	@GET
	@Path("findPartitionByVariable/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findPartitionByVariable(@PathParam("id") Integer id){
		Partition Partition = PartitionEjb.findPartitionByVariable(id);
		if (Partition==null)
			return Response.status(Status.NOT_FOUND).entity("User Not Found").build();
		else
			return Response.ok(Partition).build();
	}

	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
	//  find Partitions by Variable
	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
	@GET
	@Path("findPartitionsByVariable/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findPartitionsByVariable(@PathParam("id") Integer id){
		List<Partition> Partitions = PartitionEjb.findPartitionsByVariable(id);
		if (Partitions==null)
			return Response.status(Status.NOT_FOUND).entity("User Not Found").build();
		else
			return Response.ok(Partitions).build();
	}
	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
	//  find Partition by Constraint
	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
	@GET
	@Path("findPartitionByConstraint/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findPartitionByConstraint(@PathParam("id") Integer id){
		Partition Partition = PartitionEjb.findPartitionByConstraint(id);
		if (Partition==null)
			return Response.status(Status.NOT_FOUND).entity("User Not Found").build();
		else
			return Response.ok(Partition).build();
	}

	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
	//  find Partitions by Constraint
	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
	@GET
	@Path("findPartitionsByConstraint/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findPartitionsByConstraint(@PathParam("id") Integer id){
		List<Partition> Partitions = PartitionEjb.findPartitionsByConstraint(id);
		if (Partitions==null)
			return Response.status(Status.NOT_FOUND).entity("User Not Found").build();
		else
			return Response.ok(Partitions).build();
	}
	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
	//  find Partition by Objective
	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
	@GET
	@Path("findPartitionByObjective/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findPartitionByObjective(@PathParam("id") Integer id){
		Partition Partition = PartitionEjb.findPartitionByObjective(id);
		if (Partition==null)
			return Response.status(Status.NOT_FOUND).entity("User Not Found").build();
		else
			return Response.ok(Partition).build();
	}

	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
	//  find Partitions by Objective
	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
	@GET
	@Path("findPartitionsByObjective/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findPartitionsByObjective(@PathParam("id") Integer id){
		List<Partition> Partitions = PartitionEjb.findPartitionsByObjective(id);
		if (Partitions==null)
			return Response.status(Status.NOT_FOUND).entity("User Not Found").build();
		else
			return Response.ok(Partitions).build();
	}
}
