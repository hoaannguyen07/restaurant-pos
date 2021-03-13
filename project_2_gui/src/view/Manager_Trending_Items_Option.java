package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Vector;
import java.awt.event.ActionEvent;

public class Manager_Trending_Items_Option extends JFrame {

	private JPanel contentPane;
	private JPanel contentEntree;
	DataHelper api_connection;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Manager_Trending_Items_Option frame = new Manager_Trending_Items_Option(new DataHelper());
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
	public Manager_Trending_Items_Option(DataHelper api) {
		api_connection = api;
		initGUI();
	}
	
	void initGUI() { 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblChooser = new JLabel("Choose an Option:");
		lblChooser.setFont(new Font("Arial", Font.BOLD, 20));
		lblChooser.setBounds(121, 11, 202, 39);
		contentPane.add(lblChooser);
		
		JButton btnEntrees = new JButton("Entrees");
		btnEntrees.setFont(new Font("Arial", Font.BOLD, 20));
		btnEntrees.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == btnEntrees) { 
					entreeGUI();
				}
			}
		});
		btnEntrees.setBounds(35, 89, 163, 48);
		contentPane.add(btnEntrees);
		
		JButton btnSides = new JButton("Sides");
		btnSides.setFont(new Font("Arial", Font.BOLD, 20));
		btnSides.setBounds(208, 89, 163, 48);
		contentPane.add(btnSides);
		
		JButton btnDesserts = new JButton("Desserts");
		btnDesserts.setFont(new Font("Arial", Font.BOLD, 20));
		btnDesserts.setBounds(35, 159, 163, 48);
		contentPane.add(btnDesserts);
		
		JButton btnBeverage = new JButton("Beverages");
		btnBeverage.setFont(new Font("Arial", Font.BOLD, 20));
		btnBeverage.setBounds(211, 159, 163, 48);
		contentPane.add(btnBeverage);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 434, 261);
		contentPane.add(panel);
	}
	
	void entreeGUI() { 
		Map<String, Integer> entreeMapping = new HashMap<String, Integer>();
		entreeMapping = api_connection.getOrderAmounts("S");
		Vector<Integer> num_values = new Vector<Integer>();
		Vector<String> name_entrees = new Vector<String>();
		for(Entry<String, Integer> me: entreeMapping.entrySet()) { 
			name_entrees.add(me.getKey());
			num_values.add(me.getValue());
		}
		
		System.out.println(entreeMapping);
		
		
	}
}
