package com.supmeca.colMEA.business;

import java.util.List;

import javax.ejb.Remote;

import com.supmeca.colMEA.domain.Variable;

@Remote
public interface VariablesServiceRemote {
	
	public void CreateVariable( Variable Variable) ;
	public void EditVariable (Variable Variable);
	public void removeVariable(Variable Variable);
	public Variable findVariableById(int id);
	public List<Variable> findAllVariables() ;
}
