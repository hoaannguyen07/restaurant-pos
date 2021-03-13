package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import java.awt.Color;

public class Manager_Trending_Items_Option extends JFrame {

	private JPanel contentPane;
	private JPanel contentEntree;
	DataHelper api_connection;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Manager_Trending_Items_Option frame = new Manager_Trending_Items_Option(new DataHelper());
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
	public Manager_Trending_Items_Option(DataHelper api) {
		api_connection = api;
		initGUI();
	}
	
	void initGUI() { 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 541);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 153, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblChooser = new JLabel("Choose an Option:");
		lblChooser.setFont(new Font("Arial", Font.BOLD, 20));
		lblChooser.setBounds(121, 11, 202, 39);
		contentPane.add(lblChooser);
		
		JButton btnEntrees = new JButton("Entrees");
		btnEntrees.setBackground(new Color(153, 0, 0));
		btnEntrees.setFont(new Font("Arial", Font.BOLD, 20));
		btnEntrees.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == btnEntrees) { 
					// show entrees
					;
				}
			}
		});
		btnEntrees.setBounds(35, 89, 163, 48);
		contentPane.add(btnEntrees);
		
		JButton btnSides = new JButton("Sides");
		btnSides.setBackground(new Color(153, 0, 0));
		btnSides.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == btnSides) { 
					// show sides
					;
				}
			}
		});
		btnSides.setFont(new Font("Arial", Font.BOLD, 20));
		btnSides.setBounds(208, 89, 163, 48);
		contentPane.add(btnSides);
		
		JButton btnDesserts = new JButton("Desserts");
		btnDesserts.setBackground(new Color(153, 0, 0));
		btnDesserts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == btnDesserts) {
					// show desserts
					;
				}
			}
		});
		btnDesserts.setFont(new Font("Arial", Font.BOLD, 20));
		btnDesserts.setBounds(35, 159, 163, 48);
		contentPane.add(btnDesserts);
		
		JButton btnBeverage = new JButton("Beverages");
		btnBeverage.setBackground(new Color(153, 0, 0));
		btnBeverage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == btnBeverage) {
					// show beverage
					;
				}
			}
		});
		btnBeverage.setFont(new Font("Arial", Font.BOLD, 20));
		btnBeverage.setBounds(211, 159, 163, 48);
		contentPane.add(btnBeverage);
	}
	
}
