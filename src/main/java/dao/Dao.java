package dao;

import java.util.Map;

import model.Model;

public abstract class Dao {
	protected Object connection; // Change type to actual component when created
	
	protected void init() {
		// gets the DB connection from connection component
		// and stores it in the connection variable
	}
	
	public abstract Model get(int id);
	
	public void save(Model model) {
		// Save should check if user is being created or is already created
		
		Map<String, String> attributes = model.getAttributeMap();
		for(String attribute : attributes.keySet()) {
			String table = model.getTable();
			// construct save query
		}
	}
	
	public void delete(Model model) {
		Map<String, String> attributes = model.getAttributeMap();
		for(String attribute : attributes.keySet()) {
			String table = model.getTable();
			// construct delete query
		}
	}
	
	public void create() {
		// Insert new row in the database table
	}
}
