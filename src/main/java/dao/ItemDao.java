package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Item;

public class ItemDao extends Dao {

	@Override
	public Item get(int id) throws SQLException {
		// Creates the item
		Item item = new Item(this, id);

		// Gets the item from DB based on primary key
		String table = item.getTable();
		String condition = "id=" + id;
		String[] conditions = { condition };
		ResultSet resultSet;

		try {
			resultSet = connection.executeSelect(table, null, conditions);
		} catch (SQLException e) {
			throw new SQLException("Failed to retreive item with id " + id + ".");
		}

		// Get and set the item attributes
		try {
			while (resultSet.next()) {
				for (String attribute : item.getAttributeMap().keySet()) {
					String value = resultSet.getString(attribute);
					item.getAttributeMap().put(attribute, value);
				}
			}
		} catch (SQLException e) {
			throw new SQLException("Failed to retreive attributes of the item with id " + id + ".");
		}

		return item;
	}
	
	public ArrayList<Item> getAll() throws SQLException {
		// Creates list of items
		ArrayList<Item> items = new ArrayList<Item>();
		
		ResultSet resultSet;
		String table = new Item(this).getTable();
		
		try {
			resultSet = connection.executeSelect(table, null, null);
		} catch (SQLException e) {
			throw new SQLException("Failed to retreive items");
		}

		// Get and set the item attributes
		try {
			while (resultSet.next()) {
				int id = Integer.parseInt(resultSet.getString("id"));
				Item item = new Item(this, id);
				
				for (String attribute : item.getAttributeMap().keySet()) {
					String value = resultSet.getString(attribute);
					item.getAttributeMap().put(attribute, value);
				}
				
				items.add(item);
			}
		} catch (SQLException e) {
			throw new SQLException("Failed to retreive attributes of the items");
		}
		
		return items;
	}

}
