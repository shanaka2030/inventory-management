/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.siba.Database;

import java.sql.*;
/**
 *
 * @author shana
 */
public class DBConnection {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/inventory";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    public Connection getConnection() throws SQLException {
        try {
            Class.forName(DRIVER);
            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Connected successfully.");
            return conn;
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL JDBC Driver not found", e);
        }
    }

    public boolean testConnection() {
        try (Connection conn = getConnection()) {
            return conn != null && !conn.isClosed();
        } catch (SQLException e) {
            System.err.println("Connection test failed: " + e.getMessage());
            return false;
        }
    }
}
