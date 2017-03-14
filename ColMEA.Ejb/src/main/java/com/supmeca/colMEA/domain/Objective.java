package com.supmeca.colMEA.domain;

import com.supmeca.colMEA.domain.Variable;
import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Objectif
 *
 */
@Entity
@Inheritance
public class Objective extends Variable implements Serializable {

	
	private static final long serialVersionUID = 1L;

	//constructor with SuperClass
	public Objective() {
		super();
	}
	
   
}
