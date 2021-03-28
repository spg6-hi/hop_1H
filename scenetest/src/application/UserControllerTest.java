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
			new Hotel(0, "Grand Hotel", "Reykjavík"),
			new Hotel(1, "Hotel Hilton", "Reykjavík"),
			new Hotel(2, "Hotel Örk", "Hveragerði"),
			new Hotel(3, "Hotel Keflavík", "Keflavík"),
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
		expected.push(new Hotel(0, "Grand Hotel", "Reykjavík"));
		user.dbManager.printHotelList();
		assertEquals(expected, user.searchByName("Grand Hotel"));
	}
	
	
	@Test
	void SearchByLocationTest() {
		Stack<Hotel> expected = new Stack<Hotel>();
		expected.push(new Hotel(2, "Hotel Örk", "Hveragerði"));
		assertEquals(expected, user.searchByName("Hveragerði"));
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
