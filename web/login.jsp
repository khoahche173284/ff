<!DOCTYPE html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Login</title>
        <link rel="stylesheet" href="login.css">
    </head>

    <body>
    <body>
        <div class="container">
            <form class="login" action="login" method="POST" onsubmit="return validateForm()">
                <h1>Login</h1>
                <c:if test="${errLogin != null}">
                    <h6 style="color: red">${errLogin}</h6>
                </c:if>
                <input name="username" type="text" value="${userC}" placeholder="Username" />
                <div class="container-pass">
                    <input class="password" name="password" type="password" value="${passC}" placeholder="Password" id="passwordid" />
                    <span class="eye" onclick="Password()">👁️‍🗨️️️</span>
                </div>
                <div class="re">
                    <input type="checkbox" name="remember" id="remember-me" class="agree-term" value="1"/>
                    <label for="remember-me" class="label-agree-term"><span><span></span></span>Remember me</label>
                </div>
                <a href="">Forgot your password?</a>
                <div class="hh">
                    <button class="signin" type="submit">LogIn</button>
                </div>
                <div class="signup">
                    <img onClick="location.href = 'https://accounts.google.com/o/oauth2/auth?scope=email&redirect_uri=http://localhost:9999/Checking_Grammar/LoginGoogleHandler&response_type=code&client_id=695244412352-c5nu6e8mttjf4rf8hajs924er2j15m1q.apps.googleusercontent.com&approval_prompt=force';" class="google" src="https://w7.pngwing.com/pngs/326/85/png-transparent-google-logo-google-text-trademark-logo-thumbnail.png" alt="Logo Google" />
                    <button type="button" onclick="redirectToSignup()">Register</button>
                </div>
                <% 
String successMessage = (String) request.getAttribute("successMessage");
if (successMessage != null) {
                %>
                <p style="color:green;"><%= successMessage %></p>
                <% 
                    } 
                %>
            </form>
        </div>

        <script>
            function Password() {
                var password = document.getElementById('passwordid');
                var passwordType = password.getAttribute('type');
                if (passwordType === 'password') {
                    password.setAttribute('type', 'text');
                } else {
                    password.setAttribute('type', 'password');
                }
            }
            function redirectToSignup() {
                window.location.href = "signup.jsp";
            }
        </script>
    </body>

</html>