package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;

public class Customer_Sign_in extends JFrame {
	
	DataHelper api_connection;

	private JPanel contentPane;
	private final JLabel lblUsername = new JLabel("Username:");
	private final JTextField textField = new JTextField();
	private final JLabel lblPassword = new JLabel("Password:");
	private final JTextField textField_1 = new JTextField();
	private final JLabel lblNewLabel = new JLabel("Customer Sign In");
	private final JButton btnConfirm = new JButton("Confirm");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Customer_Sign_in frame = new Customer_Sign_in(new DataHelper());
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
	public Customer_Sign_in(DataHelper api) {
		api_connection = api;
		
		textField_1.setText(" ");
		textField_1.setBounds(116, 122, 242, 38);
		textField_1.setColumns(10);
		textField.setBounds(116, 52, 242, 38);
		textField.setColumns(10);
		initGUI();
	}
	private void initGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		lblUsername.setBounds(12, 52, 153, 38);
		
		contentPane.add(lblUsername);
		
		contentPane.add(textField);
		lblPassword.setBounds(12, 133, 101, 15);
		
		contentPane.add(lblPassword);
		
		contentPane.add(textField_1);
		lblNewLabel.setBounds(135, 0, 277, 29);
		
		contentPane.add(lblNewLabel);
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(arg0.getSource() == btnConfirm) { 
					String user_id= textField.getText();
					String user_password = textField_1.getText();
					String first_name = null, last_name = null;
				}
			}
		});
		btnConfirm.setBounds(102, 178, 256, 57);
		
		contentPane.add(btnConfirm);
	}
}
