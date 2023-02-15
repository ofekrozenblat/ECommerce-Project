package database;

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
	
	public String createSELECT(String table) {
		return createSELECT(table, null);
	}
	
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
	
	public String addWHERE(String query, String[] conditions) {
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
