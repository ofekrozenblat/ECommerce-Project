package dao;

import model.User;

// Handles CRUD operations related to User model
public class UserDao extends Dao {

	private static UserDao instance;
	
	public static UserDao getUserDao() {
		if (instance == null) {
			instance = new UserDao();
		}
		
		return instance;
	}
	
	private UserDao() {
		super.init();
	}

	@Override
	public User get(int id) {
		// gets the user from DB based on primary key
		
		// Creates the user
		User user = new User(this, id, new String[] {"Bob"});
		
		// sets the user attributes here
		return user;
	}
	
	// Can create other specific methods such as getAllUsers(), getUsersBy(...), etc..
	
}
