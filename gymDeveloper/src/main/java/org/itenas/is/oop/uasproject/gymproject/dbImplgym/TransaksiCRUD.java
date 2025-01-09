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
import org.itenas.is.oop.uasproject.gymproject.model.TransaksiGym;
import org.itenas.is.oop.uasproject.gymproject.model.MenuGym;
import org.itenas.is.oop.uasproject.gymproject.model.DataPengguna;

public class TransaksiCRUD implements CRUDService<TransaksiGym> {
    private final ConnectionManager connMan;
    
    public TransaksiCRUD(ConnectionManager connMan) {
        this.connMan = connMan;
    }

    @Override
        public void create(TransaksiGym transaksiGym) {
            String query = "INSERT INTO transaksi (id_pengguna, id_kategori, tanggal_pendaftaran, "
                         + "tanggal_berakhir, status, total_biaya, diskon) VALUES (?, ?, ?, ?, ?, ?, ?)";
            try (Connection connection = connMan.connectDb();
                 PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, transaksiGym.getDataPengguna().getIdPengguna());
                statement.setInt(2, transaksiGym.getDataMenu().getIdKategori());
                statement.setDate(3, java.sql.Date.valueOf(transaksiGym.getTanggalPendaftaran()));
                statement.setDate(4, java.sql.Date.valueOf(transaksiGym.getTanggalBerakhir()));
                statement.setString(5, transaksiGym.getStatus());
                statement.setDouble(6, transaksiGym.getTotalBiaya());
                statement.setInt(7, transaksiGym.getDiskon());
                statement.executeUpdate();
                System.out.println("Data transaksi berhasil ditambahkan.");
            } catch (SQLException e) {
                System.err.println("Error saat menambahkan data transaksi: " + e.getMessage());
            }
        }


    @Override
        public TransaksiGym findOne(String id) {
            TransaksiGym transaksiGym = null;
            String query = "SELECT t.id_transaksi, t.tanggal_pendaftaran, t.tanggal_berakhir, t.status, "
                         + "t.total_biaya, t.diskon, p.id_pengguna, p.nama_pengguna, p.no_pengguna, "
                         + "m.id_kategori, m.nama_menu, m.jenis_pengunjung, m.harga, m.durasi, m.deskripsi "
                         + "FROM transaksi t "
                         + "JOIN pengguna p ON t.id_pengguna = p.id_pengguna "
                         + "JOIN menu m ON t.id_kategori = m.id_kategori "
                         + "WHERE t.id_transaksi = ?";
            try (Connection connection = connMan.connectDb();
                 PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, id);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        DataPengguna pengguna = new DataPengguna();
                        pengguna.setIdPengguna(resultSet.getInt("id_pengguna"));
                        pengguna.setNamaPengguna(resultSet.getString("nama_pengguna"));
                        pengguna.setNoPengguna(resultSet.getString("no_pengguna"));

                        MenuGym menu = new MenuGym(
                            resultSet.getInt("id_kategori"),
                            resultSet.getString("nama_menu"),
                            resultSet.getString("jenis_pengunjung"),
                            resultSet.getDouble("harga"),
                            resultSet.getInt("durasi"),
                            resultSet.getString("deskripsi")
                        );

                        transaksiGym = new TransaksiGym(
                            resultSet.getInt("id_transaksi"),
                            pengguna,
                            menu,
                            resultSet.getDate("tanggal_pendaftaran").toLocalDate(),
                            resultSet.getString("status"),
                            resultSet.getDouble("total_biaya"),
                            resultSet.getDate("tanggal_berakhir").toLocalDate(),
                            resultSet.getInt("diskon")
                        );
                    }
                }
            } catch (SQLException e) {
                System.err.println("Error saat mengambil data transaksi: " + e.getMessage());
            }
            return transaksiGym;
        }

    @Override
        public List<TransaksiGym> findAll() {
            List<TransaksiGym> transaksiList = new ArrayList<>();
            String query = "SELECT t.id_transaksi, t.tanggal_pendaftaran, t.tanggal_berakhir, t.status, "
                         + "t.total_biaya, t.diskon, p.id_pengguna, p.nama_pengguna, p.no_pengguna, "
                         + "m.id_kategori, m.nama_menu, m.jenis_pengunjung, m.harga, m.durasi, m.deskripsi "
                         + "FROM transaksi t "
                         + "JOIN pengguna p ON t.id_pengguna = p.id_pengguna "
                         + "JOIN menu m ON t.id_kategori = m.id_kategori";
            try (Connection connection = connMan.connectDb();
                 Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(query)) {
                while (resultSet.next()) {
                    DataPengguna pengguna = new DataPengguna();
                    pengguna.setIdPengguna(resultSet.getInt("id_pengguna"));
                    pengguna.setNamaPengguna(resultSet.getString("nama_pengguna"));
                    pengguna.setNoPengguna(resultSet.getString("no_pengguna"));

                    MenuGym menu = new MenuGym(
                        resultSet.getInt("id_kategori"),
                        resultSet.getString("nama_menu"),
                        resultSet.getString("jenis_pengunjung"),
                        resultSet.getDouble("harga"),
                        resultSet.getInt("durasi"),
                        resultSet.getString("deskripsi")
                    );

                    TransaksiGym transaksi = new TransaksiGym(
                        resultSet.getInt("id_transaksi"),
                        pengguna,
                        menu,
                        resultSet.getDate("tanggal_pendaftaran").toLocalDate(),
                        resultSet.getString("status"),
                        resultSet.getDouble("total_biaya"),
                        resultSet.getDate("tanggal_berakhir").toLocalDate(),
                        resultSet.getInt("diskon")
                    );
                    transaksiList.add(transaksi);
                }
            } catch (SQLException e) {
                System.err.println("Error saat mengambil data transaksi: " + e.getMessage());
            }
            return transaksiList;
        }


    @Override
        public void update(String id, TransaksiGym transaksiGym) {
            String query = "UPDATE transaksi SET id_pengguna = ?, id_kategori = ?, "
                         + "tanggal_pendaftaran = ?, tanggal_berakhir = ?, status = ?, "
                         + "total_biaya = ?, diskon = ? WHERE id_transaksi = ?";
            try (Connection connection = connMan.connectDb();
                 PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, transaksiGym.getDataPengguna().getIdPengguna());
                statement.setInt(2, transaksiGym.getDataMenu().getIdKategori());
                statement.setDate(3, java.sql.Date.valueOf(transaksiGym.getTanggalPendaftaran()));
                statement.setDate(4, java.sql.Date.valueOf(transaksiGym.getTanggalBerakhir()));
                statement.setString(5, transaksiGym.getStatus());
                statement.setDouble(6, transaksiGym.getTotalBiaya());
                statement.setInt(7, transaksiGym.getDiskon());
                statement.setString(8, id);
                statement.executeUpdate();
                System.out.println("Data transaksi berhasil diperbarui.");
            } catch (SQLException e) {
                System.err.println("Error saat memperbarui data transaksi: " + e.getMessage());
            }
        }
    @Override
        public boolean delete(int idTransaksi) {
            String query = "DELETE FROM transaksi WHERE id_transaksi = ?";
            try (Connection connection = connMan.connectDb();
                 PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, idTransaksi);
                return statement.executeUpdate() > 0;
            } catch (SQLException e) {
                System.err.println("Error saat menghapus data transaksi: " + e.getMessage());
                return false;
            }
        }
        
        public boolean sudahMemilikiPaketAktif(int idPengguna) {
            if (idPengguna == 0) {
                return false; 
            }

            String query = "SELECT * FROM transaksi WHERE id_pengguna = ? AND status = 'AKTIF'";
            try (Connection connection = connMan.connectDb();
                 PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, idPengguna);

                ResultSet resultSet = statement.executeQuery();

     
                return resultSet.next(); // Mengembalikan true jika ada transaksi aktif
            } catch (SQLException e) {
                System.err.println("Error saat memeriksa status paket: " + e.getMessage());
                return false;
            }
        }


    
        public double getTotalPenghasilan() {
        String query = "SELECT SUM(total_biaya) AS totalBiaya FROM transaksi";
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

        
        public int getJumlahKeanggotaan(){
            String query = "SELECT COUNT(*) AS totalAnggota FROM transaksi";
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
            
        

    
}
