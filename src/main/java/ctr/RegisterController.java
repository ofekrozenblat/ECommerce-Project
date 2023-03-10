package ctr;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.Authenticator;
import factories.ModelFactory;
import model.User;
import security.Sanitizer;
import utill.SessionManager;

/**
 * Servlet implementation class SignUpController
 */
@WebServlet("/Register")
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String target = "/views/register.jsp"; 
		request.getRequestDispatcher(target).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Sanitize input
		Map<String, String> parameters = Sanitizer.cleanRequestParameters(request);
		
		//register user
		String firstName = parameters.get("firstName");
		String lastName = parameters.get("lastName");
		String email = parameters.get("email");
		String password = parameters.get("password");
		
		User user = ModelFactory.createUser();
		try {
			user.setEmailAddress(email);
			user.setFirstName(firstName);
			user.setLastName(lastName);
			user.setIsAdmin(false);
			user.save();
			
			Authenticator.registerUser(password, user.getId());

			
			SessionManager sm = (SessionManager) request.getSession().getAttribute(SessionManager.SESSION_MANAGER);
			sm.setUserId(user.getId());
			sm.setUsername(user.getFirstName());
			sm.setAuth(true);
			
			response.setHeader("success", "registered user");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			response.setHeader("error", "failed to register");
		}
		
	}

}
