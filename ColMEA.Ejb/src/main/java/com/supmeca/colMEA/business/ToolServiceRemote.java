package com.supmeca.colMEA.business;

import java.util.List;

import javax.ejb.Remote;

import com.supmeca.colMEA.domain.Tool;

@Remote
public interface ToolServiceRemote {

	public void CreateTool( Tool Tool) ;
	public void EditTool (Tool Tool);
	public void removeTool(Tool Tool);
	public Tool findToolById(int id);
	public List<Tool> findAllTools() ;
	public void DeleteTool(int id);
}
