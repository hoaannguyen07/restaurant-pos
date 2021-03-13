/*
 * Author: Adryn G
 */
package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.UIManager;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class EditItem extends JFrame {
	
	DataHelper api_connection;
	
	protected static String managerID, itemPrice, itemName;
	private JPanel contentPane;
	private final JLabel lblNewLabel = new JLabel("Manager ID: ");
	private final JLabel lblManagerID = new JLabel("");
	private final JLabel lblNewLabel_7 = new JLabel("Price");
	private final JButton btnSaveChanges = new JButton("Save Changes");
	private final JLabel lblNewLabel_1 = new JLabel("Item:");
	private final JLabel lblEntreeName = new JLabel("");
	private final JTextField txtPrice = new JTextField();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditItem frame = new EditItem();
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
	public EditItem() {
		
		api_connection = new DataHelper();
		managerID = "username";
		itemName = "Chicken Sandwich";
		
		initGUI();
	}
	
	public EditItem(DataHelper api, String _managerID, String _itemName) {
		
		api_connection = api;
		managerID = _managerID;
		itemName = _itemName;
		
		initGUI();
	}
	private void initGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 295);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 139, 139));
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		lblNewLabel.setBounds(156, 10, 74, 24);
		
		contentPane.add(lblNewLabel);
		lblManagerID.setBounds(240, 10, 74, 24);
		lblManagerID.setText(managerID);
		
		contentPane.add(lblManagerID);
		btnSaveChanges.setBounds(135, 191, 164, 40);
		
		btnSaveChanges.setBackground(new Color(0, 102, 255));
		
		String price = api_connection.getPrice(itemName);
		
		contentPane.add(btnSaveChanges);
		lblNewLabel_1.setBounds(41, 52, 43, 24);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
	    
		
		contentPane.add(lblNewLabel_1);
		lblNewLabel_7.setBounds(200, 109, 33, 17);
		contentPane.add(lblNewLabel_7);
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		txtPrice.setText("$" + String.valueOf(price));
		txtPrice.setBounds(174, 137, 86, 24);
		contentPane.add(txtPrice);
		txtPrice.setColumns(10);
		lblEntreeName.setHorizontalAlignment(SwingConstants.CENTER);
		lblEntreeName.setBounds(93, 52, 248, 24);
		contentPane.add(lblEntreeName);
		lblEntreeName.setFont(new Font("Arial", Font.BOLD, 18));
		lblEntreeName.setText(itemName);
		
		/* If button is pressed, changes are made to the database */
		btnSaveChanges.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(arg0.getSource() == btnSaveChanges) {
					/* Remove '$' from text if needed */
					api_connection.changePrice(itemName, txtPrice.getText());
					Manager_Menu menu = new Manager_Menu(api_connection, managerID);
					menu.setVisible(true);
					dispose();
				}
			}
		});
	}
}