package com.supmeca.colMEA.domain;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Teams_Engineers implements Serializable{

	private Team team;
	private Engineer engineer;
	private Teams_EngineersFK teams_engineersfk;
	private static final long serialVersionUID = 1L;

	//constructor with superclass
	public Teams_Engineers() {
		super();
	}
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="id_team",referencedColumnName="id_team",insertable=false,updatable=false)
	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="id_user",referencedColumnName="id_user",insertable=false,updatable=false)
	public Engineer getEngineer() {
		return engineer;
	}

	public void setEngineer(Engineer engineer) {
		this.engineer = engineer;
	}
	@EmbeddedId
	public Teams_EngineersFK getTeams_engineersfk() {
		return teams_engineersfk;
	}
	public void setTeams_engineersfk(Teams_EngineersFK teams_engineersfk) {
		this.teams_engineersfk = teams_engineersfk;
	}
	//constructor with Fields
	public Teams_Engineers(Team team, Engineer engineer,
			Teams_EngineersFK teams_engineersfk) {
		super();
		this.team = team;
		this.engineer = engineer;
		this.teams_engineersfk = teams_engineersfk;
	}

	
}
