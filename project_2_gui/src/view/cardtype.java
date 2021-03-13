package view;

//import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JTextField;
//import javax.swing.JTextArea;
//import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
	private final JTextField textField = new JTextField();
	private final JTextField textField_1 = new JTextField();
	protected static double price; 
	
	DataHelper api_connection;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					cardtype frame = new cardtype(price);
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
		textField_1.setBounds(299, 237, 46, 20);
		textField_1.setColumns(10);
		textField.setBounds(299, 130, 130, 20);
		textField.setColumns(16);
		price = current_price;
		System.out.println(price);
		initGUI();
	}
	
	/**
	 * @wbp.parser.constructor
	 */
	public cardtype(DataHelper api) {
		textField_1.setBounds(299, 237, 46, 20);
		textField_1.setColumns(10);
		textField.setBounds(299, 130, 130, 20);
		textField.setColumns(16);
		
		this.api_connection = api;
		
		initGUI();
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
		
		card_type_text.setBounds(71, 109, 130, 26);
		contentPane.add(card_type_text);
		
		card_carrier_text.setBounds(71, 180, 140, 26);
		contentPane.add(card_carrier_text);
		
		card_carrier_combobox = new JComboBox(new Object[]{"Mastercard", "Visa"});
		card_carrier_combobox.setBounds(71, 208, 111, 33);
		contentPane.add(card_carrier_combobox);
		
		card_number_text.setBounds(299, 109, 130, 26);
		contentPane.add(card_number_text);
		
		expr_date_text.setBounds(299, 155, 158, 26);
		contentPane.add(expr_date_text);
		
		month_combobox = new JComboBox(new Object[]{"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"});
		month_combobox.setBounds(299, 180, 62, 26);
		contentPane.add(month_combobox);
		
		year_combobox = new JComboBox(new Object[]{"2021", "2022", "2023", "2024", "2025"});
		year_combobox.setBounds(373, 180, 84, 26);
		contentPane.add(year_combobox);
		
		security_code_text.setBounds(299, 211, 140, 26);
		contentPane.add(security_code_text);
		panel_pay.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				FinishPayment view_payment = new FinishPayment(price);
				view_payment.setVisible(true);
				dispose();
			}
		});
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
		
		contentPane.add(textField);
		
		contentPane.add(textField_1);
	}
}