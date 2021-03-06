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
		String text = "SELECT t FROM t_interval t";
		Query query = em.createQuery(text);
		List<Interval> ListIntervals = query.getResultList();
		
		return ListIntervals;
	}

	@Override
	public void DeleteInterval(int id) {
		em.remove(em.find(Interval.class, id));	
		
	}
	@Override
	public List<Interval> findIntervalsByIdSet(Integer id)
	{
		String text = "SELECT t FROM t_interval as t , t_set as s"
				+ " WHERE t.sets.id_set = s.id_set and s.id_set =:id ";
		Query query = em.createQuery(text);
		query.setParameter("id", id);
		List<Interval> ListIntervals = query.getResultList();
		return ListIntervals;
	}
	
	public List<Interval> findIntervalsByVariable(Integer id){
		String text = "SELECT t FROM t_interval as t , t_set as s, Variable as v"
				+ " WHERE v.id_variable = s.variable.id_variable and t.sets.id_set = s.id_set "
				+ "and v.id_variable =:id ";
		Query query = em.createQuery(text);
		query.setParameter("id", id);
		List<Interval> ListIntervals = query.getResultList();
		return ListIntervals;
	}
}
