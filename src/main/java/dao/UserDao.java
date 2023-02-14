package dao;

import model.User;

/**
 * The {@code UserDao} class is in charge of providing the interfaces of the {@link model.User} model
 * to communicate with the database and any additional database operations related to this model.
 * 
 * @author ofekr
 * @see model.User
 * @see Dao
 *
 */
public class UserDao extends Dao {
	
	public UserDao() {
		
	}
	
	@Override
	public User get(int id) {
		// gets the user from DB based on primary key
		
		// Creates the user
		User user = new User(this, id);
		
		// sets the user attributes here
		return user;
	}
	
	// Can create other specific methods such as getAllUsers(), getUsersBy(...), etc..
	
}
