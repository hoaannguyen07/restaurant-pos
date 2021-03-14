package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;

public class Menu_Select extends JFrame {

	private static final long serialVersionUID = 1L;
	DataHelper api_connection;
	private Vector<String> orders = new Vector<String>(); // [0] = entree || [1] = side || [2] = beverage || [3] = dessert
	
	private double total_price = 0.0;
	
	private JPanel contentPane;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu_Select frame = new Menu_Select(new DataHelper());
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}//end try catch
			}//end run
		});//end invoke later
	}//end main

	/**
	 * Create the frame.
	 * @wbp.parser.constructor
	 */
	public Menu_Select(DataHelper api) {
		this.api_connection = api;
		for(int i = 0; i < 4; i++) {
			orders.addElement("");
		}//end for
		initGUI();
	}//end menu select constructor
	
	public Menu_Select(DataHelper api, Vector<String> all_orders, double price) {
		this.api_connection = api;
		this.orders = all_orders;
		this.total_price = price;
		
		System.out.println(orders.elementAt(0));
		System.out.println(orders.elementAt(1));
		System.out.println(orders.elementAt(2));
		System.out.println(orders.elementAt(3));
		System.out.println("Price: " + total_price);
		initGUI();
	}//end full constructor
	
	void initGUI() {
		//panel
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 308);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 153, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//select menu label
		JLabel select_menu_label = new JLabel("Select a Menu");
		select_menu_label.setFont(new Font("Arial", Font.BOLD, 25));
		select_menu_label.setHorizontalAlignment(SwingConstants.CENTER);
		select_menu_label.setBounds(125, 38, 184, 26);
		contentPane.add(select_menu_label);
		
		//button for the full menu
		JButton btnFullMenu = new JButton("Full Menu");
		btnFullMenu.setForeground(new Color(255, 255, 255));
		btnFullMenu.setBackground(new Color(153, 0, 0));
		btnFullMenu.setFont(new Font("Arial", Font.BOLD, 15));
		btnFullMenu.setBounds(162, 89, 109, 31);
		btnFullMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(arg0.getSource() == btnFullMenu) { 
					Customer_Menu view_cust = new Customer_Menu(api_connection, orders, total_price);
					view_cust.setVisible(true);
					dispose();
				}//end if
			}//end action performed
		});//end add action listener
		contentPane.add(btnFullMenu);
		
		//end entrees button
		JButton btnEntrees = new JButton("Entrees");
		btnEntrees.setBackground(new Color(153, 0, 0));
		btnEntrees.setForeground(new Color(255, 255, 255));
		btnEntrees.setFont(new Font("Arial", Font.BOLD, 15));
		btnEntrees.setBounds(81, 146, 109, 31);
		btnEntrees.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(arg0.getSource() == btnEntrees) { 
					Entree_Menu view_cust = new Entree_Menu(api_connection, orders, total_price);
					view_cust.setVisible(true);
					dispose();
				}//end if
			}//end action performed
		});//end add action listener
		contentPane.add(btnEntrees);
		
		//beverages button
		JButton btnBeverages = new JButton("Beverages");
		btnBeverages.setBackground(new Color(153, 0, 0));
		btnBeverages.setForeground(new Color(255, 255, 255));
		btnBeverages.setFont(new Font("Arial", Font.BOLD, 14));
		btnBeverages.setBounds(81, 210, 109, 31);
		btnBeverages.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(arg0.getSource() == btnBeverages) { 
					Beverage_Menu view_cust = new Beverage_Menu(api_connection, orders, total_price);
					view_cust.setVisible(true);
					dispose();
				}//end if
			}//end action performed
		});//end add action listener
		contentPane.add(btnBeverages);
		
		//sides button
		JButton btnSides = new JButton("Sides");
		btnSides.setForeground(new Color(255, 255, 255));
		btnSides.setBackground(new Color(153, 0, 0));
		btnSides.setFont(new Font("Arial", Font.BOLD, 15));
		btnSides.setBounds(243, 146, 109, 31);
		btnSides.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(arg0.getSource() == btnSides) { 
					Side_Menu view_cust = new Side_Menu(api_connection, orders, total_price);
					view_cust.setVisible(true);
					dispose();
				}//end if
			}//end action performed
		});//end add action listener
		contentPane.add(btnSides);
		
		//desserts button
		JButton btnDesserts = new JButton("Desserts");
		btnDesserts.setForeground(new Color(255, 255, 255));
		btnDesserts.setBackground(new Color(153, 0, 0));
		btnDesserts.setFont(new Font("Arial", Font.BOLD, 15));
		btnDesserts.setBounds(243, 210, 109, 31);
		btnDesserts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(arg0.getSource() == btnDesserts) { 
					Dessert_Menu view_cust = new Dessert_Menu(api_connection, orders, total_price);
					view_cust.setVisible(true);
					dispose();
				}//end if
			}//end action performed
		});//end add action listener
		contentPane.add(btnDesserts);
		
		//back button
		JButton btnBack = new JButton("Back");
		btnBack.setForeground(new Color(255, 255, 255));
		btnBack.setBackground(new Color(153, 0, 0));
		btnBack.setFont(new Font("Arial", Font.BOLD, 15));
		btnBack.setBounds(10, 14, 72, 23);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(arg0.getSource() == btnBack) { 
					Customer_Option_Menu customer_options = new Customer_Option_Menu(api_connection, orders, total_price);
					customer_options.setVisible(true);
					dispose();
				}//end if
			}//end action performed
		});//end add action listener
		contentPane.add(btnBack);
	}//end init gui
}//end class