package com.supmeca.colMEA.business;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.supmeca.colMEA.domain.Coordinator;


@Stateless
@LocalBean
public class CoordinatorService implements CoordinatorServiceRemote, CoordinatorServiceLocal {

    /**
     * Default constructor. 
     */
    public CoordinatorService() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void CreateCoordinator(Coordinator Coordinator) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void EditCoordinator(Coordinator Coordinator) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeCoordinator(Coordinator Coordinator) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Coordinator findCoordinatorById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Coordinator> findAllCoordinators() {
		// TODO Auto-generated method stub
		return null;
	}

}
