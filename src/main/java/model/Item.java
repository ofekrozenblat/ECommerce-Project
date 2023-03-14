package model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import dao.ItemDao;

public class Item extends Model {

	public static final String table = "items";
	public static final String primaryKeyColumnName = "id";

	/** List of reviews related to this item **/
	private List<Review> reviews;

	public Item(ItemDao dao) {
		super(dao);
		reviews = new ArrayList<Review>();
	}

	public Item(ItemDao dao, int id) {
		super(dao, id);
		reviews = new ArrayList<Review>();
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
		return new String[] { "name", "description", "category", "brand", "quantity", "price", "color" };
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

	public void setColor(String color) {
		setAttribute("color", color);
	}

	public String getColor() {
		return getAttribute("color");
	}

	public void addReview(Review review) {
		this.reviews.add(review);
	}

	public List<Review> getReviews() {
		return this.reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	public int getRating() {
		int rating = 0;

		List<Review> reviews = this.getReviews();

		for (Review review : reviews) {
			rating += review.getRating();
		}

		rating = (rating / Math.max(1, reviews.size()));
		return rating;
	}

	// Dummy method for now, need to implement proper logic for recommendations
	public List<Item> getRecommendations() {
		List<Item> recommendations = new ArrayList<Item>();
		try {
			ItemDao thisDao = (ItemDao) this.dao;
			
			String catCondition =  "category=" + this.getCategory();
			String[] catConditions = { catCondition };
			List<Item> similarCategory = thisDao.getAll(catConditions);
			
			String bCondition =  "brand=" + this.getBrand();
			String[] bConditions = { bCondition };
			List<Item> similarBrand = thisDao.getAll(bConditions);
			
			String cCondition =  "colour=" + this.getColor();
			String[] cConditions = { cCondition };
			List<Item> similarColour = thisDao.getAll(cConditions);
			
			double price = this.getPrice();
			String pCondition1 =  "price>" + (price - 50);
			String pCondition2 =  "price<" + (price + 50);
			String[] pConditions = { pCondition1, pCondition2 };
			List<Item> similarPrice = thisDao.getAll(pConditions);
			
			List<Item> all = thisDao.getAll(null);
			
			//Do not recommend current item
			similarCategory.removeIf(item -> item.getId() == this.getId());
			similarBrand.removeIf(item -> item.getId() == this.getId());
			similarColour.removeIf(item -> item.getId() == this.getId());
			similarPrice.removeIf(item -> item.getId() == this.getId());
			all.removeIf(item -> item.getId() == this.getId());
			
			while (recommendations.size() < 4) {
				
	            if (similarCategory.isEmpty() && similarBrand.isEmpty() && similarColour.isEmpty() && similarPrice.isEmpty()) {
	                
	            	for (Item item : all) {
	            		if(item.getId() != this.getId()) {
	            			recommendations.add(item);
	            			if(recommendations.size() == 4) break;
	            		}
	    			}
	            	
	            	break;
	            }
	            
	            if (!similarCategory.isEmpty()) {
	                Item item = similarCategory.remove(0);
	                if (!recommendations.contains(item)) {
	                    recommendations.add(item);
	                }
	            }
	            if (!similarBrand.isEmpty()) {
	                Item item = similarBrand.remove(0);
	                if (!recommendations.contains(item)) {
	                    recommendations.add(item);
	                }
	            }
	            if (!similarColour.isEmpty()) {
	                Item item = similarColour.remove(0);
	                if (!recommendations.contains(item)) {
	                    recommendations.add(item);
	                }
	            }
	            if (!similarPrice.isEmpty()) {
	                Item item = similarPrice.remove(0);
	                if (!recommendations.contains(item)) {
	                    recommendations.add(item);
	                }
	            }
	            
			}
			
			
			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return recommendations;
	}

}
