/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.Product;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Quang
 */
public class DAOPremium_Package extends DBConnect {

    public ArrayList<Product> getAll(String sql) {
        ArrayList<Product> vector = new ArrayList<Product>();
        try {
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            System.out.println("Total columns: " + columnCount); // Debug: In ra tổng số cột

            while (rs.next()) {
                int id = rs.getInt(1);
                String productName = rs.getString(2);
                String price = rs.getString(3);
                String description = rs.getString(4);
                String duration = rs.getString(5);

                // Kiểm tra nếu cột thứ 5 tồn tại trước khi lấy giá trị
                List<String> features = new ArrayList<>();
                if (columnCount >= 6) {
                    String featuresStr = rs.getString(6);
                    features = Arrays.asList(featuresStr.split(","));
                }

                Product product = new Product();
                product.setId(id);
                product.setProductName(productName);
                product.setDescription(description);
                product.setPrice(price);
                product.setFeatures(features);

                vector.add(product);

            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOPremium_Package.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public Product getProductById(int id) {
        Product product = null;
        try {
            String sql = "SELECT * FROM Premium_Package WHERE id = " + id;
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            if (rs.next()) {
                String productName = rs.getString("productName");
                String description = rs.getString("description");
                String price = rs.getString("price");
                String duration = rs.getString("duration");
                product = new Product(id, productName, price, description, duration);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOPremium_Package.class.getName()).log(Level.SEVERE, null, ex);
        }
        return product;
    }

    public int addProduct(Product product) {
        int result = 0;
        try {
            Statement statement = conn.createStatement();
            String sql = "INSERT INTO Premium_Package (productName, price, description, duration) VALUES ('"
                    + product.getProductName() + "', "
                    + product.getPrice() + ", '"
                    + product.getDescription() + "', '"
                    + product.getDuration() + "')";
            result = statement.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOPremium_Package.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public int deleteProduct(int id) {
        int result = 0;
        try {
            Statement statement = conn.createStatement();
            String sql = "DELETE FROM Premium_Package WHERE id = " + id;
            result = statement.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOPremium_Package.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public int updateProduct(Product product) {
        int result = 0;
        try {
            Statement statement = conn.createStatement();
            String sql = "UPDATE Premium_Package SET productName = '" + product.getProductName()
                    + "', price = " + product.getPrice()
                    + ", description = '" + product.getDescription()
                    + "', duration = '" + product.getDuration()
                    + "' WHERE id = " + product.getId();
            result = statement.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOPremium_Package.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public boolean isProductNameExist(String productName, int excludeProductId) {
        boolean exists = false;
        try {
            String sql = "SELECT COUNT(*) FROM Premium_Package WHERE productName = '" + productName + "' AND id != " + excludeProductId;
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            if (rs.next()) {
                exists = rs.getInt(1) > 0;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOPremium_Package.class.getName()).log(Level.SEVERE, null, ex);
        }
        return exists;
    }

    public boolean isProductNameExist(String productName) {
        boolean exists = false;
        try {
            String sql = "SELECT COUNT(*) FROM Premium_Package WHERE productName = '" + productName + "'";
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            if (rs.next()) {
                exists = rs.getInt(1) > 0;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOPremium_Package.class.getName()).log(Level.SEVERE, null, ex);
        }
        return exists;
    }

    public int getProductCount() {
        int count = 0;
        try {
            String sql = "SELECT COUNT(*) FROM Premium_Package";
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOPremium_Package.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }

    public static void main(String[] args) {
        DAOPremium_Package daoProduct = new DAOPremium_Package();

        // Gọi phương thức getAll với truy vấn SQL phù hợp
        int productId = 1; // ID của sản phẩm cần tìm
        Product product = daoProduct.getProductById(productId);
        if (product != null) {
            System.out.println("Sản phẩm tìm thấy:");
            System.out.println("ID: " + product.getId());
            System.out.println("Product Name: " + product.getProductName());
            System.out.println("Price: " + product.getPrice());
            System.out.println("Description: " + product.getDescription());
        } else {
            System.out.println("Không tìm thấy sản phẩm với ID " + productId);
        }
    }
}
