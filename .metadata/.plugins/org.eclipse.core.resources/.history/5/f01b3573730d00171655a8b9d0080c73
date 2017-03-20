package com.supmeca.colMEA.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class Teams_EngineersFK implements Serializable{

	private Integer id_team;
	private Integer id_user;
	
	private static final long serialVersionUID = 1L;

	public Integer getId_team() {
		return id_team;
	}

	public void setId_team(Integer id_team) {
		this.id_team = id_team;
	}

	public Integer getId_user() {
		return id_user;
	}

	public void setId_user(Integer id_user) {
		this.id_user = id_user;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id_team == null) ? 0 : id_team.hashCode());
		result = prime * result + ((id_user == null) ? 0 : id_user.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Teams_EngineersFK other = (Teams_EngineersFK) obj;
		if (id_team == null) {
			if (other.id_team != null)
				return false;
		} else if (!id_team.equals(other.id_team))
			return false;
		if (id_user == null) {
			if (other.id_user != null)
				return false;
		} else if (!id_user.equals(other.id_user))
			return false;
		return true;
	}

}
