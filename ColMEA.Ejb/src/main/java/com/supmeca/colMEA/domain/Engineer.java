package com.supmeca.colMEA.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

	//constructor with SuperClass
	public Engineer() {
		super();
	}
	
	@ManyToOne	
	@JsonIgnore
	@JoinColumn(name="id_domaine",referencedColumnName="id_domaine",insertable=false,updatable=false)
	public Domain getDomain() {
		return domain;
	}
	
	
	public void setDomain(Domain domain) {
		this.domain = domain;
	}

	@JsonIgnore
	@OneToMany(mappedBy="engineer",fetch = FetchType.EAGER,  cascade = CascadeType.ALL)
	public List<Teams_Engineers> getTeams() {
		return Teams;
	}

	public void setTeams(List<Teams_Engineers> teams) {
		Teams = teams;
	}
	//constructor with Fields

	public Engineer(String login, String password, String first_name,
			String last_name, Integer age, String email, String image,
			String service, String note, Domain domain,
			List<Teams_Engineers> teams) {
		super(login, password, first_name, last_name, age, email, image,
				service, note);
		this.domain = domain;
		Teams = teams;
	}
	

		
	
	
   
}
