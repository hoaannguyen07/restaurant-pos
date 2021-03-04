package jdbc;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.sql.*;

public class jdbc extends javax.swing.JFrame {

    private static final long serialVersionUID = 1L; //removes warning
    private JPanel contentPane;
    private final JPanel panel = new JPanel();
    private final JLabel label = new JLabel("Welcome to Chick-fil-a Express!");
    private final JButton button = new JButton("Log in");

    //Launch the application.
    public static void main(String[] args) {
        Connection conn = null;
        try {
            //Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(
                "jdbc:postgresql://csce-315-db.engr.tamu.edu/db907_group9_project2",
                dbSetup.user, dbSetup.pswd);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }//end try catch

            System.out.println("Opened database successfully");

            EventQueue.invokeLater(new Runnable() {
                public void run() {
                    try {
                        jdbc frame = new jdbc();
                        frame.start();
                        Statement stmt = conn.createStatement(); //create a statement object
                        String sqlStatement = "SELECT lastname FROM customer"; //create an SQL statement
                        ResultSet result = stmt.executeQuery(sqlStatement); //send statement to DBMS

                        //OUTPUT
                        System.out.println("Customer Last names from the Database.");
                        System.out.println("______________________________________");
                        while (result.next()) {
                          System.out.println(result.getString("lastname"));
                        } //end while
                    } catch (Exception e) {
                        System.out.println("Error accessing Database.");
                        e.printStackTrace();
                    } //end try-catch
                } //end run()
            }); //end EventQueue

        //closing the connection
        try {
            conn.close();
            System.out.println("Connection Closed.");
        } catch(Exception e) {
            System.out.println("Connection NOT Closed.");
        }//end try catch

    }

    //Create the frame.
    public jdbc() {
        initGUI();
    }//end jdbc

    public void start() {
        setVisible(true);
    }//end start

    private void initGUI() {
        setDefaultCloseOperation(jdbc.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);
        panel.setBackground(Color.RED);

        contentPane.add(panel, BorderLayout.NORTH);

        panel.add(label);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                label.setText("Successfully logged in!");    
            }
        });//end addActionListener

        contentPane.add(button, BorderLayout.WEST);
    }//end initGUI

}//end class
