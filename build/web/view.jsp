<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Comments</title>
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
                padding: 12px 16px;
                text-decoration: none;
                display: block;
            }
            .dropdown-content a:hover {
                background-color: #f1f1f1;
            }
            .dropdown:hover .dropdown-content {
                display: block;
            }
            .comment-container {
                margin-left: 200px;

            }
            .commentluot{
                gap: 30px;
                height: 50vh;
                overflow: auto;
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
            .comment-form {
                background-color: #fff;
                padding: 20px;
                border-radius: 10px;
                margin-top: 20px;
            }
            .comment-form textarea {
                width: 60vw;
                padding: 10px;
                border-radius: 5px;
                border: 1px solid #ccc;
                font-size: 16px;
                margin-bottom: 10px;
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
        <main style="margin-left: 5vw; padding-right: 5vw;">         
            <p style="text-align: center; font-size: 20px; font-weight: bold; color: #888; margin-bottom: 20px;">${postt.realName}</p>
            <p style="margin-left: 15vw; text-align: justify; font-size: 16px; line-height: 1.6; padding: 20px; border: 1px solid #ccc; border-radius: 10px; background-color: #f9f9f9; color: #555;">${postt.noiDung}</p>

            <div class="comment-container">
                <div class="commentluot">
                    <c:forEach var="comment" items="${listComments}">

                        <div class="comment">
                            <img src="${comment.img}" alt="User Image" width="40" height="40">
                            <div class="comment-content">
                                <div class="author">${comment.realName}</div>
                                <div class="meta">${comment.ngayDang}</div>
                                <div class="text">${comment.noiDung}</div>
                                <div class="comment-actions">
                                    <form action="LikeCmtController" method="post" style="display: inline;">
                                        <input type="hidden" name="commentId" value="${comment.id}">
                                        <input type="hidden" name="postId" value="${comment.postId}">
                                        <input type="hidden" name="commentContent" value="${comment.noiDung}">
                                        <button type="submit">Like</button>
                                    </form>

                                    <form action="ReplyCmtController" method="post" style="display: inline;">
                                        <input type="hidden" name="commentId" value="${comment.id}">
                                        <input type="hidden" name="postId" value="${comment.postId}">
                                        <input type="hidden" name="commentContent" value="${comment.noiDung}">
                                        <button type="button" class="reply" data-comment-id="${comment.id}" data-post-id="${comment.postId}">Reply</button>
                                    </form>

                                    <c:if test="${acc.id == comment.userId}">
                                        <form action="editComment" method="post" style="display: inline;">
                                            <input type="hidden" name="commentId" value="${comment.id}">
                                            <input type="hidden" name="postId" value="${comment.postId}">
                                            <input type="hidden" name="commentContent" value="${comment.noiDung}">
                                            <button type="submit">Edit</button>
                                        </form>
                                    </c:if>

                                    <form action="deleteComment" method="get" style="display: inline;">
                                        <input type="hidden" name="commentId" value="${comment.id}">
                                        <input type="hidden" name="postId" value="${comment.postId}">
                                        <input type="hidden" name="commentContent" value="${comment.noiDung}">
                                        <button type="submit">Delete</button>
                                    </form>
                                    <form action="ViewReply" method="get" style="display: inline;">
                                        <input type="hidden" name="commentId" value="${comment.id}">
                                        <input type="hidden" name="postId" value="${comment.postId}">
                                        <button type="submit">View more</button>
                                    </form>  

                                </div>                        
                            </div>  
                        </div> 
                    </c:forEach> 
                </div>

                <c:if test="${type == '2'}">
                    <c:forEach var="repcmt" items="${listreply}">
                        <div class="comment">
                            <img src="${repcmt.img}" alt="User Image" width="40" height="40">
                            <div class="comment-content">
                                <div class="author">${repcmt.realName}</div>
                                <div class="meta">${repcmt.ngayDang}</div>
                                <div class="text">${repcmt.noiDung}</div>
                            </div> 
                        </div> 
                    </c:forEach>
                </c:if>
                <div class="comment-form">
                    <form action="createComment" method="get" style="display: flex; align-items: center; background-color: #f5f5f5; border-radius: 25px; padding: 10px; margin: 1px auto;">
                        <img class="comment" src="${acc.img}" width="40" height="40" style="border-radius: 50%; margin-right: 10px;">
                        <input type="hidden" name="postId" value="${param.postId}">

                        <textarea name="commentContent" rows="1" placeholder="Write your comment here..." style="flex: 1; padding: 10px; border: none; border-radius: 25px; outline: none; resize: none; font-size: 16px; background-color: #f5f5f5; color: #000;"></textarea>
                        <button type="submit" style="background-color: #000; color: white; border: none; padding: 10px; border-radius: 50%; margin-left: 5px; cursor: pointer;">➤</button>
                    </form>
                    <p>${vln}</p>
                </div>
            </div>
        </main>

        <!-- Modal for Reply -->
        <div id="replyModal" class="modal">
            <div class="modal-content">
                <div class="modal-header">
                    <span class="close" id="closeReplyModal">&times;</span>
                    <h2>Reply to Comment</h2>
                </div>
                <div class="modal-body">
                    <form id="replyForm" action="ReplyCmtController" method="post">
                        <input type="hidden" name="commentId" id="replyCommentId">
                        <input type="hidden" name="postId" id="replyPostId">
                        <textarea name="commentContent" id="replyContent" rows="4" placeholder="Write your reply here..." style="width: 100%; padding: 10px; border-radius: 5px; border: 1px solid #ccc;"></textarea>
                        <div class="modal-footer">
                            <button type="submit">Submit Reply</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <script>
            // Get the modal
            var modal = document.getElementById("replyModal");

            // Get the button that opens the modal
            var replyButtons = document.querySelectorAll("button.reply");

            // Get the <span> element that closes the modal
            var span = document.getElementById("closeReplyModal");

            // When the user clicks the button, open the modal
            replyButtons.forEach(function (button) {
                button.onclick = function () {
                    var commentId = this.getAttribute("data-comment-id");
                    var postId = this.getAttribute("data-post-id");
                    document.getElementById("replyCommentId").value = commentId;
                    document.getElementById("replyPostId").value = postId;
                    modal.style.display = "block";
                };
            });

            // When the user clicks on <span> (x), close the modal
            span.onclick = function () {
                modal.style.display = "none";
            };

            // When the user clicks anywhere outside of the modal, close it
            window.onclick = function (event) {
                if (event.target == modal) {
                    modal.style.display = "none";
                }
            };
        </script>
    </body>
</html>
