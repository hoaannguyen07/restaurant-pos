/*
 * Author: Adryn G
 */
package src.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.LineBorder;

public class ItemOption extends JFrame {
	protected static String foodName;
	private JPanel contentPane;
	private final JPanel panel = new JPanel();
	private final JLabel lblFoodName = new JLabel("");
	private final JButton btnCustomize = new JButton("Customize");
	private final JPanel panel_1 = new JPanel();
	private final JButton btnAddToCart = new JButton("Add to Cart");

	/**
	 * Launch the application.
	 */

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					/* Will have to edit below */
					ItemOption frame = new ItemOption("Chicken Sandwich");
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
	public ItemOption(String nameOfFood) {
		foodName = nameOfFood;
		
		initGUI();
	}
	private void initGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBackground(Color.WHITE);
		panel.setBounds(150, 40, 150, 120);
		lblFoodName.setBounds(10, 10, 130, 13);
		
		lblFoodName.setText(foodName);
		
		contentPane.add(panel);
		panel.setLayout(null);
		
		panel.add(lblFoodName);
		btnCustomize.setBounds(10, 45, 130, 21);
		
		
		btnCustomize.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(arg0.getSource() == btnCustomize) {
					/* TEST CODE: Rename to customize frame once done */
					test2 please_work = new test2();
					please_work.setVisible(true);
					dispose();
				}
						
			}
		});
		
		panel.add(btnCustomize);
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBackground(new Color(0, 102, 255));
		panel_1.setBounds(150, 160, 150, 56);
		
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		btnAddToCart.setBounds(10, 15, 130, 21);
		panel_1.add(btnAddToCart);
		
		btnAddToCart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(arg0.getSource() == btnAddToCart) {
					/* TEST CODE: Rename to add to cart once finished*/
					test3 please_work = new test3();
					please_work.setVisible(true);
					dispose();
				}
						
			}
		});
	}
}