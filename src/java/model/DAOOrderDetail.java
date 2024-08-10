package model;

import entity.OrderDetail;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Data Access Object for OrderDetail
 */
public class DAOOrderDetail extends DBConnect {

    public ArrayList<OrderDetail> listAllOrder() {
        ArrayList<OrderDetail> list = new ArrayList<>();
        String sql = "SELECT OrderDetail.ID, OrderDate, UserID, OrderDetail.Email, ProductID, Checked, Amount, vnp_TxnRef, vnp_OrderInfo, vnp_ResponseCode, vnp_TransactionNo, vnp_BankCode, vnp_PayDate, TransactionStatus, ExpirationDate FROM OrderDetail LEFT JOIN Users ON OrderDetail.UserID = Users.ID";

        try (PreparedStatement st = conn.prepareStatement(sql); ResultSet rs = st.executeQuery()) {

            while (rs.next()) {
                Date orderDate = rs.getDate("OrderDate");
                int userId = rs.getInt("UserID");
                String email = rs.getString("Email");
                int productId = rs.getInt("ProductID");
                boolean checked = rs.getBoolean("Checked");
                double amount = rs.getDouble("Amount");
                String vnpTxnRef = rs.getString("vnp_TxnRef");
                String vnpOrderInfo = rs.getString("vnp_OrderInfo");
                String vnpResponseCode = rs.getString("vnp_ResponseCode");
                String vnpTransactionNo = rs.getString("vnp_TransactionNo");
                String vnpBankCode = rs.getString("vnp_BankCode");
                Date vnpPayDate = rs.getTimestamp("vnp_PayDate");
                Date expirationDate = rs.getDate("ExpirationDate");

                OrderDetail orderDetail = new OrderDetail(orderDate, userId, email, productId, checked, amount, vnpTxnRef, vnpOrderInfo, vnpResponseCode, vnpTransactionNo, vnpBankCode, vnpPayDate, "Thành Công", expirationDate);
                list.add(orderDetail);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DAOOrderDetail.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public ArrayList<OrderDetail> searchByGmail(String gmail) {
        ArrayList<OrderDetail> list = new ArrayList<>();
        String sql = "SELECT OrderDetail.ID, OrderDate, UserID, OrderDetail.Email, ProductID, Checked, Amount, vnp_TxnRef, vnp_OrderInfo, vnp_ResponseCode, vnp_TransactionNo, vnp_BankCode, vnp_PayDate, TransactionStatus FROM OrderDetail LEFT JOIN Users ON OrderDetail.UserID = Users.ID WHERE Email = ?";

        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, gmail);
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    Date orderDate = rs.getDate("OrderDate");
                    int userId = rs.getInt("UserID");
                    String email = rs.getString("Email");
                    int productId = rs.getInt("ProductID");
                    boolean checked = rs.getBoolean("Checked");
                    double amount = rs.getDouble("Amount");
                    String vnpTxnRef = rs.getString("vnp_TxnRef");
                    String vnpOrderInfo = rs.getString("vnp_OrderInfo");
                    String vnpResponseCode = rs.getString("vnp_ResponseCode");
                    String vnpTransactionNo = rs.getString("vnp_TransactionNo");
                    String vnpBankCode = rs.getString("vnp_BankCode");
                    Date vnpPayDate = rs.getTimestamp("vnp_PayDate");
                    String transactionStatus = rs.getString("TransactionStatus");
                    Date expirationDate = rs.getDate("ExpirationDate");

                    OrderDetail orderDetail = new OrderDetail(orderDate, userId, email, productId, checked, amount, vnpTxnRef, vnpOrderInfo, vnpResponseCode, vnpTransactionNo, vnpBankCode, vnpPayDate, transactionStatus, expirationDate);
                    list.add(orderDetail);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOOrderDetail.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public ArrayList<OrderDetail> findOrderByDateRange(LocalDate date1, LocalDate date2) {
        ArrayList<OrderDetail> list = new ArrayList<>();
        String sql = "SELECT OrderDetail.ID, OrderDate, UserID, Email, ProductID, Checked, Amount, vnp_TxnRef, vnp_OrderInfo, vnp_ResponseCode, vnp_TransactionNo, vnp_BankCode, vnp_PayDate, TransactionStatus FROM OrderDetail LEFT JOIN Users ON OrderDetail.UserID = Users.ID WHERE CAST(OrderDate AS DATE) BETWEEN ? AND ?";

        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setDate(1, java.sql.Date.valueOf(date1));
            st.setDate(2, java.sql.Date.valueOf(date2));
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    Date orderDate = rs.getDate("OrderDate");
                    int userId = rs.getInt("UserID");
                    String email = rs.getString("Email");
                    int productId = rs.getInt("ProductID");
                    boolean checked = rs.getBoolean("Checked");
                    double amount = rs.getDouble("Amount");
                    String vnpTxnRef = rs.getString("vnp_TxnRef");
                    String vnpOrderInfo = rs.getString("vnp_OrderInfo");
                    String vnpResponseCode = rs.getString("vnp_ResponseCode");
                    String vnpTransactionNo = rs.getString("vnp_TransactionNo");
                    String vnpBankCode = rs.getString("vnp_BankCode");
                    Date vnpPayDate = rs.getTimestamp("vnp_PayDate");
                    String transactionStatus = rs.getString("TransactionStatus");
                    Date expirationDate = rs.getDate("ExpirationDate");

                    OrderDetail orderDetail = new OrderDetail(orderDate, userId, email, productId, checked, amount, vnpTxnRef, vnpOrderInfo, vnpResponseCode, vnpTransactionNo, vnpBankCode, vnpPayDate, transactionStatus, expirationDate);
                    list.add(orderDetail);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOOrderDetail.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public boolean createOrderDetail(OrderDetail orderDetail) {
        String sql = "INSERT INTO OrderDetail (OrderDate, UserID, Email, ProductID, Checked, Amount, TransactionStatus, vnp_TxnRef, vnp_OrderInfo, vnp_ResponseCode, vnp_TransactionNo, vnp_BankCode, vnp_PayDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setTimestamp(1, new Timestamp(orderDetail.getOrderDate().getTime()));
            stmt.setInt(2, orderDetail.getUserID());
            stmt.setString(3, orderDetail.getEmail());
            stmt.setInt(4, orderDetail.getProductID());
            stmt.setBoolean(5, orderDetail.isChecked());
            stmt.setDouble(6, orderDetail.getAmount());
            stmt.setString(7, orderDetail.getTransactionStatus());
            stmt.setString(8, orderDetail.getVnpTxnRef());
            stmt.setString(9, orderDetail.getVnpOrderInfo());
            stmt.setString(10, orderDetail.getVnpResponseCode());
            stmt.setString(11, orderDetail.getVnpTransactionNo());
            stmt.setString(12, orderDetail.getVnpBankCode());
            stmt.setTimestamp(13, new Timestamp(orderDetail.getVnpPayDate().getTime()));
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public ArrayList<OrderDetail> findOrderByDate(LocalDate date1) {
        ArrayList<OrderDetail> list = new ArrayList<>();
        String sql = "SELECT OrderDetail.ID, OrderDetail.OrderDate, OrderDetail.UserID, OrderDetail.ProductID, OrderDetail.Email, OrderDetail.Checked, OrderDetail.Amount, OrderDetail.TransactionStatus, OrderDetail.vnp_TxnRef, OrderDetail.vnp_OrderInfo, OrderDetail.vnp_ResponseCode, OrderDetail.vnp_TransactionNo, OrderDetail.vnp_BankCode, OrderDetail.vnp_PayDate, OrderDetail.ExpirationDate FROM OrderDetail LEFT JOIN Users ON OrderDetail.UserID = Users.ID WHERE CAST(OrderDetail.OrderDate AS DATE) = ?";

        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setDate(1, java.sql.Date.valueOf(date1));

            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    Date orderDate = rs.getDate("OrderDate");
                    int userId = rs.getInt("UserID");
                    String email = rs.getString("Email");
                    int productId = rs.getInt("ProductID");
                    boolean checked = rs.getBoolean("Checked");
                    double amount = rs.getDouble("Amount");
                    String transactionStatus = rs.getString("TransactionStatus");
                    String vnpTxnRef = rs.getString("vnp_TxnRef");
                    String vnpOrderInfo = rs.getString("vnp_OrderInfo");
                    String vnpResponseCode = rs.getString("vnp_ResponseCode");
                    String vnpTransactionNo = rs.getString("vnp_TransactionNo");
                    String vnpBankCode = rs.getString("vnp_BankCode");
                    Date vnpPayDate = rs.getTimestamp("vnp_PayDate");
                    Date expirationDate = rs.getDate("ExpirationDate");

                    OrderDetail orderDetail = new OrderDetail(orderDate, userId, email, productId, checked, amount, vnpTxnRef, vnpOrderInfo, vnpResponseCode, vnpTransactionNo, vnpBankCode, vnpPayDate, transactionStatus, expirationDate);
                    list.add(orderDetail);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOOrderDetail.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public void updateUserAndOrder(int userId, Date payDate, int productId) throws SQLException {
        conn.setAutoCommit(false); // Begin transaction
        try {
            // Get the Duration from Premium_Package
            String getDurationSql = "SELECT Duration FROM Premium_Package WHERE ID = ?";
            int duration = 0;
            try (PreparedStatement psGetDuration = conn.prepareStatement(getDurationSql)) {
                psGetDuration.setInt(1, productId);
                try (ResultSet rs = psGetDuration.executeQuery()) {
                    if (rs.next()) {
                        duration = rs.getInt("Duration");
                    }
                }
            }

            // Calculate ExpirationDate
            LocalDateTime expirationDateTime = new Timestamp(payDate.getTime()).toLocalDateTime().plus(duration, ChronoUnit.MONTHS);
            Timestamp expirationDate = Timestamp.valueOf(expirationDateTime);

            // Update user role
            String updateUserRoleSql = "UPDATE Users SET RoleID = 3 WHERE ID = ?";
            try (PreparedStatement psUpdateUserRole = conn.prepareStatement(updateUserRoleSql)) {
                psUpdateUserRole.setInt(1, userId);
                psUpdateUserRole.executeUpdate();
            }

            // Update order status and ExpirationDate
            String updateOrderSql = "UPDATE OrderDetail SET Checked = 0, ExpirationDate = ? WHERE UserID = ? AND vnp_ResponseCode = '00'";
            try (PreparedStatement psUpdateOrder = conn.prepareStatement(updateOrderSql)) {
                psUpdateOrder.setTimestamp(1, expirationDate);
                psUpdateOrder.setInt(2, userId);
                psUpdateOrder.executeUpdate();
            }

            conn.commit(); // Commit transaction
        } catch (SQLException e) {
            conn.rollback(); // Rollback transaction on error
            throw e;
        } finally {
            conn.setAutoCommit(true); // Reset to default
        }
    }

    public ArrayList<OrderDetail> listOrdersByUserId(int userId) {
        ArrayList<OrderDetail> list = new ArrayList<>();
        try {
            String query = "SELECT * FROM OrderDetail WHERE UserID = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setOrderDate(rs.getTimestamp("OrderDate"));
                orderDetail.setUserID(rs.getInt("UserID"));
                orderDetail.setProductID(rs.getInt("ProductID"));
                orderDetail.setEmail(rs.getString("Email"));
                orderDetail.setChecked(rs.getBoolean("Checked"));
                orderDetail.setAmount(rs.getDouble("Amount"));
                orderDetail.setTransactionStatus(rs.getString("TransactionStatus"));
                orderDetail.setVnpTxnRef(rs.getString("vnp_TxnRef"));
                orderDetail.setVnpOrderInfo(rs.getString("vnp_OrderInfo"));
                orderDetail.setVnpResponseCode(rs.getString("vnp_ResponseCode"));
                orderDetail.setVnpTransactionNo(rs.getString("vnp_TransactionNo"));
                orderDetail.setVnpBankCode(rs.getString("vnp_BankCode"));
                orderDetail.setVnpPayDate(rs.getTimestamp("vnp_PayDate"));
                orderDetail.setExpirationDate(rs.getDate("ExpirationDate"));
                list.add(orderDetail);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public OrderDetail getOrderDetailByUserID(int userID) {
        OrderDetail orderDetail = null;
        String sql = "SELECT OrderDate, UserID, Email, ProductID, Checked, Amount, vnp_TxnRef, vnp_OrderInfo, vnp_ResponseCode, vnp_TransactionNo, vnp_BankCode, vnp_PayDate, TransactionStatus, ExpirationDate FROM OrderDetail WHERE UserID = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userID);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Date orderDate = rs.getDate("OrderDate");
                    String email = rs.getString("Email");
                    int productId = rs.getInt("ProductID");
                    boolean checked = rs.getBoolean("Checked");
                    double amount = rs.getDouble("Amount");
                    String vnpTxnRef = rs.getString("vnp_TxnRef");
                    String vnpOrderInfo = rs.getString("vnp_OrderInfo");
                    String vnpResponseCode = rs.getString("vnp_ResponseCode");
                    String vnpTransactionNo = rs.getString("vnp_TransactionNo");
                    String vnpBankCode = rs.getString("vnp_BankCode");
                    Date vnpPayDate = rs.getTimestamp("vnp_PayDate");
                    String transactionStatus = rs.getString("TransactionStatus");
                    Date expirationDate = rs.getDate("ExpirationDate");

                    orderDetail = new OrderDetail(orderDate, userID, email, productId, checked, amount, vnpTxnRef, vnpOrderInfo, vnpResponseCode, vnpTransactionNo, vnpBankCode, vnpPayDate, transactionStatus, expirationDate);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOOrderDetail.class.getName()).log(Level.SEVERE, null, ex);
        }
        return orderDetail;
    }

    public int getProductDurationByID(int productID) {
        int duration = 0;
        String sql = "SELECT Duration FROM Premium_Package WHERE ID = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, productID);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    duration = rs.getInt("Duration"); // Duration là số tháng của sản phẩm
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOOrderDetail.class.getName()).log(Level.SEVERE, null, ex);
        }

        return duration;
    }

    public void updateExpirationDate(int userID, LocalDate expirationDate) {
        String sql = "UPDATE OrderDetail SET ExpirationDate = ? WHERE UserID = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDate(1, java.sql.Date.valueOf(expirationDate));
            ps.setInt(2, userID);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOOrderDetail.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateExpiredOrders() {
        String sqlSelect = "SELECT UserID FROM OrderDetail WHERE ExpirationDate < GETDATE() AND Checked = 0";
        String sqlUpdate = "UPDATE Users SET RoleID = 2 WHERE ID = ?";

        try (PreparedStatement psSelect = conn.prepareStatement(sqlSelect); ResultSet rs = psSelect.executeQuery(); PreparedStatement psUpdate = conn.prepareStatement(sqlUpdate)) {

            while (rs.next()) {
                int userID = rs.getInt("UserID");
                psUpdate.setInt(1, userID);
                psUpdate.executeUpdate();
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOOrderDetail.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateExpiredOrdersAndUserRoles() {
        String sqlSelect = "SELECT UserID FROM OrderDetail WHERE ExpirationDate < GETDATE() AND Checked = 0";
        String sqlUpdateOrder = "UPDATE OrderDetail SET Checked = 1 WHERE UserID = ?";
        String sqlUpdateUser = "UPDATE Users SET RoleID = 2 WHERE ID = ?";

        try (PreparedStatement psSelect = conn.prepareStatement(sqlSelect); ResultSet rs = psSelect.executeQuery(); PreparedStatement psUpdateOrder = conn.prepareStatement(sqlUpdateOrder); PreparedStatement psUpdateUser = conn.prepareStatement(sqlUpdateUser)) {

            while (rs.next()) {
                int userID = rs.getInt("UserID");

                // Cập nhật trạng thái đơn hàng
                psUpdateOrder.setInt(1, userID);
                psUpdateOrder.executeUpdate();

                // Cập nhật vai trò người dùng
                psUpdateUser.setInt(1, userID);
                psUpdateUser.executeUpdate();

                System.out.println("Updated user ID: " + userID); // Log userID
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOOrderDetail.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace(); // Thêm dòng này để in lỗi ra console
        }
    }
}
