package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class test2 extends JFrame {

	private JPanel contentPane;
	private final JLabel lblTesting = new JLabel("testing");
	private final JButton btnPressThis = new JButton("press this ");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					test2 frame = new test2();
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
	public test2() {
		initGUI();
	}
	private void initGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		lblTesting.setBounds(115, 66, 182, 77);
		
		contentPane.add(lblTesting);
		btnPressThis.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(arg0.getSource() == btnPressThis) { 
					test3 please_work = new test3(); 
					please_work.setVisible(true);
					dispose();
				}
			}
		});
		btnPressThis.setBounds(236, 92, 117, 25);
		
		contentPane.add(btnPressThis);
	}

}
