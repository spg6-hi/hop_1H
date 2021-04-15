package application;

public class Hotel{
	int hotelId;
	String hotelName;
<<<<<<< HEAD
	String hotelAddress;
	String location;
	public Hotel(int id, String name, String address, String loc) {
		hotelId = id;
		hotelName = name;
		hotelAddress = address;
		location = loc;
=======
	String location;
	public Hotel(int id, String name, String loc) {
		this.hotelId = id;
		this.hotelName = name;
		this.location = loc;
>>>>>>> refs/remotes/origin/main
	}
	
	
	String getHotelName() {
<<<<<<< HEAD
		return hotelName;
	}
	
	String getAddress() {
		return hotelAddress;
=======
		return this.hotelName;
>>>>>>> refs/remotes/origin/main
	}
	
	String getLocation() {
<<<<<<< HEAD
		return location;
=======
		return this.location;
>>>>>>> refs/remotes/origin/main
	}
}
