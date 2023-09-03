package Login;

import static javax.swing.JOptionPane.showMessageDialog;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import main.MySQL;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUp_GUI extends JFrame {

	private JPanel contentPane;
	private JTextField firstname;
	private JTextField lastname;
	private JTextField username;
	private JTextField iban;
	private JPasswordField password;
	
	private String Firstname;
	private String Lastname;
	private String Username;
	private String IBAN;
	private String Password;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public SignUp_GUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Sign up");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel.setBounds(299, 10, 129, 31);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Firstname:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(60, 114, 119, 31);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Lastname:");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1_1.setBounds(60, 169, 119, 31);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Username:");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1_2.setBounds(60, 222, 119, 31);
		contentPane.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("Password:");
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1_3.setBounds(60, 278, 119, 31);
		contentPane.add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_4 = new JLabel("IBAN:");
		lblNewLabel_1_4.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1_4.setBounds(60, 335, 119, 31);
		contentPane.add(lblNewLabel_1_4);
		
		firstname = new JTextField();
		firstname.setColumns(10);
		firstname.setBounds(421, 114, 207, 34);
		contentPane.add(firstname);
		
		lastname = new JTextField();
		lastname.setColumns(10);
		lastname.setBounds(421, 169, 207, 34);
		contentPane.add(lastname);
		
		username = new JTextField();
		username.setColumns(10);
		username.setBounds(421, 222, 207, 34);
		contentPane.add(username);
		
		iban = new JTextField();
		iban.setColumns(10);
		iban.setBounds(421, 335, 207, 34);
		contentPane.add(iban);
		
		password = new JPasswordField();
		password.setBounds(421, 278, 207, 31);
		contentPane.add(password);
		
		JButton btnNewButton = new JButton("Accept");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				
				
				if (firstname.getText().isEmpty() || lastname.getText().isEmpty() || password.getText().isEmpty()
						|| username.getText().isEmpty() || iban.getText().isEmpty()) 
					{
						showMessageDialog(null, "Write in every box");
					} else{

						Firstname = firstname.getText();
						Lastname = lastname.getText();
						Username = username.getText();
						String Password =String.valueOf(password.getPassword());
						IBAN = iban.getText();
						String ibanPattern = "^(?i)([A-Z]{2}\\d{2})(\\s?\\d{4}){4}(\\s?\\d{1,4})?$";
						
						Pattern ibanpattern = Pattern.compile(ibanPattern);
						Matcher ibanmatcher = ibanpattern.matcher(IBAN);
							
						if(ibanmatcher.matches())
						{
							if (MySQL.InsertNewUser(Firstname, Lastname, Username, Password, IBAN)) {
								
						        Login_GUI Login = new Login_GUI();
						        Login.setVisible(true);
						        dispose();
						    } else {
						        showMessageDialog(null, "This Account already exists");
						    }
						}
						else
						{
							showMessageDialog(null, "IBAN is wrong");
						}
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton.setBounds(286, 395, 164, 34);
		contentPane.add(btnNewButton);
	}
}
