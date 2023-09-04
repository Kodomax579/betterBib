package main;

import Login.Login_GUI;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			MySQL.connect();
			
			
			
			
			Login_GUI Login = new Login_GUI();
			Login.setVisible(true);
			
			
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
