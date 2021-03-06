package com.supmeca.colMEA.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class Variables_PartitionsFK implements Serializable{

	private static final long serialVersionUID = 1L;
	private Integer id_variable;
	private Integer id_partition;
	private Integer id_set;
	
	

	public Variables_PartitionsFK() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Variables_PartitionsFK(Integer id_variable, Integer id_partition,
			Integer id_set) {
		super();
		this.id_variable = id_variable;
		this.id_partition = id_partition;
		this.id_set = id_set;
	}



	public Integer getId_variable() {
		return id_variable;
	}

	public void setId_variable(Integer id_variable) {
		this.id_variable = id_variable;
	}

	public Integer getId_partition() {
		return id_partition;
	}

	public void setId_partition(Integer id_partition) {
		this.id_partition = id_partition;
	}

	public Integer getId_set() {
		return id_set;
	}

	public void setId_set(Integer id_set) {
		this.id_set = id_set;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((id_partition == null) ? 0 : id_partition.hashCode());
		result = prime * result + ((id_set == null) ? 0 : id_set.hashCode());
		result = prime * result
				+ ((id_variable == null) ? 0 : id_variable.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Variables_PartitionsFK other = (Variables_PartitionsFK) obj;
		if (id_partition == null) {
			if (other.id_partition != null)
				return false;
		} else if (!id_partition.equals(other.id_partition))
			return false;
		if (id_set == null) {
			if (other.id_set != null)
				return false;
		} else if (!id_set.equals(other.id_set))
			return false;
		if (id_variable == null) {
			if (other.id_variable != null)
				return false;
		} else if (!id_variable.equals(other.id_variable))
			return false;
		return true;
	}

	
	

}
