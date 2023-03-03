package dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.BillingAddress;
import model.User;

/**
 * The {@code BillingAddressDao} class is in charge of providing the 
 * interfaces for the {@link model.BillingAddress} model
 * to communicate with the database and any additional 
 * database operations related to this model.
 * 
 * @author ofekr
 * @see model.BillingAddress
 * @see Dao
 *
 */
public class BillingAddressDao extends Dao {
	
	@Override
	public BillingAddress get(int id)  throws SQLException {
		BillingAddress billingAddress = new BillingAddress(this, id);
		
		String table = billingAddress.getTable();
		String condition = billingAddress.getPrimaryKeyColumnName() + "=" + id;
		String[] conditions = {condition};
		ResultSet resultSet;
		
		try {
			resultSet = connection.executeSelect(table, null, conditions);
		} catch (SQLException e) {
			throw new SQLException("Failed to retreive billing address with id " + id + ".");
		}
		
		// Get and set the user attributes
		try {
			resultSet.next();
			for(String attribute: billingAddress.getAttributeMap().keySet()) {
				String value = resultSet.getString(attribute);
				billingAddress.setAttribute(attribute, value);
			}
		} catch (SQLException e) {
			throw new SQLException("Failed to retreive attributes of the billing address with id " + id + ".");
		}
		
		return billingAddress;
	}
	
}
