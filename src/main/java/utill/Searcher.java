package utill;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Item;
import dao.ItemDao;
public class Searcher {
	
	public static List<String> searchItems(String input){
		List<String> result = new ArrayList<String>();
		
		String condition = "name LIKE \"%" + input + "%\" or description LIKE \"%" + input + 
				"%\" or category LIKE \"%" + input + "%\" or brand LIKE \"%" + input + "%\" or color LIKE \"%" + input + "%\"";
		
		String [] conditions = {condition};
		
		try {
			List<Item> items = new ItemDao().getAll(conditions);
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
