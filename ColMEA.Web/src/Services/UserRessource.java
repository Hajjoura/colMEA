package Services;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.supmeca.colMEA.business.UserServiceLocal;
import com.supmeca.colMEA.domain.*;
@Stateless
@Path("/Users")
public class UserRessource {

	@Inject
	private UserServiceLocal UserEjb;
	
	@GET
	@Produces("application/json")
	public Response findAllUsers(){
		List<User> users = null;
		users = UserEjb.findAllUsers();
		if (users==null)
			return Response.status(Status.NOT_FOUND).build();
		else
			return Response.ok(users).build();
		
	}
	@GET
	@Path("auth/{login}/{password}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response authenticateUser(@PathParam("login") String login, @PathParam("password") String password) 
	{
		User user = null;
		if (password == null)
			user = UserEjb.findUserByLogin(login);
		else 
			user = UserEjb.authentication(login, password);
		
		if (user==null)
			return Response.status(Status.NOT_FOUND).build();
		else
			return Response.ok(user).build();
		
	}
	
	@GET
	@Path("findByName/{first}/{last}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findUserByName(@PathParam("first") String first_Name, @PathParam("last") String last_Name) 
	{
		User user = null;
		if ((first_Name!=null)&&(last_Name!=null))
		{	
			user = UserEjb.findUserByName(first_Name, last_Name);
		}
		
		
		 if (user ==null)
		 {
				return Response.status(Status.NOT_FOUND).entity("User Not Found").build();
		 }
			else
			{
				return Response.ok(user).build();
			}
		
	}

}
