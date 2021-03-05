package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class test extends JFrame {

	protected static String username;
	protected static String password;
	private JPanel contentPane;
	private final JButton btnNewButton = new JButton("press me again");
	private final JLabel lblHello = new JLabel("Hello: ");
	private final JLabel label = new JLabel("");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					test2 frame = new test2();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param password 
	 * @param username 
	 */
	public test(String a, String b) {
		username = a;
		password = b;
		initGUI();
	}
	private void initGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		label.setText(username);
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(arg0.getSource() == btnNewButton) {
					test2 please_work = new test2();
					please_work.setVisible(true);
					dispose();
				}
						
			}
		});
		btnNewButton.setBounds(113, 160, 229, 37);
		
		contentPane.add(btnNewButton);
		lblHello.setBounds(37, 12, 70, 15);
		
		contentPane.add(lblHello);
		label.setBounds(115, 12, 70, 15);
		
		contentPane.add(label);
	}

}
