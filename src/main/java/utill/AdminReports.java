package utill;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import dao.ItemVisitDao;
import dao.OrderDao;
import model.Item;
import model.ItemVisit;
import model.Order;

public class AdminReports {
	
	/**
	 * Gets the visit page count for each item 
	 * @return a list of Json objects where each Json objects contains an item_id and visits attribute
	 */
	public List<String> getItemVisits(){
		List<String> data = new ArrayList<String>();
		
		try {
			List<ItemVisit> itemVisits = new ItemVisitDao().getAll(null);
			for(ItemVisit o: itemVisits) {
				data.add(o.toJson());
			}
		} catch (SQLException e) {
			return data;
		}
		
		return data;
	}
	
	public List<String> getOrders(){
		List<String> data = new ArrayList<String>();
		try {
			List<Order> orders = new OrderDao().getAll();
			for(Order o: orders) {
				data.add(o.toJson());
			}
		} catch (SQLException e) {
			return data;
		}
		return data;
	}
}
