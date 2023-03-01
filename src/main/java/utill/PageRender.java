package utill;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

/**
 * 
 * @author shach
 *
 */
public class PageRender {
	
	/**
	 * Server side renders a JSP page 
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @param page JSP page
	 * @return rendered HTML 
	 * @throws ServletException
	 * @throws IOException
	 */
	public static String renderJSP(HttpServletRequest request, HttpServletResponse response, String page) throws ServletException, IOException {
		response.setContentType("text/html");

	    // Get a reference to the request dispatcher for the JSP page
	    RequestDispatcher dispatcher = request.getRequestDispatcher(page);
	    
	    // Create a string writer to capture the JSP output
	    StringWriter writer = new StringWriter();
	    
	    // Use the request dispatcher to render the JSP page and capture the output in the string writer
	    dispatcher.include(request, new HttpServletResponseWrapper(response) {
	        @Override
	        public PrintWriter getWriter() throws IOException {
	            return new PrintWriter(writer);
	        }
	    });

	    // Get the rendered HTML code as a string
	    String renderedHtml = writer.toString();
	    
		return renderedHtml;
	}
}
