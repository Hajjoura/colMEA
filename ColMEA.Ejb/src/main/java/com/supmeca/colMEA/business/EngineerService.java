package com.supmeca.colMEA.business;

import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.supmeca.colMEA.domain.Coordinator;
import com.supmeca.colMEA.domain.Engineer;
import com.supmeca.colMEA.domain.Partition;
import com.supmeca.colMEA.domain.Team;
import com.supmeca.colMEA.domain.Teams_Engineers;
import com.supmeca.colMEA.domain.Variable;
import com.supmeca.colMEA.domain.Variables_Partitions;

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

	@Override
	public void DeleteEngineer(int id) {
		em.remove(em.find(Engineer.class, id));		
	}



	//Add enginner to Team
	@Override
	public Boolean addEngineerToTeam(Team team, Engineer engineer) {

		try{
			Teams_Engineers teamEng = new Teams_Engineers(team,engineer);

			em.persist(teamEng);
			return true;	
		}catch(Exception e){
			return false;
		}
	}

	//find engineer By Team

	@Override
	public Engineer findEngineerByTeam(Integer id) {
		Engineer engineer = null;

		Query query =  em.createQuery("SELECT u "
				+ "FROM Engineer as u, Team as t , Teams_Engineers as te "
				+ "WHERE  te.engineer.id = u.id and te.team.id = t.id and t.id =:id");

		query.setParameter("id", id);
		query.setFirstResult(0);
		query.setMaxResults(1);
		engineer = (Engineer)  query.getSingleResult();

		return engineer;
	}

	//find engineers By Team

	@Override
	public List<Engineer> findEngineersByTeam(Integer id) {

		Query query =  em.createQuery("SELECT u "
				+ "FROM Engineer as u, Team as t , Teams_Engineers as te "
				+ "WHERE  te.engineer.id = u.id and te.team.id = t.id and t.id =:id");

		query.setParameter("id", id);
		List<Engineer> ListEngineers = query.getResultList();


		return ListEngineers;
	}
	//find engineers By Name Team

	@Override
	public List<Engineer> findEngineersByNameTeam(String name) {

		Query query =  em.createQuery("SELECT u "
				+ "FROM Engineer as u, Team as t , Teams_Engineers as te "
				+ "WHERE  te.engineer.id = u.id and te.team.id = t.id and t.name =:name");

		query.setParameter("name", name);
		List<Engineer> ListEngineers = query.getResultList();


		return ListEngineers;
	}
	//find engineers By Name Domain

	@Override
	public List<Engineer> findEngineersByDomain(String label) {

		Query query =  em.createQuery("SELECT u "
				+ "FROM Engineer as u, Domain as d "
				+ "WHERE   u.domain.id = d.id and d.label =:label");

		query.setParameter("label", label);
		List<Engineer> ListEngineers = query.getResultList();

		return ListEngineers;
	}
	//find engineers By Project

	@Override
	public List<Engineer> findEngineersByProject(String name) {

		Query query =  em.createQuery("SELECT u "
				+ "FROM Engineer as u, Team as t, Teams_Engineers as te, Study s , Project p "
				+ "WHERE   p.id_project = s.project.id_project and s.team.id_team = t.id_team"
				+ " and t.id_team = te.team.id_team and te.engineer.id_user = u.id_user and p.name =:name");

		query.setParameter("name", name);
		List<Engineer> ListEngineers = query.getResultList();

		return ListEngineers;
	}
	//find engineers By Coordinator id
	@Override
	public List<Engineer> findEngineersByCoordinator(Integer id) {

		Query query =  em.createQuery("SELECT e "
				+ "FROM Coordinator as c, Engineer as e, Team as t, Teams_Engineers as te "
				+ "WHERE t.id_team = te.team.id_team and c.id_user = t.coordinator.id_user and "
				+ "te.engineer.id_user = e.id_user and c.id_user =:id");

		query.setParameter("id", id);
		List<Engineer> ListEngineers = query.getResultList();

		return ListEngineers;
	}
	//find engineers By Coordinator id
	@Override
	public List<Engineer> findEngineersByCoordinatorName(String login) {

		Query query =  em.createQuery("SELECT e "
				+ "FROM Coordinator as c, Engineer as e, Team as t, Teams_Engineers as te "
				+ "WHERE t.id_team = te.team.id_team and c.id_user = t.coordinator.id_user and "
				+ "te.engineer.id_user = e.id_user and c.login =:login");

		query.setParameter("login", login);
		List<Engineer> ListEngineers = query.getResultList();

		return ListEngineers;
	}
}
