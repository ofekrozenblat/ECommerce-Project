package tests.reports;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.Test;

import dao.OrderDao;
import model.Order;
import utill.*;

class SalesReportTest extends AdminReportTest{
	@Test
    void testGetOrders() {
		
		AdminReports report = new AdminReports();
		OrderDao orderDao = new OrderDao();
		try {
			List<String> salesReport = report.getOrders();
			List<Order> orders = orderDao.getAll();
			
			assertTrue(salesReport.size() == orders.size());
			
			for(String s: salesReport) {
				assertTrue(s.contains("Placed on"));	
			}
		} catch (SQLException e) {
			e.printStackTrace();
			fail("Exception: " + e.getMessage());
		}
	}
}