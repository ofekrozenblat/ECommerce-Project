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
	    List<String> category_list = null;
	    String filter_type = "category";
	    
	    try {
	    	category_list = new ItemDao().getFilters(filter_type);	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return category_list;
	}
	
	public List<String> loadBrandFilters() throws ServletException, IOException{
	    List<String> brand_list = null;
	    String filter_type = "brand";
	    
	    try {
	    	brand_list = new ItemDao().getFilters(filter_type);	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return brand_list;
	}
	
	public List<String> loadColorFilters() throws ServletException, IOException{
	    List<String> color_list = null;
	    String filter_type = "color";
	    
	    try {
	    	color_list = new ItemDao().getFilters(filter_type);	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return color_list;
	}
	
	public List<String> loadPriceFilters() throws ServletException, IOException{
	    List<String> price_list = null;
	    String filter_type = "price";
	    
	    try {
	    	price_list = new ItemDao().getPriceFilters(filter_type);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return price_list;
	}
}