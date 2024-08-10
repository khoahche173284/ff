<%-- 
    Document   : AdminManageForum
    Created on : Jul 14, 2024, 2:35:21 PM
    Author     : pgb31
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Manage Forum</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f4f4f4;
                margin: 0;
                padding: 20px;
            }
            h1 {
                color: #4CAF50; /* Lighter green */
            }
            table {
                width: 100%;
                border-collapse: collapse;
                margin-top: 20px;
            }
            table, th, td {
                border: 1px solid #ddd;
            }
            th, td {
                padding: 10px;
                text-align: left;
            }
            th {
                background-color: #4CAF50; /* Lighter green */
                color: #fff; /* White text */
            }
            td button {
                padding: 8px 15px;
                font-size: 12px;
                border: none;
                border-radius: 4px;
                background-color: #4CAF50; /* Lighter green */
                color: #fff; /* White text */
                cursor: pointer;
                transition: background-color 0.3s ease;
            }
            td button:hover {
                background-color: #388E3C; /* Slightly darker green */
            }
        </style>
    </head>
    <body>
        <h1>Admin Manage Forum</h1>

        <button onclick="location.href = 'adminhomejsp.jsp'">Back</button> 






        <p>${tb}</p> 
        <c:if test="${type == '1'}">
            <form action="ManagerUserInForum" method="GET">
                <input type="submit" value="Manage User" />
            </form>
            <br>
            <h3>List of post</h3>

            <table>
                <thead>
                    <tr>

                        <th>Title</th>
                        <th>Time of Posting</th>
                        <th>Number of Likes</th>
                        <th>Number of Comments</th>
                        <th>Number of Reports</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="post" items="${listPost}">
                        <tr>

                            <td>${post.title}</td>
                            <td>${post.ngayDang}</td>
                            <td>${post.luotThich}</td>
                            <td>${post.soLuongComment}</td>
                            <td>${post.numberReport}</td>
                            <td>
                                <button onclick="location.href = 'ViewPostDetail?postid=${post.id}'">
                                    View Detail
                                </button>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>


        <c:if test="${type == '2'}">
            <form action="AdManagerForum" method="GET">
                <input type="submit" value="Manage Post" />
            </form> 
            <br>
            <form action="Adminsearchuserforum" method="POST"> 
                <select name="Total Post">
                    <option value="0">Total Post</option>
                    <option value="ASC">Total Post: Ascending</option>
                    <option value="DESC">Total Post: Descending</option>
                </select> 
                <select name="Total comment">
                    <option value="0">Total comment</option>
                    <option value="ASC">Total comment: Ascending</option>
                    <option value="DESC">Total comment: Descending</option>
                </select>
                <select name="Total likes">
                    <option value="0">  Total likes</option>
                    <option value="ASC">  Total likes: Ascending</option>
                    <option value="DESC">  Total likes: Descending</option>
                </select> 
                 <select name="Total report">
                    <option value="0">Total report</option>
                    <option value="ASC">Total report: Ascending</option>
                    <option value="DESC">Total report: Descending</option>
                </select> 
                <select name="Status">
                    <option value="0">Status</option>
                    <option value="Normal">Normal</option>
                    <option value="Banned">Banned</option>
                </select>
                <input type="submit" value="Search" />
            </form>
            
            <br>
            <table border="1">
                <thead>
                    <tr>
                        <th>Name</th>
                        <th>Total Posts</th>
                        <th>Total Comments</th>
                        <th>Total Likes Received</th>
                        <th>Total Report Received</th>
                        <th>Status</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="c" items="${listu}">
                    <tr>
                        <td>${c.getUserRealName()}</td>
                        <td>${c.getTotalPost()}</td>
                        <td>${c.getTotalComment()}</td>
                        <td>${c.getTotalLike()}</td>
                        <td>${c.getTotalReport()}</td>
                        <td>${c.getStatus()}</td>
                        <td>
                            <button onclick="location.href ='changestatusforum?uid=${c.getUserId()}'">Change status</button>
                        </td>
                    </tr>
                    </c:forEach>
                </tbody>
            </table>

        </c:if>

    </body>
</html>
