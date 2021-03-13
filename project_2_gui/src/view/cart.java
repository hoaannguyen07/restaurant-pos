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
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.awt.Component;
import javax.swing.JTable;
import java.util.Vector;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class cart extends JFrame {
	
	DataHelper api_connection;
	
	Vector<Vector<String>> cart_list;
	
	public static final Vector<String> CART_HEADER = new Vector<String>();
	public static final Vector<Vector<String>> NULL_DATA = new Vector<Vector<String>>();
	public DefaultTableModel model;
	
	private JPanel contentPane;
	private JButton button_payment = new JButton("Payment");
	private JButton button_back = new JButton("Back");
	private JLabel label_title = new JLabel("YOUR CART");
	private JScrollPane scrollpane_menu = new JScrollPane((Component) null);
	private JTable table_cart;
	private final JLabel label_message = new JLabel("");
	private final JLabel label_price = new JLabel("New label");

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
	public cart() {
		if (CART_HEADER.size() == 0)
		{
			CART_HEADER.addElement("Item Name");
			CART_HEADER.addElement("Customizations");
		}
		initGUI();
	}
	
	public cart(DataHelper api) {
		this.api_connection = api;
		if (CART_HEADER.size() == 0)
		{
			CART_HEADER.addElement("Item Name");
			CART_HEADER.addElement("Customizations");
		}
		initGUI();
		show_cart_data();
	}
	
	private void initGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 604, 568);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 153, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		button_payment.setForeground(new Color(255, 255, 255));
		button_payment.setFont(new Font("Arial", Font.BOLD, 18));
		button_payment.setBackground(new Color(153, 0, 0));
		button_payment.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (table_cart.getRowCount() == 0) // nothing in cart so there's nothing ot pay
				{
					label_message.setText("Your cart is empty");
				}
				else
				{
					Recommendations rec = new Recommendations(api_connection);
					rec.setVisible(true);
					dispose();
				}
			}
		});
		button_payment.setBounds(465, 12, 117, 25);
		
		contentPane.add(button_payment);
		button_back.setForeground(new Color(255, 255, 255));
		button_back.setFont(new Font("Arial", Font.BOLD, 15));
		button_back.setBackground(new Color(153, 0, 0));
		button_back.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
//				pretty sure this is a bug, should go back to menuSelect()
//				customerOptionMenu com = new customerOptionMenu(api_connection);
//				com.setVisible(true);
				menuSelect menu = new menuSelect(api_connection);
				menu.setVisible(true);

				dispose();
			}
		});
		button_back.setBounds(10, 11, 69, 25);
		button_back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(arg0.getSource() == button_back) { 
					// just makes it so you can click anywhere and it will still go back
					// same functionality as line 103, can be deleted as necessary
					menuSelect menu = new menuSelect(api_connection);
					menu.setVisible(true);
					dispose();
				}
			}
		});
		
		contentPane.add(button_back);
		label_title.setFont(new Font("Arial Black", Font.BOLD, 30));
		label_title.setHorizontalAlignment(SwingConstants.CENTER);
		label_title.setBounds(177, 44, 238, 53);
		
		contentPane.add(label_title);
		
		table_cart = new JTable(NULL_DATA, CART_HEADER);
		table_cart.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = table_cart.getSelectedRow();
				
				TableModel table_model = table_cart.getModel();
				
				String name = table_model.getValueAt(index, 0).toString();
				String customizations = table_model.getValueAt(index, 1).toString();
				String item_id = api_connection.getItemID(name);
				System.out.println(name + "\t" + customizations) ;
				
				
				Item_Delete_Option delete_frame = new Item_Delete_Option(api_connection, item_id);
				delete_frame.setVisible(true);
				dispose();
			}
		});
		
		model = (DefaultTableModel)table_cart.getModel();
		scrollpane_menu = new JScrollPane(table_cart);
		scrollpane_menu.setBounds(10, 136, 572, 374);
		
		
		contentPane.add(scrollpane_menu);
		
		scrollpane_menu.setViewportView(table_cart);
		label_message.setForeground(new Color(128, 0, 0));
		label_message.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_message.setBounds(20, 108, 343, 24);
		
		contentPane.add(label_message);
		label_price.setFont(new Font("Arial Black", Font.BOLD, 15));
		label_price.setHorizontalAlignment(SwingConstants.RIGHT);
		label_price.setBounds(478, 111, 104, 21);
		label_price.setText("$" + api_connection.cart_helper.getTotal_cost());
		contentPane.add(label_price);
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
		
		cart_list = api_connection.compile_cart_for_display(); // [0] = id || [1] = name || [2] = price
//		DefaultTableModel model = (DefaultTableModel) table_cart.getModel();
		
		// only display item name and price
		for(int i = 0; i < cart_list.size(); i++)
		{
//			Vector<String> displaying_list = new Vector<String>();
//			displaying_list.addElement(menu_list.elementAt(i).elementAt(1));
//			displaying_list.addElement("$ " + menu_list.elementAt(i).elementAt(2));
//			model.addRow(displaying_list);
			model.addRow(cart_list.elementAt(i));
		}
	}
	
}
