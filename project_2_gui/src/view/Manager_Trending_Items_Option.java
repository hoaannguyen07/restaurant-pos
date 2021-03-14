package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.Font;
import java.util.Vector;
import javax.swing.JTable;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Manager_Trending_Items_Option extends JFrame {

	private static final long serialVersionUID = 1L;

	DataHelper api_connection;
	Vector<Vector<String>> ordered_times;
	
	//model
	public static final Vector<String> TRENDING_HEADER = new Vector<String>();
	public static final Vector<Vector<String>> NULL_DATA = new Vector<Vector<String>>();
	public DefaultTableModel model_trending;
	private JTable table_trending;
	
	//misc.
	private final JButton button_back = new JButton("Back");
	private final JLabel lblTrending = new JLabel("TRENDING");
	
	//JPanel
	private JPanel contentPane;
	private JScrollPane scrollpanel_trending;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Manager_Trending_Items_Option frame = new Manager_Trending_Items_Option(new DataHelper());
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}//end try catch
			}//end run
		});//end invoke later
	}//end main

	/**
	 * Create the frame.
	 */
	public Manager_Trending_Items_Option(DataHelper api) {
		api_connection = api;
		if (TRENDING_HEADER.size() == 0) {
			TRENDING_HEADER.addElement("Item Name");
			TRENDING_HEADER.addElement("Number of Times Ordered");
		}//end if
		
		ordered_times = new Vector<Vector<String>>();
		initGUI();
	}//end constructor
	
	void initGUI() { 
		//panel
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 541);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 153, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//chooser label
		JLabel lblChooser = new JLabel("Choose an Option:");
		lblChooser.setHorizontalAlignment(SwingConstants.CENTER);
		lblChooser.setBounds(125, 11, 187, 39);
		lblChooser.setFont(new Font("Arial", Font.BOLD, 20));
		contentPane.add(lblChooser);
		
		//entrees button
		JButton btnEntrees = new JButton("Entrees");
		btnEntrees.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ordered_times = api_connection.trending_options("E");
				System.out.println(ordered_times);
				show_trending_data_descending();
			}//end mouse clicked
		});//end add mouse listener
		btnEntrees.setBounds(35, 89, 163, 48);
		btnEntrees.setBackground(new Color(153, 0, 0));
		btnEntrees.setFont(new Font("Arial", Font.BOLD, 20));
		contentPane.add(btnEntrees);
		
		//side button
		JButton btnSides = new JButton("Sides");
		btnSides.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ordered_times = api_connection.trending_options("S");
				System.out.println(ordered_times);
				show_trending_data_descending();
			}//end mouse clicked
		});//end add mouse listener
		btnSides.setBounds(241, 89, 163, 48);
		btnSides.setBackground(new Color(153, 0, 0));
		btnSides.setFont(new Font("Arial", Font.BOLD, 20));
		contentPane.add(btnSides);
		
		//desserts button
		JButton btnDesserts = new JButton("Desserts");
		btnDesserts.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ordered_times = api_connection.trending_options("D");
				System.out.println(ordered_times);
				show_trending_data_descending();
			}//end mouse clicked
		});//end add mouse listener
		btnDesserts.setBounds(35, 159, 163, 48);
		btnDesserts.setBackground(new Color(153, 0, 0));
		btnDesserts.setFont(new Font("Arial", Font.BOLD, 20));
		contentPane.add(btnDesserts);
		
		//beverage button
		JButton btnBeverage = new JButton("Beverages");
		btnBeverage.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ordered_times = api_connection.trending_options("B");
				System.out.println(ordered_times);
				show_trending_data_descending();
			}//end mouse clicked
		});//end add mouse listener
		btnBeverage.setBounds(241, 159, 163, 48);
		btnBeverage.setBackground(new Color(153, 0, 0));
		btnBeverage.setFont(new Font("Arial", Font.BOLD, 20));
		contentPane.add(btnBeverage);
		
		//table trending
		table_trending = new JTable(NULL_DATA,TRENDING_HEADER);
		scrollpanel_trending = new JScrollPane(table_trending);
		scrollpanel_trending.setBounds(35, 255, 369, 164);
		model_trending = (DefaultTableModel)table_trending.getModel();
		contentPane.add(scrollpanel_trending);
		scrollpanel_trending.setViewportView(table_trending);
		
		//back button
		button_back.setBackground(new Color(204, 0, 0));
		button_back.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Manager_Option_Menu manager_opt_menu = new Manager_Option_Menu(api_connection);
				manager_opt_menu.setVisible(true);
				dispose();
			}//end mouse clicked
		});//end add mouse listener
		button_back.setBounds(10, 11, 55, 23);
		contentPane.add(button_back);
		
		//trending label
		lblTrending.setHorizontalAlignment(SwingConstants.CENTER);
		lblTrending.setFont(new Font("Arial", Font.BOLD, 20));
		lblTrending.setBounds(141, 230, 156, 26);
		contentPane.add(lblTrending);
	}//end init gui
	
	void delete_all_rows_in_table(DefaultTableModel model) {
		int row_count = model.getRowCount();
		// remove one row at a time
		for(int i = row_count - 1; i >= 0; i--) {
			model.removeRow(i);
		}//end for
	}//end delete all rows in table
	
	void show_trending_data_descending() {
		this.delete_all_rows_in_table(model_trending);
		
		System.out.println("Show data trending up");
		
		for(int i = ordered_times.size() - 1; i >= 0; i--) {
			model_trending.addRow(ordered_times.elementAt(i));
		}//end for
	}//end show trending data descending
}//end class