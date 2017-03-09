package com.supmeca.colMEA.business;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.supmeca.colMEA.domain.Tool;

/**
 * Session Bean implementation class ToolService
 */
@Stateless
@LocalBean
public class ToolService implements ToolServiceRemote, ToolServiceLocal {

	@PersistenceContext
	private EntityManager em;
	
	Tool Tool;
    /**
     * Default constructor. 
     */
    public ToolService() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void CreateTool(Tool Tool) {
		em.persist(Tool);
	}

	@Override
	public void EditTool(Tool Tool) {
		em.merge(Tool);
	}

	@Override
	public void removeTool(Tool Tool) {
		em.remove(em.merge(Tool));
	}

	@Override
	public Tool findToolById(int id) {
		Tool Tool=em.find(Tool.class,id);
		if(Tool!=null){
			return Tool;
		}
		return null;
	}

	@Override
	public List<Tool> findAllTools() {
		String text = "SELECT v FROM Variable v";
		Query query = em.createQuery(text);
		List<Tool> ListTools = query.getResultList();
		
		return ListTools;
	}
}
