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
	
	public List<Item> getAll() throws SQLException {
		// Creates list of items
		List<Item> items = new ArrayList<Item>();
		
		ResultSet resultSet;
		String table = Item.table;
		String primaryKeyColumnName = Item.primaryKeyColumnName;
		
		try {
			resultSet = connection.executeSelect(table, null, null);
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
					item.getAttributeMap().put(attribute, value);
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

}
