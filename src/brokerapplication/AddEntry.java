/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brokerapplication;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.DriverManager;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Mayank Kakad
 */
public class AddEntry extends javax.swing.JFrame {

    /**
     * Creates new form AddEntry
     */
    int max_parts=999;
    Vector<Item> itemlist;
    JLabel partindex[],partcodelabel[],partnamelabel[],partproductlabel[],partquantitylabel[],partratelabel[],partitemlabel[];
    JTextField partcode[],partproduct[],partquantity[],partrate[];
    JComboBox partname[],partitemname[];
    int ind=0;
    public AddEntry() {
        initComponents();
        jButton4.setVisible(false);
        partindex=new JLabel[max_parts];
        partcodelabel=new JLabel[max_parts];
        partnamelabel=new JLabel[max_parts];
        partproductlabel=new JLabel[max_parts];
        partquantitylabel=new JLabel[max_parts];
        partratelabel=new JLabel[max_parts];
        partcode=new JTextField[max_parts];
        partproduct=new JTextField[max_parts];
        partname=new JComboBox[max_parts];
        partquantity=new JTextField[max_parts];
        partrate=new JTextField[max_parts];
        partitemname=new JComboBox[max_parts];
        partitemlabel=new JLabel[max_parts];
        initComps();
    }
    
    public void initComps()
    {
        itemlist=LoginPage.datop.getItems();
        for(int i=0;i<itemlist.size();i++)
            jComboBox4.addItem(itemlist.get(i).name);
        jTextField6.setText(HomePage.date);
        Vector<Seller> sellerlist=LoginPage.datop.getSellerList();
        for(int i=0;i<sellerlist.size();i++)
            jComboBox3.addItem(sellerlist.get(i).name);
        Vector<Buyer> buyerlist=LoginPage.datop.getBuyerList();
        for(int i=0;i<buyerlist.size();i++)
            jComboBox2.addItem(buyerlist.get(i).name);
        jComboBox1.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(jComboBox1.getSelectedIndex()==0)
                {
                    jLabel3.setText("Buyer:");
                    jLabel4.setText("Sellers:");
                    jComboBox2.removeAllItems();
                    jComboBox3.removeAllItems();
                    Vector<Buyer> buyerlist;
                    if(jTextField1.getText().trim().equals(""))
                        buyerlist=LoginPage.datop.getBuyerList();
                    else
                        buyerlist=LoginPage.datop.getSpecificBuyerList(jTextField1.getText().trim().toUpperCase());
                    for(int i=0;i<buyerlist.size();i++)
                        jComboBox2.addItem(buyerlist.get(i).name);
                    Vector<Seller> sellerlist;
                    if(jTextField2.getText().trim().equals(""))
                        sellerlist=LoginPage.datop.getSellerList();
                    else
                        sellerlist=LoginPage.datop.getSpecificSellerList(jTextField2.getText().trim().toUpperCase());
                    for(int i=0;i<sellerlist.size();i++)
                        jComboBox3.addItem(sellerlist.get(i).name);
                    changeAllPartBoxes(0);
                }
                else
                {
                    jLabel3.setText("Seller:");
                    jLabel4.setText("Buyers:");
                    jComboBox2.removeAllItems();
                    jComboBox3.removeAllItems();
                    Vector<Seller> sellerlist;
                    if(jTextField1.getText().trim().equals(""))
                        sellerlist=LoginPage.datop.getSellerList();
                    else
                        sellerlist=LoginPage.datop.getSpecificSellerList(jTextField1.getText().trim().toUpperCase());
                    for(int i=0;i<sellerlist.size();i++)
                        jComboBox2.addItem(sellerlist.get(i).name);
                    Vector<Buyer> buyerlist;
                    if(jTextField2.getText().trim().equals(""))
                        buyerlist=LoginPage.datop.getBuyerList();
                    else
                        buyerlist=LoginPage.datop.getSpecificBuyerList(jTextField2.getText().trim().toUpperCase());
                    for(int i=0;i<buyerlist.size();i++)
                        jComboBox3.addItem(buyerlist.get(i).name);
                    changeAllPartBoxes(1);
                }
            }
        });
    }
    public void changeAllPartBoxes(int a)
    {
        if(a==1)
        {
            for(int i=0;i<ind;i++)
            {
                partname[i].removeAllItems();
                Vector<Buyer> buyerlist;
                if(partcode[i].getText().trim().equals(""))
                    buyerlist=LoginPage.datop.getBuyerList();
                else
                    buyerlist=LoginPage.datop.getSpecificBuyerList(partcode[i].getText().trim().toUpperCase());
                for(int j=0;j<buyerlist.size();j++)
                    partname[i].addItem(buyerlist.get(j).name);
            }
        }
        else
        {
            for(int i=0;i<ind;i++)
            {
                partname[i].removeAllItems();
                Vector<Seller> sellerlist;
                if(partcode[i].getText().trim().equals(""))
                    sellerlist=LoginPage.datop.getSellerList();
                else
                    sellerlist=LoginPage.datop.getSpecificSellerList(partcode[i].getText().trim().toUpperCase());
                for(int j=0;j<sellerlist.size();j++)
                    partname[i].addItem(sellerlist.get(j).name);
            }
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
        java.awt.GridBagConstraints gridBagConstraints;

        jDialog1 = new javax.swing.JDialog();
        jLabel15 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jComboBox3 = new javax.swing.JComboBox<>();
        jTextField3 = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jComboBox4 = new javax.swing.JComboBox<>();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();

        jDialog1.setTitle("Confirmation");
        jDialog1.setSize(new java.awt.Dimension(400, 300));
        jDialog1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jDialog1KeyPressed(evt);
            }
        });
        jDialog1.getContentPane().setLayout(new java.awt.GridBagLayout());

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 40)); // NOI18N
        jLabel15.setText("Confirm?");
        jDialog1.getContentPane().add(jLabel15, new java.awt.GridBagConstraints());

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

        jLabel14.setText("jLabel14");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Add Entry");

        jPanel1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPanel1KeyPressed(evt);
            }
        });
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 40)); // NOI18N
        jLabel1.setText("Add Entry");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 60, 0);
        jPanel1.add(jLabel1, gridBagConstraints);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
        jLabel2.setText("By:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 100, 0);
        jPanel1.add(jLabel2, gridBagConstraints);

        jComboBox1.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Buyer", "Seller" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 100, 0);
        jPanel1.add(jComboBox1, gridBagConstraints);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 27)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Buyer:");
        jLabel3.setPreferredSize(new java.awt.Dimension(100, 50));
        jLabel3.setRequestFocusEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 60, 30);
        jPanel1.add(jLabel3, gridBagConstraints);

        jTextField1.setFont(new java.awt.Font("Tahoma", 0, 27)); // NOI18N
        jTextField1.setPreferredSize(new java.awt.Dimension(100, 50));
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField1KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField1KeyTyped(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 60, 30);
        jPanel1.add(jTextField1, gridBagConstraints);

        jComboBox2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jComboBox2.setPreferredSize(new java.awt.Dimension(300, 50));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 60, 30);
        jPanel1.add(jComboBox2, gridBagConstraints);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 27)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Sellers:");
        jLabel4.setPreferredSize(new java.awt.Dimension(100, 50));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 30, 0);
        jPanel1.add(jLabel4, gridBagConstraints);

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel5.setText("1");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 15, 0);
        jPanel1.add(jLabel5, gridBagConstraints);

        jTextField2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jTextField2.setPreferredSize(new java.awt.Dimension(100, 50));
        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField2KeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 15, 30);
        jPanel1.add(jTextField2, gridBagConstraints);

        jComboBox3.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jComboBox3.setPreferredSize(new java.awt.Dimension(300, 50));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 15, 30);
        jPanel1.add(jComboBox3, gridBagConstraints);

        jTextField3.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jTextField3.setPreferredSize(new java.awt.Dimension(100, 50));
        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });
        jTextField3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField3KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField3KeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 15, 30);
        jPanel1.add(jTextField3, gridBagConstraints);

        jButton3.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jButton3.setText("+Add");
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
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 30);
        jPanel1.add(jButton3, gridBagConstraints);

        jButton4.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jButton4.setText("-Remove");
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
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 30);
        jPanel1.add(jButton4, gridBagConstraints);

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel6.setText("Code");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 30);
        jPanel1.add(jLabel6, gridBagConstraints);

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel7.setText("Name");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 30);
        jPanel1.add(jLabel7, gridBagConstraints);

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel8.setText("Code");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 30);
        jPanel1.add(jLabel8, gridBagConstraints);

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel9.setText("Name");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 30);
        jPanel1.add(jLabel9, gridBagConstraints);

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel10.setText("Code");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 30);
        jPanel1.add(jLabel10, gridBagConstraints);

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
        jButton1.setText("Done");
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
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.insets = new java.awt.Insets(80, 0, 0, 0);
        jPanel1.add(jButton1, gridBagConstraints);

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
        jButton2.setText("Back");
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
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 60, 0);
        jPanel1.add(jButton2, gridBagConstraints);

        jTextField4.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jTextField4.setPreferredSize(new java.awt.Dimension(100, 50));
        jTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4ActionPerformed(evt);
            }
        });
        jTextField4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField4KeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 15, 30);
        jPanel1.add(jTextField4, gridBagConstraints);

        jTextField5.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jTextField5.setPreferredSize(new java.awt.Dimension(100, 50));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 15, 0);
        jPanel1.add(jTextField5, gridBagConstraints);

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel11.setText("Quantity");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 30);
        jPanel1.add(jLabel11, gridBagConstraints);

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel12.setText("Rate");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 5;
        jPanel1.add(jLabel12, gridBagConstraints);

        jTextField6.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jTextField6.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField6.setPreferredSize(new java.awt.Dimension(135, 43));
        jTextField6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField6ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 100, 0);
        jPanel1.add(jTextField6, gridBagConstraints);

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel13.setText("Date (dd-mm-yyyy):");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 100, 0);
        jPanel1.add(jLabel13, gridBagConstraints);

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
        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.insets = new java.awt.Insets(30, 0, 50, 0);
        jPanel1.add(jButton6, gridBagConstraints);

        jComboBox4.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jComboBox4.setPreferredSize(new java.awt.Dimension(200, 50));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 15, 30);
        jPanel1.add(jComboBox4, gridBagConstraints);

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel16.setText("Item");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 30);
        jPanel1.add(jLabel16, gridBagConstraints);

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 27)); // NOI18N
        jLabel17.setText("Quantity:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 60, 30);
        jPanel1.add(jLabel17, gridBagConstraints);

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 27)); // NOI18N
        jLabel18.setText("0");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 60, 30);
        jPanel1.add(jLabel18, gridBagConstraints);

        jScrollPane1.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1120, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 807, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        jButton4.setVisible(true);
        jPanel1.remove(jButton3);
        jPanel1.remove(jButton4);
        jPanel1.remove(jButton1);
        jPanel1.remove(jButton6);
        GridBagConstraints gbc=new GridBagConstraints();
        partcodelabel[ind]=new JLabel();
        partcodelabel[ind].setFont(new java.awt.Font("Tahoma",0,18));
        partcodelabel[ind].setText("Code");
        gbc.gridx=1;
        gbc.gridy=ind+ind+7;
        gbc.insets=new Insets(0,0,0,30);
        jPanel1.add(partcodelabel[ind],gbc);
        partnamelabel[ind]=new JLabel();
        partnamelabel[ind].setFont(new java.awt.Font("Tahoma",0,18));
        partnamelabel[ind].setText("Name");
        gbc.gridx=2;
        gbc.gridy=ind+ind+7;
        gbc.insets=new Insets(0,0,0,30);
        jPanel1.add(partnamelabel[ind],gbc);
        partproductlabel[ind]=new JLabel();
        partproductlabel[ind].setFont(new java.awt.Font("Tahoma",0,18));
        partproductlabel[ind].setText("Code");
        gbc.gridx=3;
        gbc.gridy=ind+ind+7;
        gbc.insets=new Insets(0,0,0,30);
        jPanel1.add(partproductlabel[ind],gbc);
        partindex[ind]=new JLabel();
        partindex[ind].setFont(new java.awt.Font("Tahoma",0,24));
        partindex[ind].setText(Integer.toString(ind+2));
        gbc.gridx=0;
        gbc.gridy=ind+ind+8;
        gbc.insets=new Insets(0,0,15,0);
        jPanel1.add(partindex[ind],gbc);
        partcode[ind]=new JTextField();
        partcode[ind].setFont(new java.awt.Font("Tahoma",0,24));
        partcode[ind].setPreferredSize(new java.awt.Dimension(100,50));
        partcode[ind].setName(Integer.toString(ind));
        partcode[ind].addKeyListener(new KeyListener(){
            @Override
            public void keyReleased(KeyEvent e) {
                JTextField source=(JTextField) e.getSource();
                int selected=Integer.parseInt(source.getName());
                Vector<Seller> sellerlist;
                Vector<Buyer> buyerlist;
                String text=partcode[selected].getText().trim().toUpperCase();
                if(text.equals(""))
                {
                    if(jComboBox1.getSelectedIndex()==0)
                    {
                        sellerlist=LoginPage.datop.getSellerList();
                        partname[selected].removeAllItems();
                        for(int i=0;i<sellerlist.size();i++)
                            partname[selected].addItem(sellerlist.get(i).name);
                    }
                    else
                    {
                        buyerlist=LoginPage.datop.getBuyerList();
                        partname[selected].removeAllItems();
                        for(int i=0;i<buyerlist.size();i++)
                            partname[selected].addItem(buyerlist.get(i).name);
                    }
                }
                else
                {
                    if(jComboBox1.getSelectedIndex()==0)
                    {
                        sellerlist=LoginPage.datop.getSpecificSellerList(text);
                        partname[selected].removeAllItems();
                        for(int i=0;i<sellerlist.size();i++)
                            partname[selected].addItem(sellerlist.get(i).name);
                    }
                    else
                    {
                        buyerlist=LoginPage.datop.getSpecificBuyerList(text);
                        partname[selected].removeAllItems();
                        for(int i=0;i<buyerlist.size();i++)
                            partname[selected].addItem(buyerlist.get(i).name);
                    }
                }
            }

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }
            
        });
        gbc.gridx=1;
        gbc.gridy=ind+ind+8;
        gbc.insets=new Insets(0,0,15,30);
        jPanel1.add(partcode[ind],gbc);
        partproduct[ind]=new JTextField();
        partproduct[ind].setFont(new java.awt.Font("Tahoma",0,24));
        partproduct[ind].setPreferredSize(new java.awt.Dimension(100,50));
        partproduct[ind].setName(Integer.toString(ind));
        partproduct[ind].addKeyListener(new KeyListener(){
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                JTextField source=(JTextField) e.getSource();
                int selected=Integer.parseInt(source.getName());
                Vector<Item> templist;
                String text=partproduct[selected].getText().trim().toUpperCase();
                if(text.equals(""))
                {
                    templist=LoginPage.datop.getItems();
                    partitemname[selected].removeAllItems();
                    for(int i=0;i<templist.size();i++)
                        partitemname[selected].addItem(templist.get(i).name);
                }
                else
                {
                    templist=LoginPage.datop.getSpecificItemList(text);
                    partitemname[selected].removeAllItems();
                    for(int i=0;i<templist.size();i++)
                        partitemname[selected].addItem(templist.get(i).name);
                }
            }
            
        });
        gbc.gridx=3;
        gbc.gridy=ind+ind+8;
        gbc.insets=new Insets(0,0,15,30);
        jPanel1.add(partproduct[ind],gbc);
        partname[ind]=new JComboBox();
        partname[ind].setFont(new java.awt.Font("Tahoma",0,24));
        partname[ind].setPreferredSize(new java.awt.Dimension(300,50));
        if(jComboBox1.getSelectedIndex()==0)
        {
            Vector<Seller> sellerlist=LoginPage.datop.getSellerList();
            for(int i=0;i<sellerlist.size();i++)
                partname[ind].addItem(sellerlist.get(i).name);
        }
        else
        {
            Vector<Buyer> buyerlist=LoginPage.datop.getBuyerList();
            for(int i=0;i<buyerlist.size();i++)
                partname[ind].addItem(buyerlist.get(i).name);
        }
        gbc.gridx=2;
        gbc.gridy=ind+ind+8;
        gbc.insets=new Insets(0,0,15,30);
        jPanel1.add(partname[ind],gbc);
        partitemlabel[ind]=new JLabel();
        partitemlabel[ind].setFont(new java.awt.Font("Tahoma",0,18));
        partitemlabel[ind].setText("Item");
        gbc.gridx=4;
        gbc.gridy=ind+ind+7;
        gbc.insets=new Insets(0,0,0,30);
        jPanel1.add(partitemlabel[ind],gbc);
        partitemname[ind]=new JComboBox();
        partitemname[ind].setFont(new java.awt.Font("Tahoma",0,24));
        partitemname[ind].setPreferredSize(new java.awt.Dimension(200,50));
        for(int i=0;i<itemlist.size();i++)
            partitemname[ind].addItem(itemlist.get(i).name);
        gbc.gridx=4;
        gbc.gridy=ind+ind+8;
        gbc.insets=new Insets(0,0,15,30);
        jPanel1.add(partitemname[ind],gbc);
        partquantitylabel[ind]=new JLabel();
        partquantitylabel[ind].setFont(new java.awt.Font("Tahoma",0,18));
        partquantitylabel[ind].setText("Quantity");
        gbc.gridx=5;
        gbc.gridy=ind+ind+7;
        gbc.insets=new Insets(0,0,0,30);
        jPanel1.add(partquantitylabel[ind],gbc);
        partratelabel[ind]=new JLabel();
        partratelabel[ind].setFont(new java.awt.Font("Tahoma",0,18));
        partratelabel[ind].setText("Rate");
        gbc.gridx=6;
        gbc.gridy=ind+ind+7;
        gbc.insets=new Insets(0,0,0,0);
        jPanel1.add(partratelabel[ind],gbc);
        partquantity[ind]=new JTextField();
        partquantity[ind].setFont(new java.awt.Font("Tahoma",0,24));
        partquantity[ind].setPreferredSize(new java.awt.Dimension(100,50));
        partquantity[ind].addKeyListener(new KeyListener(){
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                int total=0;
                String temp=jTextField4.getText().trim();
                if(!temp.equals(""))
                   total+=Integer.parseInt(temp);
                for(int i=0;i<ind;i++)
                {
                    temp=partquantity[i].getText();
                    if(!temp.equals(""))
                        total+=Integer.parseInt(temp);
                }
                jLabel18.setText(Integer.toString(total));
            }
            
        });
        gbc.gridx=5;
        gbc.gridy=ind+ind+8;
        gbc.insets=new Insets(0,0,15,30);
        jPanel1.add(partquantity[ind],gbc);
        partrate[ind]=new JTextField();
        partrate[ind].setFont(new java.awt.Font("Tahoma",0,24));
        partrate[ind].setPreferredSize(new java.awt.Dimension(100,50));
        gbc.gridx=6;
        gbc.gridy=ind+ind+8;
        gbc.insets=new Insets(0,0,15,0);
        jPanel1.add(partrate[ind],gbc);
        gbc.gridx=1;
        gbc.gridy=ind+ind+9;
        gbc.insets=new Insets(10,0,0,30);
        jPanel1.add(jButton3,gbc);
        gbc.gridx=2;
        gbc.gridy=ind+ind+9;
        gbc.insets=new Insets(10,0,0,30);
        jPanel1.add(jButton4,gbc);
        gbc.gridx=0;
        gbc.gridy=ind+ind+10;
        gbc.gridwidth=6;
        gbc.insets=new Insets(80,0,0,0);
        jPanel1.add(jButton1,gbc);
        gbc.gridx=0;
        gbc.gridy=ind+ind+11;
        gbc.gridwidth=6;
        gbc.insets=new Insets(30,0,50,0);
        jPanel1.add(jButton6,gbc);
        ind++;
        if(ind==max_parts)
            jButton3.setVisible(false);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        LoginPage.hp.setVisible(true);
        HomePage.ae.setVisible(false);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        jButton3.setVisible(true);
        ind--;
        jPanel1.remove(jButton1);
        jPanel1.remove(jButton6);
        jPanel1.remove(jButton3);
        jPanel1.remove(jButton4);
        jPanel1.remove(partcodelabel[ind]);
        jPanel1.remove(partnamelabel[ind]);
        jPanel1.remove(partproductlabel[ind]);
        jPanel1.remove(partindex[ind]);
        jPanel1.remove(partcode[ind]);
        jPanel1.remove(partname[ind]);
        jPanel1.remove(partproduct[ind]);
        jPanel1.remove(partquantitylabel[ind]);
        jPanel1.remove(partratelabel[ind]);
        jPanel1.remove(partquantity[ind]);
        jPanel1.remove(partrate[ind]);
        jPanel1.remove(partitemname[ind]);
        jPanel1.remove(partitemlabel[ind]);
        ind--;
        GridBagConstraints gbc=new GridBagConstraints();
        gbc.gridx=1;
        gbc.gridy=ind+ind+9;
        gbc.insets=new Insets(10,0,0,30);
        jPanel1.add(jButton3,gbc);
        gbc.gridx=2;
        gbc.gridy=ind+ind+9;
        gbc.insets=new Insets(10,0,0,30);
        jPanel1.add(jButton4,gbc);
        gbc.gridx=0;
        gbc.gridy=ind+ind+10;
        gbc.gridwidth=6;
        gbc.insets=new Insets(80,0,0,0);
        jPanel1.add(jButton1,gbc);
        gbc.gridx=0;
        gbc.gridy=ind+ind+11;
        gbc.gridwidth=6;
        gbc.insets=new Insets(30,0,50,0);
        jPanel1.add(jButton6,gbc);
        ind++;
        if(ind==0)
            jButton4.setVisible(false);
        jPanel1.revalidate();
        jPanel1.repaint();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jTextField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1KeyPressed

    private void jTextField1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyTyped
     // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1KeyTyped

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        Vector<Seller> sellerlist;
        Vector<Buyer> buyerlist;
        String text=jTextField1.getText().trim().toUpperCase();
        if(text.equals(""))
        {
            if(jComboBox1.getSelectedIndex()==1)
            {
                sellerlist=LoginPage.datop.getSellerList();
                jComboBox2.removeAllItems();
                for(int i=0;i<sellerlist.size();i++)
                    jComboBox2.addItem(sellerlist.get(i).name);
            }
            else
            {
                buyerlist=LoginPage.datop.getBuyerList();
                jComboBox2.removeAllItems();
                for(int i=0;i<buyerlist.size();i++)
                    jComboBox2.addItem(buyerlist.get(i).name);
            }
        }
        else
        {
            if(jComboBox1.getSelectedIndex()==1)
            {
                sellerlist=LoginPage.datop.getSpecificSellerList(text);
                jComboBox2.removeAllItems();
                for(int i=0;i<sellerlist.size();i++)
                    jComboBox2.addItem(sellerlist.get(i).name);
            }
            else
            {
                buyerlist=LoginPage.datop.getSpecificBuyerList(text);
                jComboBox2.removeAllItems();
                for(int i=0;i<buyerlist.size();i++)
                    jComboBox2.addItem(buyerlist.get(i).name);
            }
        }     // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1KeyReleased

    private void jTextField2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyReleased
        // TODO add your handling code here:
        Vector<Seller> sellerlist;
        Vector<Buyer> buyerlist;
        String text=jTextField2.getText().trim().toUpperCase();
        if(text.equals(""))
        {
            if(jComboBox1.getSelectedIndex()==0)
            {
                sellerlist=LoginPage.datop.getSellerList();
                jComboBox3.removeAllItems();
                for(int i=0;i<sellerlist.size();i++)
                    jComboBox3.addItem(sellerlist.get(i).name);
            }
            else
            {
                buyerlist=LoginPage.datop.getBuyerList();
                jComboBox3.removeAllItems();
                for(int i=0;i<buyerlist.size();i++)
                    jComboBox3.addItem(buyerlist.get(i).name);
            }
        }
        else
        {
            if(jComboBox1.getSelectedIndex()==0)
            {
                sellerlist=LoginPage.datop.getSpecificSellerList(text);
                jComboBox3.removeAllItems();
                for(int i=0;i<sellerlist.size();i++)
                    jComboBox3.addItem(sellerlist.get(i).name);
            }
            else
            {
                buyerlist=LoginPage.datop.getSpecificBuyerList(text);
                jComboBox3.removeAllItems();
                for(int i=0;i<buyerlist.size();i++)
                    jComboBox3.addItem(buyerlist.get(i).name);
            }
        }
    }//GEN-LAST:event_jTextField2KeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here
        if(jComboBox2.getItemCount()==0)
        {
            JOptionPane.showMessageDialog(null,"Name Can't be empty","Error: invalid input",JOptionPane.ERROR_MESSAGE);
            jTextField1.requestFocus();
            return;
        }
        else if(jComboBox3.getItemCount()==0)
        {
            JOptionPane.showMessageDialog(null,"Name Can't be empty","Error: invalid input",JOptionPane.ERROR_MESSAGE);
            jTextField2.requestFocus();
            return;
        }
        else if(jComboBox4.getItemCount()==0)
        {
            JOptionPane.showMessageDialog(null,"Item Can't be empty","Error: invalid input",JOptionPane.ERROR_MESSAGE);
            jTextField3.requestFocus();
            return;
        }
        else if(jTextField4.getText().trim().equals(""))
        {
            JOptionPane.showMessageDialog(null,"Quantity Can't be empty","Error: invalid input",JOptionPane.ERROR_MESSAGE);
            jTextField4.requestFocus();
            return;
        }
        else if(jTextField5.getText().trim().equals(""))
        {
            JOptionPane.showMessageDialog(null,"Rate Can't be empty","Error: invalid input",JOptionPane.ERROR_MESSAGE);
            jTextField5.requestFocus();
            return;
        }
        else
        {
            int i=0;
            for(i=0;i<ind;i++)
            {
                if(partname[i].getItemCount()==0)
                {
                    JOptionPane.showMessageDialog(null,"Name Can't be empty","Error: invalid input",JOptionPane.ERROR_MESSAGE);
                    partcode[i].requestFocus();
                    break;
                }
                else if(partitemname[i].getItemCount()==0)
                {
                    JOptionPane.showMessageDialog(null,"Item Can't be empty","Error: invalid input",JOptionPane.ERROR_MESSAGE);
                    partproduct[i].requestFocus();
                    break;
                }
                else if(partquantity[i].getText().trim().equals(""))
                {
                    JOptionPane.showMessageDialog(null,"Quantity Can't be empty","Error: invalid input",JOptionPane.ERROR_MESSAGE);
                    partquantity[i].requestFocus();
                    break;
                }
                else if(partrate[i].getText().trim().equals(""))
                {
                    JOptionPane.showMessageDialog(null,"Rate Can't be empty","Error: invalid input",JOptionPane.ERROR_MESSAGE);
                    partrate[i].requestFocus();
                    break;
                }
            }
            if(i!=ind)
            return;
        }
        int xco=(LoginPage.hp.getWidth()-jDialog1.getWidth())/2;
        int yco=(LoginPage.hp.getHeight()-jDialog1.getHeight())/2;
        jDialog1.setBounds(xco,yco,jDialog1.getWidth(),jDialog1.getHeight());
        jDialog1.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        Vector<Seller> finalsellerlist=new Vector<Seller>();
        Vector<Buyer> finalbuyerlist=new Vector<Buyer>();
        Seller mainseller;
        Buyer mainbuyer;
        Vector<String> items=new Vector<String>();
        Vector<String> rate=new Vector<String>();
        if(jComboBox1.getSelectedIndex()==1)
        {
            mainseller=new Seller(jTextField1.getText().trim().toUpperCase(),jComboBox2.getSelectedItem().toString(),0);
            finalbuyerlist.add(new Buyer(jTextField2.getText().trim().toUpperCase(),jComboBox3.getSelectedItem().toString(),Integer.parseInt(jTextField4.getText().trim())));
            items.add(jComboBox4.getSelectedItem().toString());
            rate.add(jTextField5.getText());
            for(int i=0;i<ind;i++)
            {
                finalbuyerlist.add(new Buyer(partcode[i].getText(),partname[i].getSelectedItem().toString(),Integer.parseInt(partquantity[i].getText())));
                items.add(partitemname[i].getSelectedItem().toString());
                rate.add(partrate[i].getText());
            }
            LoginPage.datop.addSellerWiseTransaction(mainseller,finalbuyerlist,items,rate,jTextField6.getText().trim());
        }
        else
        {
            mainbuyer=new Buyer(jTextField1.getText(),jComboBox2.getSelectedItem().toString(),0);
            finalsellerlist.add(new Seller(jTextField2.getText(),jComboBox3.getSelectedItem().toString(),Integer.parseInt(jTextField4.getText().trim())));
            items.add(jComboBox4.getSelectedItem().toString());
            rate.add(jTextField5.getText());
            for(int i=0;i<ind;i++)
            {
                finalsellerlist.add(new Seller(partcode[i].getText(),partname[i].getSelectedItem().toString(),Integer.parseInt(partquantity[i].getText())));
                items.add(partitemname[i].getSelectedItem().toString());
                rate.add(partrate[i].getText());
            }
            LoginPage.datop.addBuyerWiseTransaction(mainbuyer,finalsellerlist,items,rate,jTextField6.getText().trim());
        }
        LoginPage.hp.setVisible(true);
        jDialog1.setVisible(false);
        HomePage.ae.setVisible(false);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        LoginPage.hp.setVisible(true);
        HomePage.ae.setVisible(false);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton2KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyChar()=='\n')
            jButton2.doClick();
    }//GEN-LAST:event_jButton2KeyPressed

    private void jButton1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton1KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyChar()=='\n')
            jButton1.doClick();
    }//GEN-LAST:event_jButton1KeyPressed

    private void jButton6KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton6KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyChar()=='\n')
            jButton6.doClick();
    }//GEN-LAST:event_jButton6KeyPressed

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

    private void jPanel1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPanel1KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyChar()=='\n')
            jButton1.doClick();
    }//GEN-LAST:event_jPanel1KeyPressed

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

    private void jTextField3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField3KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3KeyPressed

    private void jTextField3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField3KeyReleased
        // TODO add your handling code here:
        Vector<Item> templist;
        String text=jTextField3.getText().trim().toUpperCase();
        if(text.equals(""))
        {
            templist=LoginPage.datop.getItems();
            jComboBox4.removeAllItems();
            for(int i=0;i<templist.size();i++)
                jComboBox4.addItem(templist.get(i).name);
        }
        else
        {
            templist=LoginPage.datop.getSpecificItemList(text);
            jComboBox4.removeAllItems();
            for(int i=0;i<templist.size();i++)
                jComboBox4.addItem(templist.get(i).name);
        }
    }//GEN-LAST:event_jTextField3KeyReleased

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4ActionPerformed

    private void jTextField4KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField4KeyReleased
        // TODO add your handling code here:
        int total=0;
        String temp=jTextField4.getText().trim();
        if(!temp.equals(""))
           total+=Integer.parseInt(temp);
        for(int i=0;i<ind;i++)
        {
            temp=partquantity[i].getText();
            if(!temp.equals(""))
                total+=Integer.parseInt(temp);
        }
        jLabel18.setText(Integer.toString(total));
    }//GEN-LAST:event_jTextField4KeyReleased

    private void jTextField6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField6ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

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
            java.util.logging.Logger.getLogger(AddEntry.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddEntry.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddEntry.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddEntry.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddEntry().setVisible(true);
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
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
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
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    // End of variables declaration//GEN-END:variables
}
