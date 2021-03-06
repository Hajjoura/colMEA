package com.supmeca.colMEA.business;

import java.util.List;

import javax.ejb.Remote;

import com.supmeca.colMEA.domain.Set;
import com.supmeca.colMEA.domain.Variable;

@Remote
public interface SetServiceRemote {

	public void CreateSet(Set Set,Variable variable) ;
	public void EditSet (Set Set);
	public void removeSet(Set Set);
	public Set findSetById(int id);
	public List<Set> findAllSets() ;
	public void DeleteSet(int id);
	public List<Set> findSetByMinMax(Float min, Float max);
	public void addeSet(Set Set,Variable variable);
	public Set getLastRowSet();
	public void addSet(Set Set);
	public List<Set> getLatestRowSet(int num);

}
