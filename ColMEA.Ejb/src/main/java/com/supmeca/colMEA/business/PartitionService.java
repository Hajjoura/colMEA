package com.supmeca.colMEA.business;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.supmeca.colMEA.domain.Coordinator;
import com.supmeca.colMEA.domain.Partition;
import com.supmeca.colMEA.domain.Teams_Engineers;
import com.supmeca.colMEA.domain.Variable;
import com.supmeca.colMEA.domain.Variables_Partitions;
import com.supmeca.colMEA.domain.Variables_PartitionsFK;

/**
 * Session Bean implementation class PartitionService
 */
@Stateless
@LocalBean
public class PartitionService implements PartitionServiceRemote, PartitionServiceLocal {

	@PersistenceContext
	private EntityManager em;
	
	Partition Partition;
    /**
     * Default constructor. 
     */
    public PartitionService() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void CreatePartition(Partition Partition) {
		em.persist(Partition);
	}

	@Override
	public void EditPartition(Partition Partition) {
		em.merge(Partition);
	}

	@Override
	public void removePartition(Partition Partition) {
		em.remove(em.merge(Partition));
	}

	@Override
	public Partition findPartitionById(int id) {
		Partition Partition=em.find(Partition.class,id);
		if(Partition!=null){
			return Partition;
		}
		return null;
	}

	@Override
	public List<Partition> findAllPartitions() {
		String text = "SELECT p FROM t_partition p";
		Query query = em.createQuery(text);
		List<Partition> ListPartitions = query.getResultList();
		
		return ListPartitions;
	}

	@Override
	public void DeletePartition(int id) {
		em.remove(em.find(Partition.class, id));	
		
	}

	//Add partition to variable
	@Override
	public Boolean addVariableToPartition(Partition partition, Variable variable, Date date) {
		
		try{
			Variables_Partitions varpart = new Variables_Partitions(variable,partition, date);
						
			em.persist(varpart);
			return true;	
		}catch(Exception e){
			return false;
		}
	}
	
	//Display Variables_Partitions
		@Override
		public List<Variables_Partitions> findVariablesPartitions() {
			String text = "SELECT vp FROM Variables_Partitions vp";
			Query query = em.createQuery(text);
			List<Variables_Partitions> ListTeams = query.getResultList();
					
			return ListTeams;
		}
		
		//update Variables_Partitions
		@Override
		public Boolean updateVariableToPartition(Partition partition, Variable variable, Date date) {
			
			try{
				Variables_Partitions varpart = new Variables_Partitions(em.merge(variable), em.merge(partition), date);
							
				em.persist(varpart);
				return true;	
			}catch(Exception e){
				return false;
			}
		}

	//find variable by id
		@Override
		public Variables_Partitions findVariableById(Variables_PartitionsFK id) {
			Variables_Partitions varpart =null;
			try{
				varpart = em.find(Variables_Partitions.class,id);

				if (varpart != null)
				{
					return varpart;	
				}
			return null;
			}catch(Exception e){
				return null;
			}
		}
}
