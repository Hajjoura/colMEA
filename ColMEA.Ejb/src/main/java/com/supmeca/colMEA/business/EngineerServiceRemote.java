package com.supmeca.colMEA.business;

import java.util.List;

import javax.ejb.Remote;

import com.supmeca.colMEA.domain.Engineer;
import com.supmeca.colMEA.domain.Team;

@Remote
public interface EngineerServiceRemote {
	
	public void CreateEngineer( Engineer Engineer) ;
	public void EditEngineer (Engineer Engineer);
	public void removeEngineer(Engineer Engineer);
	public Engineer findEngineerById(int id);
	public List<Engineer> findAllEngineers() ;
	public void DeleteEngineer(int id);
	public Boolean addEngineerToTeam(Team team, Engineer engineer);
	public Engineer findEngineerByTeam(Integer id);
	public List<Engineer> findEngineersByTeam(Integer id);
	public List<Engineer> findEngineersByNameTeam(String name);
	public List<Engineer> findEngineersByDomain(String name);
	public List<Engineer> findEngineersByProject(String name);
	public List<Engineer> findEngineersByCoordinator(Integer id);
	public List<Engineer> findEngineersByCoordinatorName(String login) ;


}
