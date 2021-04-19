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
	
	Stack<Room> getRooms(int hotelId) throws SQLException{
		Stack<Room> results = new Stack<Room>();
		Connection conn = connect();
		PreparedStatement stmt = conn.prepareStatement("SELECT * FROM `rooms` WHERE `hotelid` = ? AND `user` = null");
		stmt.setInt(1, hotelId);
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next())
	    {
			results.push(new Room(rs.getInt("hotelid"),
								  rs.getInt("roomid"),
								  rs.getString("date"),
								  rs.getString("user"),
								  rs.getString("roomtype")
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
