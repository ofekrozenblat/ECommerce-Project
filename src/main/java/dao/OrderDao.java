package dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.BillingAddress;
import model.Item;
import model.Order;
import model.Payment;
import model.User;

/**
 * The {@code UserDao} class is in charge of providing the interfaces for the
 * {@link model.User} model to communicate with the database and any additional
 * database operations related to this model.
 * 
 * @author ofekr
 * @see model.User
 * @see Dao
 *
 */
public class OrderDao extends Dao {

	@Override
	public Order get(int id) throws SQLException {
		// Creates the user
		Order order = new Order(this, id);

		// Gets the user from DB based on primary key
		String table = order.getTable();
		String condition = order.getPrimaryKeyColumnName() + "=" + id;
		String[] conditions = { condition };
		ResultSet resultSet;

		try {
			resultSet = connection.executeSelect(table, null, conditions);
		} catch (SQLException e) {
			throw new SQLException("Failed to retreive payment with id " + id + ".");
		}

		// Get and set the user attributes
		try {
			resultSet.next();
			for (String attribute : order.getAttributeMap().keySet()) {
				String value = resultSet.getString(attribute);
				order.setAttribute(attribute, value);
			}
		} catch (SQLException e) {
			throw new SQLException("Failed to retreive attributes of the payment with id " + id + ".");
		}

		try {
			setPayment(order);
		} catch (SQLException e) {
			throw new SQLException("Failed to get payment of order with id " + id + ": " + e.getMessage());
		}

		try {
			setBillingAddress(order);
		} catch (SQLException e) {
			throw new SQLException("Failed to get billing address of order with id " + id + ": " + e.getMessage());
		}
		
		try {
			setOrderItems(order);
		} catch (SQLException e) {
			throw new SQLException("Failed to get items of order with id " + id + ": " + e.getMessage());
		}

		return order;
	}

	private void setOrderItems(Order order) throws SQLException {
		// Gets the user from DB based on primary key
		int id = order.getId();
		String table = Order.orderItemsTable;
		String condition = "order_id" + "=" + id;
		String[] conditions = { condition };
		ResultSet resultSet;

		try {
			resultSet = connection.executeSelect(table, null, conditions);
		} catch (SQLException e) {
			throw new SQLException("Failed to retreive order items of order with id " + id + ".");
		}

		// Get and set the user attributes
		ItemDao itemDao = new ItemDao();
		try {
			while(resultSet.next()) {
				String item_id = resultSet.getString("item_id");
				Item item = itemDao.get(Integer.parseInt(item_id));
				order.addItem(item);
			}
		} catch (SQLException e) {
			throw new SQLException("Failed to set items of order with id " + id + ".");
		}
	}

	private void setPayment(Order order) throws SQLException {
		Payment payment = (new PaymentDao()).get(order.getPaymentId());
		order.setPayment(payment);
	}

	private void setBillingAddress(Order order) throws SQLException {
		BillingAddress billingAddress = (new BillingAddressDao()).get(order.getBillingAddressId());
		order.setBillingAddress(billingAddress);
	}

}
