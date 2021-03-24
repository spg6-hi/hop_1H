/**
 * 
 */
package application;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * @author spg6
 *
 */
class Garbage {

	@Test
	void hotelSearchTest() {
		UserController user = new UserController();
		String result[] = user.hotelSearch("Hveragerði");
	}

}
