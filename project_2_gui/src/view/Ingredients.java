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

	private DataHelper api_connection;
	
	Vector<Vector<String>> ingredients_list; // keep all ingredients from table [0] = key || [1] = name || [2] = price
	
	Vector<String> orders; // [0] = entree || [1] = side || [2] = beverage || [3] = dessert
	private int order_type_id;
	
	private String menu_item;
	private final int menu_id;
//	private String cur_order = "";
	
//	private double order_price = 0.0;
	
	private JPanel contentPane;
	public static final Vector<String> MENU_HEADER = new Vector<String>();
	public static final Vector<Vector<String>> NULL_DATA = new Vector<Vector<String>>();
	public DefaultTableModel model;
	
	JTable table_menu;
	JScrollPane pane_menu;
	private final JLabel lblCustomizationOptions = new JLabel("CUSTOMIZATION OPTIONS");
	private final JPanel panel = new JPanel();
	private final JLabel lblNewLabel_2 = new JLabel("SAVE CHANGES");
	
	private final Action action = new SwingAction();
	private final JLabel lblNewLabel_1 = new JLabel("Click on the ingredient you want to customize.");
	private final JLabel label_menu_item_chosen = new JLabel("New label");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ingredients frame = new Ingredients(new DataHelper(), -1); //for testing locally, so it doesn't matter
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @wbp.parser.constructor
	 */
	public Ingredients(int ingr_menu_id) {
		if (MENU_HEADER.size() == 0)
		{
			MENU_HEADER.addElement("Ingredient Name");
			MENU_HEADER.addElement("Price");
		}
		initGUI();
		this.menu_id = ingr_menu_id;
		show_data_in_table();
	}
	
	/* Constructor used when transferring data from customer menu to ingredients
	*/
	public Ingredients(DataHelper api, Vector<String> all_orders, int order_type_id, String item_id, double cur_order_price, Boolean add_menu_item, int ingr_menu_id) {
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
		this.menu_id = ingr_menu_id;
//		this.cur_order += ":" + item_id; // ":" signify end of last cart menu and beginning of new set of items & its customization
//		this.order_price = cur_order_price;
		
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
	
	public Ingredients(DataHelper api, int ingr_menu_id) {
		if (MENU_HEADER.size() == 0)
		{
			MENU_HEADER.addElement("Ingredient Name");
			MENU_HEADER.addElement("Price");
		}
		this.api_connection = api;
		this.menu_id = ingr_menu_id;
		initGUI();
		show_data_in_table();
	}
	
	public class SwingAction extends AbstractAction {
        private static final long serialVersionUID = 1L;
        public SwingAction() {
            putValue(NAME, "Back");
            putValue(SHORT_DESCRIPTION, "Heading back to customer's menu.");
        }
        public void actionPerformed(ActionEvent e) {
        }
    }
	
	private void initGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 715);
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
				String ingredient_price = table_model.getValueAt(index, 1).toString();
				
				String ingredient_id = ingredients_list.elementAt(index).elementAt(0);
				
				System.out.println(name + "\t" + ingredient_id + "\t" + ingredient_price);
				
				api_connection.choose_ingredient_item_to_customize(ingredient_id);
				Customize_Screen customize = new Customize_Screen(api_connection, menu_id);
				customize.setVisible(true);
				dispose();
			}
		});
		JScrollPane pane_menu = new JScrollPane(table_menu);
		model = (DefaultTableModel)table_menu.getModel();
		pane_menu.setBounds(10, 192, 568, 374);
//		table_menu.setPreferredSize(568,374)
		contentPane.add(pane_menu);
		pane_menu.setViewportView(table_menu);
		lblCustomizationOptions.setFont(new Font("Segoe UI Black", Font.PLAIN, 40));
		lblCustomizationOptions.setHorizontalAlignment(SwingConstants.CENTER);
		lblCustomizationOptions.setBounds(10, 86, 568, 73);
		
		contentPane.add(lblCustomizationOptions);
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
	
				api_connection.add_cur_customized_menu_item();
				
				switch (menu_id) {
					case 0:
						Customer_Menu view_cust_option = new Customer_Menu(api_connection);
						view_cust_option.setVisible(true);
						break;
					case 1:
						entreeMenu view_cust_entree = new entreeMenu(api_connection);
						view_cust_entree.setVisible(true);
						break;
					case 2:
						sideMenu view_cust_side = new sideMenu(api_connection);
						view_cust_side.setVisible(true);
						break;
					case 3:
						beverageMenu view_cust_bev = new beverageMenu(api_connection);
						view_cust_bev.setVisible(true);
						break;
					case 4:
						dessertMenu view_cust_dessert = new dessertMenu(api_connection);
						view_cust_dessert.setVisible(true);
						break;
					case 5:
						Recommendations rec = new Recommendations(api_connection);
						rec.setVisible(true);
						break;
					case 6:
						rewards view_rewards = new rewards(api_connection);
						view_rewards.setVisible(true);
						break;
					default:
						Customize_Screen view_customize = new Customize_Screen(api_connection, menu_id);
						view_customize.setVisible(true);
						break;
						
				}
				dispose();
				
			}
		});
		panel.setBackground(new Color(153, 0, 0));
		panel.setBounds(179, 577, 229, 76);
		
		contentPane.add(panel);
		panel.setLayout(null);
		lblNewLabel_2.setFont(new Font("Arial Black", Font.BOLD, 20));
		lblNewLabel_2.setBounds(10, 11, 209, 54);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		
		panel.add(lblNewLabel_2);
		
		JButton button_back = new JButton("Back");
		button_back.setBackground(new Color(153, 0, 0));
		button_back.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
		});
        button_back.setAction(action);
        button_back.setBounds(10, 11, 64, 26);
        contentPane.add(button_back);
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1.setForeground(Color.WHITE);
        lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 20));
        lblNewLabel_1.setBounds(48, 151, 491, 32);
        
        contentPane.add(lblNewLabel_1);
        label_menu_item_chosen.setForeground(new Color(153, 0, 0));
        label_menu_item_chosen.setFont(new Font("Arial", Font.BOLD, 20));
        label_menu_item_chosen.setHorizontalAlignment(SwingConstants.CENTER);
        label_menu_item_chosen.setBounds(15, 53, 558, 26);
        String menu_item_key = api_connection.cart_helper.getCur_menu_item_key();
        
        label_menu_item_chosen.setText("Chosen Item: " + api_connection.getItemName(menu_item_key));
        
        contentPane.add(label_menu_item_chosen);
        
        button_back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(arg0.getSource() == button_back) {
					api_connection.delete_cur_menu_item();
					Customer_Menu menu = new Customer_Menu(api_connection);
					menu.setVisible(true);
					dispose();
				}
			}
		});
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
		
		ingredients_list = api_connection.get_ingredients_data(); // [0] = key || [1] = name || [2] = price
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
}