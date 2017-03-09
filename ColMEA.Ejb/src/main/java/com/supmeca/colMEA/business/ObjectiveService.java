package com.supmeca.colMEA.business;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.supmeca.colMEA.domain.Coordinator;
import com.supmeca.colMEA.domain.Objective;

/**
 * Session Bean implementation class ObjectiveService
 */
@Stateless
@LocalBean
public class ObjectiveService implements ObjectiveServiceRemote {

	@PersistenceContext
	private EntityManager em;
	
	Objective Objective;
    /**
     * Default constructor. 
     */
    public ObjectiveService() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void CreateObjective(Objective Objective) {
		em.persist(Objective);
	}

	@Override
	public void EditObjective(Objective Objective) {
		em.merge(Objective);
	}

	@Override
	public void removeObjective(Objective Objective) {
		em.remove(em.merge(Objective));
	}

	@Override
	public Objective findObjectiveById(int id) {
		Objective Objective=em.find(Objective.class,id);
		if(Objective!=null){
			return Objective;
		}
		return null;
	}

	@Override
	public List<Objective> findAllObjectives() {
		String text = "SELECT v FROM Variable v";
		Query query = em.createQuery(text);
		List<Objective> ListObjectives = query.getResultList();
		
		return ListObjectives;
	}

	@Override
	public void DeleteObjective(int id) {
		em.remove(em.find(Objective.class, id));	
		
	}
}
