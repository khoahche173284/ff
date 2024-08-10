<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order Details</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #e8f5e9;
                margin: 0;
                padding: 20px;
                box-sizing: border-box;
            }
            .header {
                display: flex;
                justify-content: space-between;
                align-items: center;
                margin-bottom: 20px;
            }
            .back-button {
                padding: 10px 20px;
                background-color: #4caf50;
                color: #ffffff;
                border: none;
                border-radius: 4px;
                cursor: pointer;
                text-decoration: none;
            }
            .back-button:hover {
                background-color: #388e3c;
            }
            form {
                background-color: #ffffff;
                padding: 20px;
                margin-bottom: 20px;
                border-radius: 8px;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            }
            input[type="text"], input[type="date"] {
                padding: 10px;
                margin: 5px 0;
                border: 1px solid #ced4da;
                border-radius: 4px;
                width: calc(100% - 22px);
            }
            input[type="submit"] {
                padding: 10px 20px;
                margin: 10px 0;
                background-color: #4caf50;
                color: #ffffff;
                border: none;
                border-radius: 4px;
                cursor: pointer;
            }
            input[type="submit"]:hover {
                background-color: #388e3c;
            }
            table {
                width: 100%;
                border-collapse: collapse;
                margin-top: 20px;
                background-color: #ffffff;
                border-radius: 8px;
                overflow: hidden;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            }
            th, td {
                padding: 12px;
                text-align: left;
                border-bottom: 1px solid #ddd;
            }
            th {
                background-color: #4caf50;
                color: #ffffff;
            }
            tr:hover {
                background-color: #f1f1f1;
            }
            .date-range {
                display: flex;
                flex-direction: column;
                align-items: flex-start;
            }
            .date-range input[type="date"] {
                width: calc(100% - 22px);
            }
        </style>
    </head>
    <body>
        <div class="header">
            <a href="home.jsp" class="back-button">Back to Home</a>
        </div>

        <table>
            <thead>
                <tr>
                    <th>Order Date</th>
                    <th>Gmail</th>
                    <th>Status</th>
                    <th>Amount</th>
                    <th>Transaction Status</th>
                    <th>vnp_TxnRef</th>
                    <th>vnp_OrderInfo</th>
                    <th>vnp_ResponseCode</th>
                    <th>vnp_TransactionNo</th>
                    <th>vnp_BankCode</th>
                    <th>vnp_PayDate</th>
                    <th>Effective Time</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${list}" var="order">
                    <tr>
                        <td><c:out value="${order.orderDate}" /></td>
                        <td><c:out value="${order.email}" /></td>
                        <c:if test="${order.checked == true}">
                            <td>Processing</td>
                        </c:if>
                        <c:if test="${order.checked == false}">
                            <td>Done</td>
                        </c:if>
                        <td><c:out value="${order.amount}" /></td>
                        <td><c:out value="${order.transactionStatus}" /></td>
                        <td><c:out value="${order.vnpTxnRef}" /></td>
                        <td><c:out value="${order.vnpOrderInfo}" /></td>
                        <td><c:out value="${order.vnpResponseCode}" /></td>
                        <td><c:out value="${order.vnpTransactionNo}" /></td>
                        <td><c:out value="${order.vnpBankCode}" /></td>
                        <td><c:out value="${order.vnpPayDate}" /></td>
                        <td><c:out value="${order.expirationDate}" /></td> <!-- Đổi từ ExpirationDate thành expirationDate -->
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </body>
</html>
