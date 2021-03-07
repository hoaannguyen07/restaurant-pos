package src.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import java.awt.Color;

public class createItem extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private final Action action = new SwingAction();
	private final Action action_1 = new SwingAction_1();
	
	DataHelper api_connection;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					createItem frame = new createItem();
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
	public createItem() 
	{
		initComponents();
	}
	
	public createItem(DataHelper api) 
	{
		this.api_connection = api;
		
		initComponents();
	}
	
	private void initComponents()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label_title = new JLabel("Add New Item to Menu");
		label_title.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_title.setHorizontalAlignment(SwingConstants.CENTER);
		label_title.setBounds(117, 11, 197, 33);
		contentPane.add(label_title);
		
		JLabel label_name = new JLabel("Name:");
		label_name.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_name.setBounds(92, 104, 49, 27);
		contentPane.add(label_name);
		
		textField = new JTextField();
		textField.setBounds(151, 109, 143, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(151, 73, 143, 20);
		contentPane.add(textField_1);
		
		JLabel label_id = new JLabel("ID:");
		label_id.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_id.setBounds(92, 68, 49, 27);
		contentPane.add(label_id);
		
		JLabel label_price = new JLabel("Price:");
		label_price.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_price.setBounds(92, 142, 49, 27);
		contentPane.add(label_price);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(151, 147, 143, 20);
		contentPane.add(textField_2);
		
		JButton button_submit = new JButton("Submit");
		button_submit.setBackground(new Color(255, 255, 255));
		button_submit.setAction(action);
		button_submit.setBounds(171, 193, 89, 23);
		contentPane.add(button_submit);
		
		JButton button_back = new JButton("Back");
		button_back.setAction(action_1);
		button_back.setBounds(23, 227, 89, 23);
		contentPane.add(button_back);
	}
	
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "Submit");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
	private class SwingAction_1 extends AbstractAction {
		public SwingAction_1() {
			putValue(NAME, "Back");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
}
