package src.view;

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
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Customer_Menu extends JFrame {
	
	DataHelper api_connection;
	private Vector<String> orders = new Vector<String>(); // [0] = entree || [1] = side || [2] = beverage || [3] = dessert
	
	private double total_price = 0.0;
	
	Vector<Vector<String>> menu_list; // save all items on menu 
	
	private JPanel contentPane;
	public static final Vector<String> MENU_HEADER = new Vector<String>();
	public static final Vector<Vector<String>> NULL_DATA = new Vector<Vector<String>>();
	public DefaultTableModel model;
	
	JTable table_menu;
	JScrollPane pane_menu;
	private final JLabel label_menu_title = new JLabel("A.N.G.S.T MENU");
	private JButton btnNewButton;
	
	public static String first;
	public static String last;
	public static String user;
	public static String pass;
	private JPanel panel;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Customer_Menu frame = new Customer_Menu(new DataHelper());
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
	public Customer_Menu(String first_name, String last_name, String username, String password) {
		if (MENU_HEADER.size() == 0)
		{
			MENU_HEADER.addElement("Name");
			MENU_HEADER.addElement("Price");
		}
		for(int i = 0; i < 4; i++)
		{
			orders.addElement("");
		}
		first = first_name;
		last = last_name;
		user = username;
		pass = password;
		initGUI();
		show_data_in_table();
	}
	

	/**
	 * @wbp.parser.constructor
	 */
	public Customer_Menu(DataHelper api) {
		if (MENU_HEADER.size() == 0)
		{
			MENU_HEADER.addElement("Name");
			MENU_HEADER.addElement("Price");
		}
		for(int i = 0; i < 4; i++)
		{
			orders.addElement("");
		}
		System.out.println("Size of Orders Table: " + orders.size());
		this.api_connection = api;
		first = this.api_connection.first_name;
		last = this.api_connection.last_name;
		user = this.api_connection.id;
		pass = this.api_connection.password;
		initGUI();
		show_data_in_table();
	}
	
	public Customer_Menu(DataHelper api, Vector<String> all_orders, double price) {
		if (MENU_HEADER.size() == 0)
		{
			MENU_HEADER.addElement("Name");
			MENU_HEADER.addElement("Price");
		}
		this.api_connection = api;
		
		first = this.api_connection.first_name;
		last = this.api_connection.last_name;
		user = this.api_connection.id;
		pass = this.api_connection.password;
		
		this.orders = all_orders;
		
		this.total_price = price;
		
		initGUI();
		show_data_in_table();
	}
	
	private void initGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 676);
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
				
				String item_id = menu_list.elementAt(index).elementAt(0);
				
				System.out.println(name + "\t" + item_id + "\t" + price);
				
				int selected_item_orders_id = -1;
				
				Character first_char = item_id.charAt(0);
				// find type of food item it is and code it using the orders code (to later pass onto the ingredients frame)
				if (first_char.equals('E')) {
					selected_item_orders_id = 0;
				}
				else if (first_char.equals('S')) {
					selected_item_orders_id = 1;
				}
				else if (first_char.equals('B')) {
					selected_item_orders_id = 2;
				}
				else if (first_char.equals('D')) {
					selected_item_orders_id = 3;
				}
				
				System.out.println("Selected Item Orders ID: " + selected_item_orders_id);
				
				System.out.println("Cart so far");
				for(int i = 0; i < orders.size(); i++)
				{
					System.out.println(i + ".\t" + orders.elementAt(i));
				}
				System.out.println("Total price: " + total_price);
				// add menu item when going to Ingredients frame
				Ingredients ingredients_frame = new Ingredients(api_connection, orders, selected_item_orders_id, item_id, total_price + Double.parseDouble(price), true); 
				ingredients_frame.setVisible(true);
				dispose();
			}
		});
		JScrollPane pane_menu = new JScrollPane(table_menu);
		model = (DefaultTableModel)table_menu.getModel();
		pane_menu.setBounds(10, 144, 568, 374);
//		table_menu.setPreferredSize(568,374)
		contentPane.add(pane_menu);
		pane_menu.setViewportView(table_menu);
		label_menu_title.setFont(new Font("Segoe UI Black", Font.PLAIN, 55));
		label_menu_title.setHorizontalAlignment(SwingConstants.CENTER);
		label_menu_title.setBounds(10, 11, 568, 73);
		
		contentPane.add(label_menu_title);
		
		btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(arg0.getSource() == btnNewButton) { 
					customerOptionMenu view_cust = new customerOptionMenu(first, last, user, pass);
					view_cust.setVisible(true);
					dispose();
				}
			}
		});
		btnNewButton.setBounds(22, 11, 44, 25);
		contentPane.add(btnNewButton);
		
		panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(196, 554, 200, 75);
		contentPane.add(panel);
		panel.setLayout(null);
		
		lblNewLabel = new JLabel("ADD TO CART");
		lblNewLabel.setFont(new Font("Arial Black", Font.BOLD, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 11, 180, 53);
		panel.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("Click on the ingredient that you want to customize.");
		lblNewLabel_1.setForeground(new Color(128, 0, 0));
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_1.setBounds(10, 107, 491, 32);
		contentPane.add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("* Note that any customization will be finalized and cannot be changed");
		lblNewLabel_2.setForeground(new Color(165, 42, 42));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(10, 529, 466, 14);
		contentPane.add(lblNewLabel_2);
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
	
	void show_data_in_table()
	{
		// first make sure there is nothing in the table before adding stuff in
		this.delete_all_rows_in_table();
		
		menu_list = get_menu_data(); // [0] = id || [1] = name || [2] = price
//		DefaultTableModel model = (DefaultTableModel) table_menu.getModel();
		
		// only display item name and price
		for(int i = 0; i < menu_list.size(); i++)
		{
			Vector<String> displaying_list = new Vector<String>();
			displaying_list.addElement(menu_list.elementAt(i).elementAt(1));
			displaying_list.addElement(menu_list.elementAt(i).elementAt(2));
			model.addRow(displaying_list);
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
				Vector<String> cur_item = new Vector<String>(); // [0] = id || [1] = name || [2] = price
				// get name and price of food item
				String food_id = result.getString("id");
				String food_name = result.getString("name");
				String food_price = result.getString("price");
				cur_item.addElement(food_id);
				cur_item.addElement(food_name);
				cur_item.addElement(food_price);
				// put all info pertaining to item into the menu list
				menu_list.addElement(cur_item);
			
			}
			
		} catch (Exception e)
		{
			System.out.println("Error adding to manager Datatable.");
		}
		System.out.println(menu_list);
		return menu_list;
	}
}
