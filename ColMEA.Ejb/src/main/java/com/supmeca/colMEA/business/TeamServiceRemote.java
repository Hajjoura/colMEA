package com.supmeca.colMEA.business;

import java.util.List;

import javax.ejb.Remote;

import com.supmeca.colMEA.domain.Engineer;
import com.supmeca.colMEA.domain.Team;
import com.supmeca.colMEA.domain.Teams_Engineers;

@Remote
public interface TeamServiceRemote {
	public void CreateTeam( Team Team) ;
	public void EditTeam (Team Team);
	public void removeTeam(Team Team);
	public Team findTeamById(int id);
	public List<Team> findAllTeams() ;
	public void DeleteTeam(int id);
	public Boolean addEngineerToTeam(Team team, Engineer engineer);
	public List<Teams_Engineers> findTeamsEngineers();
	public Team findTeamByCoordinator(Integer id);
	public Team findTeamByEngineer(Integer id) ;
	public List<Team> findTeamsByEngineer(Integer id);
	public void addTeamWithCoord(Team t, int id_coor);

}
