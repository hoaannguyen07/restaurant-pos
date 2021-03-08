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

public class Ingredients extends JFrame {
	
	private DataHelper api_connection;
	
	Vector<Vector<String>> ingredients_list; // keep all ingredients from table [0] = key || [1] = name || [2] = price
	
	Vector<String> orders; // [0] = entree || [1] = side || [2] = beverage || [3] = dessert
	private int order_type_id;
	
	private String menu_item;
	private String cur_order = "";
	
	private double order_price = 0.0;
	
	private JPanel contentPane;
	public static final Vector<String> MENU_HEADER = new Vector<String>();
	public static final Vector<Vector<String>> NULL_DATA = new Vector<Vector<String>>();
	public DefaultTableModel model;
	
	JTable table_menu;
	JScrollPane pane_menu;
	private final JLabel lblCustomizationOptions = new JLabel("CUSTOMIZATION OPTIONS");
	private final JLabel lblNewLabel = new JLabel("Click on the ingredient that you want to customize.");
	private final JLabel lblNewLabel_1 = new JLabel("* Note that any customization will be finalized and cannot be changed");
	private final JPanel panel = new JPanel();
	private final JLabel lblNewLabel_2 = new JLabel("SAVE CHANGES");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ingredients frame = new Ingredients();
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
	public Ingredients() {
		if (MENU_HEADER.size() == 0)
		{
			MENU_HEADER.addElement("Ingredient Name");
			MENU_HEADER.addElement("Price");
		}
		initGUI();
		show_data_in_table();
	}
	
	/* Constructor used when transferring data from customer menu to ingredients
	*/
	public Ingredients(DataHelper api, Vector<String> all_orders, int order_type_id, String item_id, double cur_order_price, Boolean add_menu_item) {
		System.out.println("GOT INTO INGREDIENTS");
		
		if (MENU_HEADER.size() == 0)
		{
			MENU_HEADER.addElement("Ingredient Name");
			MENU_HEADER.addElement("Price");
		}
		this.api_connection = api;
		this.orders = all_orders;
		this.order_type_id = order_type_id;
		this.menu_item = item_id;
//		this.cur_order += ":" + item_id; // ":" signify end of last cart menu and beginning of new set of items & its customization
		this.order_price = cur_order_price;
		
		System.out.println("BEFORE ADDING MENU ITEM TO ORDERS");
		System.out.println("Size of Orders Table: " + orders.size());
		// going from menu to here needs to add menu item, but going to customization screen over doesn't need it
		if (add_menu_item == true)
		{
			if (this.orders.elementAt(this.order_type_id).length() == 0) // if there are no orders for that particular category, this will be the first
			{
				String update_order = this.orders.elementAt(this.order_type_id);
				update_order += this.menu_item;
				System.out.println("Updated Menu Order for order type " + order_type_id + ": " + update_order);
				this.orders.set(this.order_type_id, update_order);
			}
			else
			{
				String update_order = this.orders.elementAt(this.order_type_id);
				update_order += ":" + this.menu_item;
				System.out.println("Updated Menu Order for order type " + order_type_id + ": " + update_order);
				this.orders.set(this.order_type_id, update_order);
			}
		}
		System.out.println("AFTER ADDING MENU ITEM TO ORDERS");
		initGUI();
		show_data_in_table();
	}
	
	private void initGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 690);
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
				String ingredient_price = table_model.getValueAt(index, 1).toString();
				
				String ingredient_id = ingredients_list.elementAt(index).elementAt(0);
				
				System.out.println(name + "\t" + ingredient_id + "\t" + ingredient_price);
				
				System.out.println("Current Order that is being customized");
				System.out.println("Order: " + cur_order);
				System.out.println("Price: " + order_price);
				
				Customize_Screen customize_frame = new Customize_Screen(api_connection, orders, order_type_id, menu_item, ingredient_id, order_price, Double.parseDouble(ingredient_price));
				customize_frame.setVisible(true);
				dispose();
			}
		});
		JScrollPane pane_menu = new JScrollPane(table_menu);
		model = (DefaultTableModel)table_menu.getModel();
		pane_menu.setBounds(10, 138, 568, 374);
//		table_menu.setPreferredSize(568,374)
		contentPane.add(pane_menu);
		pane_menu.setViewportView(table_menu);
		lblCustomizationOptions.setFont(new Font("Segoe UI Black", Font.PLAIN, 40));
		lblCustomizationOptions.setHorizontalAlignment(SwingConstants.CENTER);
		lblCustomizationOptions.setBounds(10, 11, 568, 73);
		
		contentPane.add(lblCustomizationOptions);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel.setForeground(new Color(128, 0, 0));
		lblNewLabel.setBounds(10, 105, 491, 32);
		
		contentPane.add(lblNewLabel);
		lblNewLabel_1.setForeground(new Color(165, 42, 42));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(10, 523, 466, 14);
		
		contentPane.add(lblNewLabel_1);
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// go back to Customer Menu
				System.out.println("Cart so far");
				for(int i = 0; i < orders.size(); i++)
				{
					System.out.println(i + ".\t" + orders.elementAt(i));
				}
				System.out.println("Total price: " + order_price);
				Customer_Menu customer_menu_frame = new Customer_Menu(api_connection, orders, order_price);
				customer_menu_frame.setVisible(true);
				dispose();
			}
		});
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(190, 568, 229, 76);
		
		contentPane.add(panel);
		panel.setLayout(null);
		lblNewLabel_2.setFont(new Font("Arial Black", Font.BOLD, 20));
		lblNewLabel_2.setBounds(10, 11, 209, 54);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		
		panel.add(lblNewLabel_2);
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
		
		ingredients_list = get_ingredients_data(); // [0] = key || [1] = name || [2] = price
//		DefaultTableModel model = (DefaultTableModel) table_menu.getModel();
		
		// only display item name and price
		for(int i = 0; i < ingredients_list.size(); i++)
		{
			Vector<String> displaying_list = new Vector<String>();
			displaying_list.addElement(ingredients_list.elementAt(i).elementAt(1));
			displaying_list.addElement(ingredients_list.elementAt(i).elementAt(2));
			model.addRow(displaying_list);
		}
		
	}
	
	Vector<Vector<String>> get_ingredients_data()
	{
		Vector<Vector<String>> ingredients = new Vector<Vector<String>>();
		api_connection = new DataHelper();

		try
		{
			
			Statement stmt = api_connection.conn.createStatement(); // statement object
			// create the actual statement to populate the statement object
			String sql_stmt = "SELECT * from ingredients";
			
			System.out.println("Executing Statement: " + sql_stmt);
			ResultSet result = stmt.executeQuery(sql_stmt);
			
			while(result.next())
			{
				Vector<String> cur_item = new Vector<String>(); // [0] = key || [1] = name || [2] = price
				// get name and price of food item
				String ingredient_key = result.getString("key");
				String ingredient_name = result.getString("name");
				String ingredient_price = result.getString("price");
				
				cur_item.addElement(ingredient_key);
				cur_item.addElement(ingredient_name);
				cur_item.addElement(ingredient_price);
				// put all info pertaining to item into the menu list
				ingredients.addElement(cur_item);
			
			}
			
		} catch (Exception e)
		{
			System.out.println("Error querying information from Ingredients Data Table.");
		}
		System.out.println(ingredients);
		return ingredients;
	}
}
