package Menue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Login.Login_GUI;
import main.MySQL;

import javax.swing.JLabel;

import static javax.swing.JOptionPane.showMessageDialog;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JPasswordField;

public class Update_GUI extends JFrame {

	private JPanel contentPane;
	private JTextField firstname;
	private JTextField lastname;
	private JTextField username;
	private JTextField IBAN;
	private JPasswordField password;
	
	
	/**
	 * Launch the application.
	 */
	/**
	 * Create the frame.
	 */
	public Update_GUI(int login, int position) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 680, 418);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Update data");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 23));
		lblNewLabel.setBounds(235, 10, 148, 27);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Firstname");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_1.setBounds(55, 74, 90, 27);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Lastname");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_1_1.setBounds(55, 127, 90, 27);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Username");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_1_2.setBounds(55, 179, 90, 27);
		contentPane.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("Passoword");
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_1_3.setBounds(55, 244, 90, 27);
		contentPane.add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_4 = new JLabel("IBAN");
		lblNewLabel_1_4.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_1_4.setBounds(55, 306, 90, 27);
		contentPane.add(lblNewLabel_1_4);
		
		firstname = new JTextField();
		firstname.setBounds(164, 74, 178, 27);
		contentPane.add(firstname);
		firstname.setColumns(10);
		
		lastname = new JTextField();
		lastname.setColumns(10);
		lastname.setBounds(164, 127, 178, 27);
		contentPane.add(lastname);
		
		username = new JTextField();
		username.setColumns(10);
		username.setBounds(164, 179, 178, 27);
		contentPane.add(username);
		
		IBAN = new JTextField();
		IBAN.setColumns(10);
		IBAN.setBounds(164, 306, 347, 27);
		contentPane.add(IBAN);
		
		JButton btnNewButton = new JButton("Accept");
		btnNewButton.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        String firstNameText = firstname.getText();
		        String lastNameText = lastname.getText();
		        String Username = username.getText();
		        String Password =String.valueOf(password.getPassword());
		        String ibanText = IBAN.getText();

		        String ibanPattern = "^(?i)([A-Z]{2}\\d{2})(\\s?\\d{4}){4}(\\s?\\d{1,4})?$";
				
				Pattern ibanpattern = Pattern.compile(ibanPattern);
				Matcher ibanmatcher = ibanpattern.matcher(ibanText);
					   
			        boolean a = false , b = false, c = false, d = false, f = false;

			        if (!firstNameText.isEmpty()) {
			        	
			        	a = MySQL.update_leser(firstNameText, "firstname", login);
			        }
			        if (!lastNameText.isEmpty()) {
			        	b = MySQL.update_leser(lastNameText, "lastname", login);
			        }
			        if (!Username.isEmpty()) {
			        	c = MySQL.update_leser(Username, "username", login);
			        }
			        if (!Password.isEmpty()) {
			        	d = MySQL.update_leser(Password, "password", login);
			        }
			        if (!ibanText.isEmpty()) {
			        	if(ibanmatcher.matches())
						{
			        	
			        	
			        	f = MySQL.update_leser(ibanText, "IBAN", login);
						}
			        	else
						{
							showMessageDialog(null, "IBAN is wrong");
						}
			        }

			        if (a || b || c || d || f) 
			        {
			            showMessageDialog(null, "Updated");
			            
			            if(position == 0)
						{
							Menue_GUI menue = new Menue_GUI(login,position);
							menue.setVisible(true);
							dispose();
						}
						if(position == 1 || position ==2)
						{
							AdminMeneu_GUI menue = new AdminMeneu_GUI(login, position);
							menue.setVisible(true);
							dispose();
						}
			        } 
			        else {
			            showMessageDialog(null, "Something went wrong");
			        }
				}

		});

		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnNewButton.setBounds(486, 80, 136, 37);
		contentPane.add(btnNewButton);
		
		password = new JPasswordField();
		password.setBounds(164, 244, 178, 27);
		contentPane.add(password);
		
		JButton btnBack = new JButton("Back");
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				if(position == 0)
				{
					Menue_GUI menue = new Menue_GUI(login,position);
					menue.setVisible(true);
					dispose();
				}
				if(position == 1|| position ==2)
				{
					AdminMeneu_GUI menue = new AdminMeneu_GUI(login, position);
					menue.setVisible(true);
					dispose();
				}
			}
		});
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnBack.setBounds(518, 331, 136, 37);
		contentPane.add(btnBack);
	}
}
