package Menue;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import main.MySQL;

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

public class singleBook_GUI extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 * @param login 
	 */
	public singleBook_GUI(int ID, int login) {

		ArrayList<String> array = MySQL.singleBookData(ID);
		String title = array.get(0);
		String author1 = array.get(1);
		String isbn= array.get(2);
		String genre= array.get(3);
		String discription= array.get(4);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(458, 11, 33, -13);
		contentPane.add(lblNewLabel);
		
		JLabel titel = new JLabel(title);
		titel.setHorizontalAlignment(SwingConstants.CENTER);
		titel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		titel.setBounds(127, 11, 513, 44);
		contentPane.add(titel);
		
		JLabel lblNewLabel_1 = new JLabel("Author:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(43, 71, 110, 34);
		contentPane.add(lblNewLabel_1);
		
		JLabel author = new JLabel(author1);
		author.setHorizontalAlignment(SwingConstants.CENTER);
		author.setFont(new Font("Tahoma", Font.PLAIN, 18));
		author.setBounds(164, 66, 513, 44);
		contentPane.add(author);
		
		JLabel label = new JLabel("Genre");
		label.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label.setBounds(43, 149, 110, 34);
		contentPane.add(label);
		
		JLabel label2 = new JLabel("ISBN:");
		label2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label2.setBounds(43, 224, 110, 34);
		contentPane.add(label2);
		
		JLabel label3 = new JLabel("Discription:");
		label3.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label3.setBounds(43, 310, 110, 34);
		contentPane.add(label3);
		
		JLabel Genre = new JLabel(genre);
		Genre.setHorizontalAlignment(SwingConstants.CENTER);
		Genre.setFont(new Font("Tahoma", Font.PLAIN, 18));
		Genre.setBounds(164, 149, 513, 44);
		contentPane.add(Genre);
		
		JLabel ISBN = new JLabel(isbn);
		ISBN.setHorizontalAlignment(SwingConstants.CENTER);
		ISBN.setFont(new Font("Tahoma", Font.PLAIN, 18));
		ISBN.setBounds(164, 224, 513, 44);
		contentPane.add(ISBN);
		
		JTextPane textPane = new JTextPane();
		textPane.setEditable(false);
		textPane.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textPane.setBounds(174, 279, 513, 118);
		contentPane.add(textPane);
		textPane.setText(discription);
		
		JButton btnNewButton = new JButton("Back");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				Menue_GUI menue = new Menue_GUI(login);
				menue.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton.setBounds(127, 416, 130, 34);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("lend");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton_1.setBounds(440, 416, 130, 34);
		contentPane.add(btnNewButton_1);
		
	}
}
