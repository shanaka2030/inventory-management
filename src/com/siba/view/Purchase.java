/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.siba.view;

import com.siba.DAO.ProductDAO;
import com.siba.DAO.SupplierDAO;
import com.siba.DTO.ProductDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author shana
 */
public class Purchase extends javax.swing.JPanel {

    ProductDTO productDTO;
    int quantity;
    String prodCode = null;
    /**
     * Creates new form Product
     */
    public Purchase() {
        initComponents();
        loadComboBox();
        loadDataSet();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablePurchase = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        cmbSelectSupplier = new javax.swing.JComboBox<>();
        btnNewSupplier = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        btnPurchase = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        txtProductCode = new javax.swing.JTextField();
        txtProductName = new javax.swing.JTextField();
        txtDate = new com.toedter.calendar.JDateChooser();
        txtQuantity = new javax.swing.JTextField();
        txtCostPrice = new javax.swing.JTextField();
        txtSellPrice = new javax.swing.JTextField();
        txtBrand = new javax.swing.JTextField();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Impact", 1, 36)); // NOI18N
        jLabel1.setText("Purchase");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(31, 22, -1, -1));

        jLabel2.setText("Search");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(522, 46, -1, -1));
        add(txtSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(569, 43, 148, -1));

        jButton1.setText("Refresh");
        add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(723, 43, -1, -1));

        tablePurchase.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tablePurchase.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablePurchaseMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablePurchase);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(37, 85, 459, 438));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Purchase Product"));

        cmbSelectSupplier.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        cmbSelectSupplier.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select a Supplier" }));

        btnNewSupplier.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnNewSupplier.setText("Click to add a New Supplier");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Product Code:");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("Product Name:");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("Date:");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("Quantity:");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setText("Cost Price:");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setText("Selling Price:");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setText("Brand:");

        btnPurchase.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnPurchase.setText("Purchase");
        btnPurchase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPurchaseActionPerformed(evt);
            }
        });

        btnDelete.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnClear.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnClear.setText("Clear");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        txtDate.setForeground(new java.awt.Color(102, 102, 102));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnPurchase, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnDelete, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE))
                    .addComponent(btnClear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmbSelectSupplier, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnNewSupplier, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtProductCode)
                            .addComponent(txtProductName)
                            .addComponent(txtDate, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
                            .addComponent(txtQuantity)
                            .addComponent(txtCostPrice)
                            .addComponent(txtSellPrice)
                            .addComponent(txtBrand))))
                .addContainerGap(13, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(cmbSelectSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btnNewSupplier)
                                                .addGap(18, 18, 18)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(jLabel3)
                                                    .addComponent(txtProductCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel4))
                                            .addComponent(txtProductName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel5)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel6))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(13, 13, 13)
                                        .addComponent(txtQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel7))
                            .addComponent(txtCostPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel8))
                    .addComponent(txtSellPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel9)
                    .addComponent(txtBrand, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPurchase)
                    .addComponent(btnDelete))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnClear)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(508, 85, -1, -1));
        jPanel1.getAccessibleContext().setAccessibleName("");
    }// </editor-fold>//GEN-END:initComponents

    private void btnPurchaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPurchaseActionPerformed
        productDTO = new ProductDTO();
        if (txtProductCode.getText().equals("") || txtDate.getDate()==null
                || txtQuantity.getText().equals(""))
            JOptionPane.showMessageDialog(null, "Please enter all the required details.");
        else {
            productDTO.setSuppCode(new ProductDAO().getSuppCode(cmbSelectSupplier.getSelectedItem().toString()));
            productDTO.setProdCode(txtProductCode.getText());
            try {
                ResultSet resultSet = new ProductDAO().getProdName(txtProductCode.getText());
                if (resultSet.next()) {
                    //productDTO.setProdName(nameText.getText());
                    productDTO.setDate(txtDate.getDate().toString());
                    productDTO.setQuantity(Integer.parseInt(txtQuantity.getText()));
                    Double costPrice = Double.parseDouble(txtCostPrice.getText());
                    Double totalCost = costPrice * Integer.parseInt(txtQuantity.getText());
                    productDTO.setTotalCost(totalCost);

                    new ProductDAO().addPurchaseDAO(productDTO);
                    loadDataSet();
                } else
                    JOptionPane.showMessageDialog(null, "This seems to be a new product" +
                            " that hasn't been added yet.\nPlease add this product in the \"Products\" section before proceeding.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_btnPurchaseActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        if (tablePurchase.getSelectedRow()<0)
            JOptionPane.showMessageDialog(null, "Please select a transaction from the table.");
        else {
            int opt = JOptionPane.showConfirmDialog(
                    null,
                    "Are you sure you want to delete this purchase?",
                    "Confirmation",
                    JOptionPane.YES_NO_OPTION);
            if(opt==JOptionPane.YES_OPTION) {
                new ProductDAO().deletePurchaseDAO((int) tablePurchase.getValueAt(tablePurchase.getSelectedRow(),0));
                new ProductDAO().editPurchaseStock(prodCode, quantity);
                loadDataSet();
            }
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void tablePurchaseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablePurchaseMouseClicked
        int row = tablePurchase.getSelectedRow();
        int col = tablePurchase.getColumnCount();

        Object[] data = new Object[col];
        for (int i=0; i<col; i++)
            data[i] = tablePurchase.getValueAt(row, i);
        
        quantity = Integer.parseInt(data[3].toString());
        prodCode = data[1].toString();
    }//GEN-LAST:event_tablePurchaseMouseClicked

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        txtProductCode.setText("");
        txtProductName.setText("");
        txtDate.setDate(null);
        txtQuantity.setText("");
        txtCostPrice.setText("");
        txtSellPrice.setText("");
        txtBrand.setText("");
        txtSearch.setText("");
    }//GEN-LAST:event_btnClearActionPerformed

    // Method to load and update combo box containing supplier names
    public void loadComboBox() {
        try {
            SupplierDAO supplierDAO = new SupplierDAO();
            cmbSelectSupplier.setModel(supplierDAO.buildComboBoxModel(supplierDAO.getAllSuppliers()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to load data into table
    public void loadDataSet() {
        ProductDAO productDAO = new ProductDAO();
        tablePurchase.setModel(productDAO.buildTableModel(productDAO.getPurchaseInfo()));
    }

    // Method to display search result in table
    public void loadSearchData(String text) {
        ProductDAO productDAO = new ProductDAO();
        tablePurchase.setModel(productDAO.buildTableModel(productDAO.getPurchaseSearch(text)));
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnNewSupplier;
    private javax.swing.JButton btnPurchase;
    private javax.swing.JComboBox<String> cmbSelectSupplier;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablePurchase;
    private javax.swing.JTextField txtBrand;
    private javax.swing.JTextField txtCostPrice;
    private com.toedter.calendar.JDateChooser txtDate;
    private javax.swing.JTextField txtProductCode;
    private javax.swing.JTextField txtProductName;
    private javax.swing.JTextField txtQuantity;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtSellPrice;
    // End of variables declaration//GEN-END:variables
}
