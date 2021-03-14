package view;

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

public class Revenue_Change extends JFrame {
	
	private static final long serialVersionUID = 1L;

	DataHelper api_connection;
	
	Vector<Vector<String>> orders; // [[price, entrees, sides, beverages, desserts]]
	Vector<Vector<String>> menu_list;
	Vector<Vector<String>> order_list;
	
	//Calendar
	private Calendar startDate = Calendar.getInstance();
    private Calendar endDate = Calendar.getInstance();

    //model
	private JPanel contentPane;
	private JTable table;
    public static final Vector<String> MENU_HEADER = new Vector<String>();
	public static final Vector<Vector<String>> NULL_DATA = new Vector<Vector<String>>();
	
	//JComboBox
	private JComboBox<String> startYear;
	private JComboBox<Integer> startMonth;
	private JComboBox<String> startDay;
	private JComboBox<Integer> endMonth;
	private JComboBox<String> endYear;
	private JComboBox<String> endDay;
	
	//JLabel
	private JLabel price_change_label;
	private JLabel month_label;
	private JLabel day_label;
	private JLabel year_label;
	private JLabel lblRevenue;
	private JLabel lblOrderAmount;
	private JLabel lblRevenue_adjusted;
	private JLabel adjusted_revenue_label;
	
	double base_revenue = 0;
	double new_revenue = 0;
	
	//misc.
	private JButton btnBack;
	public DefaultTableModel model;
	private JTextField priceField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Revenue_Change frame = new Revenue_Change(new DataHelper());
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
	public Revenue_Change(DataHelper api) {
		if (MENU_HEADER.size() == 0) {
			MENU_HEADER.addElement("ID");
			MENU_HEADER.addElement("Name");
			MENU_HEADER.addElement("Price");
		}//end if
		api_connection = api;
		initGUI();
		show_data_in_table();
	}//end constructor
	
	void initGUI() {
		//panel
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 468, 593);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 153, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		//order stats label
		JLabel order_stats_label = new JLabel("Order Statistics");
		order_stats_label.setBounds(69, 11, 314, 30);
		order_stats_label.setFont(new Font("Arial", Font.BOLD, 17));
		order_stats_label.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(order_stats_label);
		
		//table
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
			}//end mouse clicked
		});//end add mouse listener
		
		//pane menu
		JScrollPane pane_menu = new JScrollPane(table);
		pane_menu.setBounds(24, 52, 402, 196);
		model = (DefaultTableModel)table.getModel();
		contentPane.add(pane_menu);
		
		//month start
		startMonth = new JComboBox<Integer>();
		startMonth.setBounds(24, 299, 58, 22);
		buildMonthsList(startMonth);
		startMonth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(arg0.getSource() == startMonth) { 
					buildDaysList(startDate, startDay, startMonth);
				}//end if
			}//end action performed
		});//end add action listener
		contentPane.add(startMonth);
		
		//year start
		startYear = new JComboBox<String>();
		startYear.setBounds(160, 299, 72, 22);
		buildYearsList(startYear);
		contentPane.add(startYear);
		
		//day start
		startDay = new JComboBox<String>();
		startDay.setBounds(92, 299, 58, 22);
		buildDaysList(startDate, startDay, startMonth);
		contentPane.add(startDay);
		
		//month end
		endMonth = new JComboBox<Integer>();
		endMonth.setBounds(24, 372, 58, 22);
		buildMonthsList(endMonth);
		endMonth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(arg0.getSource() == endMonth) { 
					buildDaysList(endDate, endDay, endMonth);
				}//end if
			}//end action performed
		});//end add action listener
		contentPane.add(endMonth);
		
		//day end
		endDay = new JComboBox<String>();
		endDay.setBounds(92, 372, 58, 22);
		buildDaysList(endDate, endDay, endMonth);
		contentPane.add(endDay);
		
		//year end
		endYear = new JComboBox<String>();
		endYear.setBounds(160, 372, 72, 22);
		buildYearsList(endYear);
		contentPane.add(endYear);
		
		//start date label
		JLabel start_date_label = new JLabel("Start Date");
		start_date_label.setFont(new Font("Tahoma", Font.BOLD, 11));
		start_date_label.setBounds(69, 259, 117, 22);
		start_date_label.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(start_date_label);
		
		//price change label
		price_change_label = new JLabel("Price Change");
		price_change_label.setFont(new Font("Tahoma", Font.BOLD, 11));
		price_change_label.setHorizontalAlignment(SwingConstants.CENTER);
		price_change_label.setBounds(282, 313, 117, 22);
		contentPane.add(price_change_label);
		
		//month label
		month_label = new JLabel("Month");
		month_label.setHorizontalAlignment(SwingConstants.CENTER);
		month_label.setBounds(30, 280, 46, 14);
		contentPane.add(month_label);
		
		//day label
		day_label = new JLabel("Day");
		day_label.setHorizontalAlignment(SwingConstants.CENTER);
		day_label.setBounds(98, 280, 46, 14);
		contentPane.add(day_label);
		
		//year label
		year_label = new JLabel("Year");
		year_label.setHorizontalAlignment(SwingConstants.CENTER);
		year_label.setBounds(173, 280, 46, 14);
		contentPane.add(year_label);
		
		//price text field
		priceField = new JTextField();
		priceField.setBounds(297, 336, 86, 28);
		contentPane.add(priceField);
		priceField.setColumns(10);
		
		//calculate button
		JButton btnCalculate = new JButton("Calculate");
		btnCalculate.setBounds(181, 504, 89, 23);
		btnCalculate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(arg0.getSource() == btnCalculate) {
					int year = Integer.parseInt(startYear.getSelectedItem().toString());
					int month = Integer.parseInt(startMonth.getSelectedItem().toString()) - 1; // some mad lad decided months start at 0, but days start at 1.
					int day = Integer.parseInt(startDay.getSelectedItem().toString());		   // took me an hour to debug
				
					startDate.set(year, month, day);
					
					year = Integer.parseInt(endYear.getSelectedItem().toString());
					month = Integer.parseInt(endMonth.getSelectedItem().toString()) - 1;
					day = Integer.parseInt(endDay.getSelectedItem().toString());
				
					endDate.set(year, month, day); // properly adds days to to dates, accounting for month and year change
					
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
						} else if (new_price.contains("$")) {
							d_new_price = Double.parseDouble(new_price.substring(1, new_price.length()));
						} else {
							d_new_price = Double.parseDouble(new_price);
						}//end if/else
						
						id = table_model.getValueAt(index, 0).toString();
					}//end if
					
					// determines which column in the orders vectors to read from
					int col = 0;
					if (id.contains("E")) {
						col = 1;
					} else if (id.contains("S")) {
						col = 2;
					} else if (id.contains("B")) {
						col = 3;
					} else if (id.contains("D")) {
						col = 4;
					}//end if/else
					
					base_revenue = 0;
					new_revenue = 0;
					
					String order = "";
					double sum_items = 0.0;
					
					Double price_delta = d_new_price - d_original_price; // change in item price
					
					// loop through each row of orders, add price column from orders query to base_revenue, and add change in item price 
					// to new_revenue for each item in the order that equals the selected item
					for (int i = 0; i < orders.size(); ++i) {
						base_revenue += Double.parseDouble(orders.elementAt(i).elementAt(0));
						sum_items += Integer.parseInt(orders.elementAt(i).elementAt(5));
						
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
								    }//end if
								}//end while
								new_revenue += (count * price_delta);
							}//end if
						}//end if
					}//end for
					new_revenue += base_revenue;
					
					System.out.println(new_revenue);
					
					// ensures revenue strings output properly
					String out_revenue = "$ " + String.format("%f", base_revenue);
					String out_revenue_adj = "$ " + String.format("%f", new_revenue);
					
					if (out_revenue.contains(".")) {
						out_revenue = out_revenue.substring(0, out_revenue.indexOf(".") + 3);
					}//end if
					if (out_revenue_adj.contains(".")) {
						out_revenue_adj = out_revenue_adj.substring(0, out_revenue_adj.indexOf(".") + 3);
					}//end if
					
					String ave_items = Double.toString(sum_items / orders.size());
					if (ave_items.contains(".")) {
						ave_items = ave_items.substring(0, ave_items.indexOf(".") + 3);
					}//end if
					
					lblRevenue.setText(out_revenue);
					lblRevenue_adjusted.setText(out_revenue_adj);
					lblOrderAmount.setText(ave_items);
				}//end if
			}//end action performed
		});//end add action listener
		contentPane.add(btnCalculate);
		
		//revenue label
		lblRevenue = new JLabel("");
		lblRevenue.setFont(new Font("Arial", Font.BOLD, 14));
		lblRevenue.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		lblRevenue.setBackground(SystemColor.text);
		lblRevenue.setHorizontalAlignment(SwingConstants.CENTER);
		lblRevenue.setBounds(24, 452, 126, 30);
		contentPane.add(lblRevenue);
		
		//secondary revenue label
		JLabel secondary_revenue_label = new JLabel("Revenue");
		secondary_revenue_label.setFont(new Font("Tahoma", Font.BOLD, 11));
		secondary_revenue_label.setHorizontalAlignment(SwingConstants.CENTER);
		secondary_revenue_label.setBounds(55, 432, 64, 20);
		contentPane.add(secondary_revenue_label);
		
		//adjusted label revenue
		lblRevenue_adjusted = new JLabel("");
		lblRevenue_adjusted.setFont(new Font("Arial", Font.BOLD, 14));
		lblRevenue_adjusted.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		lblRevenue_adjusted.setHorizontalAlignment(SwingConstants.CENTER);
		lblRevenue_adjusted.setBounds(160, 452, 142, 30);
		contentPane.add(lblRevenue_adjusted);
		
		//adjusted revenue label
		adjusted_revenue_label = new JLabel("Adjusted Revenue");
		adjusted_revenue_label.setHorizontalAlignment(SwingConstants.CENTER);
		adjusted_revenue_label.setFont(new Font("Tahoma", Font.BOLD, 11));
		adjusted_revenue_label.setBounds(179, 432, 105, 20);
		contentPane.add(adjusted_revenue_label);
		
		//back button
		btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(arg0.getSource() == btnBack) {
					Manager_Option_Menu manager_menu = new Manager_Option_Menu(api_connection);
					manager_menu.setVisible(true);
					dispose();
				} //end if
			}//end action performed
		});//end add action listener
		btnBack.setBounds(10, 520, 72, 23);
		contentPane.add(btnBack);
		
		//end date label
		JLabel end_date_label = new JLabel("End Date");
		end_date_label.setHorizontalAlignment(SwingConstants.CENTER);
		end_date_label.setFont(new Font("Tahoma", Font.BOLD, 11));
		end_date_label.setBounds(69, 332, 117, 22);
		contentPane.add(end_date_label);
		
		//month label
		JLabel month_label = new JLabel("Month");
		month_label.setHorizontalAlignment(SwingConstants.CENTER);
		month_label.setBounds(30, 353, 46, 14);
		contentPane.add(month_label);
		
		//day label
		JLabel day_label = new JLabel("Day");
		day_label.setHorizontalAlignment(SwingConstants.CENTER);
		day_label.setBounds(98, 353, 46, 14);
		contentPane.add(day_label);
		
		//year label
		JLabel year_label = new JLabel("Year");
		year_label.setHorizontalAlignment(SwingConstants.CENTER);
		year_label.setBounds(173, 353, 46, 14);
		contentPane.add(year_label);
		
		//order amount label
		lblOrderAmount = new JLabel("");
		lblOrderAmount.setHorizontalAlignment(SwingConstants.CENTER);
		lblOrderAmount.setFont(new Font("Arial", Font.BOLD, 14));
		lblOrderAmount.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		lblOrderAmount.setBounds(330, 452, 86, 30);
		contentPane.add(lblOrderAmount);
		
		//average number label
		JLabel avg_number_label = new JLabel("Average Number");
		avg_number_label.setHorizontalAlignment(SwingConstants.CENTER);
		avg_number_label.setFont(new Font("Tahoma", Font.BOLD, 11));
		avg_number_label.setBounds(297, 415, 154, 22);
		contentPane.add(avg_number_label);
		
		//items per order label
		JLabel items_per_order_label = new JLabel("of Items per Order");
		items_per_order_label.setHorizontalAlignment(SwingConstants.CENTER);
		items_per_order_label.setFont(new Font("Tahoma", Font.BOLD, 11));
		items_per_order_label.setBounds(297, 431, 154, 22);
		contentPane.add(items_per_order_label);
	}//end init gui
	
    private void buildYearsList(JComboBox<String> yearsList) {
        int currentYear = startDate.get(Calendar.YEAR);
        for (int yearCount = currentYear - 5; yearCount <= currentYear; yearCount++) {
            yearsList.addItem(Integer.toString(yearCount));
        } //end for
    } //end build year list

    /**
     * This method builds the list of months for the start
     * date and end date of the semester
     * @param monthsList The combo box containing the months
     */
    private void buildMonthsList(JComboBox<Integer> monthsList) {
        monthsList.removeAllItems();
        for (int monthCount = 1; monthCount <= 12; monthCount++)
            monthsList.addItem(monthCount);
    } //end build months list

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

        for (int dayCount = 1; dayCount <= lastDay; dayCount++) {
            daysList.addItem(Integer.toString(dayCount));
        } //end for
    }//end build days list
    
    void delete_all_rows_in_table() {
		int row_count = model.getRowCount();
		// remove one row at a time
		for(int i = row_count - 1; i >= 0; i--) {
			model.removeRow(i);
		}//end for
	}//end delete all rows in table
	
	
	/**
	 * get menu data from api and add it to table to be showed on the frame
	 */
	void show_data_in_table() {
		// first make sure there is nothing in the table before adding stuff in
		this.delete_all_rows_in_table();
		
		menu_list = api_connection.get_menu_data(); // [0] = id || [1] = name || [2] = price || [3] = availability
		
		String id = "";
		String name = "";
		String price = "";
		
		for(int i = 0; i < menu_list.size(); i++) {
			Vector<String> displaying_list = new Vector<String>();
			
			id = menu_list.elementAt(i).elementAt(0);
			name = menu_list.elementAt(i).elementAt(1);
			price = "$ " + menu_list.elementAt(i).elementAt(2);
			
			displaying_list.addElement(id);
			displaying_list.addElement(name);
			displaying_list.addElement(price);
			model.addRow(displaying_list);
		}//end for
	}//end snow data in table
}//end class