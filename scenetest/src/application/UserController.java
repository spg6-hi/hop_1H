package application;

import java.util.Stack;

public class UserController {
	DBManagerMock dbManager;
	
	Stack<Hotel> getHotels(){
		return dbManager.getHotelList();
	}
	
	void deleteHotel(Hotel hotel) {
		dbManager.removeHotel(hotel);
	}
	
	void newHotel(int hotelId, String hotelName, String location) {
		dbManager.addHotel(hotelId, hotelName, location);
	}
	
	Stack<Hotel> searchByName(String name){
		return dbManager.searchByName(name);
	}
	
	Stack<Hotel> searchByLocation(String location){
		return dbManager.searchByLocation(location);
	}
}
