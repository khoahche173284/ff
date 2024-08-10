<%-- 
    Document   : admin
    Created on : May 30, 2024, 11:47:05 PM
    Author     : Quang
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Admin</title>
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
            .back-button, .add-button, .sign-out, button {
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
            .back-button:hover, .add-button:hover, .sign-out:hover, button:hover {
                background-color: #388e3c;
            }
            h2, h4 {
                margin: 0;
                padding-bottom: 10px;
                font-size: 24px;
            }
            .admin-name {
                color: #4caf50;
                margin-left: 10px;
                font-size: 18px;
            }
            .form-container {
                display: flex;
                justify-content: space-between;
                margin-bottom: 20px;
            }
            form {
                margin-bottom: 15px;
                width: 100%;
                max-width: 350px;
                display: flex;
                flex-direction: column;
                align-items: center;
                background-color: #f1f1f1;
                padding: 15px;
                border-radius: 8px;
                box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
                margin: 0 10px;
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
            .form-group input[type="date"],
            .form-group select {
                padding: 10px;
                border: 1px solid #ced4da;
                border-radius: 4px;
                width: 100%;
                margin-bottom: 8px;
                font-size: 14px;
                transition: border-color 0.3s;
            }
            .form-group input[type="text"]:focus,
            .form-group input[type="date"]:focus,
            .form-group select:focus {
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
                gap: 5px;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <div class="header">
                <a href="adminhomejsp.jsp" class="back-button">Back to Home</a>
                <div>
                    <h2>Admin</h2>
                    <span class="admin-name">${acc.username}</span>
                </div>
            </div>
            <h4 style="color: red">${er}</h4>

            <div class="form-container">
                <form action="Adsearchaccount" method="POST">
                    <div class="form-group">
                        <label for="rname">Name:</label>
                        <input type="text" name="rname" id="rname" value="" />
                    </div>
                    <div class="form-group">
                        <label for="gmail">Gmail:</label>
                        <input type="text" name="gmail" id="gmail" value="" />
                    </div>
                    <div class="form-group">
                        <label for="userType">User's type:</label>
                        <select name="userType" id="userType">  
                            <option value="User's type">User's type</option>
                            <option value="2">Normal Users</option> 
                            <option value="3">VIP Users</option>
                        </select> 
                    </div>
                    <div class="form-actions">
                        <input type="submit" value="Search" />
                    </div>
                </form>

                <form action="searchbydate" method="POST">
                    <div class="form-group">
                        <label for="date1">Date 1:</label>
                        <input type="date" name="date1" id="date1" value="" />
                    </div>
                    <div class="form-group">
                        <label for="date2">to Date 2:</label>
                        <input type="date" name="date2" id="date2" value="" />
                    </div>
                    <div class="form-actions">
                        <input type="submit" value="Search" />
                    </div>
                </form>
            </div>

            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>User Name</th>
                        <th>Name</th>
                        <th>Gmail</th>
                        <th>Password</th>
                        <th>Img</th>
                        <th>Created Date</th>  
                        <th>User's type</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${list}" var="c">
                        <tr>
                            <td>${c.getId()}</td>
                            <td>${c.getUsername()}</td>
                            <td>${c.getRealname()}</td>
                            <td>${c.getEmail()}</td>
                            <td>${c.getPassword()}</td>
                            <td><img src="${c.getImg()}" alt="User Image"></td>
                            <td>${c.createdDate}</td> 
                            <td>${c.roleId}</td>
                            <td>
                                <div class="action-buttons">
                                    <button onClick="location.href = 'adminedit?Usersid=${c.getId()}'">Edit</button>
                                    <button onClick="location.href = 'admindelete?Usersid=${c.getId()}'">Delete</button>
                                </div>
                            </td> 
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <c:if test="${not empty error}">
                <div style="color: red;">
                    ${error}
                </div>
            </c:if>

            <c:if test="${not empty message}">
                <div style="color: green;">
                    ${message}
                </div>
            </c:if>
            <div class="form-actions" style="justify-content: center; margin-top: 20px;">
                <button class="add-button" onClick="location.href = 'adduser.jsp'">Add User</button>
                <button class="sign-out" onClick="location.href = 'logout'">Sign Out</button>
            </div>
        </div>
    </body>
</html>