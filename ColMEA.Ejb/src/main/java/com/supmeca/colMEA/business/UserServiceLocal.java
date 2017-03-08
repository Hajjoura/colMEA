package com.supmeca.colMEA.business;

import java.util.List;

import javax.ejb.Local;

import com.supmeca.colMEA.domain.User;

@Local
public interface UserServiceLocal {
	public void CreateUser( User User) ;
	public void EditUser (User User);
	public void removeUser(User User);
	public User findUserById(int id);
	public List<User> findAllUsers() ;
	public User findUserByLogin(String login);
	public User authentication(String login, String password);
}
