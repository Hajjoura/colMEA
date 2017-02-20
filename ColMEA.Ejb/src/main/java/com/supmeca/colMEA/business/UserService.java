package com.supmeca.colMEA.business;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.supmeca.colMEA.domain.User;

/**
 * Session Bean implementation class UserService
 */
@Stateless
@LocalBean
public class UserService implements UserServiceRemote, UserServiceLocal {

    /**
     * Default constructor. 
     */
    public UserService() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void CreateClient(User User) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void EditClient(User User) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeClient(User User) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User findUserById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> findAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

}
