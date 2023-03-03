package utill;
import javax.servlet.http.HttpSession;

/**
 * This class is to help manage the session data used across multiple pages.
 * Session data is tied to a specific user accessing the web application.
 * SessionManager of the user can be accessed through a HttpSession object.
 * @author shach
 *
 */
public class SessionManager{
	
	//Session of the user
	private HttpSession session;
	
	//Session attributes for User
	private final String IS_ADMIN = "is_admin";
	private final String IS_AUTH = "is_auth";
	
	//Session Manager attribute 
	public static final String SESSION_MANAGER = "sessionManager";
	
	public SessionManager(HttpSession session){
		this.session = session;
		this.session.setAttribute(IS_ADMIN, false);
		this.session.setAttribute(IS_AUTH, false);
	}
	
	public void setAuth(boolean auth) {
		this.session.setAttribute(IS_AUTH, auth);
	}
	
	public void setAdmin(boolean admin) {
		this.session.setAttribute(IS_ADMIN, admin);
	}
	
}
