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



public class EditProfiles_GUI extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTable table_1;

	String[] columnNames = {"ID","Firstname", "Lastname", "Username"};
	String firstname;
	String lastname;
	String username;
	String id;
    
	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 * @param login 
	 * @param position 
	 */
    
	public EditProfiles_GUI(int login, int position) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Profile Menue");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(315, 11, 195, 37);
		contentPane.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(53, 66, 578, 366);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setEnabled(false);
		scrollPane.setViewportView(table);
		DefaultTableModel model = new DefaultTableModel();
		
		model.setColumnIdentifiers(columnNames);
		table.setModel(model);
		
		try {
			
		    ArrayList<String> allUser = MySQL.listOfUsers();
		    int i = allUser.size();

		    for (int j = 0; j < i / 4; j++) {
		    	id = allUser.get(j * 4 );
		        firstname = allUser.get(j * 4 +1 );
		        lastname = allUser.get(j * 4 + 2);
		        username = allUser.get(j * 4 + 3);

		       model.addRow(new Object[]{id,firstname,lastname,username});
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
		         if(position == 1)
		         {
		        	 SingleUser_GUI SingleUser = new SingleUser_GUI(ID, login, position);
		        	 SingleUser.setVisible(true);
		        	 dispose();
		         }
		         else if(position ==2)
		         {
		        	 SingleUserADMIN_GUI SingleUserADMIN = new SingleUserADMIN_GUI(ID, login, position);
		        	 SingleUserADMIN.setVisible(true);
		        	 dispose();
		         }
		         
		        }
		    }
		});
		
		JButton btnNewButton_1 = new JButton("Back");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
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
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton_1.setBounds(655, 395, 119, 37);
		contentPane.add(btnNewButton_1);
	}
	
}
