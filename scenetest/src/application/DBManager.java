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
	
	void addHotel(String name, String address, String location) throws SQLException {
		Connection conn = connect();
		PreparedStatement stmt = conn.prepareStatement("insert into `Hotels` values(default,?,?,?)");
		stmt.setString(1, name);
		stmt.setString(2, address);
		stmt.setString(3, location);
		stmt.executeUpdate();
	}
	
	Stack<Hotel> searchByName(String name) throws SQLException{
		Stack<Hotel> results = new Stack<Hotel>();
		Connection conn = connect();
		PreparedStatement stmt = conn.prepareStatement("SELECT * FROM `Hotels` WHERE `name` = ?");
		stmt.setString(1, name);
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
		return results;
	}
	
	
	Stack<Hotel> searchByLocation(String location) throws SQLException{
		Stack<Hotel> results = new Stack<Hotel>();
		Connection conn = connect();
		PreparedStatement stmt = conn.prepareStatement("SELECT * FROM `Hotels` WHERE `location` = ?");
		stmt.setString(1, location);
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
