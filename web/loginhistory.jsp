<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="entity.LoginHistory" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>login history</title>
        <link rel="stylesheet" href="css/home.css">
        <style>
            .content {
                padding: 40px;
            }
            .content h1 {
                font-size: 24px;
                color: #333;
                margin-bottom: 20px;
            }
            .content p {
                color: #666;
                margin-bottom: 20px;
            }
            .table-container {
                height: 50vh; /* Set the desired height */
                overflow-y: auto;
            }
            .content table {
                width: 100%;
                border-collapse: collapse;
            }
            .content th, .content td {
                padding: 15px;
                text-align: left;
            }
            .content th {
                background-color: #f2f2f2;
                color: #333;
                position: sticky;
                top: 0;
                z-index: 2;
            }
            .content tbody tr:nth-child(odd) {
                background-color: #f9f9f9;
            }
            .content tbody tr:nth-child(even) {
                background-color: #fff;
            }
        </style>

    <body>
        <header>
            <div class="logo">
                <img onclick="location.href = 'home.jsp';" src="https://static.vecteezy.com/system/resources/previews/012/986/609/non_2x/network-sharing-circle-logo-icon-free-png.png">
            </div>
            <h1 class="title-home">Fantasy Grammar</h1>
            <h5 class="accountname">${acc.realname}</h5>
            <img class="avatar" src="${acc.img}" alt="avatar" />
        </header>
        <aside class="sidebar">
            <nav>
                <button class="button-side-bar" onclick="location.href = 'home.jsp';"> Home</button>

                <button class="button-side-bar" onclick="location.href = 'Account';"> Back</button>

            </nav>
            <button onclick="location.href = 'logout';" class="sign-out button-side-bar"> Log Out</button>
        </aside>
        <div class="content">
            <!--      <h2>Select Activity Type</h2>
            <form action="FilterActivity" method="post">
                <label for="activityType">Choose an activity type:</label>
                <select name="activityType" id="activityType">
            <c:forEach var="activityType" items="${activityTypes}">
                <option value="${activityType.id}">${activityType.activityName}</option>
            </c:forEach>
        </select>
        <br><br>
        <input type="submit" value="Submit">
    </form>-->
            <h2>Select Activity Type</h2>
            <form action="filterActivity" method="post">
                <label for="activityType">Choose an activity type:</label>
                <select name="activityType" id="activityType">
                    <c:forEach var="activityType" items="${activityNames}">
                        <option value="${activityType.id}">${activityType.activityName}</option>
                    </c:forEach>
                </select>
                <br><br>
                <input type="submit" value="Submit">
            </form>

            <h1>Recent Activity</h1>
            <p>View your account activity across all devices from the past 60 days.</p>
            <div class="table-container">
                <table>
                    <thead>
                        <tr>
                            <th>Date</th>
                            <th>Activity</th>
                            <th>OS</th>
                            <th>Location <span title="Your location">?</span></th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="loginHistory" items="${loginHistoryList}">
                            <tr>
                                <td><fmt:formatDate value="${loginHistory.loginTime}" pattern="MMMM dd, yyyy 'at' hh:mm a" /></td>
                                <td>${loginHistory.typeName}</td>
                                <td>Windows</td>
                                <td>Vietnam, Hanoi</td>
                            </tr>
                        </c:forEach>

                        <c:forEach var="filteredLoginHistory" items="${filterList}">
                            <c:if test="${not empty filteredLoginHistory}">
                                <tr>
                                    <td><fmt:formatDate value="${filteredLoginHistory.loginTime}" pattern="MMMM dd, yyyy 'at' hh:mm a" /></td>
                                    <td>${filteredLoginHistory.typeName}</td>
                                    <td>Windows</td>
                                    <td>Vietnam, Hanoi</td>
                                </tr>
                            </c:if>
                        </c:forEach>


                    </tbody>
                </table>
            </div>
        </div>
    </body>
</html>