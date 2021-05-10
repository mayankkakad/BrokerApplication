/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brokerapplication;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.DriverManager;
import java.util.Vector;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Mayank Kakad
 */
public class AddCustomer extends javax.swing.JFrame {

    /**
     * Creates new form AddCustomer
     */
    int basex,basey,max_count=999;
    int gap=30,gapy=50;
    int count=0;
    Vector<Seller> sellerlist;
    Vector<Buyer> buyerlist;
    Vector<String> sellerplacelist;
    Vector<String> buyerplacelist;
    Insets numbering,type,code,cusname,tlabel,clabel,nlabel,addbutton,removebutton,donebutton,backbutton,plabel,place;
    JLabel numbers[],typelabel[],codelabel[],namelabel[],placelabel[];
    JComboBox typebox[];
    JTextField codefield[],namefield[],placefield[];
    
    public AddCustomer() {
        initComponents();
        numbers=new JLabel[max_count];
        typelabel=new JLabel[max_count];
        codelabel=new JLabel[max_count];
        namelabel=new JLabel[max_count];
        typebox=new JComboBox[max_count];
        codefield=new JTextField[max_count];
        namefield=new JTextField[max_count];
        placelabel=new JLabel[max_count];
        placefield=new JTextField[max_count];
        numbering=new Insets(0,0,0,50);
        type=new Insets(0,0,0,40);
        code=new Insets(0,0,0,40);
        cusname=new Insets(0,0,0,40);
        tlabel=new Insets(30,0,5,40);
        clabel=new Insets(30,0,5,40);
        nlabel=new Insets(30,0,5,40);
        addbutton=new Insets(40,0,0,40);
        removebutton=new Insets(40,0,0,40);
        donebutton=new Insets(60,0,0,0);
        backbutton=new Insets(30,0,50,0);
        plabel=new Insets(30,0,5,0);
        place=new Insets(0,0,0,0);
    }
    public void initComps()
    {
        jButton2.setVisible(false);
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
        jButton5 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jTextField2 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jTextField3 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();

        jDialog1.setTitle("Confirmation");
        jDialog1.setSize(new java.awt.Dimension(400, 300));
        jDialog1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jDialog1KeyPressed(evt);
            }
        });
        jDialog1.getContentPane().setLayout(new java.awt.GridBagLayout());

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 40)); // NOI18N
        jLabel6.setText("Confirm?");
        jDialog1.getContentPane().add(jLabel6, new java.awt.GridBagConstraints());

        jButton5.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
        jButton5.setText("Yes");
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
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(40, 0, 0, 0);
        jDialog1.getContentPane().add(jButton5, gridBagConstraints);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Add New Customer");
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        jScrollPane1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jScrollPane1KeyPressed(evt);
            }
        });

        jPanel1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPanel1KeyPressed(evt);
            }
        });
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 40)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Add New Customer");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 70, 0);
        jPanel1.add(jLabel1, gridBagConstraints);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("1");
        jLabel2.setPreferredSize(new java.awt.Dimension(50, 50));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 50);
        jPanel1.add(jLabel2, gridBagConstraints);

        jTextField1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jTextField1.setPreferredSize(new java.awt.Dimension(100, 50));
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField1KeyPressed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 40);
        jPanel1.add(jTextField1, gridBagConstraints);

        jComboBox1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seller", "Buyer", "Both" }));
        jComboBox1.setPreferredSize(new java.awt.Dimension(101, 50));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 40);
        jPanel1.add(jComboBox1, gridBagConstraints);

        jTextField2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jTextField2.setPreferredSize(new java.awt.Dimension(400, 50));
        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField2KeyPressed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 40);
        jPanel1.add(jTextField2, gridBagConstraints);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setText("Type");
        jLabel3.setToolTipText("");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(30, 0, 5, 40);
        jPanel1.add(jLabel3, gridBagConstraints);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText("Code");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(30, 0, 5, 40);
        jPanel1.add(jLabel4, gridBagConstraints);

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setText("Name");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(30, 0, 5, 40);
        jPanel1.add(jLabel5, gridBagConstraints);

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jButton1.setText("+Add");
        jButton1.setPreferredSize(new java.awt.Dimension(101, 40));
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
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(40, 0, 0, 40);
        jPanel1.add(jButton1, gridBagConstraints);

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jButton2.setText("-Remove");
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
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(40, 0, 0, 40);
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
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 70, 0);
        jPanel1.add(jButton3, gridBagConstraints);

        jButton4.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
        jButton4.setText("Done");
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
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.insets = new java.awt.Insets(60, 0, 0, 0);
        jPanel1.add(jButton4, gridBagConstraints);

        jButton6.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
        jButton6.setText("Back");
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
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.insets = new java.awt.Insets(30, 0, 0, 0);
        jPanel1.add(jButton6, gridBagConstraints);

        jTextField3.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jTextField3.setPreferredSize(new java.awt.Dimension(150, 50));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        jPanel1.add(jTextField3, gridBagConstraints);

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setText("Place");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(30, 0, 5, 0);
        jPanel1.add(jLabel7, gridBagConstraints);

        jButton7.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
        jButton7.setText("By File");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 70, 0);
        jPanel1.add(jButton7, gridBagConstraints);

        jScrollPane1.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1110, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 777, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        jPanel1.remove(jButton1);
        jPanel1.remove(jButton2);
        jPanel1.remove(jButton6);
        GridBagConstraints gbc=new GridBagConstraints();
        numbers[count]=new JLabel();
        numbers[count].setFont(new java.awt.Font("Tahoma",0,24));
        numbers[count].setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        numbers[count].setPreferredSize(new java.awt.Dimension(50,50));
        numbers[count].setText(Integer.toString(count+2));
        gbc.gridx=0;
        gbc.gridy=count+count+4;
        gbc.insets=numbering;
        jPanel1.add(numbers[count],gbc);
        typelabel[count]=new JLabel();
        typelabel[count].setFont(new java.awt.Font("Tahoma",0,18));
        typelabel[count].setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        typelabel[count].setText("Type");
        gbc.gridx=1;
        gbc.gridy=count+count+3;
        gbc.insets=tlabel;
        jPanel1.add(typelabel[count],gbc);
        codelabel[count]=new JLabel();
        codelabel[count].setFont(new java.awt.Font("Tahoma",0,18));
        codelabel[count].setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        codelabel[count].setText("Code");
        gbc.gridx=2;
        gbc.gridy=count+count+3;
        gbc.insets=clabel;
        jPanel1.add(codelabel[count],gbc);
        namelabel[count]=new JLabel();
        namelabel[count].setFont(new java.awt.Font("Tahoma",0,18));
        namelabel[count].setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        namelabel[count].setText("Name");
        gbc.gridx=3;
        gbc.gridy=count+count+3;
        gbc.insets=nlabel;
        jPanel1.add(namelabel[count],gbc);
        typebox[count]=new JComboBox();
        typebox[count].setFont(new java.awt.Font("Tahoma",0,24));
        typebox[count].setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seller", "Buyer", "Both" }));
        typebox[count].setPreferredSize(new java.awt.Dimension(101,50));
        gbc.gridx=1;
        gbc.gridy=count+count+4;
        gbc.insets=type;
        jPanel1.add(typebox[count],gbc);
        codefield[count]=new JTextField();
        codefield[count].setFont(new java.awt.Font("Tahoma",0,24));
        codefield[count].setPreferredSize(new java.awt.Dimension(101,50));
        gbc.gridx=2;
        gbc.gridy=count+count+4;
        gbc.insets=code;
        jPanel1.add(codefield[count],gbc);
        namefield[count]=new JTextField();
        namefield[count].setFont(new java.awt.Font("Tahoma",0,24));
        namefield[count].setPreferredSize(new java.awt.Dimension(400,50));
        gbc.gridx=3;
        gbc.gridy=count+count+4;
        gbc.insets=cusname;
        jPanel1.add(namefield[count],gbc);
        placelabel[count]=new JLabel();
        placelabel[count].setFont(new java.awt.Font("Tahoma",0,18));
        placelabel[count].setText("Place");
        gbc.gridx=4;
        gbc.gridy=count+count+3;
        gbc.insets=plabel;
        jPanel1.add(placelabel[count],gbc);
        placefield[count]=new JTextField();
        placefield[count].setFont(new java.awt.Font("Tahoma",0,24));
        placefield[count].setPreferredSize(new java.awt.Dimension(150,50));
        gbc.gridx=4;
        gbc.gridy=count+count+4;
        gbc.insets=place;
        jPanel1.add(placefield[count],gbc);
        gbc.gridx=1;
        gbc.gridy=count+count+5;
        gbc.insets=addbutton;
        jPanel1.add(jButton1,gbc);
        gbc.gridx=2;
        gbc.gridy=count+count+5;
        gbc.insets=removebutton;
        jPanel1.add(jButton2,gbc);
        jButton2.setVisible(true);
        gbc.gridx=0;
        gbc.gridy=count+count+6;
        gbc.gridwidth=5;
        gbc.insets=donebutton;
        jPanel1.add(jButton4,gbc);
        gbc.gridx=0;
        gbc.gridy=count+count+7;
        gbc.gridwidth=5;
        gbc.insets=backbutton;
        jPanel1.add(jButton6,gbc);
        count++;
        if(count==max_count)
            jButton1.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        LoginPage.hp.setVisible(true);
        HomePage.ac.setVisible(false);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        GridBagLayout gbl=(GridBagLayout) jPanel1.getLayout();
        GridBagConstraints gbc=new GridBagConstraints();
        count--;
        jPanel1.remove(numbers[count]);
        jPanel1.remove(typelabel[count]);
        jPanel1.remove(codelabel[count]);
        jPanel1.remove(namelabel[count]);
        jPanel1.remove(typebox[count]);
        jPanel1.remove(codefield[count]);
        jPanel1.remove(namefield[count]);
        jPanel1.remove(placelabel[count]);
        jPanel1.remove(placefield[count]);
        count--;
        gbc.gridx=1;
        gbc.gridy=count+count+5;
        gbc.insets=addbutton;
        gbl.setConstraints(jButton1, gbc);
        gbc.gridx=2;
        gbc.gridy=count+count+5;
        gbc.insets=removebutton;
        gbl.setConstraints(jButton2, gbc);
        gbc.gridx=0;
        gbc.gridy=count+count+6;
        gbc.gridwidth=5;
        gbc.insets=donebutton;
        gbl.setConstraints(jButton4,gbc);
        gbc.gridx=0;
        gbc.gridy=count+count+7;
        gbc.gridwidth=5;
        gbc.insets=backbutton;
        gbl.setConstraints(jButton6, gbc);
        jButton1.setVisible(true);
        count++;
        if(count==0)
            jButton2.setVisible(false);
        jPanel1.revalidate();
        jPanel1.repaint();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        boolean flag=true;
        if(jTextField2.getText().equals(""))
        {
            JOptionPane.showMessageDialog(null,"Name can't be empty", "Error: Invalid input", JOptionPane.ERROR_MESSAGE);
            jTextField2.requestFocus();
            flag=false;
        }
        else
        {
            for(int i=0;i<count;i++)
            {
                if(namefield[i].getText().equals(""))
                {
                    JOptionPane.showMessageDialog(null,"Name can't be empty", "Error: Invalid input", JOptionPane.ERROR_MESSAGE);
                    namefield[i].requestFocus();
                    flag=false;
                    break;
                }
            }
        }
        if(flag)
        {
            sellerlist=new Vector<Seller>();
            buyerlist=new Vector<Buyer>();
            sellerplacelist=new Vector<String>();
            buyerplacelist=new Vector<String>();
            Vector<Integer> sellerindex=new Vector<Integer>();
            Vector<Integer> buyerindex=new Vector<Integer>();
            String t=null;
            String t2=null;
            if(jTextField1.getText().equals(""))
                t=null;
            else
                t=jTextField1.getText().toUpperCase();
            if(jTextField3.getText().equals(""))
                t2=null;
            else
                t2=jTextField3.getText().toUpperCase();
            if(jComboBox1.getSelectedItem().toString().equals("Seller"))
            {
                sellerlist.add(new Seller(t,jTextField2.getText().toUpperCase(),0));
                sellerplacelist.add(t2);
                sellerindex.add(-1);
            }
            else if(jComboBox1.getSelectedItem().toString().equals("Buyer"))
            {
                buyerlist.add(new Buyer(t,jTextField2.getText().toUpperCase(),0));
                buyerplacelist.add(t2);
                buyerindex.add(-1);
            }
            else
            {
                sellerlist.add(new Seller(t,jTextField2.getText().toUpperCase(),0));
                sellerplacelist.add(t2);
                buyerlist.add(new Buyer(t,jTextField2.getText().toUpperCase(),0));
                buyerplacelist.add(t2);
                sellerindex.add(-1);
                buyerindex.add(-1);
            }
            for(int i=0;i<count;i++)
            {
                String temp=null;
                String temp2=null;
                if(codefield[i].getText().equals(""))
                    temp=null;
                else
                    temp=codefield[i].getText().toUpperCase();
                if(placefield[i].getText().equals(""))
                    temp2=null;
                else
                    temp2=placefield[i].getText().toUpperCase();
                if(typebox[i].getSelectedItem().toString().equals("Seller"))
                {
                    sellerlist.add(new Seller(temp,namefield[i].getText().toUpperCase(),0));
                    sellerplacelist.add(temp2);
                    sellerindex.add(i);
                }
                else if(typebox[i].getSelectedItem().toString().equals("Buyer"))
                {
                    buyerlist.add(new Buyer(temp,namefield[i].getText().toUpperCase(),0));
                    buyerplacelist.add(temp2);
                    buyerindex.add(i);
                }
                else
                {
                    sellerlist.add(new Seller(temp,namefield[i].getText().toUpperCase(),0));
                    sellerplacelist.add(temp2);
                    buyerlist.add(new Buyer(temp,namefield[i].getText().toUpperCase(),0));
                    buyerplacelist.add(temp2);
                    sellerindex.add(i);
                    buyerindex.add(i);
                }
            }
            int sellerError=-1;
            if(sellerlist.size()>0)
            {
                sellerError=LoginPage.datop.checkSellerDatabase(sellerlist);
                if(sellerError!=-1)
                {
                    sellerError=sellerindex.get(sellerError);
                    if(sellerError==-1)
                    {
                        JOptionPane.showMessageDialog(null,"Already a seller","Error: invalid input",JOptionPane.ERROR_MESSAGE);
                        jTextField2.requestFocus();
                        return;
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null,"Already a seller","Error: invalid input",JOptionPane.ERROR_MESSAGE);
                        namefield[sellerError].requestFocus();
                        return;
                    }
                }
            }
            int buyerError=-1;
            if(buyerlist.size()>0)
            {
                buyerError=LoginPage.datop.checkBuyerDatabase(buyerlist);
                if(buyerError!=-1)
                {
                    buyerError=buyerindex.get(buyerError);
                    if(buyerError==-1)
                    {
                        JOptionPane.showMessageDialog(null,"Already a buyer","Error: invalid input",JOptionPane.ERROR_MESSAGE);
                        jTextField2.requestFocus();
                        return;
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null,"Already a buyer","Error: invalid input",JOptionPane.ERROR_MESSAGE);
                        namefield[buyerError].requestFocus();
                        return;
                    }
                }
            }
            basex=HomePage.ac.getWidth();
            basey=HomePage.ac.getHeight();
            int w=jDialog1.getWidth();
            int h=jDialog1.getHeight();
            int xc=(basex-w)/2;
            int yc=(basey-h)/2;
            jDialog1.setBounds(xc,yc,w,h);
            jDialog1.setVisible(true);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        if(sellerlist.size()>0)
            LoginPage.datop.addSeller(sellerlist,sellerplacelist);
        if(buyerlist.size()>0)
            LoginPage.datop.addBuyer(buyerlist,buyerplacelist);
        LoginPage.hp.setVisible(true);
        jDialog1.setVisible(false);
        HomePage.ac.setVisible(false);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jPanel1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPanel1KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyChar()=='\n')
            jButton4.doClick();
    }//GEN-LAST:event_jPanel1KeyPressed

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyChar()=='\n')
            jButton4.doClick();
    }//GEN-LAST:event_formKeyPressed

    private void jScrollPane1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jScrollPane1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jScrollPane1KeyPressed

    private void jTextField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1KeyPressed

    private void jTextField2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2KeyPressed

    private void jButton1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton1KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyChar()=='\n')
            jButton1.doClick();
    }//GEN-LAST:event_jButton1KeyPressed

    private void jButton2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton2KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyChar()=='\n')
            jButton2.doClick();
    }//GEN-LAST:event_jButton2KeyPressed

    private void jButton4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton4KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyChar()=='\n')
            jButton4.doClick();
    }//GEN-LAST:event_jButton4KeyPressed

    private void jButton3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton3KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyChar()=='\n')
            jButton3.doClick();
    }//GEN-LAST:event_jButton3KeyPressed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        LoginPage.hp.setVisible(true);
        HomePage.ac.setVisible(false);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton6KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton6KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyChar()=='\n')
            jButton6.doClick();
    }//GEN-LAST:event_jButton6KeyPressed

    private void jButton5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton5KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyChar()=='\n')
            jButton5.doClick();
    }//GEN-LAST:event_jButton5KeyPressed

    private void jDialog1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jDialog1KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyChar()=='\n')
            jButton5.doClick();
    }//GEN-LAST:event_jDialog1KeyPressed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        try
        {
            JFileChooser chooser=new JFileChooser();
            chooser.showOpenDialog(null);
            FileReader fr=new FileReader(chooser.getCurrentDirectory()+"\\"+chooser.getSelectedFile().getName());
            BufferedReader br=new BufferedReader(fr);
            String s=br.readLine();
            if(s==null)
                return;
            if(s.equals("sellers"))
            {
                Vector<Seller> newsellerlist=new Vector<Seller>();
                Vector<String> newplacelist=new Vector<String>();
                while((s=br.readLine())!=null)
                {
                    String customer[]=s.split("\\$");
                    newsellerlist.add(new Seller(customer[0],customer[1],Integer.parseInt(customer[2])));
                    newplacelist.add(customer[3]);
                }
                LoginPage.datop.addSeller(newsellerlist,newplacelist);
            }
            else if(s.equals("buyers"))
            {
                Vector<Buyer> newbuyerlist=new Vector<Buyer>();
                Vector<String> newplacelist=new Vector<String>();
                while((s=br.readLine())!=null)
                {
                    String customer[]=s.split("\\$");
                    newbuyerlist.add(new Buyer(customer[0],customer[1],Integer.parseInt(customer[2])));
                    newplacelist.add(customer[3]);
                }
                LoginPage.datop.addBuyer(newbuyerlist,newplacelist);
            }
            br.close();
            fr.close();
            LoginPage.hp.setVisible(true);
            HomePage.ac.setVisible(false);
        }
        catch(Exception e){e.printStackTrace();}
    }//GEN-LAST:event_jButton7ActionPerformed

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
            java.util.logging.Logger.getLogger(AddCustomer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddCustomer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddCustomer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddCustomer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddCustomer().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    // End of variables declaration//GEN-END:variables
}
