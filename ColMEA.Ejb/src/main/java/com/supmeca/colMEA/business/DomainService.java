package com.supmeca.colMEA.business;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.supmeca.colMEA.domain.Coordinator;
import com.supmeca.colMEA.domain.Domain;

/**
 * Session Bean implementation class DomainService
 */
@Stateless
@LocalBean
public class DomainService implements DomainServiceRemote, DomainServiceLocal {

	@PersistenceContext
	private EntityManager em;
	
	Domain Domain;
    /**
     * Default constructor. 
     */
    public DomainService() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void CreateDomain(Domain Domain) {
		em.persist(Domain);
	}

	@Override
	public void EditDomain(Domain Domain) {
		em.merge(Domain);
	}

	@Override
	public void removeDomain(Domain Domain) {
		em.remove(em.merge(Domain));
	}

	@Override
	public Domain findDomainById(int id) {
		Domain Domain=em.find(Domain.class,id);
		if(Domain!=null){
			return Domain;
		}
		return null;
	}

	@Override
	public List<Domain> findAllDomains() {
		String text = "SELECT v FROM Variable v";
		Query query = em.createQuery(text);
		List<Domain> ListDomains = query.getResultList();
		
		return ListDomains;
	}

	@Override
	public void DeleteDomain(int id) {
		em.remove(em.find(Domain.class, id));	
		
	}

}
