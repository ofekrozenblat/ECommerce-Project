package model;

import dao.PaymentDao;

public class Payment extends Model {

	public static final String table = "payments";
	public static final String primaryKeyColumnName = "id";
	
	public Payment(PaymentDao dao) {
		super(dao);
	}
	
	public Payment(PaymentDao dao, int id) {
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
		return new String[] {"payment_type", "credit_card_name", "credit_card_number", 
				"credit_card_expiration", "credit_card_cv"};
	}
	
	// Getters & Setters
	public void setPaymentType(String street) {
		setAttribute("payment_type", street);
	}
	
	public String getPaymentType() {
		return getAttribute("payment_type");
	}
	
	public void setCreditCardName(String creditCardName) {
	    setAttribute("credit_card_name", creditCardName);
	}

	public String getCreditCardName() {
	    return getAttribute("credit_card_name");
	}

	public void setCreditCardNumber(String creditCardNumber) {
	    setAttribute("credit_card_number", creditCardNumber);
	}

	public String getCreditCardNumber() {
	    return getAttribute("credit_card_number");
	}

	public void setCreditCardExpiration(String creditCardExpiration) {
	    setAttribute("credit_card_expiration", creditCardExpiration);
	}

	public String getCreditCardExpiration() {
	    return getAttribute("credit_card_expiration");
	}

	public void setCreditCardCv(String creditCardCv) {
	    setAttribute("credit_card_cv", creditCardCv);
	}

	public String getCreditCardCv() {
	    return getAttribute("credit_card_cv");
	}

}
