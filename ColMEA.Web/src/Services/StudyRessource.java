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

import com.supmeca.colMEA.business.StudyServiceLocal;
import com.supmeca.colMEA.domain.Study;

@Path("/Studies")
public class StudyRessource {
	@Inject
	StudyServiceLocal StudyEjb;


	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response findAllStudys(){
		
		List<Study> Studys = null;
		Studys = StudyEjb.findAllStudys();
		if (Studys==null)
			return Response.status(Status.NOT_FOUND).build();
		else
			return Response.ok(Studys).build();
			
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	
	public Response addStudy (Study Study, @Context UriInfo uriInfo) {
		StudyEjb.CreateStudy(Study);
		UriBuilder builder = uriInfo.getAbsolutePathBuilder();
		return Response.created(builder.build()).entity(Study).build();
	}
 
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateStudy(Study Study){
		StudyEjb.EditStudy(Study);
		if (Study == null)
			return Response.status(Status.ACCEPTED).entity("User successfully Updated").build();
		else
			return Response.status(Status.NOT_FOUND).entity("User Not found").build();
	}
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteStudy(Study Study){
		StudyEjb.removeStudy(Study);
	if (Study == null)
		return Response.status(Status.ACCEPTED).entity("User successfully Deleted").build();
	else
		return Response.status(Status.NOT_FOUND).entity("User Not found").build();
	}
	@GET
	@Path("findStudy/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findStudyById(@PathParam("id") Integer id){
		Study En = StudyEjb.findStudyById(id);
	 if (En==null)
			return Response.status(Status.NOT_FOUND).entity("User Not Found").build();
		else
			return Response.ok(En).build();
	 
	}
	@DELETE
	@Path("DeleteStudy/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteStudy(@PathParam("id") Integer id){
		StudyEjb.DeleteStudy(id);
	if (id == null)
		return Response.status(Status.ACCEPTED).entity("User successfully Deleted").build();
	else
		return Response.status(Status.NOT_FOUND).entity("User Not found").build();
	}
}
