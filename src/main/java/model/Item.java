package model;

import dao.Dao;
import dao.ItemDao;

public class Item extends Model{

	public static final String table = "items";
	public static final String primaryKeyColumnName = "id";
	
	public Item(Dao dao) {
		super(dao);
	}
	
	public Item(ItemDao dao, int id) {
		super(dao, id);
	}

	@Override
	public String getTable() {
		return table;
	}

	@Override
	public String getPrimaryKeyColumnName() {
		return primaryKeyColumnName;
	}

	@Override
	protected String[] getAttributeNames() {
		return new String[] {"name", "description", "category", "brand", "quantity", "price"};
	}
	
	// Getters & Setters
	public void setName(String name) {
	    setAttribute("name", name);
	}

	public String getName() {
	    return getAttribute("name");
	}

	public void setDescription(String description) {
	    setAttribute("description", description);
	}

	public String getDescription() {
	    return getAttribute("description");
	}

	public void setCategory(String category) {
	    setAttribute("category", category);
	}

	public String getCategory() {
	    return getAttribute("category");
	}

	public void setBrand(String brand) {
	    setAttribute("brand", brand);
	}

	public String getBrand() {
	    return getAttribute("brand");
	}

	public void setQuantity(int quantity) {
	    setAttribute("quantity", String.valueOf(quantity));
	}

	public int getQuantity() {
	    return Integer.parseInt(getAttribute("quantity"));
	}

	public void setPrice(double price) {
	    setAttribute("price", String.valueOf(price));
	}

	public double getPrice() {
		return Double.parseDouble(getAttribute("price"));
	}

}
