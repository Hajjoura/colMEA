package com.supmeca.colMEA.business;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import com.supmeca.colMEA.domain.Partition;
import com.supmeca.colMEA.domain.Variable;
import com.supmeca.colMEA.domain.Variables_Partitions;
import com.supmeca.colMEA.domain.Variables_PartitionsFK;

@Local
public interface PartitionServiceLocal {
	public void CreatePartition( Partition Partition) ;
	public void EditPartition (Partition Partition);
	public void removePartition(Partition Partition);
	public Partition findPartitionById(int id);
	public List<Partition> findAllPartitions() ;
	public void DeletePartition(int id);
	public Boolean addVariableToPartition(Partition partition, Variable variable, Date date) ;
	public List<Variables_Partitions> findVariablesPartitions();
	public Boolean updateVariableToPartition(Partition partition, Variable variable, Date date);
	public Variables_Partitions findVariableById(Variables_PartitionsFK id) ;
}
