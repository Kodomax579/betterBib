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
import javax.swing.JTextArea;

public class NewBook_GUI2 extends JFrame {

	private JPanel contentPane;
	private JTextField title;
	private JTextField author;
	private JTextField isbn;
	private JTextField genre;
	
	
	/**
	 * Launch the application.
	 */
	/**
	 * Create the frame.
	 */
	public NewBook_GUI2(int login, int position) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Update data");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 23));
		lblNewLabel.setBounds(326, 11, 148, 27);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Title:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_1.setBounds(55, 74, 90, 27);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Author:");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_1_1.setBounds(55, 127, 90, 27);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("ISBN:");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_1_2.setBounds(55, 179, 90, 27);
		contentPane.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("Discription:");
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_1_3.setBounds(55, 282, 90, 27);
		contentPane.add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_4 = new JLabel("Genre:");
		lblNewLabel_1_4.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_1_4.setBounds(55, 227, 90, 27);
		contentPane.add(lblNewLabel_1_4);
		
		
		title = new JTextField();
		title.setBounds(333, 77, 178, 27);
		contentPane.add(title);
		title.setColumns(10);
		
		author = new JTextField();
		author.setColumns(10);
		author.setBounds(333, 130, 178, 27);
		contentPane.add(author);
		
		isbn = new JTextField();
		isbn.setColumns(10);
		isbn.setBounds(333, 182, 178, 27);
		contentPane.add(isbn);
		
		genre = new JTextField();
		genre.setColumns(10);
		genre.setBounds(333, 230, 178, 27);
		contentPane.add(genre);
		
		JButton btnNewButton = new JButton("Accept");

		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnNewButton.setBounds(604, 74, 136, 37);
		contentPane.add(btnNewButton);
		
		JTextArea discription = new JTextArea();
		discription.setBounds(228, 286, 284, 116);
		contentPane.add(discription);
		
		JButton btnBack = new JButton("Back");
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				if(position == 1 || position ==2)
				{
					AdminMeneu_GUI menue = new AdminMeneu_GUI(login, position);
					menue.setVisible(true);
					dispose();
				}
				
			}
		});
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnBack.setBounds(604, 401, 136, 37);
		contentPane.add(btnBack);
		
		btnNewButton.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) 
		    {
		    	String Title = title.getText();
		    	String Discription = discription.getText();
		    	String Author = author.getText();
		    	String Genre = genre.getText();
		    	String ISBN = isbn.getText();
		    	
		    	
		    	if(!Title.isEmpty() && !Discription.isEmpty() && !Author.isEmpty() && !Genre.isEmpty() && !ISBN.isEmpty())
		    	{
		    		if(MySQL.InsertBook(Title,Discription,Author,Genre,ISBN))
		    		{
		    			showMessageDialog(null, "New Book got added");
		    			AdminMeneu_GUI Adminmenue = new AdminMeneu_GUI(login, position);
		    			Adminmenue.setVisible(true);
		    			dispose();
		    		}
		    		
		    	}
		    	else
		    	{
		    		showMessageDialog(null, "Evry box have to be filled");
		    	}
			}

		});
	}
}
