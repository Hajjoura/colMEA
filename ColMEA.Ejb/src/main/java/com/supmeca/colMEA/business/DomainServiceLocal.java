package com.supmeca.colMEA.business;

import java.util.List;

import javax.ejb.Local;

import com.supmeca.colMEA.domain.Domain;

@Local
public interface DomainServiceLocal {

	public void CreateDomain( Domain Domain) ;
	public void EditDomain (Domain Domain);
	public void removeDomain(Domain Domain);
	public Domain findDomainById(int id);
	public List<Domain> findAllDomains() ;
}
