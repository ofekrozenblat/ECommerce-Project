package model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import dao.Dao;

public abstract class Model {
	protected String table; // Table of model in the database
	protected int id; // Primary key of table
	protected Map<String, String> attributes; // Key = column name in DB, Value = value of attribute
	protected Dao dao; // Data access object in charge of this model relationship to the database
	
	// Constructor if Model created inside application and not yet in database
	public Model(String[] attributeValues) {
		this.dao = getDao();
		createAttributeMap(attributeValues);
		
		// Get dao to store newly created model in the DB and return its ID
	}
	
	// Constructor if Model created from an already existing database row
	public Model(Dao dao, int id, String[] attributeValues) {
		this.dao = dao;
		this.id = id;
		createAttributeMap(attributeValues);
	}
	
	public void save() {
		dao.save(this);
	}
	
	public void delete() {
		dao.delete(this);
	}
	
	public int getId() {
		return this.id;
	}
	
	public Map<String, String> getAttributeMap(){
		return attributes;
	}
	
	// Force child to connect to a Dao object
	protected abstract Dao getDao();
	
	protected abstract String[] getAttributeNames();
	
	public abstract String getTable();

	private void createAttributeMap(String[] attributeValues) {
		attributes = new HashMap<String, String>();
		String[] attributeNames = getAttributeNames();
		
		if (attributeNames.length != attributeValues.length) {
			// Throw exception
		}
		
		for(int i = 0; i < attributeNames.length; i++) {
			attributes.put(attributeNames[i], attributeValues[i]);
		}	
	}
}
