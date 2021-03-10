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
	private final JButton btnNewButton_1 = new JButton("Back");
	
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
	 */
	
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
		contentPane.setBackground(new Color(0, 139, 139));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		button_reg = new JRadioButton("Regular");
		button_reg.setFont(new Font("Tahoma", Font.PLAIN, 14));
		button_reg.setBackground(new Color(0, 139, 139));
		button_reg.setBounds(56, 119, 91, 23);
		contentPane.add(button_reg);
		
		button_extra = new JRadioButton("Extra");
		button_extra.setFont(new Font("Tahoma", Font.PLAIN, 14));
		button_extra.setBackground(new Color(0, 139, 139));
		button_extra.setBounds(188, 119, 91, 23);
		contentPane.add(button_extra);
		
		ButtonGroup group = new ButtonGroup();
		group.add(button_reg);
		group.add(button_extra);
		
		JLabel lblNewLabel = new JLabel("Ingredient Options");
		lblNewLabel.setBackground(new Color(240, 240, 240));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(57, 34, 201, 41);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Confirm");
		btnNewButton.setAction(action);
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.setBounds(107, 212, 91, 23);
		contentPane.add(btnNewButton);
		btnNewButton_1.setBackground(new Color(255, 255, 255));
		btnNewButton_1.setFont(new Font("Arial", Font.PLAIN, 11));
		btnNewButton_1.setBounds(10, 11, 63, 23);
		
		contentPane.add(btnNewButton_1);
	}
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "Confirm");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		// determine how the ingredient is customized and add to order
		public void actionPerformed(ActionEvent e) {
			String added_ingredient = "";
			if (button_reg.isSelected()) {
				cur_price += ingredient_price;
				added_ingredient += ";" + ingredient_id;
			}
			else if (button_extra.isSelected()) {
				cur_price += ingredient_price;
				added_ingredient += ";" +"X" + ingredient_id;
			}
			
			String update_order = orders.elementAt(order_type_id);
			update_order += added_ingredient;
			orders.set(order_type_id, update_order);
			
//			order += added_ingredient;
			System.out.println("Cart so far");
			for(int i = 0; i < orders.size(); i++)
			{
				System.out.println(i + ".\t" + orders.elementAt(i));
			}
			System.out.println("Total price: " + cur_price);
			Ingredients ingregdients_frame = new Ingredients(api_connection, orders, order_type_id, menu_item, cur_price, false); // don't add menu item
			ingregdients_frame.setVisible(true);
			dispose();
		}
	}
}