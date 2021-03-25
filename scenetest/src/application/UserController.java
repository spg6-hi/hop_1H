package application;

public class UserController {
	Hotel hotelSearch(String SearchString){
		DBManagerMock dbManager = new DBManagerMock();
		
		return dbManager.getHotelList(SearchString);
	}
}
