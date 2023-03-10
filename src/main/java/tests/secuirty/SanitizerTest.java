package tests.secuirty;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import security.Sanitizer;

class SanitizerTest {

	@Test
	void testSanitizingInput() {
		String testString = "test input";
		
		// Tests when user input has rich text (includes html)
		String testHtml = "<p>" + testString + "<p>";
		
		// Tests when user input has malicious script
		String testMaliciousScript = "<p> <script>badStuff()</script>" + testString + "</p>";
		
		assertEquals(testString, Sanitizer.clean(testHtml));
		assertEquals(testString, Sanitizer.clean(testMaliciousScript));
	}

}
