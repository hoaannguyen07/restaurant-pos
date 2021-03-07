package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import java.util.Vector;

import javax.swing.Action;
import javax.swing.ListSelectionModel;

public class managerMenu extends JFrame {

	private JPanel contentPane;
	private JList item_list;
	private final Action action = new SwingAction();
	private final Action action_1 = new SwingAction_1();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					managerMenu frame = new managerMenu();
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
	public managerMenu() {
		initGUI();
	}
	
	public void initGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 549);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		DataHelper helper = new DataHelper();
		
		
		
		Vector<String> items = helper.get_menu().elementAt(0);
		
		item_list = new JList(items);
		item_list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		item_list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		item_list.setFont(new Font("Tahoma", Font.PLAIN, 18));
		item_list.setBounds(47, 54, 332, 358);
		contentPane.add(item_list);
		
		JLabel lblNewLabel = new JLabel("Manage Menu");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(137, 18, 145, 25);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Add New Item");
		btnNewButton.setAction(action);
		btnNewButton.setBounds(137, 423, 145, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Edit Selected Item");
		btnNewButton_1.setAction(action_1);
		btnNewButton_1.setBounds(137, 457, 145, 23);
		contentPane.add(btnNewButton_1);
	}
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "Add New Item");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			System.out.println("Adding New Item");
		}
	}
	private class SwingAction_1 extends AbstractAction {
		public SwingAction_1() {
			putValue(NAME, "Edit Selected Item");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			String s = (String) item_list.getSelectedValue();
			System.out.println("Value Selected: " + s);
		}
	}
}