/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brokerapplication;

import java.awt.Desktop;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileOutputStream;
import java.math.BigInteger;
import java.sql.DriverManager;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Comparator;
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
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageMar;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr;

/**
 *
 * @author Mayank Kakad
 */
public class BrokerHistory extends javax.swing.JFrame {

    /**
     * Creates new form BrokerHistory
     */
    Vector<Transaction> transactionsToDisplay;
    
    String myFileName;
    JLabel sr_nos[],dates[],times[],sellers[],buyers[],items[],quantities[],rates[];
    JLabel totalQuantity;
    JButton edits[];
    int selected;
    int displayed=0;
    public BrokerHistory() {
        initComponents();
        initComps();
    }
    
    public void initComps()
    {
        Vector<String> datesVector=LoginPage.datop.getDates();
        jComboBox1.addItem("All");
        for(int i=datesVector.size()-1;i>=0;i--)
            jComboBox1.addItem(datesVector.get(i));
        transactionsToDisplay=LoginPage.datop.getTransactions("all");
        Collections.sort(transactionsToDisplay, new Comparator<Transaction>() {
            @Override
            public int compare(Transaction t1, Transaction t2) {
                int d1 = Integer.parseInt(t1.date.substring(0, 2));
                int d2 = Integer.parseInt(t2.date.substring(0, 2));
                int m1 = Integer.parseInt(t1.date.substring(3, 5));
                int m2 = Integer.parseInt(t2.date.substring(3, 5));
                int y1 = Integer.parseInt(t1.date.substring(6, 9));
                int y2 = Integer.parseInt(t2.date.substring(6, 9));
                int hr1 = Integer.parseInt(t1.time.substring(0, 2));
                int hr2 = Integer.parseInt(t2.time.substring(0, 2));
                int min1 = Integer.parseInt(t1.time.substring(3, 5));
                int min2 = Integer.parseInt(t2.time.substring(3, 5));
                if(y1 == y2) {
                    if(m1 == m2) {
                        if(d1 == d2) {
                            if(hr1 == hr2) return min2 - min1;
                            else return hr2 - hr1;
                        }
                        else return d2 - d1;
                    }
                    else return m2 - m1;
                }
                else return y2 - y1;
            }
        });
        sr_nos=new JLabel[transactionsToDisplay.size()];
        dates=new JLabel[transactionsToDisplay.size()];
        times=new JLabel[transactionsToDisplay.size()];
        sellers=new JLabel[transactionsToDisplay.size()];
        buyers=new JLabel[transactionsToDisplay.size()];
        items=new JLabel[transactionsToDisplay.size()];
        quantities=new JLabel[transactionsToDisplay.size()];
        rates=new JLabel[transactionsToDisplay.size()];
        edits=new JButton[transactionsToDisplay.size()];
        jButton9.setVisible(false);
        if(displayed+100>=transactionsToDisplay.size())
            jButton10.setVisible(false);
        showDisplay();
        jComboBox1.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent e) {
                deleteDisplay();
                transactionsToDisplay=LoginPage.datop.getTransactions(jComboBox1.getSelectedItem().toString().toLowerCase());
                displayed=0;
                if(displayed+100>=transactionsToDisplay.size())
                    jButton10.setVisible(false);
                else
                    jButton10.setVisible(true);
                jButton9.setVisible(false);
                showDisplay();
            }
            
        });
    }
    
    public void showDisplay()
    {
        jPanel1.remove(jButton2);
        GridBagConstraints gbc=new GridBagConstraints();
        gbc.gridwidth=1;
        int i=0;
        for(i=displayed;i<displayed+100&&i<transactionsToDisplay.size();i++)
        {
            sr_nos[i]=new JLabel();
            sr_nos[i].setFont(new java.awt.Font("Tahoma",0,20));
            sr_nos[i].setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
            sr_nos[i].setPreferredSize(new java.awt.Dimension(82,40));
            sr_nos[i].setText(Integer.toString(i+1));
            sr_nos[i].setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            gbc.gridx=0;
            gbc.gridy=i+3;
            gbc.insets=new Insets(0,0,15,5);
            jPanel1.add(sr_nos[i],gbc);
            dates[i]=new JLabel();
            dates[i].setFont(new java.awt.Font("Tahoma",0,20));
            dates[i].setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
            dates[i].setPreferredSize(new java.awt.Dimension(112,40));
            dates[i].setText(transactionsToDisplay.get(i).date);
            dates[i].setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            gbc.gridx=1;
            gbc.gridy=i+3;
            gbc.insets=new Insets(0,0,15,5);
            jPanel1.add(dates[i],gbc);
            times[i]=new JLabel();
            times[i].setFont(new java.awt.Font("Tahoma",0,20));
            times[i].setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
            times[i].setPreferredSize(new java.awt.Dimension(66,40));
            times[i].setText(transactionsToDisplay.get(i).time.substring(0,5));
            times[i].setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            gbc.gridx=2;
            gbc.gridy=i+3;
            gbc.insets=new Insets(0,0,15,5);
            jPanel1.add(times[i],gbc);
            sellers[i]=new JLabel();
            sellers[i].setFont(new java.awt.Font("Tahoma",0,20));
            sellers[i].setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
            sellers[i].setPreferredSize(new java.awt.Dimension(282,40));
            sellers[i].setText(transactionsToDisplay.get(i).seller_code+"- "+transactionsToDisplay.get(i).seller_name);
            sellers[i].setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            gbc.gridx=3;
            gbc.gridy=i+3;
            gbc.insets=new Insets(0,0,15,5);
            jPanel1.add(sellers[i],gbc);
            buyers[i]=new JLabel();
            buyers[i].setFont(new java.awt.Font("Tahoma",0,20));
            buyers[i].setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
            buyers[i].setPreferredSize(new java.awt.Dimension(282,40));
            buyers[i].setText(transactionsToDisplay.get(i).buyer_code+"- "+transactionsToDisplay.get(i).buyer_name);
            buyers[i].setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            gbc.gridx=4;
            gbc.gridy=i+3;
            gbc.insets=new Insets(0,0,15,5);
            jPanel1.add(buyers[i],gbc);
            items[i]=new JLabel();
            items[i].setFont(new java.awt.Font("Tahoma",0,20));
            items[i].setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
            items[i].setPreferredSize(new java.awt.Dimension(122,40));
            items[i].setText(transactionsToDisplay.get(i).item);
            items[i].setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            gbc.gridx=5;
            gbc.gridy=i+3;
            gbc.insets=new Insets(0,0,15,5);
            jPanel1.add(items[i],gbc);
            quantities[i]=new JLabel();
            quantities[i].setFont(new java.awt.Font("Tahoma",0,20));
            quantities[i].setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
            quantities[i].setPreferredSize(new java.awt.Dimension(102,40));
            quantities[i].setText(Integer.toString(transactionsToDisplay.get(i).quantity));
            quantities[i].setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            gbc.gridx=6;
            gbc.gridy=i+3;
            gbc.insets=new Insets(0,0,15,5);
            jPanel1.add(quantities[i],gbc);
            rates[i]=new JLabel();
            rates[i].setFont(new java.awt.Font("Tahoma",0,20));
            rates[i].setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
            rates[i].setPreferredSize(new java.awt.Dimension(102,40));
            rates[i].setText(Integer.toString(transactionsToDisplay.get(i).rate));
            rates[i].setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            gbc.gridx=7;
            gbc.gridy=i+3;
            gbc.insets=new Insets(0,0,15,5);
            jPanel1.add(rates[i],gbc);
            edits[i]=new JButton();
            edits[i].setFont(new java.awt.Font("Tahoma",0,20));
            edits[i].setPreferredSize(new java.awt.Dimension(69,40));
            edits[i].setText("Edit");
            edits[i].setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            edits[i].setName(Integer.toString(i));
            edits[i].addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    JButton clickedButton=(JButton)e.getSource();
                    selected=Integer.parseInt(clickedButton.getName());
                    jTextField1.setText(dates[selected].getText());
                    jTextField2.setText(times[selected].getText());
                    jLabel17.setText(sellers[selected].getText());
                    jLabel19.setText(buyers[selected].getText());
                    jTextField3.setText(items[selected].getText());
                    jTextField4.setText(quantities[selected].getText());
                    jTextField5.setText(rates[selected].getText());
                    int xco=(LoginPage.hp.getWidth()-jDialog2.getWidth())/2;
                    int yco=(LoginPage.hp.getHeight()-jDialog2.getHeight())/2;
                    jDialog2.setBounds(xco,yco,jDialog2.getWidth(),jDialog2.getHeight());
                    jDialog2.setVisible(true);
                }
                
            });
            gbc.gridx=8;
            gbc.gridy=i+3;
            gbc.insets=new Insets(0,0,15,0);
            jPanel1.add(edits[i],gbc);
        }
        if(!jComboBox1.getSelectedItem().toString().equalsIgnoreCase("all"))
        {
            String selectedDate=jComboBox1.getSelectedItem().toString();
            String totquan=Integer.toString(LoginPage.datop.getTotalQuantity(selectedDate));
            totalQuantity=new JLabel();
            totalQuantity.setFont(new java.awt.Font("Tahoma",1,20));
            totalQuantity.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
            totalQuantity.setPreferredSize(new java.awt.Dimension(102,40));
            totalQuantity.setText(totquan);
            totalQuantity.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            gbc.gridx=6;
            gbc.gridy=i+3;
            gbc.insets=new Insets(0,0,15,5);
            jPanel1.add(totalQuantity,gbc);
        }
        else
        {
            int sum=0;
            Vector<String> dateVector=LoginPage.datop.getDates();
            for(int k=0;k<dateVector.size();k++)
                sum+=LoginPage.datop.getTotalQuantity(dateVector.get(k));
            String totquan=Integer.toString(sum);
            totalQuantity=new JLabel();
            totalQuantity.setFont(new java.awt.Font("Tahoma",1,20));
            totalQuantity.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
            totalQuantity.setPreferredSize(new java.awt.Dimension(102,40));
            totalQuantity.setText(totquan);
            totalQuantity.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            gbc.gridx=6;
            gbc.gridy=i+3;
            gbc.insets=new Insets(0,0,15,5);
            jPanel1.add(totalQuantity,gbc);
        }
        gbc.gridx=0;
        gbc.gridy=i+4;
        gbc.gridwidth=9;
        gbc.insets=new Insets(60,-300,0,0);
        jPanel1.add(jButton9,gbc);
        gbc.gridx=0;
        gbc.gridy=i+4;
        gbc.gridwidth=9;
        gbc.insets=new Insets(60,300,0,0);
        jPanel1.add(jButton10,gbc);
        gbc.gridx=0;
        gbc.gridy=i+5;
        gbc.gridwidth=9;
        gbc.insets=new Insets(50,0,50,0);
        jPanel1.add(jButton2,gbc);
        jPanel1.revalidate();
        jPanel1.repaint();
    }
    
    public void deleteDisplay()
    {
        for(int i=displayed;i<displayed+100&&i<transactionsToDisplay.size();i++)
        {
            jPanel1.remove(sr_nos[i]);
            jPanel1.remove(dates[i]);
            jPanel1.remove(times[i]);
            jPanel1.remove(sellers[i]);
            jPanel1.remove(buyers[i]);
            jPanel1.remove(items[i]);
            jPanel1.remove(quantities[i]);
            jPanel1.remove(rates[i]);
            jPanel1.remove(edits[i]);
            jPanel1.revalidate();
            jPanel1.repaint();
        }
        if(totalQuantity!=null)
        {
            jPanel1.remove(totalQuantity);
            jPanel1.revalidate();
            jPanel1.repaint();
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
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jDialog2 = new javax.swing.JDialog();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jDialog3 = new javax.swing.JDialog();
        jLabel23 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        jDialog4 = new javax.swing.JDialog();
        jLabel24 = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();
        jDialog5 = new javax.swing.JDialog();
        jLabel25 = new javax.swing.JLabel();
        jButton13 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();

        jDialog1.setTitle("Delete Transaction History");
        jDialog1.setSize(new java.awt.Dimension(750, 300));
        jDialog1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jDialog1KeyPressed(evt);
            }
        });
        jDialog1.getContentPane().setLayout(new java.awt.GridBagLayout());

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
        jLabel5.setText("Are you sure you want to clear all Transactions?");
        jDialog1.getContentPane().add(jLabel5, new java.awt.GridBagConstraints());

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel7.setText("Note: Take a backup");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        jDialog1.getContentPane().add(jLabel7, gridBagConstraints);

        jButton4.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
        jButton4.setText("Yes");
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
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(50, 0, 0, 0);
        jDialog1.getContentPane().add(jButton4, gridBagConstraints);

        jDialog2.setTitle("Edit Transaction");
        jDialog2.setSize(new java.awt.Dimension(550, 700));
        jDialog2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jDialog2KeyPressed(evt);
            }
        });
        jDialog2.getContentPane().setLayout(new java.awt.GridBagLayout());

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 35)); // NOI18N
        jLabel13.setText("Edit Transaction");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 50, 0);
        jDialog2.getContentPane().add(jLabel13, gridBagConstraints);

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel14.setText("Date");
        jLabel14.setPreferredSize(new java.awt.Dimension(50, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 15, 0);
        jDialog2.getContentPane().add(jLabel14, gridBagConstraints);

        jTextField1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jTextField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField1.setToolTipText("");
        jTextField1.setPreferredSize(new java.awt.Dimension(135, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 15, 0);
        jDialog2.getContentPane().add(jTextField1, gridBagConstraints);

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel15.setText("Time");
        jLabel15.setPreferredSize(new java.awt.Dimension(52, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 15, 0);
        jDialog2.getContentPane().add(jLabel15, gridBagConstraints);

        jTextField2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jTextField2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField2.setPreferredSize(new java.awt.Dimension(75, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 15, 0);
        jDialog2.getContentPane().add(jTextField2, gridBagConstraints);

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel16.setText("Seller:");
        jLabel16.setPreferredSize(new java.awt.Dimension(66, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 15, 0);
        jDialog2.getContentPane().add(jLabel16, gridBagConstraints);

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setPreferredSize(new java.awt.Dimension(400, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 15, 0);
        jDialog2.getContentPane().add(jLabel17, gridBagConstraints);

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel18.setText("Buyer:");
        jLabel18.setPreferredSize(new java.awt.Dimension(69, 40));
        jLabel18.setRequestFocusEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 15, 0);
        jDialog2.getContentPane().add(jLabel18, gridBagConstraints);

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setPreferredSize(new java.awt.Dimension(400, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 15, 0);
        jDialog2.getContentPane().add(jLabel19, gridBagConstraints);

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel20.setText("Item");
        jLabel20.setPreferredSize(new java.awt.Dimension(50, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 15, 0);
        jDialog2.getContentPane().add(jLabel20, gridBagConstraints);

        jTextField3.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jTextField3.setPreferredSize(new java.awt.Dimension(200, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 15, 0);
        jDialog2.getContentPane().add(jTextField3, gridBagConstraints);

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel21.setText("Quantity");
        jLabel21.setPreferredSize(new java.awt.Dimension(89, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 15, 0);
        jDialog2.getContentPane().add(jLabel21, gridBagConstraints);

        jTextField4.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jTextField4.setPreferredSize(new java.awt.Dimension(150, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 15, 0);
        jDialog2.getContentPane().add(jTextField4, gridBagConstraints);

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel22.setText("Rate");
        jLabel22.setPreferredSize(new java.awt.Dimension(49, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        jDialog2.getContentPane().add(jLabel22, gridBagConstraints);

        jTextField5.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jTextField5.setPreferredSize(new java.awt.Dimension(150, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        jDialog2.getContentPane().add(jTextField5, gridBagConstraints);

        jButton5.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
        jButton5.setText("Update");
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
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new java.awt.Insets(40, 0, 0, 0);
        jDialog2.getContentPane().add(jButton5, gridBagConstraints);

        jButton6.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
        jButton6.setText("Delete Transaction");
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
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new java.awt.Insets(15, 0, 0, 0);
        jDialog2.getContentPane().add(jButton6, gridBagConstraints);

        jDialog3.setTitle("Confirmation");
        jDialog3.setSize(new java.awt.Dimension(700, 300));
        jDialog3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jDialog3KeyPressed(evt);
            }
        });
        jDialog3.getContentPane().setLayout(new java.awt.GridBagLayout());

        jLabel23.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
        jLabel23.setText("Are you sure you want to delete the Transaction?");
        jDialog3.getContentPane().add(jLabel23, new java.awt.GridBagConstraints());

        jButton7.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
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

        jDialog4.setTitle("Confirmation");
        jDialog4.setSize(new java.awt.Dimension(700, 300));
        jDialog4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jDialog4KeyPressed(evt);
            }
        });
        jDialog4.getContentPane().setLayout(new java.awt.GridBagLayout());

        jLabel24.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
        jLabel24.setText("Are you sure you want to update the Transaction?");
        jDialog4.getContentPane().add(jLabel24, new java.awt.GridBagConstraints());

        jButton8.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
        jButton8.setText("Yes");
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
        gridBagConstraints.insets = new java.awt.Insets(50, 0, 0, 0);
        jDialog4.getContentPane().add(jButton8, gridBagConstraints);

        jDialog5.setTitle("Print?");
        jDialog5.setSize(new java.awt.Dimension(400, 300));
        jDialog5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jDialog5KeyPressed(evt);
            }
        });
        jDialog5.getContentPane().setLayout(new java.awt.GridBagLayout());

        jLabel25.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
        jLabel25.setText("File Saved. Print?");
        jDialog5.getContentPane().add(jLabel25, new java.awt.GridBagConstraints());

        jButton13.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
        jButton13.setText("Yes");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });
        jButton13.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton13KeyPressed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(50, 0, 0, 0);
        jDialog5.getContentPane().add(jButton13, gridBagConstraints);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Broker Transaction History");

        jPanel1.setVerifyInputWhenFocusTarget(false);
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 40)); // NOI18N
        jLabel1.setText("Broker Transaction History");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 9;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 100, 0);
        jPanel1.add(jLabel1, gridBagConstraints);

        jComboBox1.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
        jComboBox1.setPreferredSize(new java.awt.Dimension(200, 50));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 10;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 60, 300);
        jPanel1.add(jComboBox1, gridBagConstraints);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Sr No.");
        jLabel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jLabel2.setPreferredSize(new java.awt.Dimension(80, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 30, 5);
        jPanel1.add(jLabel2, gridBagConstraints);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Date");
        jLabel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jLabel3.setPreferredSize(new java.awt.Dimension(110, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 30, 5);
        jPanel1.add(jLabel3, gridBagConstraints);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Time");
        jLabel4.setToolTipText("");
        jLabel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jLabel4.setPreferredSize(new java.awt.Dimension(64, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 30, 5);
        jPanel1.add(jLabel4, gridBagConstraints);

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Seller");
        jLabel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jLabel6.setPreferredSize(new java.awt.Dimension(280, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 30, 5);
        jPanel1.add(jLabel6, gridBagConstraints);

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Buyer");
        jLabel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jLabel8.setPreferredSize(new java.awt.Dimension(280, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 30, 5);
        jPanel1.add(jLabel8, gridBagConstraints);

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Item");
        jLabel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jLabel9.setPreferredSize(new java.awt.Dimension(120, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 30, 5);
        jPanel1.add(jLabel9, gridBagConstraints);

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Quantity");
        jLabel10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jLabel10.setPreferredSize(new java.awt.Dimension(100, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 30, 5);
        jPanel1.add(jLabel10, gridBagConstraints);

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Rate");
        jLabel11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jLabel11.setPreferredSize(new java.awt.Dimension(100, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 30, 5);
        jPanel1.add(jLabel11, gridBagConstraints);

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Edit");
        jLabel12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jLabel12.setPreferredSize(new java.awt.Dimension(65, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 30, 0);
        jPanel1.add(jLabel12, gridBagConstraints);

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
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 100, 0);
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
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 9;
        gridBagConstraints.insets = new java.awt.Insets(50, 0, 50, 0);
        jPanel1.add(jButton2, gridBagConstraints);

        jButton3.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
        jButton3.setText("Clear All");
        jButton3.setPreferredSize(new java.awt.Dimension(200, 50));
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
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 100, 0);
        jPanel1.add(jButton3, gridBagConstraints);

        jButton9.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jButton9.setText("Previous");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 9;
        gridBagConstraints.insets = new java.awt.Insets(60, -300, 0, 0);
        jPanel1.add(jButton9, gridBagConstraints);

        jButton10.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jButton10.setText("Next");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 9;
        gridBagConstraints.insets = new java.awt.Insets(60, 300, 0, 0);
        jPanel1.add(jButton10, gridBagConstraints);

        jButton11.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
        jButton11.setText("Clear");
        jButton11.setPreferredSize(new java.awt.Dimension(200, 50));
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 10;
        gridBagConstraints.insets = new java.awt.Insets(0, 300, 60, 0);
        jPanel1.add(jButton11, gridBagConstraints);

        jButton12.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
        jButton12.setText("Save");
        jButton12.setMaximumSize(new java.awt.Dimension(99, 44));
        jButton12.setMinimumSize(new java.awt.Dimension(99, 44));
        jButton12.setPreferredSize(new java.awt.Dimension(200, 50));
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 60, 0);
        jPanel1.add(jButton12, gridBagConstraints);

        jScrollPane1.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1108, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        LoginPage.hp.setVisible(true);
        HomePage.bh.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        LoginPage.hp.setVisible(true);
        HomePage.bh.setVisible(false);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        int xco=(LoginPage.hp.getWidth()-jDialog1.getWidth())/2;
        int yco=(LoginPage.hp.getHeight()-jDialog1.getHeight())/2;
        jDialog1.setBounds(xco,yco,jDialog1.getWidth(),jDialog1.getHeight());
        jDialog1.setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        jDialog1.setVisible(false);
        String sel = jComboBox1.getSelectedItem().toString();
        if(sel.equalsIgnoreCase("All"))
            LoginPage.datop.deleteTransactions();
        else
            LoginPage.datop.deleteTransactionsForDate(sel.toLowerCase());
        deleteDisplay();
        transactionsToDisplay=LoginPage.datop.getTransactions(jComboBox1.getSelectedItem().toString().toLowerCase());
        showDisplay();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        int xco=(LoginPage.hp.getWidth()-jDialog3.getWidth())/2;
        int yco=(LoginPage.hp.getHeight()-jDialog3.getHeight())/2;
        jDialog3.setBounds(xco,yco,jDialog3.getWidth(),jDialog3.getHeight());
        jDialog3.setVisible(true);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        jDialog3.setVisible(false);
        jDialog2.setVisible(false);
        LoginPage.datop.deleteParticularTransaction(transactionsToDisplay.get(selected));
        deleteDisplay();
        transactionsToDisplay=LoginPage.datop.getTransactions(jComboBox1.getSelectedItem().toString().toLowerCase());
        showDisplay();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        jDialog4.setVisible(false);
        jDialog2.setVisible(false);
        LoginPage.datop.updateTransaction(transactionsToDisplay.get(selected),jTextField1.getText(),jTextField2.getText(),jTextField3.getText(),Integer.parseInt(jTextField4.getText()),Integer.parseInt(jTextField5.getText()));
        deleteDisplay();
        transactionsToDisplay=LoginPage.datop.getTransactions(jComboBox1.getSelectedItem().toString().toLowerCase());
        showDisplay();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        if(jTextField1.getText().trim().equals(""))
        {
            JOptionPane.showMessageDialog(null,"Date can't be empty","Error: invalid input",JOptionPane.ERROR_MESSAGE);
            jTextField1.requestFocus();
        }
        else if(jTextField2.getText().trim().equals(""))
        {
            JOptionPane.showMessageDialog(null,"Time can't be empty","Error: invalid input",JOptionPane.ERROR_MESSAGE);
            jTextField2.requestFocus();
        }
        else if(jTextField3.getText().trim().equals(""))
        {
            JOptionPane.showMessageDialog(null,"Item can't be empty","Error: invalid input",JOptionPane.ERROR_MESSAGE);
            jTextField3.requestFocus();
        }
        else if(jTextField4.getText().trim().equals(""))
        {
            JOptionPane.showMessageDialog(null,"Quantity can't be empty","Error: invalid input",JOptionPane.ERROR_MESSAGE);
            jTextField4.requestFocus();
        }
        else if(jTextField5.getText().trim().equals(""))
        {
            JOptionPane.showMessageDialog(null,"Rate can't be empty","Error: invalid input",JOptionPane.ERROR_MESSAGE);
            jTextField5.requestFocus();
        }
        else
        {
            int xco=(LoginPage.hp.getWidth()-jDialog4.getWidth())/2;
            int yco=(LoginPage.hp.getHeight()-jDialog4.getHeight())/2;
            jDialog4.setBounds(xco,yco,jDialog4.getWidth(),jDialog4.getHeight());
            jDialog4.setVisible(true);
        }
    }//GEN-LAST:event_jButton5ActionPerformed

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

    private void jDialog1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jDialog1KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyChar()=='\n')
            jButton4.doClick();
    }//GEN-LAST:event_jDialog1KeyPressed

    private void jButton5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton5KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyChar()=='\n')
            jButton5.doClick();
    }//GEN-LAST:event_jButton5KeyPressed

    private void jDialog2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jDialog2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jDialog2KeyPressed

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

    private void jDialog3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jDialog3KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyChar()=='\n')
            jButton7.doClick();
    }//GEN-LAST:event_jDialog3KeyPressed

    private void jButton8KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton8KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyChar()=='\n')
            jButton8.doClick();
    }//GEN-LAST:event_jButton8KeyPressed

    private void jDialog4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jDialog4KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyChar()=='\n')
            jButton8.doClick();
    }//GEN-LAST:event_jDialog4KeyPressed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
        deleteDisplay();
        displayed+=100;
        if(displayed+100>=transactionsToDisplay.size())
            jButton10.setVisible(false);
        jButton9.setVisible(true);
        showDisplay();
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        deleteDisplay();
        displayed-=100;
        if(displayed==0)
            jButton9.setVisible(false);
        jButton10.setVisible(true);
        showDisplay();
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
        int xco=(LoginPage.hp.getWidth()-jDialog1.getWidth())/2;
        int yco=(LoginPage.hp.getHeight()-jDialog1.getHeight())/2;
        String sel = jComboBox1.getSelectedItem().toString();
        if(!sel.equalsIgnoreCase("All"))
            jLabel7.setText("For: " + jComboBox1.getSelectedItem().toString().toLowerCase());
        jDialog1.setBounds(xco,yco,jDialog1.getWidth(),jDialog1.getHeight());
        jDialog1.setVisible(true);
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    public void askPrint(String filename)
    {
        myFileName=filename;
        int xco=(LoginPage.hp.getWidth()-jDialog5.getWidth())/2;
        int yco=(LoginPage.hp.getHeight()-jDialog5.getHeight())/2;
        jDialog5.setBounds(xco,yco,jDialog5.getWidth(),jDialog5.getHeight());
        jDialog5.setVisible(true);
    }
    
    public String getTotalQuantity(Vector<Transaction> temp)
    {
        int total=0;
        for(int i=0;i<temp.size();i++)
            total+=temp.get(i).quantity;
        return Integer.toString(total);
    }
    
    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // TODO add your handling code here:
        try
        {
            if(jComboBox1.getSelectedItem().toString().equals("All"))
            {
                JOptionPane.showMessageDialog(null,"No Date selected","Error: invalid input",JOptionPane.ERROR_MESSAGE);
                jComboBox1.requestFocus();
                return;
            }
            
            Bill b=LoginPage.datop.getBillFormat();
            if(b==null)
            {
                JOptionPane.showMessageDialog(null,"Bill Format not yet set","Error: invalid input",JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            String filename;
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd_MM_yyyy HH_mm_ss");  
            LocalDateTime now = LocalDateTime.now();
            String datetime=dtf.format(now);
            File file=new File(LoginPage.loggedInUser+" Broker History");
            file.mkdir();
            filename=LoginPage.loggedInUser+" Broker History\\"+datetime+".docx";
            String leftheader = b.left.substring(b.left.indexOf(";;@@##$$") + 8);
            String leftmain = b.left.substring(0, b.left.indexOf(";;@@##$$"));
            String rightheader = b.right.substring(b.right.indexOf(";;@@##$$") + 8);
            String rightmain = b.right.substring(0, b.right.indexOf(";;@@##$$"));
            String leftlines[]=leftmain.split("\n");
            String rightlines[]=rightmain.split("\n");
            XWPFDocument doc=new XWPFDocument();
            CTSectPr sectPr = doc.getDocument().getBody().addNewSectPr();
            CTPageMar pageMar = sectPr.addNewPgMar();
            pageMar.setLeft(BigInteger.valueOf(100));
            pageMar.setRight(BigInteger.valueOf(100));
            pageMar.setTop(BigInteger.valueOf(300));
            pageMar.setBottom(BigInteger.valueOf(100));
            XWPFTable headerTable = doc.createTable();
            headerTable.setTopBorder(XWPFTable.XWPFBorderType.NONE, 0, 0, "");
            headerTable.setLeftBorder(XWPFTable.XWPFBorderType.NONE, 0, 0, "");
            headerTable.setRightBorder(XWPFTable.XWPFBorderType.NONE, 0, 0, "");
            headerTable.setBottomBorder(XWPFTable.XWPFBorderType.NONE, 0, 0, "");
            headerTable.setInsideVBorder(XWPFTable.XWPFBorderType.NONE, 0, 0, "");
            headerTable.setInsideHBorder(XWPFTable.XWPFBorderType.NONE, 0, 0, "");
            XWPFTableRow headrow = headerTable.getRow(0);
            headrow.getCell(0).getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(6000));
            headrow.addNewTableCell().getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(6000));
            headrow.getCell(0).setVerticalAlignment(XWPFTableCell.XWPFVertAlign.TOP);
            headrow.getCell(0).getParagraphs().get(0).setAlignment(ParagraphAlignment.LEFT);
            headrow.getCell(1).setVerticalAlignment(XWPFTableCell.XWPFVertAlign.TOP);
            headrow.getCell(1).getParagraphs().get(0).setAlignment(ParagraphAlignment.RIGHT);
            XWPFRun leftheadertext = headrow.getCell(0).getParagraphs().get(0).createRun();
            XWPFRun rightheadertext = headrow.getCell(1).getParagraphs().get(0).createRun();
            leftheadertext.setFontFamily("Calibri");
            leftheadertext.setFontSize(8);
            leftheadertext.setText(leftheader);
            rightheadertext.setFontFamily("Calibri");
            rightheadertext.setFontSize(8);
            rightheadertext.setText(rightheader);
            FileOutputStream fos=new FileOutputStream(new File(filename));
            XWPFTable addresstable=doc.createTable();
            addresstable.setTopBorder(XWPFTable.XWPFBorderType.NONE, 0, 0, "");
            addresstable.setLeftBorder(XWPFTable.XWPFBorderType.NONE, 0, 0, "");
            addresstable.setRightBorder(XWPFTable.XWPFBorderType.NONE, 0, 0, "");
            addresstable.setBottomBorder(XWPFTable.XWPFBorderType.NONE, 0, 0, "");
            addresstable.setInsideVBorder(XWPFTable.XWPFBorderType.NONE, 0, 0, "");
            addresstable.setInsideHBorder(XWPFTable.XWPFBorderType.NONE, 0, 0, "");
            XWPFTableRow addrow1=addresstable.getRow(0);
            addrow1.getCell(0).getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(6000));
            addrow1.addNewTableCell().getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(6000));
            XWPFRun[] leftcell = new XWPFRun[leftlines.length];
            addrow1.getCell(0).setVerticalAlignment(XWPFTableCell.XWPFVertAlign.TOP);
            addrow1.getCell(0).getParagraphs().get(0).setAlignment(ParagraphAlignment.LEFT);
            for(int j = 0; j < leftlines.length; j++) {
                leftcell[j]=addrow1.getCell(0).getParagraphs().get(0).createRun();
                leftcell[j].setFontFamily("Calibri");
                leftcell[j].setFontSize(12);
                leftcell[j].setText(leftlines[j]);
                if(j != leftlines.length - 1)
                    leftcell[j].addBreak();
            }
            addrow1.getCell(1).setVerticalAlignment(XWPFTableCell.XWPFVertAlign.TOP);
            addrow1.getCell(1).getParagraphs().get(0).setAlignment(ParagraphAlignment.RIGHT);
            XWPFRun[] rightcell = new XWPFRun[rightlines.length];
            for(int j = 0; j < rightlines.length; j++) {
                rightcell[j]=addrow1.getCell(1).getParagraphs().get(0).createRun();
                rightcell[j].setFontFamily("Calibri");
                rightcell[j].setFontSize(12);
                rightcell[j].setText(rightlines[j]);
                if(j != rightlines.length - 1)
                    rightcell[j].addBreak();
            }
            int ind = b.broker_name.length();
            if(b.broker_name.contains(";;@@##$$"))
                ind = b.broker_name.indexOf(";;@@##$$");
            String bname = b.broker_name.substring(0, ind);
            if(ind == b.broker_name.length())
                ind -= 8;
            String bsubname = b.broker_name.substring(ind+8);
            XWPFParagraph para2=doc.createParagraph();
            para2.setAlignment(ParagraphAlignment.CENTER);
            XWPFRun name=para2.createRun();
            name.setFontFamily("Calibri");
            name.setBold(true);
            name.setFontSize(16);
            name.setText(bname);
            name.addBreak();
            XWPFRun subname=para2.createRun();
            subname.setFontFamily("Calibri");
            subname.setBold(true);
            subname.setFontSize(12);
            subname.setText(bsubname);
            subname.addBreak();
            XWPFRun address=para2.createRun();
            address.setFontFamily("Calibri");
            address.setFontSize(12);
            address.setText(b.broker_address);
            XWPFParagraph para3=doc.createParagraph();
            para3.setAlignment(ParagraphAlignment.CENTER);
            XWPFRun title=para3.createRun();
            title.setFontFamily("Calibri");
            title.setFontSize(14);
            title.setBold(true);
            title.setText("All Transactions on " + jComboBox1.getSelectedItem().toString());
            title.addBreak();
            XWPFRun period=para3.createRun();
            period.setFontFamily("Calibri");
            period.setFontSize(12);
            period.setText(b.bill_period);
            {
                XWPFTable table=doc.createTable();
                XWPFTableRow row1=table.getRow(0);
                row1.getCell(0).getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(400));
                row1.addNewTableCell().getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(4250));
                row1.addNewTableCell().getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(4250));
                row1.addNewTableCell().getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(1200));
                row1.addNewTableCell().getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(1000));
                row1.addNewTableCell().getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(1000));
                XWPFRun rowcell[]=new XWPFRun[6];
                for(int i=0;i<rowcell.length;i++)
                {
                    row1.getCell(i).setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);
                    row1.getCell(i).getParagraphs().get(0).setAlignment(ParagraphAlignment.CENTER);
                    rowcell[i]=row1.getCell(i).getParagraphs().get(0).createRun();
                    rowcell[i].setBold(true);
                    rowcell[i].setFontFamily("Calibri");
                    rowcell[i].setFontSize(12);
                }
                rowcell[0].setText("SR. NO.");
                rowcell[1].setText("SELLER");
                rowcell[2].setText("BUYER");
                rowcell[3].setText("ITEM");
                rowcell[4].setText("QUANTITY");
                rowcell[5].setText("RATE");
                for(int i=0;i<transactionsToDisplay.size();i++)
                {
                    XWPFTableRow rowi=table.createRow();
                    XWPFRun rowcelli[]=new XWPFRun[6];
                    for(int j=0;j<rowcell.length;j++)
                    {
                        rowi.getCell(j).setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);
                        rowi.getCell(j).getParagraphs().get(0).setAlignment(ParagraphAlignment.CENTER);
                        rowcelli[j]=rowi.getCell(j).getParagraphs().get(0).createRun();
                        rowcelli[j].setFontFamily("Calibri");
                        rowcelli[j].setFontSize(12);
                    }
                    rowcelli[0].setText(Integer.toString(i + 1));
                    rowcelli[1].setText(transactionsToDisplay.get(i).seller_name);
                    rowcelli[2].setText(transactionsToDisplay.get(i).buyer_name);
                    rowcelli[3].setText(transactionsToDisplay.get(i).item);
                    rowcelli[4].setText(Integer.toString(transactionsToDisplay.get(i).quantity));
                    rowcelli[5].setText(Integer.toString(transactionsToDisplay.get(i).rate));
                }
                XWPFTableRow lastRow=table.createRow();
                XWPFRun lastrowcells[]=new XWPFRun[6];
                for(int k=0;k<lastrowcells.length;k++)
                {
                    lastRow.getCell(k).setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);
                    lastRow.getCell(k).getParagraphs().get(0).setAlignment(ParagraphAlignment.CENTER);
                    lastrowcells[k]=lastRow.getCell(k).getParagraphs().get(0).createRun();
                    lastrowcells[k].setFontFamily("Calibri");
                    lastrowcells[k].setFontSize(12);
                }
                lastrowcells[2].setBold(true);
                lastrowcells[2].setText("Total");
                lastrowcells[4].setBold(true);
                lastrowcells[4].setText(getTotalQuantity(transactionsToDisplay));
                //lastrowcells[4].setBold(true);
                //lastrowcells[4].setText(getTotalRate(temp));
            }
            askPrint(filename);
            doc.write(fos);
            fos.close();
        }
        catch(Exception e){e.printStackTrace();}
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        // TODO add your handling code here:
        try
        {
            Desktop.getDesktop().print(new File(myFileName));
        }
        catch(Exception e){}
        jDialog5.setVisible(true);
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton13KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton13KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyChar()=='\n')
        jButton6.doClick();
    }//GEN-LAST:event_jButton13KeyPressed

    private void jDialog5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jDialog5KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyChar()=='\n')
        jButton6.doClick();
    }//GEN-LAST:event_jDialog5KeyPressed

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
            java.util.logging.Logger.getLogger(BrokerHistory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BrokerHistory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BrokerHistory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BrokerHistory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BrokerHistory().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JDialog jDialog2;
    private javax.swing.JDialog jDialog3;
    private javax.swing.JDialog jDialog4;
    private javax.swing.JDialog jDialog5;
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
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
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
    // End of variables declaration//GEN-END:variables
}
