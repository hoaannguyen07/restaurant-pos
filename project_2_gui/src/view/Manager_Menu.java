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
					Manager_Menu frame = new Manager_Menu();
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
		MENU_HEADER.addElement("Name");
		MENU_HEADER.addElement("Price");
		initGUI();
		show_data_in_table();
	}
	
	public Manager_Menu(DataHelper api) {
		MENU_HEADER.addElement("Name");
		MENU_HEADER.addElement("Price");
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
				
				TableModel table_model = table_menu.getModel();
				
				String name = table_model.getValueAt(index, 0).toString();
				String price = table_model.getValueAt(index, 1).toString();
				
				System.out.println(name + "\t" + price);
				
				EditItem openItem = new EditItem("1234", name);
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
	
	void show_data_in_table()
	{
		Vector<Vector<String>> displaying_list = get_menu_data();
//		DefaultTableModel model = (DefaultTableModel) table_menu.getModel();

		for(int i = 0; i < displaying_list.size(); i++)
		{
			model.addRow(displaying_list.elementAt(i));
		}
		
	}
	
	Vector<Vector<String>> get_menu_data()
	{
		Vector<Vector<String>> menu_list = new Vector<Vector<String>>();
		api_connection = new DataHelper();

		try
		{
			
			Statement stmt = api_connection.conn.createStatement(); // statement object
			// create the actual statement to populate the statement object
			String sql_stmt = "SELECT * from menu";
			
			System.out.println("Executing Statement: " + sql_stmt);
			ResultSet result = stmt.executeQuery(sql_stmt);
			
			while(result.next())
			{
				Vector<String> cur_item = new Vector<String>();
				// get name and price of food item
				String food_name = result.getString("name");
				String food_price = result.getString("price");
				cur_item.addElement(food_name);
				cur_item.addElement(food_price);
				// put all info pertaining to item into the menu list
				menu_list.addElement(cur_item);
			
			}
			
		} catch (Exception e)
		{
			System.out.println("Error adding to manager Datatable.");
		}
		return menu_list;
	}
}
