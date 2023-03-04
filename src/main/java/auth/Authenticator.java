package auth;

import java.sql.SQLException;

import dao.UserDao;

/**
 * {@code Authenticator} class is in charge of doing authentication checks involving users and
 * provided passwords.
 * 
 * @author ofekr
 *
 */
public class Authenticator {
	
	/**
	 * Registers the given user with id {@code userId} with the given {@code password} in
	 * the application.
	 * 
	 * @param password the user provided password
	 * @param userId the id of the user
	 * @throws Exception
	 */
	public static void registerUser(String password, int userId) throws Exception {
		String generatedPassword = Password.generatePassword(password);
		(new UserDao()).storePassword(generatedPassword, userId);
	}
	
	/**
	 * Validates whether the provided password is associated with a user with id {@code userId}.
	 * 
	 * @param password the raw password to check against the user account
	 * @param userId the id of the user
	 * @return true if password matches the user's account, false otherwise
	 * @throws Exception
	 */
	public static boolean validateUser(String password, int userId) throws Exception {
		String storedPassword;
		try {
			storedPassword = (new UserDao()).getStoredPassword(userId);
		} catch (SQLException e) {
			throw e;
		}
		
		return validatePassword(password, storedPassword);
	}
	
	/**
	 * Validates whether a given password matches the expected stored password (stored 
	 * password has the format "salt":"hash").
	 * 
	 * @param givenPassword raw password string
	 * @param storedPassword the password stored in the database in the format "salt":"hash"
	 * @return true if given password matches the stored password, false otherwise
	 * @throws Exception
	 */
	private static boolean validatePassword(String givenPassword, String storedPassword)
			throws Exception {
		String[] expectedParts = storedPassword.split(":");
		
		byte[] salt = DataConverter.hexToBytes(expectedParts[0]);
		byte[] expectedHash = DataConverter.hexToBytes(expectedParts[1]);
		
		byte[] generatedHash = Password.generateHash(givenPassword, salt);
		
		if (expectedHash.length != generatedHash.length) {
			return false;
		}
		
		for (int i = 0; i < generatedHash.length; i++) {
			if (generatedHash[i] != expectedHash[i]) {
				return false;
			}
		}
		
		return true;
	}
	
}
