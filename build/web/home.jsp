<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Fantasy Grammarly</title>
        <link rel="stylesheet" href="css/home.css">
        <style>
            .error-message {
                color: red;
                font-weight: bold;
                margin-top: 10px;
            }
            .upload-form {
                display: flex;
                align-items: center;
                gap: 10px;
                margin-top: 20px;
            }

            .custom-file-upload {
                display: inline-block;
                padding: 10px 20px;
                cursor: pointer;
                background-color: #4CAF50;
                color: white;
                border: none;
                border-radius: 5px;
            }

            .custom-file-upload:hover {
                background-color: #45a049;
            }

            .upload-button {
                padding: 10px 20px;
                background-color: #008CBA;
                color: white;
                border: none;
                border-radius: 5px;
                cursor: pointer;
            }

            .upload-button:hover {
                background-color: #007bb5;
            }

            .upload-form input[type="file"] {
                display: none;
            }

            .file-name {
                font-size: 16px;
                color: #333;
            }
        </style>
        <script>
            function updateFileName() {
                const input = document.getElementById('file-upload');
                const fileName = document.getElementById('file-name');
                fileName.textContent = input.files.length > 0 ? input.files[0].name : '';
                document.getElementById('upsuc').style.display = 'inline-block';
            }
            function confirmLogout() {
                return confirm("Are you sure you want to log out?");
            }
            function hideErrorMessage() {
                const errorMessage = document.getElementById('error-message');
                if (errorMessage) {
                    setTimeout(() => {
                        errorMessage.style.display = 'none';
                    }, 2000); // 2000 milliseconds = 2 seconds
                }
            }
            document.addEventListener('DOMContentLoaded', (event) => {
                hideErrorMessage();
            });
        </script>
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
                <img class="avatar" src="${acc.img}" alt="avatar" />
            </c:if>
        </header>
        <aside class="sidebar">
            <nav>
                <button class="button-side-bar" onclick="location.href = 'Account';">👤 Account</button>
                <button class="button-side-bar" onclick="location.href = 'history';">🧾 Draft</button>
                <button class="button-side-bar" onclick="location.href = 'favorite';">❤️ Favorite</button>
                <button class="button-side-bar" onclick="location.href = 'trash';">🗑️ Trash</button>
                <!--                <button class="button-side-bar" onclick="location.href = 'feedback';">💡 Feedback </button>-->
                <button class="button-side-bar" onclick="location.href = 'userfeedbacklist';">📫 My Feedback</button>
                <c:if test="${acc.roleId == 2}">
                    <button class="button-side-bar" onclick="location.href = 'ProductController';">💡 Upgrade</button>
                </c:if>
                <c:if test="${acc.roleId == 3}">
                    <button class="button-side-bar" onclick="location.href = 'StatusCartController';">💡 Status Cart </button>
                </c:if>
                <button class="button-side-bar" onclick="location.href = 'getAllPost';">🌍 Forum </button>
                <c:if test="${acc.roleId == 1}">
                    <button onclick="location.href = 'adminhomejsp.jsp';" class="button-side-bar">💡 Manage</button>
                </c:if>
            </nav>
            <button onclick="if (confirmLogout()) {
                        location.href = 'logout';
                    }" class="sign-out button-side-bar">🕛 Log Out</button>
            <c:if test="${acc == null}">
                <button onclick="location.href = 'login';" class="sign-out button-side-bar">👤 Log In</button>
            </c:if>
        </aside>
        <main class="content">
            <h1 class="welcome">Welcome to Fantasy Grammarly</h1>
            <hr>
            <button class="new">
                <span onclick="window.location.href = 'text.jsp';" class="newnew">🗏 New</span>
            </button>
            <c:if test="${acc.roleId == 3}">
                <form action="fileup" method="post" enctype="multipart/form-data" class="upload-form">
                    <label for="file-upload" style=" margin-left: 20px; background-color: #666" class="custom-file-upload">
                        Choose File
                    </label>
                    <input id="file-upload" type="file" name="file" onchange="updateFileName()" />
                    <span id="file-name" class="file-name"></span>

                    <button type="submit" id="upsuc" style = "display:none ; background-color: #7679d5" class="upload-button">Upload</button>

                </form>
                <c:if test="${not empty errorMessage}">
                    <div id="error-message" class="error-message">${errorMessage}</div>
                </c:if>
            </c:if>
        </main>
    </body>

</html>