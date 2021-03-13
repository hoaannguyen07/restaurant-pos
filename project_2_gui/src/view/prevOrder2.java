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
import javax.swing.JList;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextArea;

public class prevOrder2 extends JFrame {
	
	private static final long serialVersionUID = 1L;

	DataHelper api_connection;

	private JPanel contentPane;
	protected static String first;
	protected static String last;
	protected static String user;
	protected static String pass;
	protected static double price;
	private final JButton btnBack = new JButton("Back");
	private final JLabel lblNewLabel = new JLabel("YOUR LAST ORDERS:");
	JTextArea textPrevOrder = new JTextArea();
	private final JTextArea textPrevOrder2 = new JTextArea();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					prevOrder2 frame = new prevOrder2(new DataHelper());
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
	public prevOrder2(DataHelper api) {
		api_connection = api;
		initGUI();
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 460, 289);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 153, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		btnBack.setBackground(new Color(153, 0, 0));
		btnBack.setForeground(new Color(255, 255, 255));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(arg0.getSource() == btnBack) {
					customerOptionMenu view_cust_option = new customerOptionMenu(api_connection);
					view_cust_option.setVisible(true);
					dispose();
				}
			}
		});
		btnBack.setBounds(12, 11, 117, 25);
		
		Map<String, Vector<String>> PrevOrderMap = api_connection.prevOrder();
		Vector<String> orders = new Vector<String>();
		orders.addElement(PrevOrderMap.get("Entrees").elementAt(0));
		orders.addElement(PrevOrderMap.get("Sides").elementAt(0));
		orders.addElement(PrevOrderMap.get("Beverages").elementAt(0));
		orders.addElement(PrevOrderMap.get("Desserts").elementAt(0));
		System.out.println(orders);
		
		Vector<String> orders_2 = new Vector<String>();
		if(PrevOrderMap.get("Entrees").size() > 1) { 
			orders_2.addElement(PrevOrderMap.get("Entrees").elementAt(1));
			orders_2.addElement(PrevOrderMap.get("Sides").elementAt(1));
			orders_2.addElement(PrevOrderMap.get("Beverages").elementAt(1));
			orders_2.addElement(PrevOrderMap.get("Desserts").elementAt(1));
		}
		
		String temp = "";
		for(int i = 0; i < orders.size(); i++) { 
			if(orders.get(i) == null) { 
				;
			} else { 
				temp = temp + orders.get(i) + "\n";
			}
		}
		textPrevOrder.setForeground(new Color(255, 255, 255));
		textPrevOrder.setFont(new Font("Arial", Font.PLAIN, 15));
		textPrevOrder.setBackground(new Color(153, 0, 0));
		textPrevOrder.setText(temp);
		
		if(PrevOrderMap.size() > 2) { 
			temp = "";
			for(int i = 0; i < orders_2.size(); i++) {
				if(orders_2.get(i) == null) {
					;
				} else { 
					temp = temp + orders_2.get(i) + "\n";
				}
			}
		}
		
		textPrevOrder2.setForeground(new Color(255, 255, 255));
		textPrevOrder2.setFont(new Font("Arial", Font.PLAIN, 15));
		textPrevOrder2.setBackground(new Color(153, 0, 0));
		textPrevOrder2.setText(temp);
		
		
		contentPane.add(btnBack);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel.setBounds(85, 55, 231, 32);
		
		contentPane.add(lblNewLabel);
		
		textPrevOrder.setBounds(12, 120, 202, 119);
		contentPane.add(textPrevOrder);
		textPrevOrder2.setBounds(224, 120, 202, 119);
		
		contentPane.add(textPrevOrder2);
	}
}