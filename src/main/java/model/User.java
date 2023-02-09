package model;

import dao.Dao;
import dao.UserDao;

// Models the User table in the database
public class User extends Model {
	
	// onCreate: E.g. User = new User(...) when User is created in the application
	// and should be stored in the DB
	public User(String[] attributeValues) {
		super(attributeValues);
	}
	
	public User(UserDao dao, int id, String[] attributeValues) {
		super(dao, id, attributeValues);
	}
	
	public static User get(int id) {
		UserDao dao = UserDao.getUserDao();
		return dao.get(id);
	}
	
	protected Dao getDao() {
		return UserDao.getUserDao();
	}
	
	protected String[] getAttributeNames() {
		return new String[] {"first_name"};
	}
	
	public String getTable() {
		return "Users";
	}
	
	public String getFirstName() {
		return attributes.get("first_name");
	}
}
