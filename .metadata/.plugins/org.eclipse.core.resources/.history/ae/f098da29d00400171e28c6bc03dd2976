package com.supmeca.colMEA.business;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.supmeca.colMEA.domain.User;

/**
 * Session Bean implementation class UserService
 */
@Stateless
@LocalBean
public class UserService implements UserServiceRemote, UserServiceLocal {

	@PersistenceContext
	private EntityManager em;
	User user;
	
	@Override
	public void CreateUser(User User) {
		
		em.persist(user);
	}

	@Override
	public void EditUser(User User) {
		
		em.merge(user);
		
	}

	@Override
	public void removeUser(User User) {

		em.remove(em.merge(user));
	}

	@Override
	public User findUserById(int id) {
		User user=em.find(User.class,id);
		if(user!=null){
			return user;
		}
		return null;
    
	}

	@Override
	public List<User> findAllUsers() {
		String text = "SELECT u FROM User u";
		Query query = em.createQuery(text);
		List<User> ListUsers = query.getResultList();
		
		return ListUsers;
	}

	@Override
	public User findUserByLogin(String login) {
		String Text = "SELECT u FROM User u WHERE u.login = :login";
    	Query query = em.createQuery(Text).setParameter("login", login);
		User user = (User)query.getSingleResult();
    return user;
	}

	@Override
	public User authentication(String login, String password) {
		User user = null;
		 
		Query query = (Query) em
				.createQuery("select u from User u where u.login=:l and u.password=:p");

		((javax.persistence.Query) query).setParameter("l", login)
				.setParameter("p", password);
		user = (User) ((javax.persistence.Query) query).getSingleResult();
 
	return user;
	}


}
