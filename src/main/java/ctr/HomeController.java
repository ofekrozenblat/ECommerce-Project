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
import java.util.ArrayList;
/**
 * Servlet implementation class HomeController
 */
@WebServlet("/Home")
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	private static final int FEATURED_COUNT = 4;
	private static final int NEW_ARRIVALS_COUNT = 8;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ArrayList<Item> items = new ArrayList<Item>();
		ArrayList<Item> featured = new ArrayList<Item>();
		ArrayList<Item> newArrivals = new ArrayList<Item>();
		
		try {
			items = new ItemDao().getAll();
			
			for(int i = 0; i < (FEATURED_COUNT + NEW_ARRIVALS_COUNT); i++) {
				if(i < FEATURED_COUNT) {
					featured.add(items.get(i));
				}else {
					newArrivals.add(items.get(i));
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String target = "/views/home.jsp"; 
		request.setAttribute("featured_list", featured);
		request.setAttribute("new_arrivals_list", newArrivals);
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
