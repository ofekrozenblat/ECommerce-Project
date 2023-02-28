package dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import factories.ModelFactory;
import model.Review;

/**
 * The {@code ReviewDao} class is in charge of providing the interfaces 
 * for the {@link model.Review} model to communicate with the database 
 * and any additional database operations related to this model.
 * 
 * @author ofekr
 * @see model.Review
 * @see Dao
 *
 */
public class ReviewDao extends Dao {
	
	public ReviewDao() {
		
	}
	
	@Override
	public Review get(int id)  throws SQLException {
		// Creates the user
		Review review = new Review(this, id);
				
		// Gets the user from DB based on primary key
		String table = review.getTable();
		String condition = review.getPrimaryKeyColumnName() + "=" + id;
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
			for(String attribute: review.getAttributeMap().keySet()) {
				String value = resultSet.getString(attribute);
				review.setAttribute(attribute, value);
			}
		} catch (SQLException e) {
			throw new SQLException("Failed to retreive attributes of the user with id " + id + ".");
		}
		
		return review;
	}
	
	// Can create other specific methods such as getAllUsers(), getUsersBy(...), etc..
	
}
