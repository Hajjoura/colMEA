package com.supmeca.colMEA.business;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.supmeca.colMEA.domain.Interval;

/**
 * Session Bean implementation class IntervalService
 */
@Stateless(name = "IntervalServices")
@LocalBean
public class IntervalService implements IntervalServiceRemote, IntervalServiceLocal {

	@PersistenceContext
	private EntityManager em;
    /**
     * Default constructor. 
     */
    public IntervalService() {
        // TODO Auto-generated constructor stub
    }
    Interval Interval;
    @Override
	public void CreateInterval(Interval Interval) {
		em.persist(Interval);
	}

	@Override
	public void EditInterval(Interval Interval) {
		em.merge(Interval);
	}

	@Override
	public void removeInterval(Interval Interval) {
		em.remove(em.merge(Interval));
	}

	@Override
	public Interval findIntervalById(int id) {
		Interval Interval=em.find(Interval.class,id);
		if(Interval!=null){
			return Interval;
		}
		return null;
	}

	@Override
	public List<Interval> findAllIntervals() {
		String text = "SELECT t FROM Interval t";
		Query query = em.createQuery(text);
		List<Interval> ListIntervals = query.getResultList();
		
		return ListIntervals;
	}

	@Override
	public void DeleteInterval(int id) {
		em.remove(em.find(Interval.class, id));	
		
	}
}
