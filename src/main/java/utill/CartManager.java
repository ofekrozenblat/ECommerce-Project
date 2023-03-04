package utill;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import dao.ItemDao;
import model.Item;

public class CartManager {

	private Map<Integer, Integer> cart;

	public CartManager() {
		this.cart = new HashMap<Integer, Integer>();
	}

	public void addToCart(int itemId, int quanitity) {

		if (cart.containsKey(itemId)) {
			int currentQuantity = cart.get(itemId);
			cart.put(itemId, currentQuantity + quanitity);
		} else {
			cart.put(itemId, quanitity);
		}
	}

	public void removeFromCart(int itemId) {
		cart.remove(itemId);
	}

	public int getSize() {
		int cartSize = 0;
		for (int quantity : this.cart.values()) {
			cartSize += quantity;
		}
		return cartSize;
	}

	public List<Item> getItems() {
		List<Item> cartItems = new ArrayList<Item>();
		ItemDao itemDao = new ItemDao();
		for (int itemId : this.cart.keySet()) {
			try {
				cartItems.add(itemDao.get(itemId));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("Failed to load cart items: " + e.getMessage());
			}
		}
		return cartItems;
	}

	public List<Integer> getItemQuantities() {
		List<Integer> quantities = new ArrayList<Integer>();
		for (int quantity : this.cart.values()) {
			quantities.add(quantity);
		}
		return quantities;
	}
	
	public double getTotal() {
		
		double total = 0;
		List<Item> items = this.getItems();
		List<Integer> quantities = this.getItemQuantities();
		
		for(int i = 0; i < items.size(); i++) {
			total += (items.get(i).getPrice() * quantities.get(i));
		}
		
		// 2 decimal places
		DecimalFormat df = new DecimalFormat("#.##");
		String formatted = df.format(total);
		total = Double.parseDouble(formatted);
		
		return total;
	}
	
	public double getShipping() {
		double shipping = Math.round(this.getTotal() * 0.05);
		
		// 2 decimal places
		DecimalFormat df = new DecimalFormat("#.##");
		String formatted = df.format(shipping);
		shipping = Double.parseDouble(formatted);	
		
		return shipping;
	}
	
	public int getItemQuantity(int itemId) {
		return cart.get(itemId);
	}
	
	public void clear() {
		cart.clear();
	}
}
