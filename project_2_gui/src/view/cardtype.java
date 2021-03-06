package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JTextArea;

public class cardtype extends JFrame {

	private JTextField card_type_text;
	private JPanel contentPane;
	private JTextField card_carrier_text;
	private JComboBox card_carrier_combobox;
	private JTextField card_number_text;
	private JTextField expr_date_text;
	private JComboBox month_combobox;
	private JComboBox year_combobox;
	private JTextField textField;
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
		comboBox.setBounds(6, 26, 130, 40);
		contentPane.add(comboBox);
		
		card_type_text = new JTextField("Choose card type");
		card_type_text.setBounds(6, 6, 130, 26);
		contentPane.add(card_type_text);
		card_type_text.setColumns(10);
		
		card_carrier_text = new JTextField("Choose card carrier");
		card_carrier_text.setColumns(10);
		card_carrier_text.setBounds(6, 65, 140, 26);
		contentPane.add(card_carrier_text);
		
		card_carrier_combobox = new JComboBox(new Object[]{"Mastercard", "Visa"});
		card_carrier_combobox.setBounds(6, 89, 130, 40);
		contentPane.add(card_carrier_combobox);
		
		card_number_text = new JTextField("Enter card number");
		card_number_text.setBounds(196, 6, 130, 26);
		contentPane.add(card_number_text);
		card_number_text.setColumns(10);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(206, 37, 168, 16);
		contentPane.add(textArea);
		
		expr_date_text = new JTextField("Choose expiration date");
		expr_date_text.setColumns(10);
		expr_date_text.setBounds(196, 65, 158, 26);
		contentPane.add(expr_date_text);
		
		month_combobox = new JComboBox(new Object[]{"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"});
		month_combobox.setBounds(196, 89, 72, 40);
		contentPane.add(month_combobox);
		
		year_combobox = new JComboBox(new Object[]{"2021", "2022", "2023", "2024", "2025"});
		year_combobox.setBounds(261, 89, 91, 40);
		contentPane.add(year_combobox);
		
		textField = new JTextField("Enter security code");
		textField.setColumns(10);
		textField.setBounds(196, 128, 140, 26);
		contentPane.add(textField);
		
		textArea_1 = new JTextArea();
		textArea_1.setBounds(206, 166, 72, 16);
		contentPane.add(textArea_1);
		
	}
}
