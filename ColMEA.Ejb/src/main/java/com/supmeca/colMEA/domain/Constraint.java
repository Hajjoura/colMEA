package com.supmeca.colMEA.domain;

import com.supmeca.colMEA.domain.Variable;
import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Constraint
 *
 */
@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class Constraint extends Variable implements Serializable {

	
	private static final long serialVersionUID = 1L;

	public Constraint() {
		super();
	}
   
}
