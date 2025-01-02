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
import org.itenas.is.oop.uasproject.gymproject.model.PenggunaGym;
import org.itenas.is.oop.uasproject.gymproject.service.CRUDService;
/**
 *
 * @author LENOVO
 */
public class MenuRU implements CRUDService<MenuGym>{
    private final ConnectionManager connMan;

    public MenuRU(ConnectionManager connMan) {
        this.connMan = connMan;
    }


    @Override
    public void create(MenuGym object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public MenuGym findOne(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<MenuGym> findAll() {
        List<MenuGym> menuPengguna = new ArrayList<>();
    String query = "SELECT * FROM menu";
    try (Connection connection = connMan.connectDb();
         Statement statement = connection.createStatement();
         ResultSet resultSet = statement.executeQuery(query)) {
        while (resultSet.next()) {
            MenuGym menuGym = new MenuGym(
                resultSet.getInt("id_kategori"),
                resultSet.getString("jenis_pengunjung"),
                resultSet.getInt("harga")
            );
            menuPengguna.add(menuGym);
        }
    } catch (SQLException e) {
        System.err.println("Error saat mengambil data: " + e.getMessage());
    }
    return menuPengguna;
    }

    @Override
    public void update(String id, MenuGym menuGym) {
         String query = "UPDATE menu SET harga = ? WHERE id_kategori = ?";
        try (Connection connection = connMan.connectDb();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, menuGym.getHarga());
            statement.setString(2, id);
            statement.executeUpdate();
            System.out.println("Data berhasil diperbarui.");
        } catch (SQLException e) {
            System.err.println("Error saat memperbarui data: " + e.getMessage());
        }
    }

    @Override
    public boolean delete(int id_pengguna) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
    
}
