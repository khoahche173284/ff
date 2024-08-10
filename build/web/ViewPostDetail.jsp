<%-- 
    Document   : ViewPostDetail
    Created on : Jul 14, 2024, 3:30:33 PM
    Author     : pgb31
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Post Detail</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f4f4f4;
                margin: 0;
                padding: 20px;
                display: flex;
                justify-content: flex-start;
                align-items: flex-start;
                height: 100vh;
            }
            .main-content {
                display: flex;
                width: 100%;
                height: 100%;
            }
            .container {
                background-color: #fff;
                padding: 30px;
                border-radius: 8px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                flex-grow: 2;
                margin-right: 20px;
                display: flex;
                flex-direction: column;
                align-items: center;
                border-left: 8px solid #4CAF50; /* Lighter green border */
            }
            .textarea-container {
                width: 100%;
                display: flex;
                justify-content: center;
                margin-bottom: 20px;
            }
            .sidebar {
                display: flex;
                flex-direction: column;
                flex-grow: 1;
            }
            .section {
                margin-bottom: 20px;
                background-color: #fff;
                padding: 30px;
                border-radius: 8px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                border-left: 8px solid #4CAF50; /* Lighter green border */
            }
            h3, h4 {
                color: #4CAF50; /* Lighter green */
                border-bottom: 2px solid #f4f4f4;
                padding-bottom: 10px;
                margin-bottom: 20px;
                width: 100%;
            }
            textarea {
                width: 80%;
                height: 150px;
                padding: 15px;
                border: 1px solid #ddd;
                border-radius: 4px;
                resize: none;
                overflow-y: scroll;
            }
            p {
                color: #333; /* Darker text */
                margin: 5px 0;
            }
            .stats {
                display: flex;
                justify-content: space-between;
                margin-bottom: 20px;
                width: 100%;
                color: #333;
            }
            table {
                width: 100%;
                border-collapse: collapse;
                margin-bottom: 20px;
            }
            table, th, td {
                border: 1px solid #ddd;
            }
            th, td {
                padding: 15px;
                text-align: left;
            }
            th {
                background-color: #4CAF50; /* Lighter green */
                color: #fff; /* White text */
            }
            button {
                padding: 10px 20px;
                font-size: 14px;
                margin: 5px;
                border: 1px solid #4CAF50; /* Lighter green border */
                border-radius: 4px;
                background-color: #4CAF50; /* Lighter green background */
                color: #fff; /* White text */
                cursor: pointer;
                transition: background-color 0.3s ease;
            }
            button:hover {
                background-color: #388E3C; /* Slightly darker green */
            }
            .comment {
                display: flex;
                justify-content: flex-start;
                width: 100%;
                margin-bottom: 10px;
            }
            .comment h4 {
                margin: 0;
                padding-right: 10px;
                color: #4CAF50; /* Lighter green */
            }
            .comment p {
                margin: 0;
                color: #333;
            }
            .button-container {
                position: fixed;
                right: 20px;
                top: 20px;
                display: flex;
                flex-direction: column;
                align-items: flex-end;
            }
        </style>
    </head>
    <body>
        <div class="main-content">
            <div class="container">
                <h3>Posted by: ${Post.getRealName()}</h3>
                <h3>Title: ${Post.title}</h3>
                <div class="textarea-container">
                    <textarea name="noidung" readonly>${Post.noiDung}</textarea>
                </div>
                <div class="stats">
                    <p>Likes: ${Post.luotThich}</p>
                    <p>Comments: ${Post.soLuongComment}</p>
                    <p>Reports: ${Post.numberReport}</p>
                </div>
                <h4>Comments</h4>
                <c:forEach var="c" items="${listcomment}">
                    <div class="comment">
                        <h4>${c.getRealName()}:</h4>
                        <p>${c.noiDung}</p>
                    </div>
                </c:forEach>
            </div>
            <div class="sidebar">
                <div class="section">
                    <h3>List of users who like the post</h3>
                    <table>
                        <thead>
                            <tr>
                                <th>UserName</th>
                                <th>Time</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="l" items="${listlike}">
                                <tr>
                                    <td>${l.getUserName()}</td>
                                    <td>${l.likeDate}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
                <div class="section">
                    <h3>List of reports</h3>
                    <table>
                        <thead>
                            <tr>
                                <th>UserName</th>
                                <th>Time</th>
                                 <th>Reason</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="l" items="${listrp}">
                                <tr>
                                    <td>${l.getUserName()}</td>
                                    <td>${l.reportDate}</td> 
                                    <td>${l.reportreason} </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
           <form action="DeletePost" method="GET">
    <input type="hidden" name="postid" value="${Post.getId()}">
    <button type="submit">Delete Post</button>
</form>
            <br>
            <form action="AdManagerForum" method="GET">
                <button type="submit" class="button">Back</button>
            </form>
        </div>
    </body>
</html>
