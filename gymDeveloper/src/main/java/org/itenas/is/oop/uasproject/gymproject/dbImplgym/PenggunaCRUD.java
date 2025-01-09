/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itenas.is.oop.uasproject.gymproject.dbImplgym;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.itenas.is.oop.uasproject.gymproject.dbconfig.ConnectionManager;
import org.itenas.is.oop.uasproject.gymproject.model.DataPengguna;
import org.itenas.is.oop.uasproject.gymproject.service.CRUDService;

/**
 *
 * @author Hafiz Yazid
 */
public class PenggunaCRUD implements CRUDService<DataPengguna>{
    private final ConnectionManager connMan;
    
    public PenggunaCRUD(ConnectionManager connMan) {
        this.connMan = connMan;
    }

     @Override
    public void create(DataPengguna pengguna) {
        String query = "INSERT INTO pengguna (nama_pengguna, email_pengguna, alamat_pengguna, no_pengguna) VALUES (?, ?, ?, ?)";
        try (Connection connection = connMan.connectDb();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, pengguna.getNamaPengguna());
            statement.setString(2, pengguna.getEmailPengguna());
            statement.setString(3, pengguna.getAlamatPengguna());
            statement.setString(4, pengguna.getNoPengguna());
            statement.executeUpdate();
            System.out.println("Data pengguna berhasil ditambahkan.");
        } catch (SQLException e) {
            System.err.println("Error saat menambahkan data pengguna: " + e.getMessage());
        }
    }
    
    @Override
    public DataPengguna findOne(String id) {
        String query = "SELECT * FROM pengguna WHERE id_pengguna = ?";
        try (Connection connection = connMan.connectDb();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, Integer.parseInt(id));
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                DataPengguna pengguna = new DataPengguna();
                pengguna.setIdPengguna(resultSet.getInt("id_pengguna"));
                pengguna.setNamaPengguna(resultSet.getString("nama_pengguna"));
                pengguna.setEmailPengguna(resultSet.getString("email_pengguna"));
                pengguna.setAlamatPengguna(resultSet.getString("alamat_pengguna"));
                pengguna.setNoPengguna(resultSet.getString("no_pengguna"));
                return pengguna;
            }
        } catch (SQLException e) {
            System.err.println("Error saat mencari data pengguna: " + e.getMessage());
        }
        return null;
    }
    
    public DataPengguna findNama(String namaPengguna) {
    String query = "SELECT * FROM pengguna WHERE nama_pengguna LIKE ?";
    try (Connection connection = connMan.connectDb();
         PreparedStatement statement = connection.prepareStatement(query)) {
        statement.setString(1, "%" + namaPengguna + "%"); 
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            DataPengguna pengguna = new DataPengguna();
            pengguna.setIdPengguna(resultSet.getInt("id_pengguna"));
            pengguna.setNamaPengguna(resultSet.getString("nama_pengguna"));
            pengguna.setEmailPengguna(resultSet.getString("email_pengguna"));
            pengguna.setAlamatPengguna(resultSet.getString("alamat_pengguna"));
            pengguna.setNoPengguna(resultSet.getString("no_pengguna"));
            return pengguna;
        }
    } catch (SQLException e) {
        System.err.println("Error saat mencari data pengguna: " + e.getMessage());
    }
    return null;
}


    @Override
    public List<DataPengguna> findAll() {
        String query = "SELECT * FROM pengguna";
        List<DataPengguna> penggunaList = new ArrayList<>();
        try (Connection connection = connMan.connectDb();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                DataPengguna pengguna = new DataPengguna();
                pengguna.setIdPengguna(resultSet.getInt("id_pengguna"));
                pengguna.setNamaPengguna(resultSet.getString("nama_pengguna"));
                pengguna.setEmailPengguna(resultSet.getString("email_pengguna"));
                pengguna.setAlamatPengguna(resultSet.getString("alamat_pengguna"));
                pengguna.setNoPengguna(resultSet.getString("no_pengguna"));
                penggunaList.add(pengguna);
            }
        } catch (SQLException e) {
            System.err.println("Error saat mengambil semua data pengguna: " + e.getMessage());
        }
        return penggunaList;
    }

    @Override
    public void update(String id, DataPengguna pengguna) {
        String query = "UPDATE pengguna SET nama_pengguna = ?, email_pengguna = ?, alamat_pengguna = ?, no_pengguna = ? WHERE id_pengguna = ?";
        try (Connection connection = connMan.connectDb();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, pengguna.getNamaPengguna());
            statement.setString(2, pengguna.getEmailPengguna());
            statement.setString(3, pengguna.getAlamatPengguna());
            statement.setString(4, pengguna.getNoPengguna());
            statement.setInt(5, Integer.parseInt(id));
            statement.executeUpdate();
            System.out.println("Data pengguna berhasil diperbarui.");
        } catch (SQLException e) {
            System.err.println("Error saat memperbarui data pengguna: " + e.getMessage());
        }
    }

    @Override
    public boolean delete(int id) {
        String query = "DELETE FROM pengguna WHERE id_pengguna = ?";
        try (Connection connection = connMan.connectDb();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error saat menghapus data pengguna: " + e.getMessage());
        }
        return false;
    }    
    
     
    public boolean isUserExistByPhone(String phoneNumber) {
    String query = "SELECT COUNT(*) FROM pengguna WHERE no_pengguna = ?";
    try (Connection connection = connMan.connectDb();
         PreparedStatement statement = connection.prepareStatement(query)) {
        statement.setString(1, phoneNumber);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next() && resultSet.getInt(1) > 0) {
            return true;  // Nomor telepon sudah terdaftar
        }
    } catch (SQLException e) {
        System.err.println("Error saat memeriksa nomor telepon: " + e.getMessage());
    }
    return false;  // Nomor telepon belum terdaftar
}

public boolean isUserExistByEmail(String email) {
    String query = "SELECT COUNT(*) FROM pengguna WHERE email_pengguna = ?";
    try (Connection connection = connMan.connectDb();
         PreparedStatement statement = connection.prepareStatement(query)) {
        statement.setString(1, email);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next() && resultSet.getInt(1) > 0) {
            return true;  // Email sudah terdaftar
        }
    } catch (SQLException e) {
        System.err.println("Error saat memeriksa email: " + e.getMessage());
    }
    return false;  // Email belum terdaftar
}

    
}
