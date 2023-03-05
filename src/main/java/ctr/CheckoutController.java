package ctr;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utill.CartManager;
import utill.SessionManager;

/**
 * Servlet implementation class CheckoutController
 */
@WebServlet("/Checkout")
public class CheckoutController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckoutController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SessionManager sm = (SessionManager) request.getSession().getAttribute(SessionManager.SESSION_MANAGER);
		CartManager cart = sm.getCart();;
		request.setAttribute("total", cart.getTotal());
		request.setAttribute("shipping", cart.getShipping());
		
		String target = "/views/shopping-cart/checkout.jsp"; 
		request.getRequestDispatcher(target).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		SessionManager sm = (SessionManager) request.getSession().getAttribute(SessionManager.SESSION_MANAGER);
		if(sm.isAuth()) {
			response.setHeader("success", "true");
		}else {
			response.setHeader("error", "Need to login");
		}
	}

}
