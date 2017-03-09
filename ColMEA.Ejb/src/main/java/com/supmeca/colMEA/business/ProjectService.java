package com.supmeca.colMEA.business;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.supmeca.colMEA.domain.Coordinator;
import com.supmeca.colMEA.domain.Project;
import com.supmeca.colMEA.domain.Variable;

/**
 * Session Bean implementation class ProjectService
 */
@Stateless
@LocalBean
public class ProjectService implements ProjectServiceRemote, ProjectServiceLocal {

	@PersistenceContext
	private EntityManager em;
	
	Project Project;
    /**
     * Default constructor. 
     */
    public ProjectService() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void CreateProject(Project Project) {
		em.persist(Project);
	}

	@Override
	public void EditProject(Project Project) {
		em.merge(Project);
	}

	@Override
	public void removeProject(Project Project) {
		em.remove(em.merge(Project));
	}

	@Override
	public Project findProjectById(int id) {
		Project Project=em.find(Project.class,id);
		if(Project!=null){
			return Project;
		}
		return null;
	}

	@Override
	public List<Project> findAllProjects() {
		String text = "SELECT v FROM Variable v";
		Query query = em.createQuery(text);
		List<Project> ListProjects = query.getResultList();
		
		return ListProjects;
	}

	@Override
	public void DeleteProject(int id) {
		em.remove(em.find(Project.class, id));	
		
	}

}
