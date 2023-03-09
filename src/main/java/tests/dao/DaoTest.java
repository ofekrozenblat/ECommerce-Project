package tests.dao;

import java.io.File;
import java.util.Random;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import hthurow.tomcatjndi.TomcatJNDI;

public class DaoTest {
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
    
    /**
     * Generates random hex value to use for testing for unique requirements.
     * @param length
     * @return random hex string value with the provided length
     */
    protected static String generateRandomHex(int length) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        while (sb.length() < length) {
            sb.append(Integer.toHexString(random.nextInt()));
        }
        return sb.toString().substring(0, length).toUpperCase();
    }
}
