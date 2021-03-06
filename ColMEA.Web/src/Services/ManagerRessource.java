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

import com.supmeca.colMEA.business.ManagerServiceLocal;
import com.supmeca.colMEA.domain.Manager;

@Path("/Managers")
public class ManagerRessource {

	@Inject
	ManagerServiceLocal ManagerEjb;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response findAllManagers(){
		
		List<Manager> Managers = null;
		Managers = ManagerEjb.findAllManagers();
		if (Managers==null)
			return Response.status(Status.NOT_FOUND).build();
		else
			return Response.ok(Managers).build();
			
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	
	public Response addManager (Manager Manager, @Context UriInfo uriInfo) {
		ManagerEjb.CreateManager(Manager);
		UriBuilder builder = uriInfo.getAbsolutePathBuilder();
		return Response.created(builder.build()).entity(Manager).build();
	}
 
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateManager(Manager Manager){
		ManagerEjb.EditManager(Manager);
		if (Manager == null)
			return Response.status(Status.ACCEPTED).entity("User successfully Updated").build();
		else
			return Response.status(Status.NOT_FOUND).entity("User Not found").build();
	}
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteManager(Manager Manager){
		ManagerEjb.removeManager(Manager);
	if (Manager == null)
		return Response.status(Status.ACCEPTED).entity("User successfully Deleted").build();
	else
		return Response.status(Status.NOT_FOUND).entity("User Not found").build();
	}
	@GET
	@Path("findManager/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findManagerById(@PathParam("id") Integer id){
		Manager Ma = ManagerEjb.findManagerById(id);
	 if (Ma==null)
			return Response.status(Status.NOT_FOUND).entity("User Not Found").build();
		else
			return Response.ok(Ma).build();
	 
	}
	@DELETE
	@Path("DeleteManager/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteManager(@PathParam("id") Integer id){
		ManagerEjb.DeleteManager(id);
	if (id == null)
		return Response.status(Status.ACCEPTED).entity("User successfully Deleted").build();
	else
		return Response.status(Status.NOT_FOUND).entity("User Not found").build();
	}
	
	@GET
	@Path("findManagerByProject/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findManagerByProject(@PathParam("id") Integer id){
		Manager Ma =  ManagerEjb.findManagerByProject(id);
	 if (Ma==null)
			return Response.status(Status.NOT_FOUND).entity("User Not Found").build();
		else
			return Response.ok(Ma).build();
	 
	}
	
	@GET
	@Path("findManagerNameByProject/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findManagerNameByProject(@PathParam("id") Integer id){
		String Ma =  ManagerEjb.findManagerNameByProject(id);
	 if (Ma==null)
			return Response.status(Status.NOT_FOUND).entity("User Not Found").build();
		else
			return Response.ok(Ma).build();
	 
	}
}
