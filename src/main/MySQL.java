package main;

import java.sql.*;
import java.sql.Date;
import java.text.DateFormat;
import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter; 

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

	public static int Login(String username, String password)
	{
		PreparedStatement ps;
		
		try {
			ps = con.prepareStatement("SELECT * FROM `customer` WHERE username = ? AND password = ? ");
			ps.setString(1, username);
			ps.setString(2, password);
			
			ResultSet result = ps.executeQuery();
			
			if(result.next())
			{
				
				String id= result.getString("ID");
				int ID= Integer.parseInt(id);
				return ID;
			}
			else
			{
				return 0;
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	public static ArrayList<String> ListOfBooks()
	{
		ArrayList<String> books = new ArrayList<>();
		
		PreparedStatement ps;
		
		try {
			ps = con.prepareStatement("SELECT ID,titel,author,ISBN,genre,customer_ID FROM book");
			
			ResultSet result = ps.executeQuery();
			
			
			while(result.next())
			{
				books.add(result.getString("ID"));
				books.add(result.getString("titel"));
				books.add(result.getString("author"));
				books.add(result.getString("ISBN"));
				books.add(result.getString("genre"));
				books.add(result.getString("customer_ID"));
			}
			return books;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
		
	}
	public static int isAdmin(String username2, String password2) {
		// TODO Auto-generated method stub
			PreparedStatement ps;
		
		try {
			ps = con.prepareStatement("SELECT * FROM `customer` WHERE username = ? AND password = ? ");
			ps.setString(1, username2);
			ps.setString(2, password2);
			
			ResultSet result = ps.executeQuery();
			if(result.next())
			{
				
				String position= result.getString("position");
				int Position= Integer.parseInt(position);
				return Position;
			}
			else
			{
				return -1;
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}
	
	public static ArrayList<String> singleBookData(int id)
	{
		
			ArrayList<String> book = new ArrayList<>();
			
			PreparedStatement ps;
			
			try {
				ps = con.prepareStatement("SELECT titel,author,ISBN,genre,discription,customer_ID  FROM book WHERE ID = ?");
				ps.setInt(1, id);
				
				ResultSet result = ps.executeQuery();
				
				
				if(result.next())
				{
					book.add(result.getString("titel"));
					book.add(result.getString("author"));
					book.add(result.getString("ISBN"));
					book.add(result.getString("genre"));
					book.add(result.getString("discription"));
					book.add(result.getString("customer_ID"));
				}
				return book;
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
	}
	public static boolean lend(int BookiD,int loginID) 
	{
		LocalDate currentDate = LocalDate.now();

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		String formattedDate = currentDate.format(formatter);
		PreparedStatement ps;
		try {
		ps = con.prepareStatement("UPDATE book SET customer_ID = ?, date_borrow=? WHERE ID = ?");
		ps.setInt(1, loginID);
		ps.setString(2, formattedDate);
		ps.setInt(3, BookiD);
		ps.executeUpdate();
		
		return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	public static boolean historyBorrow(int BookiD,int loginID)
	{
		{
			LocalDate currentDate = LocalDate.now();

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

			String formattedDate = currentDate.format(formatter);
			PreparedStatement ps;
			try {
			ps = con.prepareStatement("INSERT INTO `borrowed`( `borrowed`, `customer_ID`, `Book_ID`) VALUES (?,?,?)");
			ps.setString(1, formattedDate);
			ps.setInt(2, loginID);
			ps.setInt(3, BookiD);
			
			ps.executeUpdate();
			
			return true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return false;
		}
	}
	
}
