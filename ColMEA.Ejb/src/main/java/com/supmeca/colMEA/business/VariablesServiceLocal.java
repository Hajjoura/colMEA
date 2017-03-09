package com.supmeca.colMEA.business;

import java.util.List;

import javax.ejb.Local;

import com.supmeca.colMEA.domain.Variable;

@Local
public interface VariablesServiceLocal {
	public void CreateVariable( Variable Variable) ;
	public void EditVariable (Variable Variable);
	public void removeVariable(Variable Variable);
	public Variable findVariableById(int id);
	public List<Variable> findAllVariables() ;
	public void DeleteVariable(int id);
}
