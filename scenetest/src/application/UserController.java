package application;

import java.sql.SQLException;
import java.util.Stack;

public class UserController {
	DBManager dbManager = new DBManager();
	
	Stack<Hotel> getHotels() throws SQLException{
		return dbManager.getHotelList();
	}
	
	void deleteHotel(Hotel hotel) {
		dbManager.removeHotel(hotel);
	}
	
	void newHotel(String hotelName, String hotelAddress, String location) throws SQLException {
		dbManager.addHotel(hotelName, hotelAddress, location);
	}
	
	Stack<Hotel> searchByName(String name) throws SQLException{
		return dbManager.searchByName(name);
	}
	
	Stack<Hotel> searchByLocation(String location) throws SQLException{
		return dbManager.searchByLocation(location);
	}
}
