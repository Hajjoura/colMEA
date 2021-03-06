package com.supmeca.colMEA.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.supmeca.colMEA.domain.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Coordinator
 *
 */
@Entity
@Inheritance
public class Coordinator extends User implements Serializable {

	private List<Team> Teams = new ArrayList<Team>();
	private static final long serialVersionUID = 1L;

	public Coordinator() {
		super();
	}

	@JsonIgnore
	@OneToMany(mappedBy="coordinator", fetch = FetchType.EAGER,  cascade = CascadeType.ALL)
	public List<Team> getTeams() {
		return Teams;
	}

	public void setTeams(List<Team> teams) {
		Teams = teams;
	}
   
}
