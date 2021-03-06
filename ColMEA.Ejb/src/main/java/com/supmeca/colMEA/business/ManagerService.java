package com.supmeca.colMEA.business;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.supmeca.colMEA.domain.Engineer;
import com.supmeca.colMEA.domain.Manager;
import com.supmeca.colMEA.domain.Project;
import com.supmeca.colMEA.domain.User;

/**
 * Session Bean implementation class ManagerService
 */
@Stateless
@LocalBean
public class ManagerService implements ManagerServiceRemote, ManagerServiceLocal {

	@PersistenceContext
	private EntityManager em;

	Manager Manager;
	Project project;
	/**
	 * Default constructor. 
	 */
	public ManagerService() {

	}

	@Override
	public void CreateManager(Manager Manager) {
		em.persist(Manager);

	}

	@Override
	public void EditManager(Manager Manager) {
		em.merge(Manager);

	}

	@Override
	public void removeManager(Manager Manager) {

		em.remove(em.merge(Manager));

	}

	@Override
	public Manager findManagerById(int id) {
		Manager Manager=em.find(Manager.class,id);
		if(Manager!=null){
			return Manager;
		}
		return null;
	}

	@Override
	public List<Manager> findAllManagers() {
		String text = "SELECT m FROM Manager m";
		Query query = em.createQuery(text);
		List<Manager> ListManagers = query.getResultList();

		return ListManagers;
	}

	@Override
	public void DeleteManager(int id) {
		em.remove(em.find(Manager.class, id));

	}

	@Override
	public Manager findManagerByProject(Integer id) {
		Manager Manager = null;

		Query query =  em.createQuery("SELECT u FROM Manager as u, Project as p "
				+ "WHERE  p.manager.id = u.id and p.id =:id");

		query.setParameter("id", id);
		query.setFirstResult(0);
		query.setMaxResults(1);
		Manager = (Manager)  query.getSingleResult();

		return Manager;
	}

	@Override
	public String findManagerNameByProject(Integer id) {
		String Manager = null;

		Query query =  em.createQuery("SELECT u.login FROM Manager as u, Project as p "
				+ "WHERE  p.manager.id = u.id and p.id =:id");

		query.setParameter("id", id);
		query.setFirstResult(0);
		query.setMaxResults(1);
		Manager = (String)query.getSingleResult();

		return Manager;
	}


}
