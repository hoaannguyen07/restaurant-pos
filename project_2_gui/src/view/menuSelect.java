package view;

import java.awt.BorderLayout;
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

public class menuSelect extends JFrame {

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
					menuSelect frame = new menuSelect(new DataHelper());
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
	public menuSelect(DataHelper api) {
		this.api_connection = api;
		for(int i = 0; i < 4; i++)
		{
			orders.addElement("");
		}
		initGUI();
	}
	
	public menuSelect(DataHelper api, Vector<String> all_orders, double price) {
		this.api_connection = api;
		this.orders = all_orders;
		this.total_price = price;
		
		System.out.println(orders.elementAt(0));
		System.out.println(orders.elementAt(1));
		System.out.println(orders.elementAt(2));
		System.out.println(orders.elementAt(3));
		System.out.println("Price: " + total_price);
		initGUI();
	}
	
	void initGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 139, 139));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Select a Menu");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 22));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(125, 11, 184, 26);
		contentPane.add(lblNewLabel);
		
		JButton btnFullMenu = new JButton("Full Menu");
		btnFullMenu.setBounds(162, 62, 109, 31);
		btnFullMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(arg0.getSource() == btnFullMenu) { 
					Customer_Menu view_cust = new Customer_Menu(api_connection, orders, total_price);
					view_cust.setVisible(true);
					dispose();
				}
			}
		});
		contentPane.add(btnFullMenu);
		
		JButton btnEntrees = new JButton("Entrees");
		btnEntrees.setBounds(81, 119, 109, 31);
		btnEntrees.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(arg0.getSource() == btnEntrees) { 
					entreeMenu view_cust = new entreeMenu(api_connection, orders, total_price);
					view_cust.setVisible(true);
					dispose();
				}
			}
		});
		contentPane.add(btnEntrees);
		
		JButton btnBeverages = new JButton("Beverages");
		btnBeverages.setBounds(81, 183, 109, 31);
		btnBeverages.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(arg0.getSource() == btnBeverages) { 
					beverageMenu view_cust = new beverageMenu(api_connection, orders, total_price);
					view_cust.setVisible(true);
					dispose();
				}
			}
		});
		contentPane.add(btnBeverages);
		
		JButton btnSides = new JButton("Sides");
		btnSides.setBounds(243, 119, 109, 31);
		btnSides.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(arg0.getSource() == btnSides) { 
					sideMenu view_cust = new sideMenu(api_connection, orders, total_price);
					view_cust.setVisible(true);
					dispose();
				}
			}
		});
		contentPane.add(btnSides);
		
		JButton btnDesserts = new JButton("Desserts");
		btnDesserts.setBounds(243, 183, 109, 31);
		btnDesserts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(arg0.getSource() == btnDesserts) { 
					dessertMenu view_cust = new dessertMenu(api_connection, orders, total_price);
					view_cust.setVisible(true);
					dispose();
				}
			}
		});
		contentPane.add(btnDesserts);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(10, 14, 72, 23);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(arg0.getSource() == btnBack) { 
					customerOptionMenu customer_options = new customerOptionMenu(api_connection, orders, total_price);
					customer_options.setVisible(true);
					dispose();
				}
			}
		});
		contentPane.add(btnBack);
	}
}