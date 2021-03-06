package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JPasswordField;

public class newCustomer extends JFrame {

	private JPanel contentPane;
	private final JLabel lblFirstName = new JLabel("First Name: ");
	private final JTextField firstname_text_field = new JTextField("Click here to enter your first name.");
	private final JLabel lblNewLabel = new JLabel("Last Name");
	private final JTextField lastname_text_field = new JTextField("Click here to enter your last name.");
	private final JLabel lblUsername = new JLabel("Username:");
	private final JLabel lblPassword = new JLabel("Password:");
	private final JTextField username_textfield = new JTextField("Click here to enter your username.");
	private final JButton btnConfirm = new JButton("Confirm");
	private final JPasswordField passwordField = new JPasswordField();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					newCustomer frame = new newCustomer();
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
	public newCustomer() {
		username_textfield.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				username_textfield.setText("");
			}
		});
		username_textfield.setBounds(157, 128, 238, 19);
		username_textfield.setColumns(10);
		lastname_text_field.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) { 
				lastname_text_field.setText("");
			}
		});
		lastname_text_field.setBounds(157, 77, 238, 19);
		lastname_text_field.setColumns(10);
		firstname_text_field.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) { 
				firstname_text_field.setText("");
			}
		});
		firstname_text_field.setBounds(157, 30, 238, 19);
		firstname_text_field.setColumns(10);
		initGUI();
	}
	private void initGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		lblFirstName.setBounds(50, 24, 100, 30);
		
		contentPane.add(lblFirstName);
		
		contentPane.add(firstname_text_field);
		lblNewLabel.setBounds(50, 79, 100, 15);
		
		contentPane.add(lblNewLabel);
		
		contentPane.add(lastname_text_field);
		lblUsername.setBounds(50, 128, 100, 19);
		
		contentPane.add(lblUsername);
		lblPassword.setBounds(50, 177, 100, 19);
		
		contentPane.add(lblPassword);
		
		contentPane.add(username_textfield);
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String first_name = firstname_text_field.getText();
				String last_name = lastname_text_field.getText();
				String username = username_textfield.getText();
				String password = String.valueOf(passwordField.getPassword());
				
				if(arg0.getSource() == btnConfirm) { 
					if(first_name.isEmpty() || last_name.isEmpty() || username.isEmpty() || password.isEmpty()) { 
						JOptionPane.showMessageDialog(null, "You missed some pane, please make sure every box was filled.");
					} else { 
						customerOptionMenu gen_options = new customerOptionMenu(first_name, last_name, username, password);
						gen_options.setVisible(true);
						dispose();
					}
				}
			}
		});
		btnConfirm.setBounds(168, 226, 117, 25);
		
		contentPane.add(btnConfirm);
		passwordField.setBounds(157, 177, 238, 19);
		
		contentPane.add(passwordField);
	}
}
