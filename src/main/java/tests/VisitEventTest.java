package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.sql.SQLException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import dao.ItemVisitDao;
import factories.ModelFactory;
import hthurow.tomcatjndi.TomcatJNDI;
import model.ItemVisit;

class VisitEventTest {
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
    void testVisitEvent() {
		int item_id = 1;
		try {
			int initialVisits = 0;
			int updateVisits = 0;
			ItemVisit itemVisit = new ItemVisitDao().getByItemId(item_id);
			initialVisits = itemVisit.getVisits();
			itemVisit.updateVisits();
			itemVisit.save();
			updateVisits = itemVisit.getVisits();
			
			assertEquals(updateVisits, (initialVisits + 1));
		} catch (SQLException e) {
			int initialVisits = 0;
			int updateVisits = 0;
			ItemVisit itemVisit = ModelFactory.createItemVisit();
			itemVisit.setItemId(item_id);
			initialVisits = itemVisit.getVisits();
			itemVisit.updateVisits();
			try {
				itemVisit.save();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			assertEquals(updateVisits, (initialVisits + 1));
		}
	}
}