/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itenas.is.oop.uasproject.gymproject.dbImplgym;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.itenas.is.oop.uasproject.gymproject.dbconfig.ConnectionManager;
import org.itenas.is.oop.uasproject.gymproject.model.MenuGym;
import org.itenas.is.oop.uasproject.gymproject.model.TransaksiGym;
import org.itenas.is.oop.uasproject.gymproject.service.CRUDService;
/**
 *
 * @author LENOVO
 */
public class MenuCRUD implements CRUDService<MenuGym>{
    private final ConnectionManager connMan;

    public MenuCRUD(ConnectionManager connMan) {
        this.connMan = connMan;
    }


    @Override
    public void create(MenuGym menuGym) {
        String query = "INSERT INTO menu (nama_menu, jenis_pengunjung, harga, durasi, deskripsi) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = connMan.connectDb();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, menuGym.getNamaMenu());
            statement.setString(2, menuGym.getJenisPengunjung());
            statement.setDouble(3, menuGym.getHarga());
            statement.setInt(4, menuGym.getDurasi());
            statement.setString(5, menuGym.getDeskripsi());
            statement.executeUpdate();
            System.out.println("Data berhasil ditambahkan.");
        } catch (SQLException e) {
            System.err.println("Error saat menambahkan data: " + e.getMessage());
        }
    }

   @Override
    public MenuGym findOne(String namaMenu) {
        String query = "SELECT * FROM menu WHERE nama_menu = ?";
        try (Connection connection = connMan.connectDb();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, namaMenu);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new MenuGym(
                        resultSet.getInt("id_kategori"),
                        resultSet.getString("nama_menu"),
                        resultSet.getString("jenis_pengunjung"),
                        resultSet.getDouble("harga"),
                        resultSet.getInt("durasi"),
                        resultSet.getString("deskripsi")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error saat mencari data berdasarkan nama_menu: " + e.getMessage());
        }
        return null;
    }


    @Override
    public List<MenuGym> findAll() {
        List<MenuGym> menuList = new ArrayList<>();
        String query = "SELECT * FROM menu";
        try (Connection connection = connMan.connectDb();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                MenuGym menuGym = new MenuGym(
                    resultSet.getInt("id_kategori"),
                    resultSet.getString("nama_menu"),
                    resultSet.getString("jenis_pengunjung"),
                    resultSet.getDouble("harga"),
                    resultSet.getInt("durasi"),
                    resultSet.getString("deskripsi")
                );
                menuList.add(menuGym);
            }
        } catch (SQLException e) {
            System.err.println("Error saat mengambil data: " + e.getMessage());
        }
        return menuList;
    }

    @Override
    public void update(String id, MenuGym menuGym) {
        String query = "UPDATE menu SET nama_menu = ?, jenis_pengunjung = ?, harga = ?, durasi = ?, deskripsi = ? WHERE id_kategori = ?";
        try (Connection connection = connMan.connectDb();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, menuGym.getNamaMenu());
            statement.setString(2, menuGym.getJenisPengunjung());
            statement.setDouble(3, menuGym.getHarga());
            statement.setInt(4, menuGym.getDurasi());
            statement.setString(5, menuGym.getDeskripsi());
            statement.setString(6, id);
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Data berhasil diperbarui.");
            } else {
                System.out.println("Tidak ada data yang diperbarui.");
            }
        } catch (SQLException e) {
            System.err.println("Error saat memperbarui data: " + e.getMessage());
        }
    }

    @Override
    public boolean delete(int id_kategori) {
        String query = "DELETE FROM menu WHERE id_kategori = ?";
        try (Connection connection = connMan.connectDb();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id_kategori);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Data berhasil dihapus.");
                return true;
            } else {
                System.out.println("Data tidak ditemukan.");
            }
        } catch (SQLException e) {
            System.err.println("Error saat menghapus data: " + e.getMessage());
        }
        return false;
    }

    
    
    
}
