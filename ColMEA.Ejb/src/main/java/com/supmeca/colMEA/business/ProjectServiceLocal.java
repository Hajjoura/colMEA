package com.supmeca.colMEA.business;

import java.util.List;

import javax.ejb.Local;

import com.supmeca.colMEA.domain.Project;

@Local
public interface ProjectServiceLocal {
	public void CreateProject( Project Project) ;
	public void EditProject (Project Project);
	public void removeProject(Project Project);
	public Project findProjectById(int id);
	public List<Project> findAllProjects() ;
}
