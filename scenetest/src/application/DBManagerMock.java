package application;

import java.util.*;

public class DBManagerMock {	
	static Stack<Hotel> hotelStack = new Stack<Hotel>();
	Stack<Hotel> helpStack = new Stack<Hotel>();
	
	
	//returns list of all hotels as Stack
	Stack<Hotel> getHotelList() {
		return hotelStack;
	}
	
	//finds and removes a hotel from the hotel stack
	void removeHotel(Hotel hotel) {
		for(int i = 0; i < hotelStack.size(); i++) {
			if(hotelStack.pop() != hotel) helpStack.push(hotelStack.pop());
			hotelStack.pop();
		}
		for(int i = 0; i < helpStack.size(); i++) {
			hotelStack.push(helpStack.pop());
		}
	}
	
	
	
	
}
