
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Feedback Manager</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                margin: 0;
                padding: 20px;
                box-sizing: border-box;
                background-color: #f5f5f5;
                display: flex;
                flex-direction: column;
                align-items: center;
            }
            .container {
                width: 100%;
                max-width: 800px;
                background-color: #fff;
                padding: 20px;
                border-radius: 8px;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            }
            .back-button {
                padding: 10px 20px;
                background-color: #4caf50;
                color: #ffffff;
                border: none;
                border-radius: 4px;
                cursor: pointer;
                text-decoration: none;
                font-size: 14px;
                margin-bottom: 20px;
                transition: background-color 0.3s;
            }
            .back-button:hover {
                background-color: #388e3c;
            }
            form {
                width: 100%;
                margin: 20px 0;
                display: flex;
                justify-content: center;
            }
            select {
                width: 50%;
                padding: 10px;
                font-size: 16px;
                border-radius: 5px;
                border: 1px solid #ccc;
                background-color: #fff;
                color: #333;
            }
            table {
                width: 100%;
                margin: 20px 0;
                border-collapse: collapse;
                box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
                background-color: #fff;
            }
            th, td {
                padding: 12px;
                text-align: left;
                border: 2px solid #ddd;
            }
            th {
                background-color: #4CAF50;
                color: white;
                border-bottom: 3px solid #ddd;
            }
            tr:nth-child(even) {
                background-color: #f2f2f2;
            }
            tr:nth-child(odd) {
                background-color: #fff;
            }
            tr:hover {
                background-color: #d4e3fc;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <a href="adminhomejsp.jsp" class="back-button">Back to Home</a>
            <form action="FeedbackManager" method="POST">
                <select name="FeedbackType" onchange="this.form.submit()">
                    <option value="FeedBack Type">FeedBack Type</option>
                    <c:forEach items="${fbt}" var="c">
                        <option value="${c.feedbackName}" ${c.feedbackName==feedbackName ? 'selected="selected"':''}>${c.feedbackName}</option>
                    </c:forEach>
                </select>
            </form>
            <table>
                <thead>
                    <tr>
                        <th>ID</th>

                        <th>Content</th>
                        <th>Date</th> 
                        <th>Reply</th>
                        <th>Delete</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${list}" var="c">
                        <tr>
                            <td>${c.id}</td>
                            <td>${c.content}</td>
                            <td>${c.createdDate}</td> 
                            <td>
                                <button type="button" onclick="location.href = 'replyfeedback?uid=${c.userId}'">Reply</button>
                            </td>
                            <td>
                                <button type="button" onclick="location.href = 'FeedbackManager?action=delete&id=${c.id}&FeedbackType=${param.FeedbackType}">Delete</button>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
</html>
