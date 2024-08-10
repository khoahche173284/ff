<%-- 
    Document   : UpdateEmailOTP
    Created on : Jun 15, 2024, 12:49:04 AM
    Author     : Tuan Hung
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Verify OTP</title>
    </head>
    <body>
        <h1>Verify OTP</h1>

        <form action="changeaccount" method="post">
            <label for="otp">Enter OTP:</label>
            <input type="text" id="otp" name="otp" required><br><br>

            <input type="submit" value="Verify OTP">
        </form>

        <form action="resendOtpUpdateEmail" method="post">
            <input type="hidden" name="resend" value="true">
        </form>

        <% 
            String errorMessage = (String) request.getAttribute("er");
            if (errorMessage != null) {
        %>
        <p style="color: red;"><%= errorMessage %></p>
        <% } %>

    </body>
</html>
