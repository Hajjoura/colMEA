package Services;

import java.util.Date;
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
import com.supmeca.colMEA.business.TeamServiceLocal;
import com.supmeca.colMEA.domain.Engineer;
import com.supmeca.colMEA.domain.Partition;
import com.supmeca.colMEA.domain.Team;
import com.supmeca.colMEA.domain.Teams_Engineers;
import com.supmeca.colMEA.domain.Teams_EngineersFK;
import com.supmeca.colMEA.domain.Variable;
import com.supmeca.colMEA.domain.Variables_Partitions;
import com.supmeca.colMEA.domain.Variables_PartitionsFK;

@Path("/Engineers")
public class EngineerRessource {

	@Inject
	EngineerServiceLocal EngineerEjb;
	
	@Inject
	TeamServiceLocal TeamEjb;
	
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
	
	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
	//  Add Team to engineer service
	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 

			@POST
			@Path("addTeam/{id_team}/{id_user}")
			@Produces("application/json")
			@Consumes("application/json")
		 	
			public Response addTeam (@PathParam("id_team")Integer id_team,@PathParam("id_user")Integer id_user)
			{			 			
				/*Date dateReservation=null;
				SimpleDateFormat simple_date= new 
						SimpleDateFormat("dd/MM/yyyy");
				try {
					 dateReservation =  (Date) simple_date.parse(date_s);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
				
			
				Teams_Engineers teamEng = new Teams_Engineers();
				
				Teams_EngineersFK teamEngFK = new Teams_EngineersFK();
				teamEngFK.setId_team(id_team);
				teamEngFK.setId_user(id_user);
				
				Team team = TeamEjb.findTeamById(id_team);
				Engineer engineer = EngineerEjb.findEngineerById(id_user);
								
				if ((team!=null)&&(engineer!=null))
				{
					if (EngineerEjb.addEngineerToTeam(team, engineer))
								  {
								return Response.status(Status.ACCEPTED).entity("Success team was added").build();
			 			}else
				 		{
							return Response.status(Status.NOT_FOUND).build();
				 		}			 
				}
				 else
				 {
						return Response.status(Status.NOT_FOUND).entity(" Team/Engineer Not Found").build();
				 }
			}
//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
//  find Enginner By Team
//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 

		@GET
		@Path("findEnginnerByTeam/{id}")
		@Produces(MediaType.APPLICATION_JSON)
		public Response findEnginnerByTeam(@PathParam("id") Integer id){
			Engineer eng =  EngineerEjb.findEngineerByTeam(id);
			 if (eng==null)
					return Response.status(Status.NOT_FOUND).entity("User Not Found").build();
				else
					return Response.ok(eng).build();
			}
	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
	//  find Enginners By Team
	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 

			@GET
			@Path("findEnginnersByTeam/{id}")
			@Produces(MediaType.APPLICATION_JSON)
			public Response findEnginnersByTeam(@PathParam("id") Integer id){
				List<Engineer> Engineers = null;
				Engineers=  EngineerEjb.findEngineersByTeam(id);
				 if (Engineers==null)
						return Response.status(Status.NOT_FOUND).entity("User Not Found").build();
					else
						return Response.ok(Engineers).build();
				}
//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
//  find Enginners By Name Team
//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
		@GET
		@Path("findEnginnersByNameTeam/{name}")
		@Produces(MediaType.APPLICATION_JSON)
		public Response findEnginnersByNameTeam(@PathParam("name") String name){
			List<Engineer> Engineers = null;
			Engineers=  EngineerEjb.findEngineersByNameTeam(name);
			if (Engineers==null)
				return Response.status(Status.NOT_FOUND).entity("User Not Found").build();
			else
				return Response.ok(Engineers).build();
		}
//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
//  find Enginners By Name Domain
//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
	@GET
	@Path("findEnginnersByNameDomain/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findEnginnersByNameDomain(@PathParam("name") String name){
		List<Engineer> Engineers = null;
		Engineers=  EngineerEjb.findEngineersByDomain(name);
		if (Engineers==null)
			return Response.status(Status.NOT_FOUND).entity("User Not Found").build();
		else
			return Response.ok(Engineers).build();
	}
//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
//  find Enginners By Name Domain
//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
	@GET
	@Path("findEnginnersByProject/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findEnginnersByProject(@PathParam("name") String name){
		List<Engineer> Engineers = null;
		Engineers=  EngineerEjb.findEngineersByProject(name);
		if (Engineers==null)
			return Response.status(Status.NOT_FOUND).entity("User Not Found").build();
		else
			return Response.ok(Engineers).build();
	}
	
//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
//  find Enginners By Coordiantor ID
//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
	@GET
	@Path("findEnginnersByCoordinator/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findEnginnersByCoordinator(@PathParam("id") Integer id){
		List<Engineer> Engineers = null;
		Engineers=  EngineerEjb.findEngineersByCoordinator(id);
		if (Engineers==null)
			return Response.status(Status.NOT_FOUND).entity("User Not Found").build();
		else
			return Response.ok(Engineers).build();
	}
//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
//  find Enginners By Coordiantor Login
//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
	@GET
	@Path("findEnginnersByCoordinatorLogin/{login}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findEnginnersByCoordinatorLogin(@PathParam("login") String login){
		List<Engineer> Engineers = null;
		Engineers=  EngineerEjb.findEngineersByCoordinatorName(login);
		if (Engineers==null)
			return Response.status(Status.NOT_FOUND).entity("User Not Found").build();
		else
			return Response.ok(Engineers).build();
	}


			
}
