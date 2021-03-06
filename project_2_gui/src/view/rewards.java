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

public class rewards extends JFrame {

	private JPanel contentPane;
	private final JLabel eligible_text = new JLabel("Eligible reward:");
	private final JLabel free_med_text = new JLabel("Free medium drink/side");
	private final JPanel visits_5_panel = new JPanel();
	private final JLabel visits_5_text = new JLabel("5 visits");
	private final JPanel view_cart_panel = new JPanel();
	private final JLabel view_cart = new JLabel("VIEW CART");
	private final JLabel earn_reward_label = new JLabel("Earn a reward with");
	private final JLabel more_visits_label = new JLabel("more visit(s).");
	private final JLabel visits_num_label = new JLabel("0");

	//Launch the application.
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
	
//	public void set_value_of_visits() {
//		initComponents();
//	}

	//Create the frame.
	public rewards() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		eligible_text.setBounds(24, 93, 116, 26);
		contentPane.add(eligible_text);
		
		free_med_text.setBounds(132, 147, 162, 26);
		contentPane.add(free_med_text);
		
		JPanel add_cart_panel_3 = new JPanel();
		add_cart_panel_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		add_cart_panel_3.setBackground(new Color(30, 144, 255));
		add_cart_panel_3.setBounds(316, 147, 95, 34);
		contentPane.add(add_cart_panel_3);
		
		JLabel add_to_cart_3 = new JLabel("Add to cart");
		add_to_cart_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		add_to_cart_3.setHorizontalAlignment(SwingConstants.CENTER);
		add_to_cart_3.setBackground(new Color(30, 144, 255));
		add_cart_panel_3.add(add_to_cart_3);
		visits_5_panel.setBackground(Color.GRAY);
		visits_5_panel.setBounds(24, 147, 85, 33);
		
		contentPane.add(visits_5_panel);
		
		visits_5_panel.add(visits_5_text);
		view_cart_panel.setBackground(new Color(30, 144, 255));
		view_cart_panel.setBounds(336, 6, 95, 34);
		
		contentPane.add(view_cart_panel);
		view_cart.setForeground(new Color(255, 255, 255));
		view_cart.setHorizontalAlignment(SwingConstants.CENTER);
		view_cart.setBackground(new Color(30, 144, 255));
		
		view_cart_panel.add(view_cart);
		earn_reward_label.setBounds(48, 39, 116, 16);
		
		contentPane.add(earn_reward_label);
		more_visits_label.setBounds(208, 39, 116, 16);
		
		contentPane.add(more_visits_label);
		visits_num_label.setFont(new Font("Lucida Grande", Font.PLAIN, 24));
		visits_num_label.setHorizontalAlignment(SwingConstants.CENTER);
		visits_num_label.setBounds(173, 34, 23, 26);
		
		String num_visits = "0"; //will change depending on API query
		visits_num_label.setText(num_visits);
		contentPane.add(visits_num_label);
	}
}
