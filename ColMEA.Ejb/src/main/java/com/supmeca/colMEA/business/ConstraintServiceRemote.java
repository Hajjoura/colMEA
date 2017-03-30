package com.supmeca.colMEA.business;

import java.util.List;

import javax.ejb.Remote;

import com.supmeca.colMEA.domain.Constraint;
import com.supmeca.colMEA.domain.Set;

@Remote
public interface ConstraintServiceRemote {
	public void CreateConstraint( Constraint Constraint) ;
	public void EditConstraint (Constraint Constraint);
	public void removeConstraint(Constraint Constraint);
	public Constraint findConstraintById(int id);
	public List<Constraint> findAllConstraints() ;
	public void DeleteConstraint(int id);
	public Constraint findConstraintByName(String name);
	public Constraint findConstraintByMinMax(float min, float max);
	public Constraint findConstraintByMinMaxRes(float minRes, float maxRes);
	public List<Constraint> findConstraintsByMinMaxRes(float minRes, float maxRes);
	public List<Constraint> findConstraintsByMinMax(float min, float max);
	public Constraint findConstraintByPartition(Integer id);
	public List<Constraint> findConstraintsByPartition(Integer id);
	public Constraint findConstraintByStudy(Integer id);
	public List<Constraint> findConstraintsByStudy(Integer id);
	public Constraint findConstraintByProject(Integer id);
	public List<Constraint> findConstraintsByProject(Integer id);
	public Constraint findConstraintByTeam(Integer id);
	public List<Constraint> findConstraintsByTeam(Integer id);
	public Constraint findConstraintByEngineer(Integer id);
	public List<Constraint> findConstraintsByEngineer(Integer id);
	public Constraint findConstraintByCoordinator(Integer id);
	public List<Constraint> findConstraintsByCoordinator(Integer id);
	public Constraint findConstraintByManager(Integer id);
	public List<Constraint> findConstraintsByManager(Integer id);
	public List<Constraint> findConstraintByVisibility(Boolean visibility);
	public List<Constraint> findSharedConstraintsByManager(Integer id);
	public List<Constraint> findSharedConstraintsByCoordinator(Integer id);
	public List<Constraint> findSharedConstraintsByEngineer(Integer id);
	public List<Constraint> findLocalConstraintsByManager(Integer id);
	public List<Constraint> findLocalConstraintsByCoordinator(Integer id);
	public List<Constraint> findLocalConstraintsByEngineer(Integer id);
	public List<Set> findConstraintwithSet(Integer id);

}
