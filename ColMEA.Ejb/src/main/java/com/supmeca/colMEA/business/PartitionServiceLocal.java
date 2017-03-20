package com.supmeca.colMEA.business;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import com.supmeca.colMEA.domain.Partition;
import com.supmeca.colMEA.domain.Variable;

@Local
public interface PartitionServiceLocal {
	public void CreatePartition( Partition Partition) ;
	public void EditPartition (Partition Partition);
	public void removePartition(Partition Partition);
	public Partition findPartitionById(int id);
	public List<Partition> findAllPartitions() ;
	public void DeletePartition(int id);
	public Boolean addVariableToPartition(Partition partition, Variable variable, Date date) ;
}
