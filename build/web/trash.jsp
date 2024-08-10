<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Fantasy Grammar</title>
        <link rel="stylesheet" href="trash.css">
        <style>
            .message {
                color: green;
                font-weight: bold;
                margin: 10px 0;
                transition: opacity 0.5s ease-out;
            }
            .pagination {
                display: flex;
                justify-content: center;
                margin-top: 20px;
            }
            .pagination a {
                margin: 0 5px;
                padding: 8px 16px;
                text-decoration: none;
                border: 1px solid #ddd;
                color: #333;
            }
            .pagination a.active {
                background-color: #4CAF50;
                color: white;
                border: 1px solid #4CAF50;
            }
            .pagination a:hover {
                background-color: #ddd;
            }
            .dung{
                background-image: linear-gradient(to bottom right, #b134eb, #7933eb, #1a60e2);
                color: white;
                padding : 15px;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.3);


            }

        </style>
        <script>
            document.addEventListener("DOMContentLoaded", function() {
                const messageDiv = document.querySelector('.message');
                if (messageDiv) {
                    setTimeout(() => {
                        messageDiv.style.opacity = '0';
                        setTimeout(() => messageDiv.style.display = 'none', 500);
                    }, 1000);
                }

                const deleteButtons = document.querySelectorAll('.delete');
                deleteButtons.forEach(button => {
                    button.addEventListener('click', function(event) {
                        const confirmed = confirm('Are you sure you want to delete this item?');
                        if (!confirmed) {
                            event.preventDefault();
                        }
                    });
                });

                const restoreButtons = document.querySelectorAll('.restore');
                restoreButtons.forEach(button => {
                    button.addEventListener('click', function(event) {
                        const confirmed = confirm('Are you sure you want to restore this item?');
                        if (!confirmed) {
                            event.preventDefault();
                        }
                    });
                });
            });
        </script>
    </head>
    <body>
        <header>
            <div class="logo">
                <img onclick="location.href = 'home.jsp';" src="https://static.vecteezy.com/system/resources/previews/012/986/609/non_2x/network-sharing-circle-logo-icon-free-png.png">
            </div>
            <h1 class="title-home">Fantasy Grammar</h1>
            <h5 class="accountname">${acc.realname}</h5>
            <img class="avatar" src="${acc.img}" alt="avatar"/>
        </header>
        <aside class="sidebar">
            <nav>
                <button class="button-side-bar" onclick="location.href = 'home.jsp';"> Home</button>

                <button class="button-side-bar" onclick="location.href = 'history';">Draft</button>
                <button class="button-side-bar dung" onclick="location.href = 'trash';">Trash</button>
                <!--<button class="button-side-bar" onclick="location.href = 'Account';"> Account</button>-->
                <!--<button class="button-side-bar" onclick="location.href = 'update';">Update </button>-->
            </nav>
            <button onclick="location.href = 'logout';" class="sign-out button-side-bar">Log Out</button>
        </aside>
        <main class="content">
            <h2 class="wellcome" style="font-family: Calibri">Trash Bin</h2>
            <div class="search-container">
                <form class="search-container" method="post" action="trash">
                    <input type="hidden" name="action" value="search">
                    <input type="hidden" name="currentPage" value="${currentPage}">
                    <input type="text" name="keyword" placeholder="Search some word..." class="search-box" value="${param.keyword}">
                    <button type="submit" class="search-button">Search</button>
                </form>
            </div>

            <hr>
            <section class="text">
                <c:if test="${not empty trashList}">
                    <h5 class="cate" style="width: 80px">Your Trash</h5>
                </c:if>

                <c:forEach var="trashItemm" items="${trashList}">
                    <div class="old-text">
                        <p class="essay">${trashItemm.content.length() > 18 ? trashItemm.content.substring(0, 18) : trashItemm.content}</p>
                        <p class="date">
                            <fmt:formatDate value="${trashItemm.deletedDate}" pattern="yyyy-MM-dd HH:mm:ss" />
                        </p>
                        <form method="post" action="trash">
                            <input type="hidden" name="id" value="${trashItemm.id}">
                            <input type="hidden" name="currentPage" value="${currentPage}">
                            <input type="hidden" name="keyword" value="${param.keyword}">
                            <button class="restore" name="action" value="restore">Restore</button>
                            <button class="delete" name="action" value="delete">Delete</button>
                        </form>
                    </div>
                </c:forEach>
            </section>
            <c:if test="${totalPages > 1}">
                <div class="pagination">
                    <c:forEach begin="1" end="${totalPages}" var="i">
                        <a href="trash?page=${i}&keyword=${param.keyword}" class="${currentPage == i ? 'active' : ''}">${i}</a>
                    </c:forEach>
                </div>
            </c:if>
            <c:if test="${not empty sessionScope.message}">
                <div class="message">${sessionScope.message}</div>
                <c:remove var="message" scope="session"/>
            </c:if>
            <h3 class="errol" style="color: rgb(228, 75, 75)">${err}</h3>
        </main>
    </body>
</html>
