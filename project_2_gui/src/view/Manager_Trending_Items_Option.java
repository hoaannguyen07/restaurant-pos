package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Manager_Trending_Items_Option extends JFrame {

	DataHelper api_connection;
	
	Vector<Vector<String>> ordered_times;
	
	public static final Vector<String> TRENDING_HEADER = new Vector<String>();
	public static final Vector<Vector<String>> NULL_DATA = new Vector<Vector<String>>();
	public DefaultTableModel model_trending_up;
	public DefaultTableModel model_trending_down;
	private JPanel contentPane;
	private JPanel contentEntree;
	private JScrollPane scrollpanel_trending_up;
	private JTable table_trending_up;
	private JScrollPane scrollpanel_trending_down = new JScrollPane();
	private JTable table_trending_down;
	private final JButton button_back = new JButton("Back");
	private final JLabel label_trending_up = new JLabel("TRENDING UP");
	private final JLabel label_trending_up_1 = new JLabel("TRENDING DOWN");

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
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Manager_Trending_Items_Option(DataHelper api) {
		api_connection = api;
		if (TRENDING_HEADER.size() == 0)
		{
			TRENDING_HEADER.addElement("Item Name");
			TRENDING_HEADER.addElement("Number of Times Ordered");
		}
		
		ordered_times = new Vector<Vector<String>>();
		
		initGUI();
	}
	
	void initGUI() { 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 541);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 153, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblChooser = new JLabel("Choose an Option:");
		lblChooser.setHorizontalAlignment(SwingConstants.CENTER);
		lblChooser.setBounds(125, 11, 187, 39);
		lblChooser.setFont(new Font("Arial", Font.BOLD, 20));
		contentPane.add(lblChooser);
		
		JButton btnEntrees = new JButton("Entrees");
		btnEntrees.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ordered_times = api_connection.trending_options("E");
				System.out.println(ordered_times);
				show_data_trending_up();
				show_data_trending_down();
			}
		});
		btnEntrees.setBounds(35, 89, 163, 48);
		btnEntrees.setBackground(new Color(153, 0, 0));
		btnEntrees.setFont(new Font("Arial", Font.BOLD, 20));

		contentPane.add(btnEntrees);
		
		JButton btnSides = new JButton("Sides");
		btnSides.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ordered_times = api_connection.trending_options("S");
				System.out.println(ordered_times);
				show_data_trending_up();
				show_data_trending_down();
			}
		});
		btnSides.setBounds(241, 89, 163, 48);
		btnSides.setBackground(new Color(153, 0, 0));

		btnSides.setFont(new Font("Arial", Font.BOLD, 20));
		contentPane.add(btnSides);
		
		JButton btnDesserts = new JButton("Desserts");
		btnDesserts.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ordered_times = api_connection.trending_options("D");
				System.out.println(ordered_times);
				show_data_trending_up();
				show_data_trending_down();
			}
		});
		btnDesserts.setBounds(35, 159, 163, 48);
		btnDesserts.setBackground(new Color(153, 0, 0));

		btnDesserts.setFont(new Font("Arial", Font.BOLD, 20));
		contentPane.add(btnDesserts);
		
		JButton btnBeverage = new JButton("Beverages");
		btnBeverage.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ordered_times = api_connection.trending_options("B");
				System.out.println(ordered_times);
				show_data_trending_up();
				show_data_trending_down();
			}
		});
		btnBeverage.setBounds(241, 159, 163, 48);
		btnBeverage.setBackground(new Color(153, 0, 0));

		btnBeverage.setFont(new Font("Arial", Font.BOLD, 20));
		contentPane.add(btnBeverage);
		
		table_trending_up = new JTable(NULL_DATA,TRENDING_HEADER);
		scrollpanel_trending_up = new JScrollPane(table_trending_up);
		scrollpanel_trending_up.setBounds(35, 255, 369, 62);
		model_trending_up = (DefaultTableModel)table_trending_up.getModel();
		
		contentPane.add(scrollpanel_trending_up);
		
		scrollpanel_trending_up.setViewportView(table_trending_up);
		
		table_trending_down = new JTable(NULL_DATA,TRENDING_HEADER);
		scrollpanel_trending_down = new JScrollPane(table_trending_down);
		scrollpanel_trending_down.setBounds(35, 380, 369, 62);
		model_trending_down = (DefaultTableModel)table_trending_down.getModel();
		
		contentPane.add(scrollpanel_trending_down);
		
		scrollpanel_trending_down.setViewportView(table_trending_down);
		button_back.setBackground(new Color(204, 0, 0));
		button_back.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				managerOptionMenu manager_opt_menu = new managerOptionMenu(api_connection);
				manager_opt_menu.setVisible(true);
				dispose();
			}
		});
		button_back.setBounds(10, 11, 55, 23);
		
		contentPane.add(button_back);
		label_trending_up.setHorizontalAlignment(SwingConstants.CENTER);
		label_trending_up.setFont(new Font("Arial", Font.BOLD, 20));
		label_trending_up.setBounds(141, 230, 156, 26);
		
		contentPane.add(label_trending_up);
		label_trending_up_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_trending_up_1.setFont(new Font("Arial", Font.BOLD, 20));
		label_trending_up_1.setBounds(125, 355, 187, 26);
		
		contentPane.add(label_trending_up_1);
	}
	
	void delete_all_rows_in_table(DefaultTableModel model)
	{
		int row_count = model.getRowCount();
		// remove one row at a time
		for(int i = row_count - 1; i >= 0; i--)
		{
			model.removeRow(i);
		}
	}
	
	void show_data_trending_up()
	{
		this.delete_all_rows_in_table(model_trending_up);
		
		System.out.println("Show data trending up");
		
		// if there are only 2 items, only have 1 up trending
		if (ordered_times.size() < 4)
		{
			model_trending_up.addRow(ordered_times.elementAt(ordered_times.size() - 1)); // take last one only b/c there is only 3
		}
		else
		{
			for(int i = ordered_times.size() - 1; i >= ordered_times.size() - 2; i--)
			{
				model_trending_up.addRow(ordered_times.elementAt(i));
			}
		}
		
	}
	
	void show_data_trending_down()
	{
		this.delete_all_rows_in_table(model_trending_down);
		
		System.out.println("Show data trending down");
		
		// if there are only 2 items, only have 1 up trending
		if (ordered_times.size() < 4)
		{
			model_trending_down.addRow(ordered_times.elementAt(0)); // only show top trending down b/c there are only < 3
		}
		else
		{
			for(int i = 0; i < 2; i++)
			{
				model_trending_down.addRow(ordered_times.elementAt(i));
			}
		}
		
	}
}
