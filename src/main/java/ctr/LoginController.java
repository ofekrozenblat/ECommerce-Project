package ctr;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.Authenticator;
import dao.UserDao;
import factories.ModelFactory;
import model.User;
import utill.SessionManager;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/Login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String target = "/views/login.jsp"; 
		request.getRequestDispatcher(target).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//current session
		SessionManager sm = (SessionManager) request.getSession().getAttribute(SessionManager.SESSION_MANAGER);
		
		//login user
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		User user;
		try {
			
			user = new UserDao().getByEmail(email);
			
			try {
				if(Authenticator.validateUser(password, user.getId())) {
					sm.setUserId(user.getId());
					sm.setUsername(user.getFirstName());
					sm.setAuth(true);
				}else {
					response.setHeader("error", "Failed to login, check credentials");
					return;
				}
			} catch (Exception e) {
				response.setHeader("error", "Failed to login, check credentials");
				return;
			}
			
			sm.setUserId(user.getId());
			sm.setUsername(user.getFirstName());
			sm.setAuth(true);
			
			response.setHeader("success", "user logged in");
		} catch (SQLException e1) {
			response.setHeader("error", "Failed to login, check credentials");
			return;
		}
		
	}

}
