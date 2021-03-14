package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Rewards extends JFrame {
	private static final long serialVersionUID = 1L;
	
	//query variables
	DataHelper api_connection;		
	public static String first;
	public static String last;
	public static String user;
	public static String pass;
	public static double price;
	
	//JLabel
	private final JLabel eligible_text = new JLabel("Possible reward:");
	private final JLabel visits_5_text = new JLabel("5 visits");
	private final JLabel free_entree_text = new JLabel("Free medium fries");
	private final JLabel earn_reward_with = new JLabel("Earn a reward with");
	private final JLabel more_visits_label = new JLabel("more visit(s).");
	private final JLabel label_zero = new JLabel("0");
	private final JLabel lblReward = new JLabel("You have a reward!");

	private JPanel contentPane;
	private final JPanel visit_5_label = new JPanel();
	private final JButton btnBack = new JButton("Back");
	private final JButton btnAddToCart = new JButton("Add to cart");
	
	private static final int REWARDS_MENU_ID = 6;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Rewards frame = new Rewards();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}//end try catch
			}//end run
		});//end invoke later
	}//end main
	

	/**
	 * Create the frame.
	 */
	public Rewards(DataHelper api) {
		api_connection = api;
		
		first = api.getFirst_name();
		last = api.getLast_name();
		user = api.getId();
		pass = api.getPassword();
		
		initGUI();
	}//end constructor
	
	//Default constructor for testing purposes
	public Rewards() {
		initGUI();
	}//end empty constructor
	
	private void initGUI() {
		//panel
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 153, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//eligible text
		eligible_text.setBounds(24, 109, 116, 26);
		free_entree_text.setFont(new Font("Arial", Font.BOLD, 15));
		free_entree_text.setBounds(158, 145, 141, 26);
		contentPane.add(free_entree_text);
		
		//five visits label
		visit_5_label.setBackground(new Color(204, 0, 0));
		visit_5_label.setBounds(24, 145, 104, 33);
		contentPane.add(visit_5_label);
		visits_5_text.setForeground(new Color(255, 255, 255));
		visits_5_text.setFont(new Font("Arial", Font.BOLD, 15));
		visit_5_label.add(visits_5_text);
		
		//earn reward with label
		earn_reward_with.setFont(new Font("Arial", Font.BOLD, 15));
		earn_reward_with.setBounds(24, 38, 139, 22);
		contentPane.add(earn_reward_with);
		
		//more visits label
		more_visits_label.setFont(new Font("Arial", Font.BOLD, 15));
		more_visits_label.setBounds(263, 42, 116, 16);
		contentPane.add(more_visits_label);
		
		//default value label
		label_zero.setFont(new Font("Lucida Grande", Font.PLAIN, 24));
		label_zero.setHorizontalAlignment(SwingConstants.CENTER);
		label_zero.setBounds(173, 34, 23, 26);

		//back button label
		btnBack.setForeground(new Color(255, 255, 255));
		btnBack.setBackground(new Color(153, 0, 0));
		btnBack.setFont(new Font("Arial", Font.BOLD, 15));
		btnBack.setBounds(24, 4, 117, 25);
		contentPane.add(btnBack);
		
		lblReward.setBounds(24, 120, 116, 16);
		
		//add to cart button
		btnAddToCart.setBackground(new Color(0, 102, 255));
		btnAddToCart.setBounds(318, 145, 110, 33);
		
		/* All functional code down below */
		
		System.out.println("ID: " + api_connection.getId());
		System.out.println("Num of Visits: " + api_connection.get_num_visits());
		
		int num_visits = api_connection.get_num_visits();
		
		label_zero.setText(String.valueOf(5 - (num_visits % 5)));
		contentPane.add(label_zero);
		
		/* Determine if customer is eligible for reward, and  if so show add to cart button */
		if (num_visits % 5 == 0 && num_visits != 0) {
			contentPane.add(lblReward);
			contentPane.add(btnAddToCart);
		} else {
			contentPane.add(eligible_text);
		}//end if/else
		
		/* Back button functionality */
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(arg0.getSource() == btnBack) { 
					Customer_Option_Menu view_cust = new Customer_Option_Menu(api_connection);
					view_cust.setVisible(true);
					dispose();
				}//end if
			}//end action performed
		});//end add action listener
		
		/* Add to cart button functionality */
		btnAddToCart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(arg0.getSource() == btnAddToCart) { 
					// Must update menu first
					
					// Add medium fries to order
					api_connection.add_free_item_to_cart("S2");
					Ingredients ingr_frame = new Ingredients(api_connection, REWARDS_MENU_ID);
					ingr_frame.setVisible(true);
					dispose();
				}//end if
			}//end action performed
		});	//end add action listener
	}//end init gui
}//end class