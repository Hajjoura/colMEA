package com.supmeca.colMEA.business;

import java.util.List;

import javax.ejb.Remote;

import com.supmeca.colMEA.domain.User;

@Remote
public interface UserServiceRemote {
	public void CreateClient( User User) ;
	public void EditClient (User User);
	public void removeClient(User User);
	public User findUserById(int id);
	public List<User> findAllUsers() ;

}
