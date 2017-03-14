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

import com.supmeca.colMEA.business.SetServiceLocal;
import com.supmeca.colMEA.business.VariablesServiceLocal;
import com.supmeca.colMEA.domain.Set;
import com.supmeca.colMEA.domain.Variable;

@Path("/Variables")
public class VariableRessource {
	
	@Inject
	VariablesServiceLocal VariableEjb;

	@Inject
	SetServiceLocal SetEjb;

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
		if (Variable == null)
			return Response.status(Status.ACCEPTED).entity("User successfully Updated").build();
		else
			return Response.status(Status.NOT_FOUND).entity("User Not found").build();
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
	@DELETE
	@Path("DeleteVariable/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteVariable(@PathParam("id") Integer id){
		VariableEjb.DeleteVariable(id);
	if (id == null)
		return Response.status(Status.ACCEPTED).entity("User successfully Deleted").build();
	else
		return Response.status(Status.NOT_FOUND).entity("User Not found").build();
	}
	
	//*************************************************************************//
	//************************* Sets *****************************************//
	//***********************************************************************//
	
	
	@GET
	@Path("/Sets")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findAllSetss(){
		
		List<Set> Sets = null;
		Sets = SetEjb.findAllSets();
		if (Sets==null)
			return Response.status(Status.NOT_FOUND).build();
		else
			return Response.ok(Sets).build();
			
	}
	
	@POST
	@Path("/addSet")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	
	public Response addSet (Set set, @Context UriInfo uriInfo) {
		SetEjb.CreateSet(set);
		UriBuilder builder = uriInfo.getAbsolutePathBuilder();
		return Response.created(builder.build()).entity(set).build();
	}
 
	@PUT
	@Path("/updateSet")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateSet(Set set){
		SetEjb.EditSet(set);
		if (set == null)
			return Response.status(Status.ACCEPTED).entity("User successfully Updated").build();
		else
			return Response.status(Status.NOT_FOUND).entity("User Not found").build();
	}
	@DELETE
	@Path("/setDelete")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteSet(Set set){
		SetEjb.removeSet(set);
		if (set == null)
		return Response.status(Status.ACCEPTED).entity("User successfully Deleted").build();
	else
		return Response.status(Status.NOT_FOUND).entity("User Not found").build();
	}
	@GET
	@Path("findSet/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findSetById(@PathParam("id") Integer id){
		Set s = SetEjb.findSetById(id);
	 if (s==null)
			return Response.status(Status.NOT_FOUND).entity("User Not Found").build();
		else
			return Response.ok(s).build();
	 
	}
	@DELETE
	@Path("DeleteSet/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteSet(@PathParam("id") Integer id){
		SetEjb.DeleteSet(id);
		if (id == null)
		return Response.status(Status.ACCEPTED).entity("User successfully Deleted").build();
	else
		return Response.status(Status.NOT_FOUND).entity("User Not found").build();
	}
}
