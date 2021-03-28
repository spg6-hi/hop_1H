package application;

import java.util.*;

public class DBManagerMock {	
	static Stack<Hotel> hotelStack;
	Stack<Hotel> helpStack = new Stack<Hotel>();
	public DBManagerMock(Stack<Hotel> hotels){
		hotelStack = hotels;
	}
		
	
	//returns list of all hotels as Stack
	Stack<Hotel> getHotelList() {
		Stack<Hotel> result = hotelStack;
		return result;
	}
	
	void printHotelList() {
		Stack<Hotel> temp = hotelStack;
		for(int i = 0;i<temp.size();i++) {
			System.out.println(temp.pop().getHotelName());
		}
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
	
	//adds new hotel to the hotel stack 
	void addHotel(int id, String name, String location){
		hotelStack.push(new Hotel(id, name, location));
	}
	
	//finds hotel by given name
	Stack<Hotel> searchByName(String name){
		Stack<Hotel> result = new Stack<Hotel>();
		Stack<Hotel> hotelList = getHotelList();
		Hotel current;
		for(int i = 0; i < hotelList.size(); i++) {
			current = hotelList.pop();
			if(current.getHotelName().equals(name)) result.push(current);
		}
		return result;
	}
	
	//finds hotels by given location
	Stack<Hotel> searchByLocation(String location){
		Stack<Hotel> result = new Stack<Hotel>();
		Stack<Hotel> hotelList = getHotelList();
		Hotel current;
		for(int i = 0; i < hotelList.size(); i++) {
			current = hotelList.pop();
			if(current.getLocation().equals(location)) result.push(current);
		}
		return result;
	}
}
