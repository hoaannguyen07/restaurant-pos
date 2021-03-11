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
import javax.swing.border.MatteBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JRadioButton;

import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Action;

public class cardtype extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private final JLabel card_type_text = new JLabel("Choose card type");
	private JPanel contentPane;
	private final JLabel card_carrier_text = new JLabel("Choose card carrier");
	@SuppressWarnings("rawtypes")
	private JComboBox card_carrier_combobox;
	private final JLabel card_number_text = new JLabel("Enter card number");
	private final JLabel expr_date_text = new JLabel("Choose expiration date");
	@SuppressWarnings("rawtypes")
	private JComboBox month_combobox;
	@SuppressWarnings("rawtypes")
	private JComboBox year_combobox;
	private final JLabel security_code_text = new JLabel("Enter security code");
	private final JPanel panel_pay = new JPanel();
	private final JLabel label_pay = new JLabel("PAY");
	private final JLabel lblNewLabel = new JLabel("PAYMENT INFORMATION");
	
	private final JTextField card_num_entry = new JTextField();
	private final JTextField security_code = new JTextField();
	
	private final JLabel card_verify_text = new JLabel("");
	
	private final Action action = new SwingAction();
	
	//query variables
	protected static double price; 
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
					cardtype frame = new cardtype(new DataHelper());
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
	public cardtype(double current_price) {
		security_code.setBounds(299, 237, 46, 20);
		security_code.setColumns(10);
		card_num_entry.setBounds(299, 130, 130, 20);
		card_num_entry.setColumns(16);
		price = current_price;
		System.out.println(price);
		initGUI();
	}
	
	/**
	 * @wbp.parser.constructor
	 */
	public cardtype(DataHelper api) {
		security_code.setBounds(299, 237, 46, 20);
		security_code.setColumns(10);
		card_num_entry.setBounds(299, 130, 218, 26);
		card_num_entry.setColumns(16);
		
		this.api_connection = api;
		
		System.out.println("API name in cardtype constructor: " + this.api_connection.getFirst_name());
		
		initGUI();
	}
	
    public class SwingAction extends AbstractAction {
        private static final long serialVersionUID = 1L;
        public SwingAction() {
            putValue(NAME, "Back");
            putValue(SHORT_DESCRIPTION, "Heading back to customer option menu.");
        }
        public void actionPerformed(ActionEvent e) {
        }
    }
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void initGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 400);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(70, 130, 180));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		String[] card_types = { "Credit", "Debit"};
		JComboBox comboBox = new JComboBox(card_types);
		comboBox.setBounds(71, 136, 111, 33);
		contentPane.add(comboBox);
		int card_type_choice = comboBox.getSelectedIndex(); //saving the boolean option
		
		card_type_text.setBounds(71, 109, 130, 26);
		contentPane.add(card_type_text);
		
		card_carrier_text.setBounds(71, 180, 140, 26);
		contentPane.add(card_carrier_text);
		
		card_carrier_combobox = new JComboBox(new Object[]{"Mastercard", "Visa"});
		card_carrier_combobox.setBounds(71, 208, 130, 33);
		contentPane.add(card_carrier_combobox);
		String card_carrier = card_carrier_combobox.getSelectedItem().toString();
		
		card_number_text.setBounds(299, 109, 130, 26);
		contentPane.add(card_number_text);
		
		expr_date_text.setBounds(299, 155, 158, 26);
		contentPane.add(expr_date_text);
		
		month_combobox = new JComboBox(new Object[]{"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"});
		month_combobox.setBounds(299, 180, 72, 26);
		contentPane.add(month_combobox);
		month = month_combobox.getSelectedItem().toString();
		
		year_combobox = new JComboBox(new Object[]{"2021", "2022", "2023", "2024", "2025"});
		year_combobox.setBounds(373, 180, 84, 26);
		contentPane.add(year_combobox);
		year = year_combobox.getSelectedItem().toString();
		
		expiration_date = month + "-" + year;
		
		security_code_text.setBounds(299, 211, 140, 26);
		contentPane.add(security_code_text);
		
		panel_pay.setForeground(new Color(0, 0, 0));
		panel_pay.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 255)));
		panel_pay.setBackground(new Color(255, 255, 255));
		panel_pay.setBounds(185, 300, 149, 54);
		contentPane.add(panel_pay);
		
		label_pay.setFont(new Font("Arial Black", Font.BOLD, 30));
		label_pay.setHorizontalAlignment(SwingConstants.CENTER);
		panel_pay.add(label_pay);
		
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 40));
		lblNewLabel.setBounds(10, 11, 518, 62);
		contentPane.add(lblNewLabel);
		
		contentPane.add(card_num_entry);
		contentPane.add(security_code);
		
		card_verify_text.setBounds(137, 78, 299, 16);
		contentPane.add(card_verify_text);
		
		JButton button_back = new JButton("Back");
        button_back.setAction(action);
        button_back.setBounds(20, 66, 111, 26);
        contentPane.add(button_back);
        
        button_back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(arg0.getSource() == button_back) {
					customerOptionMenu view_cust_option = new customerOptionMenu(api_connection);
					view_cust_option.setVisible(true);
					dispose();
				}
			}
		});
		
		panel_pay.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				//save these to their own variables.
				sec_code_str = security_code.getText().toString();
				card_num = card_num_entry.getText().toString();
				
				System.out.println("Security code: " + sec_code_str);
				System.out.println("Card num: " + card_num);
				
				if (security_code.getText().equals("") || card_num_entry.getText().equals("")) {
					card_verify_text.setText("Please enter the required fields.");
				} else if ((sec_code_str.length() != 3) //not a three-character code
					|| (card_num.length() != 16)) {
					card_verify_text.setText("The security code or card number is not valid.");
				}
				else {
					Boolean sign_in_status = api_connection.verifyPayment(card_num_entry, security_code, card_type_choice, card_carrier, expiration_date);
					
					if (sign_in_status == false) {
						card_verify_text.setText("Invalid card.");
						JLabel new_user_pop_up = new JLabel("Are you a new user?");
						new_user_pop_up.setBounds(368, 271, 140, 16);
						contentPane.add(new_user_pop_up);
						
						JRadioButton yes_button = new JRadioButton("Yes");
						yes_button.setBounds(373, 300, 141, 23);
						contentPane.add(yes_button);
						
						JRadioButton no_button = new JRadioButton("No");
						no_button.setBounds(373, 331, 141, 23);
						contentPane.add(no_button);
						
						if (yes_button.isSelected()) {
							api_connection.createPayment(card_num_entry, security_code, card_type_choice, card_carrier, expiration_date);
						} else if (no_button.isSelected()) {
							card_verify_text.setText("Invalid card, try again.");
						}

						
					} else if (sign_in_status == true) {
						card_verify_text.setText("Security code: " + security_code.getText() 
							+ " and card number: " + card_num_entry.getText());
						FinishPayment view_payment = new FinishPayment(price);
						view_payment.setVisible(true);
						dispose();
					}
				}
			}
		});
		
	}
}
