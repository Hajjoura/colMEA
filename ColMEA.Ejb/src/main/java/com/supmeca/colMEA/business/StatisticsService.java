package com.supmeca.colMEA.business;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Session Bean implementation class StatisticsService
 */
@Stateless
@LocalBean
public class StatisticsService implements StatisticsServiceRemote, StatisticsServiceLocal {

	@PersistenceContext
	private EntityManager em;
	
	
    public StatisticsService() {


    	// TODO Auto-generated constructor stub
    }


	@Override
	public Integer NumberPartitionsByStudy(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Integer NumberVariablesByProject(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Integer NumberVariablesByPartition(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Integer NumberVariablesByStudy(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Integer NumberConstraintsByPartition(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Integer NumberObjectivesByPartition(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Integer NumberStudiesByProject(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Integer NumberEngineersByProject(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Integer NumberEngineersByTeam(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Integer NumberManagersByProject(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
