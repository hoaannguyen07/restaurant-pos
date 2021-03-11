package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JScrollPane;
import java.awt.Component;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import java.util.Vector;

import javax.swing.Action;
import javax.swing.ButtonGroup;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Customize_Screen extends JFrame {

	private String order_test;
	private String ingredient_id;
	
	Vector<String> orders; // [0] = entree || [1] = side || [2] = beverage || [3] = dessert
	private int order_type_id = -1;
	
	private String menu_item = "";
	private String cur_order = "";
	
	private double cur_price = 0.0;
	private double ingredient_price = 0.0;
	
	private JPanel contentPane;
	private JRadioButton button_reg;
	private JRadioButton button_extra;
	private final Action action = new SwingAction();
	
	DataHelper api_connection;
	private final JButton button_back = new JButton("Back");
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Customize_Screen frame = new Customize_Screen(new DataHelper(), "E1", "I10");
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
	
	public Customize_Screen(DataHelper api) {
		this.api_connection = api;
		initGUI();
	}
	
	public Customize_Screen(DataHelper api,  String cur_order, String id) {
		this.api_connection = api;
		this.ingredient_id = id;
		this.order_test = cur_order;
		initGUI();
	}
	
	public Customize_Screen(DataHelper api, Vector<String> all_orders, int order_type_id, String item_id, String ingredient_id, double cur_price, double ingredient_price) {
		this.api_connection = api;
		this.orders = all_orders;
		this.order_type_id = order_type_id;
		this.menu_item = item_id;
		this.ingredient_id = ingredient_id;
		this.cur_price = cur_price;
		this.ingredient_price = ingredient_price;
		
		initGUI();
	}
	
	private void initGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 328, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(51, 153, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		button_reg = new JRadioButton("Regular");
		button_reg.setFont(new Font("Tahoma", Font.PLAIN, 14));
		button_reg.setBackground(new Color(51, 153, 255));
		button_reg.setBounds(56, 119, 91, 23);
		contentPane.add(button_reg);
		
		button_extra = new JRadioButton("Extra");
		button_extra.setFont(new Font("Tahoma", Font.PLAIN, 14));
		button_extra.setBackground(new Color(51, 153, 255));
		button_extra.setBounds(188, 119, 91, 23);
		contentPane.add(button_extra);
		
		ButtonGroup group = new ButtonGroup();
		group.add(button_reg);
		group.add(button_extra);
		
		JLabel label_title = new JLabel("Ingredient Options");
		label_title.setBackground(new Color(240, 240, 240));
		label_title.setFont(new Font("Tahoma", Font.BOLD, 18));
		label_title.setHorizontalAlignment(SwingConstants.CENTER);
		label_title.setBounds(57, 34, 201, 41);
		contentPane.add(label_title);
		
		JButton button_confirm = new JButton("Confirm");
		button_confirm.setAction(action);
		button_confirm.setBackground(new Color(255, 255, 255));
		button_confirm.setBounds(107, 212, 91, 23);
		contentPane.add(button_confirm);
		button_back.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				api_connection.reset_cart_ingredient_id();
				Ingredients ingredients_frame = new Ingredients(api_connection);
				ingredients_frame.setVisible(true);
				dispose();
			}
		});
		button_back.setBackground(new Color(0, 51, 51));
		button_back.setFont(new Font("Arial", Font.PLAIN, 11));
		button_back.setBounds(10, 11, 63, 23);
		
		contentPane.add(button_back);
	}
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "Confirm");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		// determine how the ingredient is customized and add to order
		public void actionPerformed(ActionEvent e) {
//			String added_ingredient = "";
//			if (button_reg.isSelected()) {
//				cur_price += ingredient_price;
//				added_ingredient += ";" + ingredient_id;
//			}
//			else if (button_extra.isSelected()) {
//				cur_price += ingredient_price;
//				added_ingredient += ";" +"X" + ingredient_id;
//			}
//			
//			String update_order = orders.elementAt(order_type_id);
//			update_order += added_ingredient;
//			orders.set(order_type_id, update_order);
//			
////			order += added_ingredient;
//			System.out.println("Cart so far");
//			for(int i = 0; i < orders.size(); i++)
//			{
//				System.out.println(i + ".\t" + orders.elementAt(i));
//			}
//			System.out.println("Total price: " + cur_price);
//			Ingredients ingregdients_frame = new Ingredients(api_connection, orders, order_type_id, menu_item, cur_price, false); // don't add menu item
//			ingregdients_frame.setVisible(true);
//			dispose();
			
			if (button_reg.isSelected())
			{
				api_connection.add_cur_ingredient_as_customization(1);
			}
			else if (button_extra.isSelected())
			{
				api_connection.add_cur_ingredient_as_customization(2);
			}
			
			Ingredients ingredients_frame = new Ingredients(api_connection);
			ingredients_frame.setVisible(true);
			dispose();
		}
	}
}