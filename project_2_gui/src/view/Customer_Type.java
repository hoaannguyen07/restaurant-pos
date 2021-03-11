package view;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Action;
import javax.swing.JButton;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hoaan
 */
public class Customer_Type extends javax.swing.JFrame {

    private static final long serialVersionUID = 1L;
	/**
     * Creates new form Customer_Type
     */
	
	DataHelper api_connection;
	
	private final Action action = new SwingAction();
	
    public Customer_Type() {
        initComponents();
    }
    
    public Customer_Type(DataHelper api) {
    	this.api_connection = api;
    	initComponents();
    }
    
    public class SwingAction extends AbstractAction {
        private static final long serialVersionUID = 1L;
        public SwingAction() {
            putValue(NAME, "Back");
            putValue(SHORT_DESCRIPTION, "Heading back to customer type menu.");
        }
        public void actionPerformed(ActionEvent e) {
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        panel_layout = new javax.swing.JPanel();
        panel_customer = new javax.swing.JPanel();
        
        label_customer = new javax.swing.JLabel();
        label_customer.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		newCustomer view_new_cust = new newCustomer(api_connection); 
        		view_new_cust.setVisible(true);
        		dispose();
        	}
        });
        panel_manager = new javax.swing.JPanel();
        label_manager = new javax.swing.JLabel();
        label_manager.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		Cust_Sign_In view_return_cust = new Cust_Sign_In(api_connection);
        		view_return_cust.setVisible(true);
        		dispose();
        	}
        });
        label_welcome1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panel_layout.setBackground(new java.awt.Color(0, 139, 139));
        panel_layout.setForeground(new java.awt.Color(51, 51, 255));

        panel_customer.setBackground(new java.awt.Color(0, 102, 102));
        panel_customer.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        label_customer.setFont(new java.awt.Font("Tahoma", 1, 40)); // NOI18N
        label_customer.setForeground(new java.awt.Color(255, 255, 255));
        label_customer.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_customer.setText("NEW CUSTOMER");
        label_customer.setToolTipText("");
        label_customer.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout panel_customerLayout = new javax.swing.GroupLayout(panel_customer);
        panel_customer.setLayout(panel_customerLayout);
        panel_customerLayout.setHorizontalGroup(
            panel_customerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_customerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label_customer, javax.swing.GroupLayout.DEFAULT_SIZE, 346, Short.MAX_VALUE)
                .addContainerGap())
        );
        panel_customerLayout.setVerticalGroup(
            panel_customerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_customerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label_customer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panel_manager.setBackground(new java.awt.Color(0, 102, 102));
        panel_manager.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        label_manager.setFont(new java.awt.Font("Tahoma", 1, 40)); // NOI18N
        label_manager.setForeground(new java.awt.Color(255, 255, 255));
        label_manager.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_manager.setText("RETURNING CUSTOMER");
        label_manager.setToolTipText("");
        label_manager.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout panel_managerLayout = new javax.swing.GroupLayout(panel_manager);
        panel_manager.setLayout(panel_managerLayout);
        panel_managerLayout.setHorizontalGroup(
            panel_managerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_managerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label_manager, javax.swing.GroupLayout.DEFAULT_SIZE, 497, Short.MAX_VALUE)
                .addContainerGap())
        );
        panel_managerLayout.setVerticalGroup(
            panel_managerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_managerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label_manager, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        label_welcome1.setBackground(new java.awt.Color(255, 255, 255));
        label_welcome1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        label_welcome1.setForeground(new java.awt.Color(255, 255, 255));
        label_welcome1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_welcome1.setText("Welcome to A.N.G.S.T!");

        javax.swing.GroupLayout panel_layoutLayout = new javax.swing.GroupLayout(panel_layout);
        panel_layout.setLayout(panel_layoutLayout);
        panel_layoutLayout.setHorizontalGroup(
            panel_layoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_layoutLayout.createSequentialGroup()
                .addGap(97, 97, 97)
                .addComponent(label_welcome1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_layoutLayout.createSequentialGroup()
                .addContainerGap(40, Short.MAX_VALUE)
                .addGroup(panel_layoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_layoutLayout.createSequentialGroup()
                        .addComponent(panel_manager, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_layoutLayout.createSequentialGroup()
                        .addComponent(panel_customer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(110, 110, 110))))
        );
        panel_layoutLayout.setVerticalGroup(
            panel_layoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_layoutLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(label_welcome1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(panel_customer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                .addComponent(panel_manager, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel_layout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel_layout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        
        JButton button_back = new JButton("Back");
        button_back.setAction(action);
        button_back.setBounds(240, 350, 111, 26);
        panel_layout.add(button_back);
        
        button_back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(arg0.getSource() == button_back) {
					User_Type view_cust_option = new User_Type(api_connection);
					view_cust_option.setVisible(true);
					dispose();
				}
			}
		});

        pack();
    }// </editor-fold>                        

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Customer_Type.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Customer_Type.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Customer_Type.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Customer_Type.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Customer_Type().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JLabel label_customer;
    private javax.swing.JLabel label_manager;
    private javax.swing.JLabel label_welcome1;
    private javax.swing.JPanel panel_customer;
    private javax.swing.JPanel panel_layout;
    private javax.swing.JPanel panel_manager;
    // End of variables declaration                   
}