package com.supmeca.colMEA.business;

import java.util.List;

import javax.ejb.Remote;

import com.supmeca.colMEA.domain.Domain;

@Remote
public interface DomainServiceRemote {

	public void CreateDomain( Domain Domain) ;
	public void EditDomain (Domain Domain);
	public void removeDomain(Domain Domain);
	public Domain findDomainById(int id);
	public List<Domain> findAllDomains() ;
	public void DeleteDomain(int id);
	public Domain findDomainByEngineer(Integer id);
}
