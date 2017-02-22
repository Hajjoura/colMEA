package com.supmeca.colMEA.business;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.supmeca.colMEA.domain.Engineer;

/**
 * Session Bean implementation class EngineerService
 */
@Stateless
@LocalBean
public class EngineerService implements EngineerServiceRemote, EngineerServiceLocal {

    /**
     * Default constructor. 
     */
    public EngineerService() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void CreateEngineer(Engineer Engineer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void EditEngineer(Engineer Engineer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeEngineer(Engineer Engineer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Engineer findEngineerById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Engineer> findAllEngineers() {
		// TODO Auto-generated method stub
		return null;
	}

}
