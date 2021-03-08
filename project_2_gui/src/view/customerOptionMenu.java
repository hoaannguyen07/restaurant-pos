package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class customerOptionMenu extends JFrame {

	private JPanel contentPane;
	private final JButton btnViewMenu = new JButton("View Menu");
	private final JButton btnViewLastMeal = new JButton("View Last Meal");
	private final JButton btnPaymentInformation = new JButton("Payment Information");
	private final JButton btnRewards = new JButton("Reward Tier");
	public static String first;
	public static String last;
	public static String user;
	public static String pass;
	public static double price;
	private final JLabel lblUser = new JLabel("User:");
	private final JLabel labelUserGivenName = new JLabel("");
	private final JLabel lblFirstName = new JLabel("First Name: ");
	private final JLabel lblLastName = new JLabel("Last Name:");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					customerOptionMenu frame = new customerOptionMenu(first, last, user, pass, price);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param password 
	 * @param username 
	 * @param last_name 
	 * @param first_name 
	 */
	public customerOptionMenu(String first_name, String last_name, String username, String password, double total_price) {
		first = first_name;
		last = last_name;
		user = username;
		pass = password;
		price = total_price;
		
		initGUI();
	}
	private void initGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		labelUserGivenName.setText(user);
		lblFirstName.setText(first);
		lblLastName.setText(last);
		
		btnViewMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(arg0.getSource() == btnViewMenu) {
					DataHelper data_thing = new DataHelper();
					data_thing.first_name = first;
					data_thing.last_name = last;
					data_thing.id = user;
					data_thing.password = pass;
					
					Customer_Menu view_menu = new Customer_Menu(data_thing, price);
					view_menu.setVisible(true);
					dispose();
				}
			}
		});
		btnViewMenu.setBounds(88, 30, 250, 48);
		
		contentPane.add(btnViewMenu);
		btnViewLastMeal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(arg0.getSource() == btnViewLastMeal) { 
					prevOrder2 view_prev = new prevOrder2(first, last, user, pass); 
					view_prev.setVisible(true);
					dispose();
				}
			}
		});
		btnViewLastMeal.setBounds(88, 90, 250, 48);
		
		contentPane.add(btnViewLastMeal);
		btnPaymentInformation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(arg0.getSource() == btnPaymentInformation) { 
					cardtype view_card = new cardtype(price);
					view_card.setVisible(true);
					dispose();
				}
			}
		});
		btnPaymentInformation.setBounds(88, 150, 250, 48);
		
		contentPane.add(btnPaymentInformation);
		btnRewards.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(arg0.getSource() == btnRewards) { 
					rewards view_rewards = new rewards(first, last, user, pass);
					view_rewards.setVisible(true);
					dispose();
				}
			}
		});
		btnRewards.setBounds(88, 210, 250, 48);
		
		contentPane.add(btnRewards);
		lblUser.setBounds(12, 3, 70, 15);
		
		contentPane.add(lblUser);
		labelUserGivenName.setBounds(73, 9, 135, 9);
		
		contentPane.add(labelUserGivenName);
		lblFirstName.setBounds(197, 3, 118, 15);
		
		contentPane.add(lblFirstName);
		lblLastName.setBounds(327, 3, 98, 15);
		
		contentPane.add(lblLastName);
	}

}
