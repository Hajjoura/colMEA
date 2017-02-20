package com.supmeca.colMEA.domain;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Team
 *
 */
@Entity

public class Team implements Serializable {

	
	private Integer id_team;
	private String name;
	private Coordinator coordinator;
	private List<Teams_Engineers> Engineers = new ArrayList<Teams_Engineers>();
	private List<Study> Studies = new ArrayList<Study>();

	private static final long serialVersionUID = 1L;

	public Team() {
		super();
	}   
	@Id    
	@GeneratedValue (strategy=GenerationType.IDENTITY)

	public Integer getId_team() {
		return this.id_team;
	}

	public void setId_team(Integer id_team) {
		this.id_team = id_team;
	}   
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@ManyToOne
	@JoinColumn(name="id_user",referencedColumnName="id_user",insertable=false,updatable=false)

	public Coordinator getCoordinator() {
		return coordinator;
	}
	public void setCoordinator(Coordinator coordinator) {
		this.coordinator = coordinator;
	}
	
	
	@OneToMany(mappedBy="team")
	public List<Study> getStudies() {
		return Studies;
	}
	public void setStudies(List<Study> studies) {
		Studies = studies;
	}
	
	@OneToMany(mappedBy="team")
	public List<Teams_Engineers> getEngineers() {
		return Engineers;
	}
	public void setEngineers(List<Teams_Engineers> engineers) {
		Engineers = engineers;
	}
   
}
