package view;

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
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import java.util.Vector;

import javax.swing.Action;
import javax.swing.ButtonGroup;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.SystemColor;

public class Customize_Screen extends JFrame {

	private static final long serialVersionUID = 1L;
//	private String order_test;
	
	Vector<String> orders; // [0] = entree || [1] = side || [2] = beverage || [3] = dessert
//	private int order_type_id = -1;
	
//	private String menu_item = "";
//	private String cur_order = "";
	
//	private double cur_price = 0.0;
//	private double ingredient_price = 0.0;
	
	private JPanel contentPane;
	private JRadioButton button_reg;
	private JRadioButton button_extra;
	private final Action action = new SwingAction();
	
	private final int id;
	
	DataHelper api_connection;
	private final JButton button_back = new JButton("Back");
	private JLabel lblNewLabel;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Customize_Screen frame = new Customize_Screen(new DataHelper(), 0);
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
	
	public Customize_Screen(DataHelper api, int ingr_menu_id) {
		this.api_connection = api;
		this.id = ingr_menu_id;
		initGUI();
	}
	
	public Customize_Screen(DataHelper api, String cur_order, int order_type_id) {
		this.api_connection = api;
		this.id = order_type_id;
//		this.order_test = cur_order;
		initGUI();
	}
	
	public Customize_Screen(DataHelper api, Vector<String> all_orders, int order_type_id, String item_id, String ingredient_id, double cur_price, double ingredient_price) {
		this.api_connection = api;
		this.orders = all_orders;
//		this.order_type_id = order_type_id;
//		this.menu_item = item_id;
		this.id = order_type_id;
//		this.cur_price = cur_price;
//		this.ingredient_price = ingredient_price;
		
		initGUI();
	}
	
	private void initGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 328, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 153, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		button_reg = new JRadioButton("Regular");
		button_reg.setFont(new Font("Tahoma", Font.PLAIN, 14));
		button_reg.setBackground(new Color(0, 153, 204));
		button_reg.setBounds(58, 134, 91, 23);
		contentPane.add(button_reg);
		
		button_extra = new JRadioButton("Extra");
		button_extra.setFont(new Font("Tahoma", Font.PLAIN, 14));
		button_extra.setBackground(new Color(0, 153, 204));
		button_extra.setBounds(190, 134, 91, 23);
		contentPane.add(button_extra);
		
		ButtonGroup group = new ButtonGroup();
		group.add(button_reg);
		group.add(button_extra);
		
		JLabel label_title = new JLabel("Ingredient Options");
		label_title.setBackground(new Color(240, 240, 240));
		label_title.setFont(new Font("Tahoma", Font.BOLD, 25));
		label_title.setHorizontalAlignment(SwingConstants.CENTER);
		label_title.setBounds(28, 49, 259, 44);
		contentPane.add(label_title);
		
		JButton button_confirm = new JButton("Confirm");
		button_confirm.setAction(action);
		button_confirm.setBackground(new Color(153, 0, 0));
		button_confirm.setBounds(107, 212, 91, 23);
		contentPane.add(button_confirm);
		button_back.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				api_connection.reset_cart_ingredient_id();
				Ingredients ingredients_frame = new Ingredients(api_connection, id);
				ingredients_frame.setVisible(true);
				dispose();
			}
		});
		button_back.setBackground(new Color(153, 0, 0));
		button_back.setFont(new Font("Arial", Font.PLAIN, 11));
		button_back.setBounds(10, 11, 63, 23);
		
		contentPane.add(button_back);
		
		String ingredient_id = api_connection.cart_helper.getCur_ingredient_item_key();
		lblNewLabel = new JLabel(api_connection.getIngredientName(ingredient_id));
		lblNewLabel.setForeground(new Color(153, 0, 0));
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(70, 104, 175, 23);
		contentPane.add(lblNewLabel);
	}
	private class SwingAction extends AbstractAction {
		private static final long serialVersionUID = 1L;
		public SwingAction() {
			putValue(NAME, "Confirm");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		// determine how the ingredient is customized and add to order
		public void actionPerformed(ActionEvent e) {

			if (button_reg.isSelected())
			{
				api_connection.add_cur_ingredient_as_customization(1);
			}
			else if (button_extra.isSelected())
			{
				api_connection.add_cur_ingredient_as_customization(2);
			}
			
			Ingredients ingredients_frame = new Ingredients(api_connection, id);
			ingredients_frame.setVisible(true);
			dispose();
		}
	}
}