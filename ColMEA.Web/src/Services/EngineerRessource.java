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
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import com.supmeca.colMEA.business.EngineerServiceLocal;
import com.supmeca.colMEA.domain.Engineer;

@Path("/Engineers")
public class EngineerRessource {

	@Inject
	EngineerServiceLocal EngineerEjb;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response findAllEngineers(){
		
		List<Engineer> Engineers = null;
		Engineers = EngineerEjb.findAllEngineers();
		if (Engineers==null)
			return Response.status(Status.NOT_FOUND).build();
		else
			return Response.ok(Engineers).build();
			
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	
	public Response addEngineer (Engineer engineer, @Context UriInfo uriInfo) {
		EngineerEjb.CreateEngineer(engineer);
		UriBuilder builder = uriInfo.getAbsolutePathBuilder();
		return Response.created(builder.build()).entity(engineer).build();
	}
 
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateEngineer(Engineer engineer){
		EngineerEjb.EditEngineer(engineer);
		if (engineer == null)
			return Response.status(Status.ACCEPTED).entity("User successfully Updated").build();
		else
			return Response.status(Status.NOT_FOUND).entity("User Not found").build();
	}
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteEngineer(Engineer engineer){
		EngineerEjb.removeEngineer(engineer);
	if (engineer == null)
		return Response.status(Status.ACCEPTED).entity("User successfully Deleted").build();
	else
		return Response.status(Status.NOT_FOUND).entity("User Not found").build();
	}
	@GET
	@Path("findEngineer/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findEngineerById(@PathParam("id") Integer id){
		Engineer En = EngineerEjb.findEngineerById(id);
	 if (En==null)
			return Response.status(Status.NOT_FOUND).entity("User Not Found").build();
		else
			return Response.ok(En).build();
	 
	}
	@DELETE
	@Path("DeleteEngineer/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteEngineer(@PathParam("id") Integer id){
		EngineerEjb.DeleteEngineer(id);
	if (id == null)
		return Response.status(Status.ACCEPTED).entity("User successfully Deleted").build();
	else
		return Response.status(Status.NOT_FOUND).entity("User Not found").build();
	}
}
