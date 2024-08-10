<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %><!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Fantasy Grammar</title>
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
                color : white ;
                padding : 15px;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.4);
            }
            .dung{
                background-image: linear-gradient(to bottom right, #b134eb, #7933eb, #1a60e2);
                color: white;
                padding : 15px;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.4);
            }
            .old-text p {

                padding : 5px 5px;
            }
            .category{
                color : violet;
                padding-top : 0px ;
                padding-bottom: 0px;




            }
            .category{

            }
            .old-text {
                transition: 0.75s;
                margin : 12px;
                padding : 0px 10px;
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
                p{
                    font-size: 10px;
                    color: rgb(87, 86, 87);
                    margin: 0px;
                    padding: 0px 10px 20px 10px ;
                    height: 5px;
                }
                .essay{
                    color:#958f8f;
                }
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
                <button class="button-side-bar dung" onclick="location.href = 'history';">Draft</button>
                <button class="button-side-bar " onclick="location.href = 'trash';">Trash</button>
                <button class="button-side-bar" onclick="location.href = 'favorite';">Favorite</button>

            </nav>
            <button onclick="location.href = 'logout';" class="sign-out button-side-bar"> Log Out</button>
        </aside>
        <main class="content">
            <h2 class="wellcome" style="font-family: Calibri">Draft Of Your Text</h2>
            <div class="search-container">
                <form class="search-container" method="get" action="history">
                    <input type="text" name="keyword" placeholder="Search some word..." class="search-box" value="${param.keyword}">
                    <select class="search-box"  name="category">
                        <option value="">All Categories</option>
                        <c:forEach var="cat" items="${categories}">
                            <option value="${cat}" ${param.category == cat ? 'selected' : ''}>${cat}</option>
                        </c:forEach>
                    </select>
                    <button type="submit" class="search-button">Search</button>
                </form>

            </div>
            <hr>
            <section class="text">
                <c:if test="${not empty listHistory}">
                    <h5 class="cate">TEXT</h5>  
                    <c:forEach var="historyy" items="${listHistory}">
                        <div class="old-text">
                            <span class="category">${historyy.categoriesName}</span>
                            <p class="essay">${historyy.lastTimeChange.substring(0, 10)}</p>
                            <p class="date">${historyy.content.length() > 15 ? historyy.content.substring(0, 15) : historyy.content}</p>
                            <div class="centered-div">
                                <form method="get" action="duplicate">
                                    <input type="hidden" name="id" value="${historyy.id}">
                                    <input type="hidden" name="currentPage" value="${currentPage}">
                                    <input type="hidden" name="keyword" value="${param.keyword}">
                                    <input type="hidden" name="category" value="${param.category}">
                                    <button class="delete" style="background-image: linear-gradient(to bottom right, #9bc4ff, #C6DBF5, #9bc4ff);">📋</button>
                                </form>
                                <form method="post" action="${favoritedMap[historyy.essayId] ? 'unlikefav' : 'addfavessay'}">
                                    <input type="hidden" name="essayId" value="${historyy.essayId}">
                                    <input type="hidden" name="currentPage" value="${currentPage}">
                                    <input type="hidden" name="keyword" value="${param.keyword}">
                                    <input type="hidden" name="category" value="${param.category}">
                                    <button class="delete" type="submit" style="background-image: linear-gradient(to bottom right, #b2f16a, #9be259, #c4e76b);">
                                        ${favoritedMap[historyy.essayId] ? '💖' : '🤍'}
                                    </button>
                                </form>
                                <form method="get" action="updatetext">
                                    <input type="hidden" name="essayId" value="${historyy.essayId}">
                                    <button class="delete" style="background-image: linear-gradient(to bottom right, #b2f16a, #9be259, #c4e76b);">🖊️</button>
                                </form>
                                <form method="get" action="deleteHistory" onsubmit="return confirmDelete(this);">
                                    <input type="hidden" name="id" value="${historyy.id}">
                                    <input type="hidden" name="currentPage" value="${currentPage}">
                                    <input type="hidden" name="keyword" value="${param.keyword}">
                                    <input type="hidden" name="category" value="${param.category}">
                                    <button class="delete">✖️</button>
                                </form>

                            </div>
                        </div>
                    </c:forEach>

                </c:if>
            </section>
            <p id="succ" style="color:#9be259">${succ}</p>
            <p id="err" style="color:red">${err}</p>
            <script>
                setTimeout(() => {
                    const succ = document.getElementById("succ");
                    if (succ) {
                        succ.style.display = "none";
                        // Xóa giá trị succ khỏi session
                        fetch('clearmessage?type=succ');
                    }
                    const err = document.getElementById("err");
                    if (err) {
                        err.style.display = "none";
                        // Xóa giá trị err khỏi session
                        fetch('clearmessage?type=err');
                    }
                }, 600);
                
                function confirmDelete(form) {
        return confirm("Are you sure you want to delete this essay?");
    }
            </script>
            <c:if test="${totalPages > 1}">
                <div class="pagination">
                    <c:forEach begin="1" end="${totalPages}" var="i">
                        <a href="history?page=${i}&keyword=${param.keyword}&category=${param.category}" class="${currentPage == i ? 'active' : ''}">${i}</a>
                    </c:forEach>
                </div>
            </c:if>

        </main>
    </body>

</html>
