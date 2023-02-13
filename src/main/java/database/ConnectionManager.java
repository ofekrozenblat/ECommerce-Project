package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Map;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConnectionManager {
	private DataSource ds; // TODO: look into pooledconnections

	private final String INSERT_INTO = "INSERT INTO";

	public ConnectionManager() {
		try {
			// TODO: Change lookup path if working on development or production environments
			ds = (DataSource) (new InitialContext()).lookup("java:/comp/env/jdbc/reagailDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	// Insert INTO
	public void executeUpdate(String table, Map<String, String> valueMap) {
		Connection con;
		try {
			con = this.ds.getConnection();
			PreparedStatement preparedStatement = constructPreparedStatement(con, INSERT_INTO, 
					table, valueMap);
			preparedStatement.executeUpdate();
			preparedStatement.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private PreparedStatement constructPreparedStatement(Connection con, String operation, String table,
			Map<String, String> valueMap) {
		String query = operation + " " + table + " (";
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
