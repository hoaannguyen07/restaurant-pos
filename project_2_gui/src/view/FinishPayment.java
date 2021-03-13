/*
 * Author: Adryn G
 */
package view;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import java.awt.event.ActionListener;

public class FinishPayment extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private double price;
	private JPanel contentPane;
	private final Action action = new SwingAction();
	DataHelper api_connection;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FinishPayment frame = new FinishPayment();
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
	public FinishPayment() {
		price = 0.0;
		initGUI();
	}
	
	public FinishPayment(double price, DataHelper api) {
		api_connection = api;
		this.price = api.cart_helper.getTotal_cost();
		initGUI();
	}
	
	private void initGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 370, 234);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 153, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton finalButton = new JButton("Finalize");
		finalButton.setBackground(new Color(153, 0, 0));
		finalButton.setFont(new Font("Arial", Font.BOLD, 20));
		finalButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == finalButton) { 
					api_connection.writeOrdertoDatabase();
					api_connection.closeConnection();
					dispose();
				}
			}
		});
		finalButton.setAction(action);
		finalButton.setBounds(103, 147, 143, 42);
		contentPane.add(finalButton);
		
		JLabel lblNewLabel = new JLabel("Total Cost");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Arial Black", Font.BOLD, 20));
		lblNewLabel.setBounds(76, 11, 202, 47);
		contentPane.add(lblNewLabel);
		
		String s_price = "$" + String.valueOf(price);
		if (s_price.length() < 5) {
			s_price += "0";
		}
		
		JLabel lblNewLabel_1 = new JLabel(s_price);
		lblNewLabel_1.setFont(new Font("Arial Black", Font.PLAIN, 20));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(29, 69, 296, 42);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Thank you for your patronage!");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(29, 122, 296, 14);
		contentPane.add(lblNewLabel_2);
	}
	private class SwingAction extends AbstractAction {
		private static final long serialVersionUID = 1L;
		public SwingAction() {
			putValue(NAME, "Complete");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			User_Type start = new User_Type();
			start.setVisible(true);
			dispose();
		}
	}
}