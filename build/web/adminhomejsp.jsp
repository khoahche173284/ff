<%-- 
    Document   : adminhomejsp
    Created on : Jun 3, 2024, 3:14:27 PM
    Author     : pgb31
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Home</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f0f0f0;
                text-align: center;
                margin-top: 50px;
            }
            h1 {
                color: #333;
            }
            .button-container {
                margin: 20px;
            }
            .button {
                background-color: #4CAF50; /* Green */
                border: none;
                color: white;
                padding: 15px 32px;
                text-align: center;
                text-decoration: none;
                display: inline-block;
                font-size: 16px;
                margin: 4px 2px;
                cursor: pointer;
                border-radius: 8px;
            }
            .button:hover {
                background-color: #45a049;
            }
        </style>
    </head>
    <body>
        <h1>Admin Home</h1>
        <div class="button-container">
            <button type="submit" class="button" onclick="location.href = 'dashboard'">Go to Dashboard</button>
        </div>
        <div class="button-container">
            <form action="admin" method="GET">
                <button type="submit" class="button">User's Account Manager</button>
            </form>
        </div>
        <div class="button-container">
            <form action="OrderDetailManagerServlet" method="GET">
                <button type="submit" class="button">Order Detail Manager</button>
            </form>
        </div>
        <div class="button-container">
            <form action="FeedbackManager" method="GET">
                <button type="submit" class="button">Feedback Manager</button>
            </form>
        </div>
        
        <div class="button-container">
            <form action="adminproduct" method="GET">
                <button type="submit" class="button">Premium Package Manager</button>
            </form>
        </div> 
          <div class="button-container">
            <form action="AdManagerForum" method="GET">
                <button type="submit" class="button">Forum Manager</button>
            </form>
        </div> 
           <div class="button-container">
                <button type="submit" class="button" onclick="location.href = 'home.jsp'">Back to Normal home</button>
        </div>
        
    </body>
</html>
