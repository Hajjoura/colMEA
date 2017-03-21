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
import com.supmeca.colMEA.business.VariablesServiceLocal;
import com.supmeca.colMEA.domain.Partition;
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
		if (Partition == null)
			return Response.status(Status.ACCEPTED).entity("User successfully Updated").build();
		else
			return Response.status(Status.NOT_FOUND).entity("User Not found").build();
	}
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deletePartition(Partition Partition){
		PartitionEjb.removePartition(Partition);
	if (Partition == null)
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
	@Path("addvariable/{id_partition}/{id_variable}")
	@Produces("application/json")
	@Consumes("application/json")
	 	
	public Response addvariable(@PathParam("id_partition")Integer id_partition,@PathParam("id_variable")Integer id_variable)
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
		varpartFk.setId_partition(id_partition);
		varpart.setDate(date);
		Partition partition = PartitionEjb.findPartitionById(id_partition);
		Variable variable = VariableEjb.findVariableById(id_variable);

			if ((partition!=null)&&(variable!=null))
			{
				if (PartitionEjb.addVariableToPartition(partition, variable, varpart.getDate()))
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
		
//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
//  Add variable to partition service
//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 

	@POST
	@Path("updateVariable")
	@Produces("application/json")
	@Consumes("application/json")
		
	public Response updatevariablePartition(@PathParam("id_partition")Integer id_partition,@PathParam("id_variable")Integer id_variable,Variables_Partitions varpar)
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

		Variables_Partitions varpart = new Variables_Partitions();
		Variables_PartitionsFK varpartFk = new Variables_PartitionsFK();
		varpartFk.setId_variable(id_variable);
		varpartFk.setId_partition(id_partition);
		Partition partition = PartitionEjb.findPartitionById(id_partition);
		Variable variable = VariableEjb.findVariableById(id_variable);
		varpart = PartitionEjb.findVariableById(varpartFk);

		if ((varpart!=null))
			{
			if (PartitionEjb.updateVariableToPartition(partition, variable, varpar.getDate()))
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


}
