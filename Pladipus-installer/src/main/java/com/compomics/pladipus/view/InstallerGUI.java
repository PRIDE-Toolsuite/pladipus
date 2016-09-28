package com.compomics.pladipus.view;

import com.compomics.pladipus.model.InstallOptions;
import org.apache.log4j.Logger;

import java.awt.Desktop;
import java.awt.Toolkit;
import java.io.IOException;
import java.net.URISyntaxException;
import javax.swing.*;
import javax.swing.event.HyperlinkEvent;

/**
 * @author Kenneth Verheggen
 */
public class InstallerGUI extends javax.swing.JFrame {

    private static final Logger LOGGER = Logger.getLogger(InstallerGUI.class.getName());

    private final InstallOptions selectedOption;
    
    public InstallerGUI(InstallOptions cardsToShow) {
        selectedOption = cardsToShow;
        initComponents();
        epDescription.addHyperlinkListener(e -> {
            if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                if (Desktop.isDesktopSupported()) {
                    try {
                        Desktop.getDesktop().browse(e.getURL().toURI());
                    } catch (IOException | URISyntaxException ex) {
                        LOGGER.error("could not open standard browser");
                    }
                }
            }
        });
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/pladipus_icon.gif")));
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        pnlMain = new javax.swing.JPanel();
        btnNext = new javax.swing.JButton();
        btnPrev = new javax.swing.JButton();
        pnlSteps = new javax.swing.JPanel();
        pnlDescription = new javax.swing.JPanel();
        spnlDescription = new javax.swing.JScrollPane();
        epDescription = new javax.swing.JEditorPane();
        liSteps = new javax.swing.JList();
        pnlCards =         new com.compomics.pladipus.view.StepsCardPanel(selectedOption.getCardsForOption());

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        pnlMain.setBackground(new java.awt.Color(255, 255, 255));

        btnNext.setText("Next");
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        btnPrev.setText("Previous");
        btnPrev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrevActionPerformed(evt);
            }
        });

        pnlSteps.setBackground(new java.awt.Color(255, 255, 255));

        pnlDescription.setBackground(new java.awt.Color(255, 255, 255));

        spnlDescription.setBackground(new java.awt.Color(255, 255, 255));
        spnlDescription.setBorder(null);

        epDescription.setEditable(false);
        epDescription.setContentType("text/html"); // NOI18N
        epDescription.setEditorKit(JEditorPane.createEditorKitForContentType("text/html"));
        epDescription.setText(pnlCards.getCardDescription());
        spnlDescription.setViewportView(epDescription);

        liSteps.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "0 - Overview", "1 - Installing MySQL", "2 - Installing ActiveMQ", "3 - Installing Pladipus" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        liSteps.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        liSteps.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        liSteps.setFocusable(false);
        liSteps.setRequestFocusEnabled(false);
        liSteps.setSelectionBackground(new java.awt.Color(204, 204, 255));
        liSteps.setVerifyInputWhenFocusTarget(false);

        javax.swing.GroupLayout pnlDescriptionLayout = new javax.swing.GroupLayout(pnlDescription);
        pnlDescription.setLayout(pnlDescriptionLayout);
        pnlDescriptionLayout.setHorizontalGroup(
            pnlDescriptionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDescriptionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDescriptionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(spnlDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(liSteps))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlDescriptionLayout.setVerticalGroup(
            pnlDescriptionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDescriptionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(liSteps)
                .addGap(22, 22, 22)
                .addComponent(spnlDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlStepsLayout = new javax.swing.GroupLayout(pnlSteps);
        pnlSteps.setLayout(pnlStepsLayout);
        pnlStepsLayout.setHorizontalGroup(
            pnlStepsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlStepsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlDescription, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(pnlCards, javax.swing.GroupLayout.DEFAULT_SIZE, 387, Short.MAX_VALUE))
        );
        pnlStepsLayout.setVerticalGroup(
            pnlStepsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlStepsLayout.createSequentialGroup()
                .addGap(0, 48, Short.MAX_VALUE)
                .addComponent(pnlDescription, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlStepsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlCards, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout pnlMainLayout = new javax.swing.GroupLayout(pnlMain);
        pnlMain.setLayout(pnlMainLayout);
        pnlMainLayout.setHorizontalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addGap(437, 437, 437)
                        .addComponent(btnPrev)
                        .addGap(43, 43, 43)
                        .addComponent(btnNext))
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(pnlSteps, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlMainLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnNext, btnPrev});

        pnlMainLayout.setVerticalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlSteps, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNext)
                    .addComponent(btnPrev))
                .addContainerGap(33, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(pnlMain);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 792, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnPrevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevActionPerformed
        pnlCards.showPreviousCard();
        epDescription.setText(pnlCards.getCardDescription());
        btnNext.setText("Next");
        if (!pnlCards.hasPrevious()) {
           new StartFrame().setVisible(true);
            dispose();
        }
    }//GEN-LAST:event_btnPrevActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed

            if (!pnlCards.hasNext()) {
                //then it's done
                this.dispose();
            } else {
                pnlCards.showNextCard();
                epDescription.setText(pnlCards.getCardDescription());
                if (!pnlCards.hasNext()) {
                    btnNext.setText("Finish");
                } else {
                    btnNext.setText("Next");
                }
            }
        }
//GEN-LAST:event_btnNextActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrev;
    private javax.swing.JEditorPane epDescription;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList liSteps;
    private com.compomics.pladipus.view.StepsCardPanel pnlCards;
    private javax.swing.JPanel pnlDescription;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JPanel pnlSteps;
    private javax.swing.JScrollPane spnlDescription;
    // End of variables declaration//GEN-END:variables
}
