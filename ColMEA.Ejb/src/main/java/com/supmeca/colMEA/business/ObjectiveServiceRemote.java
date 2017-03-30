package com.supmeca.colMEA.business;

import java.util.List;

import javax.ejb.Remote;

import com.supmeca.colMEA.domain.Objective;
import com.supmeca.colMEA.domain.Objective;
import com.supmeca.colMEA.domain.Set;

@Remote
public interface ObjectiveServiceRemote {
	
	public void CreateObjective( Objective Objective) ;
	public void EditObjective (Objective Objective);
	public void removeObjective(Objective Objective);
	public Objective findObjectiveById(int id);
	public List<Objective> findAllObjectives() ;
	public void DeleteObjective(int id);
	public Objective findObjectiveByName(String name);
	public Objective findObjectiveByMinMax(float min, float max);
	public Objective findObjectiveByMinMaxRes(float minRes, float maxRes);
	public List<Objective> findObjectivesByMinMaxRes(float minRes, float maxRes);
	public List<Objective> findObjectivesByMinMax(float min, float max);
	public Objective findObjectiveByPartition(Integer id);
	public List<Objective> findObjectivesByPartition(Integer id);
	public Objective findObjectiveByStudy(Integer id);
	public List<Objective> findObjectivesByStudy(Integer id);
	public Objective findObjectiveByProject(Integer id);
	public List<Objective> findObjectivesByProject(Integer id);
	public Objective findObjectiveByTeam(Integer id);
	public List<Objective> findObjectivesByTeam(Integer id);
	public Objective findObjectiveByEngineer(Integer id);
	public List<Objective> findObjectivesByEngineer(Integer id);
	public Objective findObjectiveByCoordinator(Integer id);
	public List<Objective> findObjectivesByCoordinator(Integer id);
	public Objective findObjectiveByManager(Integer id);
	public List<Objective> findObjectivesByManager(Integer id);
	public List<Objective> findObjectiveByVisibility(Boolean visibility);
	public List<Objective> findSharedObjectivesByManager(Integer id);
	public List<Objective> findSharedObjectivesByCoordinator(Integer id);
	public List<Objective> findSharedObjectivesByEngineer(Integer id);
	public List<Objective> findLocalObjectivesByManager(Integer id);
	public List<Objective> findLocalObjectivesByCoordinator(Integer id);
	public List<Objective> findLocalObjectivesByEngineer(Integer id);
	public List<Set> findObjectivewithSet(Integer id);
}
