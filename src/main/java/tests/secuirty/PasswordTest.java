package tests.secuirty;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import security.Password;

class PasswordTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@Test
	void testGeneratingPassword() {
		String rawPassword = "kilodgy12kd";
		try {
			String processedPassword = Password.generatePassword(rawPassword);
			System.out.println(processedPassword);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		fail("Only used for generation testing, no assertions needed");
	}

}
