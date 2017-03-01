package com.supmeca.colMEA.business;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.supmeca.colMEA.domain.Coordinator;


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


	
}
