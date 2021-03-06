package com.supmeca.colMEA.business;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.supmeca.colMEA.domain.Coordinator;
import com.supmeca.colMEA.domain.Set;
import com.supmeca.colMEA.domain.Variable;

/**
 * Session Bean implementation class SetService
 */
@Stateless
@LocalBean
public class SetService implements SetServiceRemote, SetServiceLocal {

	@PersistenceContext
	private EntityManager em;

	Set Set;
	/**
	 * Default constructor. 
	 */
	public SetService() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void addSet(Set Set) {
		
		em.persist(Set);
	}
	@Override
	public void CreateSet(Set Set,Variable variable) {
		Set.setVariable(variable);
		em.persist(Set);
	}

	@Override
	public void EditSet(Set Set) {
		em.merge(Set);
	}

	@Override
	public void removeSet(Set Set) {
		em.remove(em.merge(Set));
	}

	@Override
	public Set findSetById(int id) {
		Set Set=em.find(Set.class,id);
		if(Set!=null){
			return Set;
		}
		return null;
	}

	@Override
	public List<Set> findAllSets() {
		String text = "SELECT s FROM t_set s";
		Query query = em.createQuery(text);
		List<Set> ListSets = query.getResultList();

		return ListSets;
	}

	@Override
	public void DeleteSet(int id) {
		em.remove(em.find(Set.class, id));	

	}
	@Override
	public List<Set> findSetByMinMax(Float min, Float max){
		String text = "SELECT s FROM t_set as s, t_interval as t "
				+ "WHERE t.sets.id_set = s.id_set and t.min =:min and t.max =:max";
		Query query = em.createQuery(text);
		query.setParameter("min", min);
		query.setParameter("max", max);
		List<Set> ListSets = query.getResultList();
		return ListSets;

	}
	@Override
	public void addeSet(Set Set,Variable variable) {
		Set.setVariable(variable);
		em.merge(Set);
	}
	
	@Override
	public Set getLastRowSet() {
		String text = "SELECT s FROM t_set as s ORDER BY s.id_set DESC";

		Query query = em.createQuery(text);
		query.setFirstResult(0);
		query.setMaxResults(1);
		Set Set = (Set)query.getSingleResult();

		return Set;
	}
	@Override
	public List<Set> getLatestRowSet(int num) {
		String text = "SELECT s FROM t_set as s ORDER BY s.id_set DESC";

		Query query = em.createQuery(text);
		query.setMaxResults(num);
		List<Set> ListSets = query.getResultList();
		return ListSets;

	}

}
