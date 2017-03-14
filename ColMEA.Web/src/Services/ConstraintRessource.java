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

import com.supmeca.colMEA.business.ConstraintServiceLocal;
import com.supmeca.colMEA.domain.Constraint;

@Path("/Constraints")
public class ConstraintRessource {
	@Inject
	ConstraintServiceLocal ConstraintEjb;


	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response findAllConstraints(){
		
		List<Constraint> Constraints = null;
		Constraints = ConstraintEjb.findAllConstraints();
		if (Constraints==null)
			return Response.status(Status.NOT_FOUND).build();
		else
			return Response.ok(Constraints).build();
			
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	
	public Response addConstraint (Constraint Constraint, @Context UriInfo uriInfo) {
		ConstraintEjb.CreateConstraint(Constraint);
		UriBuilder builder = uriInfo.getAbsolutePathBuilder();
		return Response.created(builder.build()).entity(Constraint).build();
	}
 
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateConstraint(Constraint Constraint){
		ConstraintEjb.EditConstraint(Constraint);
		if (Constraint == null)
			return Response.status(Status.ACCEPTED).entity("User successfully Updated").build();
		else
			return Response.status(Status.NOT_FOUND).entity("User Not found").build();
	}
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteConstraint(Constraint Constraint){
		ConstraintEjb.removeConstraint(Constraint);
	if (Constraint == null)
		return Response.status(Status.ACCEPTED).entity("User successfully Deleted").build();
	else
		return Response.status(Status.NOT_FOUND).entity("User Not found").build();
	}
	@GET
	@Path("findConstraint/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findConstraintById(@PathParam("id") Integer id){
		Constraint En = ConstraintEjb.findConstraintById(id);
	 if (En==null)
			return Response.status(Status.NOT_FOUND).entity("User Not Found").build();
		else
			return Response.ok(En).build();
	 
	}
	@DELETE
	@Path("DeleteConstraint/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteConstraint(@PathParam("id") Integer id){
		ConstraintEjb.DeleteConstraint(id);
	if (id == null)
		return Response.status(Status.ACCEPTED).entity("User successfully Deleted").build();
	else
		return Response.status(Status.NOT_FOUND).entity("User Not found").build();
	}
}
