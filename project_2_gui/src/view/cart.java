package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class cart extends JFrame {
	
	DataHelper api_connection;
	
	private JPanel contentPane;
	private final JButton btnPayment = new JButton("Payment");
	private final JButton btnBack = new JButton("Back");
	private final JLabel lblYourCart = new JLabel("Your Cart");
	private final JList list = new JList();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					cart frame = new cart();
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
	public cart() {
		initGUI();
	}
	
	public cart(DataHelper api) {
		this.api_connection = api;
		initGUI();
	}
	
	private void initGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 453, 501);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		btnPayment.setBounds(324, 0, 117, 25);
		
		contentPane.add(btnPayment);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(arg0.getSource() == btnBack) { 
//					prevOrder2 go_back = new PrevOrder2 (first, last, user, pass);
				}
			}
		});
		btnBack.setBounds(12, 0, 117, 25);
		
		contentPane.add(btnBack);
		lblYourCart.setBounds(182, 31, 82, 33);
		
		contentPane.add(lblYourCart);
		list.setBounds(48, 72, 356, 352);
		
		contentPane.add(list);
	}
}
