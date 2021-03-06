package com.supmeca.colMEA.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class Variables_Partitions implements Serializable {

	private Variable variable;
	private Partition partition;
	private Set test;
	private Variables_PartitionsFK variables_partitionsFK;
	private Date date;
	private Float minRes;
	private Float maxRes;
	
	private static final long serialVersionUID = 1L;
	
	
	public Variables_Partitions() {
		super();
		
	}
	@ManyToOne
	
	@JoinColumn(name="id_variable",referencedColumnName="id_variable",insertable=false,updatable=false)
	
	public Variable getVariable() {
		return variable;
	}
	public void setVariable(Variable variable) {
		this.variable = variable;
	}
	@ManyToOne
	
	@JoinColumn(name="id_partition",referencedColumnName="id_partition",insertable=false,updatable=false)
	public Partition getPartition() {
		return partition;
	}
	public void setPartition(Partition partition) {
		this.partition = partition;
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
		

	
	public Variables_Partitions(Variable variable, Partition partition,
			Set test, Date date, Float min , Float max) {
		super();
		this.variable = variable;
		this.partition = partition;
		this.test = test;
		this.date = date;
		this.maxRes = max;
		this.minRes = min;
		this.variables_partitionsFK = new Variables_PartitionsFK(variable.getId_variable(), partition.getId_partition(), test.getId_set());

	}
	
	@ManyToOne
	
	@JoinColumn(name="id_set",referencedColumnName="id_set",insertable=false,updatable=false)
	public Set getTest() {
		return test;
	}
	public void setTest(Set test) {
		this.test = test;
	}
	public Float getMinRes() {
		return minRes;
	}
	public void setMinRes(Float minRes) {
		this.minRes = minRes;
	}
	public Float getMaxRes() {
		return maxRes;
	}
	public void setMaxRes(Float maxRes) {
		this.maxRes = maxRes;
	}
	

	

}
