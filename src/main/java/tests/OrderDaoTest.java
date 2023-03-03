package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.sql.SQLException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import dao.OrderDao;
import hthurow.tomcatjndi.TomcatJNDI;
import model.Order;

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
	void testGet() {
		try {
			Order order = (new OrderDao()).get(2);
			assertEquals(2, order.getId());
		} catch (SQLException e) {
			e.printStackTrace();
			fail("Exception: " + e.getMessage());
		}
	}

}
