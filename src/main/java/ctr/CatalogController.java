package ctr;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import dao.ItemDao;
import model.Item;

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
		 // Set the content type of the response to text/html
	    response.setContentType("text/html");

	    // Get a reference to the request dispatcher for the JSP page
	    RequestDispatcher dispatcher = request.getRequestDispatcher("/views/item/item-listing.jsp");
	    
	    // Create a string writer to capture the JSP output
	    StringWriter writer = new StringWriter();
	    
	    List<Item> catalog_list = this.getItemsBatch(request);
		request.setAttribute("catalog_list", catalog_list);
		
		if(catalog_list.isEmpty()) {
			PrintWriter out = response.getWriter();
			out.println("");
			return;
		}
		
	    // Use the request dispatcher to render the JSP page and capture the output in the string writer
	    dispatcher.include(request, new HttpServletResponseWrapper(response) {
	        @Override
	        public PrintWriter getWriter() throws IOException {
	            return new PrintWriter(writer);
	        }
	    });

	    // Get the rendered HTML code as a string
	    String renderedHtml = writer.toString();

	    // Send the rendered HTML code back to the client-side JavaScript
	    PrintWriter out = response.getWriter();
	    out.println(renderedHtml);
	    out.close();
	}


}
