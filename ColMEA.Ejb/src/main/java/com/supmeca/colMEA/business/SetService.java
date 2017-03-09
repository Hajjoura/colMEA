package com.supmeca.colMEA.business;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.supmeca.colMEA.domain.Coordinator;
import com.supmeca.colMEA.domain.Set;

/**
 * Session Bean implementation class SetService
 */
@Stateless
@LocalBean
public class SetService implements SetServiceRemote, SetServiceLocal {

	@PersistenceContext
	private EntityManager em;
	
	Set Set;
    /**
     * Default constructor. 
     */
    public SetService() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void CreateSet(Set Set) {
		em.persist(Set);
	}

	@Override
	public void EditSet(Set Set) {
		em.merge(Set);
	}

	@Override
	public void removeSet(Set Set) {
		em.remove(em.merge(Set));
	}

	@Override
	public Set findSetById(int id) {
		Set Set=em.find(Set.class,id);
		if(Set!=null){
			return Set;
		}
		return null;
	}

	@Override
	public List<Set> findAllSets() {
		String text = "SELECT v FROM Variable v";
		Query query = em.createQuery(text);
		List<Set> ListSets = query.getResultList();
		
		return ListSets;
	}

	@Override
	public void DeleteSet(int id) {
		em.remove(em.find(Set.class, id));	
		
	}

}
