package ctr;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ItemDao;
import model.Item;

/**
 * Servlet implementation class ItemController
 */
@WebServlet("/Item_detail")
public class ItemDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
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
		
		Item item = null;
		try {
			item = new ItemDao().get(item_id);
		} catch (SQLException e1) {
			// TO DO: Return 404 PAGE
			e1.printStackTrace();
		}
		
		//Set request attributes		
		request.setAttribute("name", item.getName());
		request.setAttribute("color", item.getColor());
		request.setAttribute("brand", item.getBrand());
		request.setAttribute("category", item.getCategory());
		request.setAttribute("rating", item.getRating());
		request.setAttribute("review_count", item.getReviews().size());
		request.setAttribute("price", item.getPrice());
		request.setAttribute("description", item.getDescription());
		request.setAttribute("review_count", item.getReviews().size());
		request.setAttribute("recommendation_list", item.getRecommendations());
		
		
		String target = "/views/item/item-detail.jsp";
		request.getRequestDispatcher(target).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
