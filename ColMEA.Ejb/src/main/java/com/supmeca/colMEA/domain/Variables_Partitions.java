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
	private Variables_PartitionsFK variables_partitionsFK;
	private Date date;
	
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

}
