package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JInternalFrame;
import java.awt.Button;

public class rewards extends JFrame {

	private JPanel contentPane;
	private JTextField earn_reward_text;
	private JTextField more_visits_text;
	private JTextField eligible_text;
	private JTextField visits_25_text;
	private JTextField visits_10_text;
	private JTextField visits_5_text;
	private JTextField free_entree_text;
	private JTextField free_shake_text;
	private JTextField free_med_text;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					rewards frame = new rewards();
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
	public rewards() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		earn_reward_text = new JTextField("Earn a reward with");
		earn_reward_text.setBounds(60, 19, 130, 26);
		contentPane.add(earn_reward_text);
		earn_reward_text.setColumns(10);
		
		more_visits_text = new JTextField("more visit(s).");
		more_visits_text.setColumns(10);
		more_visits_text.setBounds(243, 19, 95, 26);
		contentPane.add(more_visits_text);
		
		eligible_text = new JTextField("Eligible rewards:");
		eligible_text.setColumns(10);
		eligible_text.setBounds(60, 94, 116, 26);
		contentPane.add(eligible_text);
		
		visits_25_text = new JTextField("25 visits");
		visits_25_text.setColumns(10);
		visits_25_text.setBounds(70, 131, 67, 26);
		contentPane.add(visits_25_text);
		
		visits_10_text = new JTextField("10 visits");
		visits_10_text.setColumns(10);
		visits_10_text.setBounds(70, 169, 67, 26);
		contentPane.add(visits_10_text);
		
		visits_5_text = new JTextField("5 visits");
		visits_5_text.setColumns(10);
		visits_5_text.setBounds(70, 210, 67, 26);
		contentPane.add(visits_5_text);
		
		free_entree_text = new JTextField("Free entree");
		free_entree_text.setColumns(10);
		free_entree_text.setBounds(166, 131, 85, 26);
		contentPane.add(free_entree_text);
		
		free_shake_text = new JTextField("Free milkshake");
		free_shake_text.setColumns(10);
		free_shake_text.setBounds(166, 169, 110, 26);
		contentPane.add(free_shake_text);
		
		free_med_text = new JTextField("Free medium drink/side");
		free_med_text.setColumns(10);
		free_med_text.setBounds(166, 210, 162, 26);
		contentPane.add(free_med_text);
	}
}
