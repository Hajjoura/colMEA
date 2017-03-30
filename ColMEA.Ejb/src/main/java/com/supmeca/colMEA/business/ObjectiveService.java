package com.supmeca.colMEA.business;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.supmeca.colMEA.domain.Objective;
import com.supmeca.colMEA.domain.Coordinator;
import com.supmeca.colMEA.domain.Objective;
import com.supmeca.colMEA.domain.Set;

/**
 * Session Bean implementation class ObjectiveService
 */
@Stateless
@LocalBean
public class ObjectiveService implements ObjectiveServiceRemote, ObjectiveServiceLocal {

	@PersistenceContext
	private EntityManager em;

	Objective Objective;
	/**
	 * Default constructor. 
	 */
	public ObjectiveService() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void CreateObjective(Objective Objective) {
		em.persist(Objective);
	}

	@Override
	public void EditObjective(Objective Objective) {
		em.merge(Objective);
	}

	@Override
	public void removeObjective(Objective Objective) {
		em.remove(em.merge(Objective));
	}

	@Override
	public Objective findObjectiveById(int id) {
		Objective Objective=em.find(Objective.class,id);
		if(Objective!=null){
			return Objective;
		}
		return null;
	}

	@Override
	public List<Objective> findAllObjectives() {
		String text = "SELECT o FROM Objective o";
		Query query = em.createQuery(text);
		List<Objective> ListObjectives = query.getResultList();

		return ListObjectives;
	}

	@Override
	public void DeleteObjective(int id) {
		em.remove(em.find(Objective.class, id));	

	}

	// find Objective by name 
	@Override
	public Objective findObjectiveByName(String name){
		String Text = "SELECT v FROM Objective v WHERE v.name =:name";
		Query query = em.createQuery(Text);
		query.setParameter("name", name);
		query.setFirstResult(0);
		query.setMaxResults(1);
		Objective Variable = (Objective)query.getSingleResult();
		return Variable;
	}
	// find Objective by min and max  

	@Override
	public Objective findObjectiveByMinMax(float min, float max){
		String Text = "SELECT v FROM Objective v WHERE v.min =:min and v.max =:max";
		Query query = em.createQuery(Text);
		query.setFirstResult(0);
		query.setMaxResults(1);
		query.setParameter("min", min);
		query.setParameter("max", max);

		Objective Variable = (Objective)query.getSingleResult();
		return Variable;
	}
	// find Objectives by min and max  

	@Override
	public List<Objective> findObjectivesByMinMax(float min, float max){
		String Text = "SELECT v FROM Objective v WHERE v.min =:min and v.max =:max";
		Query query = em.createQuery(Text);
		query.setParameter("min", min);
		query.setParameter("max", max);

		List<Objective> Variables = query.getResultList();
		return Variables;
	}
	// find Objective by minRes and maxRes  

	@Override
	public Objective findObjectiveByMinMaxRes(float minRes, float maxRes){
		String Text = "SELECT v FROM Objective v WHERE v.min_res =:minRes and v.max_res =:maxRes";
		Query query = em.createQuery(Text);
		query.setFirstResult(0);
		query.setMaxResults(1);
		query.setParameter("minRes", minRes);
		query.setParameter("maxRes", maxRes);

		Objective Objective = (Objective)query.getSingleResult();
		return Objective;
	}
	// find Objective by minRes and maxRes  
	@Override
	public List<Objective> findObjectivesByMinMaxRes(float minRes, float maxRes){
		String Text = "SELECT v FROM Objective v WHERE v.min_res =:minRes and v.max_res =:maxRes";
		Query query = em.createQuery(Text);
		query.setParameter("minRes", minRes);
		query.setParameter("maxRes", maxRes);
		List<Objective> Variables = query.getResultList();
		return Variables;
	}

	// find  Objective By Partition
	@Override
	public Objective findObjectiveByPartition(Integer id){
		String Text = "SELECT v FROM Objective as v, t_partition as p , Variables_Partitions as vp "
				+ "WHERE p.id_partition = vp.partition.id_partition and v.id_variable = vp.variable.id_variable "
				+ "and p.id_partition =:id";

		Query query = em.createQuery(Text);
		query.setParameter("id", id);
		query.setFirstResult(0);
		query.setMaxResults(1);

		Objective variable = (Objective)query.getSingleResult();
		return variable;
	}

	// find Objectives by Partition  
	@Override
	public List<Objective> findObjectivesByPartition(Integer id){
		String Text = "SELECT v FROM Objective as v, t_partition as p , Variables_Partitions as vp "
				+ "WHERE p.id_partition = vp.partition.id_partition and v.id_variable = vp.variable.id_variable "
				+ "and p.id_partition =:id";

		Query query = em.createQuery(Text);
		query.setParameter("id", id);			    	
		List<Objective> variables = query.getResultList();
		return variables;
	}
	// find  Objective By Study
	@Override
	public Objective findObjectiveByStudy(Integer id){
		String Text = "SELECT v FROM Objective as v, t_partition as p , Study as s, Variables_Partitions as vp "
				+ "WHERE p.id_partition = vp.partition.id_partition and v.id_variable = vp.variable.id_variable "
				+ "and p.study.id_study = s.id_study and  s.id_study =:id";

		Query query = em.createQuery(Text);
		query.setParameter("id", id);
		query.setFirstResult(0);
		query.setMaxResults(1);

		Objective variable = (Objective)query.getSingleResult();
		return variable;
	}

	// find Objectives by Study  
	@Override
	public List<Objective> findObjectivesByStudy(Integer id){
		String Text = "SELECT v FROM Objective as v, t_partition as p , Study as s, Variables_Partitions as vp "
				+ "WHERE p.id_partition = vp.partition.id_partition and v.id_variable = vp.variable.id_variable "
				+ "and p.study.id_study = s.id_study and  s.id_study =:id";

		Query query = em.createQuery(Text);
		query.setParameter("id", id);			    	
		List<Objective> variables = query.getResultList();
		return variables;
	}

	// find  Objective By Project
	@Override
	public Objective findObjectiveByProject(Integer id){
		String Text = "SELECT v FROM Objective as v, t_partition as p , Study as s, Project as pr, Variables_Partitions as vp "
				+ "WHERE p.id_partition = vp.partition.id_partition and v.id_variable = vp.variable.id_variable "
				+ "and p.study.id_study = s.id_study and s.project.id_project = pr.id_project and  pr.id_project =:id";

		Query query = em.createQuery(Text);
		query.setParameter("id", id);
		query.setFirstResult(0);
		query.setMaxResults(1);

		Objective variable = (Objective)query.getSingleResult();
		return variable;
	}

	// find Objectives by Project  
	@Override
	public List<Objective> findObjectivesByProject(Integer id){
		String Text = "SELECT v FROM Objective as v, t_partition as p , Study as s, Project as pr, Variables_Partitions as vp "
				+ "WHERE p.id_partition = vp.partition.id_partition and v.id_variable = vp.variable.id_variable "
				+ "and p.study.id_study = s.id_study and s.project.id_project = pr.id_project and  pr.id_project =:id";

		Query query = em.createQuery(Text);
		query.setParameter("id", id);			    	
		List<Objective> variables = query.getResultList();
		return variables;
	}

	// find  Objective By Team
	@Override
	public Objective findObjectiveByTeam(Integer id){
		String Text = "SELECT v FROM Variable as v, t_partition as p , Study as s, Team as t, Variables_Partitions as vp "
				+ "WHERE p.id_partition = vp.partition.id_partition and v.id_variable = vp.variable.id_variable "
				+ "and p.study.id_study = s.id_study and s.team.id_team = t.id_team and  t.id_team =:id";

		Query query = em.createQuery(Text);
		query.setParameter("id", id);
		query.setFirstResult(0);
		query.setMaxResults(1);

		Objective variable = (Objective)query.getSingleResult();
		return variable;
	}

	// find Variables by Team  
	@Override
	public List<Objective> findObjectivesByTeam(Integer id){
		String Text = "SELECT v FROM Objective as v, t_partition as p , Study as s, Team as t, Variables_Partitions as vp "
				+ "WHERE p.id_partition = vp.partition.id_partition and v.id_variable = vp.variable.id_variable "
				+ "and p.study.id_study = s.id_study and s.team.id_team = t.id_team and  t.id_team =:id";

		Query query = em.createQuery(Text);
		query.setParameter("id", id);			    	
		List<Objective> variables = query.getResultList();
		return variables;
	}
	// find  Objective By Engineer
	@Override
	public Objective findObjectiveByEngineer(Integer id){
		String Text = "SELECT v FROM Objective as v, t_partition as p , Study as s, Team as t, Variables_Partitions as vp, Teams_Engineers te, Engineer e  "
				+ "WHERE p.id_partition = vp.partition.id_partition and v.id_variable = vp.variable.id_variable "
				+ "and p.study.id_study = s.id_study and s.team.id_team = t.id_team and te.team.id_team = t.id_team "
				+ "and te.engineer.id_user = e.id_user and  e.id_user =:id";

		Query query = em.createQuery(Text);
		query.setParameter("id", id);
		query.setFirstResult(0);
		query.setMaxResults(1);

		Objective variable = (Objective)query.getSingleResult();
		return variable;
	}

	// find Objectives by Engineer  
	@Override
	public List<Objective> findObjectivesByEngineer(Integer id){
		String Text = "SELECT v FROM Objective as v, t_partition as p , Study as s, Team as t, Variables_Partitions as vp, Teams_Engineers te, Engineer e  "
				+ "WHERE p.id_partition = vp.partition.id_partition and v.id_variable = vp.variable.id_variable "
				+ "and p.study.id_study = s.id_study and s.team.id_team = t.id_team and te.team.id_team = t.id_team "
				+ "and te.engineer.id_user = e.id_user and  e.id_user =:id";

		Query query = em.createQuery(Text);
		query.setParameter("id", id);			    	
		List<Objective> variables = query.getResultList();
		return variables;
	}

	// find  Objective By Coordinator
	@Override
	public Objective findObjectiveByCoordinator(Integer id){
		String Text = "SELECT v FROM Objective as v, t_partition as p , Study as s, Team as t, Variables_Partitions as vp, Coordinator c "
				+ "WHERE p.id_partition = vp.partition.id_partition and v.id_variable = vp.variable.id_variable "
				+ "and p.study.id_study = s.id_study and s.team.id_team = t.id_team "
				+ "and t.coordinator.id_user = c.id_user and c.id_user =:id";

		Query query = em.createQuery(Text);
		query.setParameter("id", id);
		query.setFirstResult(0);
		query.setMaxResults(1);

		Objective variable = (Objective)query.getSingleResult();
		return variable;
	}

	// find Objectives by Coordinator  
	@Override
	public List<Objective> findObjectivesByCoordinator(Integer id){
		String Text = "SELECT v FROM Objective as v, t_partition as p , Study as s, Team as t, Variables_Partitions as vp, Coordinator c "
				+ "WHERE p.id_partition = vp.partition.id_partition and v.id_variable = vp.variable.id_variable "
				+ "and p.study.id_study = s.id_study and s.team.id_team = t.id_team "
				+ "and t.coordinator.id_user = c.id_user and c.id_user =:id";

		Query query = em.createQuery(Text);
		query.setParameter("id", id);			    	
		List<Objective> variables = query.getResultList();
		return variables;
	}

	// find  Objective By Manager
	@Override
	public Objective findObjectiveByManager(Integer id){
		String Text = "SELECT v FROM Objective as v, t_partition as p , Study as s, Project as pr, Variables_Partitions as vp, Manager m "
				+ "WHERE p.id_partition = vp.partition.id_partition and v.id_variable = vp.variable.id_variable "
				+ "and p.study.id_study = s.id_study and s.project.id_project = pr.id_project "
				+ "and pr.manager.id_user = m.id_user and m.id_user =:id";

		Query query = em.createQuery(Text);
		query.setParameter("id", id);
		query.setFirstResult(0);
		query.setMaxResults(1);

		Objective variable = (Objective)query.getSingleResult();
		return variable;
	}

	// find Objectives by Manager  
	@Override
	public List<Objective> findObjectivesByManager(Integer id){
		String Text = "SELECT v FROM Objective as v, t_partition as p , Study as s, Project as pr, Variables_Partitions as vp, Manager m "
				+ "WHERE p.id_partition = vp.partition.id_partition and v.id_variable = vp.variable.id_variable "
				+ "and p.study.id_study = s.id_study and s.project.id_project = pr.id_project "
				+ "and pr.manager.id_user = m.id_user and m.id_user =:id";

		Query query = em.createQuery(Text);
		query.setParameter("id", id);			    	
		List<Objective> variables = query.getResultList();
		return variables;
	}

	// find Objective by Visibility 
	@Override
	public List<Objective> findObjectiveByVisibility(Boolean visibility){
		String Text = "SELECT v FROM Objective v WHERE v.visibility =:visibility";
		Query query = em.createQuery(Text);
		query.setParameter("visibility", visibility);
		List<Objective> Variables = query.getResultList();
		return Variables;
	}
	// find Shared Objectives by Manager  
	@Override
	public List<Objective> findSharedObjectivesByManager(Integer id){
		String Text = "SELECT v FROM Objective as v, t_partition as p , Study as s, Project as pr, Variables_Partitions as vp, Manager m "
				+ "WHERE p.id_partition = vp.partition.id_partition and v.id_variable = vp.variable.id_variable "
				+ "and p.study.id_study = s.id_study and s.project.id_project = pr.id_project "
				+ "and pr.manager.id_user = m.id_user and m.id_user =:id and v.visibility = 1";

		Query query = em.createQuery(Text);
		query.setParameter("id", id);			    	
		List<Objective> variables = query.getResultList();
		return variables;
	}

	// find Shared Objectives by Coordinator  
	@Override
	public List<Objective> findSharedObjectivesByCoordinator(Integer id){
		String Text = "SELECT v FROM Objective as v, t_partition as p , Study as s, Team as t, Variables_Partitions as vp, Coordinator c "
				+ "WHERE p.id_partition = vp.partition.id_partition and v.id_variable = vp.variable.id_variable "
				+ "and p.study.id_study = s.id_study and s.team.id_team = t.id_team "
				+ "and t.coordinator.id_user = c.id_user and c.id_user =:id and v.visibility = 1";

		Query query = em.createQuery(Text);
		query.setParameter("id", id);			    	
		List<Objective> variables = query.getResultList();
		return variables;
	}


	// find Shared Objectives by Engineer  
	@Override
	public List<Objective> findSharedObjectivesByEngineer(Integer id){
		String Text = "SELECT v FROM Objective as v, t_partition as p , Study as s, Team as t, Variables_Partitions as vp, Teams_Engineers te, Engineer e  "
				+ "WHERE p.id_partition = vp.partition.id_partition and v.id_variable = vp.variable.id_variable "
				+ "and p.study.id_study = s.id_study and s.team.id_team = t.id_team and te.team.id_team = t.id_team "
				+ "and te.engineer.id_user = e.id_user and  e.id_user =:id and v.visibility = 1";

		Query query = em.createQuery(Text);
		query.setParameter("id", id);			    	
		List<Objective> variables = query.getResultList();
		return variables;
	}

	// find Local Objectives by Manager  
	@Override
	public List<Objective> findLocalObjectivesByManager(Integer id){
		String Text = "SELECT v FROM Objective as v, t_partition as p , Study as s, Project as pr, Variables_Partitions as vp, Manager m "
				+ "WHERE p.id_partition = vp.partition.id_partition and v.id_variable = vp.variable.id_variable "
				+ "and p.study.id_study = s.id_study and s.project.id_project = pr.id_project "
				+ "and pr.manager.id_user = m.id_user and m.id_user =:id and v.visibility = 0";

		Query query = em.createQuery(Text);
		query.setParameter("id", id);			    	
		List<Objective> variables = query.getResultList();
		return variables;
	}

	// find Local Objectives by Coordinator  
	@Override
	public List<Objective> findLocalObjectivesByCoordinator(Integer id){
		String Text = "SELECT v FROM Objective as v, t_partition as p , Study as s, Team as t, Variables_Partitions as vp, Coordinator c "
				+ "WHERE p.id_partition = vp.partition.id_partition and v.id_variable = vp.variable.id_variable "
				+ "and p.study.id_study = s.id_study and s.team.id_team = t.id_team "
				+ "and t.coordinator.id_user = c.id_user and c.id_user =:id and v.visibility = 0";

		Query query = em.createQuery(Text);
		query.setParameter("id", id);			    	
		List<Objective> variables = query.getResultList();
		return variables;
	}


	// find Local Objectives by Engineer  
	@Override
	public List<Objective> findLocalObjectivesByEngineer(Integer id){
		String Text = "SELECT v FROM Objective as v, t_partition as p , Study as s, Team as t, Variables_Partitions as vp, Teams_Engineers te, Engineer e  "
				+ "WHERE p.id_partition = vp.partition.id_partition and v.id_variable = vp.variable.id_variable "
				+ "and p.study.id_study = s.id_study and s.team.id_team = t.id_team and te.team.id_team = t.id_team "
				+ "and te.engineer.id_user = e.id_user and  e.id_user =:id and v.visibility = 0";

		Query query = em.createQuery(Text);
		query.setParameter("id", id);			    	
		List<Objective> variables = query.getResultList();
		return variables;
	}

	//Find Objective with sets 

	@Override
	public List<Set> findObjectivewithSet(Integer id){
		String Text = "SELECT s FROM Objective as v, t_set as s  "
				+ "WHERE  v.id_variable =:id ";

		Query query = em.createQuery(Text);
		query.setParameter("id", id);			    	

		List<Set> set = query.getResultList();
		return set;
	}
}
