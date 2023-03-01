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
	 * Executes a SELECT operation on the database which will select the last row of the table.
	 * 
	 * @param table to execute this operation on
	 * @param primaryKeyColumn name of the primary key column
	 * @return {@link ResultSet}
	 * @throws SQLException if the execution failed
	 */
	public ResultSet executeSelectLast(String table, String primaryKeyColumn) throws SQLException{
		Connection con;
		ResultSet result = null;
		
		String query = queryBuilder.createSelectLast(table, primaryKeyColumn);
		try {
			con = ds.getConnection();
			Statement statement = con.createStatement();
			result = statement.executeQuery(query);
			con.close();
		} catch (SQLException e) {
			throw new SQLException("Failed to execute select last.");
		}
		
		return result;
	}
	
	/**
	 * Executes a SELECT operation on the database. If the {@code columns} parameter is {@code null}
	 * the SELECT statement is taken to be a SELECT * statement and will return all columns.
	 * 
	 * @param table to execute this operation on
	 * @param columns to select from the table
	 * @param conditions enforced on this statement
	 * @return {@link ResultSet}
	 * @throws SQLException if the execution failed
	 */
	public ResultSet executeSelect(String table, String[] columns, String[] conditions) throws SQLException {
		Connection con;
		ResultSet result = null;
		
		String query = queryBuilder.createSelect(table, columns);
		query = queryBuilder.addWHERE(query, conditions);
		
		try {
			con = ds.getConnection();
			Statement statement = con.createStatement();
			result = statement.executeQuery(query);
			con.close();
		} catch (SQLException e) {
			throw new SQLException("Failed to execute select.");
		}
		
		return result;
	}
	
	/**
	 * Executes an INSERT INTO operation into the database.
	 * 
	 * @param table to execute this operation on
	 * @param primaryKeyColumn the primary key column name that will 
	 * be returned upon successful insert
	 * @param valueMap the map containing the row to insert, each (key,value) pair
	 * mapping to a column name and its value
	 * @return primary key value created for this insert
	 * @throws SQLException if the execution failed
	 */
	public int executeInsert(String table, String primaryKeyColumn, 
			Map<String, String> valueMap) throws SQLException {
		Connection con;
		ResultSet result = null;
		int primaryKey = -1;
		String[] columns = extractColumnNames(valueMap);
		
		String query = queryBuilder.createInsert(table, columns);
		String[] returnColumnNames = {primaryKeyColumn};
		try {
			con = ds.getConnection();
			PreparedStatement preparedStatement = constructPreparedStatement(con, query, 
					returnColumnNames, valueMap);
			preparedStatement.executeUpdate();
			result = preparedStatement.getGeneratedKeys();
			
			if(result.next()) {
				primaryKey = result.getInt(1);
			}
			
			preparedStatement.close();
			con.close();
		} catch (SQLException e) {
			throw new SQLException("Failed to execute insert: " + e.getMessage());
		}
		
		return primaryKey;
	}
	
	/**
	 * Executes an UPDATE operation on a single row in the database.
	 * 
	 * @param table to execute this operation on
	 * @param primaryKeyColumn the primary key column name
	 * @param primaryKeyValue the primary key value of the row to update
	 * @param valueMap the map containing the row values to update, each (key,value) pair
	 * mapping to a column name and its value
	 * @throws SQLException if the execution failed
	 */
	public void executeSingleUpdate(String table, String primaryKeyColumn, String primaryKeyValue, 
			Map<String, String> valueMap) throws SQLException {
		Connection con;
		String[] columns = extractColumnNames(valueMap);
		String[] conditions = {primaryKeyColumn + "=" + primaryKeyValue};
		String query = queryBuilder.createUpdate(table, columns, conditions);
		
		try {
			con = ds.getConnection();
			PreparedStatement preparedStatement = constructPreparedStatement(con, query, valueMap);
			preparedStatement.executeUpdate();
			preparedStatement.close();
			con.close();
		} catch (SQLException e) {
			throw new SQLException("Failed to execute single update.");
		}
	}
	
	/**
	 * Executes a DELETE operation on a single row in the database.
	 * 
	 * @param table to execute this operation on
	 * @param primaryKeyColumn the primary key column name
	 * @param primaryKeyValue the primary key value of the row to delete
	 * @throws SQLException if the execution failed
	 */
	public void executeSingleDelete(String table, String primaryKeyColumn, String primaryKeyValue) throws SQLException {
		Connection con;
		String[] conditions = {primaryKeyColumn + "=" + primaryKeyValue};
		String query = queryBuilder.createDelete(table, conditions);
		
		try {
			con = ds.getConnection();
			PreparedStatement preparedStatement = con.prepareStatement(query);
			preparedStatement.executeUpdate();
			con.close();
		} catch (SQLException e) {
			throw new SQLException("Failed to execute single delete.");
		}
	}

	/**
	 * Constructs a {@link PreparedStatement} from the given parameters.
	 * 
	 * @param con the connection to perform this statement on
	 * @param query query string for this statement
	 * @param columnNames the column names to return upon successful insert
	 * @param valueMap the value map with each (key,value) pair mapping to a value name and its value 
	 * @return the constructed {@link PreparedStatement}
	 * @throws SQLException if constructing the prepared statement failed
	 */
	private PreparedStatement constructPreparedStatement(Connection con, String query, 
			String[] columnNames, Map<String, String> valueMap) throws SQLException {
		System.out.println(query);
		PreparedStatement preparedStatement = null;
		try {
			con = ds.getConnection();
			
			if (columnNames != null) { // Should only be used by INSERT
				preparedStatement = con.prepareStatement(query, columnNames);
			}else {
				preparedStatement = con.prepareStatement(query);
			}
			
			int i = 1;
			for (String valueName : valueMap.keySet()) {
				preparedStatement.setString(i, valueMap.get(valueName));
				i++;
			}
		} catch (SQLException e) {
			throw new SQLException("Failed to construct prepared statement.");
		}

		return preparedStatement;
	}
	
	/**
	 * Constructs a {@link PreparedStatement} from the given parameters.
	 * 
	 * @param con the connection to perform this statement on
	 * @param query query string for this statement
	 * @param valueMap the value map with each (key,value) pair mapping to a value name and its value 
	 * @return the constructed {@link PreparedStatement}
	 * @throws SQLException if constructing the prepared statement failed
	 */
	private PreparedStatement constructPreparedStatement (Connection con, String query, 
			Map<String, String> valueMap) throws SQLException  {
		return constructPreparedStatement(con, query, null, valueMap);
	}
	
	/**
	 * Extracts the column names from the map.
	 * 
	 * @param valueMap the value map with each (key,value) pair mapping to a column name and its value
	 * @return array of column names
	 */
	private String[] extractColumnNames(Map<String, String> valueMap) {
		return valueMap.keySet().toArray(new String[valueMap.size()]);
	}

}
