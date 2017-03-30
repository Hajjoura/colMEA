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

import com.supmeca.colMEA.business.ConstraintServiceLocal;
import com.supmeca.colMEA.domain.Constraint;
import com.supmeca.colMEA.domain.Set;
import com.supmeca.colMEA.domain.Constraint;

@Path("/Constraints")
public class ConstraintRessource {
	@Inject
	ConstraintServiceLocal ConstraintEjb;


	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response findAllConstraints(){
		
		List<Constraint> Constraints = null;
		Constraints = ConstraintEjb.findAllConstraints();
		if (Constraints==null)
			return Response.status(Status.NOT_FOUND).build();
		else
			return Response.ok(Constraints).build();
			
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	
	public Response addConstraint (Constraint Constraint, @Context UriInfo uriInfo) {
		ConstraintEjb.CreateConstraint(Constraint);
		UriBuilder builder = uriInfo.getAbsolutePathBuilder();
		return Response.created(builder.build()).entity(Constraint).build();
	}
 
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateConstraint(Constraint Constraint){
		ConstraintEjb.EditConstraint(Constraint);
		if (Constraint == null)
			return Response.status(Status.ACCEPTED).entity("User successfully Updated").build();
		else
			return Response.status(Status.NOT_FOUND).entity("User Not found").build();
	}
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteConstraint(Constraint Constraint){
		ConstraintEjb.removeConstraint(Constraint);
	if (Constraint == null)
		return Response.status(Status.ACCEPTED).entity("User successfully Deleted").build();
	else
		return Response.status(Status.NOT_FOUND).entity("User Not found").build();
	}
	@GET
	@Path("findConstraint/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findConstraintById(@PathParam("id") Integer id){
		Constraint En = ConstraintEjb.findConstraintById(id);
	 if (En==null)
			return Response.status(Status.NOT_FOUND).entity("User Not Found").build();
		else
			return Response.ok(En).build();
	 
	}
	@DELETE
	@Path("DeleteConstraint/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteConstraint(@PathParam("id") Integer id){
		ConstraintEjb.DeleteConstraint(id);
	if (id == null)
		return Response.status(Status.ACCEPTED).entity("User successfully Deleted").build();
	else
		return Response.status(Status.NOT_FOUND).entity("User Not found").build();
	}
	
	//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
		//  find Constraint by Name
		//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
		@GET
		@Path("findConstraintByName/{name}")
		@Produces(MediaType.APPLICATION_JSON)
		public Response findConstraintByName(@PathParam("name") String name){
			Constraint Constraint = ConstraintEjb.findConstraintByName(name);
			if (Constraint==null)
				return Response.status(Status.NOT_FOUND).entity("Constraint Not Found").build();
			else
				return Response.ok(Constraint).build();

		}
		//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
		//  find Constraint by Min Max
		//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 

		@GET
		@Path("findByMinMax/{min}/{max}")
		@Produces(MediaType.APPLICATION_JSON)
		public Response findByMinMax(@PathParam("min") float min, @PathParam("max") float max) 
		{
			Constraint Constraint =  ConstraintEjb.findConstraintByMinMax(min, min);

			if (Constraint ==null)
				return Response.status(Status.NOT_FOUND).entity("Constraint Not Found").build();

			else
				return Response.ok(Constraint).build();
		}

		//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
		//  find Constraint by Min Max Res
		//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 

		@GET
		@Path("findByMinMaxRes/{min}/{max}")
		@Produces(MediaType.APPLICATION_JSON)
		public Response findByMinMaxRes(@PathParam("min") float min, @PathParam("max") float max) 
		{
			Constraint Constraint =  ConstraintEjb.findConstraintByMinMaxRes(min, min);

			if (Constraint ==null)
				return Response.status(Status.NOT_FOUND).entity("Constraint Not Found").build();

			else
				return Response.ok(Constraint).build();
		}

		//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
		//  find Constraints by Min Max
		//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 

		@GET
		@Path("findConstraintsByMinMax/{min}/{max}")
		@Produces(MediaType.APPLICATION_JSON)
		public Response findConstraintsByMinMax(@PathParam("min") float min, @PathParam("max") float max) 
		{
			List<Constraint> Constraints =  ConstraintEjb.findConstraintsByMinMax(min, min);

			if (Constraints ==null)
				return Response.status(Status.NOT_FOUND).entity("Constraint Not Found").build();

			else
				return Response.ok(Constraints).build();
		}

		//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
		//  find Constraints by Min Max Res
		//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 

		@GET
		@Path("findByConstraintsMinMaxRes/{min}/{max}")
		@Produces(MediaType.APPLICATION_JSON)
		public Response findConstraintsByMinMaxRes(@PathParam("min") float min, @PathParam("max") float max) 
		{
			List<Constraint> Constraints =  ConstraintEjb.findConstraintsByMinMaxRes(min, min);

			if (Constraints ==null)
				return Response.status(Status.NOT_FOUND).entity("Constraint Not Found").build();

			else
				return Response.ok(Constraints).build();
		}

		//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
		//  find Constraint by Partition
		//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
		@GET
		@Path("findConstraintByPartition/{id}")
		@Produces(MediaType.APPLICATION_JSON)
		public Response findConstraintByPartition(@PathParam("id") Integer id){
			Constraint Constraint = ConstraintEjb.findConstraintByPartition(id);
			if (Constraint==null)
				return Response.status(Status.NOT_FOUND).entity("Constraint Not Found").build();
			else
				return Response.ok(Constraint).build();

		}


		//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
		//  find Constraint by Partition
		//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
		@GET
		@Path("findConstraintsByPartition/{id}")
		@Produces(MediaType.APPLICATION_JSON)
		public Response findConstraintsByPartition(@PathParam("id") Integer id){
			List<Constraint> Constraints = ConstraintEjb.findConstraintsByPartition(id);
			if (Constraints==null)
				return Response.status(Status.NOT_FOUND).entity("Constraint Not Found").build();
			else
				return Response.ok(Constraints).build();

		}

		//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
		//  find Constraint by Study
		//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
		@GET
		@Path("findConstraintByStudy/{id}")
		@Produces(MediaType.APPLICATION_JSON)
		public Response findConstraintByStudy(@PathParam("id") Integer id){
			Constraint Constraint = ConstraintEjb.findConstraintByStudy(id);
			if (Constraint==null)
				return Response.status(Status.NOT_FOUND).entity("Constraint Not Found").build();
			else
				return Response.ok(Constraint).build();

		}


		//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
		//  find Constraints by Study
		//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
		@GET
		@Path("findConstraintsByStudy/{id}")
		@Produces(MediaType.APPLICATION_JSON)
		public Response findConstraintsByStudy(@PathParam("id") Integer id){
			List<Constraint> Constraints = ConstraintEjb.findConstraintsByStudy(id);
			if (Constraints==null)
				return Response.status(Status.NOT_FOUND).entity("Constraint Not Found").build();
			else
				return Response.ok(Constraints).build();

		}

		//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
		//  find Constraint by Project
		//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
		@GET
		@Path("findConstraintByProject/{id}")
		@Produces(MediaType.APPLICATION_JSON)
		public Response findConstraintByProject(@PathParam("id") Integer id){
			Constraint Constraint = ConstraintEjb.findConstraintByProject(id);
			if (Constraint==null)
				return Response.status(Status.NOT_FOUND).entity("Constraint Not Found").build();
			else
				return Response.ok(Constraint).build();

		}


		//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
		//  find Constraints by Project
		//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
		@GET
		@Path("findConstraintsByProject/{id}")
		@Produces(MediaType.APPLICATION_JSON)
		public Response findConstraintsByProject(@PathParam("id") Integer id){
			List<Constraint> Constraints = ConstraintEjb.findConstraintsByProject(id);
			if (Constraints==null)
				return Response.status(Status.NOT_FOUND).entity("Constraint Not Found").build();
			else
				return Response.ok(Constraints).build();

		}


		//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
		//  find Constraint by Team
		//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
		@GET
		@Path("findConstraintByTeam/{id}")
		@Produces(MediaType.APPLICATION_JSON)
		public Response findConstraintByTeam(@PathParam("id") Integer id){
			Constraint Constraint = ConstraintEjb.findConstraintByTeam(id);
			if (Constraint==null)
				return Response.status(Status.NOT_FOUND).entity("Constraint Not Found").build();
			else
				return Response.ok(Constraint).build();

		}

		//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
		//  find Constraints by Team
		//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
		@GET
		@Path("findConstraintsByTeam/{id}")
		@Produces(MediaType.APPLICATION_JSON)
		public Response findConstraintsByTeam(@PathParam("id") Integer id){
			List<Constraint> Constraints = ConstraintEjb.findConstraintsByTeam(id);
			if (Constraints==null)
				return Response.status(Status.NOT_FOUND).entity("Constraint Not Found").build();
			else
				return Response.ok(Constraints).build();

		}

		//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
		//  find Constraint by Engineer
		//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
		@GET
		@Path("findConstraintByEngineer/{id}")
		@Produces(MediaType.APPLICATION_JSON)
		public Response findConstraintByEngineer(@PathParam("id") Integer id){
			Constraint Constraint = ConstraintEjb.findConstraintByEngineer(id);
			if (Constraint==null)
				return Response.status(Status.NOT_FOUND).entity("Constraint Not Found").build();
			else
				return Response.ok(Constraint).build();

		}

		//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
		//  find Constraints by Engineer
		//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
		@GET
		@Path("findConstraintsByEngineer/{id}")
		@Produces(MediaType.APPLICATION_JSON)
		public Response findConstraintsByEngineer(@PathParam("id") Integer id){
			List<Constraint> Constraints = ConstraintEjb.findConstraintsByEngineer(id);
			if (Constraints==null)
				return Response.status(Status.NOT_FOUND).entity("Constraint Not Found").build();
			else
				return Response.ok(Constraints).build();

		}

		//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
		//  find Constraint by Coordinator
		//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
		@GET
		@Path("findConstraintByCoordinator/{id}")
		@Produces(MediaType.APPLICATION_JSON)
		public Response findConstraintByCoordinator(@PathParam("id") Integer id){
			Constraint Constraint = ConstraintEjb.findConstraintByCoordinator(id);
			if (Constraint==null)
				return Response.status(Status.NOT_FOUND).entity("Constraint Not Found").build();
			else
				return Response.ok(Constraint).build();

		}

		//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
		//  find Constraints by Coordinator
		//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
		@GET
		@Path("findConstraintsByCoordinator/{id}")
		@Produces(MediaType.APPLICATION_JSON)
		public Response findConstraintsByCoordinator(@PathParam("id") Integer id){
			List<Constraint> Constraints = ConstraintEjb.findConstraintsByCoordinator(id);
			if (Constraints==null)
				return Response.status(Status.NOT_FOUND).entity("Constraint Not Found").build();
			else
				return Response.ok(Constraints).build();

		}

		//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
		//  find Constraint by Manager
		//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
		@GET
		@Path("findConstraintByManager/{id}")
		@Produces(MediaType.APPLICATION_JSON)
		public Response findConstraintByManager(@PathParam("id") Integer id){
			Constraint Constraint = ConstraintEjb.findConstraintByManager(id);
			if (Constraint==null)
				return Response.status(Status.NOT_FOUND).entity("Constraint Not Found").build();
			else
				return Response.ok(Constraint).build();

		}

		//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
		//  find Constraints by Manager
		//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
		@GET
		@Path("findConstraintsByManager/{id}")
		@Produces(MediaType.APPLICATION_JSON)
		public Response findConstraintsByManager(@PathParam("id") Integer id){
			List<Constraint> Constraints = ConstraintEjb.findConstraintsByManager(id);
			if (Constraints==null)
				return Response.status(Status.NOT_FOUND).entity("Constraint Not Found").build();
			else
				return Response.ok(Constraints).build();

		}

		//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
		//  find Constraint by Visibility
		//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
		@GET
		@Path("findConstraintByVisibility/{Visibility}")
		@Produces(MediaType.APPLICATION_JSON)
		public Response findConstraintByVisibility(@PathParam("Visibility") Boolean Visibility){
			List<Constraint> Constraints = ConstraintEjb.findConstraintByVisibility(Visibility);
			if (Constraints==null)
				return Response.status(Status.NOT_FOUND).entity("Constraint Not Found").build();
			else
				return Response.ok(Constraints).build();

		}

		//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
		//  find Shared Constraint by Manager
		//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
		@GET
		@Path("findSharedConstraintByManager/{id}")
		@Produces(MediaType.APPLICATION_JSON)
		public Response findSharedConstraintByManager(@PathParam("id") Integer id){
			List<Constraint> Constraints = ConstraintEjb.findSharedConstraintsByManager(id);
			if (Constraints==null)
				return Response.status(Status.NOT_FOUND).entity("Constraint Not Found").build();
			else
				return Response.ok(Constraints).build();

		}
		//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
		//  find Local Constraint by Manager
		//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
		@GET
		@Path("findLocalConstraintByManager/{id}")
		@Produces(MediaType.APPLICATION_JSON)
		public Response findLocalConstraintByManager(@PathParam("id") Integer id){
			List<Constraint> Constraints = ConstraintEjb.findLocalConstraintsByManager(id);
			if (Constraints==null)
				return Response.status(Status.NOT_FOUND).entity("Constraint Not Found").build();
			else
				return Response.ok(Constraints).build();

		}

		//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
		//  find Shared Constraint by Coodinator
		//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
		@GET
		@Path("findSharedConstraintByCoodinator/{id}")
		@Produces(MediaType.APPLICATION_JSON)
		public Response findSharedConstraintByCoodinator(@PathParam("id") Integer id){
			List<Constraint> Constraints = ConstraintEjb.findLocalConstraintsByCoordinator(id);
			if (Constraints==null)
				return Response.status(Status.NOT_FOUND).entity("Constraint Not Found").build();
			else
				return Response.ok(Constraints).build();

		}
		//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
		//  find Local Constraint by Engineer
		//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
		@GET
		@Path("findLocalConstraintByEngineer/{id}")
		@Produces(MediaType.APPLICATION_JSON)
		public Response findLocalConstraintByEngineer(@PathParam("id") Integer id){
			List<Constraint> Constraints = ConstraintEjb.findLocalConstraintsByEngineer(id);
			if (Constraints==null)
				return Response.status(Status.NOT_FOUND).entity("Constraint Not Found").build();
			else
				return Response.ok(Constraints).build();

		}
		//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
		//  find Constraint with set
		//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
		@GET
		@Path("findConstraintWithSet/{id}")
		@Produces(MediaType.APPLICATION_JSON)
		public Response findConstraintWitSet(@PathParam("id") Integer id){
			List<Set> Constraints = ConstraintEjb.findConstraintwithSet(id);
			if (Constraints==null)
				return Response.status(Status.NOT_FOUND).entity("Constraint Not Found").build();
			else
				return Response.ok(Constraints).build();

		}
}
