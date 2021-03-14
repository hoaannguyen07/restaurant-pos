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

public class Entree_Menu extends JFrame {

	private static final long serialVersionUID = 1L;
	private Vector<String> orders = new Vector<String>(); // [0] = entree || [1]  = side || [2] = beverage || [3] = dessert
	Vector<Vector<String>> menu_list; // save all items on menu

	// query variables
	public static String first;
	public static String last;
	public static String user;
	public static String pass;
	private double total_price;
	DataHelper api_connection;

	// model
	private JPanel contentPane;
	public static final Vector<String> MENU_HEADER = new Vector<String>();
	public static final Vector<Vector<String>> NULL_DATA = new Vector<Vector<String>>();
	public DefaultTableModel model;
	JTable table_menu;
	JScrollPane pane_menu;

	// JLabels
	private final JLabel lblEntreeMenu = new JLabel("ENTREE MENU");
	private JLabel click_entree_label;

	// JButtons
	private JButton btnBack;
	private JButton btnCheckout;

	private static final int ENTREE_MENU_ID = 1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Entree_Menu frame = new Entree_Menu(new DataHelper());
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				} // end try catch
			}// end run
		});// end invoke later
	}// main

	/**
	 * @wbp.parser.constructor
	 */
	public Entree_Menu(DataHelper api) {
		if (MENU_HEADER.size() == 0) {
			MENU_HEADER.addElement("Name");
			MENU_HEADER.addElement("Price");
		}//end if
		for (int i = 0; i < 4; i++) {
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
	}//end entree menu minimal constructor

	public Entree_Menu(DataHelper api, double price) {
		if (MENU_HEADER.size() == 0) {
			MENU_HEADER.addElement("Name");
			MENU_HEADER.addElement("Price");
		}// end if
		for (int i = 0; i < 4; i++) {
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
	}//end constructor

	public Entree_Menu(DataHelper api, Vector<String> all_orders, double price) {
		if (MENU_HEADER.size() == 0) {
			MENU_HEADER.addElement("Name");
			MENU_HEADER.addElement("Price");
		}//end if
		this.api_connection = api;
		this.orders = all_orders;
		this.total_price = price;

		initGUI();
		show_data_in_table();
	}//end constructor

	private void initGUI() {
		//set up panel
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 345);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 153, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		//tabel menu
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
				Ingredients ingr_frame = new Ingredients(api_connection,
						ENTREE_MENU_ID);
				ingr_frame.setVisible(true);
				dispose();
			}//end mouse clicked
		});//end add mouse listener
		
		//pane menu
		JScrollPane pane_menu = new JScrollPane(table_menu);
		model = (DefaultTableModel) table_menu.getModel();
		pane_menu.setBounds(10, 144, 568, 147);
		contentPane.add(pane_menu);
		pane_menu.setViewportView(table_menu);
		
		//entree menu label
		lblEntreeMenu.setFont(new Font("Segoe UI Black", Font.PLAIN, 52));
		lblEntreeMenu.setHorizontalAlignment(SwingConstants.CENTER);
		lblEntreeMenu.setBounds(10, 37, 568, 73);
		contentPane.add(lblEntreeMenu);

		//back button
		btnBack = new JButton("Back");
		btnBack.setFont(new Font("Arial", Font.BOLD, 15));
		btnBack.setBackground(new Color(153, 0, 0));
		btnBack.setForeground(new Color(255, 255, 255));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (arg0.getSource() == btnBack) {
					Menu_Select menu_select = new Menu_Select(api_connection,
							orders, total_price);
					menu_select.setVisible(true);
					dispose();
				}//end if
			}//end action performed
		});//end back button
		btnBack.setBounds(10, 11, 78, 25);
		contentPane.add(btnBack);

		//click entree label
		click_entree_label = new JLabel("Click on the entree you want to customize.");
		click_entree_label.setHorizontalAlignment(SwingConstants.CENTER);
		click_entree_label.setForeground(Color.WHITE);
		click_entree_label.setFont(new Font("Arial", Font.BOLD, 20));
		click_entree_label.setBounds(46, 107, 491, 32);
		contentPane.add(click_entree_label);

		btnCheckout = new JButton("CHECKOUT");
		btnCheckout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}//end action performed
		});//end action listener
		btnCheckout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Cart c = new Cart(api_connection);
				c.setVisible(true);
				dispose();
			}//end mouse clicked
		});//end add mouse listener
		btnCheckout.setForeground(Color.WHITE);
		btnCheckout.setFont(new Font("Arial", Font.BOLD, 18));
		btnCheckout.setBackground(new Color(153, 0, 0));
		btnCheckout.setBounds(443, 11, 135, 25);
		contentPane.add(btnCheckout);
	}//end init gui

	void delete_all_rows_in_table() {
		int row_count = model.getRowCount();
		// remove one row at a time
		for (int i = row_count - 1; i >= 0; i--) {
			model.removeRow(i);
		}//end for
	}//end delete all rows in table

	void show_data_in_table() {
		// first make sure there is nothing in the table before adding stuff in
		this.delete_all_rows_in_table();

		menu_list = api_connection.get_menu_data(); // [0] = id || [1] = name || [2] = price

		// only display item name and price of entrees
		for (int i = 0; i < menu_list.size(); i++) {
			if (menu_list.elementAt(i).elementAt(0).contains("E")
					&& menu_list.elementAt(i).elementAt(3).equals("t")) {
				Vector<String> displaying_list = new Vector<String>();
				displaying_list.addElement(menu_list.elementAt(i).elementAt(1));
				displaying_list
						.addElement("$ " + menu_list.elementAt(i).elementAt(2));
				model.addRow(displaying_list);
			}//end if
		}//end for
	}//end show data in table
}//end class