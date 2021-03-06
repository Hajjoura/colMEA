package com.supmeca.colMEA.business;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.supmeca.colMEA.domain.Coordinator;
import com.supmeca.colMEA.domain.Manager;
import com.supmeca.colMEA.domain.User;


@Stateless
@LocalBean
public class CoordinatorService implements CoordinatorServiceRemote, CoordinatorServiceLocal {
	@PersistenceContext
	private EntityManager em;

	Coordinator Coordinator;
	/**
	 * Default constructor. 
	 */
	public CoordinatorService() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void CreateCoordinator(Coordinator Coordinator) {

		em.persist(Coordinator);
	}

	@Override
	public void EditCoordinator(Coordinator Coordinator) {

		em.merge(Coordinator);
	}

	@Override
	public void removeCoordinator(Coordinator Coordinator) {

		em.remove(em.merge(Coordinator));

	}

	@Override
	public Coordinator findCoordinatorById(int id) {
		Coordinator coordinator=em.find(Coordinator.class,id);
		if(coordinator!=null){
			return coordinator;
		}
		return null;
	}

	@Override
	public List<Coordinator> findAllCoordinators() {
		String text = "SELECT c FROM Coordinator c";
		Query query = em.createQuery(text);
		List<Coordinator> ListCoordinators = query.getResultList();

		return ListCoordinators;
	}

	@Override
	public void DeleteCoordinator(int id) {
		em.remove(em.find(Coordinator.class, id));		
	}

	@Override
	public Coordinator findCoordinatorByName(String first_Name, String last_Name) {
		String Text = "SELECT u FROM Coordinator u WHERE u.first_name = :first and u.last_name =:last";
		Query query = em.createQuery(Text);
		query.setParameter("first", first_Name);
		query.setParameter("last", last_Name);
		query.setFirstResult(0);
		query.setMaxResults(1);    	
		Coordinator user = (Coordinator)query.getSingleResult();
		return user;
	}

	@Override
	public Coordinator findCoordinatorByEmail(String email){
		String Text = "SELECT u FROM Coordinator u WHERE u.email =:email";
		Query query = em.createQuery(Text);
		query.setParameter("email", email);
		query.setFirstResult(0);
		query.setMaxResults(1);    	
		Coordinator user = (Coordinator)query.getSingleResult();
		return user;
	}
	@Override
	public Coordinator findCoordinatorByTeam(Integer id) {
		Coordinator coordinator = null;

		Query query =  em.createQuery("SELECT u FROM Coordinator as u, Team as t "
				+ "WHERE  t.coordinator.id = u.id and t.id =:id");

		query.setParameter("id", id);
		query.setFirstResult(0);
		query.setMaxResults(1);
		coordinator = (Coordinator)  query.getSingleResult();

		return coordinator;
	}
	@Override
	public String findCoordinatorNameByTeam(Integer id) {
		String Coordinator = null;

		Query query =  em.createQuery("SELECT u.login FROM Coordinator as u, Team as t "
				+ "WHERE  t.coordinator.id = u.id and t.id =:id");

		query.setParameter("id", id);
		query.setFirstResult(0);
		query.setMaxResults(1);
		Coordinator = (String)query.getSingleResult();

		return Coordinator;
	}



}
