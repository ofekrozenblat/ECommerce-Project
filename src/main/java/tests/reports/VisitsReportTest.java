package tests.reports;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.Test;

import dao.ItemVisitDao;
import dao.OrderDao;
import factories.ModelFactory;
import model.ItemVisit;
import model.Order;
import utill.*;

class VisitsReportTest extends AdminReportTest{
	@Test
    void testGetVisits() {
		
		AdminReports report = new AdminReports();
		ItemVisitDao visitDao = new ItemVisitDao();
		try {
			List<String> visitsReport = report.getItemVisits();
			List<ItemVisit> visit = visitDao.getAll(null);
			
			assertTrue(visitsReport.size() == visit.size());
			
			for(String v: visitsReport) {
				assertTrue(v.contains("visits"));	
			}
		} catch (SQLException e) {
			e.printStackTrace();
			fail("Exception: " + e.getMessage());
		}
	}
}