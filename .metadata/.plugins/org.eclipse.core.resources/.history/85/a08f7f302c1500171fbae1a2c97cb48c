package com.supmeca.colMEA.domain;

import com.supmeca.colMEA.domain.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Manager
 *
 */
@Entity
@Inheritance
public class Manager extends User implements Serializable {

	private List<Project> Projects = new ArrayList<Project>();
	private static final long serialVersionUID = 1L;

	//constructor with SuperClass
	public Manager() {
		super();
	}

	@OneToMany(mappedBy="manager")
	public List<Project> getProjects() {
		return Projects;
	}

	public void setProjects(List<Project> projects) {
		Projects = projects;
	}
	//constructor with Fields
	public Manager(String login, String password, String first_name,
			String last_name, Integer age, String email, String image,
			String service, String note, List<Project> projects) {
		super(login, password, first_name, last_name, age, email, image,
				service, note);
		Projects = projects;
	}

	
	
   
	
}
