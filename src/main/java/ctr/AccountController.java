package ctr;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import dao.OrderDao;
import model.User;
import model.Order;
import utill.SessionManager;

/**
 * Servlet implementation class AccountController
 */
@WebServlet("/Account")
public class AccountController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AccountController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		SessionManager sm = (SessionManager) request.getSession().getAttribute(SessionManager.SESSION_MANAGER);

		if (!sm.isAuth()) {
			// User is not logged in
			String target = "/views/login.jsp";
			request.getRequestDispatcher(target).forward(request, response);
			return;
		}

		int user_id = sm.getUserId();

		try {
			// User information
			User user = new UserDao().get(user_id);
			request.setAttribute("firstname", user.getFirstName());
			request.setAttribute("lastname", user.getLastName());
			request.setAttribute("email", user.getEmailAddress());

			// User orders
			List<Order> orders = new OrderDao().getOrdersByUserId(user_id);
			request.setAttribute("orders", orders);

		} catch (SQLException e) {
		}

		String target = "/views/user/account.jsp";
		request.getRequestDispatcher(target).forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		SessionManager sm = (SessionManager) request.getSession().getAttribute(SessionManager.SESSION_MANAGER);

		if (!sm.isAuth()) {
			// User is not logged in
			return;
		}

		int user_id = sm.getUserId();

		if (request.getParameter("updateAccountInfo") != null) {
			String firstname = request.getParameter("firstname");
			String lastname = request.getParameter("lastname");
			String email = request.getParameter("email");

			try {
				// User information
				User user = new UserDao().get(user_id);
				user.setFirstName(firstname);
				user.setLastName(lastname);
				user.setEmailAddress(email);
				user.save();
				sm.setUsername(firstname);
				response.setHeader("success", "true");
				return;
			} catch (SQLException e) {
				response.setHeader("error", "failed to update");
				return;
			}
		}

	}

}
