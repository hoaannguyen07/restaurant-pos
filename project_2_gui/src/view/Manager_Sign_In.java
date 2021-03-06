package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Color;

/**
 *
 * @author hoaan
 */
public class Manager_Sign_In extends javax.swing.JFrame {

    /**
     * Creates new form Manager_Sign_In
     */
	DataHelper api_connection;
	
    public Manager_Sign_In() {
        initComponents();
    }
    
    public Manager_Sign_In(DataHelper api) {
        initComponents();
        this.api_connection = api;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        panel_layout = new javax.swing.JPanel();
        label_welcome1 = new javax.swing.JLabel();
        panel_user = new javax.swing.JPanel();
        label_username = new javax.swing.JTextField();
        panel_pass = new javax.swing.JPanel();
        label_pass = new javax.swing.JPasswordField();
        panel_login = new javax.swing.JPanel();
        label_login = new javax.swing.JLabel();
        label_login_msg = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panel_layout.setBackground(new java.awt.Color(0, 139, 139));

        label_welcome1.setBackground(new java.awt.Color(255, 255, 255));
        label_welcome1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        label_welcome1.setForeground(new java.awt.Color(255, 255, 255));
        label_welcome1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_welcome1.setText("Manager Sign In");
        label_welcome1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        panel_user.setBackground(new java.awt.Color(255, 255, 255));
        panel_user.setPreferredSize(new java.awt.Dimension(250, 40));
        panel_user.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                panel_userFocusGained(evt);
            }
        });

        label_username.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        label_username.setText("Username");
        label_username.setBorder(null);
        label_username.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                label_usernameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                label_usernameFocusLost(evt);
            }
        });
        label_username.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                label_usernameActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel_userLayout = new javax.swing.GroupLayout(panel_user);
        panel_user.setLayout(panel_userLayout);
        panel_userLayout.setHorizontalGroup(
            panel_userLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(label_username, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        panel_userLayout.setVerticalGroup(
            panel_userLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(label_username, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        panel_pass.setBackground(new java.awt.Color(255, 255, 255));
        panel_pass.setPreferredSize(new java.awt.Dimension(250, 40));

        label_pass.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        label_pass.setText("Password");
        label_pass.setBorder(null);
        label_pass.setEchoChar((char)0);
        label_pass.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                label_passFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                label_passFocusLost(evt);
            }
        });

        javax.swing.GroupLayout panel_passLayout = new javax.swing.GroupLayout(panel_pass);
        panel_pass.setLayout(panel_passLayout);
        panel_passLayout.setHorizontalGroup(
            panel_passLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(label_pass)
        );
        panel_passLayout.setVerticalGroup(
            panel_passLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(label_pass, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        panel_login.setBackground(new java.awt.Color(0, 51, 51));
        panel_login.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panel_loginMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panel_loginMouseEntered(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                panel_loginMouseReleased(evt);
            }
        });

        label_login.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        label_login.setForeground(new java.awt.Color(255, 255, 255));
        label_login.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_login.setText("LOG IN");

        javax.swing.GroupLayout panel_loginLayout = new javax.swing.GroupLayout(panel_login);
        panel_login.setLayout(panel_loginLayout);
        panel_loginLayout.setHorizontalGroup(
            panel_loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_loginLayout.createSequentialGroup()
                .addGap(88, 88, 88)
                .addComponent(label_login)
                .addContainerGap(89, Short.MAX_VALUE))
        );
        panel_loginLayout.setVerticalGroup(
            panel_loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_loginLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label_login, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                .addContainerGap())
        );

        label_login_msg.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        label_login_msg.setForeground(new java.awt.Color(204, 0, 51));
        label_login_msg.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout panel_layoutLayout = new javax.swing.GroupLayout(panel_layout);
        panel_layout.setLayout(panel_layoutLayout);
        panel_layoutLayout.setHorizontalGroup(
            panel_layoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_layoutLayout.createSequentialGroup()
                .addContainerGap(151, Short.MAX_VALUE)
                .addComponent(label_welcome1)
                .addGap(148, 148, 148))
            .addGroup(panel_layoutLayout.createSequentialGroup()
                .addGap(174, 174, 174)
                .addGroup(panel_layoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panel_user, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panel_pass, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panel_login, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(label_login_msg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panel_layoutLayout.setVerticalGroup(
            panel_layoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_layoutLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(label_welcome1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addComponent(panel_user, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(panel_pass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addComponent(label_login_msg)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panel_login, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36))
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

        pack();
    }// </editor-fold>                        

    private void label_usernameActionPerformed(java.awt.event.ActionEvent evt) {                                               
        // TODO add your handling code here:
    }                                              

    private void panel_userFocusGained(java.awt.event.FocusEvent evt) {                                       
        // TODO add your handling code here:
    }                                      

    private void label_usernameFocusGained(java.awt.event.FocusEvent evt) {                                           
        // TODO add your handling code here:
        if (label_username.getText().equals("Username"))
        {
            label_username.setText("");
        } else
        {
            label_username.selectAll();
        }
    }                                          

    @SuppressWarnings("deprecation")
	private void label_passFocusGained(java.awt.event.FocusEvent evt) {                                       
        // TODO add your handling code here:
        if (label_pass.getText().equals("Password"))
        {
            label_pass.setEchoChar('\u25cf');
            label_pass.setText("");
        } else
        {
            label_pass.selectAll();
        }
        
    }                                      

    private void label_usernameFocusLost(java.awt.event.FocusEvent evt) {                                         
        // TODO add your handling code here:
        if (label_username.getText().equals(""))
        {
            label_username.setText("Username");
        }
    }                                        

    @SuppressWarnings("deprecation")
	private void label_passFocusLost(java.awt.event.FocusEvent evt) {                                     
        // TODO add your handling code here:
        if (label_pass.getText().equals(""))
        {
            label_pass.setText("Password");
            label_pass.setEchoChar((char)0); // can see the text now
        }
    }                                    

    @SuppressWarnings("deprecation")
	private void panel_loginMouseClicked(java.awt.event.MouseEvent evt) {                                         
        // TODO add your handling code here:
        
        // change color of panel when clicked
        panel_login.setBackground(new Color (0,35,34));

        
        // haven't entered one or both of the fields
        if (label_username.getText().equals("") || label_username.getText().equals("Username") ||
                    label_pass.getText().equals("") || label_pass.getText().equals("Password"))
        {
            label_login_msg.setText("Please input all requirements!");
        }
        // correct username and password means go to main screen with all customer functionality
        else if (api_connection.verify_manager(label_username, label_pass) == 2)
        {
            label_login_msg.setText("Successful Sign In");
        }
        // incorrect username/password
        else
        {
            label_login_msg.setText("Incorrect Username or Password");
        }
    }                                        

    private void panel_loginMouseEntered(java.awt.event.MouseEvent evt) {                                         
        // TODO add your handling code here:
        panel_login.setBackground(new Color (0,35,34));
    }                                        

    private void panel_loginMouseReleased(java.awt.event.MouseEvent evt) {                                          
        // TODO add your handling code here:
        panel_login.setBackground(new Color (0,51,51));
    }                                         

    
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
            java.util.logging.Logger.getLogger(Manager_Sign_In.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Manager_Sign_In.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Manager_Sign_In.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Manager_Sign_In.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Manager_Sign_In().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JLabel label_login;
    private javax.swing.JLabel label_login_msg;
    private javax.swing.JPasswordField label_pass;
    private javax.swing.JTextField label_username;
    private javax.swing.JLabel label_welcome1;
    private javax.swing.JPanel panel_layout;
    private javax.swing.JPanel panel_login;
    private javax.swing.JPanel panel_pass;
    private javax.swing.JPanel panel_user;
    // End of variables declaration                   
}
