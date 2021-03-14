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

public class Finish_Payment extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private double price;
	DataHelper api_connection;
	
	private JPanel contentPane;
	private final Action action = new SwingAction();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Finish_Payment frame = new Finish_Payment();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}//end try-catch
			}//end run
		});//end invoke later
	}//end main

	/**
	 * Create the frame.
	 */
	public Finish_Payment() {
		price = 0.0;
		initGUI();
	}//end empty constructor

	public Finish_Payment(double price, DataHelper api) {
		api_connection = api;
		this.price = api.cart_helper.get_total_cost();
		initGUI();
	}//end constructor

	private void initGUI() {
		//set up panel
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 370, 234);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 153, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		//final button
		JButton finalButton = new JButton("Finalize");
		finalButton.setBackground(new Color(153, 0, 0));
		finalButton.setFont(new Font("Arial", Font.BOLD, 20));
		finalButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == finalButton) {
					api_connection.writeOrdertoDatabase();
					api_connection.closeConnection();
					dispose();
				}//end if
			}//end action performed
		});//end action listener
		finalButton.setAction(action);
		finalButton.setBounds(103, 147, 143, 42);
		contentPane.add(finalButton);

		//total cost label
		JLabel total_cost_label = new JLabel("Total Cost");
		total_cost_label.setHorizontalAlignment(SwingConstants.CENTER);
		total_cost_label.setFont(new Font("Arial Black", Font.BOLD, 20));
		total_cost_label.setBounds(76, 11, 202, 47);
		contentPane.add(total_cost_label);

		String s_price = "$" + String.valueOf(price);
		if (s_price.length() < 5) {
			s_price += "0";
		}//end if

		//dollar sign price label
		JLabel dollar_sign_price_label = new JLabel(s_price);
		dollar_sign_price_label.setFont(new Font("Arial Black", Font.PLAIN, 20));
		dollar_sign_price_label.setHorizontalAlignment(SwingConstants.CENTER);
		dollar_sign_price_label.setBounds(29, 69, 296, 42);
		contentPane.add(dollar_sign_price_label);

		//thank_you_label
		JLabel thank_you_label = new JLabel("Thank you for your patronage!");
		thank_you_label.setHorizontalAlignment(SwingConstants.CENTER);
		thank_you_label.setBounds(29, 122, 296, 14);
		contentPane.add(thank_you_label);
	}//end init gui
	
	private class SwingAction extends AbstractAction {
		private static final long serialVersionUID = 1L;
		public SwingAction() {
			putValue(NAME, "Complete");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}//end swing action
		public void actionPerformed(ActionEvent e) {
			User_Type start = new User_Type();
			start.setVisible(true);
			dispose();
		}//end action performed
	}//end swing action extends
}//end class