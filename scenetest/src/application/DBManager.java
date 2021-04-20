package application;

import java.sql.*;
import java.util.Stack;

public class DBManager {
	
	static Connection connect() {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hoteldb?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC", "root", "Hop1h");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	public String DateReverse(String dma) {
        return dma.substring(dma.length()-2,dma.length()) + dma.substring(dma.length()-5,dma.length()-3) + dma.substring(0, 4);
    }
	
	void addHotel(String name, String address, String location) throws SQLException {
		Connection conn = connect();
		PreparedStatement stmt = conn.prepareStatement("insert into `Hotels` values(default,?,?,?)");
		stmt.setString(1, name);
		stmt.setString(2, address);
		stmt.setString(3, location);
		stmt.executeUpdate();
	}
	
	
	
	
	Stack<Hotel> search(String name ,String location) throws SQLException{
		Stack<Hotel> results = new Stack<Hotel>();
		if(name.equals("null") && location.equals("null")) return getHotelList();
		Connection conn = connect();
		String query = "SELECT * FROM `Hotels` WHERE ";
        if(!name.equals("null")) query += "`name` = ? AND ";
        if(!location.equals("null")) query += "`location` = ? AND ";
        if(query.substring(query.length() - 4, query.length()).equals("AND ")) query = query.substring(0, query.length() - 5);
		PreparedStatement stmt = conn.prepareStatement(query);
		
		stmt.setString(1, name);
		if(name.equals("null") && !location.equals("null")) stmt.setString(1, location);
		if(!name.equals("null") && !location.equals("null")) stmt.setString(2, location);
		
		ResultSet rs = stmt.executeQuery();
		while(rs.next())
	    {
			results.push(new Hotel(rs.getInt("id"), 
								   rs.getString("name"), 
								   rs.getString("address"), 
								   rs.getString("location")
						));
	    }
	    rs.close();
	    System.out.println(results.isEmpty());
	    System.out.println(query);
	    System.out.println("name: " + name);
	    System.out.println("location: " + location);
		return results;
	}
	
	
	Stack<String> getDates(int hotelId, String roomType) throws SQLException{
		Stack<String> results = new Stack<String>();
		Connection conn = connect();
		PreparedStatement stmt = conn.prepareStatement("SELECT `date` FROM `rooms` WHERE `hotelid` = ? AND `roomtype` = ?");
		stmt.setInt(1, hotelId);
		stmt.setString(2, roomType);
		ResultSet rs = stmt.executeQuery();
		while(rs.next())
	    {
			results.push(rs.getDate("date").toString());
	    }
		rs.close();
		return results;
	}
	
	Stack<String> getRooms(int hotelId) throws SQLException{
		Stack<String> results = new Stack<String>();
		Connection conn = connect();
		PreparedStatement stmt = conn.prepareStatement("SELECT DISTINCT `roomtype` FROM `rooms` WHERE `hotelid` = ? AND `user` = '' ORDER BY `roomtype`");
		stmt.setInt(1, hotelId);
		ResultSet rs = stmt.executeQuery();
		while(rs.next())
	    {
			results.push(rs.getString("roomtype"));
	    }
		rs.close();
		return results;
	}
	
	void bookRoom(int hotelId, String date, String roomType, String user) throws SQLException {
		Connection conn = connect();
		PreparedStatement stmt = conn.prepareStatement("SELECT * FROM `rooms` WHERE `hotelid` = ? AND `date` = ? AND `user` = '' AND `roomtype` = ?");
		stmt.setInt(1, hotelId);
		stmt.setString(2, date);
		stmt.setString(3, roomType);
		ResultSet rs = stmt.executeQuery();
		rs.next();
		String bookDate = rs.getDate("date").toString();
		int bookRoom = rs.getInt("roomid");
		rs.close();
		stmt = conn.prepareStatement("UPDATE `rooms` SET `user` = ? WHERE `roomid` = ? AND `date` = ?");
		stmt.setString(3, bookDate);
		stmt.setInt(2, bookRoom);
		stmt.setString(1, user);
		stmt.executeUpdate();
	}
	
	
	
	void removeBooking(int hotelId, String date, String roomType, String user) throws SQLException {
		Connection conn = connect();
		PreparedStatement stmt = conn.prepareStatement("SELECT * FROM `rooms` WHERE `hotelid` = ? AND `date` = ? AND `user` = ? AND `roomtype` = ?");
		stmt.setInt(1, hotelId);
		stmt.setString(2, date);
		stmt.setString(3, user);
		stmt.setString(4, roomType);
		ResultSet rs = stmt.executeQuery();
		rs.next();
		String bookDate = rs.getDate("date").toString();
		int bookRoom = rs.getInt("roomid");
		rs.close();
		stmt = conn.prepareStatement("UPDATE `rooms` SET `user` = '' WHERE `roomid` = ? AND `date` = ?");
		stmt.setString(2, bookDate);
		stmt.setInt(1, bookRoom);
		stmt.executeUpdate();
	}
	
	Stack<Room> getBookings(String user) throws SQLException{
		Stack<Room> results = new Stack<Room>();
		Connection conn = connect();
		PreparedStatement stmt = conn.prepareStatement("SELECT * FROM `rooms` INNER JOIN `hotels` WHERE `id` = `hotelid`AND `user` = ?");
		stmt.setString(1, user);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			results.push(new Room(rs.getInt("hotelid"),
								  rs.getString("name"),
								  rs.getInt("roomid"),
								  rs.getDate("date").toString(),
								  rs.getString("user"),
								  rs.getString("Roomtype")
					    ));
		}
		return results;
	}
	
	
	Stack<Hotel> getHotelList() throws SQLException {
		Stack<Hotel> results = new Stack<Hotel>();
		Connection conn = connect();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("select * from `Hotels`");
		while(rs.next())
	    {
			results.push(new Hotel(rs.getInt("id"), 
								   rs.getString("name"), 
								   rs.getString("address"), 
								   rs.getString("location")
						));
	    }
	    rs.close();
	    return results;
	}
}
