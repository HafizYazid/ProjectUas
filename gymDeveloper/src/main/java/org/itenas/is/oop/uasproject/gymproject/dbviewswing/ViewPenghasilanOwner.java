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
import org.itenas.is.oop.uasproject.gymproject.dbImplgym.MenuRU;
import org.itenas.is.oop.uasproject.gymproject.dbImplgym.PenggunaGymCRUD;
import org.itenas.is.oop.uasproject.gymproject.dbconfig.ConnectionManager;
import org.itenas.is.oop.uasproject.gymproject.model.MenuGym;
import org.itenas.is.oop.uasproject.gymproject.model.PenggunaGym;

/**
 *
 * @author LENOVO
 */
public class ViewPenghasilanOwner extends javax.swing.JFrame {
    PenggunaGymCRUD conData = new PenggunaGymCRUD(new ConnectionManager());
    MenuRU conMenu = new MenuRU(new ConnectionManager());
    private DefaultTableModel model;
   
    
    public ViewPenghasilanOwner() {
        initComponents();
        setLocationRelativeTo(null);
        model = new DefaultTableModel();
        
        dataAnggota.setModel(model);
        
       model.addColumn("ID");
        model.addColumn("NAMA");
        model.addColumn("NO HP");
        model.addColumn("KATEGORI");
        model.addColumn("TANGGAL MASUK");
        model.addColumn("DURASI");
        model.addColumn("EXPIRED");
        model.addColumn("STATUS");
        model.addColumn("TOTAL BIAYA");
        loadKategoriToComboBox();
        getData();
    }
    
    public final void getData() {
        DefaultTableModel dtm = (DefaultTableModel) dataAnggota.getModel();
        dtm.setRowCount(0);
        List<PenggunaGym> listData = conData.findAll();
        Object[] data = new Object[9];

        for (PenggunaGym dataPengguna : listData) {
            data[0] = dataPengguna.getIdPengguna();
            data[1] = dataPengguna.getNamaPengguna();
            data[2] = dataPengguna.getNomorPengguna();
            data[3] = dataPengguna.getIdKategori().getJenisPengunjung();
            data[4] = dataPengguna.getTanggalMasuk();
            data[5] = dataPengguna.getDurasi();
            data[6] = dataPengguna.getTanggalBerakhir();
            data[7] = dataPengguna.getStatus();
            data[8] = dataPengguna.getTotalBiaya();
            dtm.addRow(data);
        }
        penghasilan();
        Jumlah();
    }
    
    public void penghasilan() {
    double total = conData.getTotalPenghasilan();
    
    DecimalFormat df = new DecimalFormat("Rp #,###");
    txtPenghasilan.setText( df.format(total));
}
    
    public void penghasilanbyKategori(int kategoriId) {
    // Get total income for the selected category
    double total = conData.getTotalPenghasilanByKategori(kategoriId);
    
    // Format the result to display as currency
    DecimalFormat df = new DecimalFormat("Rp #,###");
    txtPenghasilan.setText(df.format(total));
}
    
    public void Jumlah(){
        int countt = conData.getJumlahKeanggotaan();
        txtCount.setText(String.valueOf(countt));
    }
    
    public void JumlahbyKategori(int kategoriId) {
    // Get member count for the selected category
    int countt = conData.getJumlahKeanggotaanByKategori(kategoriId);
    txtCount.setText(String.valueOf(countt));
}
    
     private void loadKategoriToComboBox() {
    try {
        List<MenuGym> kategoriList = conMenu.findAll();
        kategori.removeAllItems();  
        for (MenuGym menu : kategoriList) {
            kategori.addItem(menu.getJenisPengunjung());  
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error memuat kategori: " + e.getMessage());
    }
}


    private MenuGym getSelectedKategori() {
    String selectedKategori = (String) kategori.getSelectedItem(); 
    if (selectedKategori != null) {
        List<MenuGym> kategoriList = conMenu.findAll();
        for (MenuGym menu : kategoriList) {
            if (menu.getJenisPengunjung().equals(selectedKategori)) {
                return menu;  // Return the MenuGym object of the selected category
            }
        }
    }
    return null;
}

    
    private void filterDataByKategori(int kategoriId) {
    DefaultTableModel dtm = (DefaultTableModel) dataAnggota.getModel();
    dtm.setRowCount(0);  
    List<PenggunaGym> listData = conData.findAll();  
    Object[] data = new Object[9];
    
    for (PenggunaGym pengguna : listData) {
        if (pengguna.getIdKategori().getIdKategori()== kategoriId) {  
            data[0] = pengguna.getIdPengguna();
            data[1] = pengguna.getNamaPengguna();
            data[2] = pengguna.getNomorPengguna();
            data[3] = pengguna.getIdKategori().getJenisPengunjung();
            data[4] = pengguna.getTanggalMasuk();
            data[5] = pengguna.getDurasi();
            data[6] = pengguna.getTanggalBerakhir();
            data[7] = pengguna.getStatus();
            data[8] = pengguna.getTotalBiaya();
            dtm.addRow(data);
        }
    }
    penghasilanbyKategori(kategoriId);
    JumlahbyKategori(kategoriId);
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
        panelNavbar = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtPenghasilan = new javax.swing.JTextField();
        txtCount = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        dataAnggota = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        kategori = new javax.swing.JComboBox<>();
        btnCleared = new rojerusan.RSMaterialButtonRectangle();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panel.setBackground(new java.awt.Color(255, 251, 244));

        panelNavbar.setBackground(new java.awt.Color(86, 84, 89));

        javax.swing.GroupLayout panelNavbarLayout = new javax.swing.GroupLayout(panelNavbar);
        panelNavbar.setLayout(panelNavbarLayout);
        panelNavbarLayout.setHorizontalGroup(
            panelNavbarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 180, Short.MAX_VALUE)
        );
        panelNavbarLayout.setVerticalGroup(
            panelNavbarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

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

        dataAnggota.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "NAMA", "NO HP", "KATEGORI", "TANGGAL MASUK", "DURASI", "EXPIRED", "STATUS", "TOTAL BIAYA"
            }
        ));
        dataAnggota.setGridColor(new java.awt.Color(153, 153, 153));
        dataAnggota.setShowGrid(true);
        dataAnggota.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dataAnggotaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(dataAnggota);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("FILTER BY :");

        kategori.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "VISITOR", "MEMBER" }));
        kategori.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                kategoriMouseClicked(evt);
            }
        });
        kategori.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kategoriActionPerformed(evt);
            }
        });

        btnCleared.setText("REFRESH");
        btnCleared.setFont(new java.awt.Font("Roboto Medium", 0, 12)); // NOI18N
        btnCleared.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearedActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                .addComponent(panelNavbar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 1048, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelLayout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(kategori, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCount, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(btnCleared, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(txtPenghasilan, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(21, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addGap(438, 438, 438))))
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelNavbar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 123, Short.MAX_VALUE)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4)
                    .addComponent(kategori, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPenghasilan, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(btnCleared, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(81, 81, 81))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtCountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCountActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_txtCountActionPerformed

    private void txtPenghasilanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPenghasilanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPenghasilanActionPerformed

    private void dataAnggotaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dataAnggotaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_dataAnggotaMouseClicked

    private void kategoriMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_kategoriMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_kategoriMouseClicked

    private void kategoriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kategoriActionPerformed
        // TODO add your handling code here:
         MenuGym selectedKategori = getSelectedKategori();  // Get the selected category
    if (selectedKategori != null) {
        filterDataByKategori(selectedKategori.getIdKategori());  // Filter data by selected category ID
        }
    }//GEN-LAST:event_kategoriActionPerformed

    private void btnClearedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearedActionPerformed
        // TODO add your handling code here:
        getData();
    }//GEN-LAST:event_btnClearedActionPerformed

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
            java.util.logging.Logger.getLogger(ViewPenghasilanOwner.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewPenghasilanOwner.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewPenghasilanOwner.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewPenghasilanOwner.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewPenghasilanOwner().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojerusan.RSMaterialButtonRectangle btnCleared;
    private javax.swing.JTable dataAnggota;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> kategori;
    private javax.swing.JPanel panel;
    private javax.swing.JPanel panelNavbar;
    private javax.swing.JTextField txtCount;
    private javax.swing.JTextField txtPenghasilan;
    // End of variables declaration//GEN-END:variables
}
