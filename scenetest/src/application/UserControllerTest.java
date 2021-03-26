package application;

import java.util.*;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


class UserControllerTest {
	static Stack<Hotel> hotelStack = new Stack<Hotel>();
	static Hotel[] hotels = new Hotel[] {
			new Hotel(0, "Grand Hotel", "Reykjav�k"),
			new Hotel(1, "Hotel Hilton", "Reykjav�k"),
			new Hotel(2, "Hotel �rk", "Hverager�i"),
			new Hotel(3, "Hotel Keflav�k", "Keflav�k"),
			new Hotel(4, "Icelandair Hotel", "Akureyri")
	};
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		UserController user = new UserController();
		for(int i = 0; i< hotels.length;i++){
			hotelStack.push(hotels[i]);
		}
		
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		UserController user = null;
	}
	@Test
	void getHotelsTest() {
		
	}
	
	
	@Test
	void hotelSearchTest() {
		
	}
	
	@Test
	void removeHotelTest() {
		
	}
	
	@Test
	void addNewHotelTest() {
		
	}
}
