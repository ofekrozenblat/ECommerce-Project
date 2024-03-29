package ctr;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ItemDao;
import dao.ItemVisitDao;
import dao.ReviewDao;
import factories.ModelFactory;
import model.Item;
import model.ItemVisit;
import model.Review;
import utill.SessionManager;
import security.Sanitizer;
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
		itemVisitEvent(item_id);
		
		request.getSession().setAttribute(REQ_SESSION_ITEM_ID, item_id);
		
		Item item = null;
		boolean userReviewed = false;
		try {
			item = new ItemDao().get(item_id);
			userReviewed = this.userReviewed(item_id, request);
		} catch (Exception e1) {
			// Return 404 PAGE
			request.getRequestDispatcher("/views/errors/404.jsp").forward(request, response);
			return;
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
		request.setAttribute("userReviewed", userReviewed);
		request.setAttribute("quantity", item.getQuantity());
		request.setAttribute("img", item.getImg());
		
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
			

			Map<String, String> sanatizedParams = Sanitizer.cleanRequestParameters(request);
			String title = (sanatizedParams.get("title"));
			String description = (sanatizedParams.get("description"));
			int rating = Integer.parseInt(sanatizedParams.get("rating"));
			
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
				response.setHeader("error", "something went wrong");
				return;
			}
			
			response.setHeader("success", "true");
		}
		
		if(request.getParameter("add-to-cart") != null) {
			SessionManager sm = (SessionManager) request.getSession().getAttribute(SessionManager.SESSION_MANAGER);
			
			try {
				Item item = new ItemDao().get(item_id);
				if(item.getQuantity() - sm.getCart().getItemQuantity(item_id) == 0) {
					return;
				}else {
					sm.getCart().addToCart(item_id, 1);
					response.setHeader("success", "true");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				return;
			}
			
		}
	}
	
	private boolean userReviewed(int item_id, HttpServletRequest request) throws Exception {
		SessionManager sm = (SessionManager) request.getSession().getAttribute(SessionManager.SESSION_MANAGER);
		int user_id = sm.getUserId();
		List<Review> itemReviews = new ReviewDao().getReviewsByUserId(user_id);
		
		for(Review review: itemReviews) {
			if(review.getUserId() == user_id && review.getItemId() == item_id) {
				return true;
			}
		}
		
		return false;
	}
	
	private void itemVisitEvent(int item_id) {
		try {
			ItemVisit itemVisit = new ItemVisitDao().getByItemId(item_id);
			itemVisit.updateVisits();
			itemVisit.save();
		} catch (SQLException e) {
			ItemVisit itemVisit = ModelFactory.createItemVisit();
			itemVisit.setItemId(item_id);
			itemVisit.updateVisits();
			try {
				itemVisit.save();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

}
