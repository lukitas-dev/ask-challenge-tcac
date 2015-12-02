/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.view;

import game.bean.Question;
import game.controller.PlayerController;
import game.controller.TeamController;
import java.rmi.RemoteException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

/**
 *
 * @author Lucas
 */
public class QuestionsManager extends javax.swing.JFrame {

    private PlayerController controller;
    private TeamController teamController;

    /**
     * Creates new form QuestionsManager
     *
     * @param controller
     * @param parent
     */
    public QuestionsManager(PlayerController pc, TeamController tc) {
        initComponents();
        this.controller = pc;
        this.teamController = tc;
        updateQuestions();
    }
    
    public void updateQuestions(){
        try {
            List<Question> qts = controller.factory.getQuestionInterface().getAll(teamController.team.getID());
            DefaultListModel model = new DefaultListModel();
            questions.setModel(model);
            for (Question q : qts) {
                model.addElement(q);
            }
        } catch (RemoteException ex) {
            Logger.getLogger(QuestionsManager.class.getName()).log(Level.SEVERE, null, ex);
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

        jbDelete = new javax.swing.JButton();
        jbNew = new javax.swing.JButton();
        jbEdit = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        questions = new javax.swing.JList();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Question Manager");

        jbDelete.setFont(new java.awt.Font("Tempus Sans ITC", 0, 14)); // NOI18N
        jbDelete.setText("Delete");
        jbDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbDeleteActionPerformed(evt);
            }
        });

        jbNew.setFont(new java.awt.Font("Tempus Sans ITC", 0, 14)); // NOI18N
        jbNew.setText("New");
        jbNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbNewActionPerformed(evt);
            }
        });

        jbEdit.setFont(new java.awt.Font("Tempus Sans ITC", 0, 14)); // NOI18N
        jbEdit.setText("Edit");
        jbEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbEditActionPerformed(evt);
            }
        });

        questions.setFont(new java.awt.Font("Tempus Sans ITC", 0, 14)); // NOI18N
        jScrollPane1.setViewportView(questions);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jbNew)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 141, Short.MAX_VALUE)
                        .addComponent(jbEdit)
                        .addGap(138, 138, 138)
                        .addComponent(jbDelete)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbDelete)
                    .addComponent(jbNew)
                    .addComponent(jbEdit))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbNewActionPerformed
        NewQuestion nq = new NewQuestion(controller, teamController, this);
        nq.setLocationRelativeTo(this);
        nq.setVisible(true);
    }//GEN-LAST:event_jbNewActionPerformed

    private void jbEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbEditActionPerformed
        Question qt = (Question) questions.getSelectedValue();
        EditQuestion eq = new EditQuestion(controller, teamController, qt, this);
        eq.setLocationRelativeTo(this);
        eq.setVisible(true);
    }//GEN-LAST:event_jbEditActionPerformed

    private void jbDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbDeleteActionPerformed
        Question qt = (Question) questions.getSelectedValue();
        try {
            controller.factory.getQuestionInterface().delete(teamController.team.getID(), qt);
            updateQuestions();
            JOptionPane.showMessageDialog(this, "Question was deleted!");
            
        } catch (RemoteException ex) {
            Logger.getLogger(QuestionsManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jbDeleteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbDelete;
    private javax.swing.JButton jbEdit;
    private javax.swing.JButton jbNew;
    private javax.swing.JList questions;
    // End of variables declaration//GEN-END:variables
}
