package tests.secuirty;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import hthurow.tomcatjndi.TomcatJNDI;
import security.Authenticator;

class AuthenticatorTest {

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
	void testRegister() {
		try {
			String password = "hello123";
			String falsePassword = "heLlo123";
			
			Authenticator.registerUser(password, 1);
			
			assertTrue(Authenticator.validateUser(password, 1));
			assertFalse(Authenticator.validateUser(falsePassword, 1));
			
		} catch (Exception e) {
			e.printStackTrace();
			fail("Error: " + e.getMessage());
		}
	}

}
