/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brokerapplication;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.DriverManager;
import java.util.Vector;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Mayank Kakad
 */
public class AddItems extends javax.swing.JFrame {

    /**
     * Creates new form AddItems
     */
    int max_items=999;
    int count=0;
    JLabel sr_nos[],codelabel[],namelabel[];
    Vector<Item> itemlist;
    JTextField codes[],names[];
    public AddItems() {
        initComponents();
        initComps();
    }
    
    public void initComps()
    {
        jButton5.setVisible(false);
        codelabel=new JLabel[max_items];
        namelabel=new JLabel[max_items];
        sr_nos=new JLabel[max_items];
        codes=new JTextField[max_items];
        names=new JTextField[max_items];
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
        jButton7 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();

        jDialog1.setTitle("Confirmation");
        jDialog1.setSize(new java.awt.Dimension(400, 300));
        jDialog1.getContentPane().setLayout(new java.awt.GridBagLayout());

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 40)); // NOI18N
        jLabel5.setText("Confirm?");
        jDialog1.getContentPane().add(jLabel5, new java.awt.GridBagConstraints());

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
        jDialog1.getContentPane().add(jButton7, gridBagConstraints);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Add Items");

        jPanel1.setToolTipText("");
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 40)); // NOI18N
        jLabel1.setText("Add Items");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 80, 0);
        jPanel1.add(jLabel1, gridBagConstraints);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("1");
        jLabel2.setPreferredSize(new java.awt.Dimension(30, 50));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 20, 70);
        jPanel1.add(jLabel2, gridBagConstraints);

        jTextField1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jTextField1.setPreferredSize(new java.awt.Dimension(100, 50));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 20, 50);
        jPanel1.add(jTextField1, gridBagConstraints);

        jTextField2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jTextField2.setPreferredSize(new java.awt.Dimension(300, 50));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 20, 0);
        jPanel1.add(jTextField2, gridBagConstraints);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setText("Code");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 40);
        jPanel1.add(jLabel3, gridBagConstraints);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText("Name");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        jPanel1.add(jLabel4, gridBagConstraints);

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
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 80, 0);
        jPanel1.add(jButton1, gridBagConstraints);

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
        jButton2.setText("By File");
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
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 175, 80, 0);
        jPanel1.add(jButton2, gridBagConstraints);

        jButton3.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
        jButton3.setText("Done");
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
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.insets = new java.awt.Insets(60, 0, 0, 0);
        jPanel1.add(jButton3, gridBagConstraints);

        jButton4.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jButton4.setText("+Add");
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
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(20, 0, 0, 50);
        jPanel1.add(jButton4, gridBagConstraints);

        jButton5.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jButton5.setText("-Remove");
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
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(20, 0, 0, 170);
        jPanel1.add(jButton5, gridBagConstraints);

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
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.insets = new java.awt.Insets(30, 0, 50, 0);
        jPanel1.add(jButton6, gridBagConstraints);

        jScrollPane1.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        HomePage.il.setVisible(true);
        ItemsList.ai.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        HomePage.il.setVisible(true);
        ItemsList.ai.setVisible(false);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        jPanel1.remove(jButton3);
        jPanel1.remove(jButton4);
        jPanel1.remove(jButton5);
        jPanel1.remove(jButton6);
        GridBagConstraints gbc=new GridBagConstraints();
        codelabel[count]=new JLabel();
        codelabel[count].setFont(new java.awt.Font("Tahoma",0,18));
        codelabel[count].setText("Code");
        gbc.gridx=1;
        gbc.gridy=count+count+3;
        gbc.insets=new Insets(0,0,0,50);
        jPanel1.add(codelabel[count],gbc);
        namelabel[count]=new JLabel();
        namelabel[count].setFont(new java.awt.Font("Tahoma",0,18));
        namelabel[count].setText("Name");
        gbc.gridx=2;
        gbc.gridy=count+count+3;
        gbc.insets=new Insets(0,0,0,0);
        jPanel1.add(namelabel[count],gbc);
        sr_nos[count]=new JLabel();
        sr_nos[count].setFont(new java.awt.Font("Tahoma",0,24));
        sr_nos[count].setPreferredSize(new java.awt.Dimension(30,50));
        sr_nos[count].setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        sr_nos[count].setText(Integer.toString(count+2));
        gbc.gridx=0;
        gbc.gridy=count+count+4;
        gbc.insets=new Insets(0,0,20,70);
        jPanel1.add(sr_nos[count],gbc);
        codes[count]=new JTextField();
        codes[count].setFont(new java.awt.Font("Tahoma",0,24));
        codes[count].setPreferredSize(new java.awt.Dimension(100,50));
        gbc.gridx=1;
        gbc.gridy=count+count+4;
        gbc.insets=new Insets(0,0,20,50);
        jPanel1.add(codes[count],gbc);
        names[count]=new JTextField();
        names[count].setFont(new java.awt.Font("Tahoma",0,24));
        names[count].setPreferredSize(new java.awt.Dimension(300,50));
        gbc.gridx=2;
        gbc.gridy=count+count+4;
        gbc.insets=new Insets(0,0,20,0);
        jPanel1.add(names[count],gbc);
        jButton5.setVisible(true);
        gbc.gridx=1;
        gbc.gridy=count+count+5;
        gbc.insets=new Insets(40,0,0,50);
        jPanel1.add(jButton4,gbc);
        gbc.gridx=2;
        gbc.gridy=count+count+5;
        gbc.insets=new Insets(40,0,0,170);
        jPanel1.add(jButton5,gbc);
        gbc.gridx=0;
        gbc.gridy=count+count+6;
        gbc.gridwidth=3;
        gbc.insets=new Insets(60,0,0,0);
        jPanel1.add(jButton3,gbc);
        gbc.gridx=0;
        gbc.gridy=count+count+7;
        gbc.gridwidth=3;
        gbc.insets=new Insets(30,0,50,0);
        jPanel1.add(jButton6,gbc);
        count++;
        if(count==max_items)
            jButton4.setVisible(false);
        jPanel1.revalidate();
        jPanel1.repaint();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        GridBagConstraints gbc=new GridBagConstraints();
        count--;
        jPanel1.remove(sr_nos[count]);
        jPanel1.remove(codelabel[count]);
        jPanel1.remove(namelabel[count]);
        jPanel1.remove(codes[count]);
        jPanel1.remove(names[count]);
        jPanel1.remove(jButton3);
        jPanel1.remove(jButton4);
        jPanel1.remove(jButton5);
        jPanel1.remove(jButton6);
        count--;
        gbc.gridx=1;
        gbc.gridy=count+count+5;
        gbc.insets=new Insets(40,0,0,50);
        jPanel1.add(jButton4,gbc);
        gbc.gridx=2;
        gbc.gridy=count+count+5;
        gbc.insets=new Insets(40,0,0,170);
        jPanel1.add(jButton5,gbc);
        gbc.gridx=0;
        gbc.gridy=count+count+6;
        gbc.gridwidth=3;
        gbc.insets=new Insets(60,0,0,0);
        jPanel1.add(jButton3,gbc);
        gbc.gridx=0;
        gbc.gridy=count+count+7;
        gbc.gridwidth=3;
        gbc.insets=new Insets(30,0,50,0);
        jPanel1.add(jButton6,gbc);
        count++;
        jButton4.setVisible(true);
        if(count==0)
            jButton5.setVisible(false);
        jPanel1.revalidate();
        jPanel1.repaint();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        if(jTextField2.getText().trim().equals(""))
        {
            JOptionPane.showMessageDialog(null,"Name can't be empty","Error: invalid input",JOptionPane.ERROR_MESSAGE);
            jTextField2.requestFocus();
            return;
        }
        itemlist=new Vector<Item>();
        for(int i=0;i<count;i++)
            if(names[i].getText().trim().equals(""))
            {
                JOptionPane.showMessageDialog(null,"Name can't be empty","Error: invalid input",JOptionPane.ERROR_MESSAGE);
                names[i].requestFocus();
                return;
            }
        String t=jTextField1.getText().trim().toUpperCase();
        if(t.equals(""))
            t=null;
        itemlist.add(new Item(t,jTextField2.getText().trim().toUpperCase()));
        for(int i=0;i<count;i++)
        {
            String temp=codes[i].getText().trim().toUpperCase();
            if(temp.equals(""))
                temp=null;
            itemlist.add(new Item(temp,names[i].getText().trim().toUpperCase()));
        }
        int itemError=LoginPage.datop.checkItems(itemlist);
        if(itemError==0)
        {
            JOptionPane.showMessageDialog(null,"Item already exists","Error: invalid input",JOptionPane.ERROR_MESSAGE);
            jTextField2.requestFocus();
            return;
        }
        else if(itemError>0)
        {
            JOptionPane.showMessageDialog(null,"Item already exists","Error: invalid input",JOptionPane.ERROR_MESSAGE);
            names[itemError-1].requestFocus();
            return;
        }
        int xco=(LoginPage.hp.getWidth()-jDialog1.getWidth())/2;
        int yco=(LoginPage.hp.getHeight()-jDialog1.getWidth())/2;
        jDialog1.setBounds(xco,yco,jDialog1.getWidth(),jDialog1.getHeight());
        jDialog1.setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        LoginPage.datop.addItems(itemlist);
        itemlist.clear();
        HomePage.il.deleteDisplay();
        HomePage.il.initComps();
        HomePage.il.setVisible(true);
        jDialog1.setVisible(false);
        ItemsList.ai.setVisible(false);
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        try
        {
            JFileChooser chooser=new JFileChooser();
            chooser.showOpenDialog(null);
            String filename=chooser.getCurrentDirectory()+"\\"+chooser.getSelectedFile().getName();
            FileReader fr=new FileReader(filename);
            BufferedReader br=new BufferedReader(fr);
            Vector<Item> temp=new Vector<Item>();
            String s;
            while((s=br.readLine())!=null)
            {
                String breakDown[]=s.split("\\$");
                temp.add(new Item(breakDown[0],breakDown[1]));
            }
            br.close();
            fr.close();
            LoginPage.datop.addItems(temp);
            HomePage.il.deleteDisplay();
            HomePage.il.initComps();
            HomePage.il.setVisible(true);
            ItemsList.ai.setVisible(false);
        }
        catch(Exception e){}
    }//GEN-LAST:event_jButton2ActionPerformed

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

    private void jButton5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton5KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyChar()=='\n')
            jButton5.doClick();
    }//GEN-LAST:event_jButton5KeyPressed

    private void jButton3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton3KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyChar()=='\n')
            jButton3.doClick();
    }//GEN-LAST:event_jButton3KeyPressed

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
            java.util.logging.Logger.getLogger(AddItems.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddItems.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddItems.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddItems.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddItems().setVisible(true);
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
    private javax.swing.JDialog jDialog1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
}
