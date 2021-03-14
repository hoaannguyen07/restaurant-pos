package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;

public class Customer_Option_Menu extends JFrame {

	private static final long serialVersionUID = 1L;

	private Vector<String> orders = new Vector<String>(); // [0] = entree || [1]  = side || [2] = beverage || [3] = dessert
	
	//JPanels
	private JPanel contentPane;
	
	//JButtons
	private final JButton btnViewMenu = new JButton("View Menu");
	private final JButton btnViewLastMeal = new JButton("View Last Meal");
	private final JButton btnPaymentInformation = new JButton("Payment Information");
	private final JButton btnRewards = new JButton("Reward Tier");
	
	//query variables
	public static String first = "";
	public static String last = "";
	public static String user = "";
	public static String pass = "";
	public static double price = 0;
	DataHelper api_connection;
	
	//JLabels
	private final JLabel lblUser = new JLabel("Welcome!");
	private final JLabel lblFirstName = new JLabel("First Name: ");
	private final JLabel lblLastName = new JLabel("Last Name:");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Customer_Option_Menu frame = new Customer_Option_Menu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}//end try-catch
			}//end run
		});//end invoke later
	}//end main

	// constructor for running frame by itself with no input
	public Customer_Option_Menu() {
		api_connection = new DataHelper();

		api_connection.first_name = first;
		api_connection.last_name = last;
		api_connection.id = user;
		api_connection.password = pass;

		// initialize orders vector
		for (int i = 0; i < 4; i++) {
			orders.addElement("");
		}//end for

		initGUI();
	}//end empty constructor

	/**
	 * Create the frame.
	 * 
	 * @param password
	 * @param username
	 * @param last_name
	 * @param first_name
	 */
	// constructor for after customer sign in
	public Customer_Option_Menu(DataHelper api) {
		api_connection = api;

		first = api_connection.first_name;
		last = api_connection.last_name;
		user = api_connection.id;
		pass = api_connection.password;
		price = 0;

		// initialize orders vector
		for (int i = 0; i < 4; i++) {
			orders.addElement("");
		}//end for

		initGUI();
	}//end constructor with api

	// constructor for when customer returns from creating an order from the menu
	public Customer_Option_Menu(DataHelper api, Vector<String> orders, double total_price) {
		api_connection = api;
		this.orders = orders;
		first = api_connection.first_name;
		last = api_connection.last_name;
		user = api_connection.id;
		pass = api_connection.password;
		price = total_price;

		initGUI();
	}//end full constructor

	private void initGUI() {
		//set up panel
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 336);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 153, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//first and last name label
		lblFirstName.setFont(new Font("Arial", Font.BOLD, 15));
		lblFirstName.setText(first);
		lblLastName.setFont(new Font("Arial", Font.BOLD, 15));
		lblLastName.setText(last);
		
		//view menu button
		btnViewMenu.setForeground(new Color(255, 255, 255));
		btnViewMenu.setFont(new Font("Arial", Font.BOLD, 20));
		btnViewMenu.setBackground(new Color(153, 0, 0));
		btnViewMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (arg0.getSource() == btnViewMenu) {
					Menu_Select view_menu = new Menu_Select(api_connection, orders, price);
					view_menu.setVisible(true);
					dispose();
				}//end if
			}//end action performed
		});//end button view menu
		btnViewMenu.setBounds(88, 50, 250, 48);
		contentPane.add(btnViewMenu);
		
		//view last meal button
		btnViewLastMeal.setFont(new Font("Arial", Font.BOLD, 20));
		btnViewLastMeal.setBackground(new Color(153, 0, 0));
		btnViewLastMeal.setForeground(new Color(255, 255, 255));
		btnViewLastMeal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (arg0.getSource() == btnViewLastMeal) {
					Previous_Order view_prev = new Previous_Order(api_connection);
					view_prev.setVisible(true);
					dispose();
				}//end if
			}//end action performed
		});//end add action listener
		btnViewLastMeal.setBounds(88, 109, 250, 48);
		contentPane.add(btnViewLastMeal);
		
		//payment info button
		btnPaymentInformation.setFont(new Font("Arial", Font.BOLD, 20));
		btnPaymentInformation.setForeground(new Color(255, 255, 255));
		btnPaymentInformation.setBackground(new Color(153, 0, 0));
		btnPaymentInformation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (arg0.getSource() == btnPaymentInformation) {
					Payment_Info view_card = new Payment_Info(api_connection);
					view_card.setVisible(true);
					dispose();
				}//end if
			}//end action performed
		});//end button payment information
		btnPaymentInformation.setBounds(88, 168, 250, 48);
		contentPane.add(btnPaymentInformation);
		
		//button rewards
		btnRewards.setBackground(new Color(153, 0, 0));
		btnRewards.setForeground(new Color(255, 255, 255));
		btnRewards.setFont(new Font("Arial", Font.BOLD, 20));
		btnRewards.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (arg0.getSource() == btnRewards) {
					Rewards view_rewards = new Rewards(api_connection);
					view_rewards.setVisible(true);
					dispose();
				}//end if
			}//end action performed
		});//end add action listener
		btnRewards.setBounds(88, 227, 250, 48);
		contentPane.add(btnRewards);
		
		//user label
		lblUser.setFont(new Font("Arial", Font.BOLD, 15));
		lblUser.setBounds(31, 3, 85, 20);
		contentPane.add(lblUser);
		
		//last and first name labels
		lblFirstName.setBounds(167, 8, 118, 15);
		contentPane.add(lblFirstName);
		
		lblLastName.setBounds(326, 8, 98, 15);
		contentPane.add(lblLastName);
	}//end initGUI
}//end class