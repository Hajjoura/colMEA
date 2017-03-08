package com.supmeca.colMEA.domain;

import com.supmeca.colMEA.domain.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Engineer
 *
 */
@Entity
@Inheritance
public class Engineer extends User implements Serializable {

	private Domain domain;
	private List<Teams_Engineers> Teams = new ArrayList<Teams_Engineers>();
	private static final long serialVersionUID = 1L;

	public Engineer() {
		super();
	}
	
	@ManyToOne	
	@JoinColumn(name="id_domaine",referencedColumnName="id_domaine",insertable=false,updatable=false)
	public Domain getDomain() {
		return domain;
	}

	
	public void setDomain(Domain domain) {
		this.domain = domain;
	}

	@OneToMany(mappedBy="engineer")
	public List<Teams_Engineers> getTeams() {
		return Teams;
	}

	public void setTeams(List<Teams_Engineers> teams) {
		Teams = teams;
	}
	
	
   
}
