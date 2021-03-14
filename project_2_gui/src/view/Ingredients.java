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

import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Action;
import javax.swing.JButton;

public class Ingredients extends JFrame {

	private static final long serialVersionUID = 1L;

	// query variables
	private DataHelper api_connection;
	private int order_type_id;
	private String menu_item;
	private final int menu_id;

	Vector<Vector<String>> ingredients_list; // keep all ingredients from table [0] = key || [1] = name || [2] = price
	Vector<String> orders; // [0] = entree || [1] = side || [2] = beverage || [3] = dessert

	// model
	private JPanel contentPane;
	public static final Vector<String> MENU_HEADER = new Vector<String>();
	public static final Vector<Vector<String>> NULL_DATA = new Vector<Vector<String>>();
	public DefaultTableModel model;

	// tables
	JTable table_menu;
	JScrollPane pane_menu;
	private final JPanel panel = new JPanel();

	// JLabel
	private final JLabel lblCustomizationOptions = new JLabel("CUSTOMIZATION OPTIONS");
	private final JLabel save_changes_label = new JLabel("SAVE CHANGES");
	private final JLabel click_ingredient_label = new JLabel("Click on the ingredient you want to customize.");
	private final JLabel label_menu_item_chosen = new JLabel("New label");

	private final Action action = new SwingAction();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
				} catch (Exception e) {
					e.printStackTrace();
				}//end try-catch
			}//end run
		});//end invoke later
	}//end main

	/*
	 * Constructor used when transferring data from customer menu to ingredients
	 */
	public Ingredients(DataHelper api, Vector<String> all_orders, int order_type_id, String item_id, double cur_order_price, Boolean add_menu_item, int ingr_menu_id) {
		if (MENU_HEADER.size() == 0) {
			MENU_HEADER.addElement("Ingredient Name");
			MENU_HEADER.addElement("Price");
		}//end if
		this.api_connection = api;
		this.orders = all_orders;
		this.order_type_id = order_type_id;
		this.menu_item = item_id;
		this.menu_id = ingr_menu_id;

		System.out.println("BEFORE ADDING MENU ITEM TO ORDERS");
		System.out.println("Size of Orders Table: " + orders.size());
		// going from menu to here needs to add menu item, but going to customization screen over doesn't need it
		if (add_menu_item == true) {	
			if (this.orders.elementAt(this.order_type_id).length() == 0) {
				//if there are no order for that particular category, this will be the first
				String update_order = this.orders.elementAt(this.order_type_id);
				update_order += this.menu_item;
				System.out.println("Updated Menu Order for order type " + order_type_id + ": " + update_order);
				this.orders.set(this.order_type_id, update_order);
			} else {
				String update_order = this.orders.elementAt(this.order_type_id);
				update_order += ":" + this.menu_item;
				System.out.println("Updated Menu Order for order type " + order_type_id + ": " + update_order);
				this.orders.set(this.order_type_id, update_order);
			}//end if/else
		}//end id
		System.out.println("AFTER ADDING MENU ITEM TO ORDERS");
		initGUI();
		show_data_in_table();
	}//end constructor

	public Ingredients(DataHelper api, int ingr_menu_id) {
		if (MENU_HEADER.size() == 0) {
			MENU_HEADER.addElement("Ingredient Name");
			MENU_HEADER.addElement("Price");
		}//end if
		this.api_connection = api;
		this.menu_id = ingr_menu_id;
		initGUI();
		show_data_in_table();
	}//end constructor

	public class SwingAction extends AbstractAction {
		private static final long serialVersionUID = 1L;
		public SwingAction() {
			putValue(NAME, "Back");
			putValue(SHORT_DESCRIPTION, "Heading back to customer's menu.");
		}//end swing action
		public void actionPerformed(ActionEvent e) {
		}//end action performed
	}//end swing action extends

	private void initGUI() {
		//panel set up
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 715);
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

				TableModel table_model = table_menu.getModel();

				String name = table_model.getValueAt(index, 0).toString();
				String ingredient_price = table_model.getValueAt(index, 1).toString();

				String ingredient_id = ingredients_list.elementAt(index).elementAt(0);

				System.out.println(name + "\t" + ingredient_id + "\t" + ingredient_price);

				api_connection.choose_ingredient_item_to_customize(ingredient_id);
				Customize_Screen customize = new Customize_Screen(api_connection, menu_id);
				customize.setVisible(true);
				dispose();
			}//end mouse clicked
		});//end add mouse listener
		
		//pane menu
		JScrollPane pane_menu = new JScrollPane(table_menu);
		model = (DefaultTableModel) table_menu.getModel();
		pane_menu.setBounds(10, 192, 568, 374);
		contentPane.add(pane_menu);
		pane_menu.setViewportView(table_menu);
		
		//customization options label
		lblCustomizationOptions.setFont(new Font("Segoe UI Black", Font.PLAIN, 40));
		lblCustomizationOptions.setHorizontalAlignment(SwingConstants.CENTER);
		lblCustomizationOptions.setBounds(10, 86, 568, 73);
		contentPane.add(lblCustomizationOptions);
		
		//add mouse listener
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				api_connection.add_cur_customized_menu_item();

				switch (menu_id) {
					case 0 :
						Customer_Menu view_cust_option = new Customer_Menu(api_connection);
						view_cust_option.setVisible(true);
						break;
					case 1 :
						Entree_Menu view_cust_entree = new Entree_Menu(api_connection);
						view_cust_entree.setVisible(true);
						break;
					case 2 :
						Side_Menu view_cust_side = new Side_Menu(api_connection);
						view_cust_side.setVisible(true);
						break;
					case 3 :
						Beverage_Menu view_cust_bev = new Beverage_Menu(api_connection);
						view_cust_bev.setVisible(true);
						break;
					case 4 :
						Dessert_Menu view_cust_dessert = new Dessert_Menu(api_connection);
						view_cust_dessert.setVisible(true);
						break;
					case 5 :
						Recommendations rec = new Recommendations(api_connection);
						rec.setVisible(true);
						break;
					case 6 :
						Rewards view_rewards = new Rewards(api_connection);
						view_rewards.setVisible(true);
						break;
					default :
						Customize_Screen view_customize = new Customize_Screen(api_connection, menu_id);
						view_customize.setVisible(true);
						break;

				}//end switch case
				dispose();
			}//end mouse clicked
		});//add mouse listener
		
		//panel set-up finalization
		panel.setBackground(new Color(153, 0, 0));
		panel.setBounds(179, 577, 229, 76);
		contentPane.add(panel);
		panel.setLayout(null);

		//save changes label
		save_changes_label.setFont(new Font("Arial Black", Font.BOLD, 20));
		save_changes_label.setBounds(10, 11, 209, 54);
		save_changes_label.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(save_changes_label);

		//back button
		JButton button_back = new JButton("Back");
		button_back.setBackground(new Color(153, 0, 0));
		button_back.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}//end mouse clicked
		});//end add mouse listener
		button_back.setAction(action);
		button_back.setBounds(10, 11, 64, 26);
		contentPane.add(button_back);

		// click ingredient label
		click_ingredient_label.setHorizontalAlignment(SwingConstants.CENTER);
		click_ingredient_label.setForeground(Color.WHITE);
		click_ingredient_label.setFont(new Font("Arial", Font.BOLD, 20));
		click_ingredient_label.setBounds(48, 151, 491, 32);
		contentPane.add(click_ingredient_label);

		// label for the menu item chose
		label_menu_item_chosen.setForeground(new Color(153, 0, 0));
		label_menu_item_chosen.setFont(new Font("Arial", Font.BOLD, 20));
		label_menu_item_chosen.setHorizontalAlignment(SwingConstants.CENTER);
		label_menu_item_chosen.setBounds(15, 53, 558, 26);
		String menu_item_key = api_connection.cart_helper.get_cur_menu_item_key();
		label_menu_item_chosen.setText("Chosen Item: " + api_connection.getItemName(menu_item_key));
		contentPane.add(label_menu_item_chosen);

		// back button's action listener
		button_back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (arg0.getSource() == button_back) {
					api_connection.delete_cur_menu_item();
					Customer_Menu menu = new Customer_Menu(api_connection);
					menu.setVisible(true);
					dispose();
				} // end if
			}// end action performed
		});// end add action listener
	}// end init gui

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

		ingredients_list = api_connection.get_ingredients_data(); // [0] = key || [1] = name || [2] = price

		// only display item name and price
		for (int i = 0; i < ingredients_list.size(); i++) {
			Vector<String> displaying_list = new Vector<String>();
			displaying_list.addElement(ingredients_list.elementAt(i).elementAt(1));
			displaying_list.addElement(ingredients_list.elementAt(i).elementAt(2));
			model.addRow(displaying_list);
		}//end for
	}//end show data in table
}//end class