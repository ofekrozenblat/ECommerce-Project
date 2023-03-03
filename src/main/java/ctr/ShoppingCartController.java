package ctr;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ItemDao;
import model.Item;
import utill.SessionManager;

/**
 * Servlet implementation class ShoppingCartController
 */
@WebServlet("/Cart")
public class ShoppingCartController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String ITEMS_COUNT_PARAM = "number_of_items";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShoppingCartController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		SessionManager sm = (SessionManager) request.getSession().getAttribute(SessionManager.SESSION_MANAGER);
		
		
		String target = "/views/shopping-cart/cart.jsp"; 
		
		request.getSession().setAttribute("number_of_items", sm.cartSize());
		request.getSession().setAttribute("cart_items", sm.getCart());
		
		request.getRequestDispatcher(target).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	
	// these two methods return parallel lists
	private List<Item> getCartItems(HttpServletRequest request) throws ServletException {
		List<Item> cartItems = new ArrayList<Item>();
		SessionManager sm = (SessionManager) request.getSession().getAttribute(SessionManager.SESSION_MANAGER);
		ItemDao itemDao = new ItemDao();
		for(int itemId : sm.getCart().keySet()) {
			try {
				cartItems.add(itemDao.get(itemId));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new ServletException("Failed to load cart items: " + e.getMessage());
			}
		}
		return cartItems;
	}
	
	private List<Integer> getCartItemQuantities(HttpServletRequest request) {
		return null;
	}

}
