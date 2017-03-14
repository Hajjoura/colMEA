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

import com.supmeca.colMEA.business.ProjectServiceLocal;
import com.supmeca.colMEA.domain.Project;

@Path("/Projects")
public class ProjectRessource {

	@Inject
	ProjectServiceLocal ProjectEjb;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response findAllProjects(){
		
		List<Project> Projects = null;
		Projects = ProjectEjb.findAllProjects();
		if (Projects==null)
			return Response.status(Status.NOT_FOUND).build();
		else
			return Response.ok(Projects).build();
			
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	
	public Response addProject (Project Project, @Context UriInfo uriInfo) {
		ProjectEjb.CreateProject(Project);
		UriBuilder builder = uriInfo.getAbsolutePathBuilder();
		return Response.created(builder.build()).entity(Project).build();
	}
 
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateProject(Project Project){
		ProjectEjb.EditProject(Project);
		if (Project == null)
			return Response.status(Status.ACCEPTED).entity("User successfully Updated").build();
		else
			return Response.status(Status.NOT_FOUND).entity("User Not found").build();
	}
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteProject(Project Project){
		ProjectEjb.removeProject(Project);
	if (Project == null)
		return Response.status(Status.ACCEPTED).entity("User successfully Deleted").build();
	else
		return Response.status(Status.NOT_FOUND).entity("User Not found").build();
	}
	@GET
	@Path("findProject/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findProjectById(@PathParam("id") Integer id){
		Project En = ProjectEjb.findProjectById(id);
	 if (En==null)
			return Response.status(Status.NOT_FOUND).entity("User Not Found").build();
		else
			return Response.ok(En).build();
	 
	}
	@DELETE
	@Path("DeleteProject/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteProject(@PathParam("id") Integer id){
		ProjectEjb.DeleteProject(id);
	if (id == null)
		return Response.status(Status.ACCEPTED).entity("User successfully Deleted").build();
	else
		return Response.status(Status.NOT_FOUND).entity("User Not found").build();
	}
}
