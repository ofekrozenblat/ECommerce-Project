package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import dao.*;
import factories.ModelFactory;
import hthurow.tomcatjndi.TomcatJNDI;
import model.*;

class OrderDaoTest {
	
	private static TomcatJNDI tomcatJNDI;

    @BeforeAll
    static void setup() {
		tomcatJNDI = new TomcatJNDI();
		tomcatJNDI.processContextXml(new File("src\\main\\webapp\\META-INF\\context.xml"));
		tomcatJNDI.processWebXml(new File("src\\main\\webapp\\WEB-INF\\web.xml"));
		tomcatJNDI.start();
    }

    @AfterAll
    static void tearDown() {
    	tomcatJNDI.tearDown();
    }
	
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
