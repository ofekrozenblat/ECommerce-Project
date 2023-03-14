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
			
			String cat_condition =  "category=" + this.getCategory();
			String[] cat_conditions = { cat_condition };
			List<Item> similar_category = thisDao.getAll(cat_conditions);
			
			String b_condition =  "brand=" + this.getBrand();
			String[] b_conditions = { b_condition };
			List<Item> similar_brand = thisDao.getAll(b_conditions);
			
			String c_condition =  "colour=" + this.getColor();
			String[] c_conditions = { c_condition };
			List<Item> similar_colour = thisDao.getAll(c_conditions);
			
			double price = this.getPrice();
			String p_condition1 =  "price>" + (price - 50);
			String p_condition2 =  "price<" + (price + 50);
			String[] p_conditions = { p_condition1, p_condition2 };
			List<Item> similar_price = thisDao.getAll(p_conditions);
			
			List<Item> all = thisDao.getAll(null);

			recommendations.add(similar_category.get(0));
			recommendations.add(similar_brand.get(0));
			recommendations.add(similar_colour.get(0));
			recommendations.add(similar_price.get(0));
			
			//Four additional recommendations in case there are not enough similar items
			for (int i = 0; i < 4; i++) {
				recommendations.add(all.get(i));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return recommendations;
	}

}
