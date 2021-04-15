package application;

public class Hotel{
	int hotelId;
	String hotelName;
	String hotelAddress;
	String location;
	public Hotel(int id, String name, String address, String loc) {
		hotelId = id;
		hotelName = name;
		hotelAddress = address;
		location = loc;
	}
	
	
	String getHotelName() {
		return hotelName;
	}
	
	String getAddress() {
		return hotelAddress;
	}
	
	String getLocation() {
		return location;
	}
}
