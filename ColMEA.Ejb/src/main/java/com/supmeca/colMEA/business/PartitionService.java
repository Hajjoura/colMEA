package com.supmeca.colMEA.business;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.mail.Flags.Flag;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.supmeca.colMEA.domain.Coordinator;
import com.supmeca.colMEA.domain.Partition;
import com.supmeca.colMEA.domain.Project;
import com.supmeca.colMEA.domain.Set;
import com.supmeca.colMEA.domain.Teams_Engineers;
import com.supmeca.colMEA.domain.Variable;
import com.supmeca.colMEA.domain.Variables_Partitions;
import com.supmeca.colMEA.domain.Variables_PartitionsFK;

/**
 * Session Bean implementation class PartitionService
 */
@Stateless
@LocalBean
public class PartitionService implements PartitionServiceRemote, PartitionServiceLocal {

	@PersistenceContext
	private EntityManager em;

	Partition Partition;
	/**
	 * Default constructor. 
	 */
	public PartitionService() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void CreatePartition(Partition Partition) {
		em.persist(Partition);
	}

	@Override
	public void EditPartition(Partition Partition) {
		em.merge(Partition);
	}

	@Override
	public void removePartition(Partition Partition) {
		em.remove(em.merge(Partition));
	}

	@Override
	public Partition findPartitionById(int id) {
		Partition Partition=em.find(Partition.class,id);
		if(Partition!=null){
			return Partition;
		}
		return null;
	}

	@Override
	public List<Partition> findAllPartitions() {
		String text = "SELECT p FROM t_partition p";
		Query query = em.createQuery(text);
		List<Partition> ListPartitions = query.getResultList();

		return ListPartitions;
	}

	@Override
	public void DeletePartition(int id) {
		em.remove(em.find(Partition.class, id));	

	}

	//Add partition to variable
	@Override
	public Boolean addVariableToPartition(Partition partition, Variable variable,Set set ,Date date, Float min, Float max) {

		try{
			Variables_Partitions varpart = new Variables_Partitions(em.merge(variable),em.merge(partition), em.merge(set), date, min, max);

			em.persist(varpart);
			return true;	
		}catch(Exception e){
			return false;
		}
	}

	//Display Variables_Partitions
	@Override
	public List<Variables_Partitions> findVariablesPartitions() {
		String text = "SELECT vp FROM Variables_Partitions vp";
		Query query = em.createQuery(text);
		List<Variables_Partitions> ListTeams = query.getResultList();

		return ListTeams;
	}

	//update Variables_Partitions
	@Override
	public Boolean updateVariableToPartition(Partition partition, Variable variable,Set set, Date date, Float min , Float max) {

		try{
			Variables_Partitions varpart = new Variables_Partitions(em.merge(variable),em.merge(partition), em.merge(set), date, min, max);

			em.merge(varpart);
			return true;	
		}catch(Exception e){
			return false;
		}
	}
	
	//find Variables partitions by id variable
	
	@Override
	public List<Variables_Partitions> findVariablePartitionByIdVable(Integer id) {
		String text = "SELECT vp FROM Variables_Partitions as vp WHERE vp.variables_partitionsFK.id_variable =:id";
		
		Query query = em.createQuery(text);
		query.setParameter("id", id);
		List<Variables_Partitions> List = query.getResultList();

		return List;
	}

	//find Variables partitions by id partition
	
	@Override
	public List<Variables_Partitions> findVariablePartitionByIdPart(Integer id) {
		String text = "SELECT vp FROM Variables_Partitions as vp WHERE vp.variables_partitionsFK.id_partition =:id";
		
		Query query = em.createQuery(text);
		query.setParameter("id", id);
		List<Variables_Partitions> List = query.getResultList();

		return List;
	}
	//find Variables partitions by id Set
	
	@Override
	public List<Variables_Partitions> findVariablePartitionByIdSet(Integer id) {
		String text = "SELECT vp FROM Variables_Partitions as vp WHERE vp.variables_partitionsFK.id_set =:id";
		
		Query query = em.createQuery(text);
		query.setParameter("id", id);
		List<Variables_Partitions> List = query.getResultList();

		return List;
	}
	//find variable by id
	@Override
	public Variables_Partitions findVariableById(Variables_PartitionsFK id) {
		Variables_Partitions varpart =null;
		try{
			varpart = em.find(Variables_Partitions.class,id);

			if (varpart != null)
			{
				return varpart;	
			}
			return null;
		}catch(Exception e){
			return null;
		}
	}
	// find Partition by name Partition
	@Override
	public Partition findPartitionByName(String name){
		String Text = "SELECT p FROM t_partition p WHERE p.name =:name";
		Query query = em.createQuery(Text);
		query.setParameter("name", name);
		query.setFirstResult(0);
		query.setMaxResults(1);
		Partition Partition = (Partition)query.getSingleResult();
		return Partition;
	}
	// find partition by team
	@Override
	public Partition findPartitionByTeam(Integer id){
		String Text = "SELECT p FROM t_partition as p ,Team as t, Study as s "
				+ "WHERE p.study.id_study = s.id_study and s.team.id_team = t.id_team and t.id_team =:id";

		Query query = em.createQuery(Text);
		query.setParameter("id", id);
		query.setFirstResult(0);
		query.setMaxResults(1);

		Partition Partition = (Partition)query.getSingleResult();
		return Partition;
	}
	// find Partitions by team
	@Override
	public List<Partition> findPartitionsByTeam(Integer id){
		String Text = "SELECT p FROM t_partition as p ,Team as t, Study as s "
				+ "WHERE p.study.id_study = s.id_study and s.team.id_team = t.id_team and t.id_team =:id";

		Query query = em.createQuery(Text);
		query.setParameter("id", id);			    	
		List<Partition> Partitions = query.getResultList();
		return Partitions;
	}

	// find Partition by Project
	@Override
	public Partition findPartitionByProject(Integer id){
		String Text = "SELECT p FROM t_partition as p ,Project as pr, Study as s "
				+ "WHERE p.study.id_study = s.id_study and s.project.id_project = pr.id_project and pr.id_project =:id";

		Query query = em.createQuery(Text);
		query.setParameter("id", id);
		query.setFirstResult(0);
		query.setMaxResults(1);

		Partition Partition = (Partition)query.getSingleResult();
		return Partition;
	}
	// find Partitions by project
	@Override
	public List<Partition> findPartitionsByProject(Integer id){
		String Text = "SELECT p FROM t_partition as p ,Project as pr, Study as s "
				+ "WHERE p.study.id_study = s.id_study and s.project.id_project = pr.id_project and pr.id_project =:id";

		Query query = em.createQuery(Text);
		query.setParameter("id", id);			    	
		List<Partition> Partitions = query.getResultList();
		return Partitions;
	}
	// find Partition by Study
	@Override
	public Partition findPartitionByStudy(Integer id){
		String Text = "SELECT p FROM t_partition as p , Study as s "
				+ "WHERE p.study.id_study = s.id_study and s.id_study =:id";

		Query query = em.createQuery(Text);
		query.setParameter("id", id);
		query.setFirstResult(0);
		query.setMaxResults(1);

		Partition Partition = (Partition)query.getSingleResult();
		return Partition;
	}
	// find Partitions by Study
	@Override
	public List<Partition> findPartitionsByStudy(Integer id){
		String Text = "SELECT p FROM t_partition as p ,Project as pr, Study as s "
				+ "WHERE p.study.id_study = s.id_study and s.id_study =:id";

		Query query = em.createQuery(Text);
		query.setParameter("id", id);			    	
		List<Partition> Partitions = query.getResultList();
		return Partitions;
	}
	// find Partition by Engineer
	@Override
	public Partition findPartitionByEngineer(Integer id){
		String Text = "SELECT p FROM t_partition as p , Study as s, Team as t, Engineer as e, Teams_Engineers as te "
				+ "WHERE p.study.id_study = s.id_study and s.team.id_team = t.id_team and te.team.id_team = t.id_team "
				+ "and te.engineer.id_user = e.id_user and e.id_user =:id";

		Query query = em.createQuery(Text);
		query.setParameter("id", id);
		query.setFirstResult(0);
		query.setMaxResults(1);

		Partition Partition = (Partition)query.getSingleResult();
		return Partition;
	}

	// find Partition by Coordinator
	@Override
	public Partition findPartitionByCoordinator(Integer id){
		String Text = "SELECT p FROM t_partition as p , Study as s, Team as t, Coordinator as c "
				+ "WHERE p.study.id_study = s.id_study and s.team.id_team = t.id_team and "
				+ "t.coordinator.id_user = c.id_user and c.id_user =:id";

		Query query = em.createQuery(Text);
		query.setParameter("id", id);
		query.setFirstResult(0);
		query.setMaxResults(1);

		Partition Partition = (Partition)query.getSingleResult();
		return Partition;
	}

	// find Partitions by Coordinator
	@Override
	public List<Partition> findPartitionsByCoordinator(Integer id){
		String Text = "SELECT p FROM t_partition as p , Study as s, Team as t, Coordinator as c "
				+ "WHERE p.study.id_study = s.id_study and s.team.id_team = t.id_team and "
				+ "t.coordinator.id_user = c.id_user and c.id_user =:id";

		Query query = em.createQuery(Text);
		query.setParameter("id", id);			    	
		List<Partition> Partitions = query.getResultList();
		return Partitions;
	}

	// find Partition by Manager
	@Override
	public Partition findPartitionByManager(Integer id){
		String Text = "SELECT p FROM t_partition as p , Study as s, Project as pr, Manager as m "
				+ "WHERE p.study.id_study = s.id_study and s.project.id_project = pr.id_project and "
				+ "pr.manager.id_user = m.id_user and m.id_user =:id";

		Query query = em.createQuery(Text);
		query.setParameter("id", id);
		query.setFirstResult(0);
		query.setMaxResults(1);

		Partition Partition = (Partition)query.getSingleResult();
		return Partition;
	}

	// find Partitions by Manager
	@Override
	public List<Partition> findPartitionsByManager(Integer id){
		String Text = "SELECT p FROM t_partition as p , Study as s, Project as pr, Manager as m "
				+ "WHERE p.study.id_study = s.id_study and s.project.id_project = pr.id_project and "
				+ "pr.manager.id_user = m.id_user and m.id_user =:id";

		Query query = em.createQuery(Text);
		query.setParameter("id", id);			    	
		List<Partition> Partitions = query.getResultList();
		return Partitions;
	}

	// find Partition by Variable
	@Override
	public Partition findPartitionByVariable(Integer id){
		String Text = "SELECT p FROM t_partition as p , Variable as v, Variables_Partitions as vp "
				+ "WHERE p.id_partition = vp.partition.id_partition and v.id_variable = vp.variable.id_variable "
				+ "and v.id_variable =:id";

		Query query = em.createQuery(Text);
		query.setParameter("id", id);
		query.setFirstResult(0);
		query.setMaxResults(1);

		Partition Partition = (Partition)query.getSingleResult();
		return Partition;
	}

	// find Partitions by Variable
	@Override
	public List<Partition> findPartitionsByVariable(Integer id){
		String Text = "SELECT DISTINCT p FROM t_partition as p , Variable as v, Variables_Partitions as vp "
				+ "WHERE p.id_partition = vp.partition.id_partition and v.id_variable = vp.variable.id_variable "
				+ "and v.id_variable =:id";

		Query query = em.createQuery(Text);
		query.setParameter("id", id);			    	
		List<Partition> Partitions = query.getResultList();
		return Partitions;
	}
	// find Partition by Constraint
	@Override
	public Partition findPartitionByConstraint(Integer id){
		String Text = "SELECT p FROM t_partition as p , Constraint as v, Variables_Partitions as vp "
				+ "WHERE p.id_partition = vp.partition.id_partition and v.id_variable = vp.variable.id_variable "
				+ "and v.id_variable =:id";

		Query query = em.createQuery(Text);
		query.setParameter("id", id);
		query.setFirstResult(0);
		query.setMaxResults(1);

		Partition Partition = (Partition)query.getSingleResult();
		return Partition;
	}

	// find Partitions by Constraint
	@Override
	public List<Partition> findPartitionsByConstraint(Integer id){
		String Text = "SELECT p FROM t_partition as p , Constraint as v, Variables_Partitions as vp "
				+ "WHERE p.id_partition = vp.partition.id_partition and v.id_variable = vp.variable.id_variable "
				+ "and v.id_variable =:id";

		Query query = em.createQuery(Text);
		query.setParameter("id", id);			    	
		List<Partition> Partitions = query.getResultList();
		return Partitions;
	}
	// find Partition by Objective
	@Override
	public Partition findPartitionByObjective(Integer id){
		String Text = "SELECT p FROM t_partition as p , Objective as v, Variables_Partitions as vp "
				+ "WHERE p.id_partition = vp.partition.id_partition and v.id_variable = vp.variable.id_variable "
				+ "and v.id_variable =:id";

		Query query = em.createQuery(Text);
		query.setParameter("id", id);
		query.setFirstResult(0);
		query.setMaxResults(1);

		Partition Partition = (Partition)query.getSingleResult();
		return Partition;
	}

	// find Partitions by Constraint
	@Override
	public List<Partition> findPartitionsByObjective(Integer id){
		String Text = "SELECT p FROM t_partition as p , Objective as v, Variables_Partitions as vp "
				+ "WHERE p.id_partition = vp.partition.id_partition and v.id_variable = vp.variable.id_variable "
				+ "and v.id_variable =:id";

		Query query = em.createQuery(Text);
		query.setParameter("id", id);			    	
		List<Partition> Partitions = query.getResultList();
		return Partitions;
	}






}
