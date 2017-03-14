package com.supmeca.colMEA.business;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.supmeca.colMEA.domain.Coordinator;
import com.supmeca.colMEA.domain.Partition;

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

}
