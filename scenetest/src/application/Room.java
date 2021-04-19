package application;

public class Room{
	int HotelId;
	int RoomId;
	String Date;
	String guestName;
	String roomType;
	public Room(int hotelID, int RoomID, String date, String GuestName, String RoomType) {
		HotelId = hotelID;
		RoomId = RoomID;
		Date = date;
		guestName = GuestName;
		roomType = RoomType;
	}
	
	int getHotelId() {
		return HotelId;
	}
	
	int getId() {
		return RoomId;
	}
	
	String getDate() {
		return Date;
	}
	
	String getGuest() {
		return guestName;
	}
	
	String getRoomType() {
		return roomType;
	}
}
