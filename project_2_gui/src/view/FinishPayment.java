/*
 * Author: Adryn G
 */
package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.border.LineBorder;

public class FinishPayment extends JFrame {

	private JPanel contentPane;
	private final JPanel panel = new JPanel();
	private final JLabel lblNewLabel = new JLabel("Orders");
	private final JPanel panel_1 = new JPanel();
	private final JLabel lblNewLabel_1 = new JLabel("New label");
	private final JPanel panel_2 = new JPanel();
	private final JPanel panel_3 = new JPanel();
	private final JPanel panel_4 = new JPanel();
	private final JLabel lblNewLabel_2 = new JLabel("New label");
	private final JLabel lblNewLabel_3 = new JLabel("New label");
	private final JLabel lblNewLabel_4 = new JLabel("New label");
	private final JPanel panel_5 = new JPanel();
	private final JLabel lblNewLabel_5 = new JLabel("New label");
	private final JPanel panel_6 = new JPanel();
	private final JLabel lblTotalCost = new JLabel("Total Cost");
	private final JPanel panel_7 = new JPanel();
	private final JLabel lblNewLabel_6 = new JLabel("New label");
	private final JPanel panel_8 = new JPanel();
	private final JLabel paymentMethodInfo = new JLabel("Payment Method Information");
	private final JPanel panel_9 = new JPanel();
	private final JLabel lblNewLabel_7 = new JLabel("New label");
	private final JPanel panel_10 = new JPanel();
	private final JLabel lblNewLabel_8 = new JLabel("Thank you for your patronage.");
	private final JPanel panel_11 = new JPanel();
	private final JLabel lblNewLabel_9 = new JLabel("Enjoy your meal!");

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
		initGUI();
	}
	private void initGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		panel.setBounds(10, 10, 416, 24);
		
		contentPane.add(panel);
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		panel.add(lblNewLabel);
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBounds(10, 40, 416, 24);
		
		contentPane.add(panel_1);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		
		panel_1.add(lblNewLabel_1);
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2.setBounds(10, 64, 416, 24);
		
		contentPane.add(panel_2);
		
		panel_2.add(lblNewLabel_2);
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_3.setBounds(10, 88, 416, 24);
		
		contentPane.add(panel_3);
		
		panel_3.add(lblNewLabel_3);
		panel_4.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_4.setBounds(10, 112, 416, 24);
		
		contentPane.add(panel_4);
		
		panel_4.add(lblNewLabel_4);
		panel_5.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_5.setBounds(10, 136, 416, 24);
		
		contentPane.add(panel_5);
		
		panel_5.add(lblNewLabel_5);
		panel_6.setBounds(10, 200, 416, 24);
		
		contentPane.add(panel_6);
		lblTotalCost.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		panel_6.add(lblTotalCost);
		panel_7.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_7.setBounds(10, 229, 416, 24);
		
		contentPane.add(panel_7);
		
		panel_7.add(lblNewLabel_6);
		panel_8.setBounds(10, 288, 416, 24);
		
		contentPane.add(panel_8);
		paymentMethodInfo.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		panel_8.add(paymentMethodInfo);
		panel_9.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_9.setBounds(10, 317, 416, 24);
		
		contentPane.add(panel_9);
		
		panel_9.add(lblNewLabel_7);
		panel_10.setBounds(10, 351, 416, 24);
		
		contentPane.add(panel_10);
		
		panel_10.add(lblNewLabel_8);
		panel_11.setBounds(10, 375, 416, 28);
		
		contentPane.add(panel_11);
		
		panel_11.add(lblNewLabel_9);
	}

}