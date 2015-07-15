/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.compomics.pladipus.view;

import com.compomics.pladipus.controller.setup.InitMySQL;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Kenneth Verheggen
 */
public class MySQLPanel extends javax.swing.JPanel {

    /**
     * The mysql service address
     */
    private String host;
    /**
     * The mysql service port
     */
    private String port;
    /**
     * the login for the mysql service
     */
    private String user;
    /**
     * the password for the mysql service
     */
    private String password;
    /**
     * check if the database is existing
     */
    private boolean dbExists = false;
    /**
     * the mysql setup instance
     */
    private final InitMySQL mySQLSetup = new InitMySQL();

    public String getHost() {
        return host;
    }

    public String getPort() {
        return port;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    /**
     * Creates new form MySQLPanel
     */
    public MySQLPanel() {
        initComponents();
        ImageIcon image = new ImageIcon(
                getClass().getResource(
                        "/images/logo_mysql.png"));
        lbLogo.setText("");
        lbLogo.setIcon(image);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlMain = new javax.swing.JPanel();
        lbLogo = new javax.swing.JLabel();
        lblHost = new javax.swing.JLabel();
        lblPort = new javax.swing.JLabel();
        lblUser = new javax.swing.JLabel();
        lblPass = new javax.swing.JLabel();
        tfHost = new javax.swing.JTextField();
        tfPort = new javax.swing.JTextField();
        tfUser = new javax.swing.JTextField();
        btnApply = new javax.swing.JButton();
        pfPass = new javax.swing.JPasswordField();
        btnTestConnection = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        pnlMain.setBackground(new java.awt.Color(255, 255, 255));

        lbLogo.setBackground(new java.awt.Color(255, 255, 255));
        lbLogo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbLogo.setText("jLabel1");

        lblHost.setBackground(new java.awt.Color(255, 255, 255));
        lblHost.setText("Host");

        lblPort.setBackground(new java.awt.Color(255, 255, 255));
        lblPort.setText("Port");

        lblUser.setBackground(new java.awt.Color(255, 255, 255));
        lblUser.setText("User");

        lblPass.setBackground(new java.awt.Color(255, 255, 255));
        lblPass.setText("Password");

        tfHost.setText("localhost");

        tfPort.setText("3306");

        tfUser.setText("root");

        btnApply.setBackground(new java.awt.Color(255, 255, 255));
        btnApply.setText("Apply");
        btnApply.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnApplyActionPerformed(evt);
            }
        });

        btnTestConnection.setBackground(new java.awt.Color(255, 255, 255));
        btnTestConnection.setText("Test Connection");
        btnTestConnection.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTestConnectionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlMainLayout = new javax.swing.GroupLayout(pnlMain);
        pnlMain.setLayout(pnlMainLayout);
        pnlMainLayout.setHorizontalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addComponent(lblHost)
                        .addGap(18, 18, 18)
                        .addComponent(tfHost))
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addComponent(lblPort)
                        .addGap(18, 18, 18)
                        .addComponent(tfPort))
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addComponent(lblUser)
                        .addGap(18, 18, 18)
                        .addComponent(tfUser))
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addComponent(lblPass)
                        .addGap(18, 18, 18)
                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlMainLayout.createSequentialGroup()
                                .addComponent(btnTestConnection)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                                .addComponent(btnApply))
                            .addComponent(pfPass, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)))))
            .addComponent(lbLogo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pnlMainLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {lblHost, lblPass, lblPort, lblUser});

        pnlMainLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnApply, btnTestConnection});

        pnlMainLayout.setVerticalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblHost)
                    .addComponent(tfHost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPort)
                    .addComponent(tfPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblUser)
                    .addComponent(tfUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPass)
                    .addComponent(pfPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTestConnection)
                    .addComponent(btnApply))
                .addGap(29, 29, 29))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnApplyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnApplyActionPerformed
        try {
            mySQLSetup.updateProperties(host, port, user, password);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this,
                    "Could not update properties",
                    "Inane error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnApplyActionPerformed

    private void btnTestConnectionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTestConnectionActionPerformed
        testConnection();
    }//GEN-LAST:event_btnTestConnectionActionPerformed

    private void testConnection() {
        host = tfHost.getText();
        port = tfPort.getText();
        user = tfUser.getText();
        password = new String(pfPass.getPassword());

        String url = "jdbc:mysql://" + host + ":" + port;

        System.out.println("Connecting database...");
        //1. check if db exists
        boolean connected = false;
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            connected = true;
            try {
                ResultSet executeQuery = connection.createStatement().executeQuery("SELECT SCHEMA_NAME FROM INFORMATION_SCHEMA.SCHEMATA WHERE SCHEMA_NAME = 'pladipus'");
                executeQuery.next();
                dbExists = (!executeQuery.getString("SCHEMA_NAME").isEmpty());
                JOptionPane.showMessageDialog(this, "Succesfully contacted the pladipus database.");
            } catch (SQLException theDatabaseIsNotThere) {
                if (connected) {
                    //2. prompt user if he/she wants to attempt to initialize the database?
                    int dialogResult = JOptionPane.showConfirmDialog(this, "Would you like to automatically import the pladipus database?");
                    if (dialogResult == JOptionPane.YES_OPTION) {
                        mySQLSetup.setupMySql(connection);
                        dbExists = true;
                        JOptionPane.showMessageDialog(this, "Succesfully created the pladipus database.");
                    }
                }
            } finally {
                if (dbExists) {
                    mySQLSetup.updateProperties(host, port, user, password);
                }
            }
        } catch (SQLException e) {
            if (!connected) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this,
                        "Could not connect to the database",
                        "Inane error",
                        JOptionPane.ERROR_MESSAGE);
            }
        } catch (IOException ex) {
            //custom title, error icon
            JOptionPane.showMessageDialog(this,
                    "Could not import SQL init script",
                    "Inane error",
                    JOptionPane.ERROR_MESSAGE);
        }

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnApply;
    private javax.swing.JButton btnTestConnection;
    private javax.swing.JLabel lbLogo;
    private javax.swing.JLabel lblHost;
    private javax.swing.JLabel lblPass;
    private javax.swing.JLabel lblPort;
    private javax.swing.JLabel lblUser;
    private javax.swing.JPasswordField pfPass;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JTextField tfHost;
    private javax.swing.JTextField tfPort;
    private javax.swing.JTextField tfUser;
    // End of variables declaration//GEN-END:variables
}
