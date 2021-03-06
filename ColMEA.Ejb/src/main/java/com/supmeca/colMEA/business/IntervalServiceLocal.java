package com.supmeca.colMEA.business;

import java.util.List;

import javax.ejb.Local;

import com.supmeca.colMEA.domain.Interval;

@Local
public interface IntervalServiceLocal {
	public void CreateInterval( Interval Interval) ;
	public void EditInterval (Interval Interval);
	public void removeInterval(Interval Interval);
	public Interval findIntervalById(int id);
	public List<Interval> findAllIntervals() ;
	public void DeleteInterval(int id);
	public List<Interval> findIntervalsByIdSet(Integer id);

}
