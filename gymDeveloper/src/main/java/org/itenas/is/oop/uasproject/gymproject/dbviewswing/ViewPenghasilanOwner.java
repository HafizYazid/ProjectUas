/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package org.itenas.is.oop.uasproject.gymproject.dbviewswing;

import java.text.DecimalFormat;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import org.itenas.is.oop.uasproject.gymproject.dbImplgym.MenuCRUD;
import org.itenas.is.oop.uasproject.gymproject.dbImplgym.TransaksiCRUD;
import org.itenas.is.oop.uasproject.gymproject.dbconfig.ConnectionManager;
import org.itenas.is.oop.uasproject.gymproject.model.MenuGym;
import org.itenas.is.oop.uasproject.gymproject.model.TransaksiGym;
import org.itenas.is.oop.uasproject.gymproject.model.User;

/**
 *
 * @author LENOVO
 */
public class ViewPenghasilanOwner extends javax.swing.JFrame {
    TransaksiCRUD conTran = new TransaksiCRUD(new ConnectionManager());
    MenuCRUD conMenu = new MenuCRUD(new ConnectionManager());
    
    private DefaultTableModel model;
   
    private User user;
    public ViewPenghasilanOwner(User user) {
        this.user = user;
        initComponents();
        setLocationRelativeTo(null);
        model = new DefaultTableModel();
        
        model.addColumn("ID");
        model.addColumn("NAMA PENGGUNA");
        model.addColumn("NAMA MENU");
        model.addColumn("TANGGAL PENDAFTARAN");
        model.addColumn("EXPIRED");
        model.addColumn("TOTAL BIAYA");
        model.addColumn("DISKON");
        getData();
    }
    
     public final void getData() {
            DefaultTableModel dtm = (DefaultTableModel) dataTransaksi.getModel(); 
           dtm.setRowCount(0); 

           List<TransaksiGym> listTransaksi = conTran.findAll(); 
           Object[] data = new Object[7]; 

       for (TransaksiGym transaksi : listTransaksi) {
           data[0] = transaksi.getIdTransaksi(); 
           data[1] = transaksi.getDataPengguna() != null ? transaksi.getDataPengguna().getNamaPengguna() : "Tidak Ada"; 
           data[2] = transaksi.getDataMenu() != null ? transaksi.getDataMenu().getNamaMenu() : "Tidak Ada"; 
           data[3] = transaksi.getTanggalPendaftaran();
           data[4] = transaksi.getTanggalBerakhir();
           data[5] = transaksi.getTotalBiaya(); 
           data[6] = transaksi.getDiskon(); 
           dtm.addRow(data); 
       }
        penghasilan();
        Jumlah();
    }
    
    public void penghasilan() {
    double total = conTran.getTotalPenghasilan();
    
    DecimalFormat df = new DecimalFormat("Rp #,###");
    txtPenghasilan.setText( df.format(total));
}
    
    
    public void Jumlah(){
        int countt = conTran.getJumlahKeanggotaan();
        txtCount.setText(String.valueOf(countt));
    }
    


    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtPenghasilan = new javax.swing.JTextField();
        txtCount = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        dataTransaksi = new javax.swing.JTable();
        btnCleared = new rojerusan.RSMaterialButtonRectangle();
        panelNavbar = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        btnDataMenu = new rojerusan.RSMaterialButtonRectangle();
        btnDataPenghasilan = new rojerusan.RSMaterialButtonRectangle();
        jLabel14 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panel.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("Data Penghasilan");

        txtPenghasilan.setEditable(false);
        txtPenghasilan.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtPenghasilan.setForeground(new java.awt.Color(51, 51, 51));
        txtPenghasilan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPenghasilanActionPerformed(evt);
            }
        });

        txtCount.setEditable(false);
        txtCount.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtCount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCountActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("JUMLAH DATA :");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setText("TOTAL :");

        dataTransaksi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "NAMA PENGGUNA", "NAMA MENU", "TANGGAL PENDAFTARAN", "EXPIRED", "TOTAL BIAYA", "DISKON"
            }
        ));
        dataTransaksi.setGridColor(new java.awt.Color(153, 153, 153));
        dataTransaksi.setShowGrid(true);
        dataTransaksi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dataTransaksiMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(dataTransaksi);

        btnCleared.setText("REFRESH");
        btnCleared.setFont(new java.awt.Font("Roboto Medium", 0, 12)); // NOI18N
        btnCleared.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearedActionPerformed(evt);
            }
        });

        panelNavbar.setBackground(new java.awt.Color(204, 204, 204));

        jLabel12.setIcon(new javax.swing.ImageIcon("C:\\Users\\LENOVO\\Documents\\PBO\\PROJECT_UAS\\Img\\Fitness-Gym-logo-design-template-on-transparent-background-PNG__1_-removebg-preview (2).png")); // NOI18N

        btnDataMenu.setBackground(new java.awt.Color(153, 153, 153));
        btnDataMenu.setText("DATA MENU");
        btnDataMenu.setToolTipText("");
        btnDataMenu.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnDataMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDataMenuActionPerformed(evt);
            }
        });

        btnDataPenghasilan.setBackground(new java.awt.Color(153, 153, 153));
        btnDataPenghasilan.setText("DATA PENGHASILAN");
        btnDataPenghasilan.setToolTipText("");
        btnDataPenghasilan.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnDataPenghasilan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDataPenghasilanActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel14.setIcon(new javax.swing.ImageIcon("C:\\Users\\LENOVO\\Documents\\PBO\\PROJECT_UAS\\Img\\logout (1).png")); // NOI18N
        jLabel14.setText("LogOut");

        javax.swing.GroupLayout panelNavbarLayout = new javax.swing.GroupLayout(panelNavbar);
        panelNavbar.setLayout(panelNavbarLayout);
        panelNavbarLayout.setHorizontalGroup(
            panelNavbarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNavbarLayout.createSequentialGroup()
                .addGap(0, 20, Short.MAX_VALUE)
                .addGroup(panelNavbarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnDataPenghasilan, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDataMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addGap(30, 30, 30))
            .addGroup(panelNavbarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelNavbarLayout.setVerticalGroup(
            panelNavbarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNavbarLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel12)
                .addGap(154, 154, 154)
                .addComponent(btnDataMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnDataPenghasilan, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 163, Short.MAX_VALUE)
                .addComponent(jLabel14)
                .addContainerGap())
        );

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addComponent(panelNavbar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 895, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelLayout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(txtPenghasilan, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnCleared, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 93, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtCount, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(97, 97, 97))))
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(txtCount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtPenghasilan, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCleared, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(102, 102, 102))
            .addGroup(panelLayout.createSequentialGroup()
                .addComponent(panelNavbar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtCountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCountActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_txtCountActionPerformed

    private void txtPenghasilanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPenghasilanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPenghasilanActionPerformed

    private void dataTransaksiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dataTransaksiMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_dataTransaksiMouseClicked

    private void btnClearedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearedActionPerformed
        // TODO add your handling code here:
     getData();
    }//GEN-LAST:event_btnClearedActionPerformed

    private void btnDataMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDataMenuActionPerformed
        // TODO add your handling code here:
        new ViewMenuOwner(user).setVisible(true);
        dispose();
    }//GEN-LAST:event_btnDataMenuActionPerformed

    private void btnDataPenghasilanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDataPenghasilanActionPerformed
        // TODO add your handling code here:
        new ViewPenghasilanOwner(user).setVisible(true);
        dispose();
    }//GEN-LAST:event_btnDataPenghasilanActionPerformed

  

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojerusan.RSMaterialButtonRectangle btnCleared;
    private rojerusan.RSMaterialButtonRectangle btnDataMenu;
    private rojerusan.RSMaterialButtonRectangle btnDataPenghasilan;
    private javax.swing.JTable dataTransaksi;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panel;
    private javax.swing.JPanel panelNavbar;
    private javax.swing.JTextField txtCount;
    private javax.swing.JTextField txtPenghasilan;
    // End of variables declaration//GEN-END:variables
}
