package com.supmeca.colMEA.business;

import javax.ejb.Local;

@Local
public interface StatisticsServiceLocal {

	public Integer NumberPartitionsByStudy(Integer id);
	public Integer NumberVariablesByProject(Integer id);
	public Integer NumberVariablesByPartition(Integer id);
	public Integer NumberVariablesByStudy(Integer id);
	public Integer NumberConstraintsByPartition(Integer id);
	public Integer NumberObjectivesByPartition(Integer id);
	public Integer NumberStudiesByProject(Integer id);
	public Integer NumberEngineersByProject(Integer id);
	public Integer NumberEngineersByTeam(Integer id);
	public Integer NumberProjectsByManager(Integer id);
	public Integer NumberTeamsByCoordinator(Integer id);
	public Integer NumberTeamsByEnginner(Integer id);
	public Integer NumberPartitions();
	public Integer NumberVariables();
	public Integer NumberStudies() ;
	public Integer NumberEngineers() ;
	public Integer NumberTeams();
	public Integer NumberProjects();
	
	

}
