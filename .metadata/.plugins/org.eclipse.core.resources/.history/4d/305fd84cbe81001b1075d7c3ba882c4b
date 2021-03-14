package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Manager_Menu extends JFrame {
	
	DataHelper api_connection;
	
	private JPanel contentPane;
	public static final Vector<String> MENU_HEADER = new Vector<String>();
	public static final Vector<Vector<String>> NULL_DATA = new Vector<Vector<String>>();
	public DefaultTableModel model;
	
	Vector<Vector<String>> menu_list; // save all items on menu 
	
	JTable table_menu;
	JScrollPane pane_menu;
	private final JLabel lblManagerMenu = new JLabel("MANAGER MENU");

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
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Manager_Menu() {
		if (MENU_HEADER.size() == 0)
		{
			MENU_HEADER.addElement("ID");
			MENU_HEADER.addElement("Name");
			MENU_HEADER.addElement("Price");
			MENU_HEADER.addElement("Availability");
		}
		initGUI();
		show_data_in_table();
	}
	
	public Manager_Menu(DataHelper api) {
		if (MENU_HEADER.size() == 0)
		{
			MENU_HEADER.addElement("ID");
			MENU_HEADER.addElement("Name");
			MENU_HEADER.addElement("Price");
			MENU_HEADER.addElement("Availability");
		}
		this.api_connection = api;
		initGUI();
		show_data_in_table();
	}
	
	private void initGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 515);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 139, 139));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
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
				
				EditItem openItem = new EditItem(api_connection, name);
				openItem.setVisible(true);
				dispose();
			}
		});
		JScrollPane pane_menu = new JScrollPane(table_menu);
		model = (DefaultTableModel)table_menu.getModel();
		pane_menu.setBounds(10, 95, 568, 374);
//		table_menu.setPreferredSize(568,374)
		contentPane.add(pane_menu);
		pane_menu.setViewportView(table_menu);
		lblManagerMenu.setFont(new Font("Segoe UI Black", Font.PLAIN, 55));
		lblManagerMenu.setHorizontalAlignment(SwingConstants.CENTER);
		lblManagerMenu.setBounds(10, 11, 568, 73);
		
		contentPane.add(lblManagerMenu);
	}
	
	void delete_all_rows_in_table()
	{
		int row_count = model.getRowCount();
		// remove one row at a time
		for(int i = row_count - 1; i >= 0; i--)
		{
			model.removeRow(i);
		}
	}
	
	
	/**
	 * get menu data from api and add it to table to be showed on the frame
	 */
	void show_data_in_table()
	{
		// first make sure there is nothing in the table before adding stuff in
		this.delete_all_rows_in_table();
		
		menu_list = api_connection.get_menu_data(); // [0] = id || [1] = name || [2] = price || [3] = availability
		
		for(int i = 0; i < menu_list.size(); i++)
		{
			model.addRow(menu_list.elementAt(i));
		}
		
	}
}
