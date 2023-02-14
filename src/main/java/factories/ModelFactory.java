package factories;

import dao.UserDao;
import model.*;

/**
 * This factory class is in chage of the creation and initilization of all classes implementing
 * {@link Model}.
 * 
 * @author ofekr
 */
public class ModelFactory {
	
	/**
	 * Creates and returns a {@link User} model.
	 * 
	 * @return {@link User} object
	 */
	public static User createUser() {
		// Performs dependency injection of the data access object related to the user class.
		return new User(new UserDao());
	}
	
}
