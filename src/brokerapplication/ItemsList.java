/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brokerapplication;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author Mayank Kakad
 */
public class ItemsList extends javax.swing.JFrame {

    /**
     * Creates new form ItemsList
     */
    int selected;
    JLabel sr_nos[],codes[],names[];
    JButton edits[];
    Vector<Item> itemlist;
    static AddItems ai;
    public ItemsList() {
        initComponents();
        initComps();
    }
    public void initComps()
    {
        itemlist=LoginPage.datop.getItems();
        sr_nos=new JLabel[itemlist.size()];
        codes=new JLabel[itemlist.size()];
        names=new JLabel[itemlist.size()];
        edits=new JButton[itemlist.size()];
        GridBagConstraints gbc=new GridBagConstraints();
        jPanel1.remove(jButton3);
        for(int i=0;i<itemlist.size();i++)
        {
            sr_nos[i]=new JLabel();
            sr_nos[i].setFont(new java.awt.Font("Tahoma",0,24));
            sr_nos[i].setPreferredSize(new java.awt.Dimension(92,50));
            sr_nos[i].setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            sr_nos[i].setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
            sr_nos[i].setText(Integer.toString(i+1));
            gbc.gridx=0;
            gbc.gridy=i+3;
            gbc.insets=new Insets(0,0,30,30);
            jPanel1.add(sr_nos[i],gbc);
            codes[i]=new JLabel();
            codes[i].setFont(new java.awt.Font("Tahoma",0,24));
            codes[i].setPreferredSize(new java.awt.Dimension(152,50));
            codes[i].setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            codes[i].setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
            codes[i].setText(itemlist.get(i).code);
            gbc.gridx=1;
            gbc.gridy=i+3;
            gbc.insets=new Insets(0,0,30,30);
            jPanel1.add(codes[i],gbc);
            names[i]=new JLabel();
            names[i].setFont(new java.awt.Font("Tahoma",0,24));
            names[i].setPreferredSize(new java.awt.Dimension(302,50));
            names[i].setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            names[i].setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
            names[i].setText(itemlist.get(i).name);
            gbc.gridx=2;
            gbc.gridy=i+3;
            gbc.insets=new Insets(0,0,30,30);
            jPanel1.add(names[i],gbc);
            edits[i]=new JButton();
            edits[i].setText("Edit");
            edits[i].setFont(new java.awt.Font("Tahoma",0,24));
            edits[i].setPreferredSize(new java.awt.Dimension(76,50));
            edits[i].setName(Integer.toString(i));
            edits[i].addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    JButton source=(JButton)e.getSource();
                    selected=Integer.parseInt(source.getName());
                    jTextField1.setText(codes[selected].getText());
                    jTextField2.setText(names[selected].getText());
                    int xco=(LoginPage.hp.getWidth()-jDialog1.getWidth())/2;
                    int yco=(LoginPage.hp.getHeight()-jDialog1.getHeight())/2;
                    jDialog1.setBounds(xco,yco,jDialog1.getWidth(),jDialog1.getHeight());
                    jDialog1.setVisible(true);
                }
                
            });
            gbc.gridx=3;
            gbc.gridy=i+3;
            gbc.insets=new Insets(0,0,30,0);
            jPanel1.add(edits[i],gbc);
        }
        gbc.gridx=0;
        gbc.gridy=itemlist.size()+3;
        gbc.gridwidth=4;
        gbc.insets=new Insets(60,0,50,0);
        jPanel1.add(jButton3,gbc);
        jPanel1.revalidate();
        jPanel1.repaint();
    }
    
    public void deleteDisplay()
    {
        for(int i=0;i<itemlist.size();i++)
        {
            jPanel1.remove(sr_nos[i]);
            jPanel1.remove(codes[i]);
            jPanel1.remove(names[i]);
            jPanel1.remove(edits[i]);
        }
        jPanel1.remove(jButton3);
        GridBagConstraints gbc=new GridBagConstraints();
        gbc.gridx=0;
        gbc.gridy=3;
        gbc.gridwidth=4;
        gbc.insets=new Insets(60,0,50,0);
        jPanel1.add(jButton3,gbc);
        jPanel1.revalidate();
        jPanel1.repaint();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jDialog1 = new javax.swing.JDialog();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jDialog2 = new javax.swing.JDialog();
        jLabel9 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jDialog3 = new javax.swing.JDialog();
        jLabel10 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        jDialog4 = new javax.swing.JDialog();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jButton11 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();

        jDialog1.setTitle("Edit Item");
        jDialog1.setSize(new java.awt.Dimension(700, 600));
        jDialog1.getContentPane().setLayout(new java.awt.GridBagLayout());

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel6.setText("Edit Item");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 80, 0);
        jDialog1.getContentPane().add(jLabel6, gridBagConstraints);

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel7.setText("Code");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 40, 40);
        jDialog1.getContentPane().add(jLabel7, gridBagConstraints);

        jTextField1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jTextField1.setPreferredSize(new java.awt.Dimension(100, 50));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 40, 0);
        jDialog1.getContentPane().add(jTextField1, gridBagConstraints);

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel8.setText("Name");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 40);
        jDialog1.getContentPane().add(jLabel8, gridBagConstraints);

        jTextField2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jTextField2.setPreferredSize(new java.awt.Dimension(300, 50));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        jDialog1.getContentPane().add(jTextField2, gridBagConstraints);

        jButton4.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
        jButton4.setText("Update");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jButton4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton4KeyPressed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new java.awt.Insets(70, 0, 0, 0);
        jDialog1.getContentPane().add(jButton4, gridBagConstraints);

        jButton5.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
        jButton5.setText("Delete Item");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jButton5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton5KeyPressed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new java.awt.Insets(30, 0, 0, 0);
        jDialog1.getContentPane().add(jButton5, gridBagConstraints);

        jDialog2.setTitle("Confirmation");
        jDialog2.setSize(new java.awt.Dimension(400, 300));
        jDialog2.getContentPane().setLayout(new java.awt.GridBagLayout());

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 40)); // NOI18N
        jLabel9.setText("Delete?");
        jDialog2.getContentPane().add(jLabel9, new java.awt.GridBagConstraints());

        jButton6.setFont(new java.awt.Font("Tahoma", 0, 40)); // NOI18N
        jButton6.setText("Yes");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jButton6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton6KeyPressed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(50, 0, 0, 0);
        jDialog2.getContentPane().add(jButton6, gridBagConstraints);

        jDialog3.setTitle("Confirmation");
        jDialog3.setSize(new java.awt.Dimension(400, 300));
        jDialog3.getContentPane().setLayout(new java.awt.GridBagLayout());

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 40)); // NOI18N
        jLabel10.setText("Update?");
        jDialog3.getContentPane().add(jLabel10, new java.awt.GridBagConstraints());

        jButton7.setFont(new java.awt.Font("Tahoma", 0, 40)); // NOI18N
        jButton7.setText("Yes");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jButton7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton7KeyPressed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(50, 0, 0, 0);
        jDialog3.getContentPane().add(jButton7, gridBagConstraints);

        jDialog4.setTitle("Delete Items");
        jDialog4.setSize(new java.awt.Dimension(700, 300));
        jDialog4.getContentPane().setLayout(new java.awt.GridBagLayout());

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
        jLabel17.setText("Are you sure you want to delte all Items?");
        jDialog4.getContentPane().add(jLabel17, new java.awt.GridBagConstraints());

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel18.setText("Note: Take a backup");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        jDialog4.getContentPane().add(jLabel18, gridBagConstraints);

        jButton11.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
        jButton11.setText("Yes");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        jButton11.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton11KeyPressed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(50, 0, 0, 0);
        jDialog4.getContentPane().add(jButton11, gridBagConstraints);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Items List");

        jPanel1.setLayout(new java.awt.GridBagLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 40)); // NOI18N
        jLabel1.setText("Items List");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 40, 0);
        jPanel1.add(jLabel1, gridBagConstraints);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Sr No.");
        jLabel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        jLabel2.setPreferredSize(new java.awt.Dimension(90, 50));
        jLabel2.setRequestFocusEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 30, 30);
        jPanel1.add(jLabel2, gridBagConstraints);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Code");
        jLabel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        jLabel3.setPreferredSize(new java.awt.Dimension(150, 50));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 30, 30);
        jPanel1.add(jLabel3, gridBagConstraints);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Name");
        jLabel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        jLabel4.setPreferredSize(new java.awt.Dimension(300, 50));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 30, 30);
        jPanel1.add(jLabel4, gridBagConstraints);

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Edit");
        jLabel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        jLabel5.setPreferredSize(new java.awt.Dimension(70, 50));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 30, 0);
        jPanel1.add(jLabel5, gridBagConstraints);

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
        jButton1.setText("Back");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jButton1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton1KeyPressed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 40, 0);
        jPanel1.add(jButton1, gridBagConstraints);

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
        jButton2.setText("+Add Items");
        jButton2.setToolTipText("");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jButton2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton2KeyPressed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new java.awt.Insets(0, 209, 80, 0);
        jPanel1.add(jButton2, gridBagConstraints);

        jButton3.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
        jButton3.setText("Back");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jButton3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton3KeyPressed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.insets = new java.awt.Insets(60, 0, 50, 0);
        jPanel1.add(jButton3, gridBagConstraints);

        jButton8.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
        jButton8.setText("Save List");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jButton8.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton8KeyPressed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 80, 0);
        jPanel1.add(jButton8, gridBagConstraints);

        jButton9.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
        jButton9.setText("Clear");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        jButton9.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton9KeyPressed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 80, 0);
        jPanel1.add(jButton9, gridBagConstraints);

        jScrollPane1.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1090, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 789, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        ai=new AddItems();
        ai.setExtendedState(JFrame.MAXIMIZED_BOTH);
        ai.setVisible(true);
        HomePage.il.setVisible(false);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        LoginPage.hp.setVisible(true);
        HomePage.il.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        LoginPage.hp.setVisible(true);
        HomePage.il.setVisible(false);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        int xco=(LoginPage.hp.getWidth()-jDialog2.getWidth())/2;
        int yco=(LoginPage.hp.getHeight()-jDialog2.getHeight())/2;
        jDialog2.setBounds(xco,yco,jDialog2.getWidth(),jDialog2.getHeight());
        jDialog2.setVisible(true);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        LoginPage.datop.deleteItem(new Item(codes[selected].getText(),names[selected].getText()));
        deleteDisplay();
        initComps();
        jDialog2.setVisible(false);
        jDialog1.setVisible(false);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        String newname=jTextField2.getText().trim().toUpperCase();
        if(newname.equals(""))
        {
            JOptionPane.showMessageDialog(null,"Name can't be empty","Error: invalid input",JOptionPane.ERROR_MESSAGE);
            jTextField2.requestFocus();
            return;
        }
        int xco=(LoginPage.hp.getWidth()-jDialog3.getWidth())/2;
        int yco=(LoginPage.hp.getHeight()-jDialog3.getHeight())/2;
        jDialog3.setBounds(xco,yco,jDialog3.getWidth(),jDialog3.getWidth());
        jDialog3.setVisible(true);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        String newcode,newname;
        newcode=jTextField1.getText().trim().toUpperCase();
        newname=jTextField2.getText().trim().toUpperCase();
        if(newcode.equals(""))
            newcode=null;
        LoginPage.datop.updateItem(codes[selected].getText(),names[selected].getText(),newcode,newname);
        deleteDisplay();
        initComps();
        jDialog3.setVisible(false);
        jDialog1.setVisible(false);
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        try
        {
            String filename;
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd_MM_yyyy HH_mm_ss");  
            LocalDateTime now = LocalDateTime.now();
            String datetime=dtf.format(now);
            File file=new File(LoginPage.loggedInUser+" Item Lists");
            file.mkdir();
            filename=LoginPage.loggedInUser+" Item Lists\\items "+datetime+".txt";
            FileWriter fw=new FileWriter(filename);
            for(int i=0;i<itemlist.size();i++)
                fw.write(itemlist.get(i).code+"$"+itemlist.get(i).name+"\n");
            fw.close();
        }
        catch(Exception e){}
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
        jDialog4.setVisible(false);
        LoginPage.datop.deleteItems();
        deleteDisplay();
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton11KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton11KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyChar()=='\n')
            jButton11.doClick();
    }//GEN-LAST:event_jButton11KeyPressed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        int xco=(LoginPage.hp.getWidth()-jDialog4.getWidth())/2;
        int yco=(LoginPage.hp.getHeight()-jDialog4.getHeight())/2;
        jDialog4.setBounds(xco,yco,jDialog4.getWidth(),jDialog4.getHeight());
        jDialog4.setVisible(true);
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton1KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyChar()=='\n')
            jButton1.doClick();
    }//GEN-LAST:event_jButton1KeyPressed

    private void jButton9KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton9KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyChar()=='\n')
            jButton9.doClick();
    }//GEN-LAST:event_jButton9KeyPressed

    private void jButton8KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton8KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyChar()=='\n')
            jButton8.doClick();
    }//GEN-LAST:event_jButton8KeyPressed

    private void jButton2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton2KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyChar()=='\n')
            jButton2.doClick();
    }//GEN-LAST:event_jButton2KeyPressed

    private void jButton3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton3KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyChar()=='\n')
            jButton3.doClick();
    }//GEN-LAST:event_jButton3KeyPressed

    private void jButton4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton4KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyChar()=='\n')
            jButton4.doClick();
    }//GEN-LAST:event_jButton4KeyPressed

    private void jButton5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton5KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyChar()=='\n')
            jButton5.doClick();
    }//GEN-LAST:event_jButton5KeyPressed

    private void jButton6KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton6KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyChar()=='\n')
            jButton6.doClick();
    }//GEN-LAST:event_jButton6KeyPressed

    private void jButton7KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton7KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyChar()=='\n')
            jButton7.doClick();
    }//GEN-LAST:event_jButton7KeyPressed

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
            java.util.logging.Logger.getLogger(ItemsList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ItemsList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ItemsList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ItemsList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ItemsList().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JDialog jDialog2;
    private javax.swing.JDialog jDialog3;
    private javax.swing.JDialog jDialog4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
}
