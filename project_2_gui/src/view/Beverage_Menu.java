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

public class Beverage_Menu extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	//variables retrieved from other functions
	DataHelper api_connection;
	public static String first;
	public static String last;
	public static String user;
	public static String pass;
	private double total_price;
	
	private Vector<String> orders = new Vector<String>(); // [0] = entree || [1] = side || [2] = beverage || [3] = dessert
	Vector<Vector<String>> menu_list; // save all items on menu 
	
	//JFrame content
	private JPanel contentPane;
	public static final Vector<String> MENU_HEADER = new Vector<String>();
	public static final Vector<Vector<String>> NULL_DATA = new Vector<Vector<String>>();
	public DefaultTableModel model;
	
	private JButton btnBack;
	private JButton btnCheckout;
	
	JTable table_menu;
	JScrollPane pane_menu;
	
	private final JLabel lbl_beverage_menu = new JLabel("BEVERAGE MENU");
	private JLabel lbl_click_beverage;
	
	private static final int BEVERAGE_MENU_ID = 3; //making sure the user returns to the right menu once done with beverages

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Beverage_Menu frame = new Beverage_Menu(new DataHelper());
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}//end try-catch
			}//end run
		});//end invokeLater
	}//main

	/**
	 * @wbp.parser.constructor
	 */
	public Beverage_Menu(DataHelper api) {
		if (MENU_HEADER.size() == 0) {
			MENU_HEADER.addElement("Name");
			MENU_HEADER.addElement("Price");
		}//end if
		for(int i = 0; i < 4; i++) {
			orders.addElement("");
		}//end for
		System.out.println("Size of Orders Table: " + orders.size());
		this.api_connection = api;
		first = this.api_connection.first_name;
		last = this.api_connection.last_name;
		user = this.api_connection.id;
		pass = this.api_connection.password;
		initGUI();
		show_data_in_table();
	}//end constructor with api only
	
	public Beverage_Menu(DataHelper api, double price) {
		if (MENU_HEADER.size() == 0) {
			MENU_HEADER.addElement("Name");
			MENU_HEADER.addElement("Price");
		}//end if
		for(int i = 0; i < 4; i++) {
			orders.addElement("");
		}//end for
		System.out.println("Size of Orders Table: " + orders.size());
		this.api_connection = api;
		first = this.api_connection.first_name;
		last = this.api_connection.last_name;
		user = this.api_connection.id;
		pass = this.api_connection.password;
		total_price = price;
		initGUI();
		show_data_in_table();
	}//end Beverage_Menu constructor
	
	public Beverage_Menu(DataHelper api, Vector<String> all_orders, double price) {
		if (MENU_HEADER.size() == 0) {
			MENU_HEADER.addElement("Name");
			MENU_HEADER.addElement("Price");
		}//end if
		this.api_connection = api;	
		this.orders = all_orders;
		this.total_price = price;
		
		initGUI();
		show_data_in_table();
	}//end full constructor
	
	private void initGUI() {
		//setting up the frame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 324);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 153, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//table_menu declaration and creation
		table_menu = new JTable(NULL_DATA, MENU_HEADER);
		table_menu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = table_menu.getSelectedRow();
				
				TableModel table_model = table_menu.getModel();
				
				String name = table_model.getValueAt(index, 0).toString();
				String price = table_model.getValueAt(index, 1).toString();
				
				price = price.substring(2); // removing "$ "
				
				String item_id = api_connection.getItemID(name);
				
				System.out.println(name + "\t" + item_id + "\t" + price);
				
				api_connection.choose_menu_item_to_customize(item_id);
				Ingredients ingr_frame = new Ingredients(api_connection, BEVERAGE_MENU_ID);
				ingr_frame.setVisible(true);
				dispose();
			}//end mouseClicked
		});//end addMouseListener
		
		//pane menu declaration and set-up
		JScrollPane pane_menu = new JScrollPane(table_menu);
		model = (DefaultTableModel)table_menu.getModel();
		pane_menu.setBounds(10, 153, 568, 119);
		contentPane.add(pane_menu);
		pane_menu.setViewportView(table_menu);
		
		//entree menu label
		lbl_beverage_menu.setFont(new Font("Segoe UI Black", Font.PLAIN, 52));
		lbl_beverage_menu.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_beverage_menu.setBounds(10, 42, 568, 73);
		contentPane.add(lbl_beverage_menu);
		
		//back button
		btnBack = new JButton("Back");
		btnBack.setFont(new Font("Arial", Font.BOLD, 15));
		btnBack.setForeground(new Color(255, 255, 255));
		btnBack.setBackground(new Color(153, 0, 0));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(arg0.getSource() == btnBack) { 
					Menu_Select menu_select = new Menu_Select(api_connection, orders, total_price);
					menu_select.setVisible(true);
					dispose();
				}//end if
			}//end actionPerformed
		});//end addActionListener
		btnBack.setBounds(10, 11, 75, 25);
		contentPane.add(btnBack);
		
		//click on the beverage label
		lbl_click_beverage = new JLabel("Click on the beverage you want to customize.");
		lbl_click_beverage.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_click_beverage.setForeground(Color.WHITE);
		lbl_click_beverage.setFont(new Font("Arial", Font.BOLD, 20));
		lbl_click_beverage.setBounds(48, 110, 491, 32);
		contentPane.add(lbl_click_beverage);
		
		//checkout button
		btnCheckout = new JButton("CHECKOUT");
		btnCheckout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Cart c = new Cart(api_connection);
				c.setVisible(true);
				dispose();
			}//end mouseClicked
		});//end addMouseListener
		btnCheckout.setForeground(Color.WHITE);
		btnCheckout.setFont(new Font("Arial", Font.BOLD, 18));
		btnCheckout.setBackground(new Color(153, 0, 0));
		btnCheckout.setBounds(443, 13, 135, 25);
		contentPane.add(btnCheckout);
	}//end initGUI
	
	//removing all rows from the model
	void delete_all_rows_in_table() {
		int row_count = model.getRowCount();
		// remove one row at a time
		for(int i = row_count - 1; i >= 0; i--) {
			model.removeRow(i);
		}//end for
	}//end delete all rows in table
	
	//displaying the data in the table
	void show_data_in_table() {
		this.delete_all_rows_in_table(); // make sure there is nothing in the table before adding stuff in
		
		menu_list = api_connection.get_menu_data(); // [0] = id || [1] = name || [2] = price
		
		// only display item name and price of entrees
		for(int i = 0; i < menu_list.size(); i++) {
			if (menu_list.elementAt(i).elementAt(0).contains("B") && menu_list.elementAt(i).elementAt(3).equals("t")) {
				Vector<String> displaying_list = new Vector<String>();
				displaying_list.addElement(menu_list.elementAt(i).elementAt(1));
				displaying_list.addElement("$ " + menu_list.elementAt(i).elementAt(2));
				model.addRow(displaying_list);
			}//end if
		}//end for
	}//end show data in table
}//end class