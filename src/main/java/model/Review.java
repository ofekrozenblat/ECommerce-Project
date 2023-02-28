package model;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import dao.Dao;

public class Review extends Model {

	public static final String table = "reviews";
	public static final String primaryKeyColumnName = "id";
	
	public Review(Dao dao) {
		super(dao);
		
		// Set the date of this review to the current date it was created
		setDate(Calendar.getInstance());
	}
	
	public Review(Dao dao, int id) {
		super(dao, id);
	}

	@Override
	public String getPrimaryKeyColumnName() {
		return primaryKeyColumnName;
	}

	@Override
	public String getTable() {
		return table;
	}

	@Override
	protected String[] getAttributeNames() {
		return new String[] {"rating", "description", "date", "item_id", "user_id"};
	}
	
	// Getters & Setters
	public void setRating(int rating) {
	    setAttribute("name", String.valueOf(rating));
	}

	public int getRating() {
	    return Integer.parseInt(getAttribute("rating"));
	}
	
	public void setDescription(String description) {
	    setAttribute("description", description);
	}

	public String getDescription() {
	    return getAttribute("description");
	}
	
	public void setDate(Calendar calendar) {
		setAttribute("date", calendar.toString());
	}
	
	public Calendar getDate() throws ParseException {
		String dateString = getAttribute("date");
		return parseStringToCalendar(dateString);
	}
	
	public void setItemId(int ItemId) {
		setAttribute("item_id", String.valueOf(ItemId));
	}
	
	public int getItemId() {
		return Integer.parseInt(getAttribute("item_id"));
	}
	
	public void setUserId(int userId) {
		setAttribute("user_id", String.valueOf(userId));
	}
	
	public int getUserId() {
		return Integer.parseInt(getAttribute("user_id"));
	}
	
	private Calendar parseStringToCalendar(String date) throws ParseException {
		DateFormat df = DateFormat.getDateInstance();
		Date d = df.parse(date);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(d);
		return calendar;
	}

}
