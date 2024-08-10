<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Personal Post</title>
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
            gap: 30px;
            height: 600px; /* Set the height as per your requirement */
            overflow: auto;
        }
        .main-content {
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
            background-color: #45a049;
        }
        .modal-body textarea {
            width: 100%;
            padding: 10px;
            border-radius: 5px;
            border: 1px solid #ccc;
            font-size: 16px;
        }
        .comment {
            border-top: 1px solid #e0e0e0;
            padding-top: 10px;
            margin-top: 10px;
        }
        .comment .comment-author {
            font-weight: bold;
            color: #6a11cb;
        }
        .comment .comment-time {
            color: #888;
            font-size: 12px;
        }
        .comment .comment-content {
            margin-top: 5px;
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
            <button style="background: linear-gradient(90deg, #6a11cb 0%, #2575fc 100%);color : white;" class="button-side-bar" onclick="location.href = 'forum.jsp';">💡 Forum</button>
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
                <div class="posts">
                    <div class="post">
                        <div class="post-title">${post.title}</div>
                        <div class="post-details">${post.noiDung}</div>
                        <div class="post-time">${post.ngayDang}</div>
                        <hr><!-- comment -->
                        <div class="post-actions">
                            <button>
                                <span>${post.luotThich}</span>
                                <span class="zoombuton">👍</span>
                                <span>Like</span>
                            </button>
                            <button class="commentBtn" data-post-id="${post.id}">
                                <span>${post.soLuongComment}</span>
                                <span class="zoombuton">💬</span>
                                <span>Comment</span>
                            </button>
                            <button>
                                <span>${post.numberReport}</span>
                                <span class="zoombuton">🚩</span>
                                <span>Report</span>
                            </button>
                        </div>
                        <div class="comments-section">
                            <c:forEach var="comment" items="${listComment}">
                                <div class="comment">
                                    <div class="comment-author">${comment.authorName}</div>
                                    <div class="comment-time">${comment.commentTime}</div>
                                    <div class="comment-content">${comment.content}</div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </div>
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

    <!-- Comment Modal -->
    <div id="commentModal" class="modal">
        <div class="modal-content">
            <div class="modal-header">
                <span class="close" data-modal-id="commentModal">&times;</span>
                <h2>Comment</h2>
            </div>
            <div class="modal-body">
                <form action="addComment" method="post">
                    <input type="hidden" name="postId" id="commentPostId">
                    <textarea name="commentContent" id="commentContent" rows="4" placeholder="Write your comment here..."></textarea>
                    <div class="modal-footer">
                        <button type="submit">Post Comment</button>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <script>
        // Comment modal script
        var commentModal = document.getElementById("commentModal");
        var commentButtons = document.getElementsByClassName("commentBtn");

        Array.from(commentButtons).forEach(button => {
            button.onclick = function() {
                var postId = this.getAttribute('data-post-id');
                document.getElementById("commentPostId").value = postId;
                commentModal.style.display = "block";
            };
        });

        // Handle closing modals
        var closeButtons = document.getElementsByClassName("close");

        Array.from(closeButtons).forEach(button => {
            button.onclick = function() {
                var modalId = this.getAttribute('data-modal-id');
                document.getElementById(modalId).style.display = "none";
            };
        });

        window.onclick = function(event) {
            if (event.target == commentModal) {
                commentModal.style.display = "none";
            }
        }
    </script>
</body>
</html>
