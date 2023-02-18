package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import database.ConnectionManager;
import model.Model;

/**
 * This class represents a generic data access object. It provides the interface for the {@link Model}
 * to communicate with the database, allowing all basic CRUD operations. 
 * <br>
 * This class is meant to be extended and implemented by child
 * classes that will provide data access for each existing {@code Model}.
 * 
 * @author ofekr
 * @see Model
 */
public abstract class Dao {
	
	/** Handles the connection management with the database. **/
	protected ConnectionManager connection;
	
	/**
	 * Initializes the dao object.
	 */
	protected Dao() {
		connection = new ConnectionManager();
	}
	
	/**
	 * Retrieves a {@link Model} from the database represented by the provided primary key.
	 * <br>
	 * This method is meant to be implemented by a child class which will return a concrete
	 * class of {@link Model}.
	 * 
	 * @param id primary key of the model to retrieve
	 * @return {@link Model} object
	 * @throws SQLException if the retrieval failed
	 */
	public abstract Model get(int id) throws SQLException;
	
	/**
	 * Saves the model in the database.
	 * 
	 * @param model {@link Model} object to be saved
	 * @throws SQLException if the saving failed
	 */
	public void save(Model model) throws SQLException {
		Map<String, String> attributes = model.getAttributeMap();
		String table = model.getTable();
		String primaryKeyColumn = model.getPrimaryKeyColumnName();
		String primaryKeyValue = String.valueOf(model.getId());
		
		connection.executeSingleUpdate(table, primaryKeyColumn, primaryKeyValue, attributes);
	}
	
	/**
	 * Deletes the model in the database.
	 * 
	 * @param model {@link Model} object to be deleted
	 * @throws SQLException if deleting failed
	 */
	public void delete(Model model) throws SQLException {
		String table = model.getTable();
		String primaryKeyColumn = model.getPrimaryKeyColumnName();
		String primaryKeyValue = String.valueOf(model.getId());
		
		connection.executeSingleDelete(table, primaryKeyColumn, primaryKeyValue);
	}
	
	/**
	 * Creates the model in the database and returns it's primary key.
	 * 
	 * @param model {@link Model} object to be saved
	 * @return primary key of the model
	 * @throws SQLException if the creation failed or failed to retrieve the primary key
	 */
	public int create(Model model) throws SQLException {
		// Insert new row in the database table and return the primary key of the row
		Map<String, String> attributes = model.getAttributeMap();
		String table = model.getTable();
		String primaryKeyColumn = model.getPrimaryKeyColumnName();
		
		// Executes the insert and gets the ID of the new primary key
		int retrievedPrimaryKey = connection.executeInsert(table, primaryKeyColumn, attributes);
		
		if (retrievedPrimaryKey == -1) {
			throw new SQLException("Failed to retrieve the primary key value of the model.");
		}
		
		return retrievedPrimaryKey;
	}
}
