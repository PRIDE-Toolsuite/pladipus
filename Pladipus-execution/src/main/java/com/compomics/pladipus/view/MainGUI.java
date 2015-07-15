/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.compomics.pladipus.view;

import com.compomics.pladipus.core.control.distribution.service.UserService;
import com.compomics.pladipus.core.control.distribution.service.database.dao.impl.RunDAO;
import com.compomics.pladipus.core.model.processing.templates.PladipusProcessingTemplate;
import com.compomics.pladipus.core.model.properties.NetworkProperties;
import com.compomics.pladipus.core.model.properties.PladipusProperties;
import com.compomics.pladipus.view.dialogs.LoginDialog;
import com.compomics.pladipus.view.dialogs.management.ConfigurationDialog;
import com.compomics.pladipus.view.dialogs.run.RunCreationDialog;
import com.compomics.pladipus.view.dialogs.run.RunImportDialog;
import com.compomics.pladipus.view.dialogs.user.UserUpdateDialog;
import com.compomics.pladipus.view.panels.UpdatingPanel;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import javax.swing.UIManager;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 *
 * @author Kenneth Verheggen
 */
public class MainGUI extends javax.swing.JFrame {

    /**
     * The panels and their names for the mainGUI
     */
    HashMap<String, UpdatingPanel> panelMap = new HashMap<>();
    /**
     * the current user
     */
    private String loggedInUser;
    /**
     * verify the user had admin rights
     */
    private boolean isAdmin;

    /**
     * Creates new form MainGUI
     */
    public MainGUI() {
        // try to set the look and feel
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            // ignore error, use default look and feel
        }

        //show the login dialog
        LoginDialog loginDialog = new LoginDialog(null, true);
        loginDialog.setLocationRelativeTo(null);
        loginDialog.setVisible(true);

        if (loginDialog.isAuthorised()) {
            this.loggedInUser = loginDialog.getUser();
            setTitle("Pladipus - " + loggedInUser);
            UserService service = UserService.getInstance();
            int userRole = 2;
            try {
                userRole = service.getUserRoles(loggedInUser);
            } catch (SQLException ex) {
                //log
            }
            isAdmin = userRole == 1;
            init();
        } else {
            loginDialog.setVisible(true);
        }
    }

    private void initConfigurationDialog(PladipusProperties properties) {
        ConfigurationDialog configurationDialog = new ConfigurationDialog(this, true);
        configurationDialog.setProperties(properties);
        configurationDialog.setLocationRelativeTo(this);
        configurationDialog.setVisible(true);
    }

    private void init() {
        initComponents();
        if (!isAdmin) {
            miLaunchAdmin.setEnabled(false);
            miLaunchAdmin.setVisible(false);
        }
        userPanel.setUser(loggedInUser);
        userPanel.activate();
        //add listener to the tabbed pane
        this.setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        userPanel = new com.compomics.pladipus.view.panels.impl.UserPanel();
        mnbMain = new javax.swing.JMenuBar();
        miFile = new javax.swing.JMenu();
        miCreateRun = new javax.swing.JMenuItem();
        miImportRun = new javax.swing.JMenuItem();
        miExit = new javax.swing.JMenuItem();
        miEdit = new javax.swing.JMenu();
        miSettings = new javax.swing.JMenu();
        miAccountSettings = new javax.swing.JMenuItem();
        miPladipusSettings = new javax.swing.JMenuItem();
        miLaunchAdmin = new javax.swing.JMenuItem();
        sprEdit = new javax.swing.JPopupMenu.Separator();
        miHelp = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        miFile.setText("File");

        miCreateRun.setText("Create new run");
        miCreateRun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miCreateRunActionPerformed(evt);
            }
        });
        miFile.add(miCreateRun);

        miImportRun.setText("Import Run...");
        miImportRun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miImportRunActionPerformed(evt);
            }
        });
        miFile.add(miImportRun);

        miExit.setText("Exit");
        miExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miExitActionPerformed(evt);
            }
        });
        miFile.add(miExit);

        mnbMain.add(miFile);

        miEdit.setText("Edit");

        miSettings.setText("Configuration");

        miAccountSettings.setText("My Account");
        miAccountSettings.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miAccountSettingsActionPerformed(evt);
            }
        });
        miSettings.add(miAccountSettings);

        miPladipusSettings.setText("Network");
        miPladipusSettings.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miPladipusSettingsActionPerformed(evt);
            }
        });
        miSettings.add(miPladipusSettings);

        miEdit.add(miSettings);

        miLaunchAdmin.setText("Launch Admin Console");
        miLaunchAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miLaunchAdminActionPerformed(evt);
            }
        });
        miEdit.add(miLaunchAdmin);
        miEdit.add(sprEdit);

        mnbMain.add(miEdit);

        miHelp.setText("Help");
        mnbMain.add(miHelp);

        setJMenuBar(mnbMain);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(userPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(userPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void miImportRunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miImportRunActionPerformed
        RunImportDialog runCreationDialog = new RunImportDialog(this, userPanel, true);
        runCreationDialog.setUser(loggedInUser);
        runCreationDialog.setVisible(true);

    }//GEN-LAST:event_miImportRunActionPerformed

    private void miExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miExitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_miExitActionPerformed

    private void miAccountSettingsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miAccountSettingsActionPerformed
        UserUpdateDialog userUpdateDialog = new UserUpdateDialog(this, true, loggedInUser);
        userUpdateDialog.setLocationRelativeTo(this);
        userUpdateDialog.setVisible(true);
    }//GEN-LAST:event_miAccountSettingsActionPerformed

    private void miPladipusSettingsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miPladipusSettingsActionPerformed
        initConfigurationDialog(NetworkProperties.getInstance());
    }//GEN-LAST:event_miPladipusSettingsActionPerformed

    private void miLaunchAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miLaunchAdminActionPerformed
        if (isAdmin) {
            AdminConsole.main(new String[0]);
        } else {

        }
    }//GEN-LAST:event_miLaunchAdminActionPerformed

    private void miCreateRunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miCreateRunActionPerformed
   showCreateNewRunDialog();
    }//GEN-LAST:event_miCreateRunActionPerformed

    private void showCreateNewRunDialog(){
             try {
            RunCreationDialog dialog = new RunCreationDialog(this, loggedInUser, true);
            dialog.setVisible(true);
            PladipusProcessingTemplate processingTemplate = dialog.getProcessingTemplate();
            RunDAO rInstance = RunDAO.getInstance();
            int runID = rInstance.createRun(processingTemplate);
        } catch (SQLException | ParserConfigurationException | IOException | SAXException ex) {
            ex.printStackTrace();
        }
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
            java.util.logging.Logger.getLogger(MainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem miAccountSettings;
    private javax.swing.JMenuItem miCreateRun;
    private javax.swing.JMenu miEdit;
    private javax.swing.JMenuItem miExit;
    private javax.swing.JMenu miFile;
    private javax.swing.JMenu miHelp;
    private javax.swing.JMenuItem miImportRun;
    private javax.swing.JMenuItem miLaunchAdmin;
    private javax.swing.JMenuItem miPladipusSettings;
    private javax.swing.JMenu miSettings;
    private javax.swing.JMenuBar mnbMain;
    private javax.swing.JPopupMenu.Separator sprEdit;
    private com.compomics.pladipus.view.panels.impl.UserPanel userPanel;
    // End of variables declaration//GEN-END:variables
}
