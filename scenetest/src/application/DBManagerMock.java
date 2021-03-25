package application;

public class DBManagerMock {
	Hotel[] hotels = new Hotel[] {
	new Hotel(0, "Grand Hotel", "Reykjavík"),
	new Hotel(1, "Hotel Hilton", "Reykjavík"),
	new Hotel(2, "Hotel Örk", "Hveragerði"),
	new Hotel(3, "Hotel Keflavík", "Keflavík"),
	new Hotel(4, "Icelandair Hotel", "Akureyri")
	};
	
	Hotel getHotelList(String SearchString) {
		for(int i = 0; i < hotels.length; i++) {
			
		}
		return null;
	}
}
