package com.supmeca.colMEA.business;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.supmeca.colMEA.domain.Constraint;
import com.supmeca.colMEA.domain.Coordinator;
import com.supmeca.colMEA.domain.Manager;
import com.supmeca.colMEA.domain.Objective;
import com.supmeca.colMEA.domain.Partition;
import com.supmeca.colMEA.domain.Project;
import com.supmeca.colMEA.domain.Study;
import com.supmeca.colMEA.domain.Variable;

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
	public void CreateStudie(Study s, int id) {
	
		String Text = "insert into study (number,type, id_project) values (?,?,?)";
		Query query = em.createNativeQuery(Text);
		
		query.setParameter(1, s.getNumber());
		query.setParameter(2, s.getType());
		query.setParameter(3, id);
		
		query.executeUpdate();
		
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
				+ "WHERE t.id_team = s.team.id_team and t.id_team =:id";
		Query query = em.createQuery(Text);
		query.setParameter("id", id);
		List<Study> Studies = query.getResultList();
		return Studies;
	}
	// find Studies by Engineer
	@Override
	public List<Study> findStudiesByEngineer(Integer id) {
		String Text = "SELECT s FROM Study as s , Team as t , Engineer as e ,Teams_Engineers te "
				+ "WHERE t.id_team = s.team.id_team and t.id_team = te.team.id_team and "
				+ "e.id_user = te.engineer.id_user and e.id_user =:id";
		Query query = em.createQuery(Text);
		query.setParameter("id", id);
		List<Study> Studies = query.getResultList();
		return Studies;
	}
	// find Studies by Engineer
	@Override
	public Study findStudyByEngineer(Integer id) {
		String Text = "SELECT s FROM Study as s , Team as t , Engineer as e ,Teams_Engineers te "
				+ "WHERE t.id_team = s.team.id_team and t.id_team = te.team.id_team and "
				+ "e.id_user = te.engineer.id_user and e.id_user =:id";
		Query query = em.createQuery(Text);
		query.setParameter("id", id);
		query.setFirstResult(0);
		query.setMaxResults(1);
		Study Study = (Study)query.getSingleResult();
		return Study;
	}
	// find Studies by Coordinator
	@Override
	public List<Study> findStudiesByCoordinator(Integer id) {
		String Text = "SELECT s FROM Study as s , Team as t , Coordinator as e "
				+ "WHERE t.id_team = s.team.id_team  and "
				+ "e.id_user = t.coordinator.id_user and e.id_user =:id";
		Query query = em.createQuery(Text);
		query.setParameter("id", id);
		List<Study> Studies = query.getResultList();
		return Studies;
	}
	// find Studies by Manager
		@Override
		public List<Study> findStudiesByManager(Integer id) {
			String Text = "SELECT s FROM Study as s , Project as p , Manager as m "
					+ "WHERE p.id_project = s.project.id_project and "
					+ "e.id_user = p.manager.id_user and e.id_user =:id";
			Query query = em.createQuery(Text);
			query.setParameter("id", id);
			List<Study> Studies = query.getResultList();
			return Studies;
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
		
		// find Variables by Partition  
		@Override
		public List<Variable> findVariablesByPartition(Integer id){
			String Text = "SELECT v FROM Variable as v, t_partition as p , Variables_Partitions as vp "
					+ "WHERE p.id_partition = vp.partition.id_partition and v.id_variable = vp.variable.id_variable "
					+ "and p.id_partition =:id";

			Query query = em.createQuery(Text);
			query.setParameter("id", id);			    	
			List<Variable> variables = query.getResultList();
			return variables;
		}
	//duplicate study
	
	@Override
	public void duplicateStudy(Integer id) {

	    Study s = this.findStudyById(id);
	    List<Partition> partitions = this.findPartitionsByStudy(id);
	    String Text = "INSERT INTO study (number, type, id_project, id_team) VALUES(?, ?, ?, ?)";
	    Query query = em.createNativeQuery(Text);
		query.setParameter(1,s.getNumber());
		query.setParameter(2,s.getType());
		query.setParameter(3,s.getProject().getId_project());
		query.setParameter(4,s.getTeam().getId_team());
		query.executeUpdate();
		for(int i=0; i<partitions.size();i++){
			Partition p = partitions.get(i);
		    String Text1 = "INSERT INTO t_partition (description, name, id_study) VALUES(?, ?, ?)";
		    Query query1 = em.createNativeQuery(Text1);
			query1.setParameter(1,p.getDescription());
			query1.setParameter(2,p.getName());
			query1.setParameter(3,p.getStudy().getId_study()+1);
			query1.executeUpdate();
			List<Variable> variables = this.findVariablesByPartition(p.getId_partition());
			for(int j=0; j<variables.size();j++){
				Variable v = variables.get(j);
				if (variables.get(j) instanceof Constraint){
					String Text2 = "INSERT INTO variable (date, description, image, max, max_res, min, min_res, name, unit, visibility,DTYPE) VALUES(?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
				    Query query2 = em.createNativeQuery(Text2);
					query2.setParameter(1,v.getDate());
					query2.setParameter(2,v.getDescription());
					query2.setParameter(3,v.getImage());
					query2.setParameter(4,v.getMax());
					query2.setParameter(5,v.getMax_res());
					query2.setParameter(6,v.getMin());
					query2.setParameter(7,v.getMin_res());
					query2.setParameter(8,v.getName());
					query2.setParameter(9,v.getUnit());
					query2.setParameter(10,v.getVisibility());
					query2.setParameter(11,"Constraint");
					query2.executeUpdate();
				}
				if (variables.get(j) instanceof Objective){
					String Text2 = "INSERT INTO variable (date, description, image, max, max_res, min, min_res, name, unit, visibility,DTYPE) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
				    Query query2 = em.createNativeQuery(Text2);
					query2.setParameter(1,v.getDate());
					query2.setParameter(2,v.getDescription());
					query2.setParameter(3,v.getImage());
					query2.setParameter(4,v.getMax());
					query2.setParameter(5,v.getMax_res());
					query2.setParameter(6,v.getMin());
					query2.setParameter(7,v.getMin_res());
					query2.setParameter(8,v.getName());
					query2.setParameter(9,v.getUnit());
					query2.setParameter(10,v.getVisibility());
					query2.setParameter(11,"Objective");
					query2.executeUpdate();
				}
				if (variables.get(j) instanceof Variable){
					String Text2 = "INSERT INTO variable (date, description, image, max, max_res, min, min_res, name, unit, visibility,DTYPE) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
				    Query query2 = em.createNativeQuery(Text2);
					query2.setParameter(1,v.getDate());
					query2.setParameter(2,v.getDescription());
					query2.setParameter(3,v.getImage());
					query2.setParameter(4,v.getMax());
					query2.setParameter(5,v.getMax_res());
					query2.setParameter(6,v.getMin());
					query2.setParameter(7,v.getMin_res());
					query2.setParameter(8,v.getName());
					query2.setParameter(9,v.getUnit());
					query2.setParameter(10,v.getVisibility());
					query2.setParameter(11,"Variable");
					query2.executeUpdate();
				}
				
			}
		}

		

	    
	}
}
