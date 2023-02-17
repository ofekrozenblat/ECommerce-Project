package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
	private QueryBuilder queryBuilder;
	
	public ConnectionManager() {
		try {
			// TODO: Change lookup path if working on development or production environments
			ds = (DataSource) (new InitialContext()).lookup("java:/comp/env/jdbc/reagailDB");
			queryBuilder = new QueryBuilder();
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Executes a SELECT operation on the database. If the {@code columns} parameter is {@code null}
	 * the SELECT statement is taken to be a SELECT * statement and will return all columns.
	 * 
	 * @param table to execute this operation on
	 * @param columns to select from the table
	 * @param conditions enforced on this statement
	 * @return {@link ResultSet}
	 */
	public ResultSet executeSelect(String table, String[] columns, String[] conditions) {
		Connection con;
		ResultSet result = null;
		
		String query = queryBuilder.createSELECT(table, columns);
		query = queryBuilder.addWHERE(query, conditions);
		
		try {
			con = ds.getConnection();
			Statement statement = con.createStatement();
			result = statement.executeQuery(query);
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * Executes an INSERT INTO operation into the database.
	 * 
	 * @param table to execute this operation on
	 * @param valueMap the map containing the row to insert, each (key,value) pair
	 * mapping to a column name and its value
	 */
	public void executeInsert(String table, Map<String, String> valueMap) {
		Connection con;
		String[] columns = extractColumnNames(valueMap);
		
		String query = queryBuilder.createINSERT(table, columns);
		try {
			con = this.ds.getConnection();
			PreparedStatement preparedStatement = constructPreparedStatement(con, query, valueMap);
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
	 * @param query query string for this statement
	 * @param valueMap the value map with each (key,value) pair mapping to a value name and its value 
	 * @return the constructed {@link PreparedStatement}
	 */
	private PreparedStatement constructPreparedStatement(Connection con, String query,
			Map<String, String> valueMap) {
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
	
	private String[] extractColumnNames(Map<String, String> valueMap) {
		return valueMap.keySet().toArray(new String[valueMap.size()]);
	}

}
