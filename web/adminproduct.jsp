<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Admin Product Management</title>
        <link rel="stylesheet" href="css/admin.css">
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f8f9fa;
                margin: 0;
                padding: 20px;
                box-sizing: border-box;
                display: flex;
                flex-direction: column;
                align-items: center;
            }
            .container {
                background-color: #ffffff;
                padding: 20px;
                border-radius: 8px;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
                max-width: 1200px;
                width: 100%;
                margin: auto;
            }
            .header {
                display: flex;
                justify-content: space-between;
                align-items: center;
                margin-bottom: 20px;
            }
            .header h2 {
                margin: 0;
                font-size: 24px;
                color: #ffffff;
                background-color: #4caf50;
                padding: 10px 20px;
                border-radius: 4px;
                cursor: pointer;
                transition: background-color 0.3s;
            }
            .header h2:hover {
                background-color: #388e3c;
            }
            .admin-name {
                color: #4caf50;
                margin-left: 10px;
                font-size: 18px;
            }
            .search-form {
                display: flex;
                justify-content: center;
                margin-bottom: 20px;
                width: 100%;
            }
            .search-input {
                padding: 10px;
                font-size: 14px;
                width: calc(100% - 120px);
                border: 1px solid #ced4da;
                border-radius: 4px 0 0 4px;
            }
            .search-button {
                padding: 10px 20px;
                background-color: #4caf50;
                color: #ffffff;
                border: none;
                border-radius: 0 4px 4px 0;
                cursor: pointer;
                text-decoration: none;
                font-size: 14px;
                transition: background-color 0.3s;
            }
            .search-button:hover {
                background-color: #388e3c;
            }
            .sort-buttons {
                display: flex;
                justify-content: center;
                gap: 10px;
                margin-bottom: 20px;
            }
            .sort-button {
                padding: 10px 20px;
                background-color: #4caf50;
                color: #ffffff;
                border: none;
                border-radius: 4px;
                cursor: pointer;
                text-decoration: none;
                font-size: 14px;
                transition: background-color 0.3s;
            }
            .sort-button:hover {
                background-color: #388e3c;
            }
            .button {
                padding: 10px 20px;
                background-color: #4caf50;
                color: #ffffff;
                border: none;
                border-radius: 4px;
                cursor: pointer;
                text-decoration: none;
                font-size: 14px;
                transition: background-color 0.3s;
            }
            .button:hover {
                background-color: #388e3c;
            }
            .delete-button {
                background-color: #f44336;
            }
            .delete-button:hover {
                background-color: #d32f2f;
            }
            h4 {
                color: red;
            }
            table {
                width: 100%;
                border-collapse: collapse;
                margin-top: 20px;
                background-color: #ffffff;
                border-radius: 8px;
                overflow: hidden;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
                font-size: 14px;
                margin-left: auto;
                margin-right: auto;
            }
            th, td {
                padding: 12px;
                text-align: left;
                border-bottom: 1px solid #ddd;
            }
            th {
                background-color: #4caf50;
                color: #ffffff;
                font-weight: bold;
            }
            tr:hover {
                background-color: #f1f1f1;
            }
            img {
                max-width: 50px;
                border-radius: 4px;
            }
            .action-buttons {
                display: flex;
                gap: 10px;
            }
            .form-actions {
                display: flex;
                justify-content: center;
                gap: 10px;
                margin-top: 20px;
            }
        </style>
        <script>
            function confirmDelete(productId) {
                if (confirm("Are you sure you want to delete this product?")) {
                    location.href = 'admindeleteproduct?pid=' + productId;
                }
            }
        </script>
    </head>
    <body>
        <div class="container">
            <div class="header">
                <a href="adminhomejsp.jsp" class="button">Back to Home</a>
                <h2 onClick="location.href = 'adminproduct'">Admin Product Management</h2>
                <span class="admin-name">${acc.username}</span>
            </div>
            <h4>${er}</h4>

            <form action="adminproduct" method="get" class="search-form">
                <input type="text" name="searchKeyword" class="search-input" placeholder="Search product name..." value="${searchKeyword}">
                <button type="submit" class="search-button">Search</button>
            </form>

            <div class="sort-buttons">
                <a href="adminproduct?searchKeyword=${searchKeyword}&sortType=asc" class="sort-button">Sort by Price Ascending</a>
                <a href="adminproduct?searchKeyword=${searchKeyword}&sortType=desc" class="sort-button">Sort by Price Descending</a>
            </div>

            <table>
                <thead>
                    <tr>
                        <th>Product Name</th>
                        <th>Price</th>
                        <th>Details</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${list}" var="c">
                        <tr>
                            <td>${c.getProductName()}</td>
                            <td>$${c.getPrice()}</td>
                            <td><a href="adminproductdetail?id=${c.id}">View Details</a></td>
                            <td>
                                <div class="action-buttons">
                                    <button class="button" onClick="location.href = 'adminupdateproduct?pid=${c.getId()}'">Update</button>
                                    <button class="button delete-button" onClick="confirmDelete(${c.getId()})">Delete</button>
                                </div>
                            </td> 
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <div class="form-actions">
                <button class="button" onClick="location.href = 'adminaddproduct.jsp'">Add Product</button>
                <button class="button" onClick="location.href = 'logout'">Log Out</button>
            </div>
        </div>
    </body>
</html>
