package model;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

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
	
	/** Primary key value **/
	protected int id;
	
	/** Data access object in charge of this model's relationship to the database **/
	protected Dao dao;
	
	/** Attribute map with key=column name in database, value=value in column **/
	private Map<String, String> attributes;


	/**
	 * Creates a model inside the application that is not yet in database.
	 * <br>
	 * Use {@link #save()} to save Model in the databse.
	 */
	public Model(Dao dao) {
		this(dao, -1);
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
	 * 
	 * @throws SQLException if the save fails
	 */
	public void save() throws SQLException {
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
	 * 
	 * @throws SQLException if deleting the model fails
	 */
	public void delete() throws SQLException{
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
	 * Retrieves the name of the primary key column name.
	 * 
	 * @return primary key column name
	 */
	public abstract String getPrimaryKeyColumnName();

	/**
	 * Retrieves the table name of this model in the database.
	 * 
	 * @return name of this model's table
	 */
	public abstract String getTable();

	/**
	 * Assigns a value to the attribute with a given name only if the attribute
	 * exists as part of the attributes declared by the child.
	 * 
	 * @param attributeName attribute name to modify
	 * @param value to assign to the attribute
	 */
	public void setAttribute(String attributeName, String value) {
		// Check to make sure method is not used to insert 
		// an attribute not declared in attributeNames
		if (attributes.containsKey(attributeName)) {
			attributes.put(attributeName, value);
		}
	}
	
	/**
	 * Retrieves the value of the attribute with the given name or null if the attribute
	 * does not exist.
	 * 
	 * @param attributeName attribute name to retrieve
	 * @return value of attribute if it exists, {@code null} otherwise
	 */
	protected String getAttribute(String attributeName) {
		return attributes.get(attributeName);
	}

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
	 * 
	 * @see Model#setAttribute(String, String)
	 */
	private void createAttributeMap() {
		attributes = new HashMap<String, String>();
		String[] attributeNames = getAttributeNames();
		
		for(int i = 0; i < attributeNames.length; i++) {
			attributes.put(attributeNames[i], ""); // Assign empty strings
		}	
	}
	
	/**
	 * Converts the model into a JSON format.
	 * 
	 * @return JSON string representation of this mode.
	 * @deprecated Currently should not be used.
	 */
	public String toJson() {
		StringBuilder json = new StringBuilder();
        json.append("{");
        for (Map.Entry<String, String> entry : this.getAttributeMap().entrySet()) {
            json.append("\"").append(entry.getKey()).append("\":").append(entry.getValue()).append(",");
        }
        json.deleteCharAt(json.length() - 1);
        json.append("}");
        
        return json.toString();
	}
	
	@Override
	public boolean equals(Object obj) {
	    if (obj == this) {
	        return true;
	    }
	    
	    if (!(obj instanceof Model)) {
	        return false;
	    }
	    
	    Model otherItem = (Model) obj;
	    return otherItem.getId() == this.getId();
	}
}
