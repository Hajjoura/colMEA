package com.supmeca.colMEA.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class Variables_PartitionsFK implements Serializable{

	private Integer id_variable;
	private Integer id_partition;
	
	private static final long serialVersionUID = 1L;

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((id_partition == null) ? 0 : id_partition.hashCode());
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
		if (id_variable == null) {
			if (other.id_variable != null)
				return false;
		} else if (!id_variable.equals(other.id_variable))
			return false;
		return true;
	}

}
