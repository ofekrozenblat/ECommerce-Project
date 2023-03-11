package dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.Model;
import model.Item;
import model.ItemVisit;;

public class ItemVisitDao extends Dao {

	@Override
	public ItemVisit get(int id) throws SQLException {

		ItemVisit itemVisit = new ItemVisit(this, id);
		String table = itemVisit.getTable();
		String condition = itemVisit.getPrimaryKeyColumnName() + "=" + id;
		String[] conditions = { condition };
		ResultSet resultSet;

		try {
			resultSet = connection.executeSelect(table, null, conditions);
		} catch (SQLException e) {
			throw new SQLException("Failed to retreive item with id " + id + ".");
		}

		try {
			resultSet.next();
			for (String attribute : itemVisit.getAttributeMap().keySet()) {
				String value = resultSet.getString(attribute);
				itemVisit.setAttribute(attribute, value);
			}

		} catch (SQLException e) {
			throw new SQLException("Failed to retreive attributes of the item with id " + id + ".");
		} catch (Exception e) {
			throw new SQLException(e.getMessage());
		}

		return itemVisit;
	}
	
	public ItemVisit getByItemId(int item_id) throws SQLException {
		ItemVisit itemVisit = null;
		String table = ItemVisit.table;
		String condition = "item_id = " + item_id;
		String[] conditions = { condition };
		ResultSet resultSet;

		try {
			resultSet = connection.executeSelect(table, null, conditions);
		} catch (SQLException e) {
			throw new SQLException("Failed to retreive item with item_id " + item_id + ".");
		}

		try {
			resultSet.next();
			int item_visit_id = Integer.parseInt(resultSet.getString("id"));
			itemVisit = new ItemVisit(this, item_visit_id);
			for (String attribute : itemVisit.getAttributeMap().keySet()) {
				String value = resultSet.getString(attribute);
				itemVisit.setAttribute(attribute, value);
			}

		} catch (SQLException e) {
			throw new SQLException("Failed to retreive attributes of the item with item_id " + item_id + ".");
		} catch (Exception e) {
			throw new SQLException(e.getMessage());
		}

		return itemVisit;
	}

}
