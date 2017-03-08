package Services;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Path;

import com.supmeca.colMEA.business.UserServiceRemote;
import com.supmeca.colMEA.domain.*;
@Stateless
@Path("/user")
public class UserRessource {

	@EJB
	private UserServiceRemote UserEjb;
	
	public List<User> getAllUsers(){
		return UserEjb.findAllUsers();
	
	}


}
