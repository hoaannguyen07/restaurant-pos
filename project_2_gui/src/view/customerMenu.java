package src.view;

//import java.awt.BorderLayout;
//import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
//import javax.swing.border.EmptyBorder;

import javax.swing.SwingUtilities;

public class customerMenu extends JFrame {
	public customerMenu() {
	}
	
	private static final long serialVersionUID = 1L;

//	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    customerMenu();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
	}
	
	/**
	 * Create the frame.
	 */
	public static void customerMenu() throws Exception {
        new ViewModel();
    }

}
