package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Item;
import model.Review;

public class ItemDao extends Dao {

	@Override
	public Item get(int id) throws SQLException {
		// Creates the item
		Item item = new Item(this, id);

		// Gets the item from DB based on primary key
		String table = item.getTable();
		String condition = item.getPrimaryKeyColumnName() + "=" + id;
		String[] conditions = { condition };
		ResultSet resultSet;

		try {
			resultSet = connection.executeSelect(table, null, conditions);
		} catch (SQLException e) {
			throw new SQLException("Failed to retreive item with id " + id + ".");
		}

		// Get and set the item attributes
		try {
			resultSet.next();
			for (String attribute : item.getAttributeMap().keySet()) {
				String value = resultSet.getString(attribute);
				item.setAttribute(attribute, value);
			}
			
			setReviewsForItem(item);
			
		} catch (SQLException e) {
			throw new SQLException("Failed to retreive attributes of the item with id " + id + ".");
		}catch(Exception e) {
			throw new SQLException(e.getMessage());
		}

		return item;
	}
	
	public List<Item> getAll(String[] conditions) throws SQLException {
		// Creates list of items
		List<Item> items = new ArrayList<Item>();
		
		ResultSet resultSet;
		String table = Item.table;
		String primaryKeyColumnName = Item.primaryKeyColumnName;
		
		try {
			resultSet = connection.executeSelect(table, null, conditions);
		} catch (SQLException e) {
			throw new SQLException("Failed to retreive items");
		}

		// Get and set the item attributes
		try {
			while (resultSet.next()) {
				int id = Integer.parseInt(resultSet.getString(primaryKeyColumnName));
				Item item = new Item(this, id);
				
				for (String attribute : item.getAttributeMap().keySet()) {
					String value = resultSet.getString(attribute);
					item.setAttribute(attribute, value);
				}
				setReviewsForItem(item);
				items.add(item);
			}
		} catch (SQLException e) {
			throw new SQLException("Failed to retreive attributes of the items");
		}catch(Exception e) {
			throw new SQLException(e.getMessage());
		}
		
		return items;
	}
	
	private void setReviewsForItem(Item item) throws Exception{
		List<Review> reviews = new ReviewDao().getReviewsByItemId(item.getId());
		item.setReviews(reviews);
	}
	
	public List<String> getFilters(String filter_type) throws SQLException {
		// Gets the list of filters based on the type
		
		List<String> filters = new ArrayList<String>();
		String table = Item.table;
		String[] columns = { filter_type };
		ResultSet resultSet;

		try {
			resultSet = connection.executeSelect(table, columns, null);
		} catch (SQLException e) {
			throw new SQLException("Failed to retreive list of filters  with type " + filter_type + ".");
		}
		
		// Add the filters to the List
		try {
			while (resultSet.next()) {
				String filter = resultSet.getString(filter_type);
				filters.add(filter);
			}
		} catch (SQLException e) {
			throw new SQLException("Failed to retreive filters of the items");
		}catch(Exception e) {
			throw new SQLException(e.getMessage());
		}
		
		return filters;
	}
	
	public List<String> getPriceFilters(String filter_type) throws SQLException {
		// Gets the list of filters based on the type
		
		List<String> filters = new ArrayList<String>();
		String table = Item.table;
		String[] columns = { filter_type };
		ResultSet resultSet;

		try {
			resultSet = connection.executeSelect(table, columns, null);
		} catch (SQLException e) {
			throw new SQLException("Failed to retreive list of filters  with type " + filter_type + ".");
		}
		
		// Add the filters to the List
		try {
			int min = 0;
			int max = 0;
			int price = 0;
			while (resultSet.next()) {
				price = Integer.parseInt(resultSet.getString(filter_type));
				if(price > max) {
					max = price;
				} else if(price < min) {
					min = price;
				}
			}
			filters.add("" + max);
			filters.add("" + min);
		} catch (SQLException e) {
			throw new SQLException("Failed to retreive filters of the items");
		}catch(Exception e) {
			throw new SQLException(e.getMessage());
		}
		
		return filters;
	}

}
