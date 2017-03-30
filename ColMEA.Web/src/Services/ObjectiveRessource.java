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
import com.supmeca.colMEA.domain.Objective;
import com.supmeca.colMEA.domain.Set;

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
	
	//  find Objective by Name
	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
	@GET
	@Path("findObjectiveByName/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findObjectiveByName(@PathParam("name") String name){
		Objective Objective = ObjectiveEjb.findObjectiveByName(name);
		if (Objective==null)
			return Response.status(Status.NOT_FOUND).entity("Objective Not Found").build();
		else
			return Response.ok(Objective).build();

	}
	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
	//  find Objective by Min Max
	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 

	@GET
	@Path("findByMinMax/{min}/{max}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findByMinMax(@PathParam("min") float min, @PathParam("max") float max) 
	{
		Objective Objective =  ObjectiveEjb.findObjectiveByMinMax(min, min);

		if (Objective ==null)
			return Response.status(Status.NOT_FOUND).entity("Objective Not Found").build();

		else
			return Response.ok(Objective).build();
	}

	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
	//  find Objective by Min Max Res
	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 

	@GET
	@Path("findByMinMaxRes/{min}/{max}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findByMinMaxRes(@PathParam("min") float min, @PathParam("max") float max) 
	{
		Objective Objective =  ObjectiveEjb.findObjectiveByMinMaxRes(min, min);

		if (Objective ==null)
			return Response.status(Status.NOT_FOUND).entity("Objective Not Found").build();

		else
			return Response.ok(Objective).build();
	}

	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
	//  find Objectives by Min Max
	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 

	@GET
	@Path("findObjectivesByMinMax/{min}/{max}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findObjectivesByMinMax(@PathParam("min") float min, @PathParam("max") float max) 
	{
		List<Objective> Objectives =  ObjectiveEjb.findObjectivesByMinMax(min, min);

		if (Objectives ==null)
			return Response.status(Status.NOT_FOUND).entity("Objective Not Found").build();

		else
			return Response.ok(Objectives).build();
	}

	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
	//  find Objectives by Min Max Res
	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 

	@GET
	@Path("findByObjectivesMinMaxRes/{min}/{max}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findObjectivesByMinMaxRes(@PathParam("min") float min, @PathParam("max") float max) 
	{
		List<Objective> Objectives =  ObjectiveEjb.findObjectivesByMinMaxRes(min, min);

		if (Objectives ==null)
			return Response.status(Status.NOT_FOUND).entity("Objective Not Found").build();

		else
			return Response.ok(Objectives).build();
	}

	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
	//  find Objective by Partition
	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
	@GET
	@Path("findObjectiveByPartition/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findObjectiveByPartition(@PathParam("id") Integer id){
		Objective Objective = ObjectiveEjb.findObjectiveByPartition(id);
		if (Objective==null)
			return Response.status(Status.NOT_FOUND).entity("Objective Not Found").build();
		else
			return Response.ok(Objective).build();

	}


	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
	//  find Objective by Partition
	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
	@GET
	@Path("findObjectivesByPartition/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findObjectivesByPartition(@PathParam("id") Integer id){
		List<Objective> Objectives = ObjectiveEjb.findObjectivesByPartition(id);
		if (Objectives==null)
			return Response.status(Status.NOT_FOUND).entity("Objective Not Found").build();
		else
			return Response.ok(Objectives).build();

	}

	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
	//  find Objective by Study
	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
	@GET
	@Path("findObjectiveByStudy/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findObjectiveByStudy(@PathParam("id") Integer id){
		Objective Objective = ObjectiveEjb.findObjectiveByStudy(id);
		if (Objective==null)
			return Response.status(Status.NOT_FOUND).entity("Objective Not Found").build();
		else
			return Response.ok(Objective).build();

	}


	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
	//  find Objectives by Study
	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
	@GET
	@Path("findObjectivesByStudy/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findObjectivesByStudy(@PathParam("id") Integer id){
		List<Objective> Objectives = ObjectiveEjb.findObjectivesByStudy(id);
		if (Objectives==null)
			return Response.status(Status.NOT_FOUND).entity("Objective Not Found").build();
		else
			return Response.ok(Objectives).build();

	}

	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
	//  find Objective by Project
	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
	@GET
	@Path("findObjectiveByProject/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findObjectiveByProject(@PathParam("id") Integer id){
		Objective Objective = ObjectiveEjb.findObjectiveByProject(id);
		if (Objective==null)
			return Response.status(Status.NOT_FOUND).entity("Objective Not Found").build();
		else
			return Response.ok(Objective).build();

	}


	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
	//  find Objectives by Project
	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
	@GET
	@Path("findObjectivesByProject/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findObjectivesByProject(@PathParam("id") Integer id){
		List<Objective> Objectives = ObjectiveEjb.findObjectivesByProject(id);
		if (Objectives==null)
			return Response.status(Status.NOT_FOUND).entity("Objective Not Found").build();
		else
			return Response.ok(Objectives).build();

	}


	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
	//  find Objective by Team
	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
	@GET
	@Path("findObjectiveByTeam/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findObjectiveByTeam(@PathParam("id") Integer id){
		Objective Objective = ObjectiveEjb.findObjectiveByTeam(id);
		if (Objective==null)
			return Response.status(Status.NOT_FOUND).entity("Objective Not Found").build();
		else
			return Response.ok(Objective).build();

	}

	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
	//  find Objectives by Team
	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
	@GET
	@Path("findObjectivesByTeam/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findObjectivesByTeam(@PathParam("id") Integer id){
		List<Objective> Objectives = ObjectiveEjb.findObjectivesByTeam(id);
		if (Objectives==null)
			return Response.status(Status.NOT_FOUND).entity("Objective Not Found").build();
		else
			return Response.ok(Objectives).build();

	}

	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
	//  find Objective by Engineer
	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
	@GET
	@Path("findObjectiveByEngineer/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findObjectiveByEngineer(@PathParam("id") Integer id){
		Objective Objective = ObjectiveEjb.findObjectiveByEngineer(id);
		if (Objective==null)
			return Response.status(Status.NOT_FOUND).entity("Objective Not Found").build();
		else
			return Response.ok(Objective).build();

	}

	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
	//  find Objectives by Engineer
	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
	@GET
	@Path("findObjectivesByEngineer/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findObjectivesByEngineer(@PathParam("id") Integer id){
		List<Objective> Objectives = ObjectiveEjb.findObjectivesByEngineer(id);
		if (Objectives==null)
			return Response.status(Status.NOT_FOUND).entity("Objective Not Found").build();
		else
			return Response.ok(Objectives).build();

	}

	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
	//  find Objective by Coordinator
	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
	@GET
	@Path("findObjectiveByCoordinator/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findObjectiveByCoordinator(@PathParam("id") Integer id){
		Objective Objective = ObjectiveEjb.findObjectiveByCoordinator(id);
		if (Objective==null)
			return Response.status(Status.NOT_FOUND).entity("Objective Not Found").build();
		else
			return Response.ok(Objective).build();

	}

	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
	//  find Objectives by Coordinator
	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
	@GET
	@Path("findObjectivesByCoordinator/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findObjectivesByCoordinator(@PathParam("id") Integer id){
		List<Objective> Objectives = ObjectiveEjb.findObjectivesByCoordinator(id);
		if (Objectives==null)
			return Response.status(Status.NOT_FOUND).entity("Objective Not Found").build();
		else
			return Response.ok(Objectives).build();

	}

	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
	//  find Objective by Manager
	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
	@GET
	@Path("findObjectiveByManager/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findObjectiveByManager(@PathParam("id") Integer id){
		Objective Objective = ObjectiveEjb.findObjectiveByManager(id);
		if (Objective==null)
			return Response.status(Status.NOT_FOUND).entity("Objective Not Found").build();
		else
			return Response.ok(Objective).build();

	}

	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
	//  find Objectives by Manager
	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
	@GET
	@Path("findObjectivesByManager/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findObjectivesByManager(@PathParam("id") Integer id){
		List<Objective> Objectives = ObjectiveEjb.findObjectivesByManager(id);
		if (Objectives==null)
			return Response.status(Status.NOT_FOUND).entity("Objective Not Found").build();
		else
			return Response.ok(Objectives).build();

	}

	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
	//  find Objective by Visibility
	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
	@GET
	@Path("findObjectiveByVisibility/{Visibility}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findObjectiveByVisibility(@PathParam("Visibility") Boolean Visibility){
		List<Objective> Objectives = ObjectiveEjb.findObjectiveByVisibility(Visibility);
		if (Objectives==null)
			return Response.status(Status.NOT_FOUND).entity("Objective Not Found").build();
		else
			return Response.ok(Objectives).build();

	}

	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
	//  find Shared Objective by Manager
	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
	@GET
	@Path("findSharedObjectiveByManager/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findSharedObjectiveByManager(@PathParam("id") Integer id){
		List<Objective> Objectives = ObjectiveEjb.findSharedObjectivesByManager(id);
		if (Objectives==null)
			return Response.status(Status.NOT_FOUND).entity("Objective Not Found").build();
		else
			return Response.ok(Objectives).build();

	}
	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
	//  find Local Objective by Manager
	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
	@GET
	@Path("findLocalObjectiveByManager/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findLocalObjectiveByManager(@PathParam("id") Integer id){
		List<Objective> Objectives = ObjectiveEjb.findLocalObjectivesByManager(id);
		if (Objectives==null)
			return Response.status(Status.NOT_FOUND).entity("Objective Not Found").build();
		else
			return Response.ok(Objectives).build();

	}

	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
	//  find Shared Objective by Coodinator
	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
	@GET
	@Path("findSharedObjectiveByCoodinator/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findSharedObjectiveByCoodinator(@PathParam("id") Integer id){
		List<Objective> Objectives = ObjectiveEjb.findLocalObjectivesByCoordinator(id);
		if (Objectives==null)
			return Response.status(Status.NOT_FOUND).entity("Objective Not Found").build();
		else
			return Response.ok(Objectives).build();

	}
	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
	//  find Local Objective by Engineer
	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
	@GET
	@Path("findLocalObjectiveByEngineer/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findLocalObjectiveByEngineer(@PathParam("id") Integer id){
		List<Objective> Objectives = ObjectiveEjb.findLocalObjectivesByEngineer(id);
		if (Objectives==null)
			return Response.status(Status.NOT_FOUND).entity("Objective Not Found").build();
		else
			return Response.ok(Objectives).build();

	}
	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
	//  find Objective with set
	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
	@GET
	@Path("findObjectiveWithSet/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findObjectiveWitSet(@PathParam("id") Integer id){
		List<Set> Objectives = ObjectiveEjb.findObjectivewithSet(id);
		if (Objectives==null)
			return Response.status(Status.NOT_FOUND).entity("Objective Not Found").build();
		else
			return Response.ok(Objectives).build();

	}
}
