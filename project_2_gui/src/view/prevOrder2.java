package view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Map;
import java.util.Vector;
import javax.swing.JLabel;
import javax.swing.JList;

public class prevOrder2 extends JFrame {
	
	private static final long serialVersionUID = 1L;

	DataHelper api_connection;

	private JPanel contentPane;
	private final JLabel lblEntrees = new JLabel("Entrees");
	private final JLabel lblSides = new JLabel("Sides");
	private final JLabel lblBeverage = new JLabel("Beverage");
	private final JLabel lblDesserts = new JLabel("Desserts");
	@SuppressWarnings("rawtypes")
	private JList entreeList = new JList();
	@SuppressWarnings("rawtypes")
	private JList bevList = new JList();
	@SuppressWarnings("rawtypes")
	private JList sideList = new JList();
	@SuppressWarnings("rawtypes")
	private JList dessList = new JList();
	protected static String first;
	protected static String last;
	protected static String user;
	protected static String pass;
	protected static double price;
	private final JButton btnBack = new JButton("Back");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					prevOrder2 frame = new prevOrder2(new DataHelper());
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
	public prevOrder2(DataHelper api) {
		api_connection = api;
		initGUI();
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 451, 457);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		lblEntrees.setBounds(71, 36, 70, 15);
		
		contentPane.add(lblEntrees);
		lblSides.setBounds(283, 36, 70, 15);
		
		contentPane.add(lblSides);
		lblBeverage.setBounds(71, 214, 70, 15);
		
		contentPane.add(lblBeverage);
		lblDesserts.setBounds(283, 214, 70, 15);
		
		contentPane.add(lblDesserts);
		entreeList.setBounds(12, 63, 188, 139);
		
		contentPane.add(entreeList);
		bevList.setBounds(12, 254, 188, 139);
		
		contentPane.add(bevList);
		sideList.setBounds(233, 63, 175, 139);
		
		contentPane.add(sideList);
		dessList.setBounds(233, 254, 175, 141);
		
		contentPane.add(dessList);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(arg0.getSource() == btnBack) {
					customerOptionMenu view_cust_option = new customerOptionMenu(api_connection);
					view_cust_option.setVisible(true);
					dispose();
				}
			}
		});
		btnBack.setBounds(161, 0, 117, 25);
		
		Map<String, Vector<String>> PrevOrderMap = api_connection.prevOrder();
		if (PrevOrderMap != null) {
			entreeList = new JList(PrevOrderMap.get("Entrees"));
			bevList = new JList(PrevOrderMap.get("Beverages"));
			sideList = new JList(PrevOrderMap.get("Sides"));
			dessList = new JList(PrevOrderMap.get("Dessert"));
		}
		
		contentPane.add(btnBack);
	}
}