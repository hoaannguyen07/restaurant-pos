package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JRadioButton;

import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Action;
import javax.swing.ButtonGroup;

public class Payment_Info extends JFrame {

	private static final long serialVersionUID = 1L;
	
	//JLabels
	private final JLabel card_type_text = new JLabel("Choose card type");
	private final JLabel card_carrier_text = new JLabel("Choose card carrier");
	private final JLabel card_number_text = new JLabel("Enter card number");
	private final JLabel expr_date_text = new JLabel("Choose expiration date");
	private final JLabel security_code_text = new JLabel("Enter security code");
	private final JLabel label_pay = new JLabel("PAY");
	private final JLabel label_payment_info = new JLabel("PAYMENT INFORMATION");
	private final JLabel card_verify_text = new JLabel("");
	private final JLabel new_user_pop_up = new JLabel("");
	
	//JPanels
	private JPanel contentPane;
	private final JPanel panel_pay = new JPanel();
	
	//JComboBoxes
	@SuppressWarnings("rawtypes")
	private JComboBox card_carrier_combobox;
	@SuppressWarnings("rawtypes")
	private JComboBox month_combobox;
	@SuppressWarnings("rawtypes")
	private JComboBox year_combobox;
	
	//JTextFields
	private final JTextField card_num_entry = new JTextField();
	private final JTextField security_code = new JTextField();
	
	//Action for back button
	private final Action action = new SwingAction();
	
	//query variables
	protected static double total_cost; 
	private static String year;
	private static String month;
	private static String sec_code_str;
	private static String card_num;
	private static String expiration_date;
	
	DataHelper api_connection;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Payment_Info frame = new Payment_Info(new DataHelper());
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}//end try catch
			}//end run()
		}); //end invokeLater
	}//end main
	
	/**
	 * @wbp.parser.constructor
	 */
	public Payment_Info(DataHelper api) {
		security_code.setBounds(298, 252, 46, 26);
		security_code.setColumns(10);
		card_num_entry.setBounds(298, 145, 218, 26);
		card_num_entry.setColumns(16);
		
		this.api_connection = api;
		total_cost = this.api_connection.cart_helper.getTotal_cost();
		
		System.out.println("API name in cardtype constructor: " + this.api_connection.getFirst_name());
		
		initGUI();
	}//end constructor
	
    public class SwingAction extends AbstractAction {
        private static final long serialVersionUID = 1L;
        public SwingAction() {
            putValue(NAME, "Back");
            putValue(SHORT_DESCRIPTION, "Heading back to customer option menu.");
        }//end swing action
        public void actionPerformed(ActionEvent e) {
        }//end action performed
    }//end swing action extends abstract action
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void initGUI() {
		//set up the frame.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 400);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 153, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//card carrier combobox panel
		String[] card_types = { "Credit", "Debit"};
		JComboBox comboBox = new JComboBox(card_types);
		comboBox.setBounds(70, 151, 111, 33);
		contentPane.add(comboBox);
		int card_type_choice = comboBox.getSelectedIndex(); //saving the boolean option
		
		//credit or debit card type text
		card_type_text.setBounds(70, 124, 130, 26);
		contentPane.add(card_type_text);
		
		//card carrier text and combobox set-up
		card_carrier_text.setBounds(70, 195, 140, 26);
		contentPane.add(card_carrier_text);
		
		card_carrier_combobox = new JComboBox(new Object[]{"Mastercard", "Visa"});
		card_carrier_combobox.setBounds(70, 223, 130, 33);
		contentPane.add(card_carrier_combobox);
		
		//card number of the suer
		card_number_text.setBounds(298, 124, 130, 26);
		contentPane.add(card_number_text);
		
		//the expiration date of the card, including month and year
		expr_date_text.setBounds(298, 170, 158, 26);
		contentPane.add(expr_date_text);
		
		month_combobox = new JComboBox(new Object[]{"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"});
		month_combobox.setBounds(298, 195, 72, 26);
		contentPane.add(month_combobox);
		
		year_combobox = new JComboBox(new Object[]{"2021", "2022", "2023", "2024", "2025"});
		year_combobox.setBounds(372, 195, 84, 26);
		contentPane.add(year_combobox);
		
		//security code of the card set-up
		security_code_text.setBounds(298, 226, 140, 26);
		contentPane.add(security_code_text);
		
		//payment panel set-up
		panel_pay.setForeground(new Color(255, 255, 255));
		panel_pay.setBorder(null);
		panel_pay.setBackground(new Color(153, 0, 0));
		panel_pay.setBounds(194, 300, 149, 54);
		contentPane.add(panel_pay);
		label_pay.setForeground(new Color(255, 255, 255));
		label_pay.setFont(new Font("Arial Black", Font.BOLD, 30));
		label_pay.setHorizontalAlignment(SwingConstants.CENTER);
		panel_pay.add(label_pay);
		
		//payment information label set-up
		label_payment_info.setHorizontalAlignment(SwingConstants.CENTER);
		label_payment_info.setFont(new Font("Arial", Font.BOLD, 40));
		label_payment_info.setBounds(10, 36, 518, 62);
		contentPane.add(label_payment_info);
		
		//content panes set up from when they were instantiated in the constructor
		contentPane.add(card_num_entry);
		contentPane.add(security_code);
		
		//verification text pop-up set up
		card_verify_text.setBounds(137, 78, 299, 16);
		contentPane.add(card_verify_text);
		
		//back button
		JButton button_back = new JButton("Back");
		button_back.setForeground(new Color(255, 255, 255));
		button_back.setFont(new Font("Arial", Font.BOLD, 15));
		button_back.setBackground(new Color(153, 0, 0));
        button_back.setAction(action);
        button_back.setBounds(10, 11, 72, 26);
        contentPane.add(button_back);
        
        //new user question
		new_user_pop_up.setBounds(107, 92, 140, 16);
		contentPane.add(new_user_pop_up);
		new_user_pop_up.setText("Are you a new user?");
        
		//the back button's action listener
        button_back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(arg0.getSource() == button_back) {
					customerOptionMenu view_cust_option = new customerOptionMenu(api_connection);
					view_cust_option.setVisible(true);
					dispose();
				}//endif
			}//end actionPerformed
		});//end addActionListener
		
        //buttons for new user selection: yes and no
		JRadioButton yes_button = new JRadioButton("Yes");
		yes_button.setBounds(227, 89, 46, 23);
		contentPane.add(yes_button);
		
		JRadioButton no_button = new JRadioButton("No");
		no_button.setBounds(317, 89, 46, 23);
		contentPane.add(no_button);
		
		ButtonGroup group = new ButtonGroup();
		group.add(yes_button);
		group.add(no_button);
		
		//mouse listener for if the user is ready to pay or not
		panel_pay.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				//save these to their own variables.
				sec_code_str = security_code.getText().toString();
				card_num = card_num_entry.getText().toString();
				
				System.out.println("Security code: " + sec_code_str);
				System.out.println("Card num: " + card_num);
				
				//combining the month and the year to get a valid expiration date
				month = month_combobox.getSelectedItem().toString();
				year = year_combobox.getSelectedItem().toString();
				expiration_date = month + "-" + year;
				String card_carrier = card_carrier_combobox.getSelectedItem().toString();
				
				//determining the validity of the card in question
				if (security_code.getText().equals("") || card_num_entry.getText().equals("")) {
					card_verify_text.setText("Please enter the required fields.");
				} else if ((sec_code_str.length() != 3) //not a three-character code
					|| (card_num.length() != 16)) {
					card_verify_text.setText("The security code or card number is not valid.");
				}//end if/elseif
				else {
					//determining if the card exists or not
					Boolean sign_in_status = api_connection.verifyPayment(card_num_entry, security_code, card_type_choice, card_carrier, expiration_date);
					
					//return of the boolean is false, which meant the card doesn't exist
					if (sign_in_status == false) {
						//however, the user is new
						if (yes_button.isSelected()) {
							api_connection.createPayment(card_num_entry, security_code, card_type_choice, card_carrier, expiration_date);
							sign_in_status = true;
						} else if (no_button.isSelected()) { //the user doesn't exist
							card_verify_text.setText("Invalid card, try again.");
						}//end if/elseif
					} if (sign_in_status == true) { //the user does exist in the system
						card_verify_text.setText("Security code: " + security_code.getText() 
							+ " and card number: " + card_num_entry.getText());
						FinishPayment view_payment = new FinishPayment(total_cost, api_connection);
						view_payment.setVisible(true);
						dispose();
					}//end if
				}//end else
			}//end mouseClicked
		});//end addMouseListener
	}//end initGUI
}//end class