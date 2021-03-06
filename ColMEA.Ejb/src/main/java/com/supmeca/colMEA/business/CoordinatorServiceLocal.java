package com.supmeca.colMEA.business;

import java.util.List;

import javax.ejb.Local;

import com.supmeca.colMEA.domain.Coordinator;
import com.supmeca.colMEA.domain.User;

@Local
public interface CoordinatorServiceLocal {
	public void CreateCoordinator( Coordinator Coordinator) ;
	public void EditCoordinator (Coordinator Coordinator);
	public void removeCoordinator(Coordinator Coordinator);
	public Coordinator findCoordinatorById(int id);
	public List<Coordinator> findAllCoordinators() ;
	public void DeleteCoordinator(int id);
	public Coordinator findCoordinatorByName(String first_Name, String last_Name);
	public Coordinator findCoordinatorByEmail(String email);
	public Coordinator findCoordinatorByTeam(Integer id);
	public String findCoordinatorNameByTeam(Integer id);
}
