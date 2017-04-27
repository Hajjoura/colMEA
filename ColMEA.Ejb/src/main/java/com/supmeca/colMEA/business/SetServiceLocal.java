package com.supmeca.colMEA.business;

import java.util.List;

import javax.ejb.Local;

import com.supmeca.colMEA.domain.Set;

@Local
public interface SetServiceLocal {
	
	public void CreateSet( Set Set) ;
	public void EditSet (Set Set);
	public void removeSet(Set Set);
	public Set findSetById(int id);
	public List<Set> findAllSets() ;
	public void DeleteSet(int id);
	public List<Set> findSetByMinMax(Float min, Float max);
}
