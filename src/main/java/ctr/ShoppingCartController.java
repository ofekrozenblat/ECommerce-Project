package ctr;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ItemDao;
import model.Item;
import utill.CartManager;
import utill.SessionManager;

/**
 * Servlet implementation class ShoppingCartController
 */
@WebServlet("/Cart")
public class ShoppingCartController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
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
		
		
		String target = "/views/shopping-cart/cart.jsp"; 
		
		// parallel lists
		SessionManager sm = (SessionManager) request.getSession().getAttribute(SessionManager.SESSION_MANAGER);
		CartManager cart = sm.getCart();;
		
		request.setAttribute("cart_size", cart.getSize());
		request.setAttribute("cart_items", cart.getItems());
		request.setAttribute("cart_item_quantities", cart.getItemQuantities());
		request.setAttribute("total", cart.getTotal());
		request.setAttribute("shipping", cart.getShipping());
		
		request.getRequestDispatcher(target).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SessionManager sm = (SessionManager) request.getSession().getAttribute(SessionManager.SESSION_MANAGER);
		CartManager cart = sm.getCart();
		
		if(request.getParameter("editCart") != null) {
			int item_id = Integer.parseInt(request.getParameter("item_id"));
			int editValue = Integer.parseInt(request.getParameter("edit_value"));
			
			try {
				Item item = new ItemDao().get(item_id);
				
				// If the edit quantity of the item in the cart reaches
				// 0 or goes above the item's available stock, return and do not 
				// make this adjustment 
				if(cart.getItemQuantity(item_id) + editValue == 0 ||
						cart.getItemQuantity(item_id) + editValue > item.getQuantity()) {
					return;
				}
				

				cart.addToCart(item_id, editValue);
				response.setHeader("Success", "success");
			} catch (SQLException e) {
				return;
			}
		}
		
		if(request.getParameter("deleteFromCart") != null) {
			int item_id = Integer.parseInt(request.getParameter("item_id"));
			cart.removeFromCart(item_id);
		}
	}
}
