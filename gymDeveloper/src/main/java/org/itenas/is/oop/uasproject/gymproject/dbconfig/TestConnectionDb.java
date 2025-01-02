/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itenas.is.oop.uasproject.gymproject.dbconfig;
import java.sql.Connection;
/**
 *
 * @author Hafiz Yazid
 */
public class TestConnectionDb {
    public static void main(String[] args) {
    // Create DatabaseConnection instance
    ConnectionManager conMan = new ConnectionManager();
    
    //Connect to the database
    Connection conn = conMan.connectDb();
    
    // Disconnect from the databse
    conMan.disconnectDb(conn);
    }
}
