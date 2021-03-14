package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Manager_Option_Menu extends JFrame {
	
	private static final long serialVersionUID = 1L;

	DataHelper api_connection;
	
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Manager_Option_Menu frame = new Manager_Option_Menu(new DataHelper());
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}//end try catch
			}//end run
		});//end invoke later
	}//end main

	/**
	 * Create the frame.
	 */
	public Manager_Option_Menu(DataHelper api) {
		api_connection = api;
		api_connection.get_menu_data();
		initGUI();
	}//end manager option menu constructor
		
	void initGUI() {
		//panel
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 535);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 153, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//weekly revenue button
		JButton btnWeeklyRevenue = new JButton("Revenue Stats");
		btnWeeklyRevenue.setForeground(Color.WHITE);
		btnWeeklyRevenue.setFont(new Font("Arial", Font.BOLD, 20));
		btnWeeklyRevenue.setBackground(new Color(153, 0, 0));
		btnWeeklyRevenue.setBounds(103, 172, 228, 54);
		btnWeeklyRevenue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(arg0.getSource() == btnWeeklyRevenue) {
					Revenue_Change revenue_menu = new Revenue_Change(api_connection);
					revenue_menu.setVisible(true);
					dispose();
				}//end if
			}//end action performed
		});//end add action listener
		contentPane.add(btnWeeklyRevenue);
		
		//edit menu button
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
				}//end if
			}//end action performed
		});//end add action listener
		contentPane.add(btnEditMenu);
		
		//trending items button
		JButton trending_button = new JButton("Trending Items");
		trending_button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Manager_Trending_Items_Option mtio = new Manager_Trending_Items_Option(api_connection);
				mtio.setVisible(true);
				dispose();
			}
		});
		trending_button.setBackground(new Color(153, 0, 0));
		trending_button.setForeground(Color.WHITE);
		trending_button.setFont(new Font("Arial", Font.BOLD, 20));
		trending_button.setBounds(103, 251, 228, 54);
		contentPane.add(trending_button);
		
		//manager options label
		JLabel manager_options_label = new JLabel("MANAGER OPTIONS");
		manager_options_label.setFont(new Font("Arial Black", Font.BOLD, 20));
		manager_options_label.setHorizontalAlignment(SwingConstants.CENTER);
		manager_options_label.setBounds(95, 11, 247, 54);
		contentPane.add(manager_options_label);
		
		//recommendation button
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
					}//end if	
				}//end if
			}//end action performed
		});//end add action listener
		btnRecommendation.setBounds(103, 331, 228, 54);
		contentPane.add(btnRecommendation);
		
		JButton exit_button = new JButton("Exit");
		exit_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == exit_button) { 
					User_Type user_choose = new User_Type(api_connection);
					user_choose.setVisible(true);
					dispose();
				}//end if
			}//end action performed
		});//end add action listener
		exit_button.setBackground(new Color(153, 0, 0));
		exit_button.setForeground(new Color(255, 255, 255));
		exit_button.setFont(new Font("Arial", Font.BOLD, 20));
		exit_button.setBounds(103, 415, 228, 54);
		contentPane.add(exit_button);
	}//end init gui
}//end class