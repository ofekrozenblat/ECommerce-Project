package model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import dao.Dao;

/**
 * An object that is used to represent a generic database table. This class is meant to be
 * extended and implemented by child classes that will represent specific individual tables
 * in the database.
 * 
 * @author ofekr
 *
 */
public abstract class Model {
	
	/** Table of the model in the database **/
	protected String table;
	
	/** Primary key **/
	protected int id;
	
	/** Attribute map with key=column name in database, value=value in column **/
	protected Map<String, String> attributes;
	
	/** Data access object in charge of this model's relationship to the database **/
	protected Dao dao;
	
	 
	/**
	 * Creates a model inside the application that is not yet in database.
	 * <br>
	 * Use {@link #save()} to save Model in the databse.
	 */
	public Model(Dao dao) {
		this.dao = dao;
		this.id = -1;
		createAttributeMap();
	}
	
	/**
	 * Creates a model inside the application that already exists in database.
	 * 
	 * @param dao data access object in charge of this model
	 * @param id primary key of this model
	 */
	public Model(Dao dao, int id) {
		this.dao = dao;
		this.id = id;
		createAttributeMap();
	}
	
	/**
	 * Saves the model in the database.
	 */
	public void save() {
		// Possibly also check if model is dirty or not
		
		// Check to see if this model is in the database or not based on its id
		if (id == -1) {
			// Get dao to store newly created model in the DB and return its ID
			id = dao.create(this);
		}else {
			dao.save(this);
		}
	}
	
	/**
	 * Deletes the model from the database.
	 */
	public void delete() {
		dao.delete(this);
	}
	
	/**
	 * Retrieves the primary key of this model.
	 * 
	 * @return primary key
	 */
	public int getId() {
		return this.id;
	}
	
	/**
	 * Retrieves the attribute map of this model.
	 * 
	 * @return map where key=attribute name, value=attribute value
	 */
	public Map<String, String> getAttributeMap(){
		return attributes;
	}
	
	/**
	 * Retrieves the table name of this model in the database.
	 * 
	 * @return name of this model's table
	 */
	public abstract String getTable();

	/**
	 * Retrieves the attribute names related to this model. The attribute names must be
	 * the same as the column names of this model in the database and in the same order.
	 * 
	 * @return string array of the attribute names
	 */
	protected abstract String[] getAttributeNames();

	/**
	 * Creates the attribute map of this model. Sets all the attribute values to an empty
	 * string by default. To set the attribute values the child must implement setters.
	 */
	private void createAttributeMap() {
		attributes = new HashMap<String, String>();
		String[] attributeNames = getAttributeNames();
		
		for(int i = 0; i < attributeNames.length; i++) {
			attributes.put(attributeNames[i], ""); // Assign empty strings
		}	
	}
}
