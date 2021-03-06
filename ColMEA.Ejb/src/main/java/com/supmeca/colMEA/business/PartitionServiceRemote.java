package com.supmeca.colMEA.business;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import com.supmeca.colMEA.domain.Partition;
import com.supmeca.colMEA.domain.Set;
import com.supmeca.colMEA.domain.Variable;
import com.supmeca.colMEA.domain.Variables_Partitions;
import com.supmeca.colMEA.domain.Variables_PartitionsFK;

@Remote
public interface PartitionServiceRemote {

	public void CreatePartition( Partition Partition) ;
	public void EditPartition (Partition Partition);
	public void removePartition(Partition Partition);
	public Partition findPartitionById(int id);
	public List<Partition> findAllPartitions();
	public void DeletePartition(int id);
	public Boolean addVariableToPartition(Partition partition, Variable variable,Set set ,Date date, Float min, Float max);
	public List<Variables_Partitions> findVariablesPartitions();
	public Boolean updateVariableToPartition(Partition partition, Variable variable,Set set, Date date, Float min , Float max);
	public Variables_Partitions findVariableById(Variables_PartitionsFK id) ;
	public Partition findPartitionByName(String name);
	public Partition findPartitionByTeam(Integer id);
	public List<Partition> findPartitionsByTeam(Integer id);
	public Partition findPartitionByProject(Integer id);
	public List<Partition> findPartitionsByProject(Integer id);
	public Partition findPartitionByStudy(Integer id);
	public List<Partition> findPartitionsByStudy(Integer id);
	public Partition findPartitionByEngineer(Integer id);
	public Partition findPartitionByCoordinator(Integer id);
	public List<Partition> findPartitionsByCoordinator(Integer id);
	public Partition findPartitionByManager(Integer id);
	public List<Partition> findPartitionsByManager(Integer id);
	public Partition findPartitionByVariable(Integer id);
	public List<Partition> findPartitionsByVariable(Integer id);
	public Partition findPartitionByConstraint(Integer id);
	public List<Partition> findPartitionsByConstraint(Integer id);
	public Partition findPartitionByObjective(Integer id);
	public List<Partition> findPartitionsByObjective(Integer id);
	public List<Variables_Partitions> findVariablePartitionByIdVable(Integer id);
	public List<Variables_Partitions>  findVariablePartitionByIdPart(Integer id);
	public List<Variables_Partitions> findVariablePartitionByIdSet(Integer id);

}
