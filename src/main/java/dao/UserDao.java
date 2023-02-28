package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
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
	
	public UserDao() {
		
	}
	
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
	
	// Can create other specific methods such as getAllUsers(), getUsersBy(...), etc..
	
}
