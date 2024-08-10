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
                margin-top : -100px;
                background: none;
                border: none;
                font-size: 24px;
                cursor: pointer;
            }
            .dropdown-content {
                color: black;
                display: none;
                position: absolute;
                right: 0;
                background-color: #f9f9f9;
                min-width: 160px;
                box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
                z-index: 1;
            }
            .dropdown-content a {
                color: black;

                text-decoration: none;
                display: block;
            }

            .dropdown:hover .dropdown-content {
                display: block;
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
                        <li class="nha"><a href="home.jsp"> Home</a></li>
                        <li>Type of post</li>
                        <li><a href="whathot">What's Hot</a></li>
                        <li><a href="savepost">Saved Posts</a></li>
                        <li>Premium Members</li>
                        <li>Check Points</li>
                        <li>Shop</li>
                    </ul>
                </div>
                <div class="search">
                    <form action="getAllPost" method="get">
                        <div class="search-container">
                            <input type="text" name="keyword" placeholder="Search by keyword..." value="${param.keyword}">
                            <button type="submit"><i class="fa fa-search"></i></button>
                            <input type="date" name="startDate" placeholder="Search by date..." value="${param.startDate}">
                        </div>
                    </form>
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
                <button style="background: linear-gradient(90deg, #6a11cb 0%, #2575fc 100%);color : white;" class="button-side-bar" onclick="location.href = 'getAllPost';">🌍 Forum</button>
                <button class="button-side-bar" onclick="location.href = 'feedback';">💡 Feedback</button>  
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
                        <a href="https://www.facebook.com/CheckGrammar">Join the CheckGrammar community on Facebook<a>
                                </div> 

                                <c:if test="${UserStatus == 'Normal'}">
                                    <div class="post-section">
                                        <img src="${acc.img}" alt="Profile" width="40" height="40">
                                        <input id="postInput" type="text" placeholder="${acc.realname}, what are you thinking?">
                                    </div>
                                </c:if>  
                                <c:if test="${UserStatus == 'Banned'}"> 
                                    <h3 style="color: red">Your account be banned from posting</h3>
                                </c:if>  
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

                                <p style = "color:#2575fc;margin-left : 300px;">${message}</p>



                                <!-- Display message if exists -->
                                <c:if test="${not empty messaged}">
                                    <div style="color: green; font-weight: bold;">${messaged}</div>
                                </c:if>

                                <c:if test="${not empty error}">
                                    <div style="color: red; font-weight: bold;">${error}</div>
                                </c:if>
                                <c:if test="${empty listPost}">
                                    <p style = "color:#2575fc;margin-left : 400px;">No posts available.</p>
                                </c:if>
                                <c:if test="${not empty listPost}">
                                    <c:forEach var="post" items="${listPost}">
                                        <div class="posts">
                                            <div class="post">
                                                <div style="position: relative;">
                                                    <div style="color : #888" class="post-title">${post.realName}</div>
                                                    <div style="color : #444" class="post-title">${post.title}</div>
                                                    <c:if test="${acc.id ==post.userId}">
                                                        <div class="dropdown" style="position: absolute; top: 0; right: 0;">

                                                            <button class="dropbtn">...</button>

                                                            <div class="dropdown-content">

                                                                <form action="editPost" method="get">
                                                                    <input type="hidden" name="postId" value="${post.id}">
                                                                    <input type="hidden" name="noiDung" value="${post.noiDung}">
                                                                    <input type="hidden" name="real" value="${post.realName}">
                                                                    <button style="color: black; padding: 10px; font-size: 16px;
                                                                            border: none; border-radius: 5px;
                                                                            cursor: pointer; transition: backgrtound-color 0.3s, transform 0.3s; width: 100%; text-align: left;">
                                                                        Edit
                                                                    </button>
                                                                </form>
                                                                <button style=" color: black;padding: 10px; font-size: 16px;
                                                                        border: none; border-radius: 5px;
                                                                        cursor: pointer; transition: background-color 0.3s,
                                                                        transform 0.3s; width: 100%; text-align: left;">
                                                                    <a href="DeletePost?postid=${post.id}" style=" text-decoration: none; display: block; width: 100%; height: 100%;">Delete</a>
                                                                </button>                                        </div>
                                                        </div>
                                                    </c:if> 
                                                </div>
                                                <div class="post-details">${post.noiDung}</div>
                                                <div class="post-time">${post.ngayDang}</div>
                                                <hr><!-- comment -->
                                                <div class="post-actions">
                                                    <form action="likepost" method="POST">
                                                        <input type="hidden" name="postId" value="${post.id}">
                                                        <input type="hidden" name="likenum" value="${post.luotThich}">
                                                        <button> 

                                                            <span>${post.luotThich}</span>
                                                            <span class="zoombuton">👍</span>
                                                            <span>Like</span>

                                                        </button>
                                                    </form>
                                                    <form action="getAllPost" method="get">
                                                        
                                                        <input type="hidden" name="luu" value="${post.id}">
                                                        <input type="hidden" name="action" value="save">
                                                        <input type="hidden" name="postId" value="${post.id}">
                                                        <c:if test="${luu == post.id}">
                                                            
                                                        <button type="submit">
                                                            <span class="zoombuton">💾</span>
                                                            <span>Saved</span>
                                                        </button>
                                                        </c:if>
                                                       
                                                        <c:if test="${luu != post.id}">
                                                            
                                                        <button type="submit">
                                                            <span class="zoombuton">💾</span>
                                                            <span>Save</span>
                                                        </button>
                                                        </c:if>
                                                        
                                                        
                                                    </form>
                                                    <form action="getComment" method="get">  
                                                        <button type="submit" class="commentBtn" data-post-id="${post.id}">
                                                            <input type="hidden" name="postId" value="${post.id}">
                                                            <input type="hidden" name="real" value="${post.realName}">
                                                            <input type="hidden" name="noi" value="${post.noiDung}">
                                                            <span>${post.soLuongComment}</span>
                                                            <span class="zoombuton">💬</span>
                                                            <span>Comment</span>

                                                        </button>
                                                    </form>
                                                    <form id="reportForm" method="post">
                                                        <input type="hidden" name="postId" value="${post.id}">
                                                        <input type="hidden" name="reportnum" value="${post.numberReport}">
                                                        <button type="button" class="reportBtn" data-post-id="${post.id}" data-report-num="${post.numberReport}">
                                                            <span>${post.numberReport}</span>
                                                            <span class="zoombuton">🚩</span>
                                                            <span>Report</span>
                                                        </button> 
                                                    </form>
                                                </div>
                                                <br>
                                                <p style="color: red"> ${popmessage}</p>
                                            </div>
                                        </div>
                                        <br/>
                                    </c:forEach>
                                </c:if>
                                </div>
                                <div class="sidebarr">
                                    <div class="user-profile">
                                        <span style="color : #6a11cb">${acc.realname}</span>

                                        <span>Posts: 0</span>

                                        <span>Points: 35</span>
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
                                                <input type="text" name="title" placeholder="Title" style="font-size: 30px; width :100% ;border: none; border-bottom: 1px solid #ccc; outline: none; padding: 10px;">

                                                <input type="hidden" name="typeId" value="1"/>
                                                <textarea name="noiDung" id="postContent" rows="4" placeholder="${acc.realname}, what are you thinking?"></textarea>
                                            </div>
                                            <div class="modal-footer">
                                                <button type="submit">Post</button>
                                            </div>
                                        </form> 


                                    </div>
                                </div>
                                
                                <!-- Report Modal -->
                                <div id="reportModal" class="modal">
                                    <div class="modal-content"> 
                                        <form action="reportpost" method="post">
                                            <div class="modal-header">
                                                <span class="close" data-modal-id="reportModal">&times;</span>
                                                <h2>Report Post</h2>
                                            </div> 
                                            <div class="modal-body">
                                                <input type="hidden" name="postId" id="reportPostId">
                                                <input type="hidden" name="reportnum" id="reportNum">
                                                <textarea name="reportReason" id="reportReason" rows="4" placeholder="Enter your reason for reporting this post..." required></textarea>
                                            </div>
                                            <div class="modal-footer">
                                                <button type="submit">Submit Report</button>
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

                                        // Report modal script
                                        var reportModal = document.getElementById("reportModal");
                                        var reportButtons = document.getElementsByClassName("reportBtn");

                                        Array.from(reportButtons).forEach(button => {
                                            button.addEventListener("click", function () {
                                                var postId = this.getAttribute('data-post-id');
                                                var reportNum = this.getAttribute('data-report-num');
                                                document.getElementById("reportPostId").value = postId;
                                                document.getElementById("reportNum").value = reportNum;
                                                reportModal.style.display = "block";
                                            });
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
                                            if (event.target == reportModal) {
                                                reportModal.style.display = "none";
                                            }
                                        });
                                    });
                                </script>

                                </body>
                                </html>
