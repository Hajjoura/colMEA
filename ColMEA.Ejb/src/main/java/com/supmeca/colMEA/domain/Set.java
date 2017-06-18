package com.supmeca.colMEA.domain;

import java.io.Serializable;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;



/**
 * Entity implementation class for Entity: Set
 *
 */
@Entity(name="t_set")

public class Set implements Serializable {

	
	private Integer id_set;
	private String value;
	private Variable variable;
	private List<Interval> intervales = new ArrayList<Interval>();
	private List<Variables_Partitions> partitions = new ArrayList<Variables_Partitions>();
	private static final long serialVersionUID = 1L;

	//constructor with SuperClass
	public Set() {
		super();
	}   
	@Id    
	@GeneratedValue (strategy=GenerationType.IDENTITY)

	public Integer getId_set() {
		return this.id_set;
	}

	public void setId_set(Integer id_set) {
		this.id_set = id_set;
	}   
	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	@JsonIgnore
	@OneToMany(mappedBy="test")
	public List<Variables_Partitions> getPartitions() {
		return partitions;
	}
	public void setPartitions(List<Variables_Partitions> partitions) {
		this.partitions = partitions;
	}


	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="id_variable",referencedColumnName="id_variable",insertable=false,updatable=false)
	public Variable getVariable() {
		return variable;
	}
	public void setVariable(Variable variable) {
		this.variable = variable;
	}


	@JsonIgnore
	@OneToMany(mappedBy="sets")
	public List<Interval> getIntervales() {
		return intervales;
	}
	public void setIntervales(List<Interval> intervales) {
		this.intervales = intervales;
	}
	
	
	public Set(Integer id_set, String value, Variable variable,
			List<Interval> intervales, List<Variables_Partitions> partitions) {
		super();
		this.id_set = id_set;
		this.value = value;
		this.variable = variable;
		this.intervales = intervales;
		this.partitions = partitions;
	}

   
}
