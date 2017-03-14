package com.supmeca.colMEA.domain;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Partition
 *
 */
@Entity(name="t_partition")

public class Partition implements Serializable {

	
	private Integer id_partition;
	private String name;
	private String description;
	private Study study;
	private List<Variables_Partitions> Variables = new ArrayList<Variables_Partitions>();
	private static final long serialVersionUID = 1L;

	public Partition() {
		super();
	}   
	@Id 
	@GeneratedValue (strategy=GenerationType.IDENTITY)

	public Integer getId_partition() {
		return this.id_partition;
	}

	public void setId_partition(Integer id_partition) {
		this.id_partition = id_partition;
	}   
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}   
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@ManyToOne
	@JoinColumn(name="id_study",referencedColumnName="id_study",insertable=false,updatable=false)
	public Study getStudy() {
		return study;
	}
	public void setStudy(Study study) {
		this.study = study;
	}
	@OneToMany(mappedBy="partition")
	public List<Variables_Partitions> getVariables() {
		return Variables;
	}
	public void setVariables(List<Variables_Partitions> variables) {
		Variables = variables;
	}
	
	
   
}
