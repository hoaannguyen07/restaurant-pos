package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import java.awt.Color;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JFormattedTextField;
import javax.swing.JComboBox;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;
import java.util.Vector;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.SystemColor;
import javax.swing.border.LineBorder;

public class revenueChange extends JFrame {
	
	DataHelper api_connection;
	
	Vector<Vector<String>> orders; // [[price, entrees, sides, beverages, desserts]]

	private JPanel contentPane;
	private JTable table;
	private JComboBox<String> startYear;
	private JComboBox<Integer> startMonth;
	private JComboBox<String> startDay;
	private Calendar startDate = Calendar.getInstance();
    private Calendar endDate = Calendar.getInstance();
    public static final Vector<String> MENU_HEADER = new Vector<String>();
	public static final Vector<Vector<String>> NULL_DATA = new Vector<Vector<String>>();
	public DefaultTableModel model;
	Vector<Vector<String>> menu_list;
	Vector<Vector<String>> order_list;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JTextField priceField;
	private JLabel lblRevenue;
	
	double base_revenue = 0;
	double new_revenue = 0;
	private JLabel lblRevenue_adjusted;
	private JLabel lblNewLabel_7;
	private JButton btnBack;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					revenueChange frame = new revenueChange(new DataHelper());
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
	public revenueChange(DataHelper api) {
		if (MENU_HEADER.size() == 0)
		{
			MENU_HEADER.addElement("ID");
			MENU_HEADER.addElement("Name");
			MENU_HEADER.addElement("Price");
		}
		api_connection = api;
		initGUI();
		show_data_in_table();
	}
	
	void initGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 468, 525);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 153, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Weekly Revenue");
		lblNewLabel.setBounds(69, 11, 314, 30);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 17));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNewLabel);
		
		table = new JTable(NULL_DATA, MENU_HEADER);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = table.getSelectedRow();
				
				// [0] = id || [1] = name || [2] = price || [3] = availability --> how table is set up
				TableModel table_model = table.getModel();
				
				String name = table_model.getValueAt(index, 1).toString();
				String price = table_model.getValueAt(index, 2).toString();
				
				priceField.setText(price);
				
				System.out.println(name + "\t" + price);
			}
		});
		JScrollPane pane_menu = new JScrollPane(table);
		pane_menu.setBounds(24, 52, 402, 196);
		model = (DefaultTableModel)table.getModel();
		contentPane.add(pane_menu);
		
		startMonth = new JComboBox<Integer>();
		startMonth.setBounds(24, 299, 58, 22);
		buildMonthsList(startMonth);
		startMonth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(arg0.getSource() == startMonth) { 
					buildDaysList(startDate, startDay, startMonth);
				}
			}
		});
		contentPane.add(startMonth);
		
		startYear = new JComboBox<String>();
		startYear.setBounds(160, 299, 72, 22);
		buildYearsList(startYear);
		contentPane.add(startYear);
		
		startDay = new JComboBox<String>();
		startDay.setBounds(92, 299, 58, 22);
		buildDaysList(startDate, startDay, startMonth);
		contentPane.add(startDay);
		
		JLabel lblNewLabel_1 = new JLabel("Start of the Week");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setBounds(69, 259, 117, 22);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("Price Change");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(277, 259, 117, 22);
		contentPane.add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("Month");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(30, 280, 46, 14);
		contentPane.add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("Day");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setBounds(98, 280, 46, 14);
		contentPane.add(lblNewLabel_4);
		
		lblNewLabel_5 = new JLabel("Year");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setBounds(173, 280, 46, 14);
		contentPane.add(lblNewLabel_5);
		
		priceField = new JTextField();
		priceField.setBounds(292, 296, 86, 28);
		contentPane.add(priceField);
		priceField.setColumns(10);
		
		JButton btnCalculate = new JButton("Calculate");
		btnCalculate.setBounds(181, 436, 89, 23);
		btnCalculate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(arg0.getSource() == btnCalculate) {
					int year = Integer.parseInt(startYear.getSelectedItem().toString());
					int month = Integer.parseInt(startMonth.getSelectedItem().toString()) - 1; // some mad lad decided months start at 0, but days start at 1.
					int day = Integer.parseInt(startDay.getSelectedItem().toString());		   // took me an hour to debug
				
					startDate.set(year, month, day);
					endDate.set(year, month, day);
					endDate.add(Calendar.DATE, 6); // properly adds days to to dates, accounting for month and year change
					
					orders = api_connection.getOrders(startDate, endDate);
					
					int index = table.getSelectedRow();
					
					String id = "";
					Double d_original_price = 0.0;
					Double d_new_price = 0.0;
					String original_price = "";
					String new_price = "";
					if (index != -1) {
						TableModel table_model = table.getModel();
						original_price = table_model.getValueAt(index, 2).toString();
						d_original_price = Double.parseDouble(original_price.substring(2, original_price.length()));
						
						// cleans user input to ensure the new price can be converted into a double
						new_price = priceField.getText().strip();
						if (new_price.contains("$ ")) {
							d_new_price = Double.parseDouble(new_price.substring(new_price.lastIndexOf(" "), new_price.length()));
						}
						else if (new_price.contains("$")) {
							d_new_price = Double.parseDouble(new_price.substring(1, new_price.length()));
						}
						else {
							d_new_price = Double.parseDouble(new_price);
						}
						
						id = table_model.getValueAt(index, 0).toString();
					}
					
					// determines which column in the orders vectors to read from
					int col = 0;
					if (id.contains("E")) {
						col = 1;
					}
					else if (id.contains("S")) {
						col = 2;
					}
					else if (id.contains("B")) {
						col = 3;
					}
					else if (id.contains("D")) {
						col = 4;
					}
					
					base_revenue = 0;
					new_revenue = 0;
					
					String order = "";
					
					Double price_delta = d_new_price - d_original_price; // change in item price
					
					// loop through each row of orders, add price column from orders query to base_revenue, and add change in item price 
					// to new_revenue for each item in the order that equals the selected item
					for (int i = 0; i < orders.size(); ++i) {
						base_revenue += Double.parseDouble(orders.elementAt(i).elementAt(0));
						
						// if an item was selected and the order on the current row is not null
						if (col > 0) {
							order = orders.elementAt(i).elementAt(col);
							if (order != null) {
								int lastIndex = 0;
								int count = 0;
								
								while(lastIndex != -1){
									
								    lastIndex = order.indexOf(id,lastIndex);
		
								    if(lastIndex != -1){
								        count++;
								        lastIndex += id.length();
								    }
								}
								new_revenue += (count * price_delta);
							}
						}
					}
					new_revenue += base_revenue;
					
					// ensures revenue strings output properly
					String out_revenue = "$ " + Double.toString(base_revenue);
					String out_revenue_adj = "$ " + Double.toString(new_revenue);
					
					if (out_revenue.contains(".")) {
						out_revenue = out_revenue.substring(0, out_revenue.indexOf(".") + 3);
					}
					
					if (out_revenue_adj.contains(".")) {
						out_revenue_adj = out_revenue_adj.substring(0, out_revenue_adj.indexOf(".") + 3);
					}
					
					lblRevenue.setText(out_revenue);
					lblRevenue_adjusted.setText(out_revenue_adj);
				}
			}
		});
		contentPane.add(btnCalculate);
		
		lblRevenue = new JLabel("");
		lblRevenue.setFont(new Font("Arial", Font.BOLD, 14));
		lblRevenue.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		lblRevenue.setBackground(SystemColor.text);
		lblRevenue.setHorizontalAlignment(SwingConstants.CENTER);
		lblRevenue.setBounds(91, 382, 105, 30);
		contentPane.add(lblRevenue);
		
		JLabel lblNewLabel_6 = new JLabel("Revenue");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_6.setBounds(112, 351, 64, 20);
		contentPane.add(lblNewLabel_6);
		
		lblRevenue_adjusted = new JLabel("");
		lblRevenue_adjusted.setFont(new Font("Arial", Font.BOLD, 14));
		lblRevenue_adjusted.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		lblRevenue_adjusted.setHorizontalAlignment(SwingConstants.CENTER);
		lblRevenue_adjusted.setBounds(244, 382, 105, 30);
		contentPane.add(lblRevenue_adjusted);
		
		lblNewLabel_7 = new JLabel("Adjusted Revenue");
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_7.setBounds(244, 351, 105, 20);
		contentPane.add(lblNewLabel_7);
		
		btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(arg0.getSource() == btnBack) {
					managerOptionMenu manager_menu = new managerOptionMenu(api_connection);
					manager_menu.setVisible(true);
					dispose();
				}
			}
		});
		btnBack.setBounds(10, 452, 72, 23);
		contentPane.add(btnBack);
	}
	
    private void buildYearsList(JComboBox<String> yearsList) {

        int currentYear = startDate.get(Calendar.YEAR);

        for (int yearCount = currentYear - 5; yearCount <= currentYear; yearCount++)
            yearsList.addItem(Integer.toString(yearCount));
    }

    /**
     * This method builds the list of months for the start
     * date and end date of the semester
     * @param monthsList The combo box containing the months
     */
    private void buildMonthsList(JComboBox<Integer> monthsList) {

        monthsList.removeAllItems();
        for (int monthCount = 1; monthCount <= 12; monthCount++)
            monthsList.addItem(monthCount);
    }

    /**
     * This method builds the list of years for the start
     * date and end date of the semester
     * @param dateIn The current date, which will be used for
     * the initial date of the lists
     * @param daysList The combo box that will contain the days
     * @param monthsList The combo box that will contain the months
     */
    private void buildDaysList(Calendar dateIn, JComboBox<String> daysList, JComboBox<Integer> monthsList) {

        daysList.removeAllItems();
        dateIn.set(Calendar.MONTH, monthsList.getSelectedIndex());
        int lastDay = startDate.getActualMaximum(Calendar.DAY_OF_MONTH);

        for (int dayCount = 1; dayCount <= lastDay; dayCount++)
            daysList.addItem(Integer.toString(dayCount));
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
	
	
	/**
	 * get menu data from api and add it to table to be showed on the frame
	 */
	void show_data_in_table()
	{
		// first make sure there is nothing in the table before adding stuff in
		this.delete_all_rows_in_table();
		
		menu_list = api_connection.get_menu_data(); // [0] = id || [1] = name || [2] = price || [3] = availability
		
		String id = "";
		String name = "";
		String price = "";
		
		for(int i = 0; i < menu_list.size(); i++)
		{
			Vector<String> displaying_list = new Vector<String>();
			
			id = menu_list.elementAt(i).elementAt(0);
			name = menu_list.elementAt(i).elementAt(1);
			price = "$ " + menu_list.elementAt(i).elementAt(2);
			
			displaying_list.addElement(id);
			displaying_list.addElement(name);
			displaying_list.addElement(price);
			model.addRow(displaying_list);
		}
	}
}
