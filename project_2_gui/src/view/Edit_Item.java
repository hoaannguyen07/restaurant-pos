/*
 /*
 /*
  * Author: Adryn G
 */
package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JCheckBox;

public class Edit_Item extends JFrame {

	private static final long serialVersionUID = 1L;

	//query variables
	DataHelper api_connection;
	protected static String managerID, itemPrice, itemName;
	
	//JLabels
	private final JLabel manager_id_label = new JLabel("Manager ID: ");
	private final JLabel lblManagerID = new JLabel("");
	private final JLabel price_label = new JLabel("Price");
	private final JLabel item_label = new JLabel("Item:");
	private final JLabel lblEntreeName = new JLabel("");
	
	//misc. Swing
	private JPanel contentPane;
	private JCheckBox chckbxNewCheckBox;
	private final JButton btnSaveChanges = new JButton("Save Changes");
	private final JTextField txtPrice = new JTextField();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Edit_Item frame = new Edit_Item();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}//end try-catch
			}//end run
		});//end invoke later
	}//end main

	/**
	 * Create the frame.
	 */
	public Edit_Item() {
		api_connection = new DataHelper();
		managerID = "username";
		itemName = "Chicken Sandwich";

		initGUI();
	}//end empty constructor

	public Edit_Item(DataHelper api, String _itemName) {
		api_connection = api;
		managerID = api_connection.id;
		itemName = _itemName;

		initGUI();
	}//end constructor
	
	private void initGUI() {
		//set up panels
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 295);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 153, 204));
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		//manager id label
		manager_id_label.setBounds(124, 10, 74, 24);
		contentPane.add(manager_id_label);

		//main manager id label
		lblManagerID.setBounds(197, 10, 144, 24);
		lblManagerID.setText(managerID);
		contentPane.add(lblManagerID);
		
		//save changes button
		btnSaveChanges.setForeground(new Color(255, 255, 255));
		btnSaveChanges.setBounds(137, 191, 164, 40);
		btnSaveChanges.setBackground(new Color(0, 51, 51));
		contentPane.add(btnSaveChanges);
		
		//item label
		item_label.setBounds(41, 52, 43, 24);
		item_label.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(item_label);

		//price label
		price_label.setHorizontalAlignment(SwingConstants.CENTER);
		price_label.setBounds(197, 87, 43, 17);
		contentPane.add(price_label);
		price_label.setFont(new Font("Tahoma", Font.BOLD, 14));

		String price = api_connection.getPrice(itemName);

		//price text
		txtPrice.setText("$ " + String.valueOf(price));
		txtPrice.setBounds(179, 115, 79, 24);
		contentPane.add(txtPrice);
		txtPrice.setColumns(10);
		
		//entree name label
		lblEntreeName.setHorizontalAlignment(SwingConstants.CENTER);
		lblEntreeName.setBounds(95, 52, 248, 24);
		contentPane.add(lblEntreeName);
		lblEntreeName.setFont(new Font("Arial", Font.BOLD, 18));
		lblEntreeName.setText(itemName);

		Boolean available = api_connection.getAvailability(itemName);

		//available check box
		chckbxNewCheckBox = new JCheckBox("Available");
		chckbxNewCheckBox.setSelected(available);
		chckbxNewCheckBox.setBounds(182, 146, 74, 23);
		contentPane.add(chckbxNewCheckBox);

		/* If button is pressed, changes are made to the database */
		btnSaveChanges.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (arg0.getSource() == btnSaveChanges) {
					api_connection.changePrice(itemName, txtPrice.getText());
					api_connection.changeAvailability(itemName,
							chckbxNewCheckBox.isSelected());
					Manager_Menu menu = new Manager_Menu(api_connection);
					menu.setVisible(true);
					dispose();
				}//end if
			}//end action performed
		});//end add action listener
	}//end initGUI
}//end class
