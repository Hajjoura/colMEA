package com.supmeca.colMEA.domain;

import java.io.Serializable;
import java.lang.Float;
import java.lang.Integer;

import javax.persistence.*;


/**
 * Entity implementation class for Entity: Interval
 *
 */
@Entity(name="t_interval")

public class Interval implements Serializable {

	
	private Integer id_interval;
	private Float min;
	private Float max;
	private Set sets;
	private static final long serialVersionUID = 1L;

	public Interval() {
		super();
	}   
	@Id    
	@GeneratedValue (strategy=GenerationType.IDENTITY)
	public Integer getId_interval() {
		return this.id_interval;
	}

	public void setId_interval(Integer id_interval) {
		this.id_interval = id_interval;
	}   
	public Float getMin() {
		return this.min;
	}

	public void setMin(Float min) {
		this.min = min;
	}   
	public Float getMax() {
		return this.max;
	}

	public void setMax(Float max) {
		this.max = max;
	}
	
	@ManyToOne
	@JoinColumn(name="id_set",referencedColumnName="id_set",insertable=false,updatable=false)

	public Set getSets() {
		return sets;
	}
	public void setSets(Set sets) {
		this.sets = sets;
	}
	public Interval(Integer id_interval, Float min, Float max, Set sets) {
		super();
		this.id_interval = id_interval;
		this.min = min;
		this.max = max;
		this.sets = sets;
	}
   
}
