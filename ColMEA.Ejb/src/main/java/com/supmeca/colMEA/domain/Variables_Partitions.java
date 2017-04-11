package com.supmeca.colMEA.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity

public class Variables_Partitions implements Serializable {

	private Variable variable;
	private Partition partition;
	private Set set;
	private Variables_PartitionsFK variables_partitionsFK;
	private Date date;
	private float Min;
	private float Max;
	
	private static final long serialVersionUID = 1L;
	
	
	public Variables_Partitions() {
		super();
		
	}
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="id_variable",referencedColumnName="id_variable",insertable=false,updatable=false)
	
	public Variable getVariable() {
		return variable;
	}
	public void setVariable(Variable variable) {
		this.variable = variable;
	}
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="id_partition",referencedColumnName="id_partition",insertable=false,updatable=false)
	public Partition getPartition() {
		return partition;
	}
	public void setPartition(Partition partition) {
		this.partition = partition;
	}
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="id_set",referencedColumnName="id_set",insertable=false,updatable=false)
	
	public Set getSet() {
		return set;
	}
	public void setSet(Set set) {
		this.set = set;
	}
	
	@EmbeddedId
	public Variables_PartitionsFK getVariables_partitionsFK() {
		return variables_partitionsFK;
	}
	public void setVariables_partitionsFK(Variables_PartitionsFK variables_partitionsFK) {
		this.variables_partitionsFK = variables_partitionsFK;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
		

	public float getMin() {
		return Min;
	}
	public void setMin(float min) {
		Min = min;
	}
	public float getMax() {
		return Max;
	}
	public void setMax(float max) {
		Max = max;
	}
	public Variables_Partitions(Variable variable, Partition partition,
			Set set, Date date,
			float min, float max) {
		super();
		this.variable = variable;
		this.partition = partition;
		this.set = set;
		this.variables_partitionsFK = new Variables_PartitionsFK(variable.getId_variable(), partition.getId_partition(), set.getId_set());
		this.date = date;
		Min = min;
		Max = max;
	}
	

	

}
