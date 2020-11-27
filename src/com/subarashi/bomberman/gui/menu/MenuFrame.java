package com.subarashi.bomberman.gui.menu;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.subarashi.bomberman.gui.Frame;
/**
 *
 * @author ducno
 */
public class MenuFrame extends javax.swing.JFrame {
    Frame frame;
    /**
     * Creates new form MenuFrame
     */
    public MenuFrame() throws Exception {
        initComponents();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        exitButton = new javax.swing.JButton();
        highScoreButton = new javax.swing.JButton();
        newGameButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(null);

        exitButton.setBackground(new java.awt.Color(153, 0, 255));
        exitButton.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        exitButton.setForeground(new java.awt.Color(0, 153, 153));
        exitButton.setText("EXIT");
        exitButton.addHierarchyBoundsListener(new java.awt.event.HierarchyBoundsListener() {
            public void ancestorMoved(java.awt.event.HierarchyEvent evt) {
            }
            public void ancestorResized(java.awt.event.HierarchyEvent evt) {
            }
        });
        exitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitButtonActionPerformed(evt);
            }
        });
        jPanel1.add(exitButton);
        exitButton.setBounds(140, 650, 310, 60);

        highScoreButton.setBackground(new java.awt.Color(153, 0, 255));
        highScoreButton.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        highScoreButton.setForeground(new java.awt.Color(0, 153, 153));
        highScoreButton.setText("HIGH SCORE");
        highScoreButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                highScoreButtonActionPerformed(evt);
            }
        });
        jPanel1.add(highScoreButton);
        highScoreButton.setBounds(140, 530, 310, 60);

        newGameButton.setBackground(new java.awt.Color(153, 0, 255));
        newGameButton.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        newGameButton.setForeground(new java.awt.Color(0, 153, 153));
        newGameButton.setText("NEW GAME");
        newGameButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    newGameButtonActionPerformed(evt);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        jPanel1.add(newGameButton);
        newGameButton.setBounds(140, 400, 310, 60);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/textures/background.jpg"))); // NOI18N
        jPanel1.add(jLabel1);
        jLabel1.setBounds(10, 0, 570, 900);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 581, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 895, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>

    private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {
        System.exit(0);
    }

    private void highScoreButtonActionPerformed(java.awt.event.ActionEvent evt) {

    }


    private void newGameButtonActionPerformed(java.awt.event.ActionEvent evt) throws Exception {
        this.dispose();
        frame = new Frame();
    }

    // Variables declaration - do not modify
    private javax.swing.JButton exitButton;
    private javax.swing.JButton highScoreButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton newGameButton;
    // End of variables declaration
}
