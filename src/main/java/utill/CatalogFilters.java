package utill;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ItemDao;

public class CatalogFilters {
	
	public List<String> getCategoryFilters() throws ServletException, IOException{
	    List<String> categoryList = null;
	    String filterType = "category";
	    
	    try {
	    	categoryList = new ItemDao().getFilters(filterType);	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return categoryList;
	}
	
	public List<String> loadBrandFilters() throws ServletException, IOException{
	    List<String> brandList = null;
	    String filterType = "brand";
	    
	    try {
	    	brandList = new ItemDao().getFilters(filterType);	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return brandList;
	}
	
	public List<String> loadColorFilters() throws ServletException, IOException{
	    List<String> colorList = null;
	    String filterType = "color";
	    
	    try {
	    	colorList = new ItemDao().getFilters(filterType);	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return colorList;
	}
	
	public List<String> loadPriceFilters() throws ServletException, IOException{
	    List<String> priceList = null;
	    String filterType = "price";
	    
	    try {
	    	priceList = new ItemDao().getPriceFilters(filterType);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return priceList;
	}
}