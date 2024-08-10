<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Product Details</title>
        <link rel="stylesheet" href="css/styles.css">
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
                max-width: 800px;
                width: 100%;
                margin: auto;
            }
            h2 {
                margin: 0;
                padding-bottom: 10px;
                font-size: 24px;
                color: #4caf50;
            }
            .product-details {
                margin-top: 20px;
            }
            .product-details p {
                font-size: 18px;
                margin: 10px 0;
            }
            .action-buttons {
                display: flex;
                gap: 10px;
                margin-top: 20px;
            }
            .button, .back-button {
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
            .button:hover, .back-button:hover {
                background-color: #388e3c;
            }
            .delete-button {
                background-color: #f44336;
            }
            .delete-button:hover {
                background-color: #d32f2f;
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
            <h2>Product Details</h2>
            <div class="product-details">
                <p><strong>Product Name:</strong> ${product.productName}</p>
                <p><strong>Price:</strong> ${product.price}</p>
                <p><strong>Description:</strong> ${product.description}</p>
                <p><strong>Duration:</strong> ${product.duration}</p>
            </div>
            <div class="action-buttons">
                <button class="button" onClick="location.href = 'adminupdateproduct?pid=${product.id}'">Update</button>
                <button class="button delete-button" onClick="confirmDelete(${product.id})">Delete</button>
                <a href="<c:url value='/adminproduct'/>" class="back-button">Back to Product Manager</a>
            </div>
        </div>
    </body>
</html>
