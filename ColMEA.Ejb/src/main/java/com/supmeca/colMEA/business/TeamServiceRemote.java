package com.supmeca.colMEA.business;

import java.util.List;

import javax.ejb.Remote;

import com.supmeca.colMEA.domain.Engineer;
import com.supmeca.colMEA.domain.Team;

@Remote
public interface TeamServiceRemote {
	public void CreateTeam( Team Team) ;
	public void EditTeam (Team Team);
	public void removeTeam(Team Team);
	public Team findTeamById(int id);
	public List<Team> findAllTeams() ;
	public void DeleteTeam(int id);
	public Boolean addEngineerToTeam(Team team, Engineer engineer);

}
