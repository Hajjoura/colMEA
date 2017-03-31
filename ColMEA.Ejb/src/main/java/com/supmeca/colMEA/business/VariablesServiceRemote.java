package com.supmeca.colMEA.business;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import com.supmeca.colMEA.domain.Partition;
import com.supmeca.colMEA.domain.Set;
import com.supmeca.colMEA.domain.Variable;

@Remote
public interface VariablesServiceRemote {
	
	public void CreateVariable( Variable Variable) ;
	public void EditVariable (Variable Variable);
	public void removeVariable(Variable Variable);
	public Variable findVariableById(int id);
	public List<Variable> findAllVariables();
	public void DeleteVariable(int id);
	public Boolean addVariableToPartition(Partition partition, Variable variable, Date date, float min, float max);
	public Variable findVariableByName(String name);
	public Variable findVariableByMinMax(float min, float max);
	public Variable findVariableByMinMaxRes(float minRes, float maxRes);
	public List<Variable> findVariablesByMinMaxRes(float minRes, float maxRes);
	public List<Variable> findVariablesByMinMax(float min, float max);
	public Variable findVariableByPartition(Integer id);
	public List<Variable> findVariablesByPartition(Integer id);
	public Variable findVariableByStudy(Integer id);
	public List<Variable> findVariablesByStudy(Integer id);
	public Variable findVariableByProject(Integer id);
	public List<Variable> findVariablesByProject(Integer id);
	public Variable findVariableByTeam(Integer id);
	public List<Variable> findVariablesByTeam(Integer id);
	public Variable findVariableByEngineer(Integer id);
	public List<Variable> findVariablesByEngineer(Integer id);
	public Variable findVariableByCoordinator(Integer id);
	public List<Variable> findVariablesByCoordinator(Integer id);
	public Variable findVariableByManager(Integer id);
	public List<Variable> findVariablesByManager(Integer id);
	public List<Variable> findVariableByVisibility(Boolean visibility);
	public List<Variable> findSharedVariablesByManager(Integer id);
	public List<Variable> findSharedVariablesByCoordinator(Integer id);
	public List<Variable> findSharedVariablesByEngineer(Integer id);
	public List<Variable> findLocalVariablesByManager(Integer id);
	public List<Variable> findLocalVariablesByCoordinator(Integer id);
	public List<Variable> findLocalVariablesByEngineer(Integer id);
	public List<Set>findVariablewithSet(Integer id);
	
}
