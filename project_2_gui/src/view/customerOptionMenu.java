package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class customerOptionMenu extends JFrame {

	private JPanel contentPane;
	private final JButton btnViewMenu = new JButton("View Menu");
	private final JButton btnViewLastMeal = new JButton("View Last Meal");
	private final JButton btnPaymentInformation = new JButton("Payment Information");
	private final JButton btnRewards = new JButton("Reward Tier");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					customerOptionMenu frame = new customerOptionMenu();
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
	public customerOptionMenu() {
		initGUI();
	}
	private void initGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		btnViewMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(arg0.getSource() == btnViewMenu) {
					System.out.println("here");
				}
			}
		});
		btnViewMenu.setBounds(88, 12, 250, 48);
		
		contentPane.add(btnViewMenu);
		btnViewLastMeal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(arg0.getSource() == btnViewLastMeal) { 
					System.out.println("here");
				}
			}
		});
		btnViewLastMeal.setBounds(88, 72, 250, 48);
		
		contentPane.add(btnViewLastMeal);
		btnPaymentInformation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(arg0.getSource() == btnPaymentInformation) { 
					System.out.println("here");
				}
			}
		});
		btnPaymentInformation.setBounds(88, 132, 250, 48);
		
		contentPane.add(btnPaymentInformation);
		btnRewards.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(arg0.getSource() == btnRewards) { 
					System.out.println("here");
				}
			}
		});
		btnRewards.setBounds(88, 194, 250, 48);
		
		contentPane.add(btnRewards);
	}

}
