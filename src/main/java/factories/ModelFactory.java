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
		// Performs dependency injection of the data access object related to the review class.
		return new Review(new ReviewDao());
	}
	
	/**
	 * Creates and returns a {@link Order} model.
	 * 
	 * @return {@link Order} object
	 */
	public static Order createOrder() {
		// Performs dependency injection of the data access object related to the Order class.
		return new Order(new OrderDao());
	}
	
	/**
	 * Creates and returns a {@link BillingAddress} model.
	 * 
	 * @return {@link BillingAddress} object
	 */
	public static BillingAddress createBillingAddress() {
		return new BillingAddress(new BillingAddressDao());
	}
	
	/**
	 * Creates and returns a {@link Payment} model.
	 * 
	 * @return {@link Payment} object
	 */
	public static Payment createPayment() {
		return new Payment(new PaymentDao());
	}
	
}
