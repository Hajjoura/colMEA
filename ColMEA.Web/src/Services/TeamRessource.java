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

import com.supmeca.colMEA.business.TeamServiceLocal;
import com.supmeca.colMEA.domain.Team;

@Path("/Teams")
public class TeamRessource {

	@Inject
	TeamServiceLocal TeamEjb;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response findAllTeams(){
		
		List<Team> Teams = null;
		Teams = TeamEjb.findAllTeams();
		if (Teams==null)
			return Response.status(Status.NOT_FOUND).build();
		else
			return Response.ok(Teams).build();
			
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	
	public Response addTeam (Team Team, @Context UriInfo uriInfo) {
		TeamEjb.CreateTeam(Team);
		UriBuilder builder = uriInfo.getAbsolutePathBuilder();
		return Response.created(builder.build()).entity(Team).build();
	}
 
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateTeam(Team Team){
		TeamEjb.EditTeam(Team);
		if (Team == null)
			return Response.status(Status.ACCEPTED).entity("User successfully Updated").build();
		else
			return Response.status(Status.NOT_FOUND).entity("User Not found").build();
	}
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteTeam(Team Team){
		TeamEjb.removeTeam(Team);
	if (Team == null)
		return Response.status(Status.ACCEPTED).entity("User successfully Deleted").build();
	else
		return Response.status(Status.NOT_FOUND).entity("User Not found").build();
	}
	@GET
	@Path("findTeam/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findTeamById(@PathParam("id") Integer id){
		Team En = TeamEjb.findTeamById(id);
	 if (En==null)
			return Response.status(Status.NOT_FOUND).entity("User Not Found").build();
		else
			return Response.ok(En).build();
	 
	}
	@DELETE
	@Path("DeleteTeam/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteTeam(@PathParam("id") Integer id){
		TeamEjb.DeleteTeam(id);
	if (id == null)
		return Response.status(Status.ACCEPTED).entity("User successfully Deleted").build();
	else
		return Response.status(Status.NOT_FOUND).entity("User Not found").build();
	}
}
