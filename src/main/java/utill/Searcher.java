package utill;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Item;
import dao.ItemDao;
public class Searcher {
	
	public static List<String> searchItems(String input){
		List<String> result = new ArrayList<String>();
		List<String> conditions = new ArrayList<String>();
		
		for(String s: input.split("\\s+")) {
			conditions.add("(name LIKE \"%" + s + "%\" or description LIKE \"%" + s + 
					"%\" or category LIKE \"%" + s + "%\" or brand LIKE \"%" + s + "%\" or color LIKE \"%" + s + "%\")");
		}
		
		String [] conditionsArray = new String[conditions.size()];
		
		try {
			List<Item> items = new ItemDao().getAll(conditions.toArray(conditionsArray));
			for(Item item: items) {
				result.add(item.toJson());
				if(result.size() >= 10) {
					return result;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return result;
		}
		return result;
	}
}
