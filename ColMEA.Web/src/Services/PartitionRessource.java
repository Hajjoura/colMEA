package Services;

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
import com.supmeca.colMEA.domain.Partition;

@Path("/Partitions")
public class PartitionRessource {

	@Inject
	PartitionServiceLocal PartitionEjb;
	
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
}
