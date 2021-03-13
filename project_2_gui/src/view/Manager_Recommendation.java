package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Manager_Recommendation extends JFrame {
	
	DataHelper api_connection; 
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Manager_Recommendation frame = new Manager_Recommendation(new DataHelper());
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
	public Manager_Recommendation(DataHelper api) {
		api_connection = api;
		initGUI();
	}
	
	void initGUI() { 
		setBackground(new Color(102, 153, 204));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 379, 511);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 153, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("These items aren't doing so well...");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 18));
		lblNewLabel.setBounds(28, 38, 351, 55);
		contentPane.add(lblNewLabel);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == btnBack) { 
					managerOptionMenu man_menu = new managerOptionMenu(api_connection);
					man_menu.setVisible(true);
					dispose();
				}
			}
		});
		btnBack.setBackground(new Color(153, 0, 0));
		btnBack.setForeground(new Color(255, 255, 255));
		btnBack.setBounds(10, 11, 89, 23);
		contentPane.add(btnBack);
		
		JLabel lblNewLabel_1 = new JLabel("Have you considered changing the price? ");
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_1.setBounds(28, 85, 299, 38);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("These items are quite popular...");
		lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 18));
		lblNewLabel_2.setBounds(28, 240, 299, 38);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Are you able to keep them in stock?");
		lblNewLabel_3.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel_3.setBounds(28, 289, 299, 38);
		contentPane.add(lblNewLabel_3);
		
		JTextArea textBottomItems = new JTextArea();
		textBottomItems.setFont(new Font("Arial", Font.BOLD, 15));
		textBottomItems.setForeground(new Color(255, 255, 255));
		textBottomItems.setBackground(new Color(153, 0, 0));
		textBottomItems.setBounds(65, 134, 216, 99);
		contentPane.add(textBottomItems);
		
		JTextArea textTopItems = new JTextArea();
		textTopItems.setForeground(new Color(255, 255, 255));
		textTopItems.setFont(new Font("Arial", Font.BOLD, 15));
		textTopItems.setBackground(new Color(153, 0, 0));
		textTopItems.setBounds(65, 338, 216, 102);
		contentPane.add(textTopItems);
		
		Vector<Vector<String>> entrees = api_connection.trending_options_for_rec("E");
		Vector<Vector<String>> sides = api_connection.trending_options_for_rec("S");
		Vector<Vector<String>> beverages = api_connection.trending_options_for_rec("B");
		Vector<Vector<String>> desserts = api_connection.trending_options_for_rec("D");
		
		
		Vector<String> top_items = new Vector<String>();
		top_items.add(entrees.get(0).get(0));
		top_items.add(sides.get(0).get(0));
		top_items.add(beverages.get(0).get(0));
		top_items.add(desserts.get(0).get(0));
		System.out.println(top_items);
		
		String temp = "";
		for(int i = 0; i < top_items.size(); i++) { 
			temp = temp + top_items.get(i) + "\n";
		}
		textBottomItems.setText(temp);
		
		top_items.clear();
		top_items.add(entrees.lastElement().get(0));
		top_items.add(sides.lastElement().get(0));
		top_items.add(beverages.lastElement().get(0));
		top_items.add(desserts.lastElement().get(0));
		System.out.println(top_items);
		
		temp = "";
		for(int i = 0; i < top_items.size(); i++) { 
			temp = temp + top_items.get(i) + "\n";
		}
		textTopItems.setText(temp);
		
		
	}
	
	
}
