package tests.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.Test;

import dao.*;
import factories.ModelFactory;
import model.*;

class OrderDaoTest extends DaoTest{

	// requires at least one user in the database
    @Test
    void testCreateAndGet() {
    	Order order = ModelFactory.createOrder();
    	ItemDao itemDao = new ItemDao();
    	PaymentDao paymentDao = new PaymentDao();
    	BillingAddressDao billingAddressDao = new BillingAddressDao();
    	
    	try {
			Item item10 =  itemDao.get(10);
			Item item26 =  itemDao.get(26);
			order.addItem(item10, 1);
			order.addItem(item26, 1);
			order.setTotal(item10.getPrice() + item26.getPrice());
			Payment payment = paymentDao.get(1);
			BillingAddress billingAddress = billingAddressDao.get(1);
			
			order.setBillingAddress(billingAddress);
			order.setPayment(payment);
			order.setUserId(1);
			
			assertTrue(order.getId() == -1);
			
			order.save();
			
			// Check to see that the order was created successfully
			assertTrue(order.getId() > 0);
			
		} catch (SQLException e) {
			e.printStackTrace();
			fail("Exception: " + e.getMessage());
		}
    	
    }
    
	@Test
	void testGet() {
		Order order = this.createDummyOrder();
		int orderId = order.getId();
		try {
			Order orderTest = (new OrderDao()).get(orderId);
			assertEquals(orderTest.getId(), orderId);
			assertTrue(order.getItems().size() == 2);
		} catch (SQLException e) {
			e.printStackTrace();
			fail("Exception: " + e.getMessage());
		}
	}
	
	@Test
	void testGetByUserId() {
		Order order = this.createDummyOrder();
		try {
			List<Order> orders = (new OrderDao()).getOrdersByUserId(1);
			
			boolean hasOrder = false;
			for(Order o: orders) {
				if(o.getId() == order.getId()) {
					hasOrder = true;
				}
			}
			
			assertEquals(hasOrder, true);
			
		} catch (SQLException e) {
			e.printStackTrace();
			fail("Exception: " + e.getMessage());
		}
	}
	
	// Creates a dummy order for user with id = 1
	private Order createDummyOrder() {
		Order order = ModelFactory.createOrder();
    	ItemDao itemDao = new ItemDao();
    	PaymentDao paymentDao = new PaymentDao();
    	BillingAddressDao billingAddressDao = new BillingAddressDao();
    	try {
			Item item10 =  itemDao.get(10);
			Item item26 =  itemDao.get(26);
			order.addItem(item10, 1);
			order.addItem(item26, 1);
			order.setTotal(item10.getPrice() + item26.getPrice());
			Payment payment = paymentDao.get(1);
			BillingAddress billingAddress = billingAddressDao.get(1);
			
			order.setBillingAddress(billingAddress);
			order.setPayment(payment);
			order.setUserId(1);
			
			assertTrue(order.getId() == -1);
			
			order.save();
			
			// Check to see that the order was created successfully
			assertTrue(order.getId() > 0);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	return order;
	}

}
