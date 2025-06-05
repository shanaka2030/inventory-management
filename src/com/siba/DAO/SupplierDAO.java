/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.siba.DAO;

import com.siba.DTO.SupplierDTO;
import com.siba.Database.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Locale;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

/**
 *
 * @author shana
 */
public class SupplierDAO {
    public boolean addSupplier(SupplierDTO supplierDTO) {
        String checkQuery = "SELECT 1 FROM suppliers WHERE fullname = ? AND location = ? AND mobile = ?";
        String insertQuery = "INSERT INTO suppliers (suppliercode, fullname, location, mobile) VALUES (?, ?, ?, ?)";

        try (Connection conn = new DBConnection().getConnection();
             PreparedStatement checkStmt = conn.prepareStatement(checkQuery)) {

            checkStmt.setString(1, supplierDTO.getFullName());
            checkStmt.setString(2, supplierDTO.getLocation());
            checkStmt.setString(3, supplierDTO.getPhone());

            try (ResultSet rs = checkStmt.executeQuery()) {
                if (rs.next()) {
                    return false; // Already exists
                }
            }

            try (PreparedStatement insertStmt = conn.prepareStatement(insertQuery)) {
                insertStmt.setString(1, supplierDTO.getSuppCode());
                insertStmt.setString(2, supplierDTO.getFullName());
                insertStmt.setString(3, supplierDTO.getLocation());
                insertStmt.setString(4, supplierDTO.getPhone());
                insertStmt.executeUpdate();
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public boolean updateSupplier(SupplierDTO supplierDTO) {
        String query = "UPDATE suppliers SET fullname = ?, location = ?, mobile = ? WHERE suppliercode = ?";
        try (Connection conn = new DBConnection().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, supplierDTO.getFullName());
            stmt.setString(2, supplierDTO.getLocation());
            stmt.setString(3, supplierDTO.getPhone());
            stmt.setString(4, supplierDTO.getSuppCode());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean deleteSupplier(String suppCode) {
        String query = "DELETE FROM suppliers WHERE suppliercode = ?";
        try (Connection conn = new DBConnection().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, suppCode);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public ResultSet getAllSuppliers() {
        String query = "SELECT suppliercode, fullname, location, mobile FROM suppliers";
        try {
            Connection conn = new DBConnection().getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            return stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public ResultSet searchSuppliers(String searchText) {
        String query = "SELECT suppliercode, fullname, location, mobile FROM suppliers " +
                "WHERE suppliercode LIKE ? OR location LIKE ? OR fullname LIKE ? OR mobile LIKE ?";
        try {
            Connection conn = new DBConnection().getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            String likeText = "%" + searchText + "%";
            stmt.setString(1, likeText);
            stmt.setString(2, likeText);
            stmt.setString(3, likeText);
            stmt.setString(4, likeText);
            return stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public DefaultComboBoxModel<String> buildComboBoxModel(ResultSet rs) throws SQLException {
        Vector<String> items = new Vector<>();
        while (rs != null && rs.next()) {
            items.add(rs.getString("fullname"));
        }
        return new DefaultComboBoxModel<>(items);
    }
    
    public DefaultTableModel buildTableModel(ResultSet rs) throws SQLException {
        ResultSetMetaData metaData = rs.getMetaData();
        Vector<String> columnNames = new Vector<>();
        int colCount = metaData.getColumnCount();

        for (int i = 1; i <= colCount; i++) {
            columnNames.add(metaData.getColumnName(i).toUpperCase(Locale.ROOT));
        }

        Vector<Vector<Object>> data = new Vector<>();
        while (rs.next()) {
            Vector<Object> row = new Vector<>();
            for (int i = 1; i <= colCount; i++) {
                row.add(rs.getObject(i));
            }
            data.add(row);
        }

        return new DefaultTableModel(data, columnNames);
    }

}
