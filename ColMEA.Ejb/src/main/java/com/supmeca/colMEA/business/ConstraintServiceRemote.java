package com.supmeca.colMEA.business;

import java.util.List;

import javax.ejb.Remote;

import com.supmeca.colMEA.domain.Constraint;

@Remote
public interface ConstraintServiceRemote {
	public void CreateConstraint( Constraint Constraint) ;
	public void EditConstraint (Constraint Constraint);
	public void removeConstraint(Constraint Constraint);
	public Constraint findConstraintById(int id);
	public List<Constraint> findAllConstraints() ;
	public void DeleteConstraint(int id);

}
