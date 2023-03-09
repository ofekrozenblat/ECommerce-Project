package tests.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.Test;

import dao.*;
import factories.ModelFactory;
import model.*;

class OrderDaoTest extends DaoTest{

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
		try {
			Order order = (new OrderDao()).get(6);
			assertEquals(6, order.getId());
			assertTrue(order.getItems().size() == 2);
		} catch (SQLException e) {
			e.printStackTrace();
			fail("Exception: " + e.getMessage());
		}
	}
	
	@Test
	void testGetByUserId() {
		try {
			List<Order> orders = (new OrderDao()).getOrdersByUserId(1);
			assertTrue(orders.size() == 4);
		} catch (SQLException e) {
			e.printStackTrace();
			fail("Exception: " + e.getMessage());
		}
	}

}
