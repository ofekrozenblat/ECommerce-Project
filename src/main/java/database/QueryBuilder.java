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
		DELETE("DELETE FROM"),
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
	 * Creates a SELECT statement to return the last row of the table.
	 * 
	 * @param table to select
	 * @param primaryKeyColumn the primary key column name
	 * @return SELECT last row from table query
	 */
	public String createSelectLast(String table, String primaryKeyColumn) {	
		String query = createSelect(table);
		query = addRAW(query, "ORDER BY " + primaryKeyColumn + " DESC LIMIT 1");
		
		return query;
	}
	/**
	 * Creates a SELECT statement for all columns in a table.
	 * 
	 * @param table to select
	 * @return SELECT ALL from table query
	 */
	public String createSelect(String table) {
		return createSelect(table, null);
	}
	
	/**
	 * Creates a SELECT statement for only certain columns in a table.
	 * 
	 * @param table to select
	 * @param columns the specific column tables to select
	 * @return SELECT from table only specific columns query
	 */
	public String createSelect(String table, String[] columns) {
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
	 * Creates an INSERT statement for only certain columns in a table.
	 * 
	 * @param table to select
	 * @param columns the specific column tables to insert into
	 * @return INSERT INTO table for only specific columns query
	 */
	public String createInsert(String table, String[] columns) {
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
	 * Creates an UPDATE statement for only certain columns in a table.
	 * 
	 * @param table to select
	 * @param columns the specific column tables to update
	 * @param conditions which will be placed in the WHERE clause, concatenated with an AND
	 * @return UPDATE table for only specific columns query
	 * @throws IllegalArgumentException if {@code conditions} is {@code null} or empty
	 */
	public String createUpdate(String table, String[] columns, String[] conditions) 
			throws IllegalArgumentException {
		if (conditions == null || conditions.length < 1) {
			// Throw exception, avoid updating every record in the table
			throw new IllegalArgumentException("Constructing update query requires conditions.");
		}
		
		String query = OPERATION.UPDATE.toString() + " ";
		String setClause = " SET ";
		query += table;
		
		for(int i = 0; i < columns.length; i++) {
			setClause += columns[i] + " = ?";
			
			if (i+1 != columns.length) {
				setClause += ", ";
			}
		}
		
		query += setClause;
		query = addWHERE(query, conditions);
		
		return query;
	}
	
	/**
	 * Creates a DELETE statement for the table.
	 * 
	 * @param table to delete from
	 * @param conditions which will be placed in the WHERE clause, concatenated with an AND
	 * @return DELETE from table query
	 * @throws IllegalArgumentException if {@code conditions} is {@code null} or empty
	 */
	public String createDelete(String table, String[] conditions) throws IllegalArgumentException {
		if (conditions == null || conditions.length < 1) {
			// Throw exception, avoid deleting every record in the table
			throw new IllegalArgumentException("Constructing delete query requires conditions.");
		}
		
		String query = OPERATION.DELETE.toString() + " ";
		query += table;
		query = addWHERE(query, conditions);
		
		return query;
	}
	
	/**
	 * Adds a WHERE clause to the query with the given conditions
	 * 
	 * @param query to modify
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
	
	/**
	 * Adds a raw SQL clause to the query.
	 * 
	 * @param query to modify
	 * @param rawAddOn the SQL clause to add to the query
	 * @return modified query with the added clause
	 */
	public String addRAW(String query, String rawAddOn) {
		return query += " " + rawAddOn;
	}
	
}
