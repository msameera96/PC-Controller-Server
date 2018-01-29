/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pc.controller.server;

import java.io.BufferedReader;
import java.net.URL;
import java.net.*;
import java.io.*;
import java.util.Enumeration;
import javax.swing.JOptionPane;
//import javax.swing.JTextField;

/**
 *
 * @author Sameer
 */

public class MainMenu extends javax.swing.JFrame {
    public static String conStatus ="Not Connected";
    public static boolean  istrue= false;

    /**
     * Creates new form MainMenu
     */
    InetAddress addr;
    
    
    int portNo = 4444;
    String ipAddress ="";
    ServerConnection con;
    DatabaseViewerInternalFrame dbIF;
    DeviceDetailInternalFrame ddIF;
    LoginForm loginForm;
    public MainMenu() throws IOException {
        initComponents();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        isLoggedInTxtField.setText("No admin has logged in");
        setTitle("PC Controller Server");
       setIpAddress();
        con = new ServerConnection();
        con.connectionEstablishing(portNo);
        
        
    }
    void setIpAddress()
    {
        String ip="";
        
       
       try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            int position =1;
            while (interfaces.hasMoreElements()) {
                NetworkInterface iface = interfaces.nextElement();
                //This filters out the interfaces
                // filters out 127.0.0.1 and inactive interfaces
                if (iface.isLoopback() || !iface.isUp())
                    continue;
                
                Enumeration<InetAddress> addresses = iface.getInetAddresses();
                while(addresses.hasMoreElements()) {
                    InetAddress addr = addresses.nextElement();
                    position++;
                   //if(position ==4)
                 if((addr.getHostAddress().charAt(1) == '7' || addr.getHostAddress().charAt(1) == '9') && (addr.getHostAddress().charAt(2) == '2'))
                    {
                        ipAddressTxtField.setText(iface.getDisplayName()+" : "+addr.getHostAddress());
                       
                        break;
                   }
                    
                }
                
                
            }
        } catch (SocketException e) {
            throw new RuntimeException(e);
            
        }
    
    
            
        
            
            //Global IP address
        /*URL whatismyip = new URL("http://checkip.amazonaws.com");
        BufferedReader in = new BufferedReader(new InputStreamReader(whatismyip.openStream()));
        String ip = in.readLine();
       JOptionPane.showInputDialog(null,""+ip);*/
        
        
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        topPanel = new javax.swing.JPanel();
        mainTitleLabel = new javax.swing.JLabel();
        mainBackgroundPanel = new javax.swing.JPanel();
        downPanel = new javax.swing.JPanel();
        ipAddressLabel = new javax.swing.JLabel();
        ipAddressTxtField = new javax.swing.JTextField();
        connectionStatusLabel1 = new javax.swing.JLabel();
        conStatusTextField = new javax.swing.JTextField();
        dbPanel = new javax.swing.JPanel();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        displayDBBtn = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        isLoggedInTxtField = new javax.swing.JTextField();
        loggedOutBtn = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        topPanel.setBackground(new java.awt.Color(0, 255, 127));

        mainTitleLabel.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        mainTitleLabel.setText("PC CONTROLLER SERVER");

        javax.swing.GroupLayout topPanelLayout = new javax.swing.GroupLayout(topPanel);
        topPanel.setLayout(topPanelLayout);
        topPanelLayout.setHorizontalGroup(
            topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topPanelLayout.createSequentialGroup()
                .addGap(216, 216, 216)
                .addComponent(mainTitleLabel)
                .addContainerGap(216, Short.MAX_VALUE))
        );
        topPanelLayout.setVerticalGroup(
            topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mainTitleLabel)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        mainTitleLabel.getAccessibleContext().setAccessibleName("pcControllerLabel");

        mainBackgroundPanel.setBackground(new java.awt.Color(128, 128, 128));

        downPanel.setBackground(new java.awt.Color(255, 255, 255));

        ipAddressLabel.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        ipAddressLabel.setText("IP Address");
        ipAddressLabel.setInheritsPopupMenu(false);
        ipAddressLabel.setName(""); // NOI18N

        ipAddressTxtField.setEditable(false);

        connectionStatusLabel1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        connectionStatusLabel1.setText("Connection Status");

        conStatusTextField.setEditable(false);
        conStatusTextField.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        conStatusTextField.setText(" ");

        dbPanel.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 880, Short.MAX_VALUE)
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 352, Short.MAX_VALUE)
        );

        displayDBBtn.setText("Display all Logs");
        displayDBBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                displayDBBtnActionPerformed(evt);
            }
        });

        jButton1.setText("Display All Devices details");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        isLoggedInTxtField.setEditable(false);
        isLoggedInTxtField.setForeground(new java.awt.Color(0, 0, 255));

        loggedOutBtn.setText("Logged Out");
        loggedOutBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loggedOutBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout dbPanelLayout = new javax.swing.GroupLayout(dbPanel);
        dbPanel.setLayout(dbPanelLayout);
        dbPanelLayout.setHorizontalGroup(
            dbPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1)
            .addGroup(dbPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(isLoggedInTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(loggedOutBtn)
                .addGap(18, 18, 18)
                .addComponent(displayDBBtn)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        dbPanelLayout.setVerticalGroup(
            dbPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dbPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jDesktopPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(dbPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(displayDBBtn)
                    .addComponent(jButton1)
                    .addComponent(jLabel1)
                    .addComponent(isLoggedInTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(loggedOutBtn))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout downPanelLayout = new javax.swing.GroupLayout(downPanel);
        downPanel.setLayout(downPanelLayout);
        downPanelLayout.setHorizontalGroup(
            downPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(downPanelLayout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(downPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(connectionStatusLabel1)
                    .addComponent(ipAddressLabel))
                .addGap(18, 18, 18)
                .addGroup(downPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(ipAddressTxtField)
                    .addComponent(conStatusTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 629, Short.MAX_VALUE))
                .addContainerGap(60, Short.MAX_VALUE))
            .addComponent(dbPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        downPanelLayout.setVerticalGroup(
            downPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(downPanelLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(downPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ipAddressLabel)
                    .addComponent(ipAddressTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(downPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(connectionStatusLabel1)
                    .addComponent(conStatusTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(dbPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        ipAddressLabel.getAccessibleContext().setAccessibleName("");

        javax.swing.GroupLayout mainBackgroundPanelLayout = new javax.swing.GroupLayout(mainBackgroundPanel);
        mainBackgroundPanel.setLayout(mainBackgroundPanelLayout);
        mainBackgroundPanelLayout.setHorizontalGroup(
            mainBackgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainBackgroundPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(downPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        mainBackgroundPanelLayout.setVerticalGroup(
            mainBackgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainBackgroundPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(downPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(topPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(mainBackgroundPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(topPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(mainBackgroundPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void displayDBBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_displayDBBtnActionPerformed
        // TODO add your handling code here:
        
        if(!istrue)
        loginForm = new LoginForm();
        if(istrue){
        isLoggedInTxtField.setText("Admin is logged");
        dbIF = new DatabaseViewerInternalFrame();
        jDesktopPane1.add(dbIF);
        dbIF.show();
        dbIF.displayAllLogs();}
    }//GEN-LAST:event_displayDBBtnActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if(!istrue)
        loginForm = new LoginForm();
        if(istrue){
            isLoggedInTxtField.setText("Admin is logged");
        ddIF = new DeviceDetailInternalFrame();
        
        jDesktopPane1.add(ddIF);
        ddIF.show();
        ddIF.showDeviceDetails();
        }
        
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void loggedOutBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loggedOutBtnActionPerformed
        // TODO add your handling code here:
        isLoggedInTxtField.setText("No admin has logged in");
        istrue = false;
        if(ddIF!=null)
        ddIF.dispose();
        if(dbIF!=null)
        dbIF.dispose();
    }//GEN-LAST:event_loggedOutBtnActionPerformed

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
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try{
                new MainMenu().setVisible(true);
                }catch(Exception ex)
                {
                    
                    System.out.println("Exception in Main Menu Class"+ex);
                    ex.printStackTrace();
                }
                
            }
        });
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JTextField conStatusTextField;
    private javax.swing.JLabel connectionStatusLabel1;
    private javax.swing.JPanel dbPanel;
    private javax.swing.JButton displayDBBtn;
    private javax.swing.JPanel downPanel;
    private javax.swing.JLabel ipAddressLabel;
    private javax.swing.JTextField ipAddressTxtField;
    private javax.swing.JTextField isLoggedInTxtField;
    private javax.swing.JButton jButton1;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JButton loggedOutBtn;
    private javax.swing.JPanel mainBackgroundPanel;
    private javax.swing.JLabel mainTitleLabel;
    private javax.swing.JPanel topPanel;
    // End of variables declaration//GEN-END:variables
}
