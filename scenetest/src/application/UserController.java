package application;

import java.sql.SQLException;
import java.util.Stack;

public class UserController {
	static DBManager dbManager = new DBManager();
	
	Stack<Hotel> getHotels() throws SQLException{
		return dbManager.getHotelList();
	}
	
	Stack<Hotel> search(String name, String loacation) throws SQLException{
		return dbManager.search(name, loacation);
	}

	
	void newHotel(String hotelName, String hotelAddress, String location) throws SQLException {
		dbManager.addHotel(hotelName, hotelAddress, location);
	}
	
	/*public static void main(String[] args) throws ClassNotFoundException, SQLException
	{
		for(int i=0;i < 4; i++) {
			newHotel("testhotel" + String.valueOf(i), "testaddress"+ String.valueOf(i), "testlocation"+ String.valueOf(i));
		}
		
		Stack<Hotel> hotels = search("testhotel0", "null");
		System.out.println(hotels.isEmpty());
		for(int i=0;i < hotels.size(); i++) {
			System.out.println(hotels.pop().getHotelName());
		}
		
	}*/
}
