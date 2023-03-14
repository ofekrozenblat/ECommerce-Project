package ctr;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
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
		
		request.getSession().setAttribute("itemsLoaded", 0);
		String target = "/views/catalog.jsp"; 
		List<Item> catalog_list = this.getItemsBatch(request);
		request.setAttribute("catalog_list", catalog_list);
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
	
	private void loadCategoryFilters(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
	    List<String> category_list;
	    String filter_type = "category";
	    
	    try {
	    	category_list = new ItemDao().getFilters(filter_type);	
	    	request.setAttribute("category_list", category_list);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    // Get the rendered HTML code as a string
	    String page = "/views/item/item-listing.jsp";
	    String renderedHtml = PageRender.renderJSP(request, response, page);

	    // Send the rendered HTML code back to the client-side JavaScript
	    PrintWriter out = response.getWriter();
	    out.println(renderedHtml);
	    out.close();
	}
	
	private void loadBrandFilters(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
	    List<String> brand_list;
	    String filter_type = "brand";
	    
	    try {
	    	brand_list = new ItemDao().getFilters(filter_type);	
	    	request.setAttribute("brand_list", brand_list);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    // Get the rendered HTML code as a string
	    String page = "/views/item/item-listing.jsp";
	    String renderedHtml = PageRender.renderJSP(request, response, page);

	    // Send the rendered HTML code back to the client-side JavaScript
	    PrintWriter out = response.getWriter();
	    out.println(renderedHtml);
	    out.close();
	}
	
	private void loadColorFilters(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
	    List<String> color_list;
	    String filter_type = "color";
	    
	    try {
	    	color_list = new ItemDao().getFilters(filter_type);	
	    	request.setAttribute("color_list", color_list);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    // Get the rendered HTML code as a string
	    String page = "/views/item/item-listing.jsp";
	    String renderedHtml = PageRender.renderJSP(request, response, page);

	    // Send the rendered HTML code back to the client-side JavaScript
	    PrintWriter out = response.getWriter();
	    out.println(renderedHtml);
	    out.close();
	}
	
	private void loadPriceFilters(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
	    List<String> price_list;
	    String filter_type = "price";
	    
	    try {
	    	price_list = new ItemDao().getPriceFilters(filter_type);
	    	request.setAttribute("price_list", price_list);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
