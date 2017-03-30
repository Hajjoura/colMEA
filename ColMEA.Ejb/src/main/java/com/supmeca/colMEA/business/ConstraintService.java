package com.supmeca.colMEA.business;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.supmeca.colMEA.domain.Constraint;
import com.supmeca.colMEA.domain.Coordinator;
import com.supmeca.colMEA.domain.Set;

/**
 * Session Bean implementation class ConstraintService
 */
@Stateless
@LocalBean
public class ConstraintService implements ConstraintServiceRemote, ConstraintServiceLocal {

	@PersistenceContext
	private EntityManager em;
	
	Constraint Constraint;
    /**
     * Default constructor. 
     */
    public ConstraintService() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void CreateConstraint(Constraint Constraint) {
		em.persist(Constraint);
	}

	@Override
	public void EditConstraint(Constraint Constraint) {
		em.merge(Constraint);
	}

	@Override
	public void removeConstraint(Constraint Constraint) {
		em.remove(em.merge(Constraint));
	}

	@Override
	public Constraint findConstraintById(int id) {
		Constraint Constraint=em.find(Constraint.class,id);
		if(Constraint!=null){
			return Constraint;
		}
		return null;
	}

	@Override
	public List<Constraint> findAllConstraints() {
		String text = "SELECT c FROM Constraint c";
		Query query = em.createQuery(text);
		List<Constraint> ListConstraints = query.getResultList();
		
		return ListConstraints;
	}

	@Override
	public void DeleteConstraint(int id) {
		em.remove(em.find(Constraint.class, id));	
		
	}

	// find Constraint by name 
		@Override
		public Constraint findConstraintByName(String name){
			String Text = "SELECT v FROM Constraint v WHERE v.name =:name";
			Query query = em.createQuery(Text);
			query.setParameter("name", name);
			query.setFirstResult(0);
			query.setMaxResults(1);
			Constraint Variable = (Constraint)query.getSingleResult();
			return Variable;
		}
		// find Constraint by min and max  

		@Override
		public Constraint findConstraintByMinMax(float min, float max){
			String Text = "SELECT v FROM Constraint v WHERE v.min =:min and v.max =:max";
			Query query = em.createQuery(Text);
			query.setFirstResult(0);
			query.setMaxResults(1);
			query.setParameter("min", min);
			query.setParameter("max", max);

			Constraint Variable = (Constraint)query.getSingleResult();
			return Variable;
		}
		// find Constraints by min and max  

		@Override
		public List<Constraint> findConstraintsByMinMax(float min, float max){
			String Text = "SELECT v FROM Constraint v WHERE v.min =:min and v.max =:max";
			Query query = em.createQuery(Text);
			query.setParameter("min", min);
			query.setParameter("max", max);

			List<Constraint> Variables = query.getResultList();
			return Variables;
		}
		// find Constraint by minRes and maxRes  

		@Override
		public Constraint findConstraintByMinMaxRes(float minRes, float maxRes){
			String Text = "SELECT v FROM Constraint v WHERE v.min_res =:minRes and v.max_res =:maxRes";
			Query query = em.createQuery(Text);
			query.setFirstResult(0);
			query.setMaxResults(1);
			query.setParameter("minRes", minRes);
			query.setParameter("maxRes", maxRes);

			Constraint Constraint = (Constraint)query.getSingleResult();
			return Constraint;
		}
		// find Constraint by minRes and maxRes  
		@Override
		public List<Constraint> findConstraintsByMinMaxRes(float minRes, float maxRes){
			String Text = "SELECT v FROM Constraint v WHERE v.min_res =:minRes and v.max_res =:maxRes";
			Query query = em.createQuery(Text);
			query.setParameter("minRes", minRes);
			query.setParameter("maxRes", maxRes);
			List<Constraint> Variables = query.getResultList();
			return Variables;
		}

		// find  Constraint By Partition
		@Override
		public Constraint findConstraintByPartition(Integer id){
			String Text = "SELECT v FROM Constraint as v, t_partition as p , Variables_Partitions as vp "
					+ "WHERE p.id_partition = vp.partition.id_partition and v.id_variable = vp.variable.id_variable "
					+ "and p.id_partition =:id";

			Query query = em.createQuery(Text);
			query.setParameter("id", id);
			query.setFirstResult(0);
			query.setMaxResults(1);

			Constraint variable = (Constraint)query.getSingleResult();
			return variable;
		}

		// find Constraints by Partition  
		@Override
		public List<Constraint> findConstraintsByPartition(Integer id){
			String Text = "SELECT v FROM Constraint as v, t_partition as p , Variables_Partitions as vp "
					+ "WHERE p.id_partition = vp.partition.id_partition and v.id_variable = vp.variable.id_variable "
					+ "and p.id_partition =:id";

			Query query = em.createQuery(Text);
			query.setParameter("id", id);			    	
			List<Constraint> variables = query.getResultList();
			return variables;
		}
		// find  Constraint By Study
		@Override
		public Constraint findConstraintByStudy(Integer id){
			String Text = "SELECT v FROM Constraint as v, t_partition as p , Study as s, Variables_Partitions as vp "
					+ "WHERE p.id_partition = vp.partition.id_partition and v.id_variable = vp.variable.id_variable "
					+ "and p.study.id_study = s.id_study and  s.id_study =:id";

			Query query = em.createQuery(Text);
			query.setParameter("id", id);
			query.setFirstResult(0);
			query.setMaxResults(1);

			Constraint variable = (Constraint)query.getSingleResult();
			return variable;
		}

		// find Constraints by Study  
		@Override
		public List<Constraint> findConstraintsByStudy(Integer id){
			String Text = "SELECT v FROM Constraint as v, t_partition as p , Study as s, Variables_Partitions as vp "
					+ "WHERE p.id_partition = vp.partition.id_partition and v.id_variable = vp.variable.id_variable "
					+ "and p.study.id_study = s.id_study and  s.id_study =:id";

			Query query = em.createQuery(Text);
			query.setParameter("id", id);			    	
			List<Constraint> variables = query.getResultList();
			return variables;
		}

		// find  Constraint By Project
		@Override
		public Constraint findConstraintByProject(Integer id){
			String Text = "SELECT v FROM Constraint as v, t_partition as p , Study as s, Project as pr, Variables_Partitions as vp "
					+ "WHERE p.id_partition = vp.partition.id_partition and v.id_variable = vp.variable.id_variable "
					+ "and p.study.id_study = s.id_study and s.project.id_project = pr.id_project and  pr.id_project =:id";

			Query query = em.createQuery(Text);
			query.setParameter("id", id);
			query.setFirstResult(0);
			query.setMaxResults(1);

			Constraint variable = (Constraint)query.getSingleResult();
			return variable;
		}

		// find Constraints by Project  
		@Override
		public List<Constraint> findConstraintsByProject(Integer id){
			String Text = "SELECT v FROM Constraint as v, t_partition as p , Study as s, Project as pr, Variables_Partitions as vp "
					+ "WHERE p.id_partition = vp.partition.id_partition and v.id_variable = vp.variable.id_variable "
					+ "and p.study.id_study = s.id_study and s.project.id_project = pr.id_project and  pr.id_project =:id";

			Query query = em.createQuery(Text);
			query.setParameter("id", id);			    	
			List<Constraint> variables = query.getResultList();
			return variables;
		}

		// find  Constraint By Team
		@Override
		public Constraint findConstraintByTeam(Integer id){
			String Text = "SELECT v FROM Variable as v, t_partition as p , Study as s, Team as t, Variables_Partitions as vp "
					+ "WHERE p.id_partition = vp.partition.id_partition and v.id_variable = vp.variable.id_variable "
					+ "and p.study.id_study = s.id_study and s.team.id_team = t.id_team and  t.id_team =:id";

			Query query = em.createQuery(Text);
			query.setParameter("id", id);
			query.setFirstResult(0);
			query.setMaxResults(1);

			Constraint variable = (Constraint)query.getSingleResult();
			return variable;
		}

		// find Variables by Team  
		@Override
		public List<Constraint> findConstraintsByTeam(Integer id){
			String Text = "SELECT v FROM Constraint as v, t_partition as p , Study as s, Team as t, Variables_Partitions as vp "
					+ "WHERE p.id_partition = vp.partition.id_partition and v.id_variable = vp.variable.id_variable "
					+ "and p.study.id_study = s.id_study and s.team.id_team = t.id_team and  t.id_team =:id";

			Query query = em.createQuery(Text);
			query.setParameter("id", id);			    	
			List<Constraint> variables = query.getResultList();
			return variables;
		}
		// find  Constraint By Engineer
		@Override
		public Constraint findConstraintByEngineer(Integer id){
			String Text = "SELECT v FROM Constraint as v, t_partition as p , Study as s, Team as t, Variables_Partitions as vp, Teams_Engineers te, Engineer e  "
					+ "WHERE p.id_partition = vp.partition.id_partition and v.id_variable = vp.variable.id_variable "
					+ "and p.study.id_study = s.id_study and s.team.id_team = t.id_team and te.team.id_team = t.id_team "
					+ "and te.engineer.id_user = e.id_user and  e.id_user =:id";

			Query query = em.createQuery(Text);
			query.setParameter("id", id);
			query.setFirstResult(0);
			query.setMaxResults(1);

			Constraint variable = (Constraint)query.getSingleResult();
			return variable;
		}

		// find Constraints by Engineer  
		@Override
		public List<Constraint> findConstraintsByEngineer(Integer id){
			String Text = "SELECT v FROM Constraint as v, t_partition as p , Study as s, Team as t, Variables_Partitions as vp, Teams_Engineers te, Engineer e  "
					+ "WHERE p.id_partition = vp.partition.id_partition and v.id_variable = vp.variable.id_variable "
					+ "and p.study.id_study = s.id_study and s.team.id_team = t.id_team and te.team.id_team = t.id_team "
					+ "and te.engineer.id_user = e.id_user and  e.id_user =:id";

			Query query = em.createQuery(Text);
			query.setParameter("id", id);			    	
			List<Constraint> variables = query.getResultList();
			return variables;
		}

		// find  Constraint By Coordinator
		@Override
		public Constraint findConstraintByCoordinator(Integer id){
			String Text = "SELECT v FROM Constraint as v, t_partition as p , Study as s, Team as t, Variables_Partitions as vp, Coordinator c "
					+ "WHERE p.id_partition = vp.partition.id_partition and v.id_variable = vp.variable.id_variable "
					+ "and p.study.id_study = s.id_study and s.team.id_team = t.id_team "
					+ "and t.coordinator.id_user = c.id_user and c.id_user =:id";

			Query query = em.createQuery(Text);
			query.setParameter("id", id);
			query.setFirstResult(0);
			query.setMaxResults(1);

			Constraint variable = (Constraint)query.getSingleResult();
			return variable;
		}

		// find Constraints by Coordinator  
		@Override
		public List<Constraint> findConstraintsByCoordinator(Integer id){
			String Text = "SELECT v FROM Constraint as v, t_partition as p , Study as s, Team as t, Variables_Partitions as vp, Coordinator c "
					+ "WHERE p.id_partition = vp.partition.id_partition and v.id_variable = vp.variable.id_variable "
					+ "and p.study.id_study = s.id_study and s.team.id_team = t.id_team "
					+ "and t.coordinator.id_user = c.id_user and c.id_user =:id";

			Query query = em.createQuery(Text);
			query.setParameter("id", id);			    	
			List<Constraint> variables = query.getResultList();
			return variables;
		}

		// find  Constraint By Manager
		@Override
		public Constraint findConstraintByManager(Integer id){
			String Text = "SELECT v FROM Constraint as v, t_partition as p , Study as s, Project as pr, Variables_Partitions as vp, Manager m "
					+ "WHERE p.id_partition = vp.partition.id_partition and v.id_variable = vp.variable.id_variable "
					+ "and p.study.id_study = s.id_study and s.project.id_project = pr.id_project "
					+ "and pr.manager.id_user = m.id_user and m.id_user =:id";

			Query query = em.createQuery(Text);
			query.setParameter("id", id);
			query.setFirstResult(0);
			query.setMaxResults(1);

			Constraint variable = (Constraint)query.getSingleResult();
			return variable;
		}

		// find Constraints by Manager  
		@Override
		public List<Constraint> findConstraintsByManager(Integer id){
			String Text = "SELECT v FROM Constraint as v, t_partition as p , Study as s, Project as pr, Variables_Partitions as vp, Manager m "
					+ "WHERE p.id_partition = vp.partition.id_partition and v.id_variable = vp.variable.id_variable "
					+ "and p.study.id_study = s.id_study and s.project.id_project = pr.id_project "
					+ "and pr.manager.id_user = m.id_user and m.id_user =:id";

			Query query = em.createQuery(Text);
			query.setParameter("id", id);			    	
			List<Constraint> variables = query.getResultList();
			return variables;
		}

		// find Constraint by Visibility 
		@Override
		public List<Constraint> findConstraintByVisibility(Boolean visibility){
			String Text = "SELECT v FROM Constraint v WHERE v.visibility =:visibility";
			Query query = em.createQuery(Text);
			query.setParameter("visibility", visibility);
			List<Constraint> Variables = query.getResultList();
			return Variables;
		}
		// find Shared Constraints by Manager  
		@Override
		public List<Constraint> findSharedConstraintsByManager(Integer id){
			String Text = "SELECT v FROM Constraint as v, t_partition as p , Study as s, Project as pr, Variables_Partitions as vp, Manager m "
					+ "WHERE p.id_partition = vp.partition.id_partition and v.id_variable = vp.variable.id_variable "
					+ "and p.study.id_study = s.id_study and s.project.id_project = pr.id_project "
					+ "and pr.manager.id_user = m.id_user and m.id_user =:id and v.visibility = 1";

			Query query = em.createQuery(Text);
			query.setParameter("id", id);			    	
			List<Constraint> variables = query.getResultList();
			return variables;
		}
		
		// find Shared Constraints by Coordinator  
		@Override
		public List<Constraint> findSharedConstraintsByCoordinator(Integer id){
			String Text = "SELECT v FROM Constraint as v, t_partition as p , Study as s, Team as t, Variables_Partitions as vp, Coordinator c "
					+ "WHERE p.id_partition = vp.partition.id_partition and v.id_variable = vp.variable.id_variable "
					+ "and p.study.id_study = s.id_study and s.team.id_team = t.id_team "
					+ "and t.coordinator.id_user = c.id_user and c.id_user =:id and v.visibility = 1";

			Query query = em.createQuery(Text);
			query.setParameter("id", id);			    	
			List<Constraint> variables = query.getResultList();
			return variables;
		}
		

		// find Shared Constraints by Engineer  
		@Override
		public List<Constraint> findSharedConstraintsByEngineer(Integer id){
			String Text = "SELECT v FROM Constraint as v, t_partition as p , Study as s, Team as t, Variables_Partitions as vp, Teams_Engineers te, Engineer e  "
					+ "WHERE p.id_partition = vp.partition.id_partition and v.id_variable = vp.variable.id_variable "
					+ "and p.study.id_study = s.id_study and s.team.id_team = t.id_team and te.team.id_team = t.id_team "
					+ "and te.engineer.id_user = e.id_user and  e.id_user =:id and v.visibility = 1";

			Query query = em.createQuery(Text);
			query.setParameter("id", id);			    	
			List<Constraint> variables = query.getResultList();
			return variables;
		}
		
		// find Local Constraints by Manager  
			@Override
			public List<Constraint> findLocalConstraintsByManager(Integer id){
				String Text = "SELECT v FROM Constraint as v, t_partition as p , Study as s, Project as pr, Variables_Partitions as vp, Manager m "
						+ "WHERE p.id_partition = vp.partition.id_partition and v.id_variable = vp.variable.id_variable "
						+ "and p.study.id_study = s.id_study and s.project.id_project = pr.id_project "
						+ "and pr.manager.id_user = m.id_user and m.id_user =:id and v.visibility = 0";

				Query query = em.createQuery(Text);
				query.setParameter("id", id);			    	
				List<Constraint> variables = query.getResultList();
				return variables;
			}
			
			// find Local Constraints by Coordinator  
			@Override
			public List<Constraint> findLocalConstraintsByCoordinator(Integer id){
				String Text = "SELECT v FROM Constraint as v, t_partition as p , Study as s, Team as t, Variables_Partitions as vp, Coordinator c "
						+ "WHERE p.id_partition = vp.partition.id_partition and v.id_variable = vp.variable.id_variable "
						+ "and p.study.id_study = s.id_study and s.team.id_team = t.id_team "
						+ "and t.coordinator.id_user = c.id_user and c.id_user =:id and v.visibility = 0";

				Query query = em.createQuery(Text);
				query.setParameter("id", id);			    	
				List<Constraint> variables = query.getResultList();
				return variables;
			}
			

			// find Local Constraints by Engineer  
			@Override
			public List<Constraint> findLocalConstraintsByEngineer(Integer id){
				String Text = "SELECT v FROM Constraint as v, t_partition as p , Study as s, Team as t, Variables_Partitions as vp, Teams_Engineers te, Engineer e  "
						+ "WHERE p.id_partition = vp.partition.id_partition and v.id_variable = vp.variable.id_variable "
						+ "and p.study.id_study = s.id_study and s.team.id_team = t.id_team and te.team.id_team = t.id_team "
						+ "and te.engineer.id_user = e.id_user and  e.id_user =:id and v.visibility = 0";

				Query query = em.createQuery(Text);
				query.setParameter("id", id);			    	
				List<Constraint> variables = query.getResultList();
				return variables;
			}
			
			//Find Constraint with sets 
			
			@Override
			public List<Set> findConstraintwithSet(Integer id){
				String Text = "SELECT s FROM Constraint as v, t_set as s  "
						+ "WHERE  v.id_variable =:id ";

				Query query = em.createQuery(Text);
				query.setParameter("id", id);			    	
				
				List<Set> set = query.getResultList();
				return set;
			}
}
