package com.supmeca.colMEA.business;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.supmeca.colMEA.domain.Objective;

/**
 * Session Bean implementation class StatisticsService
 */
@Stateless
@LocalBean
public class StatisticsService implements StatisticsServiceRemote, StatisticsServiceLocal {

	@PersistenceContext
	private EntityManager em;


	public StatisticsService() {


		// TODO Auto-generated constructor stub
	}


	@Override
	public Integer NumberPartitionsByStudy(Integer id) {
		String Text = "SELECT count(p) FROM  t_partition as p , Study as s "
				+ "WHERE p.study.id_study = s.id_study and s.id_study =:id";

		Query query = em.createQuery(Text);
		query.setParameter("id", id);
		query.setFirstResult(0);
		query.setMaxResults(1);

		Integer count =((Long) query.getSingleResult()).intValue();
		return count;
	}
	@Override
	public Integer NumberPartitions() {
		String Text = "SELECT count(p) FROM  t_partition as p ";

		Query query = em.createQuery(Text);
		
		query.setFirstResult(0);
		query.setMaxResults(1);

		Integer count =((Long) query.getSingleResult()).intValue();
		return count;
	}

	@Override
	public Integer NumberVariablesByProject(Integer id) {
		String Text = "SELECT count(v) FROM  Variable as v ,t_partition as p , Project as pr, Study as s , Variables_Partitions vp "
				+ "WHERE v.id_variable = vp.variable.id_variable and p.id_partition = vp.partition.id_partition and "
				+ "p.study.id_study = s.id_study and s.project.id_project = pr.id_project and pr.id_project =:id";

		Query query = em.createQuery(Text);
		query.setParameter("id", id);
		query.setFirstResult(0);
		query.setMaxResults(1);

		Integer count =((Long) query.getSingleResult()).intValue();
		return count;
	}

	@Override
	public Integer NumberVariables() {
		String Text = "SELECT count(v) FROM  Variable as v ";

		Query query = em.createQuery(Text);
		
		query.setFirstResult(0);
		query.setMaxResults(1);

		Integer count =((Long) query.getSingleResult()).intValue();
		return count;
	}

	@Override
	public Integer NumberVariablesByPartition(Integer id) {
		String Text = "SELECT count(v) FROM  Variable as v ,t_partition as p , Variables_Partitions vp "
				+ "WHERE v.id_variable = vp.variable.id_variable and p.id_partition = vp.partition.id_partition and "
				+ "p.id_partition =:id";

		Query query = em.createQuery(Text);
		query.setParameter("id", id);
		query.setFirstResult(0);
		query.setMaxResults(1);

		Integer count =((Long) query.getSingleResult()).intValue();
		return count;
	}


	@Override
	public Integer NumberVariablesByStudy(Integer id) {
		String Text = "SELECT count(v) FROM  Variable as v ,t_partition as p , Study as s , Variables_Partitions vp "
				+ "WHERE v.id_variable = vp.variable.id_variable and p.id_partition = vp.partition.id_partition and "
				+ "p.study.id_study = s.id_study and s.id_study =:id";

		Query query = em.createQuery(Text);
		query.setParameter("id", id);
		query.setFirstResult(0);
		query.setMaxResults(1);

		Integer count =((Long) query.getSingleResult()).intValue();
		return count;
	}


	@Override
	public Integer NumberConstraintsByPartition(Integer id) {
		String Text = "SELECT count(v) FROM  Constraint as v ,t_partition as p , Variables_Partitions vp "
				+ "WHERE v.id_variable = vp.variable.id_variable and p.id_partition = vp.partition.id_partition and "
				+ "p.id_partition =:id";

		Query query = em.createQuery(Text);
		query.setParameter("id", id);
		query.setFirstResult(0);
		query.setMaxResults(1);

		Integer count =((Long) query.getSingleResult()).intValue();
		return count;
	}


	@Override
	public Integer NumberObjectivesByPartition(Integer id) {
		String Text = "SELECT count(v) FROM  Objective as v ,t_partition as p , Variables_Partitions vp "
				+ "WHERE v.id_variable = vp.variable.id_variable and p.id_partition = vp.partition.id_partition and "
				+ "p.id_partition =:id";

		Query query = em.createQuery(Text);
		query.setParameter("id", id);
		query.setFirstResult(0);
		query.setMaxResults(1);

		Integer count =((Long) query.getSingleResult()).intValue();
		return count;
	}


	@Override
	public Integer NumberStudiesByProject(Integer id) {
		String Text = "SELECT count(s) FROM  Project as pr, Study as s  "
				+ "WHERE  s.project.id_project = pr.id_project and pr.id_project =:id";

		Query query = em.createQuery(Text);
		query.setParameter("id", id);
		query.setFirstResult(0);
		query.setMaxResults(1);

		Integer count =((Long) query.getSingleResult()).intValue();
		return count;
	}



	@Override
	public Integer NumberStudies() {
		String Text = "SELECT count(s) FROM  Study as s ";

		Query query = em.createQuery(Text);
		
		query.setFirstResult(0);
		query.setMaxResults(1);

		Integer count =((Long) query.getSingleResult()).intValue();
		return count;
	}
	@Override
	public Integer NumberEngineersByProject(Integer id) {
		String Text = "SELECT count(e.id_user) FROM   Engineer as e, Project as pr, Study as s , Team as t, Teams_Engineers as te "
				+ "WHERE e.id_user = te.engineer.id_user and te.team.id_team = t.id_team and s.team.id_team = t.id_team and "
				+ " s.project.id_project = pr.id_project and pr.id_project =:id";

		Query query = em.createQuery(Text);
		query.setParameter("id", id);

		Integer count =((Long) query.getSingleResult()).intValue();
		return count;
	}


	@Override
	public Integer NumberEngineersByTeam(Integer id) {
		String Text = "SELECT count(e.id_user) FROM   Engineer as e, Team as t, Teams_Engineers as te "
				+ "WHERE e.id_user = te.engineer.id_user and te.team.id_team = t.id_team and "
				+ " t.id_team =:id";

		Query query = em.createQuery(Text);
		query.setParameter("id", id);

		Integer count =((Long) query.getSingleResult()).intValue();
		return count;
	}


	@Override
	public Integer NumberEngineers() {
		String Text = "SELECT count(e.id_user) FROM   Engineer as e";

		Query query = em.createQuery(Text);
	

		Integer count =((Long) query.getSingleResult()).intValue();
		return count;
	}

	@Override
	public Integer NumberProjectsByManager(Integer id) {
		String Text = "SELECT count(pr) FROM   Manager as m, Project as pr "
				+ "WHERE m.id_user = pr.manager.id_user and m.id_user  =:id";

		Query query = em.createQuery(Text);
		query.setParameter("id", id);

		Integer count =((Long) query.getSingleResult()).intValue();
		return count;
	}
	@Override
	public Integer NumberProjects() {
		String Text = "SELECT count(pr) FROM  Project as pr ";

		Query query = em.createQuery(Text);
		

		Integer count =((Long) query.getSingleResult()).intValue();
		return count;
	}


	@Override
	public Integer NumberTeamsByCoordinator(Integer id) {
		String Text = "SELECT count(t.id_team) FROM   Coordinator as e, Team as t "
				+ "WHERE e.id_user = t.coordinator.id_user and  e.id_user =:id";

		Query query = em.createQuery(Text);
		query.setParameter("id", id);

		Integer count =((Long) query.getSingleResult()).intValue();
		return count;
	}
	@Override
	public Integer NumberTeams() {
		String Text = "SELECT count(t.id_team) FROM  Team as t ";

		Query query = em.createQuery(Text);
	

		Integer count =((Long) query.getSingleResult()).intValue();
		return count;
	}

	@Override
	public Integer NumberTeamsByEnginner(Integer id) {
		String Text = "SELECT count(t.id_team) FROM   Engineer as e, Team as t, Teams_Engineers as te "
				+ "WHERE e.id_user = te.engineer.id_user and te.team.id_team = t.id_team and "
				+ " e.id_user =:id";

		Query query = em.createQuery(Text);
		query.setParameter("id", id);

		Integer count =((Long) query.getSingleResult()).intValue();
		return count;
	}

}
