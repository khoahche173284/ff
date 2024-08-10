<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Fantasy Grammar - Favorite Essays</title>
        <link rel="stylesheet" href="history.css">
        <style>
            .centered-div {
                display: flex;
                align-items: center;
                justify-content: center;
                margin: auto;
            }

            .delete {
                margin-left: 5px;
            }

            .edit {
                background-color: green;
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

            .button-side-bar {
                background-color: #eeeeee;
                font-family: 'Calibri';
                color: #1d1c1c;
                border: none;
                padding: 10px 10px 10px 0px;
                text-align: center;
                width: 75%;
                display: block;
                margin-bottom: 10px;
                cursor: pointer;
                transition: background-color 0.3s;
                transition: 0.2s;
                border-radius: 20px;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            }

            .button-side-bar:hover {
                background-image: linear-gradient(to bottom right, #b24de2, #ad8be3, #7697d5);
                color: white;
                padding: 15px;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.4);
            }

            .dung {
                background-image: linear-gradient(to bottom right, #b134eb, #7933eb, #1a60e2);
                color: white;
                padding: 15px;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.4);
            }

            .old-text p {
                padding: 5px 5px;
            }

            .category {
                color: violet;
                padding-top: 0px;
                padding-bottom: 0px;
            }

            .old-text {
                transition: 0.75s;
                margin: 12px;
                padding: 0px 10px;
                position: relative;
                animation-duration: 4s;
                animation-delay: 2s;
                margin-top: 30px;
                background-color: rgb(255, 255, 255);
                color: #b25fff;
                border: 1px solid #e9e9e9;
                height: 150px;
                width: 150px;
                line-height: 50px;
                text-align: center;
                cursor: pointer;
                box-shadow: 0 0 10px rgba(153, 152, 146, 0.5);
                border-radius: 5px;
            }

            .old-text p {
                font-size: 10px;
                color: rgb(87, 86, 87);
                margin: 0px;
                padding: 0px 10px 20px 10px;
                height: 5px;
            }

            .essay {
                color: #958f8f;
            }

            .category {
                color: violet;
                padding-top: 0px;
                padding-bottom: 0px;
            }
        </style>
    </head>

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
                <button class="button-side-bar" onclick="location.href = 'history';"> Draft</button>
                <button class="button-side-bar" onclick="location.href = 'trash';">Trash</button>
            </nav>
            <button onclick="location.href = 'logout';" class="sign-out button-side-bar"> Log Out</button>
        </aside>
        <main class="content">
            <h2 class="wellcome" style="font-family: Calibri">Your Favorite Essays</h2>
            <div class="search-container">
                <form class="search-container" method="get" action="favorite">
                    <input type="text" name="keyword" placeholder="Search essays..." class="search-box" value="${param.keyword}">
                    <select name="category" class="search-box">
                        <option value="">Select Category</option>
                        <c:forEach var="cat" items="${categories}">
                            <option value="${cat}" ${cat == param.category ? 'selected' : ''}>${cat}</option>
                        </c:forEach>
                    </select>
                    <button type="submit" class="search-button">Search</button>
                </form>
            </div>


            <section class="text">
                <c:forEach var="favoriteEssay" items="${favoriteEssays}">
                    <div class="old-text">
                        <span class="category">${favoriteEssay.category}</span>
                        <p class="essay">${favoriteEssay.content.length() > 15 ? favoriteEssay.content.substring(0, 15) : favoriteEssay.content}</p>
                        <p class="date">Created on: <fmt:formatDate value="${favoriteEssay.createdDate}" pattern="yyyy-MM-dd"/></p>
                        <div class="centered-div">
                            <form method="get" action="updatetext">
                                <input type="hidden" name="essayId" value="${favoriteEssay.essayId}">
                                <input type="hidden" name="page" value="${currentPage}">
                                <input type="hidden" name="keyword" value="${keyword}">
                                <button class="edit" style="background-image: linear-gradient(to bottom right, #b2f16a, #9be259, #c4e76b);">⚙️</button>
                            </form>
                            <form method="get" action="unlikefav">
                                <input type="hidden" name="essayId" value="${favoriteEssay.essayId}">
                                <input type="hidden" name="page" value="${currentPage}">
                                <input type="hidden" name="keyword" value="${keyword}">
                                <input type="hidden" name="category" value="${category}">
                                <button class="delete" style="background-image: linear-gradient(to bottom right, #f1c40f, #f39c12);">💔</button>
                            </form>
                            <form method="post" action="deletefav" onsubmit="return confirmDelete(this);">
                                <input type="hidden" name="essayId" value="${favoriteEssay.essayId}">
                                <input type="hidden" name="page" value="${currentPage}">
                                <input type="hidden" name="keyword" value="${keyword}">
                                <input type="hidden" name="category" value="${category}">
                                <button class="delete" style="background-image: linear-gradient(to bottom right, #e74c3c, #c0392b);">🗑️</button>
                            </form>
                        </div>
                    </div>
                </c:forEach>
            </section>
            <p id="succ" style="color:#9be259">${succ}</p>
            <p id="err" style="color:#e44b4b">${err}</p>
            <script>
                setTimeout(() => {
                    const succ = document.getElementById("succ");
                    if (succ) {
                        succ.style.display = "none";
                        fetch('clearmessage?type=succ');
                    }
                    const err = document.getElementById("err");
                    if (err) {
                        err.style.display = "none";
                        fetch('clearmessage?type=err');
                    }
                }, 1000);
                function confirmDelete(form) {
                    return confirm("Are you sure you want to delete this essay?");
                }
            </script>
            <div class="pagination">
                <c:forEach var="i" begin="1" end="${noOfPages}">
                    <a href="favorite?page=${i}&keyword=${param.keyword}&category=${param.category}" class="${i == currentPage ? 'active' : ''}">${i}</a>
                </c:forEach>
            </div>

            <h3 class="errol" style="color: rgb(228, 75, 75)">${err}</h3>
        </main>
    </body>

</html>