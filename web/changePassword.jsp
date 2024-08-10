<%-- 
    Document   : changePassword
    Created on : June 1, 2024, 4:55:26 PM
    Author     : Quang
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>



<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Change Password</title>
        <!-- Link to Bootstrap CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <!-- Link to Font Awesome CSS -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.2/css/all.min.css">
        <link rel="stylesheet" href="css/changepass.css">
        <style>
            /* Custom style for centering text */
            .card-header h5 {
                text-align: center;
                margin-bottom: 0;
            }
            .card-header{
                background-color : rgb(93, 107, 236);
            }

            /* Custom style for better appearance */
            .card {
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            }

            /* Custom style for button */
            .btn-update {
                background-color: #7260eb;
                color: #fff;
            }
        </style>
    </head>

    <body>
        <header>
            <div class="logo">
                <img onclick="location.href = 'home.jsp';"
                     src="https://static.vecteezy.com/system/resources/previews/012/986/609/non_2x/network-sharing-circle-logo-icon-free-png.png">
            </div>
            <h1 class="title-home">Change Password</h1>
            <h5 class="accountname"> ${acc.realname}</h5>
            <img class="avatar" src="${acc.img}" alt="avatar"/>
        </header>
        <aside class="sidebar">

            <button class="button-side-bar" onclick="location.href = 'home.jsp';">Home</button>
            <button class="button-side-bar" onclick="location.href = 'Account';">Account</button>
            <button class="button-side-bar">Setting</button>
            <button onclick="logOut();location.href = 'login';" class="sign-out button-side-bar">Sign Out</button>
        </aside>
        <script>
            function logOut() {
                alert("Do you want to log out?");
            }


        </script>
        <main>
            <div class="container mt-5">
                <div class="row justify-content-center">
                    <div class="col-md-6">
                        <div class="card">



                            <div class="card-header text-white">
                                <h5>Change Password</h5>
                            </div>
                            <div class="card-body">
                                <!-- Replace the content inside this form with password change fields -->
                                <form action="ChangPassController" method="post">
                                    <div class="form-group">
                                        <label for="currentPassword">Current Password:</label>
                                        <div class="input-group">
                                            <input type="password" class="form-control" name="currentPassword" id="currentPassword" required>
                                            <div class="input-group-append">
                                                <span class="input-group-text">
                                                    <i class="fas fa-eye" id="toggleCurrentPassword"></i>
                                                </span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="newPassword">New Password:</label>
                                        <div class="input-group">
                                            <input type="password" name="newPassword" class="form-control" id="newPassword" required>
                                            <div class="input-group-append">
                                                <span class="input-group-text">
                                                    <i class="fas fa-eye" id="toggleNewPassword"></i>
                                                </span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="confirmPassword">Confirm New Password:</label>
                                        <div class="input-group">
                                            <input type="password" name="confirmPassword" class="form-control" id="confirmPassword" required>
                                            <div class="input-group-append">
                                                <span class="input-group-text">
                                                    <i class="fas fa-eye" id="toggleConfirmPassword"></i>
                                                </span>
                                            </div>
                                        </div>
                                    </div>
                                    <c:if test="${errOldPass != null}">
                                        <p style="color: red">${errOldPass}</p>
                                    </c:if>

                                    <c:if test="${errNewPass != null}">
                                        <p style="color: red">${errNewPass}</p>
                                    </c:if> 

                                    <!-- Update button -->
                                    <div class="text-center">
                                        <button type="submit" class="btn btn-update">Change Password</button>
                                        <a href="Account" class="btn btn-link">Back</a>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </main>


        <!-- Link to Bootstrap JS, Popper.js, and Font Awesome JS -->
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

        <script>
            // Toggle password visibility
            function togglePasswordVisibility(inputId, iconId) {
                const passwordInput = document.getElementById(inputId);
                const icon = document.getElementById(iconId);

                icon.addEventListener('click', () => {
                    const type = passwordInput.type === 'password' ? 'text' : 'password';
                    passwordInput.type = type;
                    icon.classList.toggle('fa-eye-slash');
                    icon.classList.toggle('fa-eye');
                });
            }

            // Toggle visibility for each password field
            togglePasswordVisibility('currentPassword', 'toggleCurrentPassword');
            togglePasswordVisibility('newPassword', 'toggleNewPassword');
            togglePasswordVisibility('confirmPassword', 'toggleConfirmPassword');
        </script>

    </body>

</html>