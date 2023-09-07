package Login;

import static javax.swing.JOptionPane.showMessageDialog;

import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Menue.Menue_GUI;
import main.MySQL;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Login_GUI extends JFrame {

	private JPanel contentPane;
	private JTextField username;
	private JPasswordField password;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public Login_GUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("LOGIN");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel.setBounds(329, 11, 91, 64);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Username:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(79, 192, 151, 34);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Password:");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1_1.setBounds(79, 271, 151, 34);
		contentPane.add(lblNewLabel_1_1);
		
		username = new JTextField();
		username.setBounds(484, 192, 207, 34);
		contentPane.add(username);
		username.setColumns(10);
		
		JButton btnNewButton = new JButton("Sign in");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				String Username = username.getText();
				String Password =String.valueOf(password.getPassword());
				int login = MySQL.Login(Username,Password);
				if(login>0)
				{
					int isAdmin=MySQL.isAdmin(Username,Password);
					System.out.println(isAdmin);
					switch(isAdmin)
					{
					case 0:
						Menue_GUI Menue = new Menue_GUI(login);
						Menue.setVisible(true);	
						dispose();
						break;
					case 1:
						//menue für admin
						break;
					case 2:
						//Menue für Oberadmin
						break;
					default:
						showMessageDialog(null, "Something went wrong");
					}
					
				}
				else
				{
					showMessageDialog(null, "Wrong Password or Username");
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton.setBounds(277, 373, 169, 34);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Sign up");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				SignUp_GUI Signup = new SignUp_GUI();
				Signup.setVisible(true);
				dispose();
			}
		});
		btnNewButton_1.setBounds(602, 382, 89, 23);
		contentPane.add(btnNewButton_1);
		
		password = new JPasswordField();
		password.setBounds(484, 271, 207, 31);
		contentPane.add(password);
	}
}
