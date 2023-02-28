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
		return "users";
	}

	protected String[] getAttributeNames() {
		return new String[] {"first_name", "last_name", "email_address", "is_admin"};
	}

	// Getters & Setters
	public void setFirstName(String firstName) {
		setAttribute("first_name", firstName);
	}
	
	public String getFirstName() {
		return getAttribute("first_name");
	}
	
	public void setLastName(String lastName) {
		setAttribute("last_name", lastName);
	}
	
	public String getLastName() {
		return getAttribute("last_name");
	}
	
	public void setEmailAddress(String emailAddress) {
		setAttribute("email_address", emailAddress);
	}
	
	public String getEmailAddress() {
		return getAttribute("email_address");
	}
	
	public void setIsAdmin(boolean admin) {
		setAttribute("is_admin", String.valueOf(admin));
	}
	
	public boolean getIsAdmin() {
		return Boolean.parseBoolean(getAttribute("is_admin"));
	}
}
