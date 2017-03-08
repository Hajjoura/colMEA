package com.supmeca.colMEA.business;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;

import com.supmeca.colMEA.domain.Objective;

@Local
public interface ObjectiveServiceLocal {
	
	public void CreateObjective( Objective Objective) ;
	public void EditObjective (Objective Objective);
	public void removeObjective(Objective Objective);
	public Objective findObjectiveById(int id);
	public List<Objective> findAllObjectives() ;

}
