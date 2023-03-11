package model;

import java.sql.SQLException;
import java.util.ArrayList;

import dao.Dao;
import dao.ItemVisitDao;
import dao.ItemDao;

public class ItemVisit extends Model{
	public static final String table = "item_vists";
	public static final String primaryKeyColumnName = "id";
	
	public ItemVisit(Dao dao) {
		super(dao);
		// TODO Auto-generated constructor stub
	}
	
	public ItemVisit(ItemVisitDao dao, int id) {
		super(dao, id);
	}

	@Override
	public String getPrimaryKeyColumnName() {
		// TODO Auto-generated method stub
		return primaryKeyColumnName;
	}

	@Override
	public String getTable() {
		// TODO Auto-generated method stub
		return table;
	}

	@Override
	protected String[] getAttributeNames() {
		return new String[] { "visits", "item_id"};
	}
	
	public int getVisits() {
		return Integer.parseInt(this.getAttribute("visits"));
	}
	
	public void updateVisits() {
		
		if(this.getAttribute("visits") != null && !this.getAttribute("visits").isEmpty()) {
			int currentVisits = Integer.parseInt(this.getAttribute("visits"));
			currentVisits += 1;
			this.setAttribute("visits", String.valueOf(currentVisits));
		}else {
			this.setAttribute("visits", String.valueOf(1));
		}
		
	}
	
	public void setItemId(int item_id) {
		this.setAttribute("item_id", String.valueOf(item_id));
	}
	
	public int getItemId() {
		return Integer.parseInt(this.getAttribute("item_id"));
	}
	
	public Item getItem() throws SQLException {
		Item item = new ItemDao().get(this.getItemId());
		return item;
	}
	
	public String toJson() {
		StringBuilder json = new StringBuilder();
		json.append("{");

		json.append("\"item_id\":").append("\"").append(this.getItemId()).append("\",");
		json.append("\"visits\":").append("\"").append(this.getVisits()).append("\",");

		json.append("}");

		return json.toString();
	}

}
