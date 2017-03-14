package com.supmeca.colMEA.business;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.supmeca.colMEA.domain.Coordinator;
import com.supmeca.colMEA.domain.Study;

/**
 * Session Bean implementation class StudyService
 */
@Stateless
@LocalBean
public class StudyService implements StudyServiceRemote, StudyServiceLocal {

	@PersistenceContext
	private EntityManager em;
	
	Study Study;
    /**
     * Default constructor. 
     */
    public StudyService() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void CreateStudy(Study Study) {
		em.persist(Study);
	}

	@Override
	public void EditStudy(Study Study) {
		em.merge(Study);
	}

	@Override
	public void removeStudy(Study Study) {
		em.remove(em.merge(Study));
	}

	@Override
	public Study findStudyById(int id) {
		Study Study=em.find(Study.class,id);
		if(Study!=null){
			return Study;
		}
		return null;
	}

	@Override
	public List<Study> findAllStudys() {
		String text = "SELECT v FROM Variable v";
		Query query = em.createQuery(text);
		List<Study> ListStudys = query.getResultList();
		
		return ListStudys;
	}

	@Override
	public void DeleteStudy(int id) {
		em.remove(em.find(Study.class, id));	
		
	}
}
