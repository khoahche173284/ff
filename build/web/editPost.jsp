<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>CheckGrammar Forum</title>
        <link rel="stylesheet" href="css/home.css">
        <style>
            body {
                font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
                margin: 0;
                padding: 0;
                background-color: #f9f9f9;
            }
            .header {
                padding: 10px 0;
                display: flex;
                justify-content: space-between;
                align-items: center;
                width: 100vw;
                border-bottom: 1px solid rgba(255, 255, 255, 0.1);
            }
            .logo {
                font-size: 28px;
                font-weight: bold;
            }
            .nav ul {
                list-style-type: none;
                margin: 0;
                padding: 0;
                display: flex;
            }
            .nav ul li {
                transition: 0.5s;
                margin: 0 20px;
                cursor: pointer;
                font-size: 16px;
            }
            .nav ul li:hover {
                transition: 0.5s;
                font-size: 17px;
                display: inline-block;
            }
            .search input {
                padding: 10px;
                border-radius: 25px;
                border: 1px solid #ccc;
                width: 200px;
            }
            .user-info span {
                margin-left: 20px;
                font-size: 16px;
            }
            .notifications, .messages {
                background-color: red;
                color: white;
                border-radius: 50%;
                padding: 2px 6px;
            }
            .content {
                display: flex;
                margin: 30px;
                /* Set the height as per your requirement */

            }
            .main-content {
                gap: 30px;
                height: 100vh;
                overflow: auto;
                flex: 3;
            }
            .sidebarr {
                flex: 1;
            }
            .announcement {
                background-color: #fff;
                padding: 15px;
                margin-bottom: 20px;
                text-align: center;
                border: 1px solid #e0e0e0;
                border-radius: 10px;
            }
            .posts {
                background-color: #fff;
                padding: 20px;
                border-radius: 10px;
                border: 1px solid #e0e0e0;
            }
            .post {
                border-bottom: 1px solid #e0e0e0;
                padding: 15px 0;
                display: flex;
                justify-content: center;
                align-items: center;
            }

            .post:last-child {
                border-bottom: none;
            }
            .post-title {
                font-weight: bold;
                color: #6a11cb;
            }
            .post-details {
                margin-top: 5px;
            }
            .post-time {
                color: #888;
                font-size: 12px;
            }
            .post-actions {
                margin-top: 10px;
                display: flex;
                justify-content: space-between;
            }
            .post-actions span {
                color: #0078D7;
            }
            .post-actions button {
                background: none;
                border: none;
                color: #6a11cb;
                cursor: pointer;
                display: flex;
                align-items: center;
                font-size: 14px;
            }
            .post-actions button span {
                margin-left: 5px;
            }
            .user-profile {
                background-color: #fff;
                padding: 20px;
                border-radius: 10px;
                text-align: center;
                border: 1px solid #e0e0e0;
            }
            .post-section {
                background: linear-gradient(90deg, #6a11cb 0%, #2575fc 100%);
                color: #fff;
                padding: 15px;
                border-radius: 10px;
                display: flex;
                align-items: center;
                margin-bottom: 20px;
                border: 1px solid rgba(255, 255, 255, 0.1);
            }
            .post-section img {
                border-radius: 50%;
                margin-right: 15px;
            }
            .post-section input {
                flex: 1;
                padding: 10px;
                border-radius: 25px;
                border: none;
                font-size: 16px;
            }
            .post-actions div {
                display: flex;
                align-items: center;
                color: #fff;
                cursor: pointer;
            }
            .nha {
                transition: 0.5s;
                color: #ccc;
            }
            .li:hover {
                transition: 1s;
                font-size: 20px;
                text-decoration: none;
            }
            .zoombuton {
                transition: 0.6s;
            }
            .zoombuton:hover {
                transition: 0.3s;
                font-size: 20px;
            }
            /* Modal styles */
            .modal {
                display: none;
                position: fixed;
                z-index: 1;
                left: 0;
                top: 0;
                width: 100%;
                height: 100%;
                overflow: auto;
                background-color: rgba(0, 0, 0, 0.6);
                padding-top: 60px;
            }
            .modal-content {
                background-color: #e0e0e0;
                margin: 5% auto;
                padding: 20px;
                border: 1px solid #888;
                width: 90%;
                max-width: 500px;
                border-radius: 10px;
                color: grey;
            }
            .close {
                color: #aaa;
                float: right;
                font-size: 28px;
                font-weight: bold;
            }
            .close:hover,
            .close:focus {
                color: white;
                text-decoration: none;
                cursor: pointer;
            }
            .modal-header, .modal-body, .modal-footer {
                padding: 10px 20px;
            }
            .modal-header {
                border-bottom: 1px solid #444;
            }
            .modal-footer {
                border-top: 1px solid #444;
                text-align: right;
            }
            .modal-body {
                margin: 15px 0;
            }
            .modal-header h2 {
                margin: 0;
            }
            .modal-footer button {
                padding: 10px 20px;
                background-color: #6a11cb;
                border: none;
                color: white;
                border-radius: 5px;
                cursor: pointer;
            }
            .modal-footer button:hover {
                background-color: #444;
            }
            .modal-body textarea {
                width: 100%;
                padding: 10px;
                border-radius: 5px;
                border: 1px solid #ccc;
                font-size: 16px;
            }
            .post-actions .icon {
                font-size: 18px;
                margin-right: 5px;
            }
            .dropdown {
                position: relative;
                display: inline-block;
            }

            .dropbtn {
                background-color: #6a11cb;
                color: white;
                padding: 10px;
                font-size: 16px;
                border: none;
                cursor: pointer;
                border-radius: 5px;
                transition: background-color 0.3s, transform 0.3s;
            }

            .dropbtn:hover {
                background-color: #531bcb;
            }

            .dropdown-content {
                display: none;
                position: absolute;
                background-color: #f9f9f9;
                min-width: 160px;
                box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
                z-index: 1;
                border-radius: 5px;
            }

            .dropdown:hover .dropdown-content {
                display: block;
            }

            .dropdown-content form,
            .dropdown-content a {
                color: black;
                padding: 12px 16px;
                text-decoration: none;
                display: block;
                text-align: left;
                border-bottom: 1px solid #ddd;
            }

            .dropdown-content a {
                background-color: #6a11cb;
                color: white;
                border: none;
                border-radius: 5px;
                transition: background-color 0.3s, transform 0.3s;
                text-align: center;
            }

            .dropdown-content a:hover {
                background-color: #531bcb;
            }

            .dropdown-button {
                background-color: #6a11cb;
                color: white;
                padding: 10px;
                font-size: 16px;
                border: none;
                border-radius: 5px;
                transition: background-color 0.3s, transform 0.3s;
                width: 100%;
                text-align: center;
                cursor: pointer;
            }

            .dropdown-button:hover {
                background-color: #531bcb;
            }

            .dropdown-content form {
                margin: 0;
            }

            .dropdown-content form button {
                background-color: transparent;
                border: none;
                color: inherit;
                width: 100%;
                text-align: left;
            }
            .comment-container {
                margin-bottom: 20px;
            }
            .comment {
                display: flex;
                align-items: flex-start;
                margin-bottom: 10px;
                background-color: #f2f3f5;
                border-radius: 10px;
                padding: 10px;
            }
            .comment img {
                border-radius: 50%;
                margin-right: 10px;
            }
            .comment-content {
                background-color: #fff;
                border-radius: 10px;
                padding: 10px;
                flex-grow: 1;
            }
            .comment-content .author {
                font-weight: bold;
            }
            .comment-content .meta {
                font-size: 12px;
                color: #888;
            }
            .comment-content .text {
                margin: 10px 0;
            }
            .comment-actions {
                display: flex;
                justify-content: flex-start;
                gap: 10px;
                font-size: 12px;
                color: #0078D7;
            }
            .comment-actions button {
                background: none;
                border: none;
                color: #0078D7;
                cursor: pointer;
            }
        </style>
    </head>
    <body>
        <header>
            <div class="header">
                <div class="logo">
                    <img onclick="location.href = 'home.jsp';" src="https://static.vecteezy.com/system/resources/previews/012/986/609/non_2x/network-sharing-circle-logo-icon-free-png.png">
                </div>
                <div class="nav">
                    <ul>
                        <li class="nha">Home</li>
                        <li>Type of post</li>
                        <li>What's Hot</li>
                        <li>Liked</li>
                        <li>Premium Members</li>
                        <li>Check Points</li>
                        <li>Shop</li>
                        <li>${acc.realname}</li>
                    </ul>
                </div>
                <div class="search">
                    <input type="text" placeholder="Advanced Search...">
                </div>
                <div class="user-info">
                    <span>${acc.realname}</span>
                    <span class="notifications">3</span>
                    <span class="messages">2</span>
                    <span class="settings"></span>
                </div>
            </div>
        </header>
        <aside class="sidebar">
            <nav>
                <button class="button-side-bar" onclick="location.href = 'Account';">👤 Account</button>
                <button class="button-side-bar" onclick="location.href = 'history';">🧾 Draft</button>
                <button class="button-side-bar" onclick="location.href = 'trash';">🗑️ Trash</button> 
                <button style="background: linear-gradient(90deg, #6a11cb 0%, #2575fc 100%);color : white;" class="button-side-bar" onclick="location.href = 'getAllPost';">💡 Forum</button>
                <button class="button-side-bar" onclick="location.href = 'feedback';">💡 Feedback</button>  
                <c:if test="${acc.roleId == 2}">
                    <button class="button-side-bar" onclick="location.href = 'ProductController';">💡 Upgrade</button> 
                </c:if>
                <c:if test="${acc.roleId == 3}">
                    <button class="button-side-bar" onclick="location.href = 'StatusCartController';">💡 Status Cart </button>
                </c:if>
                <c:if test="${acc.roleId == 1}">
                    <button onclick="location.href = 'adminhomejsp.jsp';" class="button-side-bar">💡 Manage</button>
                </c:if>
            </nav>
            <button onclick="location.href = 'logout';" class="sign-out button-side-bar">🕛 Log Out</button>
            <c:if test="${acc == null}">
                <button onclick="location.href = 'login';" class="sign-out button-side-bar">👤 Log In</button>
            </c:if>
        </aside>
        <main>
            <div class="content">
                <div class="main-content">
                    <div class="announcement">
                        <span>Join the CheckGrammar community on Facebook</span>
                    </div>
                    <div class="post-section">
                        <img src="${acc.img}" alt="Profile" width="40" height="40">
                        <input id="postInput" type="text" placeholder="${acc.realname}, what are you thinking?">
                    </div>
                    <div class="post-actions">
                        <div>
                            <span class="icon">🎥</span>
                            <span>Live Video</span>
                        </div>
                        <div>
                            <span class="icon">📷</span>
                            <span>Photo/Video</span>
                        </div>
                        <div>
                            <span class="icon">😊</span>
                            <span>Feeling/Activity</span>
                        </div>
                    </div>
                    ${message}


                    <div class="posts">
                        <div class="post">



                            <form action="editPost" method="post" style="display: inline;">
                                <div style="position: relative;"> 
                                    <input type="hidden" name="postId" value="${postId}">
                                    <div style="color : #444" class="post-title">${real}</div>
                                    <div class="dropdown" style="position: absolute; top: 0; right: 0;">
                                    </div>
                                </div>
                                <div class="input-container" style="display: flex; align-items: center;">
                                    <textarea name="postContent" rows="1"
                                              style="flex: 1; width: 1000px; height: 40px; overflow: hidden; padding: 10px; border: none;
                                              border-radius: 25px; outline: none; resize: none; font-size: 16px;
                                              background-color: #f5f5f5; color: #000;">${post.noiDung}</textarea>
                                    <button type="submit" style="
                                            padding: 10px 20px;
                                            background-color: #6a11cb;
                                            border: none;
                                            border-radius: 5px;
                                            margin-left: 10px;
                                            color: white;
                                            font-size: 16px;
                                            cursor: pointer;
                                            transition: background-color 0.3s, transform 0.3s;">
                                        done
                                    </button>
                                </div>


                            </form> 



                            <hr><!-- comment -->
                            <div class="post-actions">

                            </div>
                        </div>
                    </div>
                    <br/>


                </div>
                <div class="sidebarr">
                    <div class="user-profile">
                        <span>${acc.realname}</span>
                        <span>Points: 1,072</span>
                        <span>Posts: 0</span>
                        <span>Level: 4</span>
                        <span>Interaction Points: 35</span>
                    </div>
                </div>
            </div>
        </main>

        <!-- Post Modal -->
        <div id="myModal" class="modal">
            <div class="modal-content">
                <form action="createPost" method="get">
                    <div class="modal-header">
                        <span class="close" data-modal-id="myModal">&times;</span>
                        <h2>Create Post</h2>
                    </div>
                    <div class="modal-body">
                        <input type="text" name="title" placeholder="Title"/>
                        <input type="hidden" name="typeId" value="1"/>
                        <textarea name="noiDung" id="postContent" rows="4" placeholder="${acc.realname}, what are you thinking?"></textarea>
                    </div>
                    <div class="modal-footer">
                        <button type="submit">Đăng</button>
                    </div>
                </form>
            </div>
        </div>

        <script>
            document.addEventListener("DOMContentLoaded", function () {
                // Post modal script
                var postModal = document.getElementById("myModal");
                var postInput = document.getElementById("postInput");

                postInput.addEventListener("focus", function () {
                    postModal.style.display = "block";
                    document.getElementById("postContent").value = postInput.value;
                    postInput.value = '';
                });

                // Handle closing modals
                var closeButtons = document.getElementsByClassName("close");

                Array.from(closeButtons).forEach(button => {
                    button.addEventListener("click", function () {
                        var modalId = this.getAttribute('data-modal-id');
                        document.getElementById(modalId).style.display = "none";
                    });
                });

                window.addEventListener("click", function (event) {
                    if (event.target == postModal) {
                        postModal.style.display = "none";
                    }
                });
            });
        </script>

    </body>
</html>