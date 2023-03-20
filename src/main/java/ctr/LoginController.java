package ctr;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import factories.ModelFactory;
import model.User;
import security.Authenticator;
import security.Sanitizer;
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
		Map<String, String> sanatizedParams = Sanitizer.cleanRequestParameters(request);
		String email = sanatizedParams.get("email");
		String password = sanatizedParams.get("password");
		
		User user;
		try {
			
			// Get user from database, if doesn't exist then email credentials were wrong
			user = new UserDao().getByEmail(email);
			
			// Validate password 
			try {
				
				// if email & password match, log in user
				if(Authenticator.validateUser(password, user.getId())) {
					sm.setUserId(user.getId());
					sm.setUsername(user.getFirstName());
					sm.setAuth(true);
					sm.setAdmin(user.getIsAdmin());
				}else {
					response.setHeader("error", "Failed to login, check credentials");
					return;
				}
			} catch (Exception e) {
				response.setHeader("error", "Failed to login, check credentials");
				return;
			}
			
			response.setHeader("success", "user logged in");
		} catch (SQLException e1) {
			response.setHeader("error", "Failed to login, check credentials");
			return;
		}
		
	}

}
