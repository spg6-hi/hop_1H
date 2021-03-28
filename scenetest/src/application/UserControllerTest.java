package application;

import java.util.*;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class UserControllerTest {
	static Stack<Hotel> hotelStack = new Stack<Hotel>();
	static UserController user = new UserController();
	
	static Hotel[] hotels = new Hotel[] {
			new Hotel(0, "Grand Hotel", "Reykjav�k"),
			new Hotel(1, "Hotel Hilton", "Reykjav�k"),
			new Hotel(2, "Hotel �rk", "Hverager�i"),
			new Hotel(3, "Hotel Keflav�k", "Keflav�k"),
			new Hotel(4, "Icelandair Hotel", "Akureyri")
	};
	
	
	
	
	
	@BeforeEach
	void setUp() {
		for(int i = 0; i< hotels.length;i++){
			hotelStack.push(hotels[i]);
			//System.out.println(hotels[i]);
		}
		user.dbManager = new DBManagerMock(hotelStack);
	}
	
	

	@AfterEach
	void tearDown() {
		user.dbManager = null;
		for(int i = 0; i< hotelStack.size()-1;i++) {
			hotelStack.pop();
		}
	}
	
	
	
	@Test
	void getHotelsTest() {
		assertEquals(hotelStack, user.getHotels());
	}
	
		
	@Test
	void SearchByNameTest() {
		Stack<Hotel> expected = new Stack<Hotel>();
		expected.push(new Hotel(0, "Grand Hotel", "Reykjav�k"));
		user.dbManager.printHotelList();
		assertEquals(expected, user.searchByName("Grand Hotel"));
	}
	
	
	@Test
	void SearchByLocationTest() {
		Stack<Hotel> expected = new Stack<Hotel>();
		expected.push(new Hotel(2, "Hotel �rk", "Hverager�i"));
		assertEquals(expected, user.searchByName("Hverager�i"));
	}
	
	@Test
	void deleteHotelTest() {
		Stack<Hotel> expected = new Stack<Hotel>();
	}
	
	@Test
	void addNewHotelTest() {
		Stack<Hotel> expected = new Stack<Hotel>();
	}
}
