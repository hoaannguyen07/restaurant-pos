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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

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
		
		api_connection.get_menu_data();
		
		initGUI();
	}
		
	void initGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 535);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 153, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnWeeklyRevenue = new JButton("Revenue Stats");
		btnWeeklyRevenue.setForeground(Color.WHITE);
		btnWeeklyRevenue.setFont(new Font("Arial", Font.BOLD, 20));
		btnWeeklyRevenue.setBackground(new Color(153, 0, 0));
		btnWeeklyRevenue.setBounds(103, 172, 228, 54);
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
		btnEditMenu.setBackground(new Color(153, 0, 0));
		btnEditMenu.setFont(new Font("Arial", Font.BOLD, 20));
		btnEditMenu.setBounds(103, 93, 228, 54);
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
		
		JButton btnNewButton = new JButton("Trending Items");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Manager_Trending_Items_Option mtio = new Manager_Trending_Items_Option(api_connection);
				mtio.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBackground(new Color(153, 0, 0));
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setFont(new Font("Arial", Font.BOLD, 20));
		btnNewButton.setBounds(103, 251, 228, 54);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("MANAGER OPTIONS");
		lblNewLabel.setFont(new Font("Arial Black", Font.BOLD, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(95, 11, 247, 54);
		contentPane.add(lblNewLabel);
		
		JButton btnRecommendation = new JButton("Recommendations");
		btnRecommendation.setForeground(new Color(255, 255, 255));
		btnRecommendation.setBackground(new Color(153, 0, 0));
		btnRecommendation.setFont(new Font("Arial", Font.BOLD, 20));
		btnRecommendation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == btnRecommendation) { 
					if(e.getSource() == btnRecommendation) { 
						Manager_Recommendation man_rec = new Manager_Recommendation(api_connection);
						man_rec.setVisible(true);
						dispose();
					}
					
				}
			}
		});
		btnRecommendation.setBounds(103, 331, 228, 54);
		contentPane.add(btnRecommendation);
		
		JButton btnNewButton_2 = new JButton("Exit");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == btnNewButton_2) { 
					User_Type user_choose = new User_Type(api_connection);
					user_choose.setVisible(true);
					dispose();
				}
			}
		});
		btnNewButton_2.setBackground(new Color(153, 0, 0));
		btnNewButton_2.setForeground(new Color(255, 255, 255));
		btnNewButton_2.setFont(new Font("Arial", Font.BOLD, 20));
		btnNewButton_2.setBounds(103, 415, 228, 54);
		contentPane.add(btnNewButton_2);
	}
}

