package main;

import java.sql.*;

public class MySQL {
	private static final String host= "localhost";
	private static final String port = "3306";
	private static final String database = "bib";
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
	
}
