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

import com.supmeca.colMEA.business.ToolServiceLocal;
import com.supmeca.colMEA.domain.Tool;

@Path("/Tools")
public class ToolRessource {

	@Inject
	ToolServiceLocal ToolEjb;


	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response findAllTools(){
		
		List<Tool> Tools = null;
		Tools = ToolEjb.findAllTools();
		if (Tools==null)
			return Response.status(Status.NOT_FOUND).build();
		else
			return Response.ok(Tools).build();
			
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	
	public Response addTool (Tool Tool, @Context UriInfo uriInfo) {
		ToolEjb.CreateTool(Tool);
		UriBuilder builder = uriInfo.getAbsolutePathBuilder();
		return Response.created(builder.build()).entity(Tool).build();
	}
 
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateTool(Tool Tool){
		ToolEjb.EditTool(Tool);
		if (Tool == null)
			return Response.status(Status.ACCEPTED).entity("User successfully Updated").build();
		else
			return Response.status(Status.NOT_FOUND).entity("User Not found").build();
	}
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteTool(Tool Tool){
		ToolEjb.removeTool(Tool);
	if (Tool == null)
		return Response.status(Status.ACCEPTED).entity("User successfully Deleted").build();
	else
		return Response.status(Status.NOT_FOUND).entity("User Not found").build();
	}
	@GET
	@Path("findTool/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findToolById(@PathParam("id") Integer id){
		Tool En = ToolEjb.findToolById(id);
	 if (En==null)
			return Response.status(Status.NOT_FOUND).entity("User Not Found").build();
		else
			return Response.ok(En).build();
	 
	}
	@DELETE
	@Path("DeleteTool/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteTool(@PathParam("id") Integer id){
		ToolEjb.DeleteTool(id);
	if (id == null)
		return Response.status(Status.ACCEPTED).entity("User successfully Deleted").build();
	else
		return Response.status(Status.NOT_FOUND).entity("User Not found").build();
	}
}
