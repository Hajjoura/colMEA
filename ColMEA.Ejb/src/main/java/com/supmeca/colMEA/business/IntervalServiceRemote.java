package com.supmeca.colMEA.business;

import java.util.List;

import javax.ejb.Remote;

import com.supmeca.colMEA.domain.Interval;

@Remote
public interface IntervalServiceRemote {
	public void CreateInterval( Interval Interval) ;
	public void EditInterval (Interval Interval);
	public void removeInterval(Interval Interval);
	public Interval findIntervalById(int id);
	public List<Interval> findAllIntervals() ;
	public void DeleteInterval(int id);
	public List<Interval> findIntervalsByIdSet(Integer id);

}
