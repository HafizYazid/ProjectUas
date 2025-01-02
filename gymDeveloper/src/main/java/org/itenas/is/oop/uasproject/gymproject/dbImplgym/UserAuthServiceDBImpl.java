/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itenas.is.oop.uasproject.gymproject.dbImplgym;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import org.itenas.is.oop.uasproject.gymproject.dbconfig.ConnectionManager;
import org.itenas.is.oop.uasproject.gymproject.model.User;
import org.itenas.is.oop.uasproject.gymproject.service.UserAuthService;

/**
 *
 * @author LENOVO
 */
public class UserAuthServiceDBImpl implements UserAuthService {
    
    private ConnectionManager connMan;

    public UserAuthServiceDBImpl() {
        this.connMan = new ConnectionManager();
    }

    @Override
    public User authenticateUser(User user) {
        User loginUser = null;
        String query = "SELECT username, role FROM users WHERE username = ? AND password = ?";
        try (Connection connection = connMan.connectDb();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String dbUsername = resultSet.getString("username");
                    String role = resultSet.getString("role");
                    loginUser = new User(dbUsername, null, role); // Password tidak dikembalikan untuk keamanan
                }
            }
                } catch (SQLException ex) {
            if (ex.getMessage().toLowerCase().contains("communications link failure")) {
                JOptionPane.showMessageDialog(null, "Database server is not running! Please start the database server and try again.", "Database Error", JOptionPane.ERROR_MESSAGE);
            } else {
                System.err.println("Database error: " + ex.getMessage());
            }
        }
        return loginUser;

    
    } 

    @Override
    public User registrasiUser(User user) {
        User regisUser = null;
        String query = "INSERT INTO users (username, password, role) VALUES (?, ?, ?)";
        try (Connection connection = connMan.connectDb();
             PreparedStatement statement = connection.prepareStatement(query)) {

            // Menyusun data yang akan dimasukkan ke dalam query
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getRole());  // Role akan diambil dari parameter user

            int affectedRows = statement.executeUpdate();  // Mengeksekusi query insert

            // Jika baris berhasil dimasukkan, buat objek User yang baru
            if (affectedRows > 0) {
                regisUser = new User(user.getUsername(), null, user.getRole());  // Mengembalikan objek User dengan username dan role
            }

        } catch (SQLException ex) {
            // Menangani error yang terjadi selama proses query
            if (ex.getMessage().toLowerCase().contains("communications link failure")) {
                JOptionPane.showMessageDialog(null, "Database server is not running! Please start the database server and try again.", "Database Error", JOptionPane.ERROR_MESSAGE);
            } else {
                System.err.println("Database error: " + ex.getMessage());
            }
        }
        return regisUser; // Mengembalikan objek User yang telah terdaftar
    }


    
}
