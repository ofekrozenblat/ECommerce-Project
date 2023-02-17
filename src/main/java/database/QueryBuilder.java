package database;

/**
 * {@code QueryBuilder} is in charge of building SQL queries.
 * 
 * @author ofekr
 *
 */
public class QueryBuilder {
	
	public enum OPERATION{
		INSERT("INSERT INTO"),
		UPDATE("UPDATE"),
		DELETE("DELETE"),
		SELECT("SELECT");
		
		private final String operation;
		
		OPERATION(String operation) {
			this.operation = operation;
		}
		
		@Override
		public String toString() {
			return this.operation;
		}
	}
	
	/**
	 * Creates a SELECT statement for all columns in a table.
	 * 
	 * @param table to select
	 * @return SELECT ALL from table query
	 */
	public String createSELECT(String table) {
		return createSELECT(table, null);
	}
	
	/**
	 * Creates a SELECT statement for only certain columns in a table.
	 * 
	 * @param table to select
	 * @param columns the specific column tables to select
	 * @return SELECT from table only specific columns query
	 */
	public String createSELECT(String table, String[] columns) {
		String query = OPERATION.SELECT.toString() + " ";
		if (columns == null) {
			query += "* ";
		}else {
			for(int i = 0; i < columns.length; i++) {
				query += columns[i];
				
				if (i+1 != columns.length) {
					query += ", ";
				}
			}
		}
		query += "FROM " + table;
		
		return query;
	}
	
	/**
	 * Creates a INSERT INTO statement for only certain columns in a table.
	 * 
	 * @param table to select
	 * @param columns the specific column tables to insert into
	 * @return INSERT INTO table for only specific columns query
	 */
	public String createINSERT(String table, String[] columns) {
		String query = OPERATION.INSERT.toString() + " ";
		String values = " VALUES (";
		query += table + " (";
		
		for(int i = 0; i < columns.length; i++) {
			query += columns[i];
			values += "?";
			
			if (i+1 != columns.length) {
				query += ", ";
				values += ", ";
			}
		}
		
		query += ")";
		values += ")";
		query += values;
		
		return query;
	}
	
	/**
	 * Adds a WHERE clause to the query with the given conditions
	 * 
	 * @param query
	 * @param conditions which will be placed in the WHERE clause, concatenated with an AND
	 * @return query string with the added WHERE clause
	 */
	public String addWHERE(String query, String[] conditions) {
		if (conditions == null || conditions.length < 1) return query;
		
		String modifiedQuery = query + " WHERE ";
		
		for(int i = 0; i < conditions.length; i++) {
			modifiedQuery += "(";
			modifiedQuery += conditions[i];
			modifiedQuery += ")";
			
			if (i+1 != conditions.length) {
				modifiedQuery += " AND ";
			}
		}
		
		return modifiedQuery;
	}
	
}
