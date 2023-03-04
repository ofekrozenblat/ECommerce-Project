package ctr;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ItemDao;
import factories.ModelFactory;
import model.Item;
import model.Review;
import utill.SessionManager;

/**
 * Servlet implementation class ItemController
 */
@WebServlet("/Item_detail")
public class ItemDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final String REQ_SESSION_ITEM_ID = "Item_id"; 
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ItemDetailController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int item_id = Integer.parseInt(request.getParameter("item_id"));
		
		request.getSession().setAttribute(REQ_SESSION_ITEM_ID, item_id);
		
		Item item = null;
		try {
			item = new ItemDao().get(item_id);
		} catch (SQLException e1) {
			// TO DO: Return 404 PAGE
			e1.printStackTrace();
		}
		
		//Set request attributes	
		
		List<Review> itemReviews = item.getReviews();
		
		request.setAttribute("name", item.getName());
		request.setAttribute("color", item.getColor());
		request.setAttribute("brand", item.getBrand());
		request.setAttribute("category", item.getCategory());
		request.setAttribute("rating", item.getRating());
		request.setAttribute("review_count", item.getReviews().size());
		request.setAttribute("price", item.getPrice());
		request.setAttribute("description", item.getDescription());
		request.setAttribute("review_count", itemReviews.size());
		request.setAttribute("recommendation_list", item.getRecommendations());
		request.setAttribute("reviews", itemReviews);
		
		
		String target = "/views/item/item-detail.jsp";
		request.getRequestDispatcher(target).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		int item_id = (int) request.getSession().getAttribute(REQ_SESSION_ITEM_ID);
		
		// New review created
		if(request.getParameter("new-review") != null) {
			String title = (String) request.getParameter("title");
			String description = (String) request.getParameter("description");
			int rating = Integer.parseInt((String) request.getParameter("rating"));
			
			try {
				SessionManager sm = (SessionManager) request.getSession().getAttribute(SessionManager.SESSION_MANAGER);
				Review review = ModelFactory.createReview();
				
				review.setTitle(title);
				review.setDescription(description);
				review.setRating(rating);
				review.setItemId(item_id);
				review.setUserId(sm.getUserId()); 
				
				review.save();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				response.setHeader("error", "something went wrong");
				return;
			}
			
			response.setHeader("success", "true");
		}
		
		if(request.getParameter("add-to-cart") != null) {
			SessionManager sm = (SessionManager) request.getSession().getAttribute(SessionManager.SESSION_MANAGER);
			sm.getCart().addToCart(item_id, 1);
		}
	}

}
