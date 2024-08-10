<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
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

            .feedback {
                transition: 0.75s;
                margin: 12px;
                padding: 10px;
                position: relative;
                animation-duration: 4s;
                animation-delay: 2s;
                background-color: rgb(255, 255, 255);
                color: #b25fff;
                border: 1px solid #e9e9e9;
                text-align: left;
                cursor: pointer;
                box-shadow: 0 0 10px rgba(153, 152, 146, 0.5);
                border-radius: 5px;
                height: 150px;
                width: 300px;
                display: flex;
                flex-direction: column;
                justify-content: space-between;
            }

            .feedback .title {
                font-weight: bold;
            }

            .feedback .date {
                color: #958f8f;
            }

            .feedback .content {
                flex-grow: 1;
            }

            .scrol {
                height: 10vh;
                overflow: auto;
            }

            .message {
                color: #4CAF50;
                font-size: 16px;
                text-align: center;
                margin-top: 20px;
            }

            .message.error {
                color: #e44b4b;
            }
            .new-feedback-btn {
                padding: 8px 16px;
                font-size: 14px;
                border: none;
                border-radius: 5px;
                cursor: pointer;
                transition: background-color 0.3s, transform 0.2s;
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
                <!--                <button class="button-side-bar" onclick="location.href = 'history';">Draft</button>
                                <button class="button-side-bar" onclick="location.href = 'trash';">Trash</button>
                                <button class="button-side-bar" onclick="location.href = 'Account';"> Account</button>
                                <button class="button-side-bar" onclick="location.href = 'update';">Update </button>
                                <button class="button-side-bar" onclick="location.href = 'favorite';">Liked </button>-->
                <button class="button-side-bar dung" onclick="location.href = 'userfeedbacklist';">My Feedback</button>
                <button class="button-side-bar dung" onclick="location.href = 'feedback';">💡 New Feedback </button>
            </nav>
            <button onclick="location.href = 'logout';" class="sign-out button-side-bar"> Log Out</button>
        </aside>
        <main class="content">
            <h2 class="wellcome" style="font-family: Calibri">Your Feedback</h2>

            <div class="search-container">
                <form class="search-container" method="get" action="userfeedbacklist">
                    <input type="text" name="keyword" placeholder="Search some word..." class="search-box" value="${param.keyword}">
                    <select name="category" class="search-box">
                        <option value="">Select Category</option>
                        <c:forEach var="type" items="${feedbackTypes}">
                            <option value="${type}" ${type == param.category ? 'selected' : ''}>${type}</option>
                        </c:forEach>
                    </select>
                    <button type="submit" class="search-button">Search</button>
                </form>
            </div>


            <hr>
            <section class="text">
                <c:if test="${not empty feedbackList}">
                    <c:forEach var="feedback" items="${feedbackList}">
                        <div class="feedback">
                            <span class="category">Type: ${feedback.feedbackName}</span>
                            <p class="essay">${feedback.createdDate}</p>
                            <p class="scrol">${feedback.content}</p>
                            <div class="centered-div">
                                <form method="post" action="userdeletefeedback" onsubmit="return confirmDelete(this);">
                                    <input type="hidden" name="id" value="${feedback.id}">
                                    <input type="hidden" name="currentPage" value="${currentPage}">
                                    <input type="hidden" name="keyword" value="${param.keyword}">
                                    <input type="hidden" name="category" value="${param.category}">
                                    <button class="delete">🗑 Delete</button>
                                </form>
                            </div>
                        </div>
                    </c:forEach>
                </c:if>
            </section>


            <c:if test="${totalPages > 1}">
                <div class="pagination">
                    <c:forEach begin="1" end="${totalPages}" var="i">
                        <a href="userfeedbacklist?page=${i}&keyword=${param.keyword}&category=${param.category}" class="${currentPage == i ? 'active' : ''}">${i}</a>
                    </c:forEach>
                </div>
            </c:if>


            <p class="message" id="succ">${succ}</p>
            <p class="message error" id="err">${err}</p>
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
                }, 1000);
                
                function confirmDelete(form) {
                    return confirm("Are you sure you want to delete this feedback?");
                }
            </script>
        </main>
    </body>

</html>
