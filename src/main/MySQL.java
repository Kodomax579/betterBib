package main;

import java.sql.*;

public class MySQL {
	private static final String host= "localhost";
	private static final String port = "3306";
	private static final String database = "better_bib";
	private static final String username ="root";
	private static final String password ="";
	
	public static Connection con;
	
	
	public static boolean isConnected()
	{
		return(con == null ? false : true);
	}
	public static void connect()throws ClassNotFoundException
	{
		if(!isConnected())
		{
			try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
				con = DriverManager.getConnection("jdbc:mysql://"+host+":"+port+"/"+database, username , password );
				System.out.println("[MySQL] verbunden");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	public static void disconnect(){
		if(isConnected())
		{
			try {
				con.close();
				System.out.println("[MySQL] geschlossen");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public static boolean InsertNewUser(String firstname, String lastname, String username, String password, String iBAN) {
	    PreparedStatement ps;
	    try {
	        ps = con.prepareStatement("INSERT INTO `customer`(`firstname`, `lastname`, `username`, `password`, `position`, `IBAN`) VALUES (?, ?, ?, ?, ?, ?)");
	        ps.setString(1, firstname);
	        ps.setString(2, lastname);
	        ps.setString(3, username);
	        ps.setString(4, password);
	        ps.setInt(5, 0);
	        ps.setString(6, iBAN);

	        int rowsInserted = ps.executeUpdate();
	        return rowsInserted > 0;

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return false;
	}

	public static boolean Login(String username, String password)
	{
		PreparedStatement ps;
		
		try {
			ps = con.prepareStatement("SELECT * FROM `customer` WHERE username = ? AND password = ? ");
			ps.setString(1, username);
			ps.setString(2, password);
			
			ResultSet result = ps.executeQuery();
			
			if(result.next())
			{
				return true;
			}
			else
			{
				return false;
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
}
