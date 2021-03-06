package com.compomics.pladipus.view.dialogs.management;

import com.compomics.pladipus.core.model.properties.PladipusProperties;
import java.io.FileWriter;
import java.io.IOException;
import java.util.TreeMap;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Kenneth Verheggen
 */
public class ConfigurationDialog extends javax.swing.JDialog {

    /**
     * Properties instance
     */
    private PladipusProperties properties;

    /**
     * Creates a new NetworkConfigurationDialog.
     */
    public ConfigurationDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        setLocationRelativeTo(parent);

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
    }

    /**
     * Sets the properties to display
     */
    public void setProperties(PladipusProperties properties) {
        this.properties = properties;
        initComponents();
        spTable.getViewport().setOpaque(false);
        loadPropertiesInTable();
    }

    private void loadPropertiesInTable() {
        DefaultTableModel model = (DefaultTableModel) tblProperties.getModel();
        model.setRowCount(0);
        //sort first?
        TreeMap<String, String> sortedParameterMap = new TreeMap<>();
        properties.entrySet().stream().forEach((aParameter) -> {
            sortedParameterMap.put(String.valueOf(aParameter.getKey()), String.valueOf(aParameter.getValue()));
        });

        sortedParameterMap.entrySet().stream().forEach((aParameter) -> {
            model.addRow(new Object[]{aParameter.getKey(), aParameter.getValue()});
        });
        tblProperties.setModel(model);
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
        btnSave = new javax.swing.JButton();
        settingsPanel = new javax.swing.JPanel();
        spTable = new javax.swing.JScrollPane();
        tblProperties = new javax.swing.JTable();
        cancelButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Network Settings");

        pnlMain.setBackground(new java.awt.Color(230, 230, 230));

        btnSave.setText("Apply");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        settingsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Settings"));
        settingsPanel.setOpaque(false);

        spTable.setBackground(new java.awt.Color(255, 255, 255));
        spTable.setOpaque(false);

        tblProperties.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Parameter", "Value"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblProperties.setColumnSelectionAllowed(true);
        tblProperties.setOpaque(false);
        tblProperties.getTableHeader().setReorderingAllowed(false);
        spTable.setViewportView(tblProperties);
        tblProperties.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        javax.swing.GroupLayout settingsPanelLayout = new javax.swing.GroupLayout(settingsPanel);
        settingsPanel.setLayout(settingsPanelLayout);
        settingsPanelLayout.setHorizontalGroup(
            settingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(settingsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(spTable, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        settingsPanelLayout.setVerticalGroup(
            settingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(settingsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(spTable, javax.swing.GroupLayout.DEFAULT_SIZE, 474, Short.MAX_VALUE)
                .addContainerGap())
        );

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlMainLayout = new javax.swing.GroupLayout(pnlMain);
        pnlMain.setLayout(pnlMainLayout);
        pnlMainLayout.setHorizontalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(settingsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMainLayout.createSequentialGroup()
                        .addGap(0, 292, Short.MAX_VALUE)
                        .addComponent(btnSave)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cancelButton)
                        .addGap(4, 4, 4)))
                .addContainerGap())
        );

        pnlMainLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnSave, cancelButton});

        pnlMainLayout.setVerticalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(settingsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSave)
                    .addComponent(cancelButton))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        //get all the values from the table model and chuck them in a properties object
        DefaultTableModel model = (DefaultTableModel) tblProperties.getModel();
        for (int i = 0; i < model.getRowCount(); i++) {
            String key = String.valueOf(model.getValueAt(i, 0));
            String value = String.valueOf(model.getValueAt(i, 1));
            properties.setProperty(key, value);
        }
        try {
            properties.store(new FileWriter(properties.getPropertiesFile()), null);
            JOptionPane.showMessageDialog(this,
                    "Saving successful.",
                    "Properties Updated ",
                    JOptionPane.INFORMATION_MESSAGE
            );

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this,
                    "Could not save the properties file: " + System.lineSeparator() + ex,
                    "Save Error",
                    JOptionPane.ERROR_MESSAGE);
        } finally {
            this.setVisible(false);
            this.dispose();
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    /**
     * Close the dialog without saving.
     *
     * @param evt
     */
    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        dispose();
    }//GEN-LAST:event_cancelButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSave;
    private javax.swing.JButton cancelButton;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JPanel settingsPanel;
    private javax.swing.JScrollPane spTable;
    private javax.swing.JTable tblProperties;
    // End of variables declaration//GEN-END:variables
}
