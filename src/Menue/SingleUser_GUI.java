package Menue;

import static javax.swing.JOptionPane.showMessageDialog;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import main.MySQL;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JLabel;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.SwingConstants;
import javax.swing.JMenu;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.JEditorPane;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.beancontext.BeanContextServiceAvailableEvent;
import java.text.DateFormat;

public class SingleUser_GUI extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 * @param login 
	 */
	public SingleUser_GUI(int ID, int login, int position) {

		ArrayList<String> array = MySQL.SingleUserData(ID);
		String firstname = array.get(0);
		String lastname = array.get(1);
		String username= array.get(2);
		String rang= array.get(3);
		String Iban= array.get(4);
		int Rang = Integer.parseInt(rang);
		
		if(Rang == 0)
		{
			rang = "Customer";
		}
		else if(Rang == 1)
		{
			rang = "Admin";
		}
		else if(Rang == 2)
		{
			rang = "ADMIN";
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(458, 11, 33, -13);
		contentPane.add(lblNewLabel);
		
		JLabel rang1 = new JLabel(rang);
		rang1.setHorizontalAlignment(SwingConstants.CENTER);
		rang1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		rang1.setBounds(127, 11, 513, 44);
		contentPane.add(rang1);
		
		JLabel lblNewLabel_1 = new JLabel("Firstname");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(43, 127, 110, 34);
		contentPane.add(lblNewLabel_1);
		
		JLabel Firstname = new JLabel(firstname);
		Firstname.setHorizontalAlignment(SwingConstants.CENTER);
		Firstname.setFont(new Font("Tahoma", Font.PLAIN, 18));
		Firstname.setBounds(163, 117, 513, 44);
		contentPane.add(Firstname);
		
		JLabel lblLastname = new JLabel("Lastname:");
		lblLastname.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblLastname.setBounds(43, 204, 110, 34);
		contentPane.add(lblLastname);
		
		JLabel label2 = new JLabel("Username:");
		label2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label2.setBounds(43, 286, 110, 34);
		contentPane.add(label2);
		
		JLabel Lastname = new JLabel(lastname);
		Lastname.setHorizontalAlignment(SwingConstants.CENTER);
		Lastname.setFont(new Font("Tahoma", Font.PLAIN, 18));
		Lastname.setBounds(164, 194, 513, 44);
		contentPane.add(Lastname);
		
		JLabel Username = new JLabel(username);
		Username.setHorizontalAlignment(SwingConstants.CENTER);
		Username.setFont(new Font("Tahoma", Font.PLAIN, 18));
		Username.setBounds(164, 276, 513, 44);
		contentPane.add(Username);
		
		JButton btnNewButton = new JButton("Back");
		btnNewButton.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) 
			{
					EditProfiles_GUI menue = new EditProfiles_GUI(login, position);
					menue.setVisible(true);
					dispose();
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton.setBounds(127, 416, 130, 34);
		contentPane.add(btnNewButton);
		
		JLabel lblAccesib = new JLabel("IBAN:");
		lblAccesib.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblAccesib.setBounds(43, 359, 110, 34);
		contentPane.add(lblAccesib);
		
		JLabel IBAN = new JLabel(Iban);
		IBAN.setHorizontalAlignment(SwingConstants.CENTER);
		IBAN.setFont(new Font("Tahoma", Font.PLAIN, 18));
		IBAN.setBounds(184, 349, 513, 44);
		contentPane.add(IBAN);
		
		JButton btnNewButton_1 = new JButton("Delete");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				if(ID != login)
				{
					if(Rang != 1 || Rang != 2)
					{
						if(MySQL.DeleteUser(ID))
						{
							showMessageDialog(null, "You deleted the user");
							EditProfiles_GUI EditProfiles = new EditProfiles_GUI(login, position);
							EditProfiles.setVisible(true);
							dispose();
						}
					}
					else {
						showMessageDialog(null, "You are not allowed");
					}
				}
				else {
					showMessageDialog(null, "It is your own Account");
				}
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton_1.setBounds(301, 416, 130, 34);
		contentPane.add(btnNewButton_1);
	}
}
