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

import com.supmeca.colMEA.business.ObjectiveServiceLocal;
import com.supmeca.colMEA.domain.Objective;

@Path("/Objectives")
public class ObjectiveRessource {

	@Inject
	ObjectiveServiceLocal ObjectiveEjb;


	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response findAllObjectives(){
		
		List<Objective> Objectives = null;
		Objectives = ObjectiveEjb.findAllObjectives();
		if (Objectives==null)
			return Response.status(Status.NOT_FOUND).build();
		else
			return Response.ok(Objectives).build();
			
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	
	public Response addObjective (Objective Objective, @Context UriInfo uriInfo) {
		ObjectiveEjb.CreateObjective(Objective);
		UriBuilder builder = uriInfo.getAbsolutePathBuilder();
		return Response.created(builder.build()).entity(Objective).build();
	}
 
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateObjective(Objective Objective){
		ObjectiveEjb.EditObjective(Objective);
		if (Objective == null)
			return Response.status(Status.ACCEPTED).entity("User successfully Updated").build();
		else
			return Response.status(Status.NOT_FOUND).entity("User Not found").build();
	}
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteObjective(Objective Objective){
		ObjectiveEjb.removeObjective(Objective);
	if (Objective == null)
		return Response.status(Status.ACCEPTED).entity("User successfully Deleted").build();
	else
		return Response.status(Status.NOT_FOUND).entity("User Not found").build();
	}
	@GET
	@Path("findObjective/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findObjectiveById(@PathParam("id") Integer id){
		Objective En = ObjectiveEjb.findObjectiveById(id);
	 if (En==null)
			return Response.status(Status.NOT_FOUND).entity("User Not Found").build();
		else
			return Response.ok(En).build();
	 
	}
	@DELETE
	@Path("DeleteObjective/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteObjective(@PathParam("id") Integer id){
		ObjectiveEjb.DeleteObjective(id);
	if (id == null)
		return Response.status(Status.ACCEPTED).entity("User successfully Deleted").build();
	else
		return Response.status(Status.NOT_FOUND).entity("User Not found").build();
	}
}
