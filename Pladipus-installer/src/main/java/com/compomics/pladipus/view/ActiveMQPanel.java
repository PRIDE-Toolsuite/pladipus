/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.compomics.pladipus.view;

import com.compomics.pladipus.controller.setup.InstallActiveMQ;
import com.compomics.pladipus.core.control.distribution.PladipusTrafficManager;
import com.compomics.util.gui.waiting.waitinghandlers.ProgressDialogX;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Kenneth Verheggen
 */
public class ActiveMQPanel extends javax.swing.JPanel {

    /**
     * the active mq setup instance
     */
    private final InstallActiveMQ setup;

    /**
     * Creates new form MySQLPanel
     */
    public ActiveMQPanel() {
        initComponents();
        ImageIcon image = new ImageIcon(
                getClass().getResource(
                        "/images/logo_activeMQ.jpe"));
        lbLogo.setText("");
        lbLogo.setIcon(image);
        setup = new InstallActiveMQ();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblHost = new javax.swing.JLabel();
        lblAMQPort = new javax.swing.JLabel();
        lblJMXPort = new javax.swing.JLabel();
        tfHost = new javax.swing.JTextField();
        tfAmqPort = new javax.swing.JTextField();
        tfJmxPort = new javax.swing.JTextField();
        btnTestConnection = new javax.swing.JButton();
        btnInstallAmq = new javax.swing.JButton();
        pnlLogo = new javax.swing.JPanel();
        lbLogo = new javax.swing.JLabel();
        btnApply = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        lblHost.setBackground(new java.awt.Color(255, 255, 255));
        lblHost.setText("Host");

        lblAMQPort.setBackground(new java.awt.Color(255, 255, 255));
        lblAMQPort.setText("Port (amq)");

        lblJMXPort.setBackground(new java.awt.Color(255, 255, 255));
        lblJMXPort.setText("Port (jmx)");

        tfHost.setText("localhost");

        tfAmqPort.setText("3389");
        tfAmqPort.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfAmqPortActionPerformed(evt);
            }
        });

        tfJmxPort.setText("1099");

        btnTestConnection.setBackground(new java.awt.Color(255, 255, 255));
        btnTestConnection.setText("Test Connection");
        btnTestConnection.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTestConnectionActionPerformed(evt);
            }
        });

        btnInstallAmq.setBackground(new java.awt.Color(255, 255, 255));
        btnInstallAmq.setText("Install server");
        btnInstallAmq.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInstallAmqActionPerformed(evt);
            }
        });

        pnlLogo.setBackground(new java.awt.Color(255, 255, 255));

        lbLogo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbLogo.setText("jLabel1");

        javax.swing.GroupLayout pnlLogoLayout = new javax.swing.GroupLayout(pnlLogo);
        pnlLogo.setLayout(pnlLogoLayout);
        pnlLogoLayout.setHorizontalGroup(
            pnlLogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(pnlLogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(lbLogo, javax.swing.GroupLayout.DEFAULT_SIZE, 295, Short.MAX_VALUE))
        );
        pnlLogoLayout.setVerticalGroup(
            pnlLogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 116, Short.MAX_VALUE)
            .addGroup(pnlLogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(lbLogo, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE))
        );

        btnApply.setBackground(new java.awt.Color(255, 255, 255));
        btnApply.setText("Apply");
        btnApply.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnApplyActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlLogo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblHost)
                        .addGap(18, 18, 18)
                        .addComponent(tfHost))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblAMQPort)
                        .addGap(18, 18, 18)
                        .addComponent(tfAmqPort))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblJMXPort)
                        .addGap(18, 18, 18)
                        .addComponent(tfJmxPort))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 69, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnTestConnection, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnInstallAmq, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnApply)))))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {lblAMQPort, lblHost, lblJMXPort});

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnApply, btnInstallAmq});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlLogo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblHost)
                    .addComponent(tfHost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAMQPort)
                    .addComponent(tfAmqPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblJMXPort)
                    .addComponent(tfJmxPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnInstallAmq)
                    .addComponent(btnApply))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnTestConnection)
                .addGap(23, 23, 23))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnTestConnectionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTestConnectionActionPerformed
        testConnection();
    }//GEN-LAST:event_btnTestConnectionActionPerformed

    private void tfAmqPortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfAmqPortActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfAmqPortActionPerformed

    private void btnInstallAmqActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInstallAmqActionPerformed

        int dialogResult = JOptionPane.showConfirmDialog(null, "Would you like to apply the following settings on this machine?"
                + System.lineSeparator()
                + "ActiveMQ host \t=" + tfHost.getText()
                + System.lineSeparator()
                + "ActiveMQ port \t=" + tfAmqPort.getText()
                + System.lineSeparator()
                + "ActiveMQ JMX port \t=" + tfJmxPort.getText());
        if (dialogResult == JOptionPane.NO_OPTION) {
            return;
        }

        ProgressDialogX dialog = new ProgressDialogX(true);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    setup.setupActiveMQ(tfHost.getText(), tfAmqPort.getText(), tfJmxPort.getText());
                    JOptionPane.showMessageDialog(ActiveMQPanel.this, "Succesfully created launcher on desktop for the activeMQ starter");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(ActiveMQPanel.this,
                            "Could not install activeMQ : " + System.lineSeparator() + ex.getMessage(),
                            "Inane error",
                            JOptionPane.ERROR_MESSAGE);
                } finally {
                    dialog.setRunFinished();
                    dialog.setVisible(false);
                    dialog.dispose();
                }
            }
        }).start();
        dialog.setLocationRelativeTo(null);
        dialog.setPrimaryProgressCounterIndeterminate(true);
        dialog.setTitle("Installing ActiveMQ Server");
        dialog.setVisible(true);
    }//GEN-LAST:event_btnInstallAmqActionPerformed

    private void btnApplyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnApplyActionPerformed
        int dialogResult = JOptionPane.showConfirmDialog(null, "Would you like to apply the following settings on this machine?"
                + System.lineSeparator()
                + "ActiveMQ host \t=" + tfHost.getText()
                + System.lineSeparator()
                + "ActiveMQ port \t=" + tfAmqPort.getText()
                + System.lineSeparator()
                + "ActiveMQ JMX port \t=" + tfJmxPort.getText());
        if (dialogResult == JOptionPane.NO_OPTION) {
            return;
        }
        try {
            setup.updateProperties(tfHost.getText(), tfAmqPort.getText(), tfJmxPort.getText());
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(ActiveMQPanel.this,
                    "Could not update settings : " + System.lineSeparator() + ex.getMessage(),
                    "Inane error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnApplyActionPerformed

    private void testConnection() {
        try {
            PladipusTrafficManager.getInstance().isSystemOnline();
            JOptionPane.showMessageDialog(this, "Succesfully connected to the ActiveMQ Server");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "ActiveMQ could not be reached: " + System.lineSeparator() + ex.getMessage(),
                    "Inane error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnApply;
    private javax.swing.JButton btnInstallAmq;
    private javax.swing.JButton btnTestConnection;
    private javax.swing.JLabel lbLogo;
    private javax.swing.JLabel lblAMQPort;
    private javax.swing.JLabel lblHost;
    private javax.swing.JLabel lblJMXPort;
    private javax.swing.JPanel pnlLogo;
    private javax.swing.JTextField tfAmqPort;
    private javax.swing.JTextField tfHost;
    private javax.swing.JTextField tfJmxPort;
    // End of variables declaration//GEN-END:variables
}
