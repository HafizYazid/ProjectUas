/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package org.itenas.is.oop.uasproject.gymproject.dbviewswing;

import javax.swing.JOptionPane;
import org.itenas.is.oop.uasproject.gymproject.dbImplgym.PenggunaCRUD;
import org.itenas.is.oop.uasproject.gymproject.dbconfig.ConnectionManager;
import org.itenas.is.oop.uasproject.gymproject.model.DataPengguna;
import org.itenas.is.oop.uasproject.gymproject.model.User;

/**
 *
 * @author LENOVO
 */
public class ViewFormulirUser extends javax.swing.JFrame {
    PenggunaCRUD conData = new PenggunaCRUD(new ConnectionManager());
    
    public ViewFormulirUser() {
        initComponents();
    }
    
    private void clearData(){
        txtNama.setText("");
        txtEmail.setText("");
        txtAlamat.setText("");
        txtNoHp.setText("");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelnama = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        txtEmail = new javax.swing.JTextField();
        btnSubmited = new rojerusan.RSMaterialButtonRectangle();
        labelnama1 = new javax.swing.JLabel();
        labelnama2 = new javax.swing.JLabel();
        labelnama3 = new javax.swing.JLabel();
        labelnama4 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        logogym = new javax.swing.JLabel();
        txtNama = new javax.swing.JTextField();
        txtAlamat = new javax.swing.JTextField();
        txtNoHp = new javax.swing.JTextField();
        labelnama5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        labelnama.setText("Nama");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        txtEmail.setBackground(new java.awt.Color(204, 204, 204));
        txtEmail.setForeground(new java.awt.Color(255, 255, 255));
        txtEmail.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmailActionPerformed(evt);
            }
        });

        btnSubmited.setBackground(new java.awt.Color(102, 102, 102));
        btnSubmited.setText("Simpan");
        btnSubmited.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSubmited.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubmitedActionPerformed(evt);
            }
        });

        labelnama1.setText("Nama");

        labelnama2.setText("E-Mail");

        labelnama3.setText("Alamat");

        labelnama4.setText("Hp / Wa");

        logogym.setIcon(new javax.swing.ImageIcon("C:\\Users\\LENOVO\\Documents\\PBO\\PROJECT_UAS\\GymDeveloper\\ProjectUas\\gymDeveloper\\src\\main\\java\\org\\itenas\\is\\oop\\uasproject\\gymproject\\Img\\gym dumbbells.jpg")); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(logogym)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(logogym)
        );

        txtNama.setBackground(new java.awt.Color(204, 204, 204));
        txtNama.setForeground(new java.awt.Color(255, 255, 255));
        txtNama.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtNama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNamaActionPerformed(evt);
            }
        });

        txtAlamat.setBackground(new java.awt.Color(204, 204, 204));
        txtAlamat.setForeground(new java.awt.Color(255, 255, 255));
        txtAlamat.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtAlamat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAlamatActionPerformed(evt);
            }
        });

        txtNoHp.setBackground(new java.awt.Color(204, 204, 204));
        txtNoHp.setForeground(new java.awt.Color(255, 255, 255));
        txtNoHp.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtNoHp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNoHpActionPerformed(evt);
            }
        });

        labelnama5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        labelnama5.setForeground(new java.awt.Color(255, 255, 255));
        labelnama5.setText("Formulir Pendaftaran");

        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\Users\\LENOVO\\Documents\\PBO\\PROJECT_UAS\\GymDeveloper\\ProjectUas\\gymDeveloper\\src\\main\\java\\org\\itenas\\is\\oop\\uasproject\\gymproject\\Img\\logout (1).png")); // NOI18N
        jLabel1.setText("BACK");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 60, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(labelnama5)
                                .addGap(80, 80, 80))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtNama)
                                        .addComponent(txtEmail)
                                        .addComponent(txtAlamat)
                                        .addComponent(txtNoHp, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(labelnama1)
                                    .addComponent(labelnama2)
                                    .addComponent(labelnama3)
                                    .addComponent(labelnama4))
                                .addGap(64, 64, 64))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(63, 63, 63)
                        .addComponent(btnSubmited, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelnama5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(labelnama1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(labelnama2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(labelnama3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtAlamat, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(labelnama4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNoHp, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(btnSubmited, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmailActionPerformed

    private void btnSubmitedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubmitedActionPerformed
        // TODO add your handling code here:
        try {
            // Buat objek DataPengguna
            DataPengguna pengguna = new DataPengguna();
            pengguna.setNamaPengguna(txtNama.getText().trim());
            pengguna.setEmailPengguna(txtEmail.getText().trim());
            pengguna.setAlamatPengguna(txtAlamat.getText().trim());
            pengguna.setNoPengguna(txtNoHp.getText().trim());

            // Validasi input
            if (pengguna.getNamaPengguna().isEmpty() || pengguna.getNoPengguna().isEmpty() ||
                pengguna.getEmailPengguna().isEmpty() || pengguna.getAlamatPengguna().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Semua kolom harus diisi!", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Buat objek PenggunaCRUD untuk memeriksa apakah email atau nomor pengguna sudah terdaftar
            PenggunaCRUD penggunaCRUD = new PenggunaCRUD(new ConnectionManager());

            // Periksa apakah nomor telepon sudah ada di database
            if (penggunaCRUD.isUserExistByPhone(pengguna.getNoPengguna())) {
                JOptionPane.showMessageDialog(this, "Pengguna dengan nomor telepon tersebut sudah terdaftar!", 
                                              "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Periksa apakah email sudah ada di database
            if (penggunaCRUD.isUserExistByEmail(pengguna.getEmailPengguna())) {
                JOptionPane.showMessageDialog(this, "Pengguna dengan email tersebut sudah terdaftar!", 
                                              "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Simpan data pengguna ke database
            penggunaCRUD.create(pengguna);

            // Tampilkan pesan bahwa data berhasil disimpan
            JOptionPane.showMessageDialog(this, "Data berhasil ditambahkan", "Pesan", JOptionPane.INFORMATION_MESSAGE);

            clearData();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Pesan", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }

    }//GEN-LAST:event_btnSubmitedActionPerformed

    private void txtNamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNamaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNamaActionPerformed

    private void txtAlamatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAlamatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAlamatActionPerformed

    private void txtNoHpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNoHpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNoHpActionPerformed

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        // TODO add your handling code here:
        new ViewLogIn().setVisible(true);
        dispose();
                
    }//GEN-LAST:event_jLabel1MouseClicked
    
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
            java.util.logging.Logger.getLogger(ViewRegister.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewRegister.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewRegister.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewRegister.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewFormulirUser().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojerusan.RSMaterialButtonRectangle btnSubmited;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel labelnama;
    private javax.swing.JLabel labelnama1;
    private javax.swing.JLabel labelnama2;
    private javax.swing.JLabel labelnama3;
    private javax.swing.JLabel labelnama4;
    private javax.swing.JLabel labelnama5;
    private javax.swing.JLabel logogym;
    private javax.swing.JTextField txtAlamat;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtNama;
    private javax.swing.JTextField txtNoHp;
    // End of variables declaration//GEN-END:variables
}
