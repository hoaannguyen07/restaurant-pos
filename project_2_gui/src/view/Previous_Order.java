package view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Map;
import java.util.Vector;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextArea;

public class Previous_Order extends JFrame {

	private static final long serialVersionUID = 1L;

	//query variables
	DataHelper api_connection;
	protected static String first;
	protected static String last;
	protected static String user;
	protected static String pass;
	protected static double price;

	//Swing
	private JPanel contentPane;
	private final JButton btnBack = new JButton("Back");
	private final JLabel your_last_orders_label = new JLabel("YOUR LAST ORDERS:");
	JTextArea textPrevOrder = new JTextArea();
	private final JTextArea textPrevOrder2 = new JTextArea();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Previous_Order frame = new Previous_Order(new DataHelper());
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
	public Previous_Order(DataHelper api) {
		api_connection = api;
		initGUI();
	}//end constructor

	private void initGUI() {
		//panel
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 460, 289);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 153, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//button back
		btnBack.setFont(new Font("Arial", Font.BOLD, 15));
		btnBack.setBackground(new Color(153, 0, 0));
		btnBack.setForeground(new Color(255, 255, 255));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (arg0.getSource() == btnBack) {
					Customer_Option_Menu view_cust_option = new Customer_Option_Menu(api_connection);
					view_cust_option.setVisible(true);
					dispose();
				}//end if
			}//end action performed
		});//end add action listener
		btnBack.setBounds(12, 11, 91, 25);
		contentPane.add(btnBack);

		//previous order map
		Map<String, Vector<String>> PrevOrderMap = api_connection.prevOrder();
		Vector<String> orders = new Vector<String>();
		orders.addElement(PrevOrderMap.get("Entrees").elementAt(0));
		orders.addElement(PrevOrderMap.get("Sides").elementAt(0));
		orders.addElement(PrevOrderMap.get("Beverages").elementAt(0));
		orders.addElement(PrevOrderMap.get("Desserts").elementAt(0));
		System.out.println(orders);

		//second order, if applicable
		Vector<String> orders_2 = new Vector<String>();
		if (PrevOrderMap.get("Entrees").size() > 1) {
			orders_2.addElement(PrevOrderMap.get("Entrees").elementAt(1));
			orders_2.addElement(PrevOrderMap.get("Sides").elementAt(1));
			orders_2.addElement(PrevOrderMap.get("Beverages").elementAt(1));
			orders_2.addElement(PrevOrderMap.get("Desserts").elementAt(1));
		}//end if

		String temp = "";
		for (int i = 0; i < orders.size(); i++) {
			if (orders.get(i) == null) {
				;
			} else {
				temp = temp + orders.get(i) + "\n";
			}//end if/else
		}//end for
		textPrevOrder.setForeground(new Color(255, 255, 255));
		textPrevOrder.setFont(new Font("Arial", Font.BOLD, 15));
		textPrevOrder.setBackground(new Color(153, 0, 0));
		textPrevOrder.setText(temp);

		if (PrevOrderMap.size() > 2) {
			temp = "";
			for (int i = 0; i < orders_2.size(); i++) {
				if (orders_2.get(i) == null) {
					;
				} else {
					temp = temp + orders_2.get(i) + "\n";
				}//end if else/
			}//end for
		}//end if

		textPrevOrder2.setForeground(new Color(255, 255, 255));
		textPrevOrder2.setFont(new Font("Arial", Font.BOLD, 15));
		textPrevOrder2.setBackground(new Color(153, 0, 0));
		textPrevOrder2.setText(temp);

		your_last_orders_label.setFont(new Font("Arial", Font.BOLD, 20));
		your_last_orders_label.setBounds(112, 65, 231, 32);
		contentPane.add(your_last_orders_label);

		textPrevOrder.setBounds(12, 120, 202, 119);
		contentPane.add(textPrevOrder);
		textPrevOrder2.setBounds(224, 120, 202, 119);
		contentPane.add(textPrevOrder2);
	}//end init gui
}//end class