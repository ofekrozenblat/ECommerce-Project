package model;

import dao.Dao;

public class Order extends Model {

	public static final String table = "orders";
	public static final String primaryKeyColumnName = "id";
	
	public Order(Dao dao) {
		super(dao);
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
		return new String[] {"total", "user_id", "billing_address_id", "payment_id"};
	}

}
