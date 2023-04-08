package dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;
import dao.UserDao;
import factories.ModelFactory;
import model.User;


public class UserDaoTest extends DaoTest {

	private final String TEST_FIRST_NAME = "testFirst";
	private final String TEST_LAST_NAME = "testLast";
	private final String TEST_LAST_EMAIL = "testEmail";
	
	@Test
	void testSave() {
		User testUser = ModelFactory.createUser();
		String generatedEmail = TEST_LAST_EMAIL + generateRandomHex(22) + "@.com";

		testUser.setFirstName(TEST_FIRST_NAME);
		testUser.setLastName(TEST_LAST_NAME);
		testUser.setEmailAddress(generatedEmail);

		int id = testUser.getId();

		// user not in database yet
		assertEquals(id, -1);

		try {
			testUser.save();
		} catch (SQLException e) {
			e.printStackTrace();
			fail("Exception: " + e.getMessage());
		}

		id = testUser.getId();
		// user in the database
		assertNotEquals(id, -1);
	}

	@Test
	void testGetByEmail() {
		UserDao userDao = new UserDao();

		try {
			User testUser = ModelFactory.createUser();
			String generatedEmail = TEST_LAST_EMAIL + generateRandomHex(22) + "@.com";

			testUser.setFirstName(TEST_FIRST_NAME);
			testUser.setLastName(TEST_LAST_NAME);
			testUser.setEmailAddress(generatedEmail);
			testUser.save();
			int testUserId = testUser.getId();
			
			testUser = userDao.getByEmail(generatedEmail);
			assertEquals(testUser.getId(), testUserId);
			assertEquals(testUser.getFirstName(), TEST_FIRST_NAME);
			assertEquals(testUser.getLastName(), TEST_LAST_NAME);
			assertEquals(testUser.getEmailAddress(), generatedEmail);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("Exception: " + e.getMessage());
		}
	}
	
	@Test
	void testGet() {
		UserDao userDao = new UserDao();

		try {
			User testUser = ModelFactory.createUser();
			String generatedEmail = TEST_LAST_EMAIL + generateRandomHex(22) + "@.com";

			testUser.setFirstName(TEST_FIRST_NAME);
			testUser.setLastName(TEST_LAST_NAME);
			testUser.setEmailAddress(generatedEmail);
			testUser.save();
			int testUserId = testUser.getId();
			
			testUser = userDao.get(testUserId);
			assertEquals(testUser.getId(), testUserId);
			assertEquals(testUser.getFirstName(), TEST_FIRST_NAME);
			assertEquals(testUser.getLastName(), TEST_LAST_NAME);
			assertEquals(testUser.getEmailAddress(), generatedEmail);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("Exception: " + e.getMessage());
		}

	}


}
