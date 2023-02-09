package model;

import dao.UserDao;

// Models the User table in the database
public class User extends Model {

	private final UserDao dao;
	
	public User(UserDao dao, int id, String[] attributeValues) {
		super(dao, id, attributeValues);
		
		this.dao = dao;
	}
	
	public static User get(int id) {
		UserDao dao = UserDao.getUserDao();
		return dao.get(id);
	}
	
	protected String[] getAttributeNames() {
		return new String[] {"first_name"};
	}
}
