/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brokerapplication;

import java.awt.Desktop;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileOutputStream;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

/**
 *
 * @author Mayank Kakad
 */
public class CustomerList extends javax.swing.JFrame {

    /**
     * Creates new form CustomerList
     */
    int total,selected;
    String myFileName;
    Vector<Seller> sellerlist;
    Vector<Buyer> buyerlist;
    JLabel sr_nos[];
    JLabel codes[];
    JLabel names[];
    JLabel quantities[];
    JButton edits[];
    JLabel sellertitle,buyertitle;
    public CustomerList() {
        initComponents();
        initComps();
    }
    public void initComps()
    {
        jComboBox1.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(jComboBox1.getSelectedItem().toString().equals("All"))
                {
                    sellerlist=LoginPage.datop.getSellerList();
                    buyerlist=LoginPage.datop.getBuyerList();
                    clearPage();
                    displaySellers();
                    displayBuyers(35);
                    GridBagConstraints gbc=new GridBagConstraints();
                    gbc.gridx=0;
                    gbc.gridy=sellerlist.size()+buyerlist.size()+5;
                    gbc.insets=new Insets(40,0,0,0);
                    gbc.gridwidth=4;
                    jPanel1.add(jButton2,gbc);
                    gbc.gridx=0;
                    gbc.gridy=sellerlist.size()+buyerlist.size()+6;
                    gbc.insets=new Insets(20,0,50,0);
                    gbc.gridwidth=4;
                    jPanel1.add(jButton7,gbc);
                    jPanel1.revalidate();
                    jPanel1.repaint();
                }
                else if(jComboBox1.getSelectedItem().toString().equals("Sellers"))
                {
                    sellerlist=LoginPage.datop.getSellerList();
                    buyerlist.clear();
                    clearPage();
                    displaySellers();
                    GridBagConstraints gbc=new GridBagConstraints();
                    gbc.gridx=0;
                    gbc.gridy=sellerlist.size()+buyerlist.size()+5;
                    gbc.insets=new Insets(40,0,0,0);
                    gbc.gridwidth=4;
                    jPanel1.add(jButton2,gbc);
                    gbc.gridx=0;
                    gbc.gridy=sellerlist.size()+buyerlist.size()+6;
                    gbc.insets=new Insets(20,0,50,0);
                    gbc.gridwidth=4;
                    jPanel1.add(jButton7,gbc);
                    jPanel1.revalidate();
                    jPanel1.repaint();
                }
                else
                {
                    sellerlist.clear();
                    buyerlist=LoginPage.datop.getBuyerList();
                    clearPage();
                    displayBuyers(0);
                    GridBagConstraints gbc=new GridBagConstraints();
                    gbc.gridx=0;
                    gbc.gridy=sellerlist.size()+buyerlist.size()+5;
                    gbc.insets=new Insets(40,0,0,0);
                    gbc.gridwidth=4;
                    jPanel1.add(jButton2,gbc);
                    gbc.gridx=0;
                    gbc.gridy=sellerlist.size()+buyerlist.size()+6;
                    gbc.insets=new Insets(20,0,50,0);
                    gbc.gridwidth=4;
                    jPanel1.add(jButton7,gbc);
                    jPanel1.revalidate();
                    jPanel1.repaint();
                }
            }
        });
        sellerlist=LoginPage.datop.getSellerList();
        buyerlist=LoginPage.datop.getBuyerList();
        total=sellerlist.size()+buyerlist.size();
        sr_nos=new JLabel[total];
        codes=new JLabel[total];
        names=new JLabel[total];
        quantities=new JLabel[total];
        edits=new JButton[total];
        jPanel1.remove(jButton7);
        jPanel1.remove(jButton2);
        displaySellers();
        displayBuyers(35);
        GridBagConstraints gbc=new GridBagConstraints();
        gbc.gridx=0;
        gbc.gridy=sellerlist.size()+buyerlist.size()+5;
        gbc.insets=new Insets(40,0,0,0);
        gbc.gridwidth=4;
        jPanel1.add(jButton2,gbc);
        gbc.gridx=0;
        gbc.gridy=sellerlist.size()+buyerlist.size()+6;
        gbc.insets=new Insets(20,0,50,0);
        gbc.gridwidth=4;
        jPanel1.add(jButton7,gbc);
        jPanel1.revalidate();
        jPanel1.repaint();
        
    }
    public void displaySellers()
    {
        GridBagConstraints gbc=new GridBagConstraints();
        sellertitle=new JLabel();
        sellertitle.setFont(new java.awt.Font("Tahoma",1,27));
        sellertitle.setPreferredSize(new java.awt.Dimension(1150,50));
        sellertitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        sellertitle.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        sellertitle.setText("Sellers");
        gbc.gridx=0;
        gbc.gridy=3;
        gbc.gridwidth=5;
        gbc.insets=new Insets(0,0,30,0);
        jPanel1.add(sellertitle,gbc);
        gbc.gridwidth=1;
        for(int i=0;i<sellerlist.size();i++)
        {
            sr_nos[i]=new JLabel();
            sr_nos[i].setFont(new java.awt.Font("Tahoma",0,24));
            sr_nos[i].setPreferredSize(new java.awt.Dimension(97,50));
            sr_nos[i].setText(Integer.toString(i+1));
            sr_nos[i].setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
            sr_nos[i].setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            gbc.gridx=0;
            gbc.gridy=i+4;
            gbc.insets=new Insets(0,0,15,30);
            jPanel1.add(sr_nos[i],gbc);
            codes[i]=new JLabel();
            codes[i].setFont(new java.awt.Font("Tahoma",0,24));
            codes[i].setPreferredSize(new java.awt.Dimension(102,50));
            codes[i].setText(sellerlist.get(i).code);
            codes[i].setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
            codes[i].setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            gbc.gridx=1;
            gbc.gridy=i+4;
            gbc.insets=new Insets(0,0,15,30);
            jPanel1.add(codes[i],gbc);
            names[i]=new JLabel();
            names[i].setFont(new java.awt.Font("Tahoma",0,24));
            names[i].setPreferredSize(new java.awt.Dimension(602,50));
            names[i].setText(sellerlist.get(i).name);
            names[i].setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
            names[i].setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            gbc.gridx=2;
            gbc.gridy=i+4;
            gbc.insets=new Insets(0,0,15,30);
            jPanel1.add(names[i],gbc);
            quantities[i]=new JLabel();
            quantities[i].setFont(new java.awt.Font("Tahoma",0,24));
            quantities[i].setPreferredSize(new java.awt.Dimension(127,50));
            quantities[i].setText(Integer.toString(sellerlist.get(i).quantity));
            quantities[i].setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
            quantities[i].setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            gbc.gridx=3;
            gbc.gridy=i+4;
            gbc.insets=new Insets(0,0,15,30);
            jPanel1.add(quantities[i],gbc);
            edits[i]=new JButton();
            edits[i].setFont(new java.awt.Font("Tahoma",0,24));
            edits[i].setPreferredSize(new java.awt.Dimension(100,50));
            edits[i].setText("Edit");
            edits[i].setName(Integer.toString(i));
            gbc.gridx=4;
            gbc.gridy=i+4;
            gbc.insets=new Insets(0,0,15,0);
            edits[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JButton source=(JButton)e.getSource();
                    selected=Integer.parseInt(source.getName());
                    jComboBox2.setSelectedIndex(0);
                    jTextField1.setText(codes[selected].getText());
                    jTextField2.setText(names[selected].getText());
                    jTextField3.setText(quantities[selected].getText());
                    int basex=LoginPage.hp.getWidth();
                    int basey=LoginPage.hp.getHeight();
                    int xw=jDialog1.getWidth();
                    int yh=jDialog1.getHeight();
                    int xco=(basex-xw)/2;
                    int yco=(basey-yh)/2;
                    jDialog1.setBounds(xco,yco,xw,yh);
                    jDialog1.setVisible(true);
                }
            });
            jPanel1.add(edits[i],gbc);
        }
    }
    
    public void displayBuyers(int inp)
    {        
        GridBagConstraints gbc=new GridBagConstraints();
        buyertitle=new JLabel();
        buyertitle.setFont(new java.awt.Font("Tahoma",1,27));
        buyertitle.setText("Buyers");
        buyertitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        buyertitle.setPreferredSize(new java.awt.Dimension(1150,50));
        buyertitle.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        gbc.gridx=0;
        gbc.gridy=sellerlist.size()+4;
        gbc.gridwidth=5;
        gbc.insets=new Insets(inp,0,30,0);
        jPanel1.add(buyertitle,gbc);
        gbc.gridwidth=1;
        for(int i=0;i<buyerlist.size();i++)
        {
            sr_nos[sellerlist.size()+i]=new JLabel();
            sr_nos[sellerlist.size()+i].setFont(new java.awt.Font("Tahoma",0,24));
            sr_nos[sellerlist.size()+i].setPreferredSize(new java.awt.Dimension(97,50));
            sr_nos[sellerlist.size()+i].setText(Integer.toString(i+1));
            sr_nos[sellerlist.size()+i].setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
            sr_nos[sellerlist.size()+i].setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            gbc.gridx=0;
            gbc.gridy=sellerlist.size()+i+5;
            gbc.insets=new Insets(0,0,15,30);
            jPanel1.add(sr_nos[sellerlist.size()+i],gbc);
            codes[sellerlist.size()+i]=new JLabel();
            codes[sellerlist.size()+i].setFont(new java.awt.Font("Tahoma",0,24));
            codes[sellerlist.size()+i].setPreferredSize(new java.awt.Dimension(102,50));
            codes[sellerlist.size()+i].setText(buyerlist.get(i).code);
            codes[sellerlist.size()+i].setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
            codes[sellerlist.size()+i].setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            gbc.gridx=1;
            gbc.gridy=sellerlist.size()+i+5;
            gbc.insets=new Insets(0,0,15,30);
            jPanel1.add(codes[sellerlist.size()+i],gbc);
            names[sellerlist.size()+i]=new JLabel();
            names[sellerlist.size()+i].setFont(new java.awt.Font("Tahoma",0,24));
            names[sellerlist.size()+i].setPreferredSize(new java.awt.Dimension(602,50));
            names[sellerlist.size()+i].setText(buyerlist.get(i).name);
            names[sellerlist.size()+i].setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
            names[sellerlist.size()+i].setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            gbc.gridx=2;
            gbc.gridy=sellerlist.size()+i+5;
            gbc.insets=new Insets(0,0,15,30);
            jPanel1.add(names[sellerlist.size()+i],gbc);
            quantities[sellerlist.size()+i]=new JLabel();
            quantities[sellerlist.size()+i].setFont(new java.awt.Font("Tahoma",0,24));
            quantities[sellerlist.size()+i].setPreferredSize(new java.awt.Dimension(127,50));
            quantities[sellerlist.size()+i].setText(Integer.toString(buyerlist.get(i).quantity));
            quantities[sellerlist.size()+i].setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
            quantities[sellerlist.size()+i].setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            gbc.gridx=3;
            gbc.gridy=sellerlist.size()+i+5;
            gbc.insets=new Insets(0,0,15,30);
            jPanel1.add(quantities[sellerlist.size()+i],gbc);
            edits[sellerlist.size()+i]=new JButton();
            edits[sellerlist.size()+i].setFont(new java.awt.Font("Tahoma",0,24));
            edits[sellerlist.size()+i].setPreferredSize(new java.awt.Dimension(100,50));
            edits[sellerlist.size()+i].setText("Edit");
            edits[sellerlist.size()+i].setName(Integer.toString(sellerlist.size()+i));
            edits[sellerlist.size()+i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JButton source=(JButton)e.getSource();
                    selected=Integer.parseInt(source.getName());
                    jComboBox2.setSelectedIndex(1);
                    jTextField1.setText(codes[selected].getText());
                    jTextField2.setText(names[selected].getText());
                    jTextField3.setText(quantities[selected].getText());
                    int basex=LoginPage.hp.getWidth();
                    int basey=LoginPage.hp.getHeight();
                    int xw=jDialog1.getWidth();
                    int yh=jDialog1.getHeight();
                    int xco=(basex-xw)/2;
                    int yco=(basey-yh)/2;
                    jDialog1.setBounds(xco,yco,xw,yh);
                    jDialog1.setVisible(true);
                }
            });
            gbc.gridx=4;
            gbc.gridy=sellerlist.size()+i+5;
            gbc.insets=new Insets(0,0,15,0);
            jPanel1.add(edits[sellerlist.size()+i],gbc);
        }
    }
    
    public void clearPage()
    {
        jPanel1.remove(sellertitle);
        jPanel1.remove(buyertitle);
        for(int i=0;i<total;i++)
        {
            jPanel1.remove(sr_nos[i]);
            jPanel1.remove(codes[i]);
            jPanel1.remove(names[i]);
            jPanel1.remove(quantities[i]);
            jPanel1.remove(edits[i]);
        }
        jPanel1.remove(jButton7);
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
        jLabel3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jButton4 = new javax.swing.JButton();
        jDialog2 = new javax.swing.JDialog();
        jLabel11 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jDialog3 = new javax.swing.JDialog();
        jLabel12 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jDialog4 = new javax.swing.JDialog();
        jLabel14 = new javax.swing.JLabel();
        jButton9 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();

        jDialog1.setTitle("Edit Customer");
        jDialog1.setSize(new java.awt.Dimension(1000, 700));
        jDialog1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jDialog1KeyPressed(evt);
            }
        });
        jDialog1.getContentPane().setLayout(new java.awt.GridBagLayout());

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel3.setText("Edit Customer");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 50, 0);
        jDialog1.getContentPane().add(jLabel3, gridBagConstraints);

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Code");
        jLabel7.setPreferredSize(new java.awt.Dimension(100, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 30, 0);
        jDialog1.getContentPane().add(jLabel7, gridBagConstraints);

        jTextField1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jTextField1.setPreferredSize(new java.awt.Dimension(100, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 30, 0);
        jDialog1.getContentPane().add(jTextField1, gridBagConstraints);

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Name");
        jLabel8.setPreferredSize(new java.awt.Dimension(100, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 30, 30);
        jDialog1.getContentPane().add(jLabel8, gridBagConstraints);

        jTextField2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jTextField2.setPreferredSize(new java.awt.Dimension(400, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 30, 0);
        jDialog1.getContentPane().add(jTextField2, gridBagConstraints);

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Quantity");
        jLabel9.setPreferredSize(new java.awt.Dimension(100, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        jDialog1.getContentPane().add(jLabel9, gridBagConstraints);

        jTextField3.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jTextField3.setPreferredSize(new java.awt.Dimension(120, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        jDialog1.getContentPane().add(jTextField3, gridBagConstraints);

        jButton3.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
        jButton3.setText("Update");
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
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new java.awt.Insets(60, 0, 0, 0);
        jDialog1.getContentPane().add(jButton3, gridBagConstraints);

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Type");
        jLabel10.setPreferredSize(new java.awt.Dimension(100, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 30, 0);
        jDialog1.getContentPane().add(jLabel10, gridBagConstraints);

        jComboBox2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seller", "Buyer" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 30, 0);
        jDialog1.getContentPane().add(jComboBox2, gridBagConstraints);

        jButton4.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
        jButton4.setText("Delete Customer");
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
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new java.awt.Insets(30, 0, 0, 0);
        jDialog1.getContentPane().add(jButton4, gridBagConstraints);

        jDialog2.setTitle("Confirmation");
        jDialog2.setSize(new java.awt.Dimension(600, 300));
        jDialog2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jDialog2KeyPressed(evt);
            }
        });
        jDialog2.getContentPane().setLayout(new java.awt.GridBagLayout());

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
        jLabel11.setText("Are you sure you want to delete?");
        jDialog2.getContentPane().add(jLabel11, new java.awt.GridBagConstraints());

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
        gridBagConstraints.insets = new java.awt.Insets(60, 0, 0, 0);
        jDialog2.getContentPane().add(jButton5, gridBagConstraints);

        jDialog3.setTitle("Confirmation");
        jDialog3.setSize(new java.awt.Dimension(600, 300));
        jDialog3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jDialog3KeyPressed(evt);
            }
        });
        jDialog3.getContentPane().setLayout(new java.awt.GridBagLayout());

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
        jLabel12.setText("Are you sure you want to update?");
        jDialog3.getContentPane().add(jLabel12, new java.awt.GridBagConstraints());

        jButton6.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
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
        gridBagConstraints.insets = new java.awt.Insets(60, 0, 0, 0);
        jDialog3.getContentPane().add(jButton6, gridBagConstraints);

        jDialog4.setTitle("Print?");
        jDialog4.setSize(new java.awt.Dimension(400, 300));
        jDialog4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jDialog4KeyPressed(evt);
            }
        });
        jDialog4.getContentPane().setLayout(new java.awt.GridBagLayout());

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
        jLabel14.setText("File Saved. Print?");
        jLabel14.setToolTipText("");
        jDialog4.getContentPane().add(jLabel14, new java.awt.GridBagConstraints());

        jButton9.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
        jButton9.setText("Yes");
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
        gridBagConstraints.insets = new java.awt.Insets(50, 0, 0, 0);
        jDialog4.getContentPane().add(jButton9, gridBagConstraints);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Customers List");

        jPanel1.setLayout(new java.awt.GridBagLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 40)); // NOI18N
        jLabel1.setText("Customer List");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 80, 0);
        jPanel1.add(jLabel1, gridBagConstraints);

        jComboBox1.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All", "Sellers", "Buyers" }));
        jComboBox1.setPreferredSize(new java.awt.Dimension(155, 55));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 60, 0);
        jPanel1.add(jComboBox1, gridBagConstraints);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Sr No.");
        jLabel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));
        jLabel2.setPreferredSize(new java.awt.Dimension(95, 50));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 50, 30);
        jPanel1.add(jLabel2, gridBagConstraints);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Code");
        jLabel4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));
        jLabel4.setPreferredSize(new java.awt.Dimension(100, 50));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 50, 30);
        jPanel1.add(jLabel4, gridBagConstraints);

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Name");
        jLabel5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));
        jLabel5.setPreferredSize(new java.awt.Dimension(600, 50));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 50, 30);
        jPanel1.add(jLabel5, gridBagConstraints);

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Quantity");
        jLabel6.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));
        jLabel6.setPreferredSize(new java.awt.Dimension(125, 50));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 50, 30);
        jPanel1.add(jLabel6, gridBagConstraints);

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
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 80, 0);
        jPanel1.add(jButton1, gridBagConstraints);

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
        jButton2.setText("Print");
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
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.insets = new java.awt.Insets(40, 0, 0, 0);
        jPanel1.add(jButton2, gridBagConstraints);

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Edit");
        jLabel13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        jLabel13.setPreferredSize(new java.awt.Dimension(94, 50));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 50, 0);
        jPanel1.add(jLabel13, gridBagConstraints);

        jButton7.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
        jButton7.setText("Back");
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
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.insets = new java.awt.Insets(20, 0, 0, 0);
        jPanel1.add(jButton7, gridBagConstraints);

        jButton8.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
        jButton8.setText("Print");
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
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 80, 0);
        jPanel1.add(jButton8, gridBagConstraints);

        jScrollPane1.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1148, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 764, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        LoginPage.hp.setVisible(true);
        HomePage.cl.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

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

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        try
        {
            String filename;
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("ddMMyyyy%20HH%20mm%20ss");  
            LocalDateTime now = LocalDateTime.now();
            String datetime=dtf.format(now);
            if(jComboBox1.getSelectedIndex()==0)
            {
                JOptionPane.showMessageDialog(null,"Select Seller or Buyer, not All","Error: invalid input",JOptionPane.ERROR_MESSAGE);
                jComboBox1.requestFocus();
                return;
            }
            else if(jComboBox1.getSelectedIndex()==1)
            {
                File file=new File("SellerLists");
                file.mkdir();
                filename="SellerLists\\SellerList"+datetime+".docx";
            }
            else
            {
                File file=new File("BuyerLists");
                file.mkdir();
                filename="BuyerLists\\BuyerList"+datetime+".docx";
            }
            XWPFDocument doc=new XWPFDocument();
            FileOutputStream fos=new FileOutputStream(new File(filename));
            XWPFParagraph para1=doc.createParagraph();
            para1.setAlignment(ParagraphAlignment.CENTER);
            XWPFRun para1run1=para1.createRun();
            para1run1.setBold(true);
            para1run1.setFontFamily("Calibri");
            para1run1.setFontSize(18);
            para1run1.setText(LoginPage.datop.getName(LoginPage.loggedInUser).toUpperCase());
            para1run1.addBreak();
            XWPFRun para1run2=para1.createRun();
            para1run2.setBold(true);
            para1run2.setFontFamily("Calibri");
            para1run2.setFontSize(16);
            para1run2.setText(jComboBox1.getSelectedItem().toString().substring(0,jComboBox1.getSelectedItem().toString().length()-1)+" wise List");
            XWPFTable table=doc.createTable();
            XWPFTableRow row1=table.getRow(0);
            row1.getCell(0).getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(1000));
            row1.addNewTableCell().getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(7200));
            row1.addNewTableCell().getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(1600));
            XWPFRun rowcell[]=new XWPFRun[3];
            for(int i=0;i<rowcell.length;i++)
            {
                row1.getCell(i).setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);
                row1.getCell(i).getParagraphs().get(0).setAlignment(ParagraphAlignment.CENTER);
                rowcell[i]=row1.getCell(i).getParagraphs().get(0).createRun();
                rowcell[i].setBold(true);
                rowcell[i].setFontFamily("Calibri");
                rowcell[i].setFontSize(14);
            }
            rowcell[0].setText("Sr No.");
            if(jComboBox1.getSelectedIndex()==1)
                rowcell[1].setText("Seller");
            else
                rowcell[1].setText("Buyer");
            rowcell[2].setText("Qty Total");
            if(jComboBox1.getSelectedIndex()==1)
            {
                for(int i=0;i<sellerlist.size();i++)
                {
                    XWPFTableRow rowi=table.createRow();
                    XWPFRun rowcelli[]=new XWPFRun[3];
                    for(int j=0;j<rowcell.length;j++)
                    {
                        rowi.getCell(j).setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);
                        if(j==1)
                            rowi.getCell(j).getParagraphs().get(0).setAlignment(ParagraphAlignment.LEFT);
                        else
                            rowi.getCell(j).getParagraphs().get(0).setAlignment(ParagraphAlignment.CENTER);
                        rowcelli[j]=rowi.getCell(j).getParagraphs().get(0).createRun();
                        rowcelli[j].setFontFamily("Calibri");
                        rowcelli[j].setFontSize(14);
                    }
                    rowcelli[0].setText(Integer.toString(i+1));
                    rowcelli[1].setText(sellerlist.get(i).code+"- "+sellerlist.get(i).name);
                    rowcelli[2].setText(Integer.toString(sellerlist.get(i).quantity));
                }
            }
            else
            {
                for(int i=0;i<buyerlist.size();i++)
                {
                    XWPFTableRow rowi=table.createRow();
                    XWPFRun rowcelli[]=new XWPFRun[3];
                    for(int j=0;j<rowcell.length;j++)
                    {
                        rowi.getCell(j).setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);
                        if(j==1)
                            rowi.getCell(j).getParagraphs().get(0).setAlignment(ParagraphAlignment.LEFT);
                        else
                            rowi.getCell(j).getParagraphs().get(0).setAlignment(ParagraphAlignment.CENTER);
                        rowcelli[j]=rowi.getCell(j).getParagraphs().get(0).createRun();
                        rowcelli[j].setFontFamily("Calibri");
                        rowcelli[j].setFontSize(14);
                    }
                    rowcelli[0].setText(Integer.toString(i+1));
                    rowcelli[1].setText(buyerlist.get(i).code+"- "+buyerlist.get(i).name);
                    rowcelli[2].setText(Integer.toString(buyerlist.get(i).quantity));
                }
            }
            doc.write(fos);
            fos.close();
            askPrint(filename);
        }
        catch(Exception e){e.printStackTrace();}
    }//GEN-LAST:event_jButton2ActionPerformed

    public void askPrint(String filename)
    {
        myFileName=filename;
        int xco=(LoginPage.hp.getWidth()-jDialog4.getWidth())/2;
        int yco=(LoginPage.hp.getHeight()-jDialog4.getHeight())/2;
        jDialog4.setBounds(xco,yco,jDialog4.getWidth(),jDialog4.getHeight());
        jDialog4.setVisible(true);
    }
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        int xco=(LoginPage.hp.getWidth()-jDialog2.getWidth())/2;
        int yco=(LoginPage.hp.getHeight()-jDialog2.getHeight())/2;
        jDialog2.setBounds(xco,yco,jDialog2.getWidth(),jDialog2.getHeight());
        jDialog2.setVisible(true);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        String type;
        String username=LoginPage.loggedInUser;
        String code=codes[selected].getText();
        String name=names[selected].getText();
        if(selected<sellerlist.size())
            type="seller";
        else
            type="buyer";
        LoginPage.datop.deleteCustomer(username,code,name,type);
        jDialog1.setVisible(false);
        jDialog2.setVisible(false);
        int temp=jComboBox1.getSelectedIndex();
        clearPage();
        initComps();
        jComboBox1.setSelectedIndex((temp+1)%3);
        jComboBox1.setSelectedIndex(temp);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        if(jTextField2.getText().equals(""))
        {
            JOptionPane.showMessageDialog(null,"Name can't be empty","Error: Invalid input",JOptionPane.ERROR_MESSAGE);
            jTextField2.requestFocus();
        }
        else if(jTextField3.getText().equals(""))
        {
            JOptionPane.showMessageDialog(null,"Quantity can't be empty","Error: Invalid input",JOptionPane.ERROR_MESSAGE);
            jTextField3.requestFocus();
        }
        else
        {
            int xco=(LoginPage.hp.getWidth()-jDialog3.getWidth())/2;
            int yco=(LoginPage.hp.getHeight()-jDialog3.getHeight())/2;
            jDialog3.setBounds(xco,yco,jDialog3.getWidth(),jDialog3.getHeight());
            jDialog3.setVisible(true);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        String type;
        String username=LoginPage.loggedInUser;
        String ogcode=codes[selected].getText();
        String ogname=names[selected].getText();
        String newtype,newcode,newname,newquantity;
        if(selected<sellerlist.size())
            type="seller";
        else
            type="buyer";
        if(jTextField1.getText().equals("null")||jTextField1.getText().equals(""))
            newcode=null;
        else
            newcode=jTextField1.getText().toUpperCase();
        newtype=jComboBox2.getSelectedItem().toString().toLowerCase();
        newname=jTextField2.getText();
        newquantity=jTextField3.getText();
        LoginPage.datop.updateCustomer(username,ogcode,ogname,newcode,newname.toUpperCase(),newquantity,type,newtype);
        jDialog1.setVisible(false);
        jDialog3.setVisible(false);
        int temp=jComboBox1.getSelectedIndex();
        clearPage();
        initComps();
        jComboBox1.setSelectedIndex((temp+1)%3);
        jComboBox1.setSelectedIndex(temp);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        LoginPage.hp.setVisible(true);
        HomePage.cl.setVisible(false);
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton7KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton7KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyChar()=='\n')
            jButton7.doClick();
    }//GEN-LAST:event_jButton7KeyPressed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        jButton2.doClick();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton8KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton8KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyChar()=='\n')
            jButton8.doClick();
    }//GEN-LAST:event_jButton8KeyPressed

    private void jButton3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton3KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyChar()=='\n')
            jButton3.doClick();
    }//GEN-LAST:event_jButton3KeyPressed

    private void jDialog1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jDialog1KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyChar()=='\n')
            jButton3.doClick();
    }//GEN-LAST:event_jDialog1KeyPressed

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

    private void jDialog2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jDialog2KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyChar()=='\n')
            jButton5.doClick();
    }//GEN-LAST:event_jDialog2KeyPressed

    private void jButton6KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton6KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyChar()=='\n')
            jButton6.doClick();
    }//GEN-LAST:event_jButton6KeyPressed

    private void jDialog3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jDialog3KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyChar()=='\n')
            jButton6.doClick();
    }//GEN-LAST:event_jDialog3KeyPressed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        try
        {
            Desktop.getDesktop().print(new File(myFileName));
        }
        catch(Exception e){}
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton9KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton9KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyChar()=='\n')
            jButton9.doClick();
    }//GEN-LAST:event_jButton9KeyPressed

    private void jDialog4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jDialog4KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyChar()=='\n')
            jButton9.doClick();
    }//GEN-LAST:event_jDialog4KeyPressed

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
            java.util.logging.Logger.getLogger(CustomerList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CustomerList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CustomerList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CustomerList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CustomerList().setVisible(true);
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
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JDialog jDialog2;
    private javax.swing.JDialog jDialog3;
    private javax.swing.JDialog jDialog4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
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
    // End of variables declaration//GEN-END:variables
}
