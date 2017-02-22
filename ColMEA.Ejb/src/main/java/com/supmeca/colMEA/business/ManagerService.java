package com.supmeca.colMEA.business;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.supmeca.colMEA.domain.Manager;

/**
 * Session Bean implementation class ManagerService
 */
@Stateless
@LocalBean
public class ManagerService implements ManagerServiceRemote, ManagerServiceLocal {

    /**
     * Default constructor. 
     */
    public ManagerService() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void CreateManager(Manager Manager) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void EditManager(Manager Manager) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeManager(Manager Manager) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Manager findManagerById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Manager> findAllManagers() {
		// TODO Auto-generated method stub
		return null;
	}

}
