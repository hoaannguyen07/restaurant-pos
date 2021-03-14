package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Item_Delete_Option extends JFrame {

	private static final long serialVersionUID = 1L;
	
	//query variables
	DataHelper api_connection;
	String item_name;
	String item_id;
	
	private JPanel contentPane;
	private final JLabel label_text = new JLabel("Would you like to delete this item from your menu?");
	
	//JButton
	private final JButton button_yes = new JButton("Yes");
	private final JButton button_no = new JButton("No");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Item_Delete_Option frame = new Item_Delete_Option();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}//end try catch
			}//end run
		});//end invoke later
	}//end main

	/**
	 * Create the frame.
	 */
	public Item_Delete_Option() {
		initGUI();
	}//edn empty constructor
	
	public Item_Delete_Option(DataHelper api, String item_id) {
		this.api_connection = api;
		this.item_id = item_id;
		initGUI();
	}//end constructor
	
	private void initGUI() {
		//panel set-up
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 384, 130);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 153, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//label text
		label_text.setFont(new Font("Arial", Font.PLAIN, 15));
		label_text.setHorizontalAlignment(SwingConstants.CENTER);
		label_text.setBounds(10, 11, 352, 26);
		contentPane.add(label_text);
		
		//yes button
		button_yes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				api_connection.delete_menu_item(item_id);
				Cart c = new Cart(api_connection);
				c.setVisible(true);
				dispose();
			}//end mouse clicked
		});//end add mouse listener
		button_yes.setBounds(123, 61, 51, 23);
		contentPane.add(button_yes);
		
		//no button
		button_no.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Cart c = new Cart(api_connection);
				c.setVisible(true);
				dispose();
			}//end mouse clicked
		});//end add mouse listener
		button_no.setBounds(195, 61, 51, 23);
		contentPane.add(button_no);
	}//end init gui
}//end class