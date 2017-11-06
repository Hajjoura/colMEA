package com.supmeca.colMEA.business;

import java.util.List;

import javax.ejb.Local;

import com.supmeca.colMEA.domain.Partition;
import com.supmeca.colMEA.domain.Study;
import com.supmeca.colMEA.domain.Variable;

@Local
public interface StudyServiceLocal {
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
	public void duplicateStudy(Integer id);
	public List<Partition> findPartitionsByStudy(Integer id);
	public List<Variable> findVariablesByPartition(Integer id);
	public List<Study> findStudiesByEngineer(Integer id) ;
	public List<Study> findStudiesByCoordinator(Integer id);
	public List<Study> findStudiesByManager(Integer id) ;
	public Study findStudyByEngineer(Integer id);
	void CreateStudie(Study s, int id);
	
}
