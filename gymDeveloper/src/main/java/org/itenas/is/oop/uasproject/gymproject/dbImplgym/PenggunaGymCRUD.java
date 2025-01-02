package org.itenas.is.oop.uasproject.gymproject.dbImplgym;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.SpringLayout;
import org.itenas.is.oop.uasproject.gymproject.dbconfig.ConnectionManager;
import org.itenas.is.oop.uasproject.gymproject.service.CRUDService;
import org.itenas.is.oop.uasproject.gymproject.model.PenggunaGym;
import org.itenas.is.oop.uasproject.gymproject.model.MenuGym;

public class PenggunaGymCRUD implements CRUDService<PenggunaGym> {
    private final ConnectionManager connMan;
    
    public PenggunaGymCRUD(ConnectionManager connMan) {
        this.connMan = connMan;
    }

    @Override
    public void create(PenggunaGym penggunaGym) {
        String query = "INSERT INTO pengunjung (nama_pengguna, no_pengguna, id_kategori, "
                + "tanggal_masuk, durasi, tanggal_berakhir, status, total_biaya) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = connMan.connectDb();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, penggunaGym.getNamaPengguna());
            statement.setString(2, penggunaGym.getNomorPengguna());
            statement.setInt(3, penggunaGym.getIdKategori().getIdKategori());
            statement.setDate(4, java.sql.Date.valueOf(penggunaGym.getTanggalMasuk()));
            statement.setInt(5, penggunaGym.getDurasi());
            statement.setDate(6, java.sql.Date.valueOf(penggunaGym.tenggatStatus()));
            statement.setString(7, penggunaGym.status() ? "AKTIF" : "TIDAK AKTIF");
            statement.setDouble(8, penggunaGym.totalBiaya());
            statement.executeUpdate();
            System.out.println("Data berhasil ditambahkan.");
        } catch (SQLException e) {
            System.err.println("Error saat menambahkan data: " + e.getMessage());
        }
    }

    @Override
        public PenggunaGym findOne(String id) {
        PenggunaGym penggunaGym = null;

        String query = "SELECT p.id_pengguna, p.nama_pengguna, p.no_pengguna, m.jenis_pengunjung, "
                     + "p.tanggal_masuk, p.durasi, p.tanggal_berakhir, p.status, p.total_biaya, p.id_kategori, "
                     + "m.harga FROM pengunjung p "
                     + "JOIN menu m ON p.id_kategori = m.id_kategori WHERE p.id_pengguna = ?";

        try (Connection connection = connMan.connectDb();
             PreparedStatement statement = connection.prepareStatement(query)) {
             statement.setString(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int idPengguna = resultSet.getInt("id_pengguna");
                    String namaPengguna = resultSet.getString("nama_pengguna");
                    String noPengguna = resultSet.getString("no_pengguna");
                    int idKategori = resultSet.getInt("id_kategori");
                    LocalDate tanggalMasuk = resultSet.getDate("tanggal_masuk") != null 
                                             ? resultSet.getDate("tanggal_masuk").toLocalDate() : null;
                    int durasi = resultSet.getInt("durasi");
                    LocalDate tanggalBerakhir = resultSet.getDate("tanggal_berakhir") != null 
                                                ? resultSet.getDate("tanggal_berakhir").toLocalDate() : null;
                    String status = resultSet.getString("status");
                    double totalBiaya = resultSet.getDouble("total_biaya");
                    String jenisPengunjung = resultSet.getString("jenis_pengunjung");
                    int harga = resultSet.getInt("harga");

                    // Create a MenuGym object
                    MenuGym menuGym = new MenuGym(idKategori, jenisPengunjung, harga);

                    // Create a PenggunaGym object
                    penggunaGym = new PenggunaGym(idPengguna, namaPengguna, noPengguna, menuGym, 
                                                  tanggalMasuk, durasi, status, totalBiaya, tanggalBerakhir);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error saat mencari data: " + e.getMessage());
        }

        return penggunaGym;
    }

    @Override
    public List<PenggunaGym> findAll() {
    List<PenggunaGym> penggunaGymList = new ArrayList<>();
    String query = "SELECT p.id_pengguna, p.nama_pengguna, p.no_pengguna, m.jenis_pengunjung, "
                 + "p.tanggal_masuk, p.durasi, p.tanggal_berakhir, p.status, p.total_biaya, p.id_kategori, "
                 + "m.harga FROM pengunjung p "
                 + "JOIN menu m ON p.id_kategori = m.id_kategori";

    try (Connection connection = connMan.connectDb();
         Statement statement = connection.createStatement();
         ResultSet resultSet = statement.executeQuery(query)) {

        while (resultSet.next()) {
            int idPengguna = resultSet.getInt("id_pengguna");
            String namaPengguna = resultSet.getString("nama_pengguna");
            String noPengguna = resultSet.getString("no_pengguna");
            int idKategori = resultSet.getInt("id_kategori");
            LocalDate tanggalMasuk = resultSet.getDate("tanggal_masuk") != null ? 
                                     resultSet.getDate("tanggal_masuk").toLocalDate() : null;
            int durasi = resultSet.getInt("durasi");
            LocalDate tanggalBerakhir = resultSet.getDate("tanggal_berakhir") != null ? 
                                        resultSet.getDate("tanggal_berakhir").toLocalDate() : null;
            String status = resultSet.getString("status");
            double totalBiaya = resultSet.getDouble("total_biaya");
            String jenisPengunjung = resultSet.getString("jenis_pengunjung");
            int harga = resultSet.getInt("harga");

            MenuGym menuGym = new MenuGym(idKategori, jenisPengunjung, harga);

            PenggunaGym penggunaGym = new PenggunaGym(idPengguna, namaPengguna, noPengguna, menuGym,
                                                      tanggalMasuk, durasi, status, totalBiaya, tanggalBerakhir);

            penggunaGymList.add(penggunaGym);
        }
    } catch (SQLException e) {
        System.err.println("Error saat mengambil data: " + e.getMessage());
    }

    return penggunaGymList;
}



    @Override
    public void update(String id, PenggunaGym penggunaGym) {
        String query = "UPDATE pengunjung SET nama_pengguna = ?, no_pengguna = ?, id_kategori = ?, "
                + "tanggal_masuk = ?, durasi = ?, tanggal_berakhir = ?, "
                + "status = ?, total_biaya = ? WHERE id_pengguna = ?";
        try (Connection connection = connMan.connectDb();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, penggunaGym.getNamaPengguna());

            statement.setString(2, penggunaGym.getNomorPengguna());

            if (penggunaGym.getIdKategori() != null) {
                statement.setInt(3, penggunaGym.getIdKategori().getIdKategori());
            } else {
                statement.setNull(3, java.sql.Types.INTEGER);
            }
            if (penggunaGym.getTanggalMasuk() != null) {
                statement.setDate(4, java.sql.Date.valueOf(penggunaGym.getTanggalMasuk()));
            } else {
                statement.setNull(4, java.sql.Types.DATE);
            }
            statement.setInt(5, penggunaGym.getDurasi());
          
            statement.setDate(6, java.sql.Date.valueOf(penggunaGym.tenggatStatus()));

            statement.setString(7, penggunaGym.status() ? "AKTIF" : "TIDAK AKTIF");

            statement.setDouble(8, penggunaGym.totalBiaya());

            statement.setString(9, id);

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Data berhasil diperbarui.");
            } else {
                System.out.println("Tidak ada data yang diperbarui.");
            }

        } catch (SQLException e) {
            System.err.println("Error saat memperbarui data: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
        }
    }


    @Override
    public boolean delete(int id_pengguna) {
        String query = "DELETE FROM pengunjung WHERE id_pengguna = ?";
        try (Connection connection = connMan.connectDb();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id_pengguna);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error saat menghapus data: " + e.getMessage());
            return false;
        }
    }
    
        public double getTotalPenghasilan() {
        String query = "SELECT SUM(total_biaya) AS totalBiaya FROM pengunjung";
        double totalBiaya = 0.0;

        try (Connection connection = connMan.connectDb();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            if (resultSet.next()) {
                totalBiaya = resultSet.getDouble("totalBiaya");
            }
        } catch (SQLException e) {
            System.err.println("Error saat menghitung total biaya: " + e.getMessage());
        }

        return totalBiaya;
    }
        
        public double getTotalPenghasilanByKategori(int kategoriId) {
            String query = "SELECT SUM(total_biaya) AS totalBiaya FROM pengunjung WHERE id_kategori = ?";
            double totalBiaya = 0.0;

            try (Connection connection = connMan.connectDb();
                 PreparedStatement statement = connection.prepareStatement(query)) {

                statement.setInt(1, kategoriId);

                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        totalBiaya = resultSet.getDouble("totalBiaya");
                    }
                }
            } catch (SQLException e) {
                System.err.println("Error saat menghitung total biaya berdasarkan kategori: " + e.getMessage());
            }

            return totalBiaya;
        }

        
        public int getJumlahKeanggotaan(){
            String query = "SELECT COUNT(*) AS totalAnggota FROM pengunjung";
            int count = 0;
            
            try (Connection connection = connMan.connectDb(); 
                    Statement statement = connection.createStatement();
                    ResultSet resultset = statement.executeQuery(query)){
                
                if (resultset.next()) {
                    count = resultset.getInt("totalAnggota");
                }
                
            } catch (Exception e) {
                System.err.println("Error saat menghitung jumlah pengunjung " + e.getMessage());
            }
            return count;
        }
        
         public int getJumlahKeanggotaanByKategori(int kategoriId) {
            String query = "SELECT COUNT(*) AS totalAnggota FROM pengunjung WHERE id_kategori = ?";
            int count = 0;

            try (Connection connection = connMan.connectDb();
                 PreparedStatement statement = connection.prepareStatement(query)) {

                // Set the category ID parameter
                statement.setInt(1, kategoriId);

                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        count = resultSet.getInt("totalAnggota");
                    }
                }
            } catch (SQLException e) {
                System.err.println("Error saat menghitung jumlah anggota berdasarkan kategori: " + e.getMessage());
            }

            return count;
        }

        
        

    
}
