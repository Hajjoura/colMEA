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
	public void DeleteStudy(int id);
	public Study findStudyByType(String type);
	public Study findStudyByProject(Integer id);
	public List<Study> findStudiesByProject(Integer id);
	public Study findStudyByTeam(Integer id);
	public List<Study> findStudiesByTeam(Integer id);
}
