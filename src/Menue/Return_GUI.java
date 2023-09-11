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



public class Return_GUI extends JFrame {

	private JPanel contentPane;
	private JTable table;

	String[] columnNames = {"ID","Bookname", "Autor", "ISBN", "Genre"};
    String Titel;
    String Autor;
    String ISBN;
    String Genre;
	String id;
    
	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 * @param login 
	 */
    
	public Return_GUI(int login) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Return");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(342, 11, 64, 37);
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
			
		    ArrayList<String> allBook = MySQL.getLendBook(login);
		    int i = allBook.size();

		    for (int j = 0; j < i / 5; j++) {
		    	id = allBook.get(j * 5 );
		        Titel = allBook.get(j * 5 +1 );
		        Autor = allBook.get(j * 5 + 2);
		        ISBN = allBook.get(j * 5 + 3);
		        Genre = allBook.get(j * 5 + 4);
		        

		        
		       model.addRow(new Object[]{id,Titel, Autor, ISBN, Genre});
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
		              singleBook_GUI singleBook = new singleBook_GUI(ID,login,2);
		           singleBook.setVisible(true);
		           dispose();
		        }
		    }
		});
		
		JButton btnNewButton = new JButton("Menue");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				Menue_GUI menue = new Menue_GUI(login);
				menue.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton.setBounds(584, 68, 190, 37);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Confirm");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				int rowselected = table.getSelectedRow();
				String rowData = table.getValueAt(rowselected, 0).toString();
				int RowData = Integer.parseInt(rowData);
				
				if(MySQL.ReturnBook(RowData))
				{
					System.out.println("drin");
					MySQL.historyReturn(RowData, login);
					Return_GUI Return1 = new Return_GUI(login);
					dispose();
					Return1.setVisible(true);
					showMessageDialog(null, "You returned the book");
				}
				else
				{
					showMessageDialog(null, "Select a book");
				}
				
				
			}
		});
		
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton_1.setBounds(584, 395, 190, 37);
		contentPane.add(btnNewButton_1);
	}
	
}
