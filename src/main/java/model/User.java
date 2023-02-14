package model;

import dao.Dao;
import dao.UserDao;

/**
 * Represents the user table in the database.
 * 
 * @author ofekr
 *
 */
public class User extends Model {
	
	public User(UserDao dao) {
		super(dao);
	}
	
	public User(UserDao dao, int id) {
		super(dao, id);
	}
	
	public String getTable() {
		return "user";
	}

	protected String[] getAttributeNames() {
		return new String[] {"first_name"};
	}

	// Getters & Setters
	public void setFirstName(String firstName) {
		// Check to make sure method is not used to insert 
		// an attribute not declared in attributeNames
		if (attributes.containsKey("first_name")) {
			attributes.put("first_name", firstName);
		}
	}
	
	public String getFirstName() {
		return attributes.get("first_name");
	}
}
