package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JPasswordField;

import javax.swing.AbstractAction;
import javax.swing.Action;
import java.awt.Color;
import java.awt.Font;

public class newCustomer extends JFrame {
	
	private static final long serialVersionUID = 1L;

	DataHelper api_connection;

	private JPanel contentPane;
	private final JLabel lblFirstName = new JLabel("First Name: ");
	private final JTextField firstname_text_field = new JTextField("Click here to enter your first name.");
	private final JLabel lblNewLabel = new JLabel("Last Name:");
	private final JTextField lastname_text_field = new JTextField("Click here to enter your last name.");
	private final JLabel lblUsername = new JLabel("Username:");
	private final JLabel lblPassword = new JLabel("Password:");
	private final JTextField username_textfield = new JTextField("Click here to enter your username.");
	private final JButton btnConfirm = new JButton("Confirm");
	private final JPasswordField passwordField = new JPasswordField();
	
	private final Action action = new SwingAction();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					newCustomer frame = new newCustomer(new DataHelper());
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
	public newCustomer(DataHelper api) {
		api_connection = api;
		username_textfield.setFont(new Font("Arial", Font.BOLD, 12));
		username_textfield.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				username_textfield.setText("");
			}
		});
		username_textfield.setBounds(157, 128, 238, 38);
		username_textfield.setColumns(10);
		lastname_text_field.setFont(new Font("Arial", Font.BOLD, 12));
		lastname_text_field.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) { 
				lastname_text_field.setText("");
			}
		});
		lastname_text_field.setBounds(157, 77, 238, 40);
		lastname_text_field.setColumns(10);
		firstname_text_field.setFont(new Font("Arial", Font.BOLD, 12));
		firstname_text_field.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) { 
				firstname_text_field.setText("");
			}
		});
		firstname_text_field.setBounds(157, 30, 238, 36);
		firstname_text_field.setColumns(10);
		initGUI();
	}
	
	public class SwingAction extends AbstractAction {
        private static final long serialVersionUID = 1L;
        public SwingAction() {
            putValue(NAME, "Back");
            putValue(SHORT_DESCRIPTION, "Heading back to customer type menu.");
        }
        public void actionPerformed(ActionEvent e) {
        }
    }
	
	private void initGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 482, 330);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 153, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		lblFirstName.setFont(new Font("Arial", Font.BOLD, 20));
		lblFirstName.setBounds(21, 30, 117, 30);
		
		contentPane.add(lblFirstName);
		
		contentPane.add(firstname_text_field);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel.setBounds(21, 87, 117, 15);
		
		contentPane.add(lblNewLabel);
		
		contentPane.add(lastname_text_field);
		lblUsername.setFont(new Font("Arial", Font.BOLD, 20));
		lblUsername.setBounds(21, 135, 117, 19);
		
		contentPane.add(lblUsername);
		lblPassword.setFont(new Font("Arial", Font.BOLD, 20));
		lblPassword.setBounds(21, 186, 117, 19);
		
		contentPane.add(lblPassword);
		
		contentPane.add(username_textfield);
		
		JButton btnBack = new JButton("Back:");
		btnBack.setForeground(new Color(255, 255, 255));
		btnBack.setBackground(new Color(153, 0, 0));
		btnBack.setFont(new Font("Arial", Font.BOLD, 20));
        btnBack.setAction(action);
        btnBack.setBounds(118, 237, 86, 26);
        contentPane.add(btnBack);
        
        btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(arg0.getSource() == btnBack) {
					Customer_Type view_cust_option = new Customer_Type(api_connection);
					view_cust_option.setVisible(true);
					dispose();
				}
			}
		});
		btnConfirm.setForeground(new Color(255, 255, 255));
		btnConfirm.setBackground(new Color(153, 0, 0));
		btnConfirm.setFont(new Font("Arial", Font.BOLD, 20));
        
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String first_name = firstname_text_field.getText();
				String last_name = lastname_text_field.getText();
				String username = username_textfield.getText();
				
				//System.out.println(first_name.contentEquals("MARY"));
				String password = String.valueOf(passwordField.getPassword());
				System.out.println(password);
				
				
				if(arg0.getSource() == btnConfirm) { 
					if(first_name.isEmpty() || last_name.isEmpty() || username.isEmpty() || password.isEmpty()) { 
						JOptionPane.showMessageDialog(null, "You missed some pane, please make sure every box was filled.");
					} else { 
						if (api_connection.addNewCustomer(first_name, last_name, username, password)) {
							customerOptionMenu gen_options = new customerOptionMenu(api_connection);
							gen_options.setVisible(true);
							dispose();
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Username already exists. Try again.");
						}
					}
				}
			}
		});
		btnConfirm.setBounds(228, 238, 117, 25);
		
		contentPane.add(btnConfirm);
		passwordField.setBounds(157, 177, 238, 37);
		
		contentPane.add(passwordField);
	}
}
