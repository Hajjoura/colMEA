package com.supmeca.colMEA.business;

import java.util.List;

import javax.ejb.Remote;

import com.supmeca.colMEA.domain.Study;

@Remote
public interface StudyServiceRemote {
	public void CreateStudy( Study Study) ;
	public void EditStudy (Study Study);
	public void removeStudy(Study Study);
	public Study findStudyById(int id);
	public List<Study> findAllStudys() ;
}
