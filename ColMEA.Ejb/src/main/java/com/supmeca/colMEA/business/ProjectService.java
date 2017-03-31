package com.supmeca.colMEA.business;

import java.util.Date;
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
		System.out.println("la date est: "+Project.getEnd_date());
		System.out.println("la date est: "+Project.getStart_date());
		if(Project!=null){

			return Project;
		}
		return null;
	}

	@Override
	public List<Project> findAllProjects() {
		
		String text = "SELECT v FROM Project v";
		Query query = em.createQuery(text);
		List<Project> ListProjects = query.getResultList();

		return ListProjects;
	}

	@Override
	public void DeleteProject(int id) {
		em.remove(em.find(Project.class, id));	

	}
	// find project by name project
	@Override
	public Project findProjectByName(String name){
		String Text = "SELECT p FROM Project p WHERE p.name =:name";
		Query query = em.createQuery(Text);
		query.setParameter("name", name);
		query.setFirstResult(0);
		query.setMaxResults(1);
		Project project = (Project)query.getSingleResult();
		return project;
	}
	// find project by team
	@Override
	public Project findProjectByTeam(Integer id){
		String Text = "SELECT p FROM Project as p ,Team as t, Study as s "
				+ "WHERE p.id_project = s.project.id_project and s.team.id_team = t.id_team and t.id_team =:id";

		Query query = em.createQuery(Text);
		query.setParameter("id", id);
		query.setFirstResult(0);
		query.setMaxResults(1);

		Project project = (Project)query.getSingleResult();
		return project;
	}
	// find projects by team
	@Override
	public List<Project> findProjectsByTeam(Integer id){
		String Text = "SELECT p FROM Project as p ,Team as t, Study as s "
				+ "WHERE p.id_project = s.project.id_project and s.team.id_team = t.id_team and t.id_team =:id";

		Query query = em.createQuery(Text);
		query.setParameter("id", id);

		List<Project> projects = query.getResultList();
		return projects;
	}
	// find project by coordinator
	@Override
	public Project findProjectByCoordinator(Integer id){
		String Text = "SELECT p FROM Project as p ,Team as t, Study as s, Coordinator as c "
				+ "WHERE p.id_project = s.project.id_project and s.team.id_team = t.id_team "
				+ "and t.coordinator.id_user = c.id_user and c.id_user =:id";

		Query query = em.createQuery(Text);
		query.setParameter("id", id);
		query.setFirstResult(0);
		query.setMaxResults(1);

		Project project = (Project)query.getSingleResult();
		return project;
	}

	// find projects by coordinator
	@Override
	public List<Project> findProjectsByCoordinator(Integer id){
		String Text = "SELECT p FROM Project as p ,Team as t, Study as s, Coordinator as c "
				+ "WHERE p.id_project = s.project.id_project and s.team.id_team = t.id_team "
				+ "and t.coordinator.id_user = c.id_user and c.id_user =:id";

		Query query = em.createQuery(Text);
		query.setParameter("id", id);

		List<Project> projects = query.getResultList();
		return projects;
	}	
	// find project by Study
	@Override
	public Project findProjectByStudy(Integer id){
		String Text = "SELECT p FROM Project as p , Study as s "
				+ "WHERE p.id_project = s.project.id_project  "
				+ "and s.id_study =:id";

		Query query = em.createQuery(Text);
		query.setParameter("id", id);
		query.setFirstResult(0);
		query.setMaxResults(1);

		Project project = (Project)query.getSingleResult();
		return project;
	}

	// find projects by Study
	@Override
	public List<Project> findProjectsByStudy(Integer id){
		String Text = "SELECT p FROM Project as p ,Team as t, Study as s, Coordinator as c "
				+ "WHERE p.id_project = s.project.id_project and s.team.id_team = t.id_team "
				+ "and t.coordinator.id_user = c.id_user and c.id_user =:id";

		Query query = em.createQuery(Text);
		query.setParameter("id", id);

		List<Project> projects = query.getResultList();
		return projects;
	}	

	// find project by Manager
	@Override
	public Project findProjectByManager(Integer id){
		String Text = "SELECT p FROM Project as p , Manager as m "
				+ "WHERE m.id_user = p.manager.id_user and  m.id_user =:id";

		Query query = em.createQuery(Text);
		query.setParameter("id", id);
		query.setFirstResult(0);
		query.setMaxResults(1);

		Project project = (Project)query.getSingleResult();
		return project;
	}

	// find projects by Manager
	@Override
	public List<Project> findProjectsByManager(Integer id){
		String Text = "SELECT p FROM Project as p , Manager as m "
				+ "WHERE m.id_user = p.manager.id_user and  m.id_user =:id";

		Query query = em.createQuery(Text);
		query.setParameter("id", id);

		List<Project> projects = query.getResultList();
		return projects;
	}	

	// find project by Engineer
	@Override
	public Project findProjectByEngineer(Integer id){
		String Text = "SELECT p FROM Project as p ,Team as t, Study as s, Engineer as e, Teams_Engineers as te "
				+ "WHERE p.id_project = s.project.id_project and s.team.id_team = t.id_team "
				+ "and te.engineer.id_user = e.id_user and te.team.id_team = t.id_team and e.id_user =:id";

		Query query = em.createQuery(Text);
		query.setParameter("id", id);
		query.setFirstResult(0);
		query.setMaxResults(1);

		Project project = (Project)query.getSingleResult();
		return project;
	}

	// find projects by Engineer
	@Override
	public List<Project> findProjectsByEngineer(Integer id){
		String Text = "SELECT p FROM Project as p ,Team as t, Study as s, Engineer as e, Teams_Engineers as te "
				+ "WHERE p.id_project = s.project.id_project and s.team.id_team = t.id_team "
				+ "and te.engineer.id_user = e.id_user and te.team.id_team = t.id_team and e.id_user =:id";

		Query query = em.createQuery(Text);
		query.setParameter("id", id);

		List<Project> projects = query.getResultList();
		return projects;
	}
	// find project by start_date project
	@Override
	public Project findProjectByStartDate(Date start_date){
		String Text = "SELECT p FROM Project p WHERE p.start_date =:start_date";
		Query query = em.createQuery(Text);
		query.setParameter("start_date", start_date);
		query.setFirstResult(0);
		query.setMaxResults(1);
		Project project = (Project)query.getSingleResult();
		return project;
	}
	// find project by end_date project
	@Override
	public Project findProjectByEndDate(Date end_date){
		String Text = "SELECT p FROM Project p WHERE p.end_date =:end_date";
		Query query = em.createQuery(Text);
		query.setParameter("end_date", end_date);
		query.setFirstResult(0);
		query.setMaxResults(1);
		Project project = (Project)query.getSingleResult();
		return project;
	}
}

