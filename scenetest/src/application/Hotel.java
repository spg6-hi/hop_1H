package application;

public class Hotel{
	int hotelId;
	String hotelName;
	String location;
	public Hotel(int id, String name, String loc) {
		this.hotelId = id;
		this.hotelName = name;
		this.location = loc;
	}
	String getHotelName() {
		return this.hotelName;
	}
	
	String getLocation() {
		return this.location;
	}
}
