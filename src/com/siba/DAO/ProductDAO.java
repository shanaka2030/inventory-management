/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.siba.DAO;

import com.siba.DTO.ProductDTO;
import com.siba.Database.DBConnection;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.Locale;
import java.util.Vector;
import java.util.logging.Logger;
import java.util.logging.Level;

/**
 *
 * @author shana
 */
public class ProductDAO {

    private static final Logger LOGGER = Logger.getLogger(ProductDAO.class.getName());
    private final DBConnection connection;

    public ProductDAO() {
        this.connection = new DBConnection();
    }

    // Helper method to validate string inputs
    private boolean isValidString(String input) {
        return input != null && !input.trim().isEmpty() && input.length() <= 255;
    }

    // Helper method to validate numeric inputs
    private boolean isValidPositiveNumber(Double number) {
        return number != null && number > 0;
    }

    private boolean isValidPositiveInteger(Integer number) {
        return number != null && number > 0;
    }

    public ResultSet getSuppInfo() {
        String query = "SELECT suppliercode, fullname, address, phone, email FROM suppliers ORDER BY fullname";
        try (Connection conn = connection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            return stmt.executeQuery();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error retrieving supplier information", e);
            return null;
        }
    }

    public ResultSet getCustInfo() {
        String query = "SELECT customercode, fullname, address, phone, email FROM customers ORDER BY fullname";
        try (Connection conn = connection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            return stmt.executeQuery();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error retrieving customer information", e);
            return null;
        }
    }

    public ResultSet getProdStock() {
        String query = "SELECT productcode, quantity FROM currentstock WHERE quantity > 0 ORDER BY productcode";
        try (Connection conn = connection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            return stmt.executeQuery();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error retrieving product stock", e);
            return null;
        }
    }

    public ResultSet getProdInfo() {
        String query = "SELECT productcode, productname, costprice, sellprice, brand FROM products ORDER BY productname";
        try (Connection conn = connection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            return stmt.executeQuery();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error retrieving product information", e);
            return null;
        }
    }

    public Double getProdCost(String prodCode) {
        if (!isValidString(prodCode)) {
            LOGGER.warning("Invalid product code provided for cost lookup");
            return null;
        }

        String query = "SELECT costprice FROM products WHERE productcode = ? LIMIT 1";
        try (Connection conn = connection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, prodCode.trim());
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getDouble("costprice");
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error retrieving product cost for code: " + prodCode, e);
        }
        return null;
    }

    public Double getProdSell(String prodCode) {
        if (!isValidString(prodCode)) {
            LOGGER.warning("Invalid product code provided for sell price lookup");
            return null;
        }

        String query = "SELECT sellprice FROM products WHERE productcode = ? LIMIT 1";
        try (Connection conn = connection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, prodCode.trim());
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getDouble("sellprice");
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error retrieving product sell price for code: " + prodCode, e);
        }
        return null;
    }

    public String getSuppCode(String suppName) {
        if (!isValidString(suppName)) {
            LOGGER.warning("Invalid supplier name provided");
            return null;
        }

        String query = "SELECT suppliercode FROM suppliers WHERE fullname = ? LIMIT 1";
        try (Connection conn = connection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, suppName.trim());
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("suppliercode");
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error retrieving supplier code for name: " + suppName, e);
        }
        return null;
    }

    public String getProdCode(String prodName) {
        if (!isValidString(prodName)) {
            LOGGER.warning("Invalid product name provided");
            return null;
        }

        String query = "SELECT productcode FROM products WHERE productname = ? LIMIT 1";
        try (Connection conn = connection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, prodName.trim());
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("productcode");
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error retrieving product code for name: " + prodName, e);
        }
        return null;
    }

    public String getCustCode(String custName) {
        if (!isValidString(custName)) {
            LOGGER.warning("Invalid customer name provided");
            return null;
        }

        String query = "SELECT customercode FROM customers WHERE fullname = ? LIMIT 1";
        try (Connection conn = connection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, custName.trim());
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("customercode");
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error retrieving customer code for name: " + custName, e);
        }
        return null;
    }

    //Products data set retrieval for display
    public ResultSet getQueryResult() {
        Connection conn = null;
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        try {
            String query = "SELECT productcode,productname,costprice,sellprice,brand FROM products ORDER BY pid";
            conn = connection.getConnection();
            statement = conn.prepareStatement(query);
            resultSet = statement.executeQuery(query);
            return resultSet;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public ResultSet getProdName(String code) {
        Connection conn = null;
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        try {
            String query = "SELECT productname FROM products WHERE productcode='" + code + "'";
            conn = connection.getConnection();
            statement = conn.prepareStatement(query);
            resultSet = statement.executeQuery(query);
            return resultSet;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }
//    public ResultSet getQueryResult(){
//        ResultSet result = null;
//        String query = "SELECT productcode,productname,costprice,sellprice,brand FROM products ORDER BY pid";
//        
//        try(Connection conn = connection.getConnection();
//                PreparedStatement stmt = conn.prepareStatement(query)){
//            result = stmt.executeQuery();
//        }catch (SQLException e) {
//            LOGGER.log(Level.SEVERE, "Error checking stock for product code: " + e);
//        }
//        return result;
//    }

    public boolean checkStock(String prodCode) {
        if (!isValidString(prodCode)) {
            LOGGER.warning("Invalid product code provided for stock check");
            return false;
        }

        String query = "SELECT 1 FROM currentstock WHERE productcode = ? AND quantity > 0 LIMIT 1";
        try (Connection conn = connection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, prodCode.trim());
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error checking stock for product code: " + prodCode, e);
            return false;
        }
    }

    public boolean addProductDAO(ProductDTO productDTO) {
        if (!validateProductDTO(productDTO)) {
            JOptionPane.showMessageDialog(null, "Invalid product data provided.");
            return false;
        }

        // Check if product already exists
        String checkQuery = "SELECT 1 FROM products WHERE productname = ? AND costprice = ? AND sellprice = ? AND brand = ? LIMIT 1";
        try (Connection conn = connection.getConnection(); PreparedStatement checkStmt = conn.prepareStatement(checkQuery)) {

            checkStmt.setString(1, productDTO.getProdName().trim());
            checkStmt.setDouble(2, productDTO.getCostPrice());
            checkStmt.setDouble(3, productDTO.getSellPrice());
            checkStmt.setString(4, productDTO.getBrand().trim());

            try (ResultSet rs = checkStmt.executeQuery()) {
                if (rs.next()) {
                    JOptionPane.showMessageDialog(null, "Product has already been added.");
                    return false;
                }
            }

            return addProductFunction(conn, productDTO);

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error adding product", e);
            JOptionPane.showMessageDialog(null, "Error adding product: " + e.getMessage());
            return false;
        }
    }

    private boolean addProductFunction(Connection conn, ProductDTO productDTO) throws SQLException {
        conn.setAutoCommit(false);

        try {
            // Insert into products table
            String productQuery = "INSERT INTO products (productcode, productname, costprice, sellprice, brand) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement productStmt = conn.prepareStatement(productQuery)) {
                productStmt.setString(1, productDTO.getProdCode().trim());
                productStmt.setString(2, productDTO.getProdName().trim());
                productStmt.setDouble(3, productDTO.getCostPrice());
                productStmt.setDouble(4, productDTO.getSellPrice());
                productStmt.setString(5, productDTO.getBrand().trim());
                productStmt.executeUpdate();
            }

            // Insert into currentstock table
            String stockQuery = "INSERT INTO currentstock (productcode, quantity) VALUES (?, ?)";
            try (PreparedStatement stockStmt = conn.prepareStatement(stockQuery)) {
                stockStmt.setString(1, productDTO.getProdCode().trim());
                stockStmt.setInt(2, productDTO.getQuantity());
                stockStmt.executeUpdate();
            }

            conn.commit();
            JOptionPane.showMessageDialog(null, "Product added and ready for sale.");
            return true;

        } catch (SQLException e) {
            conn.rollback();
            throw e;
        } finally {
            conn.setAutoCommit(true);
        }
    }

    public boolean addPurchaseDAO(ProductDTO productDTO) {
        if (!validatePurchaseDTO(productDTO)) {
            JOptionPane.showMessageDialog(null, "Invalid purchase data provided.");
            return false;
        }

        try (Connection conn = connection.getConnection()) {
            conn.setAutoCommit(false);

            try {
                // Insert purchase record
                String purchaseQuery = "INSERT INTO purchaseinfo (suppliercode, productcode, date, quantity, totalcost) VALUES (?, ?, ?, ?, ?)";
                try (PreparedStatement purchaseStmt = conn.prepareStatement(purchaseQuery)) {
                    purchaseStmt.setString(1, productDTO.getSuppCode().trim());
                    purchaseStmt.setString(2, productDTO.getProdCode().trim());
                    purchaseStmt.setString(3, productDTO.getDate());
                    purchaseStmt.setInt(4, productDTO.getQuantity());
                    purchaseStmt.setDouble(5, productDTO.getTotalCost());
                    purchaseStmt.executeUpdate();
                }

                // Update or insert stock
                updateStockAfterPurchase(conn, productDTO);

                conn.commit();
                JOptionPane.showMessageDialog(null, "Purchase log added.");
                return true;

            } catch (SQLException e) {
                conn.rollback();
                throw e;
            } finally {
                conn.setAutoCommit(true);
            }

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error adding purchase", e);
            JOptionPane.showMessageDialog(null, "Error adding purchase: " + e.getMessage());
            return false;
        }
    }

    private void updateStockAfterPurchase(Connection conn, ProductDTO productDTO) throws SQLException {
        String checkStockQuery = "SELECT quantity FROM currentstock WHERE productcode = ?";
        try (PreparedStatement checkStmt = conn.prepareStatement(checkStockQuery)) {
            checkStmt.setString(1, productDTO.getProdCode().trim());
            try (ResultSet rs = checkStmt.executeQuery()) {
                if (rs.next()) {
                    // Update existing stock
                    String updateQuery = "UPDATE currentstock SET quantity = quantity + ? WHERE productcode = ?";
                    try (PreparedStatement updateStmt = conn.prepareStatement(updateQuery)) {
                        updateStmt.setInt(1, productDTO.getQuantity());
                        updateStmt.setString(2, productDTO.getProdCode().trim());
                        updateStmt.executeUpdate();
                    }
                } else {
                    // Insert new stock record
                    String insertQuery = "INSERT INTO currentstock (productcode, quantity) VALUES (?, ?)";
                    try (PreparedStatement insertStmt = conn.prepareStatement(insertQuery)) {
                        insertStmt.setString(1, productDTO.getProdCode().trim());
                        insertStmt.setInt(2, productDTO.getQuantity());
                        insertStmt.executeUpdate();
                    }
                }
            }
        }
    }

    public boolean editProdDAO(ProductDTO productDTO) {
        if (!validateProductDTO(productDTO)) {
            JOptionPane.showMessageDialog(null, "Invalid product data provided.");
            return false;
        }

        try (Connection conn = connection.getConnection()) {
            conn.setAutoCommit(false);

            try {
                // Update product
                String productQuery = "UPDATE products SET productname = ?, costprice = ?, sellprice = ?, brand = ? WHERE productcode = ?";
                try (PreparedStatement productStmt = conn.prepareStatement(productQuery)) {
                    productStmt.setString(1, productDTO.getProdName().trim());
                    productStmt.setDouble(2, productDTO.getCostPrice());
                    productStmt.setDouble(3, productDTO.getSellPrice());
                    productStmt.setString(4, productDTO.getBrand().trim());
                    productStmt.setString(5, productDTO.getProdCode().trim());
                    productStmt.executeUpdate();
                }

                // Update stock
                String stockQuery = "UPDATE currentstock SET quantity = ? WHERE productcode = ?";
                try (PreparedStatement stockStmt = conn.prepareStatement(stockQuery)) {
                    stockStmt.setInt(1, productDTO.getQuantity());
                    stockStmt.setString(2, productDTO.getProdCode().trim());
                    stockStmt.executeUpdate();
                }

                conn.commit();
                JOptionPane.showMessageDialog(null, "Product details updated.");
                return true;

            } catch (SQLException e) {
                conn.rollback();
                throw e;
            } finally {
                conn.setAutoCommit(true);
            }

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error updating product", e);
            JOptionPane.showMessageDialog(null, "Error updating product: " + e.getMessage());
            return false;
        }
    }

    public boolean sellProductDAO(ProductDTO productDTO, String username) {
        if (!validateSaleDTO(productDTO) || !isValidString(username)) {
            JOptionPane.showMessageDialog(null, "Invalid sale data provided.");
            return false;
        }

        try (Connection conn = connection.getConnection()) {
            conn.setAutoCommit(false);

            try {
                // Check current stock
                int currentStock = getCurrentStock(conn, productDTO.getProdCode());

                if (currentStock < productDTO.getQuantity()) {
                    JOptionPane.showMessageDialog(null, "Insufficient stock for this product. Available: " + currentStock);
                    return false;
                }

                // Update stock
                String stockQuery = "UPDATE currentstock SET quantity = quantity - ? WHERE productcode = ?";
                try (PreparedStatement stockStmt = conn.prepareStatement(stockQuery)) {
                    stockStmt.setInt(1, productDTO.getQuantity());
                    stockStmt.setString(2, productDTO.getProdCode().trim());
                    stockStmt.executeUpdate();
                }

                // Insert sale record
                String salesQuery = "INSERT INTO salesinfo (date, productcode, customercode, quantity, revenue, soldby) VALUES (?, ?, ?, ?, ?, ?)";
                try (PreparedStatement salesStmt = conn.prepareStatement(salesQuery)) {
                    salesStmt.setString(1, productDTO.getDate());
                    salesStmt.setString(2, productDTO.getProdCode().trim());
                    salesStmt.setString(3, productDTO.getCustCode().trim());
                    salesStmt.setInt(4, productDTO.getQuantity());
                    salesStmt.setDouble(5, productDTO.getTotalRevenue());
                    salesStmt.setString(6, username.trim());
                    salesStmt.executeUpdate();
                }

                conn.commit();
                JOptionPane.showMessageDialog(null, "Product sold successfully.");
                return true;

            } catch (SQLException e) {
                conn.rollback();
                throw e;
            } finally {
                conn.setAutoCommit(true);
            }

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error processing sale", e);
            JOptionPane.showMessageDialog(null, "Error processing sale: " + e.getMessage());
            return false;
        }
    }

    private int getCurrentStock(Connection conn, String productCode) throws SQLException {
        String query = "SELECT quantity FROM currentstock WHERE productcode = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, productCode.trim());
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("quantity");
                }
            }
        }
        return 0;
    }

    public boolean deleteProductDAO(String code) {
        if (!isValidString(code)) {
            JOptionPane.showMessageDialog(null, "Invalid product code provided.");
            return false;
        }

        try (Connection conn = connection.getConnection()) {
            conn.setAutoCommit(false);

            try {
                // Delete from currentstock first (foreign key constraint)
                String stockQuery = "DELETE FROM currentstock WHERE productcode = ?";
                try (PreparedStatement stockStmt = conn.prepareStatement(stockQuery)) {
                    stockStmt.setString(1, code.trim());
                    stockStmt.executeUpdate();
                }

                // Delete from products
                String productQuery = "DELETE FROM products WHERE productcode = ?";
                try (PreparedStatement productStmt = conn.prepareStatement(productQuery)) {
                    productStmt.setString(1, code.trim());
                    int rowsAffected = productStmt.executeUpdate();

                    if (rowsAffected == 0) {
                        JOptionPane.showMessageDialog(null, "Product not found.");
                        return false;
                    }
                }

                conn.commit();
                JOptionPane.showMessageDialog(null, "Product has been removed.");
                return true;

            } catch (SQLException e) {
                conn.rollback();
                throw e;
            } finally {
                conn.setAutoCommit(true);
            }

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error deleting product", e);
            JOptionPane.showMessageDialog(null, "Error deleting product: " + e.getMessage());
            return false;
        }
    }

    public void editPurchaseStock(String code, int quantity) {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        try {
            String query = "SELECT * FROM currentstock WHERE productcode='" + code + "'";
            statement = conn.prepareStatement(query);
            result = statement.executeQuery(query);
            if (result.next()) {
                String query2 = "UPDATE currentstock SET quantity=quantity-? WHERE productcode=?";
                statement = conn.prepareStatement(query2);
                statement.setInt(1, quantity);
                statement.setString(2, code);
                statement.executeUpdate();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void deletePurchaseDAO(int ID) {
        Connection conn = null;
        PreparedStatement statement = null;
        try {
            String query = "DELETE FROM purchaseinfo WHERE purchaseID=?";
            statement = conn.prepareStatement(query);
            statement.setInt(1, ID);
            statement.executeUpdate();

            JOptionPane.showMessageDialog(null, "Transaction has been removed.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        deleteStock();
    }

    public void deleteStock() {
        Connection conn = null;
        PreparedStatement statement1 = null;
        PreparedStatement statement2 = null;
        try {
            String query = "DELETE FROM currentstock WHERE productcode NOT IN(SELECT productcode FROM purchaseinfo)";
            String query2 = "DELETE FROM salesinfo WHERE productcode NOT IN(SELECT productcode FROM products)";

            statement1 = conn.prepareStatement(query);
            statement2 = conn.prepareStatement(query2);

            statement1.executeUpdate(query);
            statement2.executeUpdate(query2);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
//    public ResultSet getCurrentStockInfo() {
//        String query = """
//                SELECT currentstock.productcode, products.productname,
//                currentstock.quantity, products.costprice, products.sellprice
//                FROM currentstock INNER JOIN products
//                ON currentstock.productcode = products.productcode
//                WHERE currentstock.quantity > 0
//                ORDER BY products.productname
//                """;
//        
//        try (Connection conn = connection.getConnection();
//             PreparedStatement stmt = conn.prepareStatement(query)) {
//            return stmt.executeQuery();
//        } catch (SQLException e) {
//            LOGGER.log(Level.SEVERE, "Error retrieving current stock information", e);
//            return null;
//        }
//    }

    public ResultSet getCurrentStockInfo() {
        Connection conn = null;
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        try {
            String query = """
                SELECT currentstock.productcode, products.productname,
                currentstock.quantity, products.costprice, products.sellprice
                FROM currentstock INNER JOIN products
                ON currentstock.productcode = products.productcode
                WHERE currentstock.quantity > 0
                ORDER BY products.productname
                """;
            conn = connection.getConnection();
            statement = conn.prepareStatement(query);
            resultSet = statement.executeQuery(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return resultSet;
    }

    public ResultSet getPurchaseInfo() {
        String query = """
                SELECT purchaseid, purchaseinfo.productcode, products.productname,
                purchaseinfo.quantity, purchaseinfo.totalcost
                FROM purchaseinfo INNER JOIN products
                ON products.productcode = purchaseinfo.productcode
                ORDER BY purchaseid
                """;

        try {
            Connection conn = connection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            return stmt.executeQuery();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error retrieving purchase information", e);
            return null;
        }
    }

    public ResultSet getSalesInfo() {
        String query = """
                SELECT salesid, salesinfo.productcode, products.productname,
                salesinfo.quantity, salesinfo.revenue, users.name AS sold_by
                FROM salesinfo INNER JOIN products
                ON salesinfo.productcode = products.productcode
                INNER JOIN users
                ON salesinfo.soldby = users.username
                ORDER BY salesid
                """;

        try (Connection conn = connection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            return stmt.executeQuery();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error retrieving sales information", e);
            return null;
        }
    }

    // Search methods for product
    public ResultSet getProductSearch(String searchText) {
        if (!isValidString(searchText)) {
            return null;
        }

        String query = "SELECT productcode, productname, costprice, sellprice, brand FROM products "
                + "WHERE productcode LIKE ? OR productname LIKE ? OR brand LIKE ? ORDER BY productname";

        try (Connection conn = connection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            String likeParam = "%" + searchText.trim() + "%";
            stmt.setString(1, likeParam);
            stmt.setString(2, likeParam);
            stmt.setString(3, likeParam);
            return stmt.executeQuery();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error searching products", e);
            return null;
        }
    }

    //search method for purchase
    public ResultSet getPurchaseSearch(String searchText) {
        if (!isValidString(searchText)) {
            return null;
        }
        String query = "SELECT PurchaseID,purchaseinfo.productcode,products.productname,quantity,totalcost "
                + "FROM purchaseinfo INNER JOIN products ON purchaseinfo.productcode=products.productcode "
                + "INNER JOIN suppliers ON purchaseinfo.suppliercode=suppliers.suppliercode"
                + "WHERE PurchaseID LIKE ? OR productcode LIKE ? OR productname LIKE ? "
                + "OR suppliers.fullname LIKE ? OR purchaseinfo.suppliercode LIKE ? "
                + "OR date LIKE ? ORDER BY purchaseid";
        try (Connection conn = connection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            String likeParam = "%" + searchText.trim() + "%";
            stmt.setString(1, likeParam);
            stmt.setString(2, likeParam);
            stmt.setString(3, likeParam);
            stmt.setString(4, likeParam);
            stmt.setString(5, likeParam);
            stmt.setString(6, likeParam);
            return stmt.executeQuery();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error searching products", e);
            return null;
        }
    }

    // Validation methods
    private boolean validateProductDTO(ProductDTO productDTO) {
        return productDTO != null
                && isValidString(productDTO.getProdCode())
                && isValidString(productDTO.getProdName())
                && isValidString(productDTO.getBrand())
                && isValidPositiveNumber(productDTO.getCostPrice())
                && isValidPositiveNumber(productDTO.getSellPrice())
                && isValidPositiveInteger(productDTO.getQuantity());
    }

    private boolean validatePurchaseDTO(ProductDTO productDTO) {
        return productDTO != null
                && isValidString(productDTO.getSuppCode())
                && isValidString(productDTO.getProdCode())
                && isValidString(productDTO.getDate())
                && isValidPositiveInteger(productDTO.getQuantity())
                && isValidPositiveNumber(productDTO.getTotalCost());
    }

    private boolean validateSaleDTO(ProductDTO productDTO) {
        return productDTO != null
                && isValidString(productDTO.getProdCode())
                && isValidString(productDTO.getCustCode())
                && isValidString(productDTO.getDate())
                && isValidPositiveInteger(productDTO.getQuantity())
                && isValidPositiveNumber(productDTO.getTotalRevenue());
    }

    // Table model builder with proper resource management
    public DefaultTableModel buildTableModel(ResultSet resultSet) {
        if (resultSet == null) {
            return new DefaultTableModel();
        }

        try {
            ResultSetMetaData metaData = resultSet.getMetaData();
            Vector<String> columnNames = new Vector<>();
            int colCount = metaData.getColumnCount();

            for (int col = 1; col <= colCount; col++) {
                columnNames.add(metaData.getColumnName(col).toUpperCase(Locale.ROOT));
            }

            Vector<Vector<Object>> data = new Vector<>();
            while (resultSet.next()) {
                Vector<Object> vector = new Vector<>();
                for (int col = 1; col <= colCount; col++) {
                    vector.add(resultSet.getObject(col));
                }
                data.add(vector);
            }

            return new DefaultTableModel(data, columnNames);

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error building table model", e);
            return new DefaultTableModel();
        }
    }
}
