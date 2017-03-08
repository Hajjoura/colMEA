package com.supmeca.colMEA.domain;

import java.io.Serializable;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Domain
 *
 */
@Entity

public class Domain implements Serializable {

	
	private int id_domaine;
	private String label;
	private List<Engineer> Engineers = new ArrayList<Engineer>();

	private static final long serialVersionUID = 1L;

	public Domain() {
		super();
	}   
	@Id
	@GeneratedValue (strategy=GenerationType.IDENTITY)

	public int getId_domaine() {
		return this.id_domaine;
	}

	public void setId_domaine(int id_domaine) {
		this.id_domaine = id_domaine;
	}   
	public String getLabel() {
		return this.label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
	@OneToMany(mappedBy="domain", cascade=CascadeType.ALL)
	public List<Engineer> getEngineers() {
		return Engineers;
	}
	public void setEngineers(List<Engineer> engineers) {
		Engineers = engineers;
	}
   
}
