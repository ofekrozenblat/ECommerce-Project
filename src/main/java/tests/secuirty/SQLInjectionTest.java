package tests.secuirty;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.sql.SQLException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import dao.PaymentDao;
import factories.ModelFactory;
import hthurow.tomcatjndi.TomcatJNDI;
import model.Payment;
import model.User;

class SQLInjectionTest {

	private static TomcatJNDI tomcatJNDI;

	@BeforeAll
	static void setup() {
		tomcatJNDI = new TomcatJNDI();
		tomcatJNDI.processContextXml(new File("src\\main\\webapp\\META-INF\\context.xml"));
		tomcatJNDI.processWebXml(new File("src\\main\\webapp\\WEB-INF\\web.xml"));
		tomcatJNDI.start();
	}

	@AfterAll
	static void tearDown() {
		tomcatJNDI.tearDown();
	}

	@Test
	void test_user_registration_sql_injection_attack() {
		// register user
		
		/**
		 * Explanation: This is simulating a user who tries to perform an SQL injection
		 * attack when registering as a user on our web site. The malicious attempt is performed
		 * by entering a potential SQL statement to drop our payments table through the first name
		 * text field.
		 */
		String firstName_SQL_INJECTION = "Bob; DROP TABLE payments; --";
		String lastName = "Builder";
		String email = "bobthebuilder@gmail.com";

		User user = ModelFactory.createUser();
		user.setEmailAddress(email);
		user.setFirstName(firstName_SQL_INJECTION);
		user.setLastName(lastName);
		user.setIsAdmin(false);

		try {
			user.save();
		} catch (SQLException e) {
			assertEquals(true, true);
			System.out.println(e.getMessage());
		}

		/* Check to see if we can still retrieve a payment object.
		 * If we can, then the table was not dropped and JDBC prepared
		 * statements protected against the SQL injection attack.
		*/
		try {
			Payment payment = (new PaymentDao()).get(1);
			
			assertEquals("1", payment.getPaymentType());
		} catch (SQLException e) {
			fail("Unable to get payment, table potentially deleted.");
		}
	}

}
