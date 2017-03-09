package com.supmeca.colMEA.business;

import java.util.List;

import javax.ejb.Local;

import com.supmeca.colMEA.domain.Tool;

@Local
public interface ToolServiceLocal {
	public void CreateTool( Tool Tool) ;
	public void EditTool (Tool Tool);
	public void removeTool(Tool Tool);
	public Tool findToolById(int id);
	public List<Tool> findAllTools() ;
	public void DeleteTool(int id);
}
