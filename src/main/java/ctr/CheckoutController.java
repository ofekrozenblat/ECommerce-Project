package ctr;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import factories.ModelFactory;
import utill.CartManager;
import utill.SessionManager;
import model.BillingAddress;
import model.Item;
import model.Order;
import model.Payment;
import security.Sanitizer;

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
		
		if(request.getParameter("orderSuccess") != null) {
			String target = "/views/shopping-cart/orderSuccess.jsp"; 
			request.getRequestDispatcher(target).forward(request, response);
			return;
		}
		
		
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
		SessionManager sm = (SessionManager) request.getSession().getAttribute(SessionManager.SESSION_MANAGER);
		sm.updateCheckoutAttempts();
		
		//Every third attempt, placing an order fails (as per project requirements)
		if(sm.getCheckoutAttempts() % 3 == 0) {
			response.setHeader("failed", "true");
			return;
		}
		
		// Place order
		try {
			processOrder(request);
			response.setHeader("success", "true");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			response.setHeader("failed", "true");
		}

	}
	
	private void processOrder(HttpServletRequest request) throws SQLException {
		SessionManager sm = (SessionManager) request.getSession().getAttribute(SessionManager.SESSION_MANAGER);
		Map<String, String> sanatizedParams = Sanitizer.cleanRequestParameters(request);
		int userId = sm.getUserId();
		
		//Get billing address information
		String address = sanatizedParams.get("address");
		String address2 = sanatizedParams.get("address2");
		String country = sanatizedParams.get("country");
		String postal_code = sanatizedParams.get("postal_code").strip().replaceAll(" ", "");
		String province = sanatizedParams.get("province");
		
		if(address2 != null && !address2.isEmpty()) {
			address += ", Suite " + address2;
		}
		
		//Get payment information
		String cc_name = sanatizedParams.get("cc-name");
		String cc_number = sanatizedParams.get("cc-number");
		String cc_cvv = sanatizedParams.get("cc-cvv");
		String cc_expiration = sanatizedParams.get("cc-expiration");
		String paymentMethod = sanatizedParams.get("paymentMethod");
		
		Order order = ModelFactory.createOrder();
		
		BillingAddress ba = ModelFactory.createBillingAddress();
		Payment payment = ModelFactory.createPayment();
		
		//set BillingAddress info
		ba.setCountry(country);
		ba.setPostalCode(postal_code);
		ba.setProvince(province);
		ba.setStreet(address);
		ba.save();
		//set payment info
		payment.setCreditCardCvv(cc_cvv);
		payment.setCreditCardExpiration(cc_expiration);
		payment.setCreditCardName(cc_name);
		payment.setCreditCardNumber(cc_number);
		payment.setPaymentType(paymentMethod);
		payment.save();
		
		order.setBillingAddress(ba);
		order.setPayment(payment);
		order.setUserId(userId);
		
		
		// set order items 
		CartManager cart = sm.getCart();
		order.setTotal(cart.getTotal() + cart.getShipping());
		
		for(Item item: cart.getItems()) {
			order.addItem(item, cart.getItemQuantity(item.getId()));
		}
		
		order.save();
		
		cart.clear();
	}

}
