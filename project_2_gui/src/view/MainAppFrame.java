package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.JFormattedTextField;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;

import java.sql.*;
import javax.swing.AbstractAction;
import javax.swing.Action;

public class MainAppFrame extends JFrame {
	private final JPanel panel = new JPanel();
	private final JLabel lblWelcomeToChick = new JLabel("Welcome to Chick fil A Express");
	private final JPanel panel_1 = new JPanel();
	private final Action action = new SwingAction();
	private final JSplitPane splitPane = new JSplitPane();
	private final JLabel lblUsername = new JLabel("username: ");
	private final static JTextField textField = new JTextField();
	private final JPanel panel_2 = new JPanel();
	private final JSplitPane splitPane_1 = new JSplitPane();
	private final static JTextField textField_1 = new JTextField();
	private final JLabel lblPassword = new JLabel("password: ");
	private final JButton btnConfirm = new JButton("Confirm");
	private final JButton btnExit = new JButton("Exit");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainAppFrame frame = new MainAppFrame();
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
	public MainAppFrame() {
		textField_1.setColumns(10);
		textField.setColumns(10);
		initGUI();
	}
	private void initGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		panel.setBounds(67, 12, 308, 31);
		
		getContentPane().add(panel);
		
		panel.add(lblWelcomeToChick);
		panel_1.setBounds(77, 55, 299, 47);
		
		getContentPane().add(panel_1);
		
		panel_1.add(splitPane);
		
		splitPane.setLeftComponent(lblUsername);
		
		splitPane.setRightComponent(textField);
		panel_2.setBounds(77, 129, 299, 47);
		
		getContentPane().add(panel_2);
		
		panel_2.add(splitPane_1);
		
		splitPane_1.setRightComponent(textField_1);
		
		splitPane_1.setLeftComponent(lblPassword);
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String username = textField.getText(); 
				String password = textField_1.getText(); 
				
				if(e.getSource() == btnConfirm) { 
					if(username.isEmpty() && password.isEmpty()) { 
						System.out.println("failed");
					} else {
						test please_work = new test(username, password); 
						please_work.setVisible(true);
						dispose();
					}
				}
			}
		});
		btnConfirm.setBounds(173, 200, 117, 25);
		
		getContentPane().add(btnConfirm);
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(arg0.getSource() == btnExit) { 
					dbSetup my = new dbSetup();
				    //Building the connection
				     Connection conn = null;
				     try {
				        //Class.forName("org.postgresql.Driver");
				        conn = DriverManager.getConnection(
				          "jdbc:postgresql://csce-315-db.engr.tamu.edu/sthomas_demo",
				           my.user, my.pswd);
				     } catch (Exception e) {
				        e.printStackTrace();
				        System.err.println(e.getClass().getName()+": "+e.getMessage());
				        System.exit(0);
				     }//end try catch
				     System.out.println("Opened database successfully");
				     String cus_lname = "";
				     try{
				     //create a statement object
				       Statement stmt = conn.createStatement();
				       //create an SQL statement
				       String sqlStatement = "SELECT cus_lname FROM customer";
				       //send statement to DBMS
				       ResultSet result = stmt.executeQuery(sqlStatement);

				       //OUTPUT
				       System.out.println("Customer Last names from the Database.");
				       System.out.println("______________________________________");
				       while (result.next()) {
				         System.out.println(result.getString("cus_lname"));
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
				}
			}
		});
		btnExit.setBounds(12, 226, 117, 25);
		
		getContentPane().add(btnExit);
	}
	
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		
		public void actionPerformed(ActionEvent e) {
			
			
		}
	}
}
