package view;

import java.awt.EventQueue;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;

public class Manager_Menu extends JFrame {
	
	private static final long serialVersionUID = 1L;

	DataHelper api_connection;
	
	Vector<Vector<String>> menu_list; // save all items on menu 
	
	//model
	private JPanel contentPane;
	public static final Vector<String> MENU_HEADER = new Vector<String>();
	public static final Vector<Vector<String>> NULL_DATA = new Vector<Vector<String>>();
	public DefaultTableModel model;
	
	JTable table_menu;
	JScrollPane pane_menu;
	
	//misc Swing functionality
	private final JLabel lblManagerMenu = new JLabel("MANAGER MENU");
	private JButton btnBack;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Manager_Menu frame = new Manager_Menu(new DataHelper());
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}//end try catch
			}//end run
		});//end invoke later
	}//end main
	
	public Manager_Menu(DataHelper api) {
		if (MENU_HEADER.size() == 0) {
			MENU_HEADER.addElement("ID");
			MENU_HEADER.addElement("Name");
			MENU_HEADER.addElement("Price");
			MENU_HEADER.addElement("Availability");
		}
		this.api_connection = api;
		initGUI();
		show_data_in_table();
	}//end constructor
	
	private void initGUI() {
		//set up panel
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 515);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 153, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//table menu
		table_menu = new JTable(NULL_DATA, MENU_HEADER);
		table_menu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = table_menu.getSelectedRow();
				
				// [0] = id || [1] = name || [2] = price || [3] = availability --> how table is set up
				TableModel table_model = table_menu.getModel();
				
				String name = table_model.getValueAt(index, 1).toString();
				String price = table_model.getValueAt(index, 2).toString();
				
				System.out.println(name + "\t" + price);
				
				Edit_Item openItem = new Edit_Item(api_connection, name);
				openItem.setVisible(true);
				dispose();
			}//end mouse clicked
		});//end add mouse listener
		
		//pane menu
		JScrollPane pane_menu = new JScrollPane(table_menu);
		model = (DefaultTableModel)table_menu.getModel();
		pane_menu.setBounds(10, 95, 568, 338);
		contentPane.add(pane_menu);
		pane_menu.setViewportView(table_menu);
		
		//manager menu label
		lblManagerMenu.setFont(new Font("Segoe UI Black", Font.PLAIN, 55));
		lblManagerMenu.setHorizontalAlignment(SwingConstants.CENTER);
		lblManagerMenu.setBounds(43, 11, 501, 73);
		contentPane.add(lblManagerMenu);
		
		//back button
		btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(arg0.getSource() == btnBack) {
					Manager_Option_Menu manager_menu = new Manager_Option_Menu(api_connection);
					manager_menu.setVisible(true);
					dispose();
				}//end if
			}//end action performed
		});//end add action listener
		btnBack.setBounds(10, 442, 89, 23);
		contentPane.add(btnBack);
	}//end init gui
	
	void delete_all_rows_in_table() {
		int row_count = model.getRowCount();
		// remove one row at a time
		for(int i = row_count - 1; i >= 0; i--) {
			model.removeRow(i);
		}//end for
	}//end delete all rows in table
	
	/**
	 * get menu data from api and add it to table to be showed on the frame
	 */
	void show_data_in_table() {
		// first make sure there is nothing in the table before adding stuff in
		this.delete_all_rows_in_table();
		
		menu_list = api_connection.get_menu_data(); // [0] = id || [1] = name || [2] = price || [3] = availability
		
		String id = "";
		String name = "";
		String price = "";
		String available = "";
		
		for(int i = 0; i < menu_list.size(); i++) {
			Vector<String> displaying_list = new Vector<String>();
			
			id = menu_list.elementAt(i).elementAt(0);
			name = menu_list.elementAt(i).elementAt(1);
			price = "$ " + menu_list.elementAt(i).elementAt(2);
			
			if (menu_list.elementAt(i).elementAt(3).equals("t")) {
				available = "Available";
			} else {
				available = "Not Available";
			}//end if/else
			
			displaying_list.addElement(id);
			displaying_list.addElement(name);
			displaying_list.addElement(price);
			displaying_list.addElement(available);
			model.addRow(displaying_list);
			//model.addRow(menu_list.elementAt(i));
		}//end for	
	}//end show data in table
}//end class