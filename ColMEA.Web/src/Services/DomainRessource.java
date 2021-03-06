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

import com.supmeca.colMEA.business.DomainServiceLocal;
import com.supmeca.colMEA.domain.Domain;

@Path("/Domains")
public class DomainRessource {

	@Inject
	DomainServiceLocal DomainEjb;


	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response findAllDomains(){
		
		List<Domain> Domains = null;
		Domains = DomainEjb.findAllDomains();
		if (Domains==null)
			return Response.status(Status.NOT_FOUND).build();
		else
			return Response.ok(Domains).build();
			
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	
	public Response addDomain (Domain Domain, @Context UriInfo uriInfo) {
		DomainEjb.CreateDomain(Domain);
		UriBuilder builder = uriInfo.getAbsolutePathBuilder();
		return Response.created(builder.build()).entity(Domain).build();
	}
 
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateDomain(Domain Domain){
		DomainEjb.EditDomain(Domain);
		if (Domain == null)
			return Response.status(Status.ACCEPTED).entity("User successfully Updated").build();
		else
			return Response.status(Status.NOT_FOUND).entity("User Not found").build();
	}
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteDomain(Domain Domain){
		DomainEjb.removeDomain(Domain);
	if (Domain == null)
		return Response.status(Status.ACCEPTED).entity("User successfully Deleted").build();
	else
		return Response.status(Status.NOT_FOUND).entity("User Not found").build();
	}
	@GET
	@Path("findDomain/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findDomainById(@PathParam("id") Integer id){
		Domain En = DomainEjb.findDomainById(id);
	 if (En==null)
			return Response.status(Status.NOT_FOUND).entity("User Not Found").build();
		else
			return Response.ok(En).build();
	 
	}
	@DELETE
	@Path("DeleteDomain/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteDomain(@PathParam("id") Integer id){
		DomainEjb.DeleteDomain(id);
	if (id == null)
		return Response.status(Status.ACCEPTED).entity("User successfully Deleted").build();
	else
		return Response.status(Status.NOT_FOUND).entity("User Not found").build();
	}
	
//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
//  find Domain by Enginner
//-*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*--*-*-*-*-*-*- 
	@GET
	@Path("findDomainByEngineer/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findDomainByEngineer(@PathParam("id") Integer id){
		Domain dom = DomainEjb.findDomainByEngineer(id);
	 if (dom==null)
			return Response.status(Status.NOT_FOUND).entity("User Not Found").build();
		else
			return Response.ok(dom).build();
	}
}
