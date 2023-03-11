package security;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.jsoup.*;
import org.jsoup.safety.*;

/**
 * This class is in charge of sanitizing user input.
 * 
 * @author ofekr
 *
 */
public class Sanitizer {
	
	/**
	 * Takes a input string and cleans it, only allowing simple text and removing everything else.
	 * Makes use of {@link org.jsoup.safety.Safelist#none()} as the whitelist.
	 * 
	 * @param input string to clean
	 * @return cleaned input string
	 */
	public static String clean(String input) {
	     Cleaner cleaner = new Cleaner(Safelist.none());
	     String text = cleaner.clean(Jsoup.parse(input)).text();
	     return text;
	}
	
	/**
	 * Cleans all the parameters in the request and returns a map with the cleaned
	 * parameter values.<br>
	 * <strong>Important: This method assumes that each parameter has a single value.
	 * If the parameter value is an array, the values are concatenated as a single String. </strong>
	 * 
	 * @param request the {@link HttpServletRequest} to clean
	 * @return map with keys as the parameter names and values as the cleaned parameter values.
	 */
	public static Map<String, String> cleanRequestParameters(HttpServletRequest request) {
		Map<String, String[]> parameterMap = request.getParameterMap();
		Map<String, String> result = new HashMap<String, String>();
		
		for(String paramName : parameterMap.keySet()) {
			result.put(paramName, "");
			
			String[] paramValue = parameterMap.get(paramName);
			for(String value : paramValue) {
				String cleanedValue = clean(value);
				
				String currentValue = result.get(paramName);
				result.put(paramName, currentValue+cleanedValue);
			}
		}
		
		return result;
	}

}
