package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;

public class managerOptionMenu extends JFrame {
	
	DataHelper api_connection;

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					managerOptionMenu frame = new managerOptionMenu(new DataHelper());
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
	public managerOptionMenu(DataHelper api) {
		api_connection = api;
		initGUI();
	}
		
	void initGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 153, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnWeeklyRevenue = new JButton("Weekly Revenue");
		btnWeeklyRevenue.setForeground(Color.WHITE);
		btnWeeklyRevenue.setFont(new Font("Arial", Font.BOLD, 20));
		btnWeeklyRevenue.setBackground(new Color(204, 0, 0));
		btnWeeklyRevenue.setBounds(103, 156, 228, 54);
		btnWeeklyRevenue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(arg0.getSource() == btnWeeklyRevenue) {
					revenueChange revenue_menu = new revenueChange(api_connection);
					revenue_menu.setVisible(true);
					dispose();
				}
			}
		});
		contentPane.add(btnWeeklyRevenue);
		
		JButton btnEditMenu = new JButton("Edit Menu");
		btnEditMenu.setForeground(Color.WHITE);
		btnEditMenu.setBackground(new Color(204, 0, 0));
		btnEditMenu.setFont(new Font("Arial", Font.BOLD, 20));
		btnEditMenu.setBounds(103, 51, 228, 54);
		btnEditMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(arg0.getSource() == btnEditMenu) {
					Manager_Menu manager_menu = new Manager_Menu(api_connection);
					manager_menu.setVisible(true);
					dispose();
				}
			}
		});
		contentPane.add(btnEditMenu);
	}
}
