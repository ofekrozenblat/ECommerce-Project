package filters;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import hthurow.tomcatjndi.TomcatJNDI;

import utill.CatalogFilters;

class FiltersTest {
private static TomcatJNDI tomcatJNDI;
	
	@BeforeAll
    static void setup() {
		tomcatJNDI = new TomcatJNDI();
		tomcatJNDI.processContextXml(new File("src\\main\\webapp\\META-INF\\context.xml"));
		tomcatJNDI.processWebXml(new File("src\\main\\webapp\\WEB-INF\\web.xml"));
		tomcatJNDI.start();
    }

    @AfterAll
    static void tearDown() {
    	tomcatJNDI.tearDown();
    }
	
	@Test
    void testCategoryFilters() {
		CatalogFilters filters = new CatalogFilters();
		List<String> filterCategories = filters.getCategoryFilters();
		
		assertTrue(filterCategories.size() == 4);
		
		assertTrue(filterCategories.contains("Eyeglasses"));
		assertTrue(filterCategories.contains("Sunglasses"));
		assertTrue(filterCategories.contains("Reading glasses"));
		assertTrue(filterCategories.contains("Fashion glasses"));
    }
	
	@Test
    void testBrandFilters() {
		CatalogFilters filters = new CatalogFilters();
		List<String> filterBrands = filters.getBrandFilters();
		
		assertTrue(filterBrands.size() == 18);
		
		assertTrue(filterBrands.contains("Gucci"));
		assertTrue(filterBrands.contains("Ray-Ban"));
		assertTrue(filterBrands.contains("Prada"));
		assertTrue(filterBrands.contains("Chanel"));
		assertTrue(filterBrands.contains("Tom Ford"));
		
		assertTrue(filterBrands.contains("Persol"));
		assertTrue(filterBrands.contains("Armani"));
		assertTrue(filterBrands.contains("Dior"));
		assertTrue(filterBrands.contains("Marc Jacobs"));
		assertTrue(filterBrands.contains("Porsche Design"));
		
		assertTrue(filterBrands.contains("Versace"));
		assertTrue(filterBrands.contains("Fendi"));
		assertTrue(filterBrands.contains("Hugo Boss"));
		assertTrue(filterBrands.contains("Jimmy Choo"));
		
		assertTrue(filterBrands.contains("Oakley"));
		assertTrue(filterBrands.contains("Alexander McQueen"));
		assertTrue(filterBrands.contains("Dolce & Gabbana"));
		assertTrue(filterBrands.contains("Giorgio Armani"));
	}
	
	@Test
    void testColorFilters() {
		CatalogFilters filters = new CatalogFilters();
		List<String> filterColors = filters.getColorFilters();
		
		assertTrue(filterColors.size() == 9);
		
		assertTrue(filterColors.contains("Black"));
		assertTrue(filterColors.contains("Gold"));
		assertTrue(filterColors.contains("Red"));
		assertTrue(filterColors.contains("Pink"));
		assertTrue(filterColors.contains("Brown"));
		assertTrue(filterColors.contains("Purple"));
		assertTrue(filterColors.contains("Silver"));
		assertTrue(filterColors.contains("Blue"));
		assertTrue(filterColors.contains("White"));
	}
	
	@Test
    void testPriceFilters() {
		CatalogFilters filters = new CatalogFilters();
		List<String> filterPrice = filters.getPriceFilters();
		
		assertTrue(filterPrice.size() == 2);
		
		assertTrue(filterPrice.contains("99.99"));
		assertTrue(filterPrice.contains("999.99"));
	}
}
