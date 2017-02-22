package com.supmeca.colMEA.business;

import java.util.List;

import javax.ejb.Remote;

import com.supmeca.colMEA.domain.Engineer;

@Remote
public interface EngineerServiceRemote {
	
	public void CreateEngineer( Engineer Engineer) ;
	public void EditEngineer (Engineer Engineer);
	public void removeEngineer(Engineer Engineer);
	public Engineer findEngineerById(int id);
	public List<Engineer> findAllEngineers() ;

}
