/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.compomics.pladipus.view.panels.impl;

import com.compomics.pladipus.core.control.distribution.service.ProcessService;
import com.compomics.pladipus.core.control.distribution.service.RunService;
import com.compomics.pladipus.core.control.distribution.service.database.dao.impl.RunDAO;
import com.compomics.pladipus.core.control.runtime.steploader.StepLoadingException;
import com.compomics.pladipus.core.model.processing.templates.PladipusProcessingTemplate;
import com.compomics.pladipus.view.dialogs.run.ProcessCreationDialog;
import com.compomics.pladipus.view.dialogs.run.RunCreationDialog;
import com.compomics.pladipus.view.panels.UpdatingPanel;
import com.compomics.pladipus.view.util.listener.UserTableListSelectionListener;
import com.compomics.pladipus.view.util.menu.ProcessPopupMenu;
import com.compomics.pladipus.view.util.menu.RunPopupMenu;
import com.compomics.pladipus.view.util.renderer.ProgressCellRenderer;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;
import javax.management.MalformedObjectNameException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.log4j.Logger;
import org.xml.sax.SAXException;

/**
 *
 * @author Kenneth Verheggen
 */
public class UserPanel extends javax.swing.JPanel implements UpdatingPanel {

    /**
     * The process service instance
     */
    private ProcessService pService = ProcessService.getInstance();
    /**
     * The process service instance
     */
    private RunService rService = RunService.getInstance();
    /**
     * The process service instance
     */
    private String userName;
    /**
     * The process service instance
     */
    private UpdateWorker updateWorker;
    /**
     * The selected run id
     */
    private int selected_run_id = -1;
    /**
     * The template for the selected run
     */
    private PladipusProcessingTemplate runTemplate;
    /**
     * The maximum size for a page
     */
    private int pageSize = 100;
    /**
     * The current page index
     */
    private int currentPage = 1;
    /**
     * The amount of pages needed to display everything
     */
    private int pagesNeeded;
    /**
     * The size of the current selected run (rows) ToDo check if these can be
     * merged
     */
    private int runSize;
    /**
     * The size of the current selected run (rows)
     */
    private int totalRunTableRowCount;
    /**
     * The Logging instance
     */
    private static final Logger LOGGER = Logger.getLogger(UserPanel.class);

    /**
     * Creates new form UserRunStatistics
     */
    public UserPanel() {
        initComponents();
        //make the tables transparent
        spnlRun.getViewport().setOpaque(false);
        spnlProcess.getViewport().setOpaque(false);
        addTableListener();
        setProgressColumn();
        setPageFieldListener();
    }

    private void setPageFieldListener() {
        tfCurrentPage.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                checkCurrentPageChange();
            }

            ;
            @Override
            public void focusLost(FocusEvent e) {
                checkCurrentPageChange();
            }
        });
    }

    private void setProgressColumn() {
        TableColumnModel rColumnModel = tblRunInfo.getColumnModel();
        TableColumn rColumn = rColumnModel.getColumn(rColumnModel.getColumnCount() - 1);
        rColumn.setCellRenderer(new ProgressCellRenderer());
        TableColumnModel pColumnModel = tblProcessInfo.getColumnModel();
        TableColumn pColumn = pColumnModel.getColumn(pColumnModel.getColumnCount() - 1);
        pColumn.setCellRenderer(new ProgressCellRenderer());
    }

    private void addTableListener() {
        tblRunInfo.getSelectionModel().addListSelectionListener(new UserTableListSelectionListener(this));
        tblProcessInfo.setComponentPopupMenu(new ProcessPopupMenu(this));
        tblRunInfo.setComponentPopupMenu(new RunPopupMenu(this));
    }

    public void setUser(String userName) {
        this.userName = userName;
    }

    @Override
    public void activate() {
        updateWorker = new UpdateWorker();
        updateWorker.execute();
    }

    @Override
    public void deactivate() {
        if (updateWorker != null) {
            updateWorker.done();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnAddProcess = new javax.swing.JButton();
        btnNewRun = new javax.swing.JButton();
        pnlRun = new javax.swing.JPanel();
        spnlRun = new javax.swing.JScrollPane();
        tblRunInfo = new javax.swing.JTable();
        pnlProcess = new javax.swing.JPanel();
        spnlProcess = new javax.swing.JScrollPane();
        tblProcessInfo = new javax.swing.JTable();
        pnlPagination = new javax.swing.JPanel();
        pnlPaginationInput = new javax.swing.JPanel();
        tfCurrentPage = new javax.swing.JTextField();
        lbPages = new javax.swing.JLabel();
        btnNextPage = new javax.swing.JButton();
        btnPrevPage = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        btnAddProcess.setText("+");
        btnAddProcess.setToolTipText("Add processes to an existing run");
        btnAddProcess.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddProcessActionPerformed(evt);
            }
        });

        btnNewRun.setText("+");
        btnNewRun.setToolTipText("Create a new run");
        btnNewRun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewRunActionPerformed(evt);
            }
        });

        pnlRun.setBackground(new java.awt.Color(255, 255, 255));
        pnlRun.setBorder(javax.swing.BorderFactory.createTitledBorder("My Runs"));

        spnlRun.setBackground(new java.awt.Color(255, 255, 255));
        spnlRun.setBorder(null);

        tblRunInfo.setAutoCreateRowSorter(true);
        tblRunInfo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "", "R_ID", "Name", "Progress"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblRunInfo.setColumnSelectionAllowed(true);
        tblRunInfo.setGridColor(new java.awt.Color(255, 255, 255));
        tblRunInfo.setOpaque(false);
        tblRunInfo.getTableHeader().setReorderingAllowed(false);
        spnlRun.setViewportView(tblRunInfo);
        tblRunInfo.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        if (tblRunInfo.getColumnModel().getColumnCount() > 0) {
            tblRunInfo.getColumnModel().getColumn(0).setMinWidth(50);
            tblRunInfo.getColumnModel().getColumn(0).setPreferredWidth(50);
            tblRunInfo.getColumnModel().getColumn(0).setMaxWidth(50);
            tblRunInfo.getColumnModel().getColumn(1).setMinWidth(50);
            tblRunInfo.getColumnModel().getColumn(1).setPreferredWidth(50);
            tblRunInfo.getColumnModel().getColumn(1).setMaxWidth(50);
            tblRunInfo.getColumnModel().getColumn(3).setMinWidth(200);
            tblRunInfo.getColumnModel().getColumn(3).setPreferredWidth(200);
            tblRunInfo.getColumnModel().getColumn(3).setMaxWidth(200);
        }

        javax.swing.GroupLayout pnlRunLayout = new javax.swing.GroupLayout(pnlRun);
        pnlRun.setLayout(pnlRunLayout);
        pnlRunLayout.setHorizontalGroup(
            pnlRunLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(spnlRun, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 705, Short.MAX_VALUE)
        );
        pnlRunLayout.setVerticalGroup(
            pnlRunLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(spnlRun, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
        );

        pnlProcess.setBackground(new java.awt.Color(255, 255, 255));
        pnlProcess.setBorder(javax.swing.BorderFactory.createTitledBorder("My Processes"));

        spnlProcess.setBackground(new java.awt.Color(255, 255, 255));
        spnlProcess.setBorder(null);
        spnlProcess.setOpaque(false);

        tblProcessInfo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "", "P_ID", "State", "Progress"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblProcessInfo.setGridColor(new java.awt.Color(255, 255, 255));
        tblProcessInfo.setOpaque(false);
        tblProcessInfo.getTableHeader().setReorderingAllowed(false);
        spnlProcess.setViewportView(tblProcessInfo);
        if (tblProcessInfo.getColumnModel().getColumnCount() > 0) {
            tblProcessInfo.getColumnModel().getColumn(0).setMinWidth(50);
            tblProcessInfo.getColumnModel().getColumn(0).setPreferredWidth(50);
            tblProcessInfo.getColumnModel().getColumn(0).setMaxWidth(50);
            tblProcessInfo.getColumnModel().getColumn(1).setMinWidth(50);
            tblProcessInfo.getColumnModel().getColumn(1).setPreferredWidth(50);
            tblProcessInfo.getColumnModel().getColumn(1).setMaxWidth(50);
            tblProcessInfo.getColumnModel().getColumn(3).setMinWidth(200);
            tblProcessInfo.getColumnModel().getColumn(3).setPreferredWidth(200);
            tblProcessInfo.getColumnModel().getColumn(3).setMaxWidth(200);
        }

        javax.swing.GroupLayout pnlProcessLayout = new javax.swing.GroupLayout(pnlProcess);
        pnlProcess.setLayout(pnlProcessLayout);
        pnlProcessLayout.setHorizontalGroup(
            pnlProcessLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(spnlProcess, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        pnlProcessLayout.setVerticalGroup(
            pnlProcessLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(spnlProcess)
        );

        pnlPagination.setBackground(new java.awt.Color(255, 255, 255));

        pnlPaginationInput.setBackground(new java.awt.Color(255, 255, 255));
        pnlPaginationInput.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tfCurrentPage.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tfCurrentPage.setText("1");
        tfCurrentPage.setBorder(null);
        tfCurrentPage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfCurrentPageActionPerformed(evt);
            }
        });

        lbPages.setText("/1");

        javax.swing.GroupLayout pnlPaginationInputLayout = new javax.swing.GroupLayout(pnlPaginationInput);
        pnlPaginationInput.setLayout(pnlPaginationInputLayout);
        pnlPaginationInputLayout.setHorizontalGroup(
            pnlPaginationInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPaginationInputLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(tfCurrentPage, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbPages)
                .addGap(7, 7, 7))
        );

        pnlPaginationInputLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {lbPages, tfCurrentPage});

        pnlPaginationInputLayout.setVerticalGroup(
            pnlPaginationInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPaginationInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(tfCurrentPage, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(lbPages, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        btnNextPage.setText(">");
        btnNextPage.setToolTipText("View the next page of processes");
        btnNextPage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextPageActionPerformed(evt);
            }
        });

        btnPrevPage.setText("<");
        btnPrevPage.setToolTipText("View the previous page of processes");
        btnPrevPage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrevPageActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlPaginationLayout = new javax.swing.GroupLayout(pnlPagination);
        pnlPagination.setLayout(pnlPaginationLayout);
        pnlPaginationLayout.setHorizontalGroup(
            pnlPaginationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPaginationLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(btnPrevPage, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlPaginationInput, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnNextPage)
                .addContainerGap())
        );
        pnlPaginationLayout.setVerticalGroup(
            pnlPaginationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPaginationLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlPaginationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnNextPage)
                    .addComponent(pnlPaginationInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPrevPage))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pnlPagination, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlRun, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlProcess, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnNewRun)
                    .addComponent(btnAddProcess))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnNewRun)
                    .addComponent(pnlRun, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAddProcess)
                    .addComponent(pnlProcess, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlPagination, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void showCreateNewRunDialog() {
        try {
            RunCreationDialog dialog = new RunCreationDialog(null, userName, true);
            dialog.setVisible(true);
            PladipusProcessingTemplate processingTemplate = dialog.getProcessingTemplate();
            if(dialog.isConfirmed()){
            RunDAO rInstance = RunDAO.getInstance();
            int runID = rInstance.createRun(processingTemplate);
            }
            dialog.dispose();
        } catch (SQLException | ParserConfigurationException | IOException | SAXException ex) {
            ex.printStackTrace();
        }
    }
    private void btnNewRunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewRunActionPerformed
        showCreateNewRunDialog();
    }//GEN-LAST:event_btnNewRunActionPerformed

    private void btnAddProcessActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddProcessActionPerformed
        //check only one run is selected
        int[] selectedRows = tblRunInfo.getSelectedRows();
        if (selectedRows.length <= 0) {
            JOptionPane.showMessageDialog(this,
                    "Please select a single run to add processes to",
                    "Inane error",
                    JOptionPane.WARNING_MESSAGE);
        } else {
            Integer runID = Integer.parseInt(String.valueOf(tblRunInfo.getValueAt(tblRunInfo.getSelectedRow(), 1)));
            try {
                PladipusProcessingTemplate templateForRun = rService.getTemplateForRun(runID);
                ProcessCreationDialog processCreationDialog = new ProcessCreationDialog(null, true, templateForRun);
                processCreationDialog.setVisible(true);
            } catch (SQLException | IOException | StepLoadingException | ParserConfigurationException | SAXException ex) {
                ex.printStackTrace();
            }
        }
        try {
            ((DefaultTableModel) tblRunInfo.getModel()).setRowCount(0);
            //update tables
            updateRunTable();
            updateProcessTable();
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }//GEN-LAST:event_btnAddProcessActionPerformed

    private void btnNextPageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextPageActionPerformed
        if (currentPage < (pagesNeeded)) {
            currentPage++;
            tfCurrentPage.setText(String.valueOf(currentPage));
            try {
                updateProcessTable();
            } catch (Exception ex) {
                LOGGER.error(ex);
            }
        }
    }//GEN-LAST:event_btnNextPageActionPerformed

    private void btnPrevPageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevPageActionPerformed
        if (currentPage > 1) {
            currentPage--;
            tfCurrentPage.setText(String.valueOf(currentPage));
            try {
                updateProcessTable();
            } catch (Exception ex) {
                LOGGER.error(ex);
            }
        }
    }//GEN-LAST:event_btnPrevPageActionPerformed

    private void tfCurrentPageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfCurrentPageActionPerformed
        checkCurrentPageChange();
    }//GEN-LAST:event_tfCurrentPageActionPerformed

    private void checkCurrentPageChange() {
        try {
            int parseInt = Integer.parseInt(tfCurrentPage.getText());
            if (parseInt <= 0 || parseInt > pagesNeeded) {
                promptErrorMessage();
            } else {
                currentPage = Integer.parseInt(tfCurrentPage.getText());
                updateProcessTable();
            }
        } catch (NumberFormatException e) {
            promptErrorMessage();
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    private void promptErrorMessage() {
        JOptionPane.showMessageDialog(null,
                "Error: Please enter number between 1 and " + pagesNeeded, "Error Massage",
                JOptionPane.ERROR_MESSAGE);
    }

    public void reloadPage() {

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddProcess;
    private javax.swing.JButton btnNewRun;
    private javax.swing.JButton btnNextPage;
    private javax.swing.JButton btnPrevPage;
    private javax.swing.JLabel lbPages;
    private javax.swing.JPanel pnlPagination;
    private javax.swing.JPanel pnlPaginationInput;
    private javax.swing.JPanel pnlProcess;
    private javax.swing.JPanel pnlRun;
    private javax.swing.JScrollPane spnlProcess;
    private javax.swing.JScrollPane spnlRun;
    private javax.swing.JTable tblProcessInfo;
    private javax.swing.JTable tblRunInfo;
    private javax.swing.JTextField tfCurrentPage;
    // End of variables declaration//GEN-END:variables

    public JTable getRunTable() {
        return tblRunInfo;
    }

    public JTable getProcessTable() {
        return tblProcessInfo;
    }

    public void updateRunTable() throws IOException, MalformedObjectNameException, Exception {
        JTable table = UserPanel.this.tblRunInfo;
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        if (model == null) {
            model = new DefaultTableModel();
            totalRunTableRowCount = 10;
        }
        TreeMap<Integer, String> runs = rService.getRuns(userName);
        totalRunTableRowCount = runs.size();
        if (totalRunTableRowCount > 0) {
            model.setRowCount(totalRunTableRowCount);
            int[] selectedRunRows = tblRunInfo.getSelectedRows();
            int row = 0;
            int index = 1;
            for (Map.Entry<Integer, String> run_id : runs.entrySet()) {
                //set the values in the existing sells
                try {
                    double completeProcessCount = pService.getCompleteProcessCount(run_id.getKey());
                    model.setValueAt(index, row, 0);
                    model.setValueAt(run_id.getKey(), row, 1);
                    model.setValueAt(run_id.getValue(), row, 2);
                    model.setValueAt(completeProcessCount, row, 3);
                    index++;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                row++;
            }
            for (int selectedRowIndex : selectedRunRows) {
                tblRunInfo.addRowSelectionInterval(selectedRowIndex, selectedRowIndex);
            }
            table.setModel(model);
            totalRunTableRowCount = runs.size();
            model.setRowCount(totalRunTableRowCount);
        }
    }

    public void updateProcessTable() throws Exception {
        if (runTemplate != null) {
            int totalStepSize = runTemplate.getProcessingSteps().size();
            int[] selectedProcessRows = tblProcessInfo.getSelectedRows();
            if (runSize > -1) {
                pagesNeeded = 0;
                if (runSize % pageSize > 0) {
                    pagesNeeded++;
                }
                pagesNeeded += (runSize / pageSize);

                lbPages.setText("/ " + (pagesNeeded));
                int lowerlimit = (currentPage - 1) * pageSize;
                int index = lowerlimit;

                DefaultTableModel tableModel = (DefaultTableModel) tblProcessInfo.getModel();
                LinkedList<Object[]> processInformation = pService.getProcessInformation(selected_run_id, lowerlimit, pageSize);
                tableModel.setNumRows(processInformation.size());
                int row = 0;

                for (Object[] anObjectArray : processInformation) {
                    index++;
                    long processId = (Long) anObjectArray[0];
                    String state = String.valueOf(anObjectArray[1]);
                    int stepCount = (Integer) anObjectArray[2];
                    boolean completed = (Boolean) anObjectArray[3];
                    int completionPercent = (int) Math.round(100 * (double) stepCount / (double) totalStepSize);
                    tableModel.setValueAt(index, row, 0);
                    tableModel.setValueAt(processId, row, 1);
                    tableModel.setValueAt(state, row, 2);
                    if (completed) {
                        tableModel.setValueAt(100.0, row, 3);
                    } else {
                        tableModel.setValueAt(Math.min(99, completionPercent), row, 3);
                    }
                    row++;
                }
                if (selectedProcessRows.length > 0) {
                    for (int selectedRowIndex : selectedProcessRows) {
                        tblProcessInfo.addRowSelectionInterval(selectedRowIndex, selectedRowIndex);
                    }
                }
                tblProcessInfo.setModel(tableModel);
            }
        }
    }

    public void setSelectedRunId(int run_id) {
        this.selected_run_id = run_id;
        //   ((DefaultTableModel) tblProcessInfo.getModel()).setRowCount(100);
        try {
            this.runTemplate = rService.getTemplateForRun(selected_run_id);
            this.runSize = rService.getRunSize(selected_run_id);
            lbPages.setText("/" + runSize);
            currentPage = 1;
            tfCurrentPage.setText(String.valueOf(currentPage));

        } catch (SQLException | IOException | StepLoadingException | ParserConfigurationException | SAXException ex) {
            //ignore for now?
        }
    }

    private class UpdateWorker extends SwingWorker<Integer, Integer> {

        private boolean isDone = false;

        @Override
        protected Integer doInBackground() throws Exception {
            updateRunTable();
            while (!isDone) {
                try {
                    Thread.sleep(1000);
                    if (selected_run_id > 0) {
                        updateRunTable();
                        updateProcessTable();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return 0;
                }
            }
            return 0;
        }

        @Override
        protected void done() {
            isDone = true;
        }

    }

}
