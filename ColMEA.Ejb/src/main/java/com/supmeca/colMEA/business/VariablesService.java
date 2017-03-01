package com.supmeca.colMEA.business;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.supmeca.colMEA.domain.Coordinator;
import com.supmeca.colMEA.domain.Manager;
import com.supmeca.colMEA.domain.Variable;

/**
 * Session Bean implementation class VariablesService
 */
@Stateless
@LocalBean
public class VariablesService implements VariablesServiceRemote, VariablesServiceLocal {

	@PersistenceContext
	private EntityManager em;
	
	Variable Variable;
    /**
     * Default constructor. 
     */
    public VariablesService() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void CreateVariable(Variable Variable) {
		em.persist(Variable);
	}

	@Override
	public void EditVariable(Variable Variable) {
		em.merge(Variable);
	}

	@Override
	public void removeVariable(Variable Variable) {
		em.remove(em.merge(Variable));
		
	}

	@Override
	public Variable findVariableById(int id) {
		Variable Variable=em.find(Variable.class,id);
		if(Variable!=null){
			return Variable;
		}
		return null;
	}

	@Override
	public List<Variable> findAllVariables() {
		String text = "SELECT v FROM Variable v";
		Query query = em.createQuery(text);
		List<Variable> ListVariables = query.getResultList();
		
		return ListVariables;
	}

}
