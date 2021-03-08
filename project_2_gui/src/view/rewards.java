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
import java.beans.PropertyChangeEvent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class rewards extends JFrame {

	private JPanel contentPane;
	private final JLabel eligible_text = new JLabel("Eligible rewards:");
	private final JLabel visits_25_text = new JLabel("25 visits");
	private final JLabel free_entree_text = new JLabel("Free entree");
	private final JLabel free_shake_text = new JLabel("Free milkshake");
	private final JLabel free_med_text = new JLabel("Free medium drink/side");
	private final JLabel add_to_cart_1 = new JLabel("Add to cart");
	private final JPanel panel_3 = new JPanel();
	private final JPanel panel_3_1 = new JPanel();
	private final JLabel visits_25_text_1 = new JLabel("10 visits");
	private final JPanel panel_3_1_1 = new JPanel();
	private final JLabel visits_25_text_1_1 = new JLabel("5 visits");
	private final JPanel panel_4 = new JPanel();
	private final JLabel view_cart = new JLabel("VIEW CART");
	private final JLabel lblNewLabel = new JLabel("Earn a reward with");
	private final JLabel lblNewLabel_1 = new JLabel("more visit(s).");
	private final JLabel lblNewLabel_2 = new JLabel("0");
	private final JButton btnBack = new JButton("Back");
	public static String first;
	public static String last;
	public static String user;
	public static String pass;
	public static double price;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					rewards frame = new rewards(first, last, user, pass);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
//	public void set_value_of_visits() {
//		initComponents();
//	}

	/**
	 * Create the frame.
	 */
	public rewards(String first_name, String last_name, String username, String password) {
		first = first_name;
		last = last_name;
		user = username;
		pass = password;
		initGUI();
	}
	private void initGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		eligible_text.setBounds(24, 93, 116, 26);
		contentPane.add(eligible_text);
		
		free_entree_text.setBounds(130, 134, 85, 26);
		contentPane.add(free_entree_text);
		
				free_shake_text.setBounds(130, 172, 110, 26);
		contentPane.add(free_shake_text);
		
		free_med_text.setBounds(130, 210, 162, 26);
		contentPane.add(free_med_text);
		
		String[] visits = {"0", "1", "2", "3", "4"};
		SpinnerListModel visitslist = new SpinnerListModel(visits);
		
		String rewards = "3";
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(30, 144, 255));
		panel.setBounds(318, 124, 95, 34);
		contentPane.add(panel);
		panel.add(add_to_cart_1);
		
		add_to_cart_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		add_to_cart_1.setHorizontalAlignment(SwingConstants.CENTER);
		add_to_cart_1.setBackground(new Color(30, 144, 255));
		
		JPanel panel_1 = new JPanel();
		panel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		panel_1.setBackground(new Color(30, 144, 255));
		panel_1.setBounds(318, 165, 95, 34);
		contentPane.add(panel_1);
		
		JLabel add_to_cart_2 = new JLabel("Add to cart");
		add_to_cart_2.setHorizontalAlignment(SwingConstants.CENTER);
		add_to_cart_2.setBackground(new Color(30, 144, 255));
		panel_1.add(add_to_cart_2);
		JPanel panel_2 = new JPanel();
		panel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		panel_2.setBackground(new Color(30, 144, 255));
		panel_2.setBounds(318, 203, 95, 34);
		contentPane.add(panel_2);
		
		JLabel add_to_cart_3 = new JLabel("Add to cart");
		add_to_cart_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		add_to_cart_3.setHorizontalAlignment(SwingConstants.CENTER);
		add_to_cart_3.setBackground(new Color(30, 144, 255));
		panel_2.add(add_to_cart_3);
		panel_3.setBackground(new Color(128, 128, 128));
		panel_3.setBounds(24, 124, 85, 33);
		
		contentPane.add(panel_3);
		panel_3.add(visits_25_text);
		panel_3_1.setBackground(Color.GRAY);
		panel_3_1.setBounds(24, 165, 85, 33);
		
		contentPane.add(panel_3_1);
		
		panel_3_1.add(visits_25_text_1);
		panel_3_1_1.setBackground(Color.GRAY);
		panel_3_1_1.setBounds(24, 203, 85, 33);
		
		contentPane.add(panel_3_1_1);
		
		panel_3_1_1.add(visits_25_text_1_1);
		panel_4.setBackground(new Color(30, 144, 255));
		panel_4.setBounds(336, 6, 95, 34);
		
		contentPane.add(panel_4);
		view_cart.setForeground(new Color(255, 255, 255));
		view_cart.setHorizontalAlignment(SwingConstants.CENTER);
		view_cart.setBackground(new Color(30, 144, 255));
		
		panel_4.add(view_cart);
		
		JLabel label = new JLabel("New label");
		label.setBounds(179, 98, 61, 16);
		contentPane.add(label);
		lblNewLabel.setBounds(48, 39, 116, 16);
		
		contentPane.add(lblNewLabel);
		lblNewLabel_1.setBounds(208, 39, 116, 16);
		
		contentPane.add(lblNewLabel_1);
		lblNewLabel_2.setFont(new Font("Lucida Grande", Font.PLAIN, 24));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(173, 34, 23, 26);
		
		String num_visits = "3"; //will change depending on API query
		lblNewLabel_2.setText(num_visits);
		contentPane.add(lblNewLabel_2);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(arg0.getSource() == btnBack) { 
					customerOptionMenu view_cust = new customerOptionMenu(first, last, user, pass, price);
					view_cust.setVisible(true);
					dispose();
				}
			}
		});
		btnBack.setBounds(23, 0, 117, 25);
		
		contentPane.add(btnBack);
	}
}
