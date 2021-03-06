package com.supmeca.colMEA.business;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

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
	private static final AtomicLong counter = new AtomicLong();

	private static List<User> users;

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
	public User findUserByName(String first_Name, String last_Name) {
		String Text = "SELECT u FROM User u WHERE u.first_name = :first and u.last_name =:last";
		Query query = em.createQuery(Text);
		query.setParameter("first", first_Name);
		query.setParameter("last", last_Name);

		User user = (User)query.getSingleResult();
		return user;
	}


	@Override
	public User authentication(String login, String password) {
		User user = null;

		Query query =  em.createQuery("select u from User u where u.login=:l and u.password=:p");

		query.setParameter("l", login);
		query.setParameter("p", password);
		user = (User)  query.getSingleResult();
		
		return user;
	}

	@Override
	public void DeleteUser(int id) {
		em.remove(em.find(User.class, id));

	}

	@Override
	public boolean isUserExist(User user) {
		return findByName(user.getFirst_name())!=null;
	}

	@Override
	public User findByName(String name) {
		for(User user : users){
			if(user.getFirst_name().equalsIgnoreCase(name)){
				return user;
			}
		}
		return null;
	}




}
