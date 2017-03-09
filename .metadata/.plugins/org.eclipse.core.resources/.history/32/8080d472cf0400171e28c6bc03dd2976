package com.supmeca.colMEA.business;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.supmeca.colMEA.domain.Constraint;

/**
 * Session Bean implementation class ConstraintService
 */
@Stateless
@LocalBean
public class ConstraintService implements ConstraintServiceRemote, ConstraintServiceLocal {

	@PersistenceContext
	private EntityManager em;
	
	Constraint Constraint;
    /**
     * Default constructor. 
     */
    public ConstraintService() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void CreateConstraint(Constraint Constraint) {
		em.persist(Constraint);
	}

	@Override
	public void EditConstraint(Constraint Constraint) {
		em.merge(Constraint);
	}

	@Override
	public void removeConstraint(Constraint Constraint) {
		em.remove(em.merge(Constraint));
	}

	@Override
	public Constraint findConstraintById(int id) {
		Constraint Constraint=em.find(Constraint.class,id);
		if(Constraint!=null){
			return Constraint;
		}
		return null;
	}

	@Override
	public List<Constraint> findAllConstraints() {
		String text = "SELECT v FROM Variable v";
		Query query = em.createQuery(text);
		List<Constraint> ListConstraints = query.getResultList();
		
		return ListConstraints;
	}


}
