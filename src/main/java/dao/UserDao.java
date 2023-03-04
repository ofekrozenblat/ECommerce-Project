package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import model.User;

/**
 * The {@code UserDao} class is in charge of providing the interfaces for the {@link model.User} model
 * to communicate with the database and any additional database operations related to this model.
 * 
 * @author ofekr
 * @see model.User
 * @see Dao
 *
 */
public class UserDao extends Dao {
	
	@Override
	public User get(int id)  throws SQLException {
		// Creates the user
		User user = new User(this, id);
				
		// Gets the user from DB based on primary key
		String table = user.getTable();
		String condition = user.getPrimaryKeyColumnName() + "=" + id;
		String[] conditions = {condition};
		ResultSet resultSet;
		
		try {
			resultSet = connection.executeSelect(table, null, conditions);
		} catch (SQLException e) {
			throw new SQLException("Failed to retreive user with id " + id + ".");
		}
		
		// Get and set the user attributes
		try {
			resultSet.next();
			for(String attribute: user.getAttributeMap().keySet()) {
				String value = resultSet.getString(attribute);
				user.setAttribute(attribute, value);
			}
		} catch (SQLException e) {
			throw new SQLException("Failed to retreive attributes of the user with id " + id + ".");
		}
		
		return user;
	}
	
	public String getStoredPassword(int userId) throws SQLException {
		String storedPassword = "";
		String table = "passwords";
		String condition = "user_id" + "=" + userId;
		String[] conditions = {condition};
		ResultSet resultSet;
		
		try {
			resultSet = connection.executeSelect(table, null, conditions);
		} catch (SQLException e) {
			throw new SQLException("Failed to retreive password for user with id " + userId
					+ ": " + e.getMessage());
		}
		
		try {
			resultSet.next();
			storedPassword = resultSet.getString("password");
		} catch (SQLException e) {
			throw new SQLException("No password attribute for user with id " + userId + " found"
					+ ": " + e.getMessage());
		}
		
		return storedPassword;
	}
	
	public void storePassword(String password, int userId) throws SQLException {
		String table = "passwords";
		String primaryKeyColumn = "id";
		Map<String, String> attributes = new HashMap<String, String>();
		attributes.put("password", password);
		attributes.put("user_id", String.valueOf(userId));
		
		// Executes the insert and gets the ID of the new primary key
		int retrievedPrimaryKey = connection.executeInsert(table, primaryKeyColumn, attributes);
		
		if (retrievedPrimaryKey == -1) {
			throw new SQLException("Failed to store password for user with id " + userId);
		}
	}
	
	// Can create other specific methods such as getAllUsers(), getUsersBy(...), etc..
	
}
