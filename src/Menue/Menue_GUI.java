package Menue;

import static javax.swing.JOptionPane.showMessageDialog;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import main.MySQL;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.Color;



public class Menue_GUI extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTable table_1;

	String[] columnNames = {"ID","Bookname", "Autor", "ISBN", "Genre","Available"};
    String Titel;
    String Autor;
    String ISBN;
    String Genre;
    String customer_ID;
	String id;
    
	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 * @param login 
	 * @param position 
	 */
    
	public Menue_GUI(int login, int position) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Menue");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(342, 11, 64, 37);
		contentPane.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(53, 66, 482, 366);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setEnabled(false);
		scrollPane.setViewportView(table);
		DefaultTableModel model = new DefaultTableModel();
		
		model.setColumnIdentifiers(columnNames);
		table.setModel(model);
		
		try {
			
		    ArrayList<String> allBook = MySQL.ListOfBooks();
		    int i = allBook.size();

		    for (int j = 0; j < i / 6; j++) {
		    	id = allBook.get(j * 6 );
		        Titel = allBook.get(j * 6 +1 );
		        Autor = allBook.get(j * 6 + 2);
		        ISBN = allBook.get(j * 6 + 3);
		        Genre = allBook.get(j * 6 + 4);
		        customer_ID = allBook.get(j * 6 + 5);

		        if(customer_ID == null)
		        {
		        	customer_ID = "Available";
		        }
		        else {
		        	customer_ID ="Not Available";
		        }
		       model.addRow(new Object[]{id,Titel, Autor, ISBN, Genre,customer_ID});
		    }
		    

		} catch (Exception e) {
		    e.printStackTrace();
		}
		
		table.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent evt) { 
		        if (evt.getClickCount() == 2) {
		            Point pnt = evt.getPoint();
		            int row = table.rowAtPoint(pnt);
		            String id = table.getValueAt(row, 0).toString();
		            int ID = Integer.parseInt(id);
		         
		           singleBook_GUI singleBook = new singleBook_GUI(ID,login,position);
		           singleBook.setVisible(true);
		           dispose();
		        }
		    }
		});
		
		JButton btnNewButton = new JButton("Return book");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				Return_GUI Return = new Return_GUI(login,position);
				Return.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton.setBounds(584, 68, 190, 37);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Edit Profil");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				Update_GUI update = new Update_GUI(login, position);
				update.setVisible(true);
				dispose();
				
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton_1.setBounds(584, 133, 190, 37);
		contentPane.add(btnNewButton_1);
	}
	
}
