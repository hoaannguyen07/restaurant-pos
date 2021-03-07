package view;

import java.sql.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JList;

public class prevOrder2 extends JFrame {

	private JPanel contentPane;
	private final JLabel lblEntrees = new JLabel("Entrees");
	private final JLabel lblSides = new JLabel("Sides");
	private final JLabel lblBeverage = new JLabel("Beverage");
	private final JLabel lblDesserts = new JLabel("Desserts");
	private  JList entreeList;
	private  JList bevList;
	private  JList sideList;
	private  JList dessList;
	protected static String first;
	protected static String last;
	protected static String user;
	protected static String pass;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					prevOrder2 frame = new prevOrder2(first, last, user, pass);
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
	public prevOrder2(String first_name, String last_name, String username, String password) {
		first = first_name;
		last = last_name;
		user = username; 
		pass = password;
		initGUI();
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initGUI() {
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
		
		try { 
			Statement stmnt = conn.createStatement();

			String where = "WHERE customer.lastname LIKE '" + "SMITH" + "%' ";
			String and_str = "AND customer.firstname LIKE '" + "MARY"+ "%' ";
			String sqlStatement = 
					"SELECT m1.name AS entree, m2.name AS side, m3.name AS beverage, m4.name AS dessert " + 
					"FROM orders " + 
					"FULL OUTER JOIN customer ON orders.customerid = customer.id " + 
					"FULL OUTER JOIN menu m1 ON orders.entrees = m1.id " + 
					"FULL OUTER JOIN menu m2 ON orders.sides = m2.id " + 
					"FULL OUTER JOIN menu m3 ON orders.beverages = m3.id " + 
					"FULL OUTER JOIN menu m4 ON orders.desserts = m4.id " + 
					 where + and_str + 
					"ORDER BY orders.date DESC " + 
					"LIMIT 5";
			ResultSet result = stmnt.executeQuery(sqlStatement);
			while (result.next()) {
				entree = result.getString("entree");
				entrees.add(entree);
				
				side = result.getString("side");
				sides.add(side);
				
				beverage = result.getString("beverage");
				beverages.add(beverage);
				
				dessert = result.getString("dessert");
				desserts.add(dessert);
			}
		} catch (Exception e) { 
			System.out.println("failed to access database");
		}
		entreeList = new JList(entrees);
		bevList = new JList(beverages);
		sideList = new JList(sides);
		dessList = new JList(desserts);
		
		try { 
			conn.close(); 
			System.out.println("Database closed");
		} catch (Exception e) { 
			System.out.println("Database not closed");
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 451, 457);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		lblEntrees.setBounds(71, 12, 70, 15);
		
		contentPane.add(lblEntrees);
		lblSides.setBounds(283, 12, 70, 15);
		
		contentPane.add(lblSides);
		lblBeverage.setBounds(71, 199, 70, 15);
		
		contentPane.add(lblBeverage);
		lblDesserts.setBounds(283, 199, 70, 15);
		
		contentPane.add(lblDesserts);
		entreeList.setBounds(12, 29, 188, 139);
		
		contentPane.add(entreeList);
		bevList.setBounds(12, 226, 188, 139);
		
		contentPane.add(bevList);
		sideList.setBounds(233, 29, 175, 139);
		
		contentPane.add(sideList);
		dessList.setBounds(233, 224, 175, 141);
		
		contentPane.add(dessList);
	}
}
