package com.supmeca.colMEA.domain;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Tool
 *
 */
@Entity

public class Tool implements Serializable {

	
	private Integer id_tool;
	private String name;
	private List<Variable> Variables = new ArrayList<Variable>();
	private static final long serialVersionUID = 1L;

	//constructor with superclass
	public Tool() {
		super();
	}   
	@Id  
	@GeneratedValue (strategy=GenerationType.IDENTITY)

	public Integer getId_tool() {
		return this.id_tool;
	}

	public void setId_tool(Integer id_tool) {
		this.id_tool = id_tool;
	}   
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@OneToMany(mappedBy="tool")
	public List<Variable> getVariables() {
		return Variables;
	}
	public void setVariables(List<Variable> variables) {
		Variables = variables;
	}
	
	//constructor with Fields
	public Tool(String name, List<Variable> variables) {
		super();
		this.name = name;
		Variables = variables;
	}
	
   
}
