package application;

public class DBManagerMock {
	Hotel[] hotels = new Hotel[] {
	new Hotel(0, "Grand Hotel", "Reykjav�k"),
	new Hotel(1, "Hotel Hilton", "Reykjav�k"),
	new Hotel(2, "Hotel �rk", "Hverager�i"),
	new Hotel(3, "Hotel Keflav�k", "Keflav�k"),
	new Hotel(4, "Icelandair Hotel", "Akureyri")
	};
	
	Hotel getHotelList(String SearchString) {
		for(int i = 0; i < hotels.length; i++) {
			
		}
		return null;
	}
}
