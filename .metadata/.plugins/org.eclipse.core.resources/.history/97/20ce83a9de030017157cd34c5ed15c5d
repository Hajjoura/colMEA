package com.supmeca.colMEA.domain;

import java.io.Serializable;
import java.lang.String;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Set
 *
 */
@Entity(name="t_set")

public class Set implements Serializable {

	
	private int id_set;
	private String value;
	private Variable variable;
	private static final long serialVersionUID = 1L;

	public Set() {
		super();
	}   
	@Id    
	@GeneratedValue (strategy=GenerationType.IDENTITY)

	public int getId_set() {
		return this.id_set;
	}

	public void setId_set(int id_set) {
		this.id_set = id_set;
	}   
	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	@ManyToOne
	@JoinColumn(name="id_variable",referencedColumnName="id_variable",insertable=false,updatable=false)

	public Variable getVariable() {
		return variable;
	}
	public void setVariable(Variable variable) {
		this.variable = variable;
	}
   
}
