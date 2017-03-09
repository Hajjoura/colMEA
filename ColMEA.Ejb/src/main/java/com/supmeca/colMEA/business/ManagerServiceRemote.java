package com.supmeca.colMEA.business;

import java.util.List;

import javax.ejb.Remote;

import com.supmeca.colMEA.domain.Manager;

@Remote
public interface ManagerServiceRemote {

	public void CreateManager( Manager Manager) ;
	public void EditManager (Manager Manager);
	public void removeManager(Manager Manager);
	public Manager findManagerById(int id);
	public List<Manager> findAllManagers() ;
	public void DeleteManager(int id);

}
