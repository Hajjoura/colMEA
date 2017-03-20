package com.supmeca.colMEA.business;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.supmeca.colMEA.domain.Engineer;
import com.supmeca.colMEA.domain.Team;
import com.supmeca.colMEA.domain.Teams_Engineers;

/**
 * Session Bean implementation class TeamService
 */
@Stateless
@LocalBean
public class TeamService implements TeamServiceRemote, TeamServiceLocal {

	@PersistenceContext
	private EntityManager em;
	
	Team Team;
    /**
     * Default constructor. 
     */
    public TeamService() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void CreateTeam(Team Team) {
		
		em.persist(Team);
	}

	@Override
	public void EditTeam(Team Team) {

		em.merge(Team);
	}

	@Override
	public void removeTeam(Team Team) {
		
		em.remove(em.merge(Team));
		
	}

	@Override
	public Team findTeamById(int id) {
		Team Team=em.find(Team.class,id);
		if(Team!=null){
			return Team;
		}
		return null;
	}

	@Override
	public List<Team> findAllTeams() {
		String text = "SELECT t FROM Team t";
		Query query = em.createQuery(text);
		List<Team> ListTeams = query.getResultList();
		
		return ListTeams;
	}

	@Override
	public void DeleteTeam(int id) {
		em.remove(em.find(Team.class, id));		
	}
	
	//Add Team to enginner 
			@Override
			public Boolean addEngineerToTeam(Team team, Engineer engineer) {
				
				try{
					Teams_Engineers teamEng = new Teams_Engineers(team,engineer);
								
					em.persist(teamEng);
					return true;	
				}catch(Exception e){
					return false;
				}
			}
	




}
