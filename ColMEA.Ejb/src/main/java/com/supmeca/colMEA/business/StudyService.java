package com.supmeca.colMEA.business;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.supmeca.colMEA.domain.Coordinator;
import com.supmeca.colMEA.domain.Project;
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
		String text = "SELECT s FROM Study s";
		Query query = em.createQuery(text);
		List<Study> ListStudys = query.getResultList();

		return ListStudys;
	}

	@Override
	public void DeleteStudy(int id) {
		em.remove(em.find(Study.class, id));	

	}
	// find Study by Type
	@Override
	public Study findStudyByType(String type){
		String Text = "SELECT s FROM Study s WHERE s.type =:type";
		Query query = em.createQuery(Text);
		query.setParameter("type", type);
		query.setFirstResult(0);
		query.setMaxResults(1);
		Study Study = (Study)query.getSingleResult();
		return Study;
	}
	// find Study by project
	@Override
	public Study findStudyByProject(Integer id){
		String Text = "SELECT s FROM Study as s , Project as p "
				+ "WHERE p.id_project = s.project.id_project and p.id =:id";
		Query query = em.createQuery(Text);
		query.setParameter("id", id);
		query.setFirstResult(0);
		query.setMaxResults(1);
		Study Study = (Study)query.getSingleResult();
		return Study;
	}
	// find Study by Team
	@Override
	public Study findStudyByTeam(Integer id){
		String Text = "SELECT s FROM Study as s , Team as t "
				+ "WHERE t.id_team = s.team.id_team and t.id =:id";
		Query query = em.createQuery(Text);
		query.setParameter("id", id);
		query.setFirstResult(0);
		query.setMaxResults(1);
		Study Study = (Study)query.getSingleResult();
		return Study;
	}
	// find Studies by Project
	@Override
	public List<Study> findStudiesByProject(Integer id) {
		String Text = "SELECT s FROM Study as s , Project as p "
				+ "WHERE p.id_project = s.project.id_project and p.id =:id";
		Query query = em.createQuery(Text);
		query.setParameter("id", id);
		List<Study> Studies = query.getResultList();
		return Studies;
	}
	// find Studies by Team
	@Override
	public List<Study> findStudiesByTeam(Integer id) {
		String Text = "SELECT s FROM Study as s , Team as t "
				+ "WHERE t.id_team = s.team.id_team and t.id =:id";
		Query query = em.createQuery(Text);
		query.setParameter("id", id);
		List<Study> Studies = query.getResultList();
		return Studies;
	}
}
