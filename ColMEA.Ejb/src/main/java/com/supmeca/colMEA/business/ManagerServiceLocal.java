package com.supmeca.colMEA.business;

import java.util.List;

import javax.ejb.Local;

import com.supmeca.colMEA.domain.Manager;
import com.supmeca.colMEA.domain.User;

@Local
public interface ManagerServiceLocal {

	public void CreateManager( Manager Manager) ;
	public void EditManager (Manager Manager);
	public void removeManager(Manager Manager);
	public Manager findManagerById(int id);
	public List<Manager> findAllManagers() ;
	public void DeleteManager(int id);
	public Manager findManagerByProject(Integer id);
	public String findManagerNameByProject(Integer id);

}
