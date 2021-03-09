package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Cust_Sign_In extends JFrame {
	DataHelper api;

	private JPanel contentPane;
	private final JLabel label_title = new JLabel("Customer Sign In");
	private final JPanel panel_username = new JPanel();
	private final JTextField tf_username = new JTextField();
	private final JPanel panel_password = new JPanel();
	private final JPasswordField pf_password = new JPasswordField();
	private final JPanel panel = new JPanel();
	private final JLabel label_log_in = new JLabel("LOG IN");
	private final JLabel label_log_in_msg = new JLabel("");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cust_Sign_In frame = new Cust_Sign_In(new DataHelper());
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
	public Cust_Sign_In() {
		
		initGUI();
	}
	
	public Cust_Sign_In(DataHelper api_connection) {
		
		this.api = api_connection;
		
		initGUI();
	}
	
	private void initGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 128, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		label_title.setForeground(new Color(255, 255, 255));
		label_title.setFont(new Font("Arial Black", Font.BOLD, 30));
		label_title.setHorizontalAlignment(SwingConstants.CENTER);
		label_title.setBounds(136, 11, 314, 72);
		
		contentPane.add(label_title);
		panel_username.setBorder(null);
		panel_username.setBackground(new Color(255, 255, 255));
		panel_username.setBounds(191, 136, 202, 25);
		
		contentPane.add(panel_username);
		panel_username.setLayout(null);
		
		
		tf_username.addFocusListener(new FocusAdapter() {
			@Override
			// put in functions to make username show and hide when focused or not so user can enter username
			// and know what that box is for
			public void focusGained(FocusEvent e) {
				if (tf_username.getText().equals("Username"))
				{
					tf_username.setText("");
				}
				else {
					tf_username.selectAll();
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if (tf_username.getText().equals(""))
				{
					tf_username.setText("Username");
				}
			}
		});
		tf_username.setBounds(10, 0, 182, 23);
		tf_username.setText("Username");
		tf_username.setBorder(null);
		tf_username.setColumns(10);
		panel_username.add(tf_username);
		
		panel_password.setBorder(null);
		panel_password.setLayout(null);
		panel_password.setBackground(new Color(255, 255, 255));
		panel_password.setBounds(191, 191, 202, 25);
		contentPane.add(panel_password);
		pf_password.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (pf_password.getText().equals("Password"))
				{
					pf_password.setEchoChar('●');
					pf_password.setText("");
				}
				else {
					pf_password.selectAll();
				}
				
			}
			@Override
			public void focusLost(FocusEvent e) {
				if (pf_password.getText().equals(""))
				{
					pf_password.setEchoChar((char)0);
					pf_password.setText("Password");
				}
			}
		});
		pf_password.setText("Password");
		pf_password.setEchoChar((char)0);
		pf_password.setBorder(null);
		pf_password.setBounds(10, 0, 182, 25);
		
		panel_password.add(pf_password);
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
//				if (tf_username.getText().equals("Username") || tf_username.getText().equals("")
//						|| pf_password.getText().equals("Password") || pf_password.getText().equals(""))
//				{
//					
//				}
//				else if (tf_username.getText().equals("admin") && pf_password.getText().equals("admin123"))
//				{
//					label_log_in_msg.setText("User: " + tf_username.getText() + " and Pass: " + pf_password.getText());
//				}
//				else
//				{
//					label_log_in_msg.setText("Invalid username or password");
//				}
				
				if (tf_username.getText().equals("Username") || tf_username.getText().equals("")
						|| pf_password.getText().equals("Password") || pf_password.getText().equals(""))
				{
					label_log_in_msg.setText("Please enter the required fields.");
				} 
				else 
				{
					int sign_in_status = api.verify_customer(tf_username, pf_password);
					
					if (sign_in_status == 1)
					{
						label_log_in_msg.setText("Invalid username or password");
					}
					else if (sign_in_status == 2)
					{
						label_log_in_msg.setText("User: " + tf_username.getText() + " and Pass: " + pf_password.getText());
					}
				}
			}
		});
		panel.setBackground(new Color(0, 51, 51));
		panel.setBounds(191, 263, 202, 54);
		
		contentPane.add(panel);
		panel.setLayout(null);
		label_log_in.setForeground(Color.WHITE);
		label_log_in.setFont(new Font("Arial Black", Font.BOLD, 20));
		label_log_in.setHorizontalAlignment(SwingConstants.CENTER);
		label_log_in.setBounds(62, 11, 85, 32);
		
		panel.add(label_log_in);
		label_log_in_msg.setForeground(new Color(139, 0, 0));
		label_log_in_msg.setHorizontalAlignment(SwingConstants.CENTER);
		label_log_in_msg.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_log_in_msg.setBounds(147, 233, 292, 19);
		
		contentPane.add(label_log_in_msg);
	}
}
