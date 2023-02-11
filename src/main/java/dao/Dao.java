package dao;

import java.util.Map;

import model.Model;

public abstract class Dao {
	protected Object connection; // Change type to actual component when created
	
	protected Dao() {
		// gets the DB connection from connection component
		// and stores it in the connection variable
	}
	
	public abstract Model get(int id);
	
	public void save(Model model) {
		// Save should check if user is being created or is already created
		Map<String, String> attributes = model.getAttributeMap();
		String table = model.getTable();
		for(String attribute : attributes.keySet()) {
			
			// construct save query
		}
	}
	
	public void delete(Model model) {
		Map<String, String> attributes = model.getAttributeMap();
		String table = model.getTable();
		for(String attribute : attributes.keySet()) {
			// construct delete query
		}
	}
	
	public int create(Model model) {
		// Insert new row in the database table and return the primary key of the row
		return -1;
	}
}
