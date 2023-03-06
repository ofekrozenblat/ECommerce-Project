package model;

import dao.BillingAddressDao;

public class BillingAddress extends Model {

	public static final String table = "billing_addresses";
	public static final String primaryKeyColumnName = "id";
	
	public BillingAddress(BillingAddressDao dao) {
		super(dao);
	}
	
	public BillingAddress(BillingAddressDao dao, int id) {
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
		return new String[] {"street", "province", "country", "postal_code"};
	}
	
	// Getters & Setters
	public void setStreet(String street) {
		setAttribute("street", street);
	}
	
	public String getStreet() {
		return getAttribute("street");
	}
	
	public void setProvince(String province) {
	    setAttribute("province", province);
	}

	public String getProvince() {
	    return getAttribute("province");
	}

	public void setCountry(String country) {
	    setAttribute("country", country);
	}

	public String getCountry() {
	    return getAttribute("country");
	}

	public void setPostalCode(String postalCode) {
	    setAttribute("postal_code", postalCode);
	}

	public String getPostalCode() {
	    return getAttribute("postal_code");
	}
	
	public String toString() {
		String result = this.getStreet() + ", " + this.getProvince() + " " + this.getPostalCode() + " " + this.getCountry();
		return result;
	}

}
