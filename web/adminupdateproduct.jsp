<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Update Product</title>
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
                max-width: 800px;
                width: 100%;
                margin: auto;
            }
            .header {
                display: flex;
                justify-content: space-between;
                align-items: center;
                margin-bottom: 20px;
            }
            .back-button, .submit-button {
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
            .back-button:hover, .submit-button:hover {
                background-color: #388e3c;
            }
            h2 {
                margin: 0;
                padding-bottom: 10px;
                font-size: 24px;
            }
            .form-container {
                display: flex;
                flex-direction: column;
                align-items: center;
            }
            form {
                width: 100%;
                max-width: 400px;
                background-color: #f1f1f1;
                padding: 30px;
                border-radius: 8px;
                box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
                margin-bottom: 20px;
            }
            .form-group {
                display: flex;
                flex-direction: column;
                width: 100%;
                margin-bottom: 10px;
            }
            .form-group label {
                margin-bottom: 5px;
                font-weight: bold;
                font-size: 14px;
            }
            .form-group input[type="text"],
            .form-group input[type="number"],
            .form-group textarea {
                padding: 10px;
                border: 1px solid #ced4da;
                border-radius: 4px;
                width: 100%;
                margin-bottom: 8px;
                font-size: 14px;
                transition: border-color 0.3s;
            }
            .form-group input[type="text"]:focus,
            .form-group input[type="number"]:focus,
            .form-group textarea:focus {
                border-color: #4caf50;
            }
            .form-actions {
                display: flex;
                justify-content: center;
                gap: 10px;
                width: 100%;
            }
            .form-actions input[type="submit"] {
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
            .form-actions input[type="submit"]:hover {
                background-color: #388e3c;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <div class="header">
                <a href="<c:url value='/adminproduct'/>" class="back-button">Back to Product Manager</a>
                <h2>Update Product</h2>
            </div>
            <c:if test="${not empty message}">
                <h4 style="color: ${messageType == 'success' ? 'green' : 'red'}">${message}</h4>
            </c:if>

            <div class="form-container">
                <form action="adminupdateproduct" method="post">
                    <div class="form-group">
                        <label for="productId">Product ID</label>
                        <input type="text" id="productId" name="productId" value="${product.id}" readonly>
                    </div>
                    <div class="form-group">
                        <label for="productName">Product Name</label>
                        <input type="text" id="productName" name="productName" value="${product.productName}" required>
                    </div>
                    <div class="form-group">
                        <label for="price">Price</label>
                        <input type="number" id="price" name="price" value="${product.price}" step="1" required>
                    </div>
                    <div class="form-group">
                        <label for="description">Description</label>
                        <textarea id="description" name="description" rows="4" required>${product.description}</textarea>
                    </div>
                    <div class="form-group">
                        <label for="duration">Description</label>
                        <textarea id="duration" name="duration" rows="1" required>${product.duration}</textarea>
                    </div>
                    <div class="form-actions">
                        <input type="submit" value="Update Product" class="submit-button">
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>