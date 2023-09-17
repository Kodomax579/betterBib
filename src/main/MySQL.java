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
	private static final String database = "libary";
	private static final String username ="root";
	private static final String password ="";
	
	public static Connection con;

	
	public static boolean isConnected()
	{
		return(con == null ? false : true);
	}
	public static void connect() throws ClassNotFoundException {
	    if (!isConnected()) {
	        try {
	        	Class.forName("com.mysql.cj.jdbc.Driver");
	            con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/", username, password);
	            System.out.println("[MySQL] verbunden");

	         // Ändern Sie die Datenbank
	            Statement statement = con.createStatement();
	            statement.execute("USE libary");

	            
	            // Überprüfen, ob die Datenbank existiert
	            String checkDBSQL = "SELECT SCHEMA_NAME FROM INFORMATION_SCHEMA.SCHEMATA WHERE SCHEMA_NAME = ?";
	            
	            try (PreparedStatement checkDBStatement = con.prepareStatement(checkDBSQL)) {
	                checkDBStatement.setString(1, database);
	                ResultSet resultSet = checkDBStatement.executeQuery();
	            
	                if (!resultSet.next()) {
	                    // Die Datenbank existiert nicht, also erstellen Sie sie
	                	String createDatabaseSQL = "CREATE DATABASE IF NOT EXISTS libary;";

	                	String createTableBookSQL = "CREATE TABLE `book` (\r\n"
	                	        + "  `ID` int(11) NOT NULL,\r\n"
	                	        + "  `titel` varchar(30) NOT NULL,\r\n"
	                	        + "  `author` varchar(30) NOT NULL,\r\n"
	                	        + "  `ISBN` varchar(17) NOT NULL,\r\n"
	                	        + "  `discription` mediumtext NOT NULL,\r\n"
	                	        + "  `date_added` date NOT NULL,\r\n"
	                	        + "  `date_updated` date NOT NULL,\r\n"
	                	        + "  `date_borrow` date DEFAULT NULL,\r\n"
	                	        + "  `genre` varchar(20) NOT NULL,\r\n"
	                	        + "  `customer_ID` int(5) DEFAULT NULL\r\n"
	                	        + ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;";

	                	String createTableBorrowedSQL = "CREATE TABLE `borrowed` (\r\n"
	                	        + "  `ID` int(11) NOT NULL,\r\n"
	                	        + "  `borrowed` date DEFAULT NULL,\r\n"
	                	        + "  `returned` date DEFAULT NULL,\r\n"
	                	        + "  `customer_ID` int(11) NOT NULL,\r\n"
	                	        + "  `Book_ID` int(11) NOT NULL\r\n"
	                	        + ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;";

	                	String createTableCustomerSQL = "CREATE TABLE `customer` (\r\n"
	                	        + "  `ID` int(11) NOT NULL,\r\n"
	                	        + "  `firstname` varchar(30) NOT NULL,\r\n"
	                	        + "  `lastname` varchar(30) NOT NULL,\r\n"
	                	        + "  `username` varchar(30) NOT NULL,\r\n"
	                	        + "  `password` varchar(30) NOT NULL,\r\n"
	                	        + "  `position` int(11) NOT NULL,\r\n"
	                	        + "  `IBAN` varchar(30) NOT NULL\r\n"
	                	        + ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;";

	                	String alterTableBookSQL = "ALTER TABLE `book`\r\n"
	                	        + "  ADD PRIMARY KEY (`ID`),\r\n"
	                	        + "  ADD KEY `customer_book` (`customer_ID`);";

	                	String alterTableBorrowedSQL = "ALTER TABLE `borrowed`\r\n"
	                	        + "  ADD PRIMARY KEY (`ID`),\r\n"
	                	        + "  ADD KEY `customer_borrowed` (`customer_ID`),\r\n"
	                	        + "  ADD KEY `book_borrowed` (`Book_ID`);";

	                	String alterTableCustomerSQL = "ALTER TABLE `customer`\r\n"
	                	        + "  ADD PRIMARY KEY (`ID`);";

	                	String modifyAutoIncrementSQL = "ALTER TABLE `book`\r\n"
	                	        + "  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;";

	                	String modifyAutoIncrementSQL2 = "ALTER TABLE `borrowed`\r\n"
	                	        + "  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=93;";

	                	String modifyAutoIncrementSQL3 = "ALTER TABLE `customer`\r\n"
	                	        + "  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;";

	                	String addForeignKeySQL = "ALTER TABLE `book`\r\n"
	                	        + "  ADD CONSTRAINT `customer_book` FOREIGN KEY (`customer_ID`) REFERENCES `customer` (`ID`);";

	                	try (Statement createDBStatement = con.createStatement()) {
	                	    createDBStatement.executeUpdate(createDatabaseSQL);
	                	    System.out.println("Datenbank erstellt: " + database);

	                	    // Verwenden Sie die Datenbank
	                	    createDBStatement.executeUpdate("USE libary;");
	                	    
	                	    // Erstellen Sie Tabellen
	                	    createDBStatement.executeUpdate(createTableBookSQL);
	                	    createDBStatement.executeUpdate(createTableBorrowedSQL);
	                	    createDBStatement.executeUpdate(createTableCustomerSQL);

	                	    // Ändern Sie Tabellen
	                	    createDBStatement.executeUpdate(alterTableBookSQL);
	                	    createDBStatement.executeUpdate(alterTableBorrowedSQL);
	                	    createDBStatement.executeUpdate(alterTableCustomerSQL);

	                	    // Ändern Sie die AUTO_INCREMENT-Einstellungen
	                	    createDBStatement.executeUpdate(modifyAutoIncrementSQL);
	                	    createDBStatement.executeUpdate(modifyAutoIncrementSQL2);
	                	    createDBStatement.executeUpdate(modifyAutoIncrementSQL3);

	                	    // Fügen Sie Fremdschlüssel hinzu
	                	    createDBStatement.executeUpdate(addForeignKeySQL);

	                	    System.out.println("Tabellen erstellt und bearbeitet.");
	                	}


	                }
	            }
	        } catch (SQLException e) {
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
	public static int isAdmin(int login) {
		// TODO Auto-generated method stub
			PreparedStatement ps;
		
		try {
			ps = con.prepareStatement("SELECT position FROM `customer` WHERE ID = ?");
			ps.setInt(1, login);
			
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
	public static ArrayList<String> getLendBook(int customerID)
	{
		PreparedStatement ps;
		
		ArrayList<String> book = new ArrayList<>();
		
		try {
			ps=con.prepareStatement("SELECT ID,titel,author,ISBN,genre FROM book WHERE customer_ID=?");
			ps.setInt(1, customerID);
			ResultSet result = ps.executeQuery();
			while(result.next())
			{
				book.add(result.getString("ID"));
				book.add(result.getString("titel"));
				book.add(result.getString("author"));
				book.add(result.getString("ISBN"));
				book.add(result.getString("genre"));
			}
			return book;
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	public static boolean ReturnBook(int rowData) {
		
		PreparedStatement ps;
		try {
			ps= con.prepareStatement("UPDATE book SET customer_ID = ?, date_borrow=? WHERE ID = ?");
			ps.setString(1,null);
			ps.setString(2,null);
			ps.setInt(3, rowData);
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	public static boolean historyReturn(int BookiD,int loginID)
	{
		{
			LocalDate currentDate = LocalDate.now();

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

			String formattedDate = currentDate.format(formatter);
			PreparedStatement ps;
			try {
			ps = con.prepareStatement("INSERT INTO `borrowed`( `returned`, `customer_ID`, `Book_ID`) VALUES (?,?,?)");
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
	
	public static boolean update_leser(String data, String Variable, int id) {
	    
        PreparedStatement ps;
       
        try {
        	
            String sql = "UPDATE customer SET " + Variable + " = ? WHERE ID = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, data);
            ps.setInt(2, id);
            int result = ps.executeUpdate();
            return result>0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    return false;
}
	public static boolean InsertBook(String title, String discription, String author, String genre, String isbn) {

		PreparedStatement ps;
		
		LocalDate currentDate = LocalDate.now();

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		String formattedDate = currentDate.format(formatter);
		
		try {
			ps = con.prepareStatement("INSERT INTO `book`(`titel`, `author`, `ISBN`, `discription`, `date_added`, `date_updated`, `genre`) VALUES (?,?,?,?,?,?,?)");
			ps.setString(1, title);
			ps.setString(2, author);
			ps.setString(3, isbn);
			ps.setString(4, discription);
			ps.setString(5, formattedDate);
			ps.setString(6, formattedDate);
			ps.setString(7, genre);
			
			  int rowsInserted = ps.executeUpdate();
		       return rowsInserted > 0;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
	public static boolean DeleteBook(int rowData) {

		PreparedStatement ps;
		try {
			ps= con.prepareStatement("DELETE FROM book WHERE ID=?");
			ps.setInt(1,rowData);
			ps.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	public static ArrayList<String> listOfUsers() {


		ArrayList<String> user = new ArrayList<>();
		
		PreparedStatement ps;
		
		try {
			ps = con.prepareStatement("SELECT ID,firstname,lastname,username FROM customer");
			
			ResultSet result = ps.executeQuery();
			
			
			while(result.next())
			{
				user.add(result.getString("ID"));
				user.add(result.getString("firstname"));
				user.add(result.getString("lastname"));
				user.add(result.getString("username"));
			}
			return user;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public static ArrayList<String> SingleUserData(int iD) {
		ArrayList<String> user = new ArrayList<>();
		
		PreparedStatement ps;
		
		try {
			ps = con.prepareStatement("SELECT firstname,lastname,username,position,IBAN FROM customer WHERE ID = ?");
			ps.setInt(1, iD);
			
			ResultSet result = ps.executeQuery();
			
			
			if(result.next())
			{
				user.add(result.getString("firstname"));
				user.add(result.getString("lastname"));
				user.add(result.getString("username"));
				user.add(result.getString("position"));
				user.add(result.getString("IBAN"));
			}
			return user;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public static boolean DeleteUser(int iD) {

		PreparedStatement ps;
		try {
			ps= con.prepareStatement("DELETE FROM customer WHERE ID=?");
			ps.setInt(1,iD);
			ps.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	public static boolean ToAdmin(int iD) {

		PreparedStatement ps;
		try {
			ps= con.prepareStatement("Update customer SET position = ? WHERE ID = ?");
			ps.setInt(1,1);
			ps.setInt(2,iD);
			ps.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	public static boolean ToCustomer(int iD) {

		PreparedStatement ps;
		try {
			ps= con.prepareStatement("Update customer SET position = ? WHERE ID = ?");
			ps.setInt(1,0);
			ps.setInt(2,iD);
			ps.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	
}
