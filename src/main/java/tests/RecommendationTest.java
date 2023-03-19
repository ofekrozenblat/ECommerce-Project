package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import dao.ItemDao;
import hthurow.tomcatjndi.TomcatJNDI;
import model.Item;

class RecommendationTest {
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
    void testRecommendations() {
		ItemDao itemDao = new ItemDao();
		try {
			//current item
			Item item12 =  itemDao.get(12);
			
			//first item with similar category (Diva)
			Item item4 =  itemDao.get(4);
			//first item with similar brand (Geometric)
			Item item29 =  itemDao.get(29);
			//first item with similar color (Classic)
			Item item1 =  itemDao.get(1);
			//first item with similar price (Diva, but already in list)
			//similar category not empty (Trendsetter)
			Item item8 =  itemDao.get(8);
			
			List<Item> recommendations = item12.getRecommendations();
			
			assertTrue(recommendations.size() == 4);
			
			assertTrue(recommendations.contains(item4));
			assertTrue(recommendations.contains(item29));
			assertTrue(recommendations.contains(item1));
			assertTrue(recommendations.contains(item8));
			
		} catch (SQLException e) {
			e.printStackTrace();
			fail("Exception: " + e.getMessage());
		}
    }
}