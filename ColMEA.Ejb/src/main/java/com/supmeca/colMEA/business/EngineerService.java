package com.supmeca.colMEA.business;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.supmeca.colMEA.domain.Coordinator;
import com.supmeca.colMEA.domain.Engineer;

/**
 * Session Bean implementation class EngineerService
 */
@Stateless
@LocalBean
public class EngineerService implements EngineerServiceRemote, EngineerServiceLocal {

	@PersistenceContext
	private EntityManager em;
	
	Engineer Engineer;
    /**
     * Default constructor. 
     */
    public EngineerService() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void CreateEngineer(Engineer Engineer) {
		em.persist(Engineer);
		
	}

	@Override
	public void EditEngineer(Engineer Engineer) {
		em.merge(Engineer);
		
	}

	@Override
	public void removeEngineer(Engineer Engineer) {
		em.remove(em.merge(Engineer));
	}

	@Override
	public Engineer findEngineerById(int id) {
		Engineer Engineer=em.find(Engineer.class,id);
		if(Engineer!=null){
			return Engineer;
		}
		return null;
	}
	

	@Override
	public List<Engineer> findAllEngineers() {
		String text = "SELECT e FROM Engineer e";
		Query query = em.createQuery(text);
		List<Engineer> ListEngineers = query.getResultList();
		
		return ListEngineers;
	}

}
