package entity;

import java.util.Date;

public class OrderDetail {

    private Date orderDate;
    private int userID;
    private String email;
    private int productID;
    private boolean checked;
    private double amount;
    private String vnpTxnRef;
    private String vnpOrderInfo;
    private String vnpResponseCode;
    private String vnpTransactionNo;
    private String vnpBankCode;
    private Date vnpPayDate;
    private String transactionStatus;
    private Date expirationDate; // Đổi từ ExpirationDate thành expirationDate

    public OrderDetail() {
    }

    public OrderDetail(
            Date orderDate,
            int userID,
            String email,
            int productID,
            boolean checked,
            double amount,
            String vnpTxnRef,
            String vnpOrderInfo,
            String vnpResponseCode,
            String vnpTransactionNo,
            String vnpBankCode,
            Date vnpPayDate,
            String transactionStatus
    ) {
        this.orderDate = orderDate;
        this.userID = userID;
        this.email = email;
        this.productID = productID;
        this.checked = checked;
        this.amount = amount;
        this.vnpTxnRef = vnpTxnRef;
        this.vnpOrderInfo = vnpOrderInfo;
        this.vnpResponseCode = vnpResponseCode;
        this.vnpTransactionNo = vnpTransactionNo;
        this.vnpBankCode = vnpBankCode;
        this.vnpPayDate = vnpPayDate;
        this.transactionStatus = transactionStatus;
    }
    
    public OrderDetail(
            Date orderDate,
            int userID,
            String email,
            int productID,
            boolean checked,
            double amount,
            String vnpTxnRef,
            String vnpOrderInfo,
            String vnpResponseCode,
            String vnpTransactionNo,
            String vnpBankCode,
            Date vnpPayDate,
            String transactionStatus,
            Date expirationDate // Đổi từ ExpirationDate thành expirationDate
    ) {
        this.orderDate = orderDate;
        this.userID = userID;
        this.email = email;
        this.productID = productID;
        this.checked = checked;
        this.amount = amount;
        this.vnpTxnRef = vnpTxnRef;
        this.vnpOrderInfo = vnpOrderInfo;
        this.vnpResponseCode = vnpResponseCode;
        this.vnpTransactionNo = vnpTransactionNo;
        this.vnpBankCode = vnpBankCode;
        this.vnpPayDate = vnpPayDate;
        this.transactionStatus = transactionStatus;
        this.expirationDate = expirationDate; // Đổi từ ExpirationDate thành expirationDate
    }

    // Các phương thức getter và setter
    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getVnpTxnRef() {
        return vnpTxnRef;
    }

    public void setVnpTxnRef(String vnpTxnRef) {
        this.vnpTxnRef = vnpTxnRef;
    }

    public String getVnpOrderInfo() {
        return vnpOrderInfo;
    }

    public void setVnpOrderInfo(String vnpOrderInfo) {
        this.vnpOrderInfo = vnpOrderInfo;
    }

    public String getVnpResponseCode() {
        return vnpResponseCode;
    }

    public void setVnpResponseCode(String vnpResponseCode) {
        this.vnpResponseCode = vnpResponseCode;
    }

    public String getVnpTransactionNo() {
        return vnpTransactionNo;
    }

    public void setVnpTransactionNo(String vnpTransactionNo) {
        this.vnpTransactionNo = vnpTransactionNo;
    }

    public String getVnpBankCode() {
        return vnpBankCode;
    }

    public void setVnpBankCode(String vnpBankCode) {
        this.vnpBankCode = vnpBankCode;
    }

    public Date getVnpPayDate() {
        return vnpPayDate;
    }

    public void setVnpPayDate(Date vnpPayDate) {
        this.vnpPayDate = vnpPayDate;
    }

    public String getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(String transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public Date getExpirationDate() {
        return expirationDate; // Đổi từ getExpirationDate thành getExpirationDate
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate; // Đổi từ setExpirationDate thành setExpirationDate
    }

    @Override
    public String toString() {
        return "OrderDetail{" + "orderDate=" + orderDate + ", userID=" + userID + ", email=" + email + ", productID=" + productID + ", checked=" + checked + ", amount=" + amount + ", vnpTxnRef=" + vnpTxnRef + ", vnpOrderInfo=" + vnpOrderInfo + ", vnpResponseCode=" + vnpResponseCode + ", vnpTransactionNo=" + vnpTransactionNo + ", vnpBankCode=" + vnpBankCode + ", vnpPayDate=" + vnpPayDate + ", transactionStatus=" + transactionStatus + ", expirationDate=" + expirationDate + '}';
    }
}
