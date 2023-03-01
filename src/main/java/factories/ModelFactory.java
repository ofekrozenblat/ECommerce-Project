package factories;

import dao.*;
import model.*;

/**
 * This factory class is in charge of the creation and initialization of all classes implementing
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
	
	/**
	 * Creates and returns a {@link Review} model.
	 * 
	 * @return {@link Review} object
	 */
	public static Review createReview() {
		// Performs dependency injection of the data access object related to the user class.
		return new Review(new ReviewDao());
	}
	
}
