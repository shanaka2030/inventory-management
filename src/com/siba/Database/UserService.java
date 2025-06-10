/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.siba.Database;
import com.siba.util.PasswordUtil;
import java.sql.*;
/**
 *
 * @author shana
 */
public class UserService {
    private DBConnection connect;
    
    public UserService() {
        this.connect = new DBConnection();
    }
    
    public UserService(DBConnection connect) {
        this.connect = connect;
    }
    
    public boolean checkLogin(String username, String password, String userType) {
        String query = "SELECT id FROM users WHERE username = ? AND password = ? AND usertype = ? LIMIT 1";
        
        try (Connection conn = connect.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setString(1, username);
            pstmt.setString(2, password); //PasswordUtil.hashPassword(password)
            pstmt.setString(3, userType);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next();
                //return true;
            }
            
        } catch (SQLException e) {
            System.err.println("Database error during login verification: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean createUser(String username, String password, String userType) {
        String query = "INSERT INTO users (username, password, usertype) VALUES (?, ?, ?)";
        
        try (Connection conn = connect.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setString(1, username);
            pstmt.setString(2, PasswordUtil.hashPassword(password));
            pstmt.setString(3, userType);
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Database error during user creation: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean userExists(String username) {
        String query = "SELECT id FROM users WHERE username = ? LIMIT 1";
        
        try (Connection conn = connect.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setString(1, username);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next();
            }
            
        } catch (SQLException e) {
            System.err.println("Database error during user existence check: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}
