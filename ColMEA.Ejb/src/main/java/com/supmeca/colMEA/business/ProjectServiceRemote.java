package com.supmeca.colMEA.business;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import com.supmeca.colMEA.domain.Project;

@Remote
public interface ProjectServiceRemote {

	public void CreateProject( Project Project) ;
	public void EditProject (Project Project);
	public void removeProject(Project Project);
	public Project findProjectById(int id);
	public List<Project> findAllProjects();
	public void DeleteProject(int id);
	public Project findProjectByName(String name);
	public Project findProjectByTeam(Integer id);
	public List<Project> findProjectsByTeam(Integer id);
	public Project findProjectByCoordinator(Integer id);
	public List<Project> findProjectsByCoordinator(Integer id);
	public Project findProjectByStudy(Integer id);
	public List<Project> findProjectsByStudy(Integer id);
	public Project findProjectByManager(Integer id);
	public List<Project> findProjectsByManager(Integer id);
	public Project findProjectByEngineer(Integer id);
	public List<Project> findProjectsByEngineer(Integer id);
	public Project findProjectByStartDate(Date start_date);
	public Project findProjectByEndDate(Date end_date);
	void CreateProjectWithIdManager(Project Project, int id);
}
