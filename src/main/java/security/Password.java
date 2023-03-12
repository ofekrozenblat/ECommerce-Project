package security;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Random;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/**
 * The {@code Password} class is in charge of handling generating secure passwords.
 * Only meant to be used with {@link Authenticator} class.
 * 
 * @author ofekr
 *
 */
public class Password {
	
	private static final Random SECURE_RANDOM = new SecureRandom();
	private static final int ITERATIONS = 10000;
	private static final int KEY_LENGTH = 256;
	
	/**
	 * Generated a secure password from the raw given password.
	 * 
	 * @param password raw password to generate a secure password from
	 * @return secure password in the form of "salt:"hash"
	 * @throws Exception if generating password failed
	 */
	public static String generatePassword(String password) throws Exception {
	    byte[] salt = generateSalt();
	    byte[] hash = generateHash(password, salt);
	    
	    String hashString = DataConverter.bytesToHex(hash);
	    String saltString = DataConverter.bytesToHex(salt);
	    
		return saltString + ":" + hashString;
	}
	
	/**
	 * Generates a hash corresponding to the raw given password and given salt.
	 * 
	 * @param password raw password
	 * @param salt the salt as a byte array to append at the end of the password
	 * @return secure hash corresponding to (password+salt)
	 * @throws Exception if generating the hash failed
	 */
	public static byte[] generateHash(String password, byte[] salt) throws Exception {
		char[] chars = password.toCharArray();
		byte[] hash;
		
	    PBEKeySpec spec = new PBEKeySpec(chars, salt, ITERATIONS, KEY_LENGTH);
	    SecretKeyFactory skf;
	    
		try {
			skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
			hash = skf.generateSecret(spec).getEncoded();
		} catch (NoSuchAlgorithmException e) {
			throw new Exception(e.getMessage());
		} catch (InvalidKeySpecException e) {
			throw new Exception(e.getMessage());
		}
		
		return hash;
	}
	
	/**
	 * Generates a random salt.
	 * 
	 * @return secure random salt as a byte array
	 */
	private static byte[] generateSalt() {
        byte[] salt = new byte[16];
        SECURE_RANDOM.nextBytes(salt);
		return salt;
	}
	
}
