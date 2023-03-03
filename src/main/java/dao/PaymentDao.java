package dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.BillingAddress;
import model.Payment;
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
public class PaymentDao extends Dao {
	
	@Override
	public Payment get(int id)  throws SQLException {
		// Creates the user
		Payment payment = new Payment(this, id);
				
		// Gets the user from DB based on primary key
		String table = payment.getTable();
		String condition = payment.getPrimaryKeyColumnName() + "=" + id;
		String[] conditions = {condition};
		ResultSet resultSet;
		
		try {
			resultSet = connection.executeSelect(table, null, conditions);
		} catch (SQLException e) {
			throw new SQLException("Failed to retreive payment with id " + id + ".");
		}
		
		// Get and set the user attributes
		try {
			resultSet.next();
			for(String attribute: payment.getAttributeMap().keySet()) {
				String value = resultSet.getString(attribute);
				payment.setAttribute(attribute, value);
			}
		} catch (SQLException e) {
			throw new SQLException("Failed to retreive attributes of the payment with id " + id + ".");
		}
		
		return payment;
	}
	
}
