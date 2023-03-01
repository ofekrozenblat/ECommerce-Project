package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
				
		// Gets the review from DB based on primary key
		String table = review.getTable();
		String condition = review.getPrimaryKeyColumnName() + "=" + id;
		String[] conditions = {condition};
		ResultSet resultSet;
		
		try {
			resultSet = connection.executeSelect(table, null, conditions);
		} catch (SQLException e) {
			throw new SQLException("Failed to retreive review with id " + id + ".");
		}
		
		// Get and set the user attributes
		try {
			resultSet.next();
			for(String attribute: review.getAttributeMap().keySet()) {
				String value = resultSet.getString(attribute);
				review.setAttribute(attribute, value);
			}
		} catch (SQLException e) {
			throw new SQLException("Failed to retreive attributes of the review with id " + id + ".");
		}
		
		return review;
	}
	
	/**
	 * Gets all the reviews related to the item with id {@code itemId}.
	 * 
	 * @param itemId id of the item to retrieve reviews for
	 * @return list of {@link Review} objects related to this item
	 * @throws Exception if something went wrong with retrieving the id of the reviews or an SQL
	 * exception has occurred
	 */
	public List<Review> getReviewsByItemId(int itemId) throws Exception{
		// Creates list of items
		List<Review> reviews = new ArrayList<Review>();
		
		ResultSet resultSet = null;
		String table = Review.table;
		String primaryKeyColumnName = Review.primaryKeyColumnName;
		String condition = "item_id = " + itemId;
		String[] conditions = {condition};
		
		try {
			resultSet = connection.executeSelect(table, null, conditions);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Get and set the item attributes
		try {
			while (resultSet.next()) {
				int id = Integer.parseInt(resultSet.getString(primaryKeyColumnName));
				Review review = new Review(this, id);
				
				for (String attribute : review.getAttributeMap().keySet()) {
					String value = resultSet.getString(attribute);
					review.getAttributeMap().put(attribute, value);
				}
				
				reviews.add(review);
			}
		} catch (NumberFormatException e) {
			throw new Exception("Failed to format id: " + e.getMessage());
		} catch (SQLException e) {
			throw new Exception("Failed to get reviews: " + e.getMessage());
		}
	
		return reviews;
	}
	
}
