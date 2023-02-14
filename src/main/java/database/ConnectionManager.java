package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Map;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * This class is in charge of managing the connections to the database and performing
 * CRUD operations, returning any results to the caller as necessary.
 * 
 * @author ofekr
 *
 */
public class ConnectionManager {
	private DataSource ds; // TODO: look into pooledconnections

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
	
	public ConnectionManager() {
		try {
			// TODO: Change lookup path if working on development or production environments
			ds = (DataSource) (new InitialContext()).lookup("java:/comp/env/jdbc/reagailDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Executes an INSERT INTO operation into the database.
	 * 
	 * @param table the table to execute this operation on
	 * @param valueMap the map containing the row to insert, each (key,value) pair
	 * mapping to a column name and its value
	 */
	public void executeInsert(String table, Map<String, String> valueMap) {
		Connection con;
		try {
			con = this.ds.getConnection();
			PreparedStatement preparedStatement = constructPreparedStatement(con, OPERATION.INSERT, 
					table, valueMap);
			preparedStatement.executeUpdate();
			preparedStatement.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Constructs a {@link PreparedStatement} from the given parameters.
	 * 
	 * @param con the connection to perform this statement on
	 * @param operation CRUD operation for this statement
	 * @param table the database table this statement will affect
	 * @param valueMap the value map with each (key,value) pair mapping to a value name and its value 
	 * @return the constructed {@link PreparedStatement}
	 */
	private PreparedStatement constructPreparedStatement(Connection con, OPERATION operation, 
			String table, Map<String, String> valueMap) {
		String updateOperation = operation.toString();
		String query = updateOperation + " " + table + " (";
		String valuesAddOn = " VALUES (";
		Iterator<String> it = valueMap.keySet().iterator();

		while (it.hasNext()) {
			String valueName = it.next();
			query += valueName;
			valuesAddOn += "?";
			if (it.hasNext()) {
				query += ",";
				valuesAddOn += ",";
			}
		}

		valuesAddOn += ")";
		query += ")";
		query += valuesAddOn;
		System.out.println(query);
		PreparedStatement preparedStatement = null;
		try {
			con = this.ds.getConnection();
			preparedStatement = con.prepareStatement(query);
			int i = 1;
			for (String valueName : valueMap.keySet()) {
				preparedStatement.setString(i, valueMap.get(valueName));
				i++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return preparedStatement;
	}

}
