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

import com.supmeca.colMEA.business.CoordinatorServiceLocal;
import com.supmeca.colMEA.domain.Coordinator;

public class CoordinatorRessource {
	@Inject
	CoordinatorServiceLocal CoordinatorEjb;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response findAllCoordinators(){
		
		List<Coordinator> Coordinators = null;
		Coordinators = CoordinatorEjb.findAllCoordinators();
		if (Coordinators==null)
			return Response.status(Status.NOT_FOUND).build();
		else
			return Response.ok(Coordinators).build();
			
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	
	public Response addCoordinator (Coordinator Coordinator, @Context UriInfo uriInfo) {
		CoordinatorEjb.CreateCoordinator(Coordinator);
		UriBuilder builder = uriInfo.getAbsolutePathBuilder();
		return Response.created(builder.build()).entity(Coordinator).build();
	}
 
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateCoordinator(Coordinator Coordinator){
		CoordinatorEjb.EditCoordinator(Coordinator);
		if (Coordinator == null)
			return Response.status(Status.ACCEPTED).entity("User successfully Updated").build();
		else
			return Response.status(Status.NOT_FOUND).entity("User Not found").build();
	}
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteCoordinator(Coordinator Coordinator){
		CoordinatorEjb.removeCoordinator(Coordinator);
	if (Coordinator == null)
		return Response.status(Status.ACCEPTED).entity("User successfully Deleted").build();
	else
		return Response.status(Status.NOT_FOUND).entity("User Not found").build();
	}
	@GET
	@Path("findCoordinator/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findCoordinatorById(@PathParam("id") Integer id){
		Coordinator En = CoordinatorEjb.findCoordinatorById(id);
	 if (En==null)
			return Response.status(Status.NOT_FOUND).entity("User Not Found").build();
		else
			return Response.ok(En).build();
	 
	}
	@DELETE
	@Path("DeleteCoordinator/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteCoordinator(@PathParam("id") Integer id){
		CoordinatorEjb.DeleteCoordinator(id);
	if (id == null)
		return Response.status(Status.ACCEPTED).entity("User successfully Deleted").build();
	else
		return Response.status(Status.NOT_FOUND).entity("User Not found").build();
	}
}
