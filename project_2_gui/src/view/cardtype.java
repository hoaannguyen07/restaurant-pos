package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JTextArea;

public class cardtype extends JFrame {

	private final JLabel card_type_text = new JLabel("Choose card type");
	private JPanel contentPane;
	private final JLabel card_carrier_text = new JLabel("Choose card carrier");
	private JComboBox card_carrier_combobox;
	private final JLabel card_number_text = new JLabel("Enter card number");
	private final JLabel expr_date_text = new JLabel("Choose expiration date");
	private JComboBox month_combobox;
	private JComboBox year_combobox;
	private final JLabel security_code_text = new JLabel("Enter security code");
	private JTextArea textArea_1;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					cardtype frame = new cardtype();
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
	public cardtype() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		String[] card_types = { "Credit", "Debit"};
		JComboBox comboBox = new JComboBox(card_types);
		comboBox.setBounds(53, 96, 130, 40);
		contentPane.add(comboBox);
		
		card_type_text.setBounds(43, 69, 130, 26);
		contentPane.add(card_type_text);
		
		card_carrier_text.setBounds(44, 138, 140, 26);
		contentPane.add(card_carrier_text);
		
		card_carrier_combobox = new JComboBox(new Object[]{"Mastercard", "Visa"});
		card_carrier_combobox.setBounds(53, 164, 130, 40);
		contentPane.add(card_carrier_combobox);
		
		card_number_text.setBounds(196, 45, 130, 26);
		contentPane.add(card_number_text);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(206, 74, 168, 16);
		contentPane.add(textArea);
		
		expr_date_text.setBounds(196, 102, 158, 26);
		contentPane.add(expr_date_text);
		
		month_combobox = new JComboBox(new Object[]{"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"});
		month_combobox.setBounds(196, 121, 72, 40);
		contentPane.add(month_combobox);
		
		year_combobox = new JComboBox(new Object[]{"2021", "2022", "2023", "2024", "2025"});
		year_combobox.setBounds(263, 121, 91, 40);
		contentPane.add(year_combobox);
		
		security_code_text.setBounds(196, 161, 140, 26);
		contentPane.add(security_code_text);
		
		textArea_1 = new JTextArea();
		textArea_1.setBounds(206, 188, 72, 16);
		contentPane.add(textArea_1);
		
	}
}
