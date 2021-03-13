package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.awt.Component;
import javax.swing.JTable;
import java.util.Vector;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.Font;

public class Recommendations extends JFrame {
	
	DataHelper api_connection;
	
	Vector<Vector<String>> cart_list;
	
	private static final int RECOMMENDATION_MENU_ID = 5;
	
	public static final Vector<String> REC_HEADER = new Vector<String>();
	public static final Vector<Vector<String>> NULL_DATA = new Vector<Vector<String>>();
	public DefaultTableModel model;
	
	private JPanel contentPane;
	private JButton btnPayment = new JButton("Payment");
	private JButton btnBack = new JButton("Back");
	private JLabel lblYourCart = new JLabel("We also recommend these items!");
	private JScrollPane scrollpane_menu = new JScrollPane((Component) null);
	private JTable table_menu;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					cart frame = new cart(new DataHelper());
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
	public Recommendations() {
		if (REC_HEADER.size() == 0)
		{
			REC_HEADER.addElement("Item Name");
			REC_HEADER.addElement("Price");
		}
		initGUI();
	}
	
	public Recommendations(DataHelper api) {
		this.api_connection = api;
		if (REC_HEADER.size() == 0)
		{
			REC_HEADER.addElement("Item Name");
			REC_HEADER.addElement("Price");
		}
		initGUI();
		
		
		
		
		
		show_cart_data();
	}
	
	private void initGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 380, 293);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 153, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		btnPayment.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cardtype payment_info = new cardtype(api_connection);
				payment_info.setVisible(true);
				dispose();
			}
		});
		btnPayment.setBounds(263, 0, 89, 25);
		
		contentPane.add(btnPayment);
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cart c = new cart(api_connection);
				c.setVisible(true);
				dispose();
			}
		});
		btnBack.setBounds(10, 0, 76, 25);
				
		contentPane.add(btnBack);
		lblYourCart.setFont(new Font("Arial", Font.BOLD, 15));
		lblYourCart.setHorizontalAlignment(SwingConstants.LEFT);
		lblYourCart.setBounds(63, 71, 249, 33);
		
		contentPane.add(lblYourCart);
		
		table_menu = new JTable(NULL_DATA, REC_HEADER);
		table_menu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = table_menu.getSelectedRow();
				
				TableModel table_model = table_menu.getModel();
				
				String name = table_model.getValueAt(index, 0).toString();
				String price = table_model.getValueAt(index, 1).toString();
				
				price = price.substring(2); // removing "$ "
				
				String item_id = api_connection.getItemID(name);
				
				System.out.println(name + "\t" + item_id + "\t" + price);
				
				// let api update information on what menu item is being added to cart
				api_connection.choose_menu_item_to_customize(item_id);
				Ingredients ingr_frame = new Ingredients(api_connection, RECOMMENDATION_MENU_ID);
				ingr_frame.setVisible(true);
				dispose();
			}
		});
		
		model = (DefaultTableModel)table_menu.getModel();
		scrollpane_menu = new JScrollPane(table_menu);
		scrollpane_menu.setBounds(10, 105, 344, 109);
		
		
		contentPane.add(scrollpane_menu);
		
		scrollpane_menu.setViewportView(table_menu);
	}
	
	void delete_all_rows_in_table()
	{
		int row_count = model.getRowCount();
		// remove one row at a time
		for(int i = row_count - 1; i >= 0; i--)
		{
			model.removeRow(i);
		}
	}
	
	void show_cart_data()
	{
		// first make sure there is nothing in the table before adding stuff in
		this.delete_all_rows_in_table();
		
		Vector<Vector<String>> entrees = api_connection.trending_options_for_rec("E");
		Vector<Vector<String>> sides = api_connection.trending_options_for_rec("S");
		Vector<Vector<String>> beverages = api_connection.trending_options_for_rec("B");
		Vector<Vector<String>> desserts = api_connection.trending_options_for_rec("D");
		
		if (entrees.size() > 0)
		{
			model.addRow(entrees.elementAt(entrees.size() - 1));
		}
		
		if (sides.size() > 0)
		{
			model.addRow(sides.elementAt(sides.size() - 1));
		}
		
		if (beverages.size() > 0)
		{
			model.addRow(beverages.elementAt(beverages.size() - 1));
		}
		
		if (desserts.size() > 0)
		{
			model.addRow(desserts.elementAt(desserts.size() - 1));
		}
	}
	
}
