/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.siba.DAO;

import com.siba.DTO.CustomerDTO;
import com.siba.Database.DBConnection;
import java.sql.*;
import java.util.Locale;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author shana
 */
public class CustomerDAO {
    public void addCustomer(CustomerDTO customerDTO) {
        String checkQuery = "SELECT 1 FROM customers WHERE fullname = ? AND location = ? AND phone = ?";
        String insertQuery = "INSERT INTO customers (customercode, fullname, location, phone) VALUES (?, ?, ?, ?)";

        try (Connection conn = new DBConnection().getConnection();
             PreparedStatement checkStmt = conn.prepareStatement(checkQuery)) {

            checkStmt.setString(1, customerDTO.getFullName());
            checkStmt.setString(2, customerDTO.getLocation());
            checkStmt.setString(3, customerDTO.getPhone());

            try (ResultSet rs = checkStmt.executeQuery()) {
                if (rs.next()) {
                    System.out.println("Customer already exists.");
                    return;
                }
            }

            try (PreparedStatement insertStmt = conn.prepareStatement(insertQuery)) {
                insertStmt.setString(1, customerDTO.getCustCode());
                insertStmt.setString(2, customerDTO.getFullName());
                insertStmt.setString(3, customerDTO.getLocation());
                insertStmt.setString(4, customerDTO.getPhone());
                insertStmt.executeUpdate();
                System.out.println("New customer added.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editCustomer(CustomerDTO customerDTO) {
        String query = "UPDATE customers SET fullname = ?, location = ?, phone = ? WHERE customercode = ?";
        try (Connection conn = new DBConnection().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, customerDTO.getFullName());
            stmt.setString(2, customerDTO.getLocation());
            stmt.setString(3, customerDTO.getPhone());
            stmt.setString(4, customerDTO.getCustCode());
            stmt.executeUpdate();
            System.out.println("Customer details updated.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteCustomer(String custCode) {
        String query = "DELETE FROM customers WHERE customercode = ?";
        try (Connection conn = new DBConnection().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, custCode);
            stmt.executeUpdate();
            System.out.println("Customer deleted.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet getQueryResult() {
        String query = "SELECT customercode, fullname, location, phone FROM customers";
        try {
            Connection conn = new DBConnection().getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            return stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ResultSet getCustomerSearch(String text) {
        String query = "SELECT customercode, fullname, location, phone FROM customers " +
                       "WHERE customercode LIKE ? OR fullname LIKE ? OR location LIKE ? OR phone LIKE ?";
        try {
            Connection conn = new DBConnection().getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            String searchText = "%" + text + "%";
            stmt.setString(1, searchText);
            stmt.setString(2, searchText);
            stmt.setString(3, searchText);
            stmt.setString(4, searchText);
            return stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ResultSet getCustName(String custCode) {
        String query = "SELECT * FROM customers WHERE customercode = ?";
        try {
            Connection conn = new DBConnection().getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, custCode);
            return stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ResultSet getProdName(String prodCode) {
        String query = "SELECT productname, currentstock.quantity FROM products " +
                       "INNER JOIN currentstock ON products.productcode = currentstock.productcode " +
                       "WHERE currentstock.productcode = ?";
        try {
            Connection conn = new DBConnection().getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, prodCode);
            return stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public DefaultTableModel buildTableModel(ResultSet resultSet) throws SQLException {
        ResultSetMetaData metaData = resultSet.getMetaData();
        Vector<String> columnNames = new Vector<>();
        int colCount = metaData.getColumnCount();

        for (int col = 1; col <= colCount; col++) {
            columnNames.add(metaData.getColumnName(col).toUpperCase(Locale.ROOT));
        }

        Vector<Vector<Object>> data = new Vector<>();
        while (resultSet.next()) {
            Vector<Object> row = new Vector<>();
            for (int col = 1; col <= colCount; col++) {
                row.add(resultSet.getObject(col));
            }
            data.add(row);
        }

        return new DefaultTableModel(data, columnNames);
    }
}

