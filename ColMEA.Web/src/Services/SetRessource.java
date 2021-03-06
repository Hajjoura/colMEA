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

import com.supmeca.colMEA.business.IntervalServiceLocal;
import com.supmeca.colMEA.business.SetServiceLocal;
import com.supmeca.colMEA.business.VariablesServiceLocal;
import com.supmeca.colMEA.domain.Set;
import com.supmeca.colMEA.domain.Interval;
import com.supmeca.colMEA.domain.Variable;

@Path("/Sets")
public class SetRessource {

	@Inject
	SetServiceLocal SetEjb;

	@Inject
	IntervalServiceLocal IntervalEjb;
	@Inject
	VariablesServiceLocal VariableEjb;
	
	@GET
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
	@Path("addSet/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)

	public Response CreateSet (@PathParam("id") Integer id,Set set, @Context UriInfo uriInfo) {
		Variable v = VariableEjb.findVariableById(id);
		SetEjb.CreateSet(set,v);
		UriBuilder builder = uriInfo.getAbsolutePathBuilder();
		return Response.created(builder.build()).entity(set).build();
	}
	@POST
	@Path("addSet")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)

	public Response addSet (Set set, @Context UriInfo uriInfo) {
		
		SetEjb.addSet(set);
		UriBuilder builder = uriInfo.getAbsolutePathBuilder();
		return Response.created(builder.build()).entity(set).build();
	}
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateSet(Set set){
		SetEjb.EditSet(set);
		if (set == null)
			return Response.status(Status.ACCEPTED).entity("Set successfully Updated").build();
		else
			return Response.status(Status.NOT_FOUND).entity("Set Not found").build();
	}
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteSet(Set set){
		SetEjb.removeSet(set);
		if (set == null)
			return Response.status(Status.ACCEPTED).entity("Set successfully Deleted").build();
		else
			return Response.status(Status.NOT_FOUND).entity("Set Not found").build();
	}
	@GET
	@Path("findSet/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findSetById(@PathParam("id") Integer id){
		Set s = SetEjb.findSetById(id);
		if (s==null)
			return Response.status(Status.NOT_FOUND).entity("Set Not Found").build();
		else
			return Response.ok(s).build();

	}

	@DELETE
	@Path("DeleteSet/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteSet(@PathParam("id") Integer id){
		SetEjb.DeleteSet(id);
		if (id == null)
			return Response.status(Status.ACCEPTED).entity("Set successfully Deleted").build();
		else
			return Response.status(Status.NOT_FOUND).entity("Set Not found").build();
	}
	@GET
	@Path("findLastSet")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findSLastetById(){
		Set s = SetEjb.getLastRowSet();
		if (s==null)
			return Response.status(Status.NOT_FOUND).entity("Set Not Found").build();
		else
			return Response.ok(s).build();

	}
	
	//*************************************************************************//
	//************************* Intervals ************************************//
	//***********************************************************************//

	@GET
	@Path("/Intervals")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findAllIntervals(){
		
		List<Interval> Intervals = null;
		Intervals = IntervalEjb.findAllIntervals();
		if (Intervals==null)
			return Response.status(Status.NOT_FOUND).build();
		else
			return Response.ok(Intervals).build();
			
	}
	
	@POST
	@Path("/addInterval")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	
	public Response addInterval (Interval Interval, @Context UriInfo uriInfo) {
		IntervalEjb.CreateInterval(Interval);
		UriBuilder builder = uriInfo.getAbsolutePathBuilder();
		return Response.created(builder.build()).entity(Interval).build();
	}
 
	@PUT
	@Path("/updateInterval")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateInterval(Interval Interval){
		IntervalEjb.EditInterval(Interval);
		if (Interval == null)
			return Response.status(Status.ACCEPTED).entity("Interval successfully Updated").build();
		else
			return Response.status(Status.NOT_FOUND).entity("Interval Not found").build();
	}
	@DELETE
	@Path("/deleteInterval")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteInterval(Interval Interval){
		IntervalEjb.removeInterval(Interval);
	if (Interval == null)
		return Response.status(Status.ACCEPTED).entity("Interval successfully Deleted").build();
	else
		return Response.status(Status.NOT_FOUND).entity("Interval Not found").build();
	}
	@GET
	@Path("findInterval/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findIntervalById(@PathParam("id") Integer id){
		Interval En = IntervalEjb.findIntervalById(id);
	 if (En==null)
			return Response.status(Status.NOT_FOUND).entity("Interval Not Found").build();
		else
			return Response.ok(En).build();
	 
	}
	@DELETE
	@Path("DeleteInterval/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteInterval(@PathParam("id") Integer id){
		IntervalEjb.DeleteInterval(id);
	if (id == null)
		return Response.status(Status.ACCEPTED).entity("Interval successfully Deleted").build();
	else
		return Response.status(Status.NOT_FOUND).entity("Interval Not found").build();
	}
	
	@GET
	@Path("findIntervalsBySet/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findIntervalByIdSet(@PathParam("id") Integer id){
		List<Interval> En = IntervalEjb.findIntervalsByIdSet(id);
	 if (En==null)
			return Response.status(Status.NOT_FOUND).entity("Interval Not Found").build();
		else
			return Response.ok(En).build();
	 
	}
	@GET
	@Path("findSetByMinMax/{min}/{max}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findIntervalByIdSet(@PathParam("min") Float min,@PathParam("max") Float max){
		List<Set> En = SetEjb.findSetByMinMax(min, max);
	 if (En==null)
			return Response.status(Status.NOT_FOUND).entity("Interval Not Found").build();
		else
			return Response.ok(En).build();
	 
	}
	
	@GET
	@Path("getLatestRowSet/{num}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response fgetLatestRowSet(@PathParam("num") Integer num){
		List<Set> En = SetEjb.getLatestRowSet(num);
	 if (En==null)
			return Response.status(Status.NOT_FOUND).entity("Interval Not Found").build();
		else
			return Response.ok(En).build();
	 
	}
}


