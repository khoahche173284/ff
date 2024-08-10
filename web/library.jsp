<%-- 
    Document   : library
    Created on : Jun 16, 2024, 12:49:12 AM
    Author     : pgb31
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Library Management</title>
        <link rel="stylesheet" href="css/home.css">
        <style>
            body {
                font-family: Arial, sans-serif;
                margin: 0;
                padding: 0;
                background-color: #F6F6F6;
                text-align: center;
                color: #5d4373;
            }
            h1, h2 {
                color: #6b467a;
            }
            button {
                padding: 10px 20px;
                background-color: #d1b3e0;
                color: white;
                border: none;
                border-radius: 5px;
                cursor: pointer;
                margin: 10px 0;
            }
            button:hover {
                background-color: #c49bd4;
            }
            table {
                overflow: auto;
                width: 60vw;
                height: 30vh;
                margin: 20px auto;
                border-collapse: collapse;
                background-color: white;
            }
            .taybo{
                overflow: auto;
                height: 30vh;
            }
            th, td {
                padding: 12px;
                border: 1px solid #ddd;
                text-align: left;
            }
            th {
                background-color: #e9d8f3;
            }
            form {
                height: 36vh;
                display: inline-block;
                margin: 20px auto;
                padding: 20px;
                background-color: white;
                border: 1px solid #ddd;
                border-radius: 5px;
            }
            input[type="text"] {
                padding: 10px;
                margin: 10px 0;
                width: 100%;
                box-sizing: border-box;
                border: 1px solid #ddd;
                border-radius: 5px;
            }
            input[type="submit"] {
                padding: 10px 20px;
                background-color: #d1b3e0; 
                color: white;
                border: none;
                border-radius: 5px;
                cursor: pointer;
            }
            input[type="submit"]:hover {
                background-color: #c49bd4; 
            }
        </style>
    </head>
    <body>
        <header>
            <div class="logo">
                <img onclick="location.href = 'home.jsp';"
                     src="https://static.vecteezy.com/system/resources/previews/012/986/609/non_2x/network-sharing-circle-logo-icon-free-png.png">
            </div>
            <h3 class="title-home">Fantasy Grammarly</h3>
            <c:if test="${acc != null}">
                <h5 class="accountname">Hello ${acc.realname}</h5>
                <img class="avatar" src="${acc.img}" alt="avatar"/>
            </c:if>
        </header>
        <aside class="sidebar">
            <nav>
                <button class="button-side-bar" onclick="location.href = 'Account';">Back</button>
               
            </nav>
            <button onclick="location.href = 'logout';" class="sign-out button-side-bar">🕛 Log Out</button>
            <c:if test="${acc == null}">
                <button onclick="location.href = 'login';" class="sign-out button-side-bar">👤 Log In</button>
            </c:if>
        </aside>
        <main class="content">
            <c:if test="${empty elist}">
                <form action="Library" method="POST">
                    <h2>Add New Word</h2>
                    <label for="key">Enter your word:</label>
                    <input type="text" id="key" name="key" value="" /><br>
                    <label for="define">Definition:</label>
                    <input type="text" id="define" name="define" value="" /><br>
                    <input type="submit" value="Add" />
                </form> 
            </c:if>

            <p>${er}</p>
            <div class = "taybo">
                <table>
                    <thead>
                        <tr>
                            <th>Words</th>
                            <th>Definition</th> 
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="c" items="${list}">
                            <tr>
                                <td>${c.keyword}</td>
                                <td>${c.define}</td>
                                <td>
                                    <button onClick="location.href = 'changeword?lid=${c.id}'">Edit</button>
                                    <button onClick="location.href = 'deletelib?lid=${c.id}'">Delete</button>
                                </td> 
                            </tr> 
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <c:if test="${not empty elist}">
                <form action="changeword" method="POST"> 
                    <h3>Edit Word</h3>
                    <table>
                        <thead>
                            <tr>
                                <th>Current words</th>
                                <th>Current definition</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="c" items="${elist}">
                                <tr>
                                    <td>${c.keyword}</td>
                                    <td>${c.define}</td> 
                                </tr>
                                <tr>
                            <tr>
                                <td>
                                    <input type="text" name="newkey" value="${c.keyword}" required/>
                                </td>
                                <td>
                                    <input type="text" name="newdefi" value="${c.define}" required/>
                                </td>
                            </tr>
                            </tr>
                            </c:forEach>
                            
                        </tbody>
                    </table>
                    <input type="hidden" name="lid" value="${lid}">
                    <input type="submit" value="Save" />
                </form>
            </c:if>
        </main>
    </body>
</html>
