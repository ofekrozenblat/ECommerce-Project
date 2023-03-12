package ctr;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utill.AdminReports;
import utill.SessionManager;

/**
 * Servlet implementation class AdminController
 */
@WebServlet("/Admin")
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String REQ_REPORT_SALES = "sales";
	private static final String REQ_REPORT_VISITS = "visits";
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SessionManager sm = (SessionManager) request.getSession().getAttribute(SessionManager.SESSION_MANAGER);

		if(!sm.isAdmin()) {
			String location = "Home";
			response.sendRedirect(location);
			return;
		}
		
		
		String target = "/views/admin/admin-home.jsp"; 
		request.setAttribute("username", sm.getUsername());
		request.getRequestDispatcher(target).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AdminReports adminReports = new AdminReports();
		
		if(request.getParameter(REQ_REPORT_VISITS) != null) {
			 PrintWriter out = response.getWriter();
			 out.println(adminReports.getItemVisits());
			 out.close();
		}
		
		if(request.getParameter(REQ_REPORT_SALES) != null) {
			 PrintWriter out = response.getWriter();
			 out.println(adminReports.getOrders());
			 out.close();
		}
	}

}
