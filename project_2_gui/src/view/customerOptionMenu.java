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

public class customerOptionMenu extends JFrame {
	
	private static final long serialVersionUID = 1L;
	DataHelper api_connection;
	private Vector<String> orders = new Vector<String>(); // [0] = entree || [1] = side || [2] = beverage || [3] = dessert

	private JPanel contentPane;
	private final JButton btnViewMenu = new JButton("View Menu");
	private final JButton btnViewLastMeal = new JButton("View Last Meal");
	private final JButton btnPaymentInformation = new JButton("Payment Information");
	private final JButton btnRewards = new JButton("Reward Tier");
	public static String first = "";
	public static String last = "";
	public static String user = "";
	public static String pass = "";
	public static double price = 0;
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
					customerOptionMenu frame = new customerOptionMenu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	//constructor for running frame by itself with no input
	public customerOptionMenu() {
		
		api_connection = new DataHelper();
		
		api_connection.first_name = first;
		api_connection.last_name = last;
		api_connection.id = user;
		api_connection.password = pass;
		
		// initialize orders vector
		for(int i = 0; i < 4; i++)
		{
			orders.addElement("");
		}
		
		initGUI();
	}

	/**
	 * Create the frame.
	 * @param password 
	 * @param username 
	 * @param last_name 
	 * @param first_name 
	 */
	// constructor for after customer sign in
	public customerOptionMenu(DataHelper api) {
		api_connection = api;
		
		first = api_connection.first_name;
		last = api_connection.last_name;
		user = api_connection.id;
		pass = api_connection.password;
		price = 0;
		
		// initialize orders vector
		for(int i = 0; i < 4; i++)
		{
			orders.addElement("");
		}
		
		initGUI();
	}
	
	//constructor for when customer returns from creating an order from the menu
	public customerOptionMenu(DataHelper api, Vector<String> orders, double total_price) {
		api_connection = api;
		this.orders = orders;
		first = api_connection.first_name;
		last = api_connection.last_name;
		user = api_connection.id;
		pass = api_connection.password;
		price = total_price;
		
		initGUI();
	}
	
	private void initGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 336);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 153, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		lblFirstName.setFont(new Font("Arial", Font.BOLD, 15));
		lblFirstName.setText(first);
		lblLastName.setFont(new Font("Arial", Font.BOLD, 15));
		lblLastName.setText(last);
		btnViewMenu.setForeground(new Color(255, 255, 255));
		btnViewMenu.setFont(new Font("Arial", Font.BOLD, 20));
		btnViewMenu.setBackground(new Color(153, 0, 0));
		
		btnViewMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(arg0.getSource() == btnViewMenu) {
					menuSelect view_menu = new menuSelect(api_connection, orders, price);
					view_menu.setVisible(true);
					dispose();
				}
			}
		});
		btnViewMenu.setBounds(88, 50, 250, 48);
		
		contentPane.add(btnViewMenu);
		btnViewLastMeal.setFont(new Font("Arial", Font.BOLD, 20));
		btnViewLastMeal.setBackground(new Color(153, 0, 0));
		btnViewLastMeal.setForeground(new Color(255, 255, 255));
		btnViewLastMeal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(arg0.getSource() == btnViewLastMeal) { 
					prevOrder2 view_prev = new prevOrder2(api_connection); 
					view_prev.setVisible(true);
					dispose();
				}
			}
		});
		btnViewLastMeal.setBounds(88, 109, 250, 48);
		
		contentPane.add(btnViewLastMeal);
		btnPaymentInformation.setFont(new Font("Arial", Font.BOLD, 20));
		btnPaymentInformation.setForeground(new Color(255, 255, 255));
		btnPaymentInformation.setBackground(new Color(153, 0, 0));
		btnPaymentInformation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(arg0.getSource() == btnPaymentInformation) { 
					cardtype view_card = new cardtype(api_connection);
					view_card.setVisible(true);
					dispose();
				}
			}
		});
		btnPaymentInformation.setBounds(88, 168, 250, 48);
		
		contentPane.add(btnPaymentInformation);
		btnRewards.setBackground(new Color(153, 0, 0));
		btnRewards.setForeground(new Color(255, 255, 255));
		btnRewards.setFont(new Font("Arial", Font.BOLD, 20));
		btnRewards.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(arg0.getSource() == btnRewards) { 
					rewards view_rewards = new rewards(api_connection);
					view_rewards.setVisible(true);
					dispose();
				}
			}
		});
		btnRewards.setBounds(88, 227, 250, 48);
		
		contentPane.add(btnRewards);
		lblUser.setFont(new Font("Arial", Font.BOLD, 15));
		lblUser.setBounds(31, 3, 85, 20);
		
		contentPane.add(lblUser);
		lblFirstName.setBounds(167, 8, 118, 15);
		
		contentPane.add(lblFirstName);
		lblLastName.setBounds(326, 8, 98, 15);
		
		contentPane.add(lblLastName);
	}

}
