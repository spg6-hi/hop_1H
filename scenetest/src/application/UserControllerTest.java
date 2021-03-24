/**
 * 
 */
package application;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * @author stull
 *
 */
class UserControllerTest {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		UserController user = new UserController();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterAll
	static void tearDownAfterClass() throws Exception {
		UserController user = null;
	}

	@Test
	void hotelSearchTest() {
		String result[] = user.hotelSearch("Hveragerði");
		assertArrayEquals(["hótel1","hótel2"], result);
	}
}
