package com.supmeca.colMEA.business;

import java.util.List;

import javax.ejb.Local;

import com.supmeca.colMEA.domain.Study;

@Local
public interface StudyServiceLocal {
	public void CreateStudy( Study Study) ;
	public void EditStudy (Study Study);
	public void removeStudy(Study Study);
	public Study findStudyById(int id);
	public List<Study> findAllStudys() ;
	public void DeleteStudy(int id);
}
