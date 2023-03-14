package utill;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.ItemDao;

public class CatalogFilters {
	
	public List<String> getCategoryFilters(){
	    List<String> categoryList = new ArrayList<>();
	    String filterType = "category";
	    
	    try {
	    	categoryList = new ItemDao().getFilters(filterType);	
		} catch (SQLException e) {
			return categoryList;
		}
	    return categoryList;
	}
	
	public List<String> getBrandFilters(){
	    List<String> brandList = new ArrayList<>();
	    String filterType = "brand";
	    
	    try {
	    	brandList = new ItemDao().getFilters(filterType);	
		} catch (SQLException e) {
			return brandList;
		}
	    return brandList;
	}
	
	public List<String> getColorFilters(){
	    List<String> colorList = new ArrayList<>();
	    String filterType = "color";
	    
	    try {
	    	colorList = new ItemDao().getFilters(filterType);	
		} catch (SQLException e) {
			return colorList;
		}
	    return colorList;
	}
	
	public List<String> getPriceFilters(){
	    List<String> priceList = new ArrayList<>();
	    String filterType = "price";
	    
	    try {
	    	priceList = new ItemDao().getPriceFilters(filterType);
		} catch (SQLException e) {
			return priceList;
		}
	    return priceList;
	}
}