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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class entreeMenu extends JFrame {
	
	private static final long serialVersionUID = 1L;
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
	private final JLabel lblEntreeMenu = new JLabel("ENTREE MENU");
	private JButton btnBack;
	
	public static String first;
	public static String last;
	public static String user;
	public static String pass;
	private JPanel panel;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	
	private static final int ENTREE_MENU_ID = 1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					entreeMenu frame = new entreeMenu(new DataHelper());
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
	public entreeMenu(String first_name, String last_name, String username, String password) {
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
	public entreeMenu(DataHelper api) {
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
	
	public entreeMenu(DataHelper api, double price) {
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
		total_price = price;
		initGUI();
		show_data_in_table();
	}
	
	public entreeMenu(DataHelper api, Vector<String> all_orders, double price) {
		if (MENU_HEADER.size() == 0)
		{
			MENU_HEADER.addElement("Name");
			MENU_HEADER.addElement("Price");
		}
		this.api_connection = api;
		
//		first = this.api_connection.first_name;
//		last = this.api_connection.last_name;
//		user = this.api_connection.id;
//		pass = this.api_connection.password;
//		
		this.orders = all_orders;
		
		this.total_price = price;
		
		initGUI();
		show_data_in_table();
	}
	
	private void initGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 676);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 153, 204));
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
				
				// removing "$ "
				price = price.substring(2);
				
				String item_id = api_connection.getItemID(name);
				
				System.out.println(name + "\t" + item_id + "\t" + price);
				
				api_connection.choose_menu_item_to_customize(item_id);
				Ingredients ingr_frame = new Ingredients(api_connection, ENTREE_MENU_ID);
				ingr_frame.setVisible(true);
				dispose();
			}
		});
		JScrollPane pane_menu = new JScrollPane(table_menu);
		model = (DefaultTableModel)table_menu.getModel();
		pane_menu.setBounds(10, 144, 568, 374);
//		table_menu.setPreferredSize(568,374)
		contentPane.add(pane_menu);
		pane_menu.setViewportView(table_menu);
		lblEntreeMenu.setFont(new Font("Segoe UI Black", Font.PLAIN, 52));
		lblEntreeMenu.setHorizontalAlignment(SwingConstants.CENTER);
		lblEntreeMenu.setBounds(10, 23, 568, 73);
		
		contentPane.add(lblEntreeMenu);
		
		btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(arg0.getSource() == btnBack) { 
					menuSelect menu_select = new menuSelect(api_connection, orders, total_price);
					menu_select.setVisible(true);
					dispose();
				}
			}
		});
		btnBack.setBounds(10, 11, 67, 25);
		contentPane.add(btnBack);
		
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
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_1.setBounds(46, 107, 491, 32);
		contentPane.add(lblNewLabel_1);
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
		
		menu_list = api_connection.get_menu_data(); // [0] = id || [1] = name || [2] = price
		
		// only display item name and price of entrees
		for(int i = 0; i < menu_list.size(); i++)
		{
			if (menu_list.elementAt(i).elementAt(0).contains("E") && menu_list.elementAt(i).elementAt(3).equals("t")) {
				Vector<String> displaying_list = new Vector<String>();
				displaying_list.addElement(menu_list.elementAt(i).elementAt(1));
				displaying_list.addElement("$ " + menu_list.elementAt(i).elementAt(2));
				model.addRow(displaying_list);
			}
		}
		
	}
}