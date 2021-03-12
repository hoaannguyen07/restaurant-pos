package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.SpinnerListModel;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;

import java.awt.Button;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;
import javax.swing.JSpinner;
import javax.swing.JProgressBar;
import java.beans.PropertyChangeListener;
import java.util.Vector;
import java.beans.PropertyChangeEvent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class rewards extends JFrame {
	DataHelper api_connection;		
	public static String first;
	public static String last;
	public static String user;
	public static String pass;
	public static double price;

	private JPanel contentPane;
	private final JLabel eligible_text = new JLabel("Possible reward:");
	private final JLabel visits_25_text = new JLabel("5 visits");
	private final JLabel free_entree_text = new JLabel("Free medium fries");
	private final JPanel panel_3 = new JPanel();
	private final JLabel lblNewLabel = new JLabel("Earn a reward with");
	private final JLabel lblNewLabel_1 = new JLabel("more visit(s).");
	private final JLabel lblNewLabel_2 = new JLabel("0");
	private final JButton btnBack = new JButton("Back");
	private final JButton btnCart = new JButton("Cart");
	private final JLabel lblReward = new JLabel("You have a reward!");
	private final JButton btnAddToCart = new JButton("Add to cart");
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					rewards frame = new rewards();
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
	public rewards(DataHelper api) {
		api_connection = api;
		
		first = api.getFirst_name();
		last = api.getLast_name();
		user = api.getId();
		pass = api.getPassword();
		
		initGUI();
	}
	
	//Default constructor for testing purposes
	public rewards() {
		initGUI();
	}
	
	private void initGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		eligible_text.setBounds(24, 109, 116, 26);
		
		free_entree_text.setBounds(138, 145, 116, 26);
		contentPane.add(free_entree_text);
		
		String[] visits = {"0", "1", "2", "3", "4"};
		SpinnerListModel visitslist = new SpinnerListModel(visits);
		panel_3.setBackground(new Color(128, 128, 128));
		panel_3.setBounds(24, 145, 85, 33);
		
		contentPane.add(panel_3);
		panel_3.add(visits_25_text);
		lblNewLabel.setBounds(48, 39, 116, 16);
		
		contentPane.add(lblNewLabel);
		lblNewLabel_1.setBounds(208, 39, 116, 16);
		
		contentPane.add(lblNewLabel_1);
		lblNewLabel_2.setFont(new Font("Lucida Grande", Font.PLAIN, 24));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(173, 34, 23, 26);
		
		btnBack.setBounds(24, 4, 117, 25);
		
		contentPane.add(btnBack);
		btnCart.setBackground(new Color(0, 102, 255));
		btnCart.setBounds(318, 3, 110, 26);
		
		contentPane.add(btnCart);
		
		lblReward.setBounds(24, 120, 116, 16);
		
		btnAddToCart.setBackground(new Color(0, 102, 255));
		btnAddToCart.setBounds(318, 145, 110, 33);
		
		/* All functional code down below */
		
		/* TEST: Default customer info to test
		 * Delete later */
		DataHelper api_connection = new DataHelper();
		api_connection.first_name = "Mary";
		api_connection.last_name = "Smith";
		api_connection.id = "7"; //For test purposes: ID 1 has 1 visit, ID 7 had 260 visits
		api_connection.password = "7";	
		
		/* End TEST code to delete later */
		
		System.out.println("ID: " + api_connection.getId());
		System.out.println("Num of Visits: " + api_connection.get_num_visits());
		
		int num_visits = api_connection.get_num_visits();
		
		lblNewLabel_2.setText(String.valueOf(5 - (num_visits % 5)));
		contentPane.add(lblNewLabel_2);
		
		/* Determine if customer is eligible for reward, and  if so show add to cart button */
		if (num_visits % 5 == 0 && num_visits != 0)
		{
			contentPane.add(lblReward);
			contentPane.add(btnAddToCart);
		} else {
			contentPane.add(eligible_text);
		}
		
		/* Back button functionality */
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(arg0.getSource() == btnBack) { 
					customerOptionMenu view_cust = new customerOptionMenu(api_connection);
					view_cust.setVisible(true);
					dispose();
				}
			}
		});
		
		/* Cart button functionality */
		btnCart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(arg0.getSource() == btnCart) { 
					cart view_cart = new cart();
					view_cart.setVisible(true);
					dispose();
				}
			}
		});	
		
		/* Add to cart button functionality */
		btnAddToCart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(arg0.getSource() == btnAddToCart) { 
					// Must update menu first
					Vector<Vector<String>> menu_list = api_connection.get_menu_data();
					
					// Add medium fries to order
					api_connection.add_free_item_to_cart("S2");
					Ingredients ingr_frame = new Ingredients(api_connection);
					ingr_frame.setVisible(true);
					dispose();
				}
			}
		});	
	}
}