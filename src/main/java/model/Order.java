package model;

import java.util.ArrayList;
import java.util.List;

import dao.OrderDao;

public class Order extends Model {

	public static final String table = "orders";
	public static final String primaryKeyColumnName = "id";
	
	/** The relationship table between this order and items (ONE-TO-MANY) **/
	public static final String orderItemsTable = "order_items";
	
	private Payment payment;
	private BillingAddress billingAddress;
	
	/** Items related to this order. Represents the relationship table order_items**/
	private List<Item> orderItems;
	
	public Order(OrderDao dao) {
		super(dao);
		orderItems = new ArrayList<Item>();
	}
	
	public Order(OrderDao dao, int id) {
		super(dao, id);
		orderItems = new ArrayList<Item>();
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
	
	/**
	 * Will re-calculate the total of the order based on the items
	 * in this order and set the calculated total to the total of this order.
	 */
	public void calculateTotal() {
		double total = 0.0;
		for(Item item : this.orderItems) {
			total += item.getPrice();
		}
		
		setTotal(total);
	}
	
	public void setTotal(double total) {
	    setAttribute("total", String.valueOf(total));
	}

	public double getTotal() {
	    return Double.parseDouble(getAttribute("total"));
	}

	public void setUserId(int userId) {
	    setAttribute("user_id", String.valueOf(userId));
	}

	public int getUserId() {
	    return Integer.parseInt(getAttribute("user_id"));
	}

	public void setBillingAddressId(int billingAddressId) {
	    setAttribute("billing_address_id", String.valueOf(billingAddressId));
	}

	public int getBillingAddressId() {
	    return Integer.parseInt(getAttribute("billing_address_id"));
	}

	public void setPaymentId(int paymentId) {
	    setAttribute("payment_id", String.valueOf(paymentId));
	}

	public int getPaymentId() {
	    return Integer.parseInt(getAttribute("payment_id"));
	}
	
	public void setPayment(Payment payment) {
		this.payment = payment;
		setPaymentId(payment.getId());
	}
	
	public Payment getPayment() {
		return this.payment;
	}
	
	public void setBillingAddress(BillingAddress billingAddress) {
		this.billingAddress = billingAddress;
		setBillingAddressId(billingAddress.getId());
	}
	
	public BillingAddress getBillingAddress() {
		return this.billingAddress;
	}
	
	public void addItem(Item item) {
		this.orderItems.add(item);
	}
	
	public List<Item> getItems(){
		return this.orderItems;
	}

}
