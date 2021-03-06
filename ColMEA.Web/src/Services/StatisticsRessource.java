package Services;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.supmeca.colMEA.business.StatisticsServiceLocal;

@Path("/Statistics")
public class StatisticsRessource {

	@Inject
	StatisticsServiceLocal StatisticsEjb;

	@GET
	@Path("NbrPartitionsByStudy/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response NumberPartitionsByStudy(@PathParam("id") Integer id){

		Integer count = StatisticsEjb.NumberPartitionsByStudy(id);
		if (count==null)
			return Response.status(Status.NOT_FOUND).build();
		else
			return Response.ok(count).build();

	}

	@GET
	@Path("NbrVariablesByProject/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response NumberVariablesByProject(@PathParam("id") Integer id){

		Integer count = StatisticsEjb.NumberVariablesByProject(id);
		if (count==null)
			return Response.status(Status.NOT_FOUND).build();
		else
			return Response.ok(count).build();

	}
	@GET
	@Path("NbrVariablesByPartition/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response NumberVariablesByPartition(@PathParam("id") Integer id){

		Integer count = StatisticsEjb.NumberVariablesByPartition(id);
		if (count==null)
			return Response.status(Status.NOT_FOUND).build();
		else
			return Response.ok(count).build();

	}

	@GET
	@Path("NbrVariablesByStudy/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response NumberVariablesByStudy(@PathParam("id") Integer id){

		Integer count = StatisticsEjb.NumberVariablesByStudy(id);
		if (count==null)
			return Response.status(Status.NOT_FOUND).build();
		else
			return Response.ok(count).build();

	}
	@GET
	@Path("NbrConstraintsByPartition/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response NumberConstraintsByPartition(@PathParam("id") Integer id){

		Integer count = StatisticsEjb.NumberConstraintsByPartition(id);
		if (count==null)
			return Response.status(Status.NOT_FOUND).build();
		else
			return Response.ok(count).build();

	}
	@GET
	@Path("NbrObjectivesByPartition/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response NumberObjectivesByPartition(@PathParam("id") Integer id){

		Integer count = StatisticsEjb.NumberObjectivesByPartition(id);
		if (count==null)
			return Response.status(Status.NOT_FOUND).build();
		else
			return Response.ok(count).build();

	}

	@GET
	@Path("NbrStudiesByProject/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response NumberStudiesByProject(@PathParam("id") Integer id){

		Integer count = StatisticsEjb.NumberStudiesByProject(id);
		if (count==null)
			return Response.status(Status.NOT_FOUND).build();
		else
			return Response.ok(count).build();

	}
	@GET
	@Path("NbrEngineersByProject/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response NumberEngineersByProject(@PathParam("id") Integer id){

		Integer count = StatisticsEjb.NumberEngineersByProject(id);
		if (count==null)
			return Response.status(Status.NOT_FOUND).build();
		else
			return Response.ok(count).build();

	}

	@GET
	@Path("NbrEngineersByTeam/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response NumberEngineersByTeam(@PathParam("id") Integer id){

		Integer count = StatisticsEjb.NumberEngineersByTeam(id);
		if (count==null)
			return Response.status(Status.NOT_FOUND).build();
		else
			return Response.ok(count).build();

	}

	@GET
	@Path("NbrProjectsByManager/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response NumberProjectsBuManager(@PathParam("id") Integer id){

		Integer count = StatisticsEjb.NumberProjectsByManager(id);
		if (count==null)
			return Response.status(Status.NOT_FOUND).build();
		else
			return Response.ok(count).build();

	}

	@GET
	@Path("NbrTeamsByEngineer/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response NumberTeamsByEngineer(@PathParam("id") Integer id){

		Integer count = StatisticsEjb.NumberTeamsByEnginner(id);
		if (count==null)
			return Response.status(Status.NOT_FOUND).build();
		else
			return Response.ok(count).build();

	}
	@GET
	@Path("NbrTeamsByCoordinator/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response NumberTeamsByCoordinator(@PathParam("id") Integer id){

		Integer count = StatisticsEjb.NumberTeamsByCoordinator(id);
		if (count==null)
			return Response.status(Status.NOT_FOUND).build();
		else
			return Response.ok(count).build();

	}

	@GET
	@Path("NbrTeams")
	@Produces(MediaType.APPLICATION_JSON)
	public Response NumberTeams(){

		Integer count = StatisticsEjb.NumberTeams();
		if (count==null)
			return Response.status(Status.NOT_FOUND).build();
		else
			return Response.ok(count).build();

	}
	@GET
	@Path("NbrProjects")
	@Produces(MediaType.APPLICATION_JSON)
	public Response NumberProjects(){

		Integer count = StatisticsEjb.NumberProjects();
		if (count==null)
			return Response.status(Status.NOT_FOUND).build();
		else
			return Response.ok(count).build();

	}
	@GET
	@Path("NbrPartitions")
	@Produces(MediaType.APPLICATION_JSON)
	public Response NumberPartitions(){

		Integer count = StatisticsEjb.NumberPartitions();
		if (count==null)
			return Response.status(Status.NOT_FOUND).build();
		else
			return Response.ok(count).build();

	}
	@GET
	@Path("NbrEngineers")
	@Produces(MediaType.APPLICATION_JSON)
	public Response NumberEngineers(){

		Integer count = StatisticsEjb.NumberEngineers();
		if (count==null)
			return Response.status(Status.NOT_FOUND).build();
		else
			return Response.ok(count).build();

	}

}
