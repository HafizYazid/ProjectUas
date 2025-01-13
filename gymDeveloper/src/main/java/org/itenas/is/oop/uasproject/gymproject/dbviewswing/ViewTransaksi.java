/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package org.itenas.is.oop.uasproject.gymproject.dbviewswing;

import java.awt.Font;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import org.itenas.is.oop.uasproject.gymproject.dbImplgym.MenuCRUD;
import org.itenas.is.oop.uasproject.gymproject.dbImplgym.PenggunaCRUD;
import org.itenas.is.oop.uasproject.gymproject.dbImplgym.TransaksiCRUD;
import org.itenas.is.oop.uasproject.gymproject.dbconfig.ConnectionManager;
import org.itenas.is.oop.uasproject.gymproject.model.DataPengguna;
import org.itenas.is.oop.uasproject.gymproject.model.MenuGym;
import org.itenas.is.oop.uasproject.gymproject.model.TransaksiGym;
import org.itenas.is.oop.uasproject.gymproject.model.User;

/**
 *
 * @author LENOVO
 */
public class ViewTransaksi extends javax.swing.JFrame {
    TransaksiCRUD conTran = new TransaksiCRUD(new ConnectionManager());
    PenggunaCRUD conData = new PenggunaCRUD(new ConnectionManager());
    MenuCRUD conMenu = new MenuCRUD(new ConnectionManager());
    private DefaultTableModel model;
    private int selectedIdPengguna = -1; // Variabel untuk menyimpan ID Pengguna yang dipilih
    
    private User user;
    public ViewTransaksi(User user) {
         this.user = user;
         initComponents();
         setLocationRelativeTo(null);
         loadKategoriToComboBox();
         setupComboBoxListener();
         
        model = new DefaultTableModel();
        
        dataTransaksi.setModel(model);
        
        model.addColumn("ID");
        model.addColumn("NAMA PENGGUNA");
        model.addColumn("NAMA MENU");
        model.addColumn("TANGGAL PENDAFTARAN");
        model.addColumn("EXPIRED");
        model.addColumn("STATUS");
        getData();
    }
    
     public final void getData() {
            DefaultTableModel dtm = (DefaultTableModel) dataTransaksi.getModel(); 
           dtm.setRowCount(0); 

           List<TransaksiGym> listTransaksi = conTran.findAll(); 
           Object[] data = new Object[6]; 

       for (TransaksiGym transaksi : listTransaksi) {
           data[0] = transaksi.getIdTransaksi(); 
           data[1] = transaksi.getDataPengguna() != null ? transaksi.getDataPengguna().getNamaPengguna() : "Tidak Ada";
           data[2] = transaksi.getDataMenu() != null ? transaksi.getDataMenu().getNamaMenu() : "Tidak Ada"; 
           data[3] = transaksi.getTanggalPendaftaran();
           data[4] = transaksi.getTanggalBerakhir();
           data[5] = transaksi.getStatus(); 
           dtm.addRow(data); 
       }
    }
     
 
   
    private void loadKategoriToComboBox() {
    try {
        // Panggil findAll untuk mengambil semua data menu
        List<MenuGym> kategoriList = conMenu.findAll();

        // Hapus semua item sebelumnya dari JComboBox
        pilihMenu.removeAllItems();

        // Tambahkan data baru ke JComboBox
        for (MenuGym menu : kategoriList) {
            pilihMenu.addItem(menu.getNamaMenu());
        }

        // Log untuk debugging
        System.out.println("Kategori berhasil dimuat. Total: " + kategoriList.size());

    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error memuat kategori: " + e.getMessage());
    }
}


    private MenuGym getSelectedKategori(String selectedKategori) {
    selectedKategori = (String) pilihMenu.getSelectedItem(); 

    if (selectedKategori != null) {
        // Cari data dalam list yang dikembalikan oleh findAll
        List<MenuGym> menuList = conMenu.findAll();
        for (MenuGym menu : menuList) {
            if (menu.getNamaMenu().equals(selectedKategori)) {
                return menu; // Kembalikan objek MenuGym yang sesuai
            }
        }
    }
    return null; // Kembalikan null jika tidak ditemukan
}
    
    private void setupComboBoxListener() {
    pilihMenu.addActionListener(e -> {
        String selectedNamaMenu = (String) pilihMenu.getSelectedItem();
        if (selectedNamaMenu != null && !selectedNamaMenu.equals("Tidak ada data tersedia")) {
            MenuGym selectedMenu = getSelectedKategori(selectedNamaMenu);
            if (selectedMenu != null) {
                // Tampilkan durasi dan deskripsi
                txtMasaAktif.setText(selectedMenu.getDurasi() + " hari");
                txtHarga.setText("Rp " + selectedMenu.getHarga());
                txtDeskripsi.setText(selectedMenu.getDeskripsi());
                
            }
        } else {
            txtMasaAktif.setText("-");
            txtHarga.setText("-");
            txtDeskripsi.setText("-");
            
        }
    });
}

    private void clearData() {
    // Kosongkan semua inputan form setelah pembayaran
      txtNama.setText("");
    txtNoHp.setText("");
    txtEmail.setText("");
    txtAlamat.setText("");
    txtDiskon.setText("");
    txtMasaAktif.setText("");
    txtHarga.setText("");
    txtDeskripsi.setText("");
    txtTotalBiaya.setText("");
    txtJumlahUang.setText("");
    txtKembalian.setText("");
    getData();
}
    private double calculateTotalBiaya(double harga, double diskon) {
    double convertDiskon = diskon / 100;
    double calculate = harga * convertDiskon;
    return harga - calculate;
}
    private LocalDate hitungTanggalExpired(int durasiMenu) {
    LocalDate tanggalPendaftaranDate = LocalDate.now(); 
    return tanggalPendaftaranDate.plusDays(durasiMenu); 
}

    
    
    private void updateTotalBiaya() { 
 
    try {
        String diskonText = txtDiskon.getText().trim();
        if (diskonText.isEmpty()) {
            diskonText = "0";  // Set diskon ke 0 jika kosong
        }

        double diskon = Double.parseDouble(diskonText);

        String selectedNamaMenu = (String) pilihMenu.getSelectedItem();
        MenuGym selectedMenu = getSelectedKategori(selectedNamaMenu);

        if (selectedMenu == null) {
            JOptionPane.showMessageDialog(this, "Menu tidak valid!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        double hargaMenu = selectedMenu.getHarga();
        double totalPembayaran = calculateTotalBiaya(hargaMenu, diskon);

        // Validasi hasil perhitungan sebelum menampilkan
        if (totalPembayaran < 0) {
            JOptionPane.showMessageDialog(this, "Total biaya tidak valid.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        txtTotalBiaya.setText(String.format("%.1f", totalPembayaran)); // Format dengan 1 desimal
        this.totalBiaya = totalPembayaran; // Update variabel global totalBiaya

    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Masukkan angka valid untuk diskon.", 
                                      "Error", JOptionPane.ERROR_MESSAGE);
    }

}

    
    public void updateKembalian(){
    try {
        String jumlahUangText = txtJumlahUang.getText().trim();

        // Validasi input jumlah uang agar hanya angka valid
        if (jumlahUangText.isEmpty() || !jumlahUangText.matches("\\d+(\\.\\d+)?")) {
            JOptionPane.showMessageDialog(this, "Jumlah uang harus berupa angka valid.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Konversi jumlah uang ke tipe double
        double jumlahUang = Double.parseDouble(jumlahUangText);

        // Validasi apakah jumlah uang cukup untuk membayar total biaya
        if (jumlahUang < totalBiaya) {
            JOptionPane.showMessageDialog(this, "Jumlah uang kurang dari total biaya. Mohon masukkan jumlah yang cukup.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            txtJumlahUang.requestFocus();
            return;
        }

        // Hitung kembalian
        double kembalian = jumlahUang - totalBiaya;

        // Tampilkan kembalian di text field dengan format dua desimal
        txtKembalian.setText(String.format("%.2f", kembalian)); // Format ke 2 desimal
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Masukkan angka valid untuk jumlah uang.",
                "Error", JOptionPane.ERROR_MESSAGE);
    }
    }
    
        private void cetakStruk() {                                       
    try {
        // Ambil nilai nama, tanggal pendaftaran, dan tanggal expired dari text field
        String namaPengguna = txtNama.getText().trim();
        String tanggalPendaftaran = txtMasaAktif.getText().trim();

        if (namaPengguna.isEmpty() || tanggalPendaftaran.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Harap lengkapi data yang diperlukan!", 
                                          "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String selectedNamaMenu = (String) pilihMenu.getSelectedItem();
        MenuGym selectedMenu = getSelectedKategori(selectedNamaMenu);
        
        LocalDate expired = hitungTanggalExpired(selectedMenu.getDurasi());
        
        double totalPembayaran = this.totalBiaya;
        double diskon = Double.parseDouble(txtDiskon.getText().trim());
        double jumlahUang = Double.parseDouble(txtJumlahUang.getText().trim());
        double kembalian = jumlahUang - totalPembayaran;

        if (jumlahUang < totalPembayaran) {
            JOptionPane.showMessageDialog(this, "Jumlah uang tidak cukup!", 
                                          "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String tanggalCetak = now.format(formatter);

        String struk = "==== Struk Pembayaran ====\n"
                     + "Nama: " + namaPengguna + "\n"
                     + "Tanggal Pendaftaran: " + tanggalPendaftaran + "\n"
                     + "Tanggal Expired: " + expired + "\n"
                     + "Total Biaya: " + String.format("%.2f", totalPembayaran) + "\n"
                     + "Diskon: " + String.format("%.2f", diskon) + "%\n"
                     + "Jumlah Uang: " + String.format("%.2f", jumlahUang) + "\n"
                     + "Kembalian: " + String.format("%.2f", kembalian) + "\n"
                     + "Tanggal Cetak: " + tanggalCetak + "\n"
                     + "===========================\n"
                     + "Terima Kasih!";

        // Tampilkan struk dalam frame baru
        JFrame frameStruk = new JFrame("Struk Pembayaran");
        frameStruk.setSize(300, 400);
        frameStruk.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JTextArea textArea = new JTextArea(struk);
        textArea.setEditable(false);
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 14));

        JScrollPane scrollPane = new JScrollPane(textArea);
        frameStruk.add(scrollPane);

        frameStruk.setLocationRelativeTo(null);
        frameStruk.setVisible(true);

    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Terjadi kesalahan: " + e.getMessage(),
                                      "Error", JOptionPane.ERROR_MESSAGE);
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

        panel = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        txtNama = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtAlamat = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtNoHp = new javax.swing.JTextField();
        btnSearchData = new rojerusan.RSMaterialButtonRectangle();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtHarga = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtMasaAktif = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        pilihMenu = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        txtDiskon = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtTotalBiaya = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtJumlahUang = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtKembalian = new javax.swing.JTextField();
        btnPayment = new rojerusan.RSMaterialButtonRectangle();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtDeskripsi = new javax.swing.JTextArea();
        panelNavbar13 = new javax.swing.JPanel();
        jLabel40 = new javax.swing.JLabel();
        btnlogout = new javax.swing.JLabel();
        btnViewTransaksi = new rojerusan.RSMaterialButtonRectangle();
        btnViewPengguna = new rojerusan.RSMaterialButtonRectangle();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        dataTransaksi = new javax.swing.JTable();
        btnUpdatedStatus = new rojerusan.RSMaterialButtonRectangle();
        jLabel18 = new javax.swing.JLabel();
        refresh = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panel.setBackground(new java.awt.Color(255, 255, 255));

        jPanel4.setBackground(new java.awt.Color(204, 204, 255));
        jPanel4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));

        txtNama.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        txtNama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNamaActionPerformed(evt);
            }
        });

        txtEmail.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        txtEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmailActionPerformed(evt);
            }
        });

        jLabel2.setText("E-mail");

        jLabel5.setText("Nama");

        jLabel14.setText("Alamat");

        txtAlamat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        txtAlamat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAlamatActionPerformed(evt);
            }
        });

        jLabel15.setText("Hp / Wa");

        txtNoHp.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        txtNoHp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNoHpActionPerformed(evt);
            }
        });

        btnSearchData.setBackground(new java.awt.Color(153, 153, 153));
        btnSearchData.setText("Search");
        btnSearchData.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnSearchData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchDataActionPerformed(evt);
            }
        });

        jLabel8.setText("Jenis Member");

        jLabel7.setText("Deskripsi Menu");

        txtHarga.setEditable(false);
        txtHarga.setBackground(new java.awt.Color(255, 255, 255));
        txtHarga.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        txtHarga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtHargaActionPerformed(evt);
            }
        });

        jLabel6.setText("Harga");

        txtMasaAktif.setEditable(false);
        txtMasaAktif.setBackground(new java.awt.Color(255, 255, 255));
        txtMasaAktif.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        txtMasaAktif.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMasaAktifActionPerformed(evt);
            }
        });

        jLabel1.setText("Masa Aktif");

        pilihMenu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        pilihMenu.setToolTipText("");
        pilihMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pilihMenuActionPerformed(evt);
            }
        });

        jLabel10.setText("Diskon");

        txtDiskon.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        txtDiskon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDiskonActionPerformed(evt);
            }
        });

        jLabel11.setText("Total");

        txtTotalBiaya.setEditable(false);
        txtTotalBiaya.setBackground(new java.awt.Color(255, 255, 255));
        txtTotalBiaya.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        txtTotalBiaya.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotalBiayaActionPerformed(evt);
            }
        });

        jLabel12.setText("Jumlah Uang");

        txtJumlahUang.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        txtJumlahUang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtJumlahUangActionPerformed(evt);
            }
        });

        jLabel13.setText("Kembalian");

        txtKembalian.setEditable(false);
        txtKembalian.setBackground(new java.awt.Color(255, 255, 255));
        txtKembalian.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        txtKembalian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtKembalianActionPerformed(evt);
            }
        });

        btnPayment.setBackground(new java.awt.Color(153, 153, 153));
        btnPayment.setText("PAYMENT");
        btnPayment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPaymentActionPerformed(evt);
            }
        });

        txtDeskripsi.setEditable(false);
        txtDeskripsi.setBackground(new java.awt.Color(255, 255, 255));
        txtDeskripsi.setColumns(20);
        txtDeskripsi.setRows(5);
        jScrollPane2.setViewportView(txtDeskripsi);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(btnSearchData, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel5)
                    .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(jLabel15)
                    .addComponent(txtNoHp, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAlamat, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(144, 144, 144)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jLabel6)
                    .addComponent(txtHarga, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMasaAktif, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(pilihMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 144, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel10)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDiskon, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12)
                            .addComponent(txtJumlahUang, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTotalBiaya, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13)
                            .addComponent(txtKembalian, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(btnPayment, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(13, 13, 13)))
                .addGap(65, 65, 65))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(btnSearchData, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtAlamat, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtNoHp, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(178, 178, 178)
                                .addComponent(jLabel12)
                                .addGap(18, 18, 18)
                                .addComponent(txtJumlahUang, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtKembalian, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnPayment, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel10))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(pilihMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtDiskon, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel11))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtMasaAktif, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTotalBiaya, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtHarga, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(23, 23, 23)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        panelNavbar13.setBackground(new java.awt.Color(204, 204, 204));

        jLabel40.setIcon(new javax.swing.ImageIcon("C:\\Users\\LENOVO\\Documents\\PBO\\PROJECT_UAS\\GymDeveloper\\ProjectUas\\gymDeveloper\\src\\main\\java\\org\\itenas\\is\\oop\\uasproject\\gymproject\\Img\\Fitness-Gym-logo-design-template-on-transparent-background-PNG__1_-removebg-preview (2).png")); // NOI18N

        btnlogout.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnlogout.setIcon(new javax.swing.ImageIcon("C:\\Users\\LENOVO\\Documents\\PBO\\PROJECT_UAS\\GymDeveloper\\ProjectUas\\gymDeveloper\\src\\main\\java\\org\\itenas\\is\\oop\\uasproject\\gymproject\\Img\\logout (1).png")); // NOI18N
        btnlogout.setText("LogOut");
        btnlogout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnlogoutMouseClicked(evt);
            }
        });

        btnViewTransaksi.setBackground(new java.awt.Color(153, 153, 153));
        btnViewTransaksi.setText("Transaksi");
        btnViewTransaksi.setToolTipText("");
        btnViewTransaksi.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnViewTransaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewTransaksiActionPerformed(evt);
            }
        });

        btnViewPengguna.setBackground(new java.awt.Color(153, 153, 153));
        btnViewPengguna.setText("Data Anggota");
        btnViewPengguna.setToolTipText("");
        btnViewPengguna.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnViewPengguna.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewPenggunaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelNavbar13Layout = new javax.swing.GroupLayout(panelNavbar13);
        panelNavbar13.setLayout(panelNavbar13Layout);
        panelNavbar13Layout.setHorizontalGroup(
            panelNavbar13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNavbar13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnlogout)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelNavbar13Layout.createSequentialGroup()
                .addGap(0, 20, Short.MAX_VALUE)
                .addGroup(panelNavbar13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnViewPengguna, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnViewTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel40))
                .addGap(30, 30, 30))
        );
        panelNavbar13Layout.setVerticalGroup(
            panelNavbar13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNavbar13Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel40)
                .addGap(152, 152, 152)
                .addComponent(btnViewTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnViewPengguna, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 185, Short.MAX_VALUE)
                .addComponent(btnlogout)
                .addContainerGap())
        );

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));

        dataTransaksi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(dataTransaksi);

        btnUpdatedStatus.setBackground(new java.awt.Color(153, 153, 153));
        btnUpdatedStatus.setText("UPDATE");
        btnUpdatedStatus.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnUpdatedStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdatedStatusActionPerformed(evt);
            }
        });

        jLabel18.setText("Ubah status ke tidak aktif :");

        refresh.setIcon(new javax.swing.ImageIcon("C:\\Users\\LENOVO\\Documents\\PBO\\PROJECT_UAS\\GymDeveloper\\ProjectUas\\gymDeveloper\\src\\main\\java\\org\\itenas\\is\\oop\\uasproject\\gymproject\\Img\\rotate.png")); // NOI18N
        refresh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                refreshMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 941, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnUpdatedStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(refresh, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(refresh, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnUpdatedStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel18)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
        );

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                .addComponent(panelNavbar13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(9, Short.MAX_VALUE))
            .addComponent(panelNavbar13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnPaymentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPaymentActionPerformed
        // TODO add your handling code here:
        try {

            if (conTran.sudahMemilikiPaketAktif(selectedIdPengguna)) {
                JOptionPane.showMessageDialog(this, "Pengguna sudah memiliki paket aktif, tidak dapat membeli paket lagi.",
                    "Peringatan", JOptionPane.WARNING_MESSAGE);
                return;
            }

            DataPengguna pengguna = conData.findOne(String.valueOf(selectedIdPengguna));

            String selectedNamaMenu = (String) pilihMenu.getSelectedItem();
            MenuGym selectedMenu = getSelectedKategori(selectedNamaMenu);
            if (selectedMenu == null) {
                JOptionPane.showMessageDialog(this, "Kategori tidak dipilih!",
                    "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            double totalPembayaran = totalBiaya;
            LocalDate expired = hitungTanggalExpired(selectedMenu.getDurasi());
            
            TransaksiGym transaksi = new TransaksiGym();
            transaksi.setDataPengguna(pengguna);
            transaksi.setDataMenu(selectedMenu);
            transaksi.setTanggalPendaftaran(LocalDate.now());
            transaksi.setTanggalBerakhir(expired);
            transaksi.setTotalBiaya(totalPembayaran);
            transaksi.setDiskon((int) Double.parseDouble(txtDiskon.getText().trim()));
            transaksi.setStatus("AKTIF");

            conTran.create(transaksi);

            JOptionPane.showMessageDialog(this, "Pembayaran berhasil dan transaksi telah dicatat!",
                "Informasi", JOptionPane.INFORMATION_MESSAGE);
            
            cetakStruk();
            
            clearData();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnPaymentActionPerformed

    private void txtKembalianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKembalianActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtKembalianActionPerformed

    private void txtJumlahUangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtJumlahUangActionPerformed
        updateKembalian();
    }//GEN-LAST:event_txtJumlahUangActionPerformed

    private void txtTotalBiayaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalBiayaActionPerformed
        // TODO add your handling code here:
        updateTotalBiaya();
    }//GEN-LAST:event_txtTotalBiayaActionPerformed

    private void txtDiskonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDiskonActionPerformed
        // TODO add your handling code here:
        updateTotalBiaya();
    }//GEN-LAST:event_txtDiskonActionPerformed

    private void pilihMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pilihMenuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pilihMenuActionPerformed

    private void txtMasaAktifActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMasaAktifActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMasaAktifActionPerformed

    private void txtHargaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHargaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHargaActionPerformed

    private void btnSearchDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchDataActionPerformed
        // TODO add your handling code here:
        try {
            // Ambil semua data pengguna
            PenggunaCRUD penggunaCRUD = new PenggunaCRUD(new ConnectionManager());
            List<DataPengguna> penggunaList = penggunaCRUD.findAll();

            if (penggunaList.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Tidak ada data pengguna ditemukan", "Info", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            Object[][] data = new Object[penggunaList.size()][5];
            for (int i = 0; i < penggunaList.size(); i++) {
                DataPengguna pengguna = penggunaList.get(i);
                data[i][0] = pengguna.getIdPengguna();
                data[i][1] = pengguna.getNamaPengguna();
                data[i][2] = pengguna.getNoPengguna();
                data[i][3] = pengguna.getEmailPengguna();
                data[i][4] = pengguna.getAlamatPengguna();
            }

            String[] columnNames = {"ID", "Nama", "No HP", "Email", "Alamat"};
            JTable table = new JTable(data, columnNames);
            table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

            // Buat panel untuk menampung tabel
            JScrollPane scrollPane = new JScrollPane(table);

            // Membuat jendela baru untuk menampilkan tabel
            JFrame newFrame = new JFrame("Pilih Data Pengguna");
            newFrame.setSize(500, 300);
            newFrame.setLocationRelativeTo(null);  // Posisi di tengah layar
            newFrame.add(scrollPane);

            // Tambahkan listener pada tabel ketika baris dipilih
            table.getSelectionModel().addListSelectionListener(e -> {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = table.getSelectedRow();
                    if (selectedRow != -1) {
                        // Ambil data pengguna yang dipilih
                        selectedIdPengguna = (int) table.getValueAt(selectedRow, 0); // Menyimpan ID Pengguna
                        DataPengguna selectedPengguna = penggunaCRUD.findOne(String.valueOf(selectedIdPengguna));

                        if (selectedPengguna != null) {
                            // Isi data pengguna ke form utama
                            txtNama.setText(selectedPengguna.getNamaPengguna());
                            txtNoHp.setText(selectedPengguna.getNoPengguna());
                            txtEmail.setText(selectedPengguna.getEmailPengguna());
                            txtAlamat.setText(selectedPengguna.getAlamatPengguna());
                        }
                        // Tutup frame pencarian setelah memilih data
                        newFrame.dispose();
                    }
                }
            });

            newFrame.setVisible(true);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Pesan", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnSearchDataActionPerformed

    private void txtNoHpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNoHpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNoHpActionPerformed

    private void txtAlamatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAlamatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAlamatActionPerformed

    private void txtEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmailActionPerformed

    private void txtNamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNamaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNamaActionPerformed

    private void btnViewTransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewTransaksiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnViewTransaksiActionPerformed

    private void btnViewPenggunaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewPenggunaActionPerformed
        // TODO add your handling code here:
        new ViewDataAnggota(user).setVisible(true);
        dispose();
    }//GEN-LAST:event_btnViewPenggunaActionPerformed

    private void btnUpdatedStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdatedStatusActionPerformed
        // TODO add your handling code here:
         int i = dataTransaksi.getSelectedRow();
    if (i == -1) {
        JOptionPane.showMessageDialog(btnUpdatedStatus, "Harap pilih salah satu data transaksi.",
                                      "Warning", JOptionPane.WARNING_MESSAGE);
        return;
    }

    try {
        // Ambil ID Transaksi dari tabel (ID transaksi sekarang berupa String)
        String idTransaksi = dataTransaksi.getModel().getValueAt(i, 0).toString();

        // Konfirmasi apakah ingin mengubah status
        int confirm = JOptionPane.showConfirmDialog(this, 
                                                    "Yakin paket ini sudah expired?", 
                                                    "Konfirmasi", 
                                                    JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) {
            return; // Jika pengguna memilih "NO", batalkan proses
        }

        TransaksiGym transaksi = conTran.findOne(idTransaksi); // Menggunakan findOne yang menerima String
        if (transaksi == null) {
            JOptionPane.showMessageDialog(btnUpdatedStatus, "Transaksi tidak ditemukan!", 
                                          "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        transaksi.setStatus("TIDAK AKTIF"); 
        conTran.update(idTransaksi, transaksi); 

        // Beri notifikasi sukses
        JOptionPane.showMessageDialog(btnUpdatedStatus, "Status transaksi berhasil diupdate menjadi TIDAK AKTIF.", 
                                      "Informasi", JOptionPane.INFORMATION_MESSAGE);

        getData();

    } catch (Exception e) {
        JOptionPane.showMessageDialog(btnUpdatedStatus, "Terjadi kesalahan: " + e.getMessage(),
                                      "Error", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
    }
    }//GEN-LAST:event_btnUpdatedStatusActionPerformed

    private void refreshMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_refreshMouseClicked
        // TODO add your handling code here:
        clearData();
    }//GEN-LAST:event_refreshMouseClicked

    private void btnlogoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnlogoutMouseClicked
        // TODO add your handling code here:
        int confirm = JOptionPane.showConfirmDialog(ViewTransaksi.this,
            "Are you sure you want to logout?", "Logout Confirmation",
            JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            user.setRole(null); // Clear user role
            user.setUsername(null); // Clear username
            JOptionPane.showMessageDialog(ViewTransaksi.this, "You have been logged out.");
            dispose(); // Close AdminDashboard
            new ViewLogIn().setVisible(true); // Open Login Form
        }
    }//GEN-LAST:event_btnlogoutMouseClicked
    double totalBiaya = 0.0;

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojerusan.RSMaterialButtonRectangle btnPayment;
    private rojerusan.RSMaterialButtonRectangle btnSearchData;
    private rojerusan.RSMaterialButtonRectangle btnUpdatedStatus;
    private rojerusan.RSMaterialButtonRectangle btnViewPengguna;
    private rojerusan.RSMaterialButtonRectangle btnViewTransaksi;
    private javax.swing.JLabel btnlogout;
    private javax.swing.JTable dataTransaksi;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel panel;
    private javax.swing.JPanel panelNavbar13;
    private javax.swing.JComboBox<String> pilihMenu;
    private javax.swing.JLabel refresh;
    private javax.swing.JTextField txtAlamat;
    private javax.swing.JTextArea txtDeskripsi;
    private javax.swing.JTextField txtDiskon;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtHarga;
    private javax.swing.JTextField txtJumlahUang;
    private javax.swing.JTextField txtKembalian;
    private javax.swing.JTextField txtMasaAktif;
    private javax.swing.JTextField txtNama;
    private javax.swing.JTextField txtNoHp;
    private javax.swing.JTextField txtTotalBiaya;
    // End of variables declaration//GEN-END:variables
}
