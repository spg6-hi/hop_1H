package application;

public class Hotel{
	HotelMock hotel = new HotelMock();
	public Hotel(int id, String name, String loc) {
		hotel.hotelId = id;
		hotel.hotelName = name;
		hotel.location = loc;
	}
}
