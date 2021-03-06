package com.supmeca.colMEA.business;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.supmeca.colMEA.domain.Coordinator;
import com.supmeca.colMEA.domain.Interval;
import com.supmeca.colMEA.domain.Manager;
import com.supmeca.colMEA.domain.Partition;
import com.supmeca.colMEA.domain.Set;
import com.supmeca.colMEA.domain.User;
import com.supmeca.colMEA.domain.Variable;
import com.supmeca.colMEA.domain.Variables_Partitions;
import com.supmeca.colMEA.domain.Variables_PartitionsFK;

/**
 * Session Bean implementation class VariablesService
 */
@Stateless
@LocalBean
public class VariablesService implements VariablesServiceRemote, VariablesServiceLocal {

	@PersistenceContext
	private EntityManager em;

	Variable Variable;

	SetServiceLocal setEjb;
	PartitionServiceLocal PartitionEjb;
	/**
	 * Default constructor. 
	 */
	public VariablesService() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void CreateVariable(Variable Variable) {
		em.persist(Variable);
	}

	@Override
	public void EditVariable(Variable Variable) {
		em.merge(Variable);
	}

	@Override
	public void removeVariable(Variable Variable) {
		em.remove(em.merge(Variable));

	}

	@Override
	public Variable findVariableById(int id) {
		Variable Variable=em.find(Variable.class,id);
		if(Variable!=null){
			return Variable;
		}
		return null;
	}

	@Override
	public List<Variable> findAllVariables() {
		String text = "SELECT v FROM Variable v";
		Query query = em.createQuery(text);
		List<Variable> ListVariables = query.getResultList();

		return ListVariables;
	}

	@Override
	public void DeleteVariable(int id) {
		em.remove(em.find(Variable.class, id));	

	}

	//Add variable to partition
	@Override
	public Boolean addVariableToPartition(Partition partition, Variable variable,Set set, Date date, Float min, Float max) {

		try{
			Variables_Partitions varpart = new Variables_Partitions(em.merge(variable),em.merge(partition), em.merge(set), date, min, max);

			em.persist(varpart);
			return true;	
		}catch(Exception e){
			return false;
		}
	}

	// find Variable by name 
	@Override
	public Variable findVariableByName(String name){
		String Text = "SELECT v FROM Variable v WHERE v.name =:name";
		Query query = em.createQuery(Text);
		query.setParameter("name", name);
		query.setFirstResult(0);
		query.setMaxResults(1);
		Variable Variable = (Variable)query.getSingleResult();
		return Variable;
	}
	// find Variable by min and max  

	@Override
	public Variable findVariableByMinMax(float min, float max){
		String Text = "SELECT v FROM Variable v WHERE v.min =:min and v.max =:max";
		Query query = em.createQuery(Text);
		query.setFirstResult(0);
		query.setMaxResults(1);
		query.setParameter("min", min);
		query.setParameter("max", max);

		Variable Variable = (Variable)query.getSingleResult();
		return Variable;
	}
	// find Variables by min and max  

	@Override
	public List<Variable> findVariablesByMinMax(float min, float max){
		String Text = "SELECT v FROM Variable v WHERE v.min =:min and v.max =:max";
		Query query = em.createQuery(Text);
		query.setParameter("min", min);
		query.setParameter("max", max);

		List<Variable> Variables = query.getResultList();
		return Variables;
	}
	//find local variables
	@Override
	public List<Variable> findLocalVariables(){
		String Text = "SELECT v FROM Variable v WHERE v.visibility = 0";
		Query query = em.createQuery(Text);
		
		List<Variable> Variables = query.getResultList();
		return Variables;
	}
	
	//find Shared variables
	@Override
	public List<Variable> findSharedVariables(){
		String Text = "SELECT v FROM Variable v WHERE v.visibility = 1";
		Query query = em.createQuery(Text);
		
		List<Variable> Variables = query.getResultList();
		return Variables;
	}
	// find Variable by minRes and maxRes  

	@Override
	public Variable findVariableByMinMaxRes(float minRes, float maxRes){
		String Text = "SELECT v FROM Variable v WHERE v.min_res =:minRes and v.max_res =:maxRes";
		Query query = em.createQuery(Text);
		query.setFirstResult(0);
		query.setMaxResults(1);
		query.setParameter("minRes", minRes);
		query.setParameter("maxRes", maxRes);

		Variable Variable = (Variable)query.getSingleResult();
		return Variable;
	}
	// find Variable by minRes and maxRes  
	@Override
	public List<Variable> findVariablesByMinMaxRes(float minRes, float maxRes){
		String Text = "SELECT v FROM Variable v WHERE v.min_res =:minRes and v.max_res =:maxRes";
		Query query = em.createQuery(Text);
		query.setParameter("minRes", minRes);
		query.setParameter("maxRes", maxRes);
		List<Variable> Variables = query.getResultList();
		return Variables;
	}

	// find  Variable By Partition
	@Override
	public Variable findVariableByPartition(Integer id){
		String Text = "SELECT v FROM Variable as v, t_partition as p , Variables_Partitions as vp "
				+ "WHERE p.id_partition = vp.partition.id_partition and v.id_variable = vp.variable.id_variable "
				+ "and p.id_partition =:id";

		Query query = em.createQuery(Text);
		query.setParameter("id", id);
		query.setFirstResult(0);
		query.setMaxResults(1);

		Variable variable = (Variable)query.getSingleResult();
		return variable;
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
	// find  Variable By Study
	@Override
	public Variable findVariableByStudy(Integer id){
		String Text = "SELECT v FROM Variable as v, t_partition as p , Study as s, Variables_Partitions as vp "
				+ "WHERE p.id_partition = vp.partition.id_partition and v.id_variable = vp.variable.id_variable "
				+ "and p.study.id_study = s.id_study and  s.id_study =:id";

		Query query = em.createQuery(Text);
		query.setParameter("id", id);
		query.setFirstResult(0);
		query.setMaxResults(1);

		Variable variable = (Variable)query.getSingleResult();
		return variable;
	}

	// find Variables by Study  
	@Override
	public List<Variable> findVariablesByStudy(Integer id){
		String Text = "SELECT v FROM Variable as v, t_partition as p , Study as s, Variables_Partitions as vp "
				+ "WHERE p.id_partition = vp.partition.id_partition and v.id_variable = vp.variable.id_variable "
				+ "and p.study.id_study = s.id_study and  s.id_study =:id";

		Query query = em.createQuery(Text);
		query.setParameter("id", id);			    	
		List<Variable> variables = query.getResultList();
		return variables;
	}

	// find  Variable By Project
	@Override
	public Variable findVariableByProject(Integer id){
		String Text = "SELECT v FROM Variable as v, t_partition as p , Study as s, Project as pr, Variables_Partitions as vp "
				+ "WHERE p.id_partition = vp.partition.id_partition and v.id_variable = vp.variable.id_variable "
				+ "and p.study.id_study = s.id_study and s.project.id_project = pr.id_project and  pr.id_project =:id";

		Query query = em.createQuery(Text);
		query.setParameter("id", id);
		query.setFirstResult(0);
		query.setMaxResults(1);

		Variable variable = (Variable)query.getSingleResult();
		return variable;
	}

	// find Variables by Project  
	@Override
	public List<Variable> findVariablesByProject(Integer id){
		String Text = "SELECT v FROM Variable as v, t_partition as p , Study as s, Project as pr, Variables_Partitions as vp "
				+ "WHERE p.id_partition = vp.partition.id_partition and v.id_variable = vp.variable.id_variable "
				+ "and p.study.id_study = s.id_study and s.project.id_project = pr.id_project and  pr.id_project =:id";

		Query query = em.createQuery(Text);
		query.setParameter("id", id);			    	
		List<Variable> variables = query.getResultList();
		return variables;
	}

	// find  Variable By Team
	@Override
	public Variable findVariableByTeam(Integer id){
		String Text = "SELECT v FROM Variable as v, t_partition as p , Study as s, Team as t, Variables_Partitions as vp "
				+ "WHERE p.id_partition = vp.partition.id_partition and v.id_variable = vp.variable.id_variable "
				+ "and p.study.id_study = s.id_study and s.team.id_team = t.id_team and  t.id_team =:id";

		Query query = em.createQuery(Text);
		query.setParameter("id", id);
		query.setFirstResult(0);
		query.setMaxResults(1);

		Variable variable = (Variable)query.getSingleResult();
		return variable;
	}

	// find Variables by Team  
	@Override
	public List<Variable> findVariablesByTeam(Integer id){
		String Text = "SELECT v FROM Variable as v, t_partition as p , Study as s, Team as t, Variables_Partitions as vp "
				+ "WHERE p.id_partition = vp.partition.id_partition and v.id_variable = vp.variable.id_variable "
				+ "and p.study.id_study = s.id_study and s.team.id_team = t.id_team and  t.id_team =:id";

		Query query = em.createQuery(Text);
		query.setParameter("id", id);			    	
		List<Variable> variables = query.getResultList();
		return variables;
	}
	// find  Variable By Engineer
	@Override
	public Variable findVariableByEngineer(Integer id){
		String Text = "SELECT v FROM Variable as v, t_partition as p , Study as s, Team as t, Variables_Partitions as vp, Teams_Engineers te, Engineer e  "
				+ "WHERE p.id_partition = vp.partition.id_partition and v.id_variable = vp.variable.id_variable "
				+ "and p.study.id_study = s.id_study and s.team.id_team = t.id_team and te.team.id_team = t.id_team "
				+ "and te.engineer.id_user = e.id_user and  e.id_user =:id";

		Query query = em.createQuery(Text);
		query.setParameter("id", id);
		query.setFirstResult(0);
		query.setMaxResults(1);

		Variable variable = (Variable)query.getSingleResult();
		return variable;
	}

	// find Variables by Engineer  
	@Override
	public List<Variable> findVariablesByEngineer(Integer id){
		String Text = "SELECT DISTINCT v FROM Variable as v, t_partition as p , Study as s, Team as t, Variables_Partitions as vp, Teams_Engineers te, Engineer e  "
				+ "WHERE p.id_partition = vp.partition.id_partition and v.id_variable = vp.variable.id_variable "
				+ "and p.study.id_study = s.id_study and s.team.id_team = t.id_team and te.team.id_team = t.id_team "
				+ "and te.engineer.id_user = e.id_user and  e.id_user =:id";

		Query query = em.createQuery(Text);
		query.setParameter("id", id);			    	
		List<Variable> variables = query.getResultList();
		return variables;
	}

	// find  Variable By Coordinator
	@Override
	public Variable findVariableByCoordinator(Integer id){
		String Text = "SELECT v FROM Variable as v, t_partition as p , Study as s, Team as t, Variables_Partitions as vp, Coordinator c "
				+ "WHERE p.id_partition = vp.partition.id_partition and v.id_variable = vp.variable.id_variable "
				+ "and p.study.id_study = s.id_study and s.team.id_team = t.id_team "
				+ "and t.coordinator.id_user = c.id_user and c.id_user =:id";

		Query query = em.createQuery(Text);
		query.setParameter("id", id);
		query.setFirstResult(0);
		query.setMaxResults(1);

		Variable variable = (Variable)query.getSingleResult();
		return variable;
	}

	// find Variables by Coordinator  
	@Override
	public List<Variable> findVariablesByCoordinator(Integer id){
		String Text = "SELECT v FROM Variable as v, t_partition as p , Study as s, Team as t, Variables_Partitions as vp, Coordinator c "
				+ "WHERE p.id_partition = vp.partition.id_partition and v.id_variable = vp.variable.id_variable "
				+ "and p.study.id_study = s.id_study and s.team.id_team = t.id_team "
				+ "and t.coordinator.id_user = c.id_user and c.id_user =:id";

		Query query = em.createQuery(Text);
		query.setParameter("id", id);			    	
		List<Variable> variables = query.getResultList();
		return variables;
	}

	// find  Variable By Manager
	@Override
	public Variable findVariableByManager(Integer id){
		String Text = "SELECT v FROM Variable as v, t_partition as p , Study as s, Project as pr, Variables_Partitions as vp, Manager m "
				+ "WHERE p.id_partition = vp.partition.id_partition and v.id_variable = vp.variable.id_variable "
				+ "and p.study.id_study = s.id_study and s.project.id_project = pr.id_project "
				+ "and pr.manager.id_user = m.id_user and m.id_user =:id";

		Query query = em.createQuery(Text);
		query.setParameter("id", id);
		query.setFirstResult(0);
		query.setMaxResults(1);

		Variable variable = (Variable)query.getSingleResult();
		return variable;
	}

	// find Variables by Manager  
	@Override
	public List<Variable> findVariablesByManager(Integer id){
		String Text = "SELECT v FROM Variable as v, t_partition as p , Study as s, Project as pr, Variables_Partitions as vp, Manager m "
				+ "WHERE p.id_partition = vp.partition.id_partition and v.id_variable = vp.variable.id_variable "
				+ "and p.study.id_study = s.id_study and s.project.id_project = pr.id_project "
				+ "and pr.manager.id_user = m.id_user and m.id_user =:id";

		Query query = em.createQuery(Text);
		query.setParameter("id", id);			    	
		List<Variable> variables = query.getResultList();
		return variables;
	}

	// find Variable by Visibility 
	@Override
	public List<Variable> findVariableByVisibility(Boolean visibility){
		String Text = "SELECT v FROM Variable v WHERE v.visibility =:visibility";
		Query query = em.createQuery(Text);
		query.setParameter("visibility", visibility);
		List<Variable> Variables = query.getResultList();
		return Variables;
	}
	// find Shared Variables by Manager  
	@Override
	public List<Variable> findSharedVariablesByManager(Integer id){
		String Text = "SELECT v FROM Variable as v, t_partition as p , Study as s, Project as pr, Variables_Partitions as vp, Manager m "
				+ "WHERE p.id_partition = vp.partition.id_partition and v.id_variable = vp.variable.id_variable "
				+ "and p.study.id_study = s.id_study and s.project.id_project = pr.id_project "
				+ "and pr.manager.id_user = m.id_user and m.id_user =:id and v.visibility = 1";

		Query query = em.createQuery(Text);
		query.setParameter("id", id);			    	
		List<Variable> variables = query.getResultList();
		return variables;
	}

	// find Shared Variables by Coordinator  
	@Override
	public List<Variable> findSharedVariablesByCoordinator(Integer id){
		String Text = "SELECT v FROM Variable as v, t_partition as p , Study as s, Team as t, Variables_Partitions as vp, Coordinator c "
				+ "WHERE p.id_partition = vp.partition.id_partition and v.id_variable = vp.variable.id_variable "
				+ "and p.study.id_study = s.id_study and s.team.id_team = t.id_team "
				+ "and t.coordinator.id_user = c.id_user and c.id_user =:id and v.visibility = 1";

		Query query = em.createQuery(Text);
		query.setParameter("id", id);			    	
		List<Variable> variables = query.getResultList();
		return variables;
	}


	// find Shared Variables by Engineer  
	@Override
	public List<Variable> findSharedVariablesByEngineer(Integer id){
		String Text = "SELECT v FROM Variable as v, t_partition as p , Study as s, Team as t, Variables_Partitions as vp, Teams_Engineers te, Engineer e  "
				+ "WHERE p.id_partition = vp.partition.id_partition and v.id_variable = vp.variable.id_variable "
				+ "and p.study.id_study = s.id_study and s.team.id_team = t.id_team and te.team.id_team = t.id_team "
				+ "and te.engineer.id_user = e.id_user and  e.id_user =:id and v.visibility = 1";

		Query query = em.createQuery(Text);
		query.setParameter("id", id);			    	
		List<Variable> variables = query.getResultList();
		return variables;
	}

	// find Local Variables by Manager  
	@Override
	public List<Variable> findLocalVariablesByManager(Integer id){
		String Text = "SELECT v FROM Variable as v, t_partition as p , Study as s, Project as pr, Variables_Partitions as vp, Manager m "
				+ "WHERE p.id_partition = vp.partition.id_partition and v.id_variable = vp.variable.id_variable "
				+ "and p.study.id_study = s.id_study and s.project.id_project = pr.id_project "
				+ "and pr.manager.id_user = m.id_user and m.id_user =:id and v.visibility = 0";

		Query query = em.createQuery(Text);
		query.setParameter("id", id);			    	
		List<Variable> variables = query.getResultList();
		return variables;
	}

	// find Local Variables by Coordinator  
	@Override
	public List<Variable> findLocalVariablesByCoordinator(Integer id){
		String Text = "SELECT v FROM Variable as v, t_partition as p , Study as s, Team as t, Variables_Partitions as vp, Coordinator c "
				+ "WHERE p.id_partition = vp.partition.id_partition and v.id_variable = vp.variable.id_variable "
				+ "and p.study.id_study = s.id_study and s.team.id_team = t.id_team "
				+ "and t.coordinator.id_user = c.id_user and c.id_user =:id and v.visibility = 0 "
				+ "and v NOT IN (SELECT c FROM Constraint as c) and v NOT IN (SELECT c FROM Objective as c)";

		Query query = em.createQuery(Text);
		query.setParameter("id", id);			    	
		List<Variable> variables = query.getResultList();
		return variables;
	}
	


	// find Local Variables by Engineer  
	@Override
	public List<Variable> findLocalVariablesByEngineer(Integer id){
		String Text = "SELECT v FROM Variable as v, t_partition as p , Study as s, Team as t, Variables_Partitions as vp, Teams_Engineers te, Engineer e  "
				+ "WHERE p.id_partition = vp.partition.id_partition and v.id_variable = vp.variable.id_variable "
				+ "and p.study.id_study = s.id_study and s.team.id_team = t.id_team and te.team.id_team = t.id_team "
				+ "and te.engineer.id_user = e.id_user and  e.id_user =:id and v.visibility = 0";

		Query query = em.createQuery(Text);
		query.setParameter("id", id);			    	
		List<Variable> variables = query.getResultList();
		return variables;
	}

	//Find variable with sets 

	@Override
	public List<Set> findVariablewithSet(Integer id){
		String Text = "SELECT s FROM Variable as v, t_set as s  "
				+ "WHERE s.variable.id_variable =v.id_variable and v.id_variable =:id ";

		Query query = em.createQuery(Text);
		query.setParameter("id", id);			    	

		List<Set> set = query.getResultList();
		return set;
	}

	@Override
	public List<Number> findSetsByVariable(int id) {
		String text = "SELECT DISTINCT s.value FROM t_set as s , Variable as v , Variables_Partitions as vp "
				+ "WHERE v.id_variable = s.variable.id_variable and v.id_variable = vp.variable.id_variable and v.id_variable =:id";
		Query query = em.createQuery(text);
		query.setParameter("id", id);			    	

		List<Number> ListSets = query.getResultList();

		return ListSets;
	}
	@Override
	public HashMap<String, List<Number>> findVariablewithSets(Integer id) {
		HashMap<String, List<Number>> result = new HashMap<String, List<Number>>();
		Variable variable = this.findVariableById(id);

		List<Number> sets = this.findSetsByVariable(id);
		result.put(variable.getName(), sets); 
		return result;
	}
	@Override
	public List<Number> findSetsByVariableandPartition(int id_vable, int id_part) {
		String text = "SELECT  DISTINCT s.value "
				+ "FROM t_set as s , Variable as v , Variables_Partitions as vp , t_partition as p "
				+ "WHERE  vp.variable.id_variable = v.id_variable and s.id_set = vp.test.id_set  "
				+ "and vp.partition.id_partition = p.id_partition and p.id_partition =:id_part and v.id_variable =:id_vable";
		
		String text1 = "SELECT  DISTINCT s.value "
				+ "FROM t_set as s , Variable as v , Variables_Partitions as vp  , t_partition as p "
				+ "WHERE p.id_partition = vp.partition.id_partition and p.id_partition =:id_part and "
				+ "s.id_set IN (SELECT s.id_set FROM t_set as s , Variable as v , Variables_Partitions as vp "
				+ "WHERE v.id_variable = s.variable.id_variable and v.id_variable = vp.variable.id_variable and v.id_variable =:id_vable) ";
		
		Query query = em.createQuery(text);
		query.setParameter("id_vable", id_vable);		
		query.setParameter("id_part", id_part);

		List<Number> ListSets = query.getResultList();

		return ListSets;
	}
	
	@Override
	public HashMap<String, List<Number>> findVariablewithSetsbyPartition(Integer id_var, Integer id_part) {
		HashMap<String, List<Number>> result = new HashMap<String, List<Number>>();
		Variable variable = this.findVariableById(id_var);
	
		List<Number> sets = this.findSetsByVariableandPartition(id_var , id_part);
		result.put(variable.getName(), sets); 
		return result;
	}
	
	@Override
	public List<Interval> findIntervalsByVariable(Integer id){
		String text = "SELECT t FROM t_interval as t , t_set as s, Variable as v"
				+ " WHERE v.id_variable = s.variable.id_variable and t.sets.id_set = s.id_set "
				+ "and v.id_variable =:id ";
		Query query = em.createQuery(text);
		query.setParameter("id", id);
		List<Interval> ListIntervals = query.getResultList();
		return ListIntervals;
	}
	@Override
	public HashMap<String, List<Interval>> findVariablewithIntervals(Integer id) {
		HashMap<String, List<Interval>> result = new HashMap<String, List<Interval>>();
		Variable variable = this.findVariableById(id);

		List<Interval> sets = this.findIntervalsByVariable(id);
		result.put(variable.getName(), sets); 
		return result;
	}
	@Override
	public List<Interval> findIntervalByVableandpart(Integer id_vable, Integer id_part){
		String text = "SELECT t FROM t_interval as t , t_set as s, Variable as v, Variables_Partitions as vp  , t_partition as p"
				+ " WHERE v.id_variable = s.variable.id_variable and t.sets.id_set = s.id_set and v.id_variable = vp.variable.id_variable "
				+ "and vp.partition.id_partition = p.id_partition and s.value= null and p.id_partition =:id_part and v.id_variable =:id_vable";
		Query query = em.createQuery(text);
		query.setParameter("id_vable", id_vable);		
		query.setParameter("id_part", id_part);
		List<Interval> ListIntervals = query.getResultList();
		return ListIntervals;
	}
	@Override
	public HashMap<String, List<Interval>> findIntervalsByVableandpart(Integer id_vable, Integer id_part) {
		HashMap<String, List<Interval>> result = new HashMap<String, List<Interval>>();
		Variable variable = this.findVariableById(id_vable);

		List<Interval> sets = this.findIntervalByVableandpart(id_vable, id_part);
		result.put(variable.getName(), sets); 
		return result;
	}
	
	@Override
	public Variable getLastRow() {
		String text = "SELECT v FROM  Variable as v ORDER BY v.id_variable DESC";

		Query query = em.createQuery(text);
		query.setFirstResult(0);
		query.setMaxResults(1);
		Variable Set = (Variable)query.getSingleResult();

		return Set;
	}

}
