package utill;

import java.util.HashMap;
import java.util.Map;


/**
 * This class is to help manage the session data used across multiple pages.
 * Session data is tied to a specific user accessing the web application.
 * SessionManager of the user can be accessed through a HttpSession object.
 * @author shach
 *
 */
public class SessionManager{

	private boolean isAuth;
	private boolean isAdmin;
	
	//Session Manager attribute 
	public static final String SESSION_MANAGER = "sessionManager";
	
	//User's shopping cart
	private CartManager cart;
	
	public SessionManager(){
		this.isAuth = true;
		this.isAdmin = false;
		this.cart = new CartManager();
	}
	
	public void setAuth(boolean auth) {
		this.isAuth = auth;
	}
	
	public void setAdmin(boolean admin) {
		this.isAdmin = admin;
	}
	
	public boolean isAuth() {
		return this.isAuth;
	}
	
	public boolean isAdmin() {
		return this.isAdmin;
	}
	
	public CartManager getCart() {
		return this.cart;
	}
	
}
