/*
 * Author: Adryn G
 */
package src.view;

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


/****************						FIX THE SQL STATEMENT TO MAKE IT WORK BECAUSE IT AIN'T						 *****************/

public class EditItem extends JFrame {
	protected static String managerID, itemPrice, itemName;
	private JPanel contentPane;
	private final JLabel lblNewLabel = new JLabel("Manager ID: ");
	private final JLabel lblManagerID = new JLabel("");
	private final JPanel panel_1 = new JPanel();
	private final JLabel lblNewLabel_2 = new JLabel("New label");
	private final JPanel panel_2 = new JPanel();
	private final JLabel lblNewLabel_3 = new JLabel("New label");
	private final JPanel panel_3 = new JPanel();
	private final JLabel lblNewLabel_4 = new JLabel("New label");
	private final JPanel panel_4 = new JPanel();
	private final JLabel lblNewLabel_5 = new JLabel("New label");
	private final JPanel panel_5 = new JPanel();
	private final JLabel lblNewLabel_6 = new JLabel("New label");
	private final JPanel panel_6 = new JPanel();
	private final JLabel lblNewLabel_7 = new JLabel("Price");
	private final JPanel panel_7 = new JPanel();
	private final JButton btnSaveChanges = new JButton("Save Changes");
	private final JTextField txtPrice = new JTextField();
	private final JLabel lblNewLabel_1 = new JLabel("Entree:");
	private final JLabel lblEntreeName = new JLabel("");
	private final JPanel panel = new JPanel();
	
	DataHelper api_connection;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditItem frame = new EditItem("1234", "Chicken Sandwich");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public final class dbSetup  {
		  public static final String user = "nikolai11";
		  public static final String pswd = "Ang_022800";
	}//end class

	/**
	 * Create the frame.
	 */
	public EditItem(DataHelper api,String _managerID, String _itemName) {
		this.api_connection = api;
		txtPrice.setText("$");
		txtPrice.setColumns(10);
		
		managerID = _managerID;
		itemName = _itemName;
		
		initGUI();
	}
	
	public EditItem(String _managerID, String _itemName) {
		txtPrice.setText("$");
		txtPrice.setColumns(10);
		
		managerID = _managerID;
		itemName = _itemName;
		
		initGUI();
	}
	private void initGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		lblNewLabel.setBounds(156, 10, 74, 24);
		
		contentPane.add(lblNewLabel);
		lblManagerID.setBounds(240, 10, 74, 24);
		lblManagerID.setText(managerID);
		
		contentPane.add(lblManagerID);
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBounds(10, 88, 416, 24);
		
		contentPane.add(panel_1);
		
		panel_1.add(lblNewLabel_2);
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2.setBounds(10, 112, 416, 24);
		
		contentPane.add(panel_2);
		
		panel_2.add(lblNewLabel_3);
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_3.setBounds(10, 136, 416, 24);
		
		contentPane.add(panel_3);
		
		panel_3.add(lblNewLabel_4);
		panel_4.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_4.setBounds(10, 160, 416, 24);
		
		contentPane.add(panel_4);
		
		panel_4.add(lblNewLabel_5);
		panel_5.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_5.setBounds(10, 184, 416, 24);
		
		contentPane.add(panel_5);
		
		panel_5.add(lblNewLabel_6);
		panel_6.setBounds(10, 234, 416, 24);
		
		contentPane.add(panel_6);
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		panel_6.add(lblNewLabel_7);
		panel_7.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_7.setBounds(10, 258, 416, 31);
		
		contentPane.add(panel_7);
		
		panel_7.add(txtPrice);
		
		btnSaveChanges.setBackground(new Color(0, 102, 255));
		btnSaveChanges.setBounds(133, 323, 164, 40);
		
		
		dbSetup my = new dbSetup();
	    //Building the connection
	    Connection conn = null;
	    try {
	    	//Class.forName("org.postgresql.Driver");
	        conn = DriverManager.getConnection(
	          "jdbc:postgresql://csce-315-db.engr.tamu.edu/db907_group9_project2",
	           my.user, my.pswd);
	    } catch (Exception e) {
	    	e.printStackTrace();
	        System.err.println(e.getClass().getName()+": "+e.getMessage());
	        System.exit(0);
	    }//end try catch
	    System.out.println("Opened database successfully");
	    
	    double price = 0;
	    try {
	    	//create a statement object
	    	Statement stmt = conn.createStatement();
	        //create an SQL statement
	        String sqlStatement = "SELECT menu.price FROM public.menu WHERE menu.name='" + itemName + "'";
	        //send statement to DBMS
	        ResultSet result = stmt.executeQuery(sqlStatement);
	        while (result.next()) {
	        	price = result.getDouble("price");
	        }
	    } catch (Exception e){
	    	System.out.println("Error accessing Database.");
	    }
	    
	    //closing the connection
	    try {
	      conn.close();
	      System.out.println("Connection Closed.");
	    } catch(Exception e) {
	      System.out.println("Connection NOT Closed.");
	    }//end try catch
		
		/* If button is pressed, changes are made to the database */
		btnSaveChanges.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(arg0.getSource() == btnSaveChanges) {
					/* Remove '$' from text if needed */
					String priceChangeTxt = txtPrice.getText();
					if (priceChangeTxt.charAt(0) == '$') {
						priceChangeTxt = priceChangeTxt.substring(1);
					}
					
					double priceChange = Double.valueOf(priceChangeTxt);
					
					dbSetup my = new dbSetup();
				    //Building the connection
				    Connection conn = null;
				    try {
				    	//Class.forName("org.postgresql.Driver");
				        conn = DriverManager.getConnection(
				          "jdbc:postgresql://csce-315-db.engr.tamu.edu/db907_group9_project2",
				           my.user, my.pswd);
				    } catch (Exception e) {
				    	e.printStackTrace();
				        System.err.println(e.getClass().getName()+": "+e.getMessage());
				        System.exit(0);
				    }//end try catch
				    System.out.println("Opened database successfully");
					
					try {
						Statement stmt = conn.createStatement(); // statement object
						// create the actual statement to populate the statement object
						String sql_stmt = "UPDATE menu SET price=" + priceChange + " WHERE name='" + itemName + "'";
						
						System.out.println("Executing Statement: " + sql_stmt);
						
						stmt.executeQuery(sql_stmt);
						System.out.println("Successfully update price of " + itemName + " to " + priceChange);
						System.out.println("Updated item " + itemName + " in database to " + priceChange);
					} catch (Exception e) {
						System.out.println("Error updating database");
					}
				    
				    //closing the connection
				    try {
				      conn.close();
				      System.out.println("Connection Closed.");
				    } catch(Exception e) {
				      System.out.println("Connection NOT Closed.");
				    }//end try catch
					
					Manager_Menu please_work = new Manager_Menu();
					please_work.setVisible(true);
					dispose();
				}
			}
		});
		
		contentPane.add(btnSaveChanges);
	    
	    txtPrice.setText("$" + String.valueOf(price));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNewLabel_1.setBounds(10, 52, 86, 24);
	    
		
		contentPane.add(lblNewLabel_1);
		panel.setBounds(93, 52, 245, 34);
		
		contentPane.add(panel);
		panel.add(lblEntreeName);
		lblEntreeName.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblEntreeName.setText(itemName);
	}
}