package com.supmeca.colMEA.business;

import java.util.List;

import javax.ejb.Local;

import com.supmeca.colMEA.domain.Engineer;
import com.supmeca.colMEA.domain.Team;

@Local
public interface EngineerServiceLocal {
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

}
