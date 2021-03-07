package src.view;

import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;

public class prevOrder extends JFrame {

	private JPanel contentPane;
	private final JButton btnBack = new JButton("Back");
	protected static String first;
	protected static String last;
	protected static String user;
	protected static String pass;
	Vector vec = new Vector<String>();
	private JList list_entree;
	private JList list_sides;
	private JList list_beverages;
	private JList list_dessert;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					prevOrder frame = new prevOrder(first, last, user, pass);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param pass 
	 * @param user 
	 * @param last 
	 * @param first 
	 */
	

	public prevOrder(String first_name, String last_name, String username, String password) {
		first = first_name;
		last = last_name;
		user = username;
		pass = password;
		initGUI();
	}
	@SuppressWarnings("unchecked")
	private void initGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 609, 610);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		Connection conn = null;
		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection("jdbc:postgresql://csce-315-db.engr.tamu.edu/db907_group9_project2",
					"zali110", "227009838");
		} catch(Exception e) { 
			 e.printStackTrace();
		     System.err.println(e.getClass().getName()+": "+e.getMessage());
		     System.exit(0);
		}
		
		System.out.println("database opened correctly");
		String entree, side, beverage, dessert;
		Vector<String> entrees = new Vector<String>();
		Vector<String> sides = new Vector<String>();
		Vector<String> beverages = new Vector<String>();
		Vector<String> desserts = new Vector<String>();
		
		String entree, side, beverage, dessert;
		
		try { 
			Statement stmnt = conn.createStatement();
			String sqlStatement = 
<<<<<<< HEAD
					"SELECT m1.name AS entree, m2.name AS side, m3.name AS beverage, m4.name AS dessert " + 
					"FROM orders " + 
=======
					"SELECT orders.entrees, orders.sides, orders.beverages, orders.desserts FROM orders " + 
>>>>>>> 874c45d396bd0f32539a69e1e2e3a95a2cf48fb9
					"FULL OUTER JOIN customer ON orders.customerid = customer.id " + 
					"FULL OUTER JOIN menu m1 ON orders.entrees = m1.id " + 
					"FULL OUTER JOIN menu m2 ON orders.sides = m2.id " + 
					"FULL OUTER JOIN menu m3 ON orders.beverages = m3.id " + 
					"FULL OUTER JOIN menu m4 ON orders.desserts = m4.id " + 
					"WHERE customer.lastname LIKE 'SMITH%' " + 
					"AND customer.firstname LIKE 'MARY%' " + 
					"ORDER BY orders.date DESC " + 
					"LIMIT 5";
			ResultSet result = stmnt.executeQuery(sqlStatement);
			while (result.next()) {
<<<<<<< HEAD
				entree = result.getString("entree");
				entrees.add(entree);
				
				side = result.getString("side");
				sides.add(side);
				
				beverage = result.getString("beverage");
				beverages.add(beverage);
				
				dessert = result.getString("dessert");
				desserts.add(dessert);
			}
		}
			
=======
				entree = result.getString("entrees");
				entrees.add(entree);
				
				side = result.getString("sides");
				sides.add(side);
				
				beverage = result.getString("beverages");
				beverages.add(beverage);
				
				dessert = result.getString("desserts");
				desserts.add(dessert);
			}
		}
>>>>>>> 874c45d396bd0f32539a69e1e2e3a95a2cf48fb9
		 catch (Exception e) { 
			System.out.println("failed to access database");
		}
		try { 
			conn.close(); 
			System.out.println("Database closed");
		} catch (Exception e) { 
			System.out.println("Database not closed");
		}
		
		list_entree = new JList(entrees);
		list_sides = new JList(sides);
		list_beverages = new JList(beverages);
		list_dessert = new JList(desserts);
				
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(arg0.getSource() == btnBack) { 
					customerOptionMenu go_back = new customerOptionMenu(first, last, user, pass); 
					go_back.setVisible(true);
					dispose();
				}
			}
		});
		btnBack.setBounds(12, 12, 117, 25);
		contentPane.add(btnBack);
		list_entree.setBounds(89, 62, 130, 163);
		
		contentPane.add(list_entree);
		list_sides.setBounds(316, 62, 130, 163);
		
		contentPane.add(list_sides);
		list_beverages.setBounds(89, 265, 130, 163);
		
		contentPane.add(list_beverages);
		list_dessert.setBounds(316, 265, 130, 163);
		
		contentPane.add(list_dessert);
		contentPane.add(btnBack);
		
		
		
	
	}
}
