package ctr;

import java.io.IOException;
import java.io.PrintWriter;
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
import utill.PageRender;

/**
 * Servlet implementation class CatalogController
 */
@WebServlet("/Catalog")
public class CatalogController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final int CATALOG_BATCH_SIZE = 12;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CatalogController() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public void init() throws ServletException {
    	// TODO Auto-generated method stub
    	super.init();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		if(request.getParameter("loadMore") != null) {
			loadMoreCatalogItems(request, response);
			return;
		}
		
		//load item in batches
		request.getSession().setAttribute("itemsLoaded", 0);
		List<Item> catalog_list = this.getItemsBatch(request);
		request.setAttribute("catalog_list", catalog_list);
		
		// return catalog page
		String target = "/views/catalog.jsp"; 
		request.getRequestDispatcher(target).forward(request, response);
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private List<Item> getItemsBatch(HttpServletRequest request){
		List<Item> items = new ArrayList<Item>();
		String primaryKey = Item.primaryKeyColumnName;
		
		int itemsLoaded = (int) request.getSession().getAttribute("itemsLoaded");
		String [] conditions = {
			primaryKey + ">" + itemsLoaded,
			primaryKey + "<=" + (itemsLoaded + CATALOG_BATCH_SIZE)
		};
				
		try {
			items = new ItemDao().getAll(conditions);	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.getSession().setAttribute("itemsLoaded", itemsLoaded + CATALOG_BATCH_SIZE);
		
		return items;
	}
	

	private void loadMoreCatalogItems(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
	    List<Item> added_catalog_list = this.getItemsBatch(request);
		request.setAttribute("catalog_list", added_catalog_list);

		// if all items were loaded
		if(added_catalog_list.isEmpty() || added_catalog_list.size() != CATALOG_BATCH_SIZE) {
			response.setHeader("Loaded-All", "true");
		}

	    // Get the rendered HTML code as a string
	    String page = "/views/item/item-listing.jsp";
	    String renderedHtml = PageRender.renderJSP(request, response, page);

	    // Send the rendered HTML code back to the client-side JavaScript
	    PrintWriter out = response.getWriter();
	    out.println(renderedHtml);
	    out.close();
	}
}
