package Menue;

import static javax.swing.JOptionPane.showMessageDialog;


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



public class DeleteBook_GUI extends JFrame {

	private JPanel contentPane;
	private JTable table;

	String[] columnNames = {"ID","Bookname", "Autor", "ISBN", "Genre"};
    String Titel;
    String Autor;
    String ISBN;
    String Genre;
	String id;
    String customer_ID;
	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 * @param login 
	 */
    
	public DeleteBook_GUI(int login, int position) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Delete Book");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(310, 11, 168, 37);
		contentPane.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(53, 66, 482, 366);
		contentPane.add(scrollPane);
		
		table = new JTable();
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

		JButton btnNewButton = new JButton("Back");
		btnNewButton.addMouseListener(new MouseAdapter() {
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
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton.setBounds(584, 395, 190, 37);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Confirm");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				int rowselected = table.getSelectedRow();
				String rowData = table.getValueAt(rowselected, 0).toString();
				int RowData = Integer.parseInt(rowData);
				
				if(MySQL.DeleteBook(RowData))
				{
					System.out.println("drin");
					DeleteBook_GUI Return1 = new DeleteBook_GUI(login, position);
					dispose();
					Return1.setVisible(true);
					showMessageDialog(null, "You deleted the book");
				}
				else
				{
					showMessageDialog(null, "Select a book");
				}
				
				
			}
		});
		
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton_1.setBounds(584, 66, 190, 37);
		contentPane.add(btnNewButton_1);
	}
	
}
