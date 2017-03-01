package com.supmeca.colMEA.business;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.supmeca.colMEA.domain.Engineer;
import com.supmeca.colMEA.domain.Manager;

/**
 * Session Bean implementation class ManagerService
 */
@Stateless
@LocalBean
public class ManagerService implements ManagerServiceRemote, ManagerServiceLocal {

	@PersistenceContext
	private EntityManager em;
	
	Manager Manager;
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

}
